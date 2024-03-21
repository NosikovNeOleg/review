package Exceptions;

public class AnimalArrayEmptyException extends Exception{
    public AnimalArrayEmptyException() {
        super("Массив животных не содежит элементов или содержит null-элементы");
    }
}
