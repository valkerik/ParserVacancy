package parser.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import parser.vo.Vacancy;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class HabrCareerStrategy implements Strategy{
    private static final String URL_FORMAT = "https://career.habr.com/vacancies?q=java+%s&items_on_page=20&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) throws MalformedURLException {
        List<Vacancy> vacancyList = new ArrayList<>();
        int page = 0;
        Document document = null;
        do {

            try {document = getDocument(searchString, page);} catch (IOException e) {e.printStackTrace();}

            Elements dataQa = document.select("[class=vacancy-card]");
            if(dataQa.isEmpty()){break;}

            for (Element element : dataQa) {

                Vacancy vacancy = new Vacancy();

                vacancy.setSiteName("https://career.habr.com/ " + "  страница сайта=" + page);
                vacancy.setTitle(element.getElementsByAttributeValue("class","vacancy-card__title").text());
                vacancy.setUrl("https://career.habr.com/"+element.getElementsByAttributeValue("class", "vacancy-card__title-link").attr("href"));
                vacancy.setCity(element.getElementsByAttributeValue("class","vacancy-card__meta").text());
                vacancy.setCompanyName(element.getElementsByAttributeValue("class","vacancy-card__company").text());
                vacancy.setSalary(element.getElementsByAttributeValue("class","vacancy-card__salary").text());

                vacancyList.add(vacancy);

            }
            page++;
            if(page>1){break;}  //TODO останавливаем на 2 странице (по 25 вакансий со страницы)
        } while (true);

           vacancyList.forEach(vacancy -> System.out.println(vacancy));
        return vacancyList;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        return Jsoup.connect(String.format(URL_FORMAT, searchString, page))
                .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Mobile Safari/537.36")
                .referrer("https://hh.ru/")
                .get();
    }
}
