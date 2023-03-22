package collaborationTestSecond;

public class TakeTaxiTest {
    public static void main(String[] args) {
        Human humanEdward = new Human("Edward", 20000);
        Taxi taxi = new Taxi("잘 간다 운수");

        taxi.take(humanEdward, 10000);

        humanEdward.showInfo();
        taxi.showInfo();
    }
}
