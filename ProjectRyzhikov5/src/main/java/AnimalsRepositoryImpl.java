import Exceptions.AnimalArrayEmptyException;
import Exceptions.AnimalArrayNullException;
import Interfaces.Animal;
import Interfaces.AnimalsRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static java.time.LocalDate.now;

public class AnimalsRepositoryImpl implements AnimalsRepository {

    public Map<String, LocalDate> findLeapYearNames(Animal[] arrayAnimals) throws AnimalArrayNullException, AnimalArrayEmptyException {
        if(IsInputNull(arrayAnimals)) {
            throw new AnimalArrayNullException();
        }
        if(IsInputEmpty(arrayAnimals)) {
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

    public Map<Animal, Integer> findOlderAnimal(Animal[] arrayAnimals, int age) throws AnimalArrayEmptyException, AnimalArrayNullException {
        if(IsInputNull(arrayAnimals)) {
            throw new AnimalArrayNullException();
        }
        if(IsInputEmpty(arrayAnimals)) {
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
            Animal oldestAnimal = arrayAnimals[0];
            LocalDate oldestDate = oldestAnimal.getBirthDate();

            for (int i = 1; i < arrayAnimals.length; i++) {
                if (oldestDate.isAfter(arrayAnimals[i].getBirthDate())) {
                    oldestAnimal = arrayAnimals[i];
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

    public Map<String, Integer> findDuplicate(Animal[] arrayAnimals) throws AnimalArrayNullException, AnimalArrayEmptyException {
        if(IsInputNull(arrayAnimals)) {
            throw new AnimalArrayNullException();
        }
        if(IsInputEmpty(arrayAnimals)) {
            throw new AnimalArrayEmptyException();
        }

        Map<String, Integer> animalsMap = new HashMap<>();
        Integer currentDuplicates;
        for (Animal arrayAnimal : arrayAnimals) {
            currentDuplicates = animalsMap.get(arrayAnimal.getAnimalType());
            if (currentDuplicates == null) {
                animalsMap.put(arrayAnimal.getAnimalType(), 1);
            } else {
                currentDuplicates = animalsMap.get(arrayAnimal.getAnimalType()) + 1;
                animalsMap.put(arrayAnimal.getAnimalType(), currentDuplicates);
            }
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

    boolean IsInputNull(Animal[] arrayAnimals){
        return arrayAnimals == null;
    }

    boolean IsInputEmpty(Animal[] arrayAnimals){
        // если в массиве нет элементов или хоть один из элементов null
        if (arrayAnimals.length < 1) {return true;}
        for (Animal arrayAnimal : arrayAnimals) {
            if (arrayAnimal == null) {
                return true;
            }
        }
        return (false);
    }

}
