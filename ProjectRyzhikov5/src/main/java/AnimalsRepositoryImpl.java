import Exceptions.AnimalArrayEmptyException;
import Exceptions.AnimalArrayNullException;
import Interfaces.Animal;
import Interfaces.AnimalsRepository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.time.LocalDate.now;

public class AnimalsRepositoryImpl implements AnimalsRepository {

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

}
