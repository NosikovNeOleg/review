import Exceptions.AnimalArrayEmptyException;
import Exceptions.AnimalArrayNullException;
import Interfaces.Animal;
import Interfaces.AnimalsRepository;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import Animals.Cat;
import Animals.Dog;
import Animals.Shark;
import Animals.Wolf;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.time.LocalDate.now;


class AnimalsRepositoryImplTest {

    // HW-3-fix Переделал с массива на лист
    AnimalsRepository animalsRepository = new AnimalsRepositoryImpl();
    List<Animal> listAnimals = CreateAnimalsTestArray();
    List<Animal> listAnimalsEmpty = new ArrayList<>();
    Map<String, LocalDate> animalsMap;
    Map<Animal, Integer> animalsOlderMap;
    Map<String, List<Animal>> animalDuplicateMap; // HW-4 исправлен тип данных
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    List<Animal> oldAndExpensive = new ArrayList<>();
    List<String> minConstAnimals = new ArrayList<>();


    @Test
    @DisplayName("Поиск животных високосного года рождения")
    void findLeapYearNames() throws AnimalArrayNullException, AnimalArrayEmptyException {
        animalsMap = animalsRepository.findLeapYearNames(listAnimals);
        Assert.assertEquals(4, animalsMap.size());
    }

    @Test
    @DisplayName("Поиск животных старше определенного возраста")
    void findOlderAnimal() throws AnimalArrayNullException, AnimalArrayEmptyException {
        animalsOlderMap = animalsRepository.findOlderAnimal(listAnimals, 2);
        Assert.assertEquals(4, animalsOlderMap.size());
        animalsOlderMap = animalsRepository.findOlderAnimal(listAnimals, 22);
        // HW-3-fix Переделал на проверку полученного возраста
        Assert.assertTrue(animalsOlderMap.containsValue(now().
                minusYears(listAnimals.get(4).getBirthDate().getYear()).
                minusMonths(listAnimals.get(4).getBirthDate().getMonthValue()).
                minusDays(listAnimals.get(4).getBirthDate().getDayOfMonth()).getYear()));
    }

    @Test
    @DisplayName("Поиск дубликатов животных")
    void findDuplicate() throws AnimalArrayNullException, AnimalArrayEmptyException {
        animalDuplicateMap = animalsRepository.findDuplicate(listAnimals);
        // HW-4 Исправлено в соответствии с новым типом данных
        Assert.assertTrue(animalDuplicateMap.keySet().toString().contains("Собака"));
        Assert.assertTrue(animalDuplicateMap.keySet().toString().contains("Кот"));
        Assert.assertTrue(animalDuplicateMap.keySet().toString().contains("Волк"));
        Assert.assertTrue(animalDuplicateMap.keySet().toString().contains("Акула"));
        Assert.assertEquals(2, animalDuplicateMap.get("Кот").size());
        Assert.assertEquals(1, animalDuplicateMap.get("Собака").size());
        Assert.assertEquals(1, animalDuplicateMap.get("Акула").size());
        Assert.assertEquals(1, animalDuplicateMap.get("Волк").size());
    }

    @Test
    @DisplayName("Проверка null-массива")
    void checkNullArrayException() {
        Exception exception = Assert.assertThrows(Exception.class,
                () -> animalsRepository.findLeapYearNames(null));
        Assert.assertEquals("Массив животных - NULL", exception.getMessage());
        exception = Assert.assertThrows(Exception.class,
                () -> animalsRepository.findOlderAnimal(null, 2));
        Assert.assertEquals("Массив животных - NULL", exception.getMessage());
        exception = Assert.assertThrows(Exception.class,
                () -> animalsRepository.findDuplicate(null));
        Assert.assertEquals("Массив животных - NULL", exception.getMessage());
    }

    @Test
    @DisplayName("Проверка массива без элементов")
    void checkEmptyArrayException() {
        Exception exception = Assert.assertThrows(Exception.class,
                () -> animalsRepository.findLeapYearNames(listAnimalsEmpty));
        Assert.assertEquals("Массив животных не содежит элементов или содержит null-элементы",
                exception.getMessage());
        exception = Assert.assertThrows(Exception.class,
                () -> animalsRepository.findOlderAnimal(listAnimalsEmpty, 2));
        Assert.assertEquals("Массив животных не содежит элементов или содержит null-элементы",
                exception.getMessage());
        exception = Assert.assertThrows(Exception.class,
                () -> animalsRepository.findDuplicate(listAnimalsEmpty));
        Assert.assertEquals("Массив животных не содежит элементов или содержит null-элементы",
                exception.getMessage());
    }

    //HW-4
    @Test
    @DisplayName("Вывод на экран среднего возраста")
    void findAverageAge() throws AnimalArrayNullException, AnimalArrayEmptyException {
        System.setOut(new PrintStream(output));
        animalsRepository.findAverageAge(listAnimals);
        Assert.assertEquals("Средний возраст: 4.4", output.toString().trim());
        System.setOut(null);
    }

    //HW-4
    @Test
    @DisplayName("Поиск возрастных дорогих животных")
    void findOldAndExpensive() throws AnimalArrayNullException, AnimalArrayEmptyException {
        oldAndExpensive = animalsRepository.findOldAndExpensive(listAnimals);
        Assert.assertEquals(2, oldAndExpensive.size());
        Assert.assertEquals("Bobik", oldAndExpensive.get(0).getName());
        Assert.assertEquals("Persik", oldAndExpensive.get(1).getName());
    }

    //HW-4
    @Test
    @DisplayName("Поиск животных наименьшей стоимости")
    void findMinConstAnimals() throws AnimalArrayNullException, AnimalArrayEmptyException {
        minConstAnimals = animalsRepository.findMinConstAnimals(listAnimals);
        Assert.assertEquals("Volk",minConstAnimals.get(0));
        Assert.assertEquals("Ryba",minConstAnimals.get(1));
        Assert.assertEquals("Barsik",minConstAnimals.get(2));
    }


    private List<Animal> CreateAnimalsTestArray() {
        List<Animal> listAnimals = new ArrayList<>();
        listAnimals.add(new Cat("Дворовый", "Barsik", 2.1d,
                "Спокойный", LocalDate.of(2020, 3, 23)));
        listAnimals.add(new Dog("Дворовый", "Bobik", 6.1d,
                "Спокойный", LocalDate.of(2016, 3, 24)));
        listAnimals.add(new Shark("Морской", "Ryba", 4.1d,
                "Спокойный", LocalDate.of(2021, 3, 23)));
        listAnimals.add(new Wolf("Лютоволк", "Volk", 5.1d,
                "Спокойный", LocalDate.of(2024, 3, 11)));
        listAnimals.add(new Cat("Персидский", "Persik", 6.1d,
                "Спокойный", LocalDate.of(2012, 3, 23)));
        return listAnimals;
    }

}