package polymorphism;

import java.util.ArrayList;
import java.util.List;

public class AnimalController {

    List<Animal> animals = new ArrayList<>();

    public void inputAnimal(Animal animal){
        animals.add(animal);
    }

    public void printAnimal(){
        for(Animal animal : animals)
            animal.move();
    }

}
