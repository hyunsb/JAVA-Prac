package staticTest;

public class MyClass {
    public static int staticVar = 0;
    public int instanceVar = 0;

    public static void staticMethod() {
        System.out.println("This is a static method");
    }

    public void instanceMethod() {
        System.out.println("This is an instance method");
    }
}
