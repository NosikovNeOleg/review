package Animals;

public class Cat extends Pet{
    @Override
    public String toString() {
        return "Cat{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                '}';
    }

    public Cat() {
        character = "Кошачий";
    }
}
