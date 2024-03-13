import Animals.*;
import Interfaces.CreateAnimalService;

public class CreateAnimalServiceImpl implements CreateAnimalService {

    @Override
    public void createAnimals() {
        int i = 0;
        int animalChooser;
        do {
            System.out.print("     Create Animal №" + (i+1) + ": ");
            animalChooser = i % 4;
            switch (animalChooser) {
                case 0 -> System.out.println(new Cat());
                case 1 -> System.out.println(new Dog());
                case 2 -> System.out.println(new Shark());
                case 3 -> System.out.println(new Wolf());
                default -> System.out.println(new Predator());
            }
            i++;
        }
        while (i < 10);
    }

    public void createAnimals(int animalsQuantity) {
        int animalChooser;
        for (int i = 0; i < animalsQuantity; i++) {
            System.out.print("          Create Animal №" + (i+1) + ": ");
            animalChooser = i % 4;
            switch (animalChooser) {
                case 0 -> System.out.println(new Cat());
                case 1 -> System.out.println(new Dog());
                case 2 -> System.out.println(new Shark());
                case 3 -> System.out.println(new Wolf());
                default -> System.out.println(new Predator());
            }
        }
    }

}
