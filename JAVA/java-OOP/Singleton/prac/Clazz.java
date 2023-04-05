package Singleton.prac;

public class Clazz {
    public static int field = 0;
    private volatile static Clazz uniqueInstance;

    private Clazz() {}

    public static Clazz getInstance() {
        if(uniqueInstance == null) {
            synchronized (Class.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Clazz();
                }
            }
        }
        return uniqueInstance;
    }
}
