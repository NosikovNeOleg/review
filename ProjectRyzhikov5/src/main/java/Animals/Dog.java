package Animals;

import java.time.LocalDate;

public class Dog extends Pet{
    @Override
    public String toString() {
        return "Dog{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                ", birthDate=" + dateTimeFormatter.format(birthDate) +
                '}';
    }

    public Dog() {
        character = "Собачий";
    }

    public Dog(String breed, String name, Double cost, String character, LocalDate birthDate) {
        super(breed, name, cost, character, birthDate);
    }
}
