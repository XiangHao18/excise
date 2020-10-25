package dao;

import vo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private Connection con;
    private PreparedStatement pst;

    public UserDao (Connection con){
        this.con=con;
    }

    public User get(String userName){
        User user=null;
        String sql="select * from t_user where userName=?";
        try {
            pst=con.prepareStatement(sql);
            pst.setString(1,userName);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                user=new User();
                user.setUsername(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                user.setChrName(rs.getString("chrName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
