package Animals;

import java.time.LocalDate;

public class Wolf extends Predator{


    public Wolf() {
        character = "Волчара";
    }

    public Wolf(String breed, String name, Double cost, String character, LocalDate birthDate) {
        super(breed, name, cost, character, birthDate);
    }

    @Override
    public String toString() {
        return "Wolf{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                ", birthDate=" + dateTimeFormatter.format(birthDate) +
                '}';
    }
}
