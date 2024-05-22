package Animals;


import java.time.LocalDate;

public class Cat extends Pet {

    @Override
    public String getAnimalType() {
        return animalType;
    }

    private final String animalType;

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
        return "Cat{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                ", birthDate=" + dateTimeFormatter.format(birthDate) +
                ", character='" + getAnimalType() + '\'' +
                ", secretInformation='" + secretInformation + '\'' +
                '}';
    }

    public Cat() {
        character = "Кошачий";
        animalType = "Кот";
        birthDate = LocalDate.of(2020, 3, 23);
    }

    public Cat(String catName) {
        character = "Кошачий";
        animalType = "Кот";
        name = catName;
        birthDate = LocalDate.of(2020, 3, 23);
    }

    public Cat(String breed, String name, Double cost, String character, LocalDate birthDate) {
        super(breed, name, cost, character, birthDate);
        animalType = "Кот";
    }

}
