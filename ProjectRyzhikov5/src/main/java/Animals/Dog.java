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

    public Dog() {
        character = "Собачий";
        animalType = "Собака";
    }

    public Dog(String breed, String name, Double cost, String character, LocalDate birthDate) {
        super(breed, name, cost, character, birthDate);
        animalType = "Собака";
    }
}
