package Animals;

import java.time.LocalDate;

public class Predator extends AbstractAnimal{

    private final String animalType;

    public Predator() {
        character = "Хищный";
        animalType = "Хищник";
    }

    public Predator(String breed, String name, Double cost, String character, LocalDate birthDate) {
        super(breed, name, cost, character, birthDate);
        animalType = "Хищник";
    }

    public String getBreed() {
        return breed;
    }

    public String getName() {
        return name;
    }

    public String getAnimalType() {
        return animalType;
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
}
