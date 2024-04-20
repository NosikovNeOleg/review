import Interfaces.Animal;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.time.LocalDate.now;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateAnimalServiceImplTest {

    //CreateAnimalService createAnimalService = new CreateAnimalServiceImpl();
    List<String> animalTypesForCreation = createAnimalTypesList(); // HW-3-fix перечень животных, которые создаются в CreateAnimalServiceImpl
    ResultReader resultReader = new ResultReader();
    Path path = Paths.get("src", "test", "resources", "animals", "logData.txt");

    @Mock
    private FilesConfig filesConfig;

    @InjectMocks
    private CreateAnimalServiceImpl createAnimalService;

    @Test
    @DisplayName("Проверка создания HashMap животных")
    void checkAnimalsMap() throws IOException {
        int animalsQuantity = 6;
        Files.deleteIfExists(path);
        Files.createFile(path);
        //FilesCongig filesCongig = mock(FilesCongig.class);
        when(filesConfig.GetPath(1)).thenReturn(path);

        // HW-6
        Map<String, List<Animal>> mapAnimals = createAnimalService.createAnimalsMap(animalsQuantity);
        Assert.assertEquals(4, mapAnimals.size());
        int rowsCount;
        try {
            List<String> lines = Files.readAllLines(path);
            rowsCount = lines.size(); // HW-6 fix
            // HW-6 fix Проверки записанных в файл животных на соответствие алгоритму создания
            Assert.assertTrue(lines.get(0).trim().contains("Кот w 0.1 " + now().toString()));
            Assert.assertTrue(lines.get(1).trim().contains("Собака w 1.1 " + now().toString()));
            Assert.assertTrue(lines.get(2).trim().contains("Акула w 2.1 " + now().toString()));
            Assert.assertTrue(lines.get(3).trim().contains("Волк w 3.1 " + now().toString()));
            Assert.assertTrue(lines.get(4).trim().contains("Кот w 4.1 " + now().toString()));
            Assert.assertTrue(lines.get(5).trim().contains("Собака w 5.1 " + now().toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(animalsQuantity, rowsCount); // HW-6
    }

    @ParameterizedTest
    @DisplayName("Параметризованный тест")
    @MethodSource("fetchData")
    void checkAnimalsMapParametrized(int animalsInput, int mapSize) throws IOException {
        // HW-3-fix Тест проверяет количества животных всех типов, перечень типов задается отдельно
        when(filesConfig.GetPath(1)).thenReturn(path);
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