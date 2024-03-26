import Interfaces.Animal;
import Interfaces.CreateAnimalService;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CreateAnimalServiceImplTest {

    CreateAnimalService createAnimalService = new CreateAnimalServiceImpl();
    List<String> animalTypesForCreation = createAnimalTypesList(); // HW-3-fix перечень животных, которые создаются в CreateAnimalServiceImpl

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
    void checkAnimalsMapParametrized(int animalsInput, int mapSize) {
        // HW-3-fix Тест проверяет количества животных всех типов, перечень типов задается отдельно
        Map<String, List<Animal>> mapAnimals = createAnimalService.createAnimalsMap(animalsInput);
        Assert.assertEquals(mapSize, mapAnimals.size());
        int curAnimalNum = 0;
        int curAnimalQuantity;
        int commonQuantity;
        int resultQuantity;
        for (String animalType : animalTypesForCreation) {
            commonQuantity = animalsInput / animalTypesForCreation.size();
            curAnimalQuantity = commonQuantity * animalTypesForCreation.size();
            resultQuantity = commonQuantity + (animalsInput - curAnimalQuantity - curAnimalNum > 0 ? 1 : 0);
            curAnimalNum++;
            Assert.assertEquals(resultQuantity,
                    (mapAnimals.get(animalType) == null ? 0 : mapAnimals.get(animalType).size()));
        }
    }


    private static Stream<Arguments> fetchData() {
        return Stream.of(
                Arguments.arguments(1, 1),
                Arguments.arguments(3, 3),
                Arguments.arguments(5, 4),
                Arguments.arguments(11, 4)
        );
    }

    private List<String> createAnimalTypesList() {
        List<String> animalTypes = new ArrayList<>();
        animalTypes.add("Cat");
        animalTypes.add("Dog");
        animalTypes.add("Shark");
        animalTypes.add("Wolf");
        return animalTypes;
    }
}