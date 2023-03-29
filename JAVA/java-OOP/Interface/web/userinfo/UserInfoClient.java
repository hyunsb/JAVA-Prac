package Interface.web.userinfo;

import Interface.domain.userInfo.UserInfo;
import Interface.domain.userInfo.dao.UserInfoDao;
import Interface.domain.userInfo.dao.mysql.UserInfoMysqlDao;
import Interface.domain.userInfo.dao.oracle.UserInfoOracleDao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UserInfoClient {
    public static void main(String[] args) throws IOException {

        FileInputStream fis = new FileInputStream("JAVA/java-OOP/Interface/db.properties");

        Properties prop = new Properties();
        prop.load(fis);

        String dbType = prop.getProperty("DBTYPE");

        UserInfo userInfo = new UserInfo();

        UserInfoDao userInfoDao = (dbType.equals("MYSQL")) ? new UserInfoMysqlDao() : new UserInfoOracleDao();

    }
}
