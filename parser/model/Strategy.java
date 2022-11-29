package parser.model;

import parser.vo.Vacancy;

import java.net.MalformedURLException;
import java.util.List;

public interface Strategy {

    List<Vacancy> getVacancies(String searchString) throws MalformedURLException;
}
