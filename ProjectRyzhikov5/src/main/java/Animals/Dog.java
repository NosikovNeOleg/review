package Animals;

import java.time.LocalDate;

public class Dog extends Pet{

    private final String animalType;

    @Override
    public String getAnimalType() {
        return animalType;
    }

    @Override
    public String toString() {
        return animalType + "{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                ", birthDate=" + dateTimeFormatter.format(birthDate) +
                '}';
    }

    @Override
    public String toStringWithSecretInformation() {
        return "Dog{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                ", birthDate=" + dateTimeFormatter.format(birthDate) +
                ", character='" + getAnimalType() + '\'' +
                ", secretInformation='" + secretInformation + '\'' +
                '}';
    }

    public Dog() {
        character = "Собачий";
        animalType = "Собака";
        birthDate = LocalDate.of(2020, 3, 23);
    }

    public Dog(String dogName) {
        character = "Собачий";
        animalType = "Собака";
        name = dogName;
        birthDate = LocalDate.of(2020, 3, 23);
    }

    public Dog(String breed, String name, Double cost, String character, LocalDate birthDate) {
        super(breed, name, cost, character, birthDate);
        animalType = "Собака";
    }
}
