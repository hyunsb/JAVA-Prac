package String;

import java.util.Objects;

public class StringTest {
    public static void main(String[] args) {
        String str1 = new String("String1");
        String str2 = str1;
        System.out.println(System.identityHashCode(str1));
        System.out.println(System.identityHashCode(str2));
    }
}
