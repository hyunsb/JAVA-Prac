package Singleton;

public class CarFactory {

    private static CarFactory instance = new CarFactory();
    private static int carNum = 1001;

    private CarFactory(){ }

    public static CarFactory getInstance(){
        return instance;
    }

    public Car createCar(){
        return new Car(carNum++);
    }
}
