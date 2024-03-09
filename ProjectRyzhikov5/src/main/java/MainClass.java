public class MainClass {
    public static void main(String[] args) {

        //System.out.println("Hello");
        CreateAnimalServiceImpl createAnimalServiceImpl = new CreateAnimalServiceImpl();
        createAnimalServiceImpl.createAnimalsInitial();
        createAnimalServiceImpl.createAnimals();
        createAnimalServiceImpl.createAnimals(8);
    }

}

