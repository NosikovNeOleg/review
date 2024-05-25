package com.rdm.rdm.implementations;

import Animals.*;
import Interfaces.Animal;
import Interfaces.CreateAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

import static java.time.LocalDate.now;


// В AnimalStarter мы уже сконфигурировали бин, если здесь указать @Component или @Service
// то спринг будет ругаться, потому что будет два бина одного класса и он не сможет выбрать ни один
//@Component
//@Service
public class CreateAnimalServiceImpl implements CreateAnimalService {

//    @Autowired
//    public CreateAnimalServiceImpl(List<String> catNames, List<String> dogNames, List<String> wolfNames, List<String> sharkNames) {
//        this.catNames = catNames;
//        this.dogNames = dogNames;
//        this.wolfNames = wolfNames;
//        this.sharkNames = sharkNames;
//    }
    // закомментировал поскольку получили значения через @Value


    public CreateAnimalServiceImpl(){}

    FilesConfig filesConfig = new FilesConfig();
    @Value("${catNames}")
    public List<String> catNames;
    @Value("${dogNames}")
    public List<String> dogNames;
    @Value("${wolfNames}")
    public List<String> wolfNames;
    @Value("${sharkNames}")
    public List<String> sharkNames;

    @Override
    public void createAnimals() {
        int i = 0;
        int animalChooser;
        do {
            System.out.print("     Create Animal №" + (i + 1) + ": ");
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
        while (i < 10);
    }

    public void createAnimals(int animalsQuantity) {
        int animalChooser;
        for (int i = 0; i < animalsQuantity; i++) {
            System.out.print("          Create Animal №" + (i + 1) + ": ");
            animalChooser = i % 4;
            switch (animalChooser) {
                case 0 -> System.out.println(new Cat(getCatName()));
                case 1 -> System.out.println(new Dog(getDogName()));
                case 2 -> System.out.println(new Shark(getSharkName()));
                case 3 -> System.out.println(new Wolf(getWolfName()));
                default -> System.out.println(new Predator());
            }
        }
    }

    public Map<String, List<Animal>> createAnimalsMap(int animalsQuantity) throws IOException {
        Map<String, List<Animal>> animalsMap = new HashMap<>();
        int animalChooser;
        List<Animal> curAnimalsList;
        Animal curAnimal;

        ResultReader resultReader = new ResultReader();
        int lastAnimalInFile = resultReader.LogDataRowsCount() +1;

        for (int i = 0; i < animalsQuantity; i++) {
            System.out.print("          Create Animal №" + (i + 1) + ": ");
            animalChooser = i % 4;
            switch (animalChooser) {
                case 0 -> {
                    curAnimalsList = animalsMap.computeIfAbsent("Cat", k -> new ArrayList<>());
                    //curAnimalsList.add(new Cat("q","w",i+0.1,"e",now()));
                    curAnimal = new Cat("q", "w", i + 0.1, "e", now());
                }
                case 1 -> {
                    curAnimalsList = animalsMap.computeIfAbsent("Dog", k -> new ArrayList<>());
                    //curAnimalsList.add(new Dog("q", "w", i + 0.1, "e", now()));
                    curAnimal = new Dog("q", "w", i + 0.1, "e", now());
                }
                case 2 -> {
                    curAnimalsList = animalsMap.computeIfAbsent("Shark", k -> new ArrayList<>());
                    //curAnimalsList.add(new Shark("q", "w", i + 0.1, "e", now()));
                    curAnimal = new Shark("q", "w", i + 0.1, "e", now());
                }
                case 3 -> {
                    curAnimalsList = animalsMap.computeIfAbsent("Wolf", k -> new ArrayList<>());
                    //curAnimalsList.add(new Wolf("q", "w", i + 0.1, "e", now()));
                    curAnimal = new Wolf("q", "w", i + 0.1, "e", now());
                }
                default -> {
                    curAnimalsList = animalsMap.computeIfAbsent("Predator", k -> new ArrayList<>());
                    //curAnimalsList.add(new Predator("q", "w", i + 0.1, "e", now()));
                    curAnimal = new Predator("q", "w", i + 0.1, "e", now());
                }
            }
            curAnimalsList.add(curAnimal);
            Path path = filesConfig.GetPath(1);
            Files.writeString(path, lastAnimalInFile + " " + curAnimal.getAnimalType()  + " "
                    + curAnimal.getName() + " " + curAnimal.getCost() + " " + curAnimal.getBirthDate() + "\n"
            , StandardOpenOption.APPEND);
            lastAnimalInFile++;
        }
        return animalsMap;
    }

    public String getCatName() {
        Random rn = new Random();
        int randomNum = rn.nextInt(catNames.size());
        return catNames.get(randomNum);
    }

    public String getDogName() {
        Random rn = new Random();
        int randomNum = rn.nextInt(dogNames.size());
        return dogNames.get(randomNum);
    }

    public String getSharkName() {
        Random rn = new Random();
        int randomNum = rn.nextInt(sharkNames.size());
        return sharkNames.get(randomNum);
    }

    public String getWolfName() {
        Random rn = new Random();
        int randomNum = rn.nextInt(wolfNames.size());
        return wolfNames.get(randomNum);
    }

}
