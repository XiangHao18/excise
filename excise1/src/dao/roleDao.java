package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class roleDao {
    private Connection con;
    private PreparedStatement pst;

    public roleDao(Connection con) {
        this.con = con;
    }

    public String getRoleName(int roleId){
        String roleName="";
        String sql="select roleName from t_role where roleId=?";
        try {
            pst=con.prepareStatement(sql);
            pst.setInt(1,roleId);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                roleName=rs.getString("roleName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roleName;
    }
}
