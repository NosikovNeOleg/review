package Animals;

import java.time.LocalDate;

public class Shark extends Predator{

    private final String animalType;

    @Override
    public String getAnimalType() {
        return animalType;
    }

    public Shark() {
        character = "Акулий";
        animalType = "Акула";
    }

    public Shark(String breed, String name, Double cost, String character, LocalDate birthDate) {
        super(breed, name, cost, character, birthDate);
        animalType = "Акула";
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
}
