package Singleton;

public class Main {
    public static void main(String[] args) {

        Singleton singleton1 = Singleton.getInstance();
        Object singleton2 = Singleton.getInstance();

        System.out.println( (singleton1 == singleton2) );

        Runtime runtime = Runtime.getRuntime();
    }
}
