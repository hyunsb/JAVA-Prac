package inheritance.test;

public class Member {

    public String name;

    Member(){ System.out.println("Member 생성자 호출"); }

    Member(String name) {
        this.name = name;
    }

}
