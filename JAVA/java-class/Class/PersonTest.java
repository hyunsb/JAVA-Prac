package Class;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class PersonTest {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        Class<?> c1 = Class.forName("Class.Person");

        Person person1 = (Person) c1.newInstance();
        person1.setName("장보고");
        System.out.println(person1.getName());

        Class<?> c2 = person1.getClass();
        Person person2 = (Person) c2.newInstance();


        Class[] parameterTypes = {String.class};
        Constructor<?> constructor = c2.getConstructor(parameterTypes);

        Object[] initArgs = {"kim"};
        Person person3 = (Person) constructor.newInstance(initArgs);

        System.out.println(person2.getName());
        System.out.println(person3.getName());
    }
}
