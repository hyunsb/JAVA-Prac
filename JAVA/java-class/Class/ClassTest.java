package Class;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ClassTest {
    public static void main(String[] args) throws ClassNotFoundException {

        // 리플렉션 프로그램

        Class c = Class.forName("java.lang.String");

        // Sting 클래스 생성자 호출
        Constructor[] constructors = c.getConstructors();
        for(Constructor con : constructors)
            System.out.println(con);


        // String 클래스 메소드 호출
        Method[] methods = c.getMethods();
        for (Method method : methods)
            System.out.println(method);
    }
}
