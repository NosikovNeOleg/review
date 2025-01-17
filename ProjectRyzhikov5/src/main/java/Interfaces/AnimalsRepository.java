package Interfaces;

import Exceptions.AnimalArrayEmptyException;
import Exceptions.AnimalArrayNullException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AnimalsRepository {
    // // HW-3-fix Переделал входной аргумент на лист во всех методах
    Map<String, LocalDate> findLeapYearNames(List<Animal> arrayAnimals) throws AnimalArrayNullException, AnimalArrayEmptyException;

    Map<Animal, Integer> findOlderAnimal(List<Animal> arrayAnimals, int age) throws AnimalArrayEmptyException, AnimalArrayNullException;

    Map<String, List<Animal>> findDuplicate(List<Animal> arrayAnimals) throws AnimalArrayNullException, AnimalArrayEmptyException;

    // HW-4 Новый метод
    void findAverageAge (List<Animal> arrayAnimals) throws AnimalArrayNullException, AnimalArrayEmptyException;

    // HW-4 Новый метод
    List<Animal> findOldAndExpensive (List<Animal> arrayAnimals) throws AnimalArrayNullException, AnimalArrayEmptyException;

    // HW-4 Новый метод
    List<String> findMinConstAnimals (List<Animal> arrayAnimals) throws AnimalArrayNullException, AnimalArrayEmptyException;

}
