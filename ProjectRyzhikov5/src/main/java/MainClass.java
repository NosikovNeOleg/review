import Animals.Cat;
import Animals.Dog;
import Animals.Shark;
import Exceptions.InvalidAnimalBirthDateException;
import Interfaces.SearchService;

import java.time.LocalDate;

public class MainClass {
    public static void main(String[] args) throws Exception {

        //Выводы по ДЗ-1
        //System.out.println("Hello");
        //CreateAnimalService createAnimalService = new CreateAnimalServiceImpl();
        //createAnimalService.createAnimals();
        //createAnimalService.createAnimals(8);
        //CreateAnimalService createAnimalServiceImplDefault = new CreateAnimalServiceImplDefault();
        //createAnimalServiceImplDefault.createAnimals(); // вызов дефолтного метода интерфейса

        SearchService searchService = new SearchServiceImpl();
        LocalDate localDate = LocalDate.of(2021, 3, 23);
        Cat cat = new Cat("Кот","Кот",1d,"Cat1", localDate);
        searchService.checkLeapYearAnimal(cat);

        localDate = LocalDate.of(2020, 12, 1);
        Shark shark = new Shark("Акул","Акул",3d,"Shark1", localDate);
        searchService.checkLeapYearAnimal(shark);

        try{
            Dog dog = new Dog("Собак","Собак",10d,"Dog", null);
            searchService.checkLeapYearAnimal(dog);
        } catch (Exception e){
            throw new InvalidAnimalBirthDateException("Работа метода завершилась ошибкой "+ e);
        }


        searchService.checkLeapYearAnimal(null);


    }

}

