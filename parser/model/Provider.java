package parser.model;

import parser.vo.Vacancy;

import java.net.MalformedURLException;
import java.util.List;

public class Provider {
    private Strategy strategy;

    public Provider(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public List<Vacancy> getJavaVacancies(String searchString) {
        try {
            return strategy.getVacancies(searchString);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
