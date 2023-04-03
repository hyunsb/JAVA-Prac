package object;

import java.util.Objects;

public class StudentTest{
    public static void main(String[] args) throws CloneNotSupportedException {
        Student student1 = new Student(1, "아이언맨");
        Student student2 = new Student(1, "아이언맨");
        Student student3 = new Student(2, "스파이더맨");

        System.out.println(student1.equals(student2));
        System.out.println(student1.equals(student3));

        System.out.println(Objects.equals(student1.hashCode(), student2.hashCode()));
        System.out.println(System.identityHashCode(student1));
        System.out.println(System.identityHashCode(student2));

        // 깊은 복사
        Student student1Clone = (Student) student1.clone();
        System.out.println(System.identityHashCode(student1));
        System.out.println(System.identityHashCode(student1Clone));

        student1Clone.setName("베트맨");
        System.out.println(student1.getName());
        System.out.println(student1Clone.getName());
    }
}