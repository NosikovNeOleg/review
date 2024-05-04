package Interfaces;

import java.time.LocalDate;

public interface Animal {
    String getBreed();

    String getName();

    Double getCost();

    String getCharacter();

    LocalDate getBirthDate();

    String getAnimalType();

    // HW-4
    Integer getAge();

    // HW-6
    String getSecretInformation();

    void setSecretInformation(String secretInformation);

    String toStringWithSecretInformation();
}
