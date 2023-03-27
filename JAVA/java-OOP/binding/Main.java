package binding;

public class Main {

    public static void main(String[] args) {

        School school1 = new School();
        school1.ringBell();

        Classroom classroom1 = new Classroom();
        classroom1 .ringBell();

        School school2 = new Classroom();
        school2.ringBell();
    }
}
