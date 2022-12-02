package parser.view;

import parser.Controller;
import parser.vo.Vacancy;

import java.util.List;

public class HtmlView implements View{
    private Controller controller;

    @Override
    public void update(List<Vacancy> vacancies) {

    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }
}
