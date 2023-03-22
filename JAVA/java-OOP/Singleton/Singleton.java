package Singleton;

public class Singleton {

    // 내부에서 자체적으로 인스턴스 생성
    private static final Singleton instance = new Singleton();

    // 외부에서 인스턴스 생성 불가
    private Singleton(){ }

    public static Singleton getInstance() {
        return instance;
    }
}
