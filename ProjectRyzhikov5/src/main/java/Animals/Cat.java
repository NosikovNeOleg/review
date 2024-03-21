package Animals;

import java.time.LocalDate;

public class Cat extends Pet {

    @Override
    public String getAnimalType() {
        return animalType;
    }

    private final String animalType;

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

    public Cat() {
        character = "Кошачий";
        animalType = "Кот";
    }

    public Cat(String breed, String name, Double cost, String character, LocalDate birthDate) {
        super(breed, name, cost, character, birthDate);
        animalType = "Кот";
    }
}
