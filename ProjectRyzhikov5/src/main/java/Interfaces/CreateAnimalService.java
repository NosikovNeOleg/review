package Interfaces;

import Animals.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CreateAnimalService {

    int quantity = 10;

    default void createAnimals() {
        int i = 0;
        int animalChooser;

        while (i < quantity) {
            System.out.print("Create Animal №" + (i + 1) + ": ");
            animalChooser = i % 4;
            switch (animalChooser) {
                case 0 -> System.out.println(new Cat());
                case 1 -> System.out.println(new Dog());
                case 2 -> System.out.println(new Shark());
                case 3 -> System.out.println(new Wolf());
                default -> System.out.println(new Predator());
            }
            i++;
        }
    }

    void createAnimals(int animalsQuantity);

    Map<String, List<Animal>> createAnimalsMap(int animalsQuantity) throws IOException;

    //String getCatName() ;

}
