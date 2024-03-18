package Exceptions;

import static java.time.LocalDate.now;

public class InvalidAnimalException extends Error{

    public InvalidAnimalException() {
        throw new Error("На вход пришел некорректный объект животного, "+now());
    }
}
