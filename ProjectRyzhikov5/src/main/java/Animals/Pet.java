package Animals;

import java.time.LocalDate;

public class Pet extends AbstractAnimal {

    public Pet() {
        character = "Домашний";
    }

    public Pet(String breed, String name, Double cost, String character, LocalDate birthDate) {
        super(breed, name, cost, character, birthDate);
    }

    public String getBreed() {
        return breed;
    }

    public String getName() {
        return name;
    }

    public Double getCost() {
        return cost;
    }

    public String getCharacter() {
        return character;
    }

    public LocalDate getbirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                ", birthDate=" + dateTimeFormatter.format(birthDate) +
                '}';
    }
}
