import Animals.*;
import Interfaces.SearchService;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static java.time.LocalDate.now;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;


class SearchServiceImplTest {

    SearchService searchService = new SearchServiceImpl();
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @Test
    @DisplayName("Проверка невисокосного года")
    void checkLeapYearAnimalNotLeap() throws Exception {
        LocalDate localDate = LocalDate.of(2021, 3, 23);
        Cat cat = new Cat("Кот", "Кот", 1d, "Cat1", localDate);
        searchService.checkLeapYearAnimal(cat);
        Assert.assertEquals(cat.getName() + " не был рожден в високосный год", output.toString().trim());
    }

    @Test
    @DisplayName("Проверка високосного года")
    void checkLeapYearAnimalLeap() throws Exception {
        LocalDate localDate = LocalDate.of(2020, 12, 1);
        Shark shark = new Shark("Акул", "Акул", 3d, "Shark1", localDate);
        searchService.checkLeapYearAnimal(shark);
        Assert.assertEquals(shark.getName() + " был рожден в високосный год", output.toString().trim());
    }

    @AfterEach
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    @DisplayName("Проверка Exception")
    void checkLeapYearAnimalException(){
        Dog dog = new Dog("Собак","Собак",10d,"Dog", null);
        Exception exception = Assert.assertThrows(Exception.class,
                () -> searchService.checkLeapYearAnimal(dog));
        Assert.assertEquals("У животного " + dog.getClass().toString() + " не указана дата его рождения", exception.getMessage());
    }

    @Test
    @DisplayName("Проверка Error")
    void checkLeapYearAnimalError(){
        Error error = Assert.assertThrows(Error.class,
                () -> searchService.checkLeapYearAnimal(null));
        Assert.assertEquals("На вход пришел некорректный объект животного, "+now(), error.getMessage());
    }

    @ParameterizedTest
    @DisplayName("Параметризованный тест")
    @MethodSource("fetchData")
    void checkLeapYearAnimalParametrized (AbstractAnimal abstractAnimal, String expectedResult) throws Exception {
        searchService.checkLeapYearAnimal(abstractAnimal);
        Assert.assertEquals(expectedResult, output.toString().trim());

    }

    private static Stream<Arguments> fetchData(){
        return Stream.of (
                Arguments.arguments(new Cat ("Кот", "Кот", 1d, "Cat1",
                        LocalDate.of(2020, 3, 23)),
                        "Кот был рожден в високосный год"),
                Arguments.arguments( new Shark("Акул", "Акул", 3d, "Shark1",
                        LocalDate.of(1920, 5, 1)),
                        "Акул был рожден в високосный год"),
                Arguments.arguments( new Wolf("Волк", "Волк", 5d, "Волк",
                        LocalDate.of(1900, 11, 11)),
                        "Волк не был рожден в високосный год"),
                Arguments.arguments( new Dog("Собак", "Собак", 7d, "Собак",
                        LocalDate.of(2022, 2, 5)),
                        "Собак не был рожден в високосный год")
        );
    }

}
