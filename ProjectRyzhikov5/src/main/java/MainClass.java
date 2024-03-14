import Interfaces.CreateAnimalService;

public class MainClass {
    public static void main(String[] args) {

        //System.out.println("Hello");
        CreateAnimalService createAnimalService = new CreateAnimalServiceImpl();
        createAnimalService.createAnimals();
        createAnimalService.createAnimals(8);
        CreateAnimalService createAnimalServiceImplDefault = new CreateAnimalServiceImplDefault();
        createAnimalServiceImplDefault.createAnimals(); // вызов дефолтного метода интерфейса
    }

}

