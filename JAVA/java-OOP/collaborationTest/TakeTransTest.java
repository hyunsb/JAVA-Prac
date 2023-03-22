package collaborationTest;

public class TakeTransTest {
    public static void main(String[] args) {

        Student studentJames = new Student("James", 5000);
        Student studentTomas = new Student("Tomas", 10000);

        Bus bus100 = new Bus(100);
        Subway subway2 = new Subway(2);

        studentJames.takeBus(bus100);
        studentTomas.takeSubway(subway2);

        studentJames.showInfo();
        studentTomas.showInfo();
        bus100.showBusInfo();
        subway2.showSubwayInfo();

    }
}
