package Animals;

import Interfaces.Animal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.LocalDate.now;

public abstract class AbstractAnimal implements Animal {

    protected String breed; // порода
    protected String name; // имя
    protected Double cost; // цена в магазине
    protected String character; // характер
    protected LocalDate birthDate;

    protected DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public AbstractAnimal() {
    }

    public AbstractAnimal(String breed, String name, Double cost, String character, LocalDate birthDate) {
        this.breed = breed;
        this.name = name;
        this.cost = cost;
        this.character = character;
        this.birthDate = birthDate;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String getBreed() {
        return breed;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Double getCost() {
        return cost;
    }

    @Override
    public String getCharacter() {
        return character;
    }

    @Override
    public LocalDate getBirthDate() {
        return birthDate;
    }

    // HW-4
    public Integer getAge() {
        return now().minusYears(this.getBirthDate().getYear()).
                minusMonths(this.getBirthDate().getMonthValue()).
                minusDays(this.getBirthDate().getDayOfMonth()).getYear();
    }

    @Override
    public String toString() {
        return "AbstractAnimal{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                ", birthDate=" + dateTimeFormatter.format(birthDate) +
                '}';
    }
}
