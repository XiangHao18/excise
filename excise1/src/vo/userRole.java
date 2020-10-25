package vo;

public class userRole {
    private int id;
    private int roleId;
    private String userName;

    public userRole(int id, int roleId, String userName) {
        this.id = id;
        this.roleId = roleId;
        this.userName = userName;
    }

    public userRole() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "userRole{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
