package parser;

import parser.model.HHStrategy;
import parser.model.HabrCareerStrategy;
import parser.model.Provider;

public class Aggregator {

    public static void main(String[] args) {
        Provider providerHabrCareer = new Provider(new HabrCareerStrategy());
        Provider providerHH = new Provider(new HHStrategy());
        Controller controller = new Controller(providerHH, providerHabrCareer);

        controller.scan();
    }
}
