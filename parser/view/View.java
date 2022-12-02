package parser.view;

import parser.Controller;
import parser.vo.Vacancy;

import java.util.List;

public interface View {

    void update(List<Vacancy> vacancies);
    void setController(Controller controller);
}
