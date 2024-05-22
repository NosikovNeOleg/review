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
        birthDate = LocalDate.of(2020, 3, 23);
    }

    public Shark(String sharkName) {
        character = "Акулий";
        animalType = "Акула";
        name = sharkName;
        birthDate = LocalDate.of(2020, 3, 23);
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

    @Override
    public String toStringWithSecretInformation() {
        return "Shark{" +
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
