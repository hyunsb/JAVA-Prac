package staticTest;

public class Main {
    public static void main(String[] args) {
        MyClass.staticVar = 1;
        MyClass.staticMethod();

        MyClass myClass1 = new MyClass();
        MyClass myClass2 = new MyClass();

        myClass1.instanceVar = 2;
        myClass2.instanceVar = 3;

        myClass1.instanceMethod();
        myClass2.instanceMethod();

        System.out.println(MyClass.staticVar);
    }
}
