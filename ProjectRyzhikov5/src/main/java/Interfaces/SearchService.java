package Interfaces;

import Animals.AbstractAnimal;
import Exceptions.InvalidAnimalBirthDateException;
import Exceptions.InvalidAnimalException;

public interface SearchService {
    void checkLeapYearAnimal(AbstractAnimal abstractAnimal) throws InvalidAnimalException, InvalidAnimalBirthDateException;
}
