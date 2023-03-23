package inheritance.test;

public class MemberVIP extends Member{

    String memberName;

    MemberVIP(String name) {
        System.out.println("MemberVIP 생성자 호출");
        this.name = name;
        memberName = name;
    }

}
