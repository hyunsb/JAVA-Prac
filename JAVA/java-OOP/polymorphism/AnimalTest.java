package polymorphism;

public class AnimalTest {

    public static void main(String[] args) {
        AnimalController animalController = new AnimalController();

        Animal animalHuman = new Human();
        Animal animalTiger = new Tiger();
        Animal animalEagle = new Eagle();

        animalController.inputAnimal(animalHuman);
        animalController.inputAnimal(animalTiger);
        animalController.inputAnimal(animalEagle);

        animalController.printAnimal();
    }
}
