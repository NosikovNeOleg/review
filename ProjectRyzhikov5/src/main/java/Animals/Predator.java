package Animals;

import java.time.LocalDate;

public class Predator extends AbstractAnimal{

    public Predator() {
        character = "Хищный";
    }

    public Predator(String breed, String name, Double cost, String character, LocalDate birthDate) {
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
}
