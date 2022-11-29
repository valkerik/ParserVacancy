package parser.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import parser.vo.Vacancy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HHStrategy implements Strategy {

    private static final String URL_FORMAT = "https://hh.ru/search/vacancy?text=java+%s&items_on_page=20&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancyList = new ArrayList<>();
        int page = 0;
        Document document = null;
        do {

            try {
                document = getDocument(searchString, page);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements dataQa = document.body().getElementsByAttributeValueMatching("data-qa", "vacancy-serp__vacancy\\s");

            if (dataQa.isEmpty()) {
                break;
            }

            for (Element element : dataQa) {

                Vacancy vacancy = new Vacancy();

                vacancy.setSiteName("https://hh.ru/" + "  страница сайта=" + page);
                vacancy.setTitle(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").text());
                vacancy.setUrl(element.getElementsByAttributeValue("data-qa", "serp-item__title").attr("href"));
                vacancy.setCity(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address").text());
                vacancy.setCompanyName(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").text());
                vacancy.setSalary(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation").text());

                vacancyList.add(vacancy);
            }
            page++;
            if (page > 5) {
                break;
            }  //TODO останавливаем на 5 странице (по 20 вакансий со страницы)
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
