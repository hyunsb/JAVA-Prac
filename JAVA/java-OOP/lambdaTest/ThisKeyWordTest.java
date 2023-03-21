package lambdaTest;

class ThisKeyWord {
    public String field = "Outer Class Field";

    class Inner {
        String field = "Inner Class Field";

        void method() {
            MyFunctionalInterface fi = () -> {
                String field = "lambda field";

                System.out.println(field);
                System.out.println(this.field);
                System.out.println(ThisKeyWord.this.field);
            };
            fi.method();
        }

    }
}

public class ThisKeyWordTest {

    String field = "main class field";

    public static void main(String[] args) {

        String field = "main method field";

        ThisKeyWord thisKeyWord = new ThisKeyWord();
        ThisKeyWord.Inner inner = thisKeyWord.new Inner();

        inner.method();
    }
}
