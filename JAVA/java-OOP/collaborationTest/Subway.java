package collaborationTest;

public class Subway {

    int number;
    int passengerCount;
    int earnings;

    public Subway(int number){
        this.number = number;
    }

    public void take(int money) {
        passengerCount += 1;
        earnings += money;
    }

    public void showSubwayInfo(){
        System.out.println("지하철 넘버: " + number + " 탑승 승객: " + passengerCount + " 수입: " + earnings);
    }

}
