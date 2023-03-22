package collaborationTest;

public class Student {

    String name;
    int money;

    int busCost;
    int subwayCost;

    public Student(String name, int money){
        this.name = name;
        this.money = money;
        busCost = 1000;
        subwayCost = 1200;
    }

    public void takeBus(Bus bus){
        bus.take(busCost);
        this.money -= busCost;
    }

    public void takeSubway(Subway subway){
        subway.take(subwayCost);
        this.money -= subwayCost;
    }

    public void showInfo(){
        System.out.println(name + "의 남은 돈: " + money);
    }

}
