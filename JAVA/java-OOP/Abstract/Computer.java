package Abstract;

public abstract class Computer {

    protected abstract void display();

    protected abstract void typing();

    public void turnOn() {
        System.out.println("전원을 켭니다.");
    }

    public void turnOff() {
        System.out.println("전원을 끕니다.");
    }

}
