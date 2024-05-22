package com.rdm.rdm.implementations;

import Animals.*;
import Interfaces.Animal;
import Interfaces.CreateAnimalService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateAnimalServiceImplDefault implements CreateAnimalService {

    public void createAnimals(int animalsQuantity) {
        int animalChooser;
        for (int i = 0; i < animalsQuantity; i++) {
            System.out.print("          Create Animal №" + (i + 1) + ": ");
            animalChooser = (i + 1) % 4;
            switch (animalChooser) {
                case 0 -> System.out.println(new Cat());
                case 1 -> System.out.println(new Dog());
                case 2 -> System.out.println(new Shark());
                case 3 -> System.out.println(new Wolf());
                default -> System.out.println(new Predator());
            }
        }
    }
    public Map<String, List<Animal>> createAnimalsMap(int animalsQuantity) {
        return new HashMap<>();
    }

    //public String getCatName() {return null;}
}
