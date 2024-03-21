package Interfaces;

import Exceptions.AnimalArrayEmptyException;
import Exceptions.AnimalArrayNullException;

import java.time.LocalDate;
import java.util.Map;

public interface AnimalsRepository {
    Map<String, LocalDate> findLeapYearNames(Animal[] arrayAnimals) throws AnimalArrayNullException, AnimalArrayEmptyException;

    Map<Animal, Integer> findOlderAnimal(Animal[] arrayAnimals, int age) throws AnimalArrayEmptyException, AnimalArrayNullException;

    Map<String, Integer> findDuplicate(Animal[] arrayAnimals) throws AnimalArrayNullException, AnimalArrayEmptyException;
}
