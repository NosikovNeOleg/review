package Animals;

public class Pet extends AbstractAnimal {

    public Pet() {
        character = "Домашний";
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
}
