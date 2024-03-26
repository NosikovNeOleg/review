import Exceptions.AnimalArrayEmptyException;
import Exceptions.AnimalArrayNullException;
import Interfaces.Animal;
import Interfaces.AnimalsRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        Map<String, LocalDate> animalsMap = new HashMap<>();
        for (Animal arrayAnimal : arrayAnimals) {
            if (arrayAnimal.getBirthDate().isLeapYear()) {
                animalsMap.put(arrayAnimal.getAnimalType() + " " + arrayAnimal.getName(),
                        arrayAnimal.getBirthDate());
            }
        }
        return animalsMap;
    }

    public Map<Animal, Integer> findOlderAnimal(List<Animal> arrayAnimals, int age) throws AnimalArrayEmptyException, AnimalArrayNullException {
        // HW-3-fix Переделал входной аргумент на лист
        if (isInputNull(arrayAnimals)) {
            throw new AnimalArrayNullException();
        }
        if (isInputEmpty(arrayAnimals)) {
            throw new AnimalArrayEmptyException();
        }

        Map<Animal, Integer> animalsMap = new HashMap<>();
        int animalAge;
        for (Animal arrayAnimal : arrayAnimals) {
            animalAge = now().minusYears(arrayAnimal.getBirthDate().getYear()).
                    minusMonths(arrayAnimal.getBirthDate().getMonthValue()).
                    minusDays(arrayAnimal.getBirthDate().getDayOfMonth()).getYear();
            if (animalAge >= age) {
                animalsMap.put(arrayAnimal, animalAge);
            }
        }
        if (animalsMap.size() == 0) {
            Animal oldestAnimal = arrayAnimals.get(0);
            LocalDate oldestDate = oldestAnimal.getBirthDate();

            for (int i = 1; i < arrayAnimals.size(); i++) {
                if (oldestDate.isAfter(arrayAnimals.get(i).getBirthDate())) {
                    oldestAnimal = arrayAnimals.get(i);
                    oldestDate = oldestAnimal.getBirthDate();
                }
            }
            animalAge = now().minusYears(oldestAnimal.getBirthDate().getYear()).
                    minusMonths(oldestAnimal.getBirthDate().getMonthValue()).
                    minusDays(oldestAnimal.getBirthDate().getDayOfMonth()).getYear();
            animalsMap.put(oldestAnimal, animalAge);
        }

        return animalsMap;
    }

    public Map<String, Integer> findDuplicate(List<Animal> arrayAnimals) throws AnimalArrayNullException, AnimalArrayEmptyException {
        // HW-3-fix Переделал входной аргумент на лист
        if (isInputNull(arrayAnimals)) {
            throw new AnimalArrayNullException();
        }
        if (isInputEmpty(arrayAnimals)) {
            throw new AnimalArrayEmptyException();
        }

        Map<String, Integer> animalsMap = new HashMap<>();
        for (Animal arrayAnimal : arrayAnimals) {
            animalsMap.put(arrayAnimal.getAnimalType(), animalsMap.getOrDefault(
                    arrayAnimal.getAnimalType(),
                    0) + 1); // HW-3-fix Использование getOrDefault
        }

        String currentAnimalType;
        for (Animal arrayAnimal : arrayAnimals) {
            currentAnimalType = arrayAnimal.getAnimalType();
            if (animalsMap.get(currentAnimalType) > 1) {
                System.out.println("Дубликат: " + arrayAnimal);
            }
        }

        return animalsMap;
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
