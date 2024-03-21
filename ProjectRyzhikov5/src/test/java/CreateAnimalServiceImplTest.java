import Interfaces.Animal;
import Interfaces.CreateAnimalService;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CreateAnimalServiceImplTest {

    CreateAnimalService createAnimalService = new CreateAnimalServiceImpl();

    @Test
    @DisplayName("Проверка создания HashMap животных")
    void checkAnimalsMap() {
        int animalsQuantity = 6;
        Map<String, List<Animal>> mapAnimals = createAnimalService.createAnimalsMap(animalsQuantity);
        Assert.assertEquals(4, mapAnimals.size());
    }

    @ParameterizedTest
    @DisplayName("Параметризованный тест")
    @MethodSource("fetchData")
    void checkAnimalsMapParametrized(int animalsIntut, int mapSize, int catsQuantity) {
        Map<String, List<Animal>> mapAnimals = createAnimalService.createAnimalsMap(animalsIntut);
        Assert.assertEquals(mapSize, mapAnimals.size());
        Assert.assertEquals(catsQuantity, mapAnimals.get("Cat").size());
    }


    private static Stream<Arguments> fetchData() {
        return Stream.of(
                Arguments.arguments(1, 1, 1),
                Arguments.arguments(3, 3, 1),
                Arguments.arguments(5, 4, 2),
                Arguments.arguments(11, 4, 3)
        );
    }
}