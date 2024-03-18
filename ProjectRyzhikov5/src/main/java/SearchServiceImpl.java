import Animals.AbstractAnimal;
import Exceptions.InvalidAnimalBirthDateException;
import Exceptions.InvalidAnimalException;
import Interfaces.SearchService;


public class SearchServiceImpl implements SearchService {

    @Override
    public void checkLeapYearAnimal(AbstractAnimal abstractAnimal) throws InvalidAnimalException, InvalidAnimalBirthDateException {
        if (abstractAnimal == null) {
            throw new InvalidAnimalException();
        } else {
            if (abstractAnimal.getBirthDate() == null) {
                throw new InvalidAnimalBirthDateException(abstractAnimal.getClass().toString());
            } else {
                String leapYearResult;
                boolean isLeapYear = abstractAnimal.getBirthDate().isLeapYear();
                if (isLeapYear) {
                    leapYearResult = " был рожден в високосный год";
                } else {
                    leapYearResult = " не был рожден в високосный год";
                }

                System.out.println(abstractAnimal.getName() + leapYearResult );
            }
        }
    }
}
