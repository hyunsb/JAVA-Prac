package object;

import java.util.Objects;

public class Student implements Cloneable{
    private int number;
    private String name;


    Student(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Student)
                && (Objects.equals(this.number, ((Student)obj).number));
    }

    @Override
    public int hashCode() {
        return number;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}