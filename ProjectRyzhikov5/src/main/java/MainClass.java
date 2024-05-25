import Animals.Cat;
import Animals.Dog;
import Animals.Shark;
import Animals.Wolf;
import Interfaces.Animal;
import Interfaces.AnimalsRepository;
import Interfaces.CreateAnimalService;
import Interfaces.Multithreading;
import com.rdm.rdm.implementations.AnimalsRepositoryImpl;
import com.rdm.rdm.implementations.CreateAnimalServiceImpl;
import com.rdm.rdm.implementations.MultithreadingImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.time.LocalDate.now;

public class MainClass {
    public static void main(String[] args) throws Exception {

        //Выводы по ДЗ-1
        //System.out.println("Hello");
        //CreateAnimalService createAnimalService = new CreateAnimalServiceImpl();
        //createAnimalService.createAnimals();
        //createAnimalService.createAnimals(8);
        //CreateAnimalService createAnimalServiceImplDefault = new CreateAnimalServiceImplDefault();
        //createAnimalServiceImplDefault.createAnimals(); // вызов дефолтного метода интерфейса

        //Выводы по ДЗ-2
        /*SearchService searchService = new SearchServiceImpl();
        LocalDate localDate = LocalDate.of(2021, 3, 23);
        Cat cat = new Cat("Кот","Кот",1d,"Cat1", localDate);
        searchService.checkLeapYearAnimal(cat);

        localDate = LocalDate.of(2020, 12, 1);
        Shark shark = new Shark("Акул","Акул",3d,"Shark1", localDate);
        searchService.checkLeapYearAnimal(shark);

        Dog dog = new Dog("Собак","Собак",10d,"Dog", null);
        try{
            searchService.checkLeapYearAnimal(dog);
        } catch (Exception e){
           throw new Exception("Работа метода завершилась ошибкой "+ e);
        }

        searchService.checkLeapYearAnimal(null);*/

        CreateAnimalService createAnimalService = new CreateAnimalServiceImpl();
        Map<String, List<Animal>> mapAnimals = createAnimalService.createAnimalsMap(5);
        System.out.println(mapAnimals.keySet().toString());
        System.out.println(mapAnimals.get("Cat").toString());
        System.out.println(mapAnimals.get("Dog").toString());

        AnimalsRepository animalsRepository = new AnimalsRepositoryImpl();

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

        Map<String, LocalDate> animalsMap;
        animalsMap = animalsRepository.findLeapYearNames(listAnimals);
        System.out.println(animalsMap.keySet());

        Map<Animal, Integer> animalsOlderMap;
        animalsOlderMap = animalsRepository.findOlderAnimal(listAnimals, 2);
        System.out.println(animalsOlderMap.keySet());
        /*animalsOlderMap = animalsRepository.findOlderAnimal(listAnimals, 22);
        System.out.println(animalsOlderMap.keySet());*/

        Map<String, List<Animal>> animalDuplicateMap;
        animalDuplicateMap = animalsRepository.findDuplicate(listAnimals);
        System.out.println(animalDuplicateMap.keySet() + " " + animalDuplicateMap.get("Кот")
                + " " + animalDuplicateMap.get("Собака") + " " + animalDuplicateMap.get("Акула")
                + " " + animalDuplicateMap.get("Волк"));

        //animalsRepository.findLeapYearNames(null);
        //animalsRepository.findOlderAnimal(null,2);
        //animalsRepository.findDuplicate(null);

        //HW-4
//        animalsRepository.findAverageAge(listAnimals);
//        System.out.println(animalsRepository.findOldAndExpensive(listAnimals).get(0));
//        System.out.println(animalsRepository.findOldAndExpensive(listAnimals).get(1));
//        System.out.println(animalsRepository.findMinConstAnimals(listAnimals));

        // HW-5
//        Multithreading multithreading = new MultithreadingImpl();
//        multithreading.ParallelGenerationRandoms(4, 100000);
//        multithreading.ThreadSafelyCounter(4, 10);
//        multithreading.ParallelFactorialCalculation(4,20);

        // HW-6
        //ResultReader resultReader = new ResultReader();
        //resultReader.PrintAnimalJson();
    }

}

