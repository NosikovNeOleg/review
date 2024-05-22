package com.rdm.rdm.implementations;

import Exceptions.AnimalArrayEmptyException;
import Exceptions.AnimalArrayNullException;
import Interfaces.Animal;
import Interfaces.AnimalsRepository;
import Interfaces.CreateAnimalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.LocalDate.now;

public class AnimalsRepositoryImpl implements AnimalsRepository {

    FilesConfig filesConfig = new FilesConfig();

    public Map<String, LocalDate> findLeapYearNames(List<Animal> arrayAnimals) throws AnimalArrayNullException, AnimalArrayEmptyException {
        // HW-3-fix Переделал входной аргумент на лист
        if (isInputNull(arrayAnimals)) {
            throw new AnimalArrayNullException();
        }
        if (isInputEmpty(arrayAnimals)) {
            throw new AnimalArrayEmptyException();
        }

        // HW-4 Переделано на Стрим
        return arrayAnimals.stream()
                .filter(p -> p.getBirthDate().isLeapYear())
                .collect(Collectors.toMap(value
                                -> value.getAnimalType() + " " + value.getName(),
                        Animal::getBirthDate));
    }

    public Map<Animal, Integer> findOlderAnimal(List<Animal> arrayAnimals, int age) throws AnimalArrayEmptyException, AnimalArrayNullException {
        // HW-3-fix Переделал входной аргумент на лист
        if (isInputNull(arrayAnimals)) {
            throw new AnimalArrayNullException();
        }
        if (isInputEmpty(arrayAnimals)) {
            throw new AnimalArrayEmptyException();
        }

        // HW-4 Переделано на Стрим
        Map<Animal, Integer> animalsMap;
        animalsMap = arrayAnimals.stream()
                .filter(p -> p.getAge() >= age)
                .collect(Collectors.toMap(value
                                -> value,
                        Animal::getAge));
        if (animalsMap.size() == 0) {
            animalsMap = arrayAnimals.stream()
                    .sorted(Comparator.comparing(Animal::getBirthDate))
                    .limit(1)
                    .collect(Collectors.toMap(value
                                    -> value,
                            Animal::getAge));
        }
        // HW-6
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        //objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        Map<Animal, Integer> animalsMapEncode = new HashMap<>();
        int animalAge;
        for (Animal key : animalsMap.keySet()) {
            animalAge = animalsMap.get(key);
            key.setSecretInformation(Base64.getEncoder().
                    encodeToString(key.getSecretInformation().getBytes()));
            animalsMapEncode.put(key, animalAge);
        }
        String path = filesConfig.GetStringPath(2);
        File file = new File(path);

        try {
            objectMapper.writeValue(file, animalsMapEncode.keySet());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        ResultReader resultReader = new ResultReader();
        resultReader.PrintAnimalJson();

        return animalsMap;
    }

    public Map<String, List<Animal>> findDuplicate(List<Animal> arrayAnimals) throws AnimalArrayNullException, AnimalArrayEmptyException {
        // HW-3-fix Переделал входной аргумент на лист
        if (isInputNull(arrayAnimals)) {
            throw new AnimalArrayNullException();
        }
        if (isInputEmpty(arrayAnimals)) {
            throw new AnimalArrayEmptyException();
        }

        // HW-4 Переделано на Стрим
        return arrayAnimals.stream()
                .collect(Collectors.groupingBy(Animal::getAnimalType,
                        Collectors.toList()));
    }

    // HW-4 Новый метод
    public void findAverageAge(List<Animal> arrayAnimals) throws AnimalArrayNullException, AnimalArrayEmptyException {
        if (isInputNull(arrayAnimals)) {
            throw new AnimalArrayNullException();
        }
        if (isInputEmpty(arrayAnimals)) {
            throw new AnimalArrayEmptyException();
        }
        System.out.println("Средний возраст: " + arrayAnimals.stream().mapToInt(Animal::getAge)
                .summaryStatistics().getAverage());
    }

    // HW-4 Новый метод
    public List<Animal> findOldAndExpensive(List<Animal> arrayAnimals) throws AnimalArrayNullException, AnimalArrayEmptyException {
        if (isInputNull(arrayAnimals)) {
            throw new AnimalArrayNullException();
        }
        if (isInputEmpty(arrayAnimals)) {
            throw new AnimalArrayEmptyException();
        }
        Double average = arrayAnimals.stream().mapToDouble(Animal::getCost)
                .summaryStatistics().getAverage();
        return arrayAnimals.stream()
                .filter(p -> p.getAge() > 5)
                .filter(p -> p.getCost() > average)
                .sorted((o1, o2) -> -o1.getBirthDate().compareTo(o2.getBirthDate()))
                .collect(Collectors.toList());
    }

    // HW-4 Новый метод
    public List<String> findMinConstAnimals(List<Animal> arrayAnimals) throws AnimalArrayNullException, AnimalArrayEmptyException {
        if (isInputNull(arrayAnimals)) {
            throw new AnimalArrayNullException();
        }
        if (isInputEmpty(arrayAnimals)) {
            throw new AnimalArrayEmptyException();
        }
        return arrayAnimals.stream()
                .sorted(Comparator.comparing(Animal::getCost))
                .limit(3)
                .sorted((o1, o2) -> -o1.getName().compareTo(o2.getName()))
                .map(Animal::getName)
                .collect(Collectors.toList());
    }


    private boolean isInputNull(List<Animal> arrayAnimals) {
        // HW-3-fix Переделал входной аргумент на лист
        // HW-3-fix Исправлено имя метода, добавлен private
        return arrayAnimals == null;
    }

    private boolean isInputEmpty(List<Animal> arrayAnimals) {
        // HW-3-fix Переделал входной аргумент на лист
        // HW-3-fix Исправлено имя метода, добавлен private
        // если в массиве нет элементов или хоть один из элементов null
        if (arrayAnimals.size() < 1) {
            return true;
        }
        for (Animal arrayAnimal : arrayAnimals) {
            if (arrayAnimal == null) {
                return true;
            }
        }
        return (false);
    }

    @PostConstruct
    void fillStoreAnimals() {
        CreateAnimalService createAnimalService = new CreateAnimalServiceImpl();
        createAnimalService.createAnimals(4);
    }

}
