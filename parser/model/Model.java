package parser.model;

import parser.view.View;
import parser.vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private View view;
    private Provider[] providers;

    public Model(View view, Provider... providers) {
       if (view == null || providers == null || providers.length == 0){
           throw new IllegalArgumentException();
       }
        this.view = view;
        this.providers = providers;
    }

    public void selectCity(String city){
        List<Vacancy> vacancyList = new ArrayList<>();
        for (Provider provider : providers) {
           vacancyList.addAll(provider.getJavaVacancies(city)) ;
        }
        view.update(vacancyList);
    }
}
