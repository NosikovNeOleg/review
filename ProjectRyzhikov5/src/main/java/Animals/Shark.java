package Animals;

import java.time.LocalDate;

public class Shark extends Predator{
    public Shark() {
        character = "Акулий";
    }

    public Shark(String breed, String name, Double cost, String character, LocalDate birthDate) {
        super(breed, name, cost, character, birthDate);
    }

    @Override
    public String toString() {
        return "Shark{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                ", birthDate=" + dateTimeFormatter.format(birthDate) +
                '}';
    }
}
