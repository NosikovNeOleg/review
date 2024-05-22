package Animals;

import java.time.LocalDate;

public class Wolf extends Predator{

    private final String animalType;

    public Wolf() {
        character = "Волчара";
        animalType = "Волк";
        birthDate = LocalDate.of(2020, 3, 23);
    }

    public Wolf(String wolfName) {
        character = "Волчара";
        animalType = "Волк";
        name = wolfName;
        birthDate = LocalDate.of(2020, 3, 23);
    }

    @Override
    public String getAnimalType() {
        return animalType;
    }

    public Wolf(String breed, String name, Double cost, String character, LocalDate birthDate) {
        super(breed, name, cost, character, birthDate);
        animalType = "Волк";
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
        return "Wolf{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                ", birthDate=" + dateTimeFormatter.format(birthDate) +
                ", character='" + getAnimalType() + '\'' +
                ", secretInformation='" + secretInformation + '\'' +
                '}';
    }
}
