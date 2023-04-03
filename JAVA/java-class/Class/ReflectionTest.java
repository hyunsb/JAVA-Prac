package Class;

import java.util.Scanner;

public class ReflectionTest {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String DbName = "Mysql";

        Class jdbc = Class.forName(DbName);
        //DbConn dbconn = Class.forName(DbName).newInstance();
    }
}
