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

import java.time.LocalDate;
import java.util.Map;


class AnimalsRepositoryImplTest {

    AnimalsRepository animalsRepository = new AnimalsRepositoryImpl();
    Animal[] arrayAnimals = CreateAnimalsTestArray();
    Animal[] arrayAnimalsEmpty = new Animal[5];
    Map<String, LocalDate> animalsMap;
    Map<Animal, Integer> animalsOlderMap;
    Map<String, Integer> animalDuplicateMap;


    @Test
    @DisplayName("Поиск животных високосного года рождения")
    void findLeapYearNames() throws AnimalArrayNullException, AnimalArrayEmptyException {
        animalsMap = animalsRepository.findLeapYearNames(arrayAnimals);
        Assert.assertEquals(4, animalsMap.size());
    }

    @Test
    @DisplayName("Поиск животных старше определенного возраста")
    void findOlderAnimal() throws AnimalArrayNullException, AnimalArrayEmptyException {
        animalsOlderMap = animalsRepository.findOlderAnimal(arrayAnimals, 2);
        Assert.assertEquals(4, animalsOlderMap.size());
        animalsOlderMap = animalsRepository.findOlderAnimal(arrayAnimals, 22);
        Assert.assertEquals("[Кот{breed='Персидский', name='Persik', cost=6.1, " +
                        "character='Спокойный', birthDate=2012-03-23}]",
                animalsOlderMap.keySet().toString());
    }

    @Test
    @DisplayName("Поиск дубликатов животных")
    void findDuplicate() throws AnimalArrayNullException, AnimalArrayEmptyException {
        animalDuplicateMap = animalsRepository.findDuplicate(arrayAnimals);
        Assert.assertEquals("[Собака, Кот, Волк, Акула]", animalDuplicateMap.keySet().toString());
        Assert.assertEquals( 2 ,animalDuplicateMap.get("Кот").intValue());
        Assert.assertEquals( 1 ,animalDuplicateMap.get("Собака").intValue());
        Assert.assertEquals( 1 ,animalDuplicateMap.get("Акула").intValue());
        Assert.assertEquals( 1 ,animalDuplicateMap.get("Волк").intValue());
    }

    @Test
    @DisplayName("Проверка null-массива")
    void checkNullArrayException() {
        Exception exception = Assert.assertThrows(Exception.class,
                () -> animalsRepository.findLeapYearNames(null));
        Assert.assertEquals("Массив животных - NULL", exception.getMessage());
        exception = Assert.assertThrows(Exception.class,
                () -> animalsRepository.findOlderAnimal(null,2));
        Assert.assertEquals("Массив животных - NULL", exception.getMessage());
        exception = Assert.assertThrows(Exception.class,
                () -> animalsRepository.findDuplicate(null));
        Assert.assertEquals("Массив животных - NULL", exception.getMessage());
    }

    @Test
    @DisplayName("Проверка массива без элементов")
    void checkEmptyArrayException() {
        Exception exception = Assert.assertThrows(Exception.class,
                () -> animalsRepository.findLeapYearNames(arrayAnimalsEmpty));
        Assert.assertEquals("Массив животных не содежит элементов или содержит null-элементы",
                exception.getMessage());
        exception = Assert.assertThrows(Exception.class,
                () -> animalsRepository.findOlderAnimal(arrayAnimalsEmpty,2));
        Assert.assertEquals("Массив животных не содежит элементов или содержит null-элементы",
                exception.getMessage());
        exception = Assert.assertThrows(Exception.class,
                () -> animalsRepository.findDuplicate(arrayAnimalsEmpty));
        Assert.assertEquals("Массив животных не содежит элементов или содержит null-элементы",
                exception.getMessage());
    }



    private Animal[] CreateAnimalsTestArray() {
        Animal[] arrayAnimals = new Animal[5];
        arrayAnimals[0] = new Cat("Дворовый", "Barsik", 2.1d,
                "Спокойный", LocalDate.of(2020, 3, 23));
        arrayAnimals[1] = new Dog("Дворовый", "Bobik", 3.1d,
                "Спокойный", LocalDate.of(2016, 3, 24));
        arrayAnimals[2] = new Shark("Морской", "Ryba", 4.1d,
                "Спокойный", LocalDate.of(2021, 3, 23));
        arrayAnimals[3] = new Wolf("Лютоволк", "Volk", 5.1d,
                "Спокойный", LocalDate.of(2024, 3, 11));
        arrayAnimals[4] = new Cat("Персидский", "Persik", 6.1d,
                "Спокойный", LocalDate.of(2012, 3, 23));
        return arrayAnimals;
    }

}