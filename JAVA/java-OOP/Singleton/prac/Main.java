package Singleton.prac;

public class Main {

    public static void main(String[] args) {
        Clazz c1 = Clazz.getInstance();
        Clazz c2 = Clazz.getInstance();

        System.out.println(c1.hashCode());
        System.out.println(c1.hashCode());

        c1.field = 1;
        System.out.println(c2.field);
    }
}
