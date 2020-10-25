package dao;

import vo.userRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userRoleDao {
    private Connection con;
    private PreparedStatement pst;

    public userRoleDao(Connection con) {
        this.con = con;
    }

    public int getRoleId(String userName){
        int roleId=0;
        String sql="select roleId from t_user_role where userName=?";
        try {
            pst=con.prepareStatement(sql);
            pst.setString(1,userName);
            ResultSet rs=pst.executeQuery();
            if (rs.next()){
                roleId=rs.getInt("roleId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roleId;
    }
}
