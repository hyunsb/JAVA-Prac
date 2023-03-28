package polymorphism;

public class InstanceofTest {
    public static void main(String[] args) {

        Human human = new Human();
        if(human instanceof Animal) human.move();

    }
}
