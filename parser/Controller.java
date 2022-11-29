package parser;

import parser.model.Provider;
import parser.vo.Vacancy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {

    private Provider[] providers;

    public Controller(Provider... providers) {

        if (providers.length == 0) {
            throw new IllegalArgumentException();
        }
        this.providers = providers;
    }

    @Override
    public String toString() {
        return "Controller{" +
                "providers=" + Arrays.toString(providers) +
                '}';
    }

    public void scan() {
        List<Vacancy> vacancyList = new ArrayList<>();
        for (Provider provider : providers) {
            vacancyList.addAll(provider.getJavaVacancies("Russia"));
        }

        System.out.println(vacancyList.size());

    }
}
