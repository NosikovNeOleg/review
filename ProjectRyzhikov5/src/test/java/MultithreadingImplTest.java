import Interfaces.Multithreading;
import com.rdm.rdm.implementations.MultithreadingImpl;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

public class MultithreadingImplTest {
    Multithreading multithreading = new MultithreadingImpl();
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @ParameterizedTest
    @DisplayName("Многопоточная генерация случайных чисел")
    @MethodSource("fetchDataRandoms")
    void ParallelGenerationRandoms(int threadsQuantity, int numbersQuantity) throws ExecutionException, InterruptedException {
        multithreading.ParallelGenerationRandoms(threadsQuantity, numbersQuantity);
        for (int i = 1; i < threadsQuantity + 1; i++) {
            Assert.assertTrue(output.toString().trim().contains("Номер потока: " + i + " Затраченное время:"));
        }
    }

    @ParameterizedTest
    @DisplayName("Многоаоточный счетчик")
    @MethodSource("fetchDataCounter")
    void ThreadSafelyCounter(int threadsQuantity, int numbersQuantity) throws InterruptedException {
        multithreading.ThreadSafelyCounter(threadsQuantity, numbersQuantity);
        numbersQuantity *= threadsQuantity;
        Assert.assertEquals("Atomic Integer " + numbersQuantity, output.toString().trim());
    }

    @ParameterizedTest
    @DisplayName("Многопоточное высисление факториала")
    @MethodSource("fetchDataFactorial")
    void ParallelFactorialCalculation(int threadsQuantity, int numberForFactorial, String result) throws ExecutionException, InterruptedException {
        multithreading.ParallelFactorialCalculation(threadsQuantity, numberForFactorial);
        Assert.assertEquals(result, output.toString().trim());
    }

    @AfterEach
    public void cleanUpStreams() {
        System.setOut(null);
    }


    private static Stream<Arguments> fetchDataRandoms() {
        return Stream.of(
                Arguments.arguments(2, 1000),
                Arguments.arguments(3, 5000),
                Arguments.arguments(4, 10000),
                Arguments.arguments(5, 10000),
                Arguments.arguments(4, 50000)
        );
    }

    private static Stream<Arguments> fetchDataCounter() {
        return Stream.of(
                Arguments.arguments(2, 20),
                Arguments.arguments(3, 20),
                Arguments.arguments(4, 20),
                Arguments.arguments(3, 100),
                Arguments.arguments(4, 205),
                Arguments.arguments(3, 500),
                Arguments.arguments(3, 1000)
        );
    }

    private static Stream<Arguments> fetchDataFactorial() {
        return Stream.of(
                Arguments.arguments(2, 2, "Факториал 2 равен: 2"),
                Arguments.arguments(4, 3, "Факториал 3 равен: 6"),
                Arguments.arguments(3, 7, "Факториал 7 равен: 5040"),
                Arguments.arguments(4, 10, "Факториал 10 равен: 3628800"),
                Arguments.arguments(3, 16, "Факториал 16 равен: 20922789888000"),
                Arguments.arguments(3, 20, "Факториал 20 равен: 2432902008176640000"),
                Arguments.arguments(2, 21, "Факториал числа 21 выходит за диапазон значений Long. Факториал 20 равен: 2432902008176640000"),
                Arguments.arguments(4, 27, "Факториал числа 27 выходит за диапазон значений Long. Факториал 20 равен: 2432902008176640000")
        );
    }

}
