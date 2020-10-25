package dbc;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnection {
    private Connection con;
    private static String className;
    private static String url;
    private static String name;
    private static String password;

    static {
        try {
            Properties pro=new Properties();
            InputStream in= DataBaseConnection.class.getClassLoader().getResourceAsStream("resources/jdbc.properties");
            pro.load((new InputStreamReader(in,"utf-8")));
            in.close();
            className=pro.getProperty("className");
            url=pro.getProperty("url");
            name=pro.getProperty("name");
            password=pro.getProperty("password");
            Class.forName(className);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public DataBaseConnection(){
        try {
            this.con= DriverManager.getConnection(url,name,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return con;
    }

    public void close(){
        if(con!=null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
