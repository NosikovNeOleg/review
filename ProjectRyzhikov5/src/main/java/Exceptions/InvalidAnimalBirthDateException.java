package Exceptions;


public class InvalidAnimalBirthDateException extends Exception {
    public InvalidAnimalBirthDateException(String animalType) throws Exception {
        throw new Exception("У животного " + animalType + " не указана дата его рождения");
    }
}
