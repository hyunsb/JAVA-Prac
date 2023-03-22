package collaborationTest;

public class Bus {

    int number;
    int passengerCount;
    int earnings;

    public Bus(int number){
        this.number = number;
    }

    public void take(int money) {
        passengerCount += 1;
        earnings += money;
    }

    public void showBusInfo(){
        System.out.println("버스 넘버: " + number + " 탑승 승객: " + passengerCount + " 수입: " + earnings);
    }
}
