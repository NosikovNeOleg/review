package Animals;

import Interfaces.Animal;
import Interfaces.AnimalsRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import static java.time.LocalDate.now;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "animalType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Cat.class, name = "Кот"),
        @JsonSubTypes.Type(value = Dog.class, name = "Собака"),
        @JsonSubTypes.Type(value = Shark.class, name = "Акула"),
        @JsonSubTypes.Type(value = Wolf.class, name = "Волк"),
        @JsonSubTypes.Type(value = Pet.class, name = "Домашний"),
        @JsonSubTypes.Type(value = Predator.class, name = "Хищник")})
public abstract class AbstractAnimal implements Animal, Serializable {

    protected String animalType;
    protected String breed; // порода
    protected String name; // имя
    protected Double cost; // цена в магазине
    protected String character; // характер
    // HW-6 Аннотации
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    protected LocalDate birthDate;
    protected String secretInformation; // HW-6

    protected DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public AbstractAnimal() {
    }

    public AbstractAnimal(String breed, String name, Double cost, String character, LocalDate birthDate) {
        this.breed = breed;
        this.name = name;
        this.cost = cost;
        this.character = character;
        this.birthDate = birthDate;
        // HW-6
        this.secretInformation = calculateSecretInformation();
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

    // HW-6
    public void setSecretInformation(String secretInformation) {
        this.secretInformation = secretInformation;
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

    public String getSecretInformation() {
        return this.secretInformation;
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

    public String toStringWithSecretInformation() {
        return "AbstractAnimal{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                ", birthDate=" + dateTimeFormatter.format(birthDate) +
                ", character='" + getAnimalType() + '\'' +
                ", secretInformation='" + secretInformation + '\'' +
                '}';
    }

    // HW-6 Метод выбора случайной строки из файла secretInformation.txt
    private String calculateSecretInformation() {
        String secretString;

        Path path = Paths.get("src", "main", "resources", "secretStore", "secretInformation.txt");
        int linesCount = 0;
        try {
            List<String> lines = Files.readAllLines(path);
            for (String ignored : lines) {
                linesCount++;
            }
            Random rn = new Random();
            int randomNum = rn.nextInt(linesCount);

            secretString = lines.get(randomNum); // HW-6 fix

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return secretString;
    }
}
