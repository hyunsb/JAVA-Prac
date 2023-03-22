package collaborationTestSecond;

public class Taxi {

    String company;
    int earnings;

    public Taxi(String company){
       this.company = company;
    }

    public void take(Human human, int cost){
        human.money -= cost;
        earnings += cost;
    }

    public void showInfo(){
        System.out.println(company + "택시 수입은 " + earnings + "원 입니다.");
    }

}
