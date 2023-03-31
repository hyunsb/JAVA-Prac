package object;

import java.util.Objects;

class Student {
    private int number;
    private String name;


    Student(int number, String name) {
        this.number = number;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Student)
                && (Objects.equals(this.number, ((Student)obj).number));
    }
}

public class StudentTest{
    public static void main(String[] args) {
        Student student1 = new Student(1, "아이언맨");
        Student student2 = new Student(1, "아이언맨");
        Student student3 = new Student(2, "스파이더맨");

        System.out.println(student1.equals(student2));
        System.out.println(student1.equals(student3));
    }
}