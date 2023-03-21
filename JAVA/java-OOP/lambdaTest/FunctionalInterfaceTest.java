package lambdaTest;

public class FunctionalInterfaceTest {
    public static void main(String[] args) {
        SearchMinNumInterface myFunction1 = (x, y) -> x < y ? x : y;
        SearchMinNumInterface myFunction2 = (x, y) -> Math.min(x, y);
        SearchMinNumInterface myFunction3 = Math::min;

        System.out.println(myFunction1.min(99, 100));
        System.out.println(myFunction2.min(99, 100));
        System.out.println(myFunction3.min(99, 100));
    }
}