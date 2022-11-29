package parser;

import parser.model.HHStrategy;
import parser.model.HabrCareerStrategy;
import parser.model.Provider;

public class Aggregator {

    public static void main(String[] args) {
        Provider provider = new Provider(new HabrCareerStrategy());
        Controller controller = new Controller(provider);
        controller.scan();
    }
}
