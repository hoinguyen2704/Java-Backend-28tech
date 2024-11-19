package ads.User;

import ads.Basic.Basicimpl;
import ads.Object.UserObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserImpl extends Basicimpl implements User {

    public UserImpl() {
        super("User");
    }

    public static void main(String[] args) {
        User u = new UserImpl();
        //đối tượng để thêm mới
        UserObject nUser = new UserObject();
//        nUser.setUser_id(77);
        nUser.setUser_name("hoinguyen22");
        nUser.setUser_created_date("19/11/2024");
        nUser.setUser_pass("121113456");
        nUser.setUser_parent_id(93847);
        nUser.setUser_email("ditmemay@cw3roncac.lon");
        nUser.setUser_fullname("Ditmemay");
        nUser.setUser_birthday("27/04/1990");
        nUser.setUser_mobilephone("565645646");
        nUser.setUser_homephone("94845566");
        boolean result = u.addUser(nUser);
        if (result) {
            System.out.println("User added successfully");
        } else {
            System.out.println("Error added user");
        }
        // Danh sach nguoi su dung
        ArrayList<ResultSet> res = u.getUsers(null, 0, (byte) 15);
        //Tra ve ket noi


        ResultSet rs = res.get(0); // Danh sach nguoi su dung
        String row = "";
        try {
            while (rs.next()) {
                row = "ID: " + rs.getInt("user_id");
                row += "\tNAME: " + rs.getString("user_name");
                row += "\tFULL NAME: " + rs.getString("user_fullname");
                row += "\tPASS: " + rs.getString("user_pass");

                System.out.print(row);
            }
            rs.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //Tong so nguoi su dung
        rs = res.get(1);
        if (rs != null) {
            try {
                if (rs.next()) {
                    System.out.println("Tong so nguoi su dung: " + rs.getInt("total"));
                }
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean addUser(UserObject item) {
        if (this.isExiting(item)) {
            return false;
        }
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO tbluser(user_name, user_pass, user_fullname, user_birthday, user_mobilephone");
        sql.append(",user_homephone, user_officephone, user_email, user_address, user_jobarea");
        sql.append(",user_job, user_position, user_applyyear, user_permission, user_notes");
        sql.append(",user_roles, user_logined, user_created_date, user_last_modified, user_last_logined");
        sql.append(",user_parent_id, user_actions, user_deleted)");
        sql.append("VALUES(?,md5(?),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        try {
            PreparedStatement pre = this.con.prepareStatement(sql.toString());
            pre.setString(1, item.getUser_name());
            pre.setString(2, item.getUser_pass());
            pre.setString(3, item.getUser_fullname());
            pre.setString(4, item.getUser_birthday());
            pre.setString(5, item.getUser_mobilephone());
            pre.setString(6, item.getUser_homephone());
            pre.setString(7, item.getUser_officephone());
            pre.setString(8, item.getUser_email());
            pre.setString(9, item.getUser_address());
            pre.setString(10, item.getUser_jobarea());
            pre.setString(11, item.getUser_job());
            pre.setString(12, item.getUser_position());
            pre.setShort(13, item.getUser_applyyear());
            pre.setByte(14, item.getUser_permission());
            pre.setString(15, item.getUser_notes());
            pre.setString(16, item.getUser_roles());
            pre.setInt(17, item.getUser_logined());
            pre.setString(18, item.getUser_created_date());
            pre.setString(19, item.getUser_last_modified());
            pre.setString(20, item.getUser_last_logined());
            pre.setInt(21, item.getUser_parent_id());
            pre.setByte(22, item.getUser_actions());
            pre.setBoolean(23, item.isUser_deleted());

            return this.add(pre);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                this.con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean editUser(UserObject item) {
        // TODO Auto-generated method stub
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE tbluser set user_pass=md5(?) user_fullname=? user_birthday=? user_mobilephone=?");
        sql.append("user_homephone=? user_officephone=? user_email=? user_address=? user_jobarea=?");
        sql.append("user_job=? user_position=? user_applyyear=? user_permission=? user_notes=?");
        sql.append("user_roles=? user_last_modified=? user_last_logined=?");
        sql.append(" user_actions=? user_deleted=?");
        sql.append("WHERE user_id=?");
        try {
            PreparedStatement pre = this.con.prepareStatement(sql.toString());
            pre.setString(1, item.getUser_pass());
            pre.setString(2, item.getUser_fullname());
            pre.setString(3, item.getUser_birthday());
            pre.setString(4, item.getUser_mobilephone());
            pre.setString(5, item.getUser_homephone());
            pre.setString(6, item.getUser_officephone());
            pre.setString(7, item.getUser_email());
            pre.setString(8, item.getUser_address());
            pre.setString(9, item.getUser_jobarea());
            pre.setString(10, item.getUser_job());
            pre.setString(11, item.getUser_position());
            pre.setShort(12, item.getUser_applyyear());
            pre.setByte(13, item.getUser_permission());
            pre.setString(14, item.getUser_notes());
            pre.setString(15, item.getUser_roles());
            pre.setString(16, item.getUser_last_modified());
            pre.setString(17, item.getUser_last_logined());
            pre.setByte(18, item.getUser_actions());
            pre.setBoolean(19, item.isUser_deleted());
            pre.setInt(20, item.getUser_id());

            return this.add(pre);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                this.con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean delUser(UserObject item) {
        // TODO Auto-generated method stub
        if (!this.isEmpty(item)) {
            return false;
        }
        String sql = "DELETE FROM tbluser WHERE user_id=?";
        try {
            PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setInt(1, item.getUser_id());
            return this.del(pre);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                this.con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public ArrayList<ResultSet> getUsers(String multiSelect) {
        // TODO Auto-generated method stub

        if (multiSelect != null && !" ".equalsIgnoreCase(multiSelect)) {
            return this.gets(multiSelect);
        } else {
            String sql = "SELECT * FROM tbluser " + "ORDER BY user_id DESC " + ("LIMIT 10");

            return this.gets(sql);
        }
    }

    @Override
    public ResultSet getUser(int id) {
        // TODO Auto-generated method stub

        String sql = "SELECT * FROM tbluser WHERE user_id =?";

        return this.get(sql, id);
    }

    @Override
    public ResultSet getUser(String username, String userpass) {
        // TODO Auto-generated method stub

        String sql = "SELECT * FROM tbluser WHERE (user_name=?) AND (user_pass=?)";

        return this.get(sql, username, userpass);
    }

    @Override
    public ArrayList<ResultSet> getUsers(UserObject similar, int at, byte total) {
        // TODO Auto-generated method stub

        String sql = "SELECT * FROM tbluser " + "ORDER BY user_id DESC " + "LIMIT 10" + at + ", " + total + ";" +

                //Dem so luong nguoi su dung trong he thong
                "SELECT COUNT(user_id) AS total FROM tblUser;";

        return this.gets(sql);
    }

    private boolean isExiting(UserObject item) {
        boolean flag = false;
        String sql = "SELECT user_id FROM tbluser WHERE user_name='" + item.getUser_name() + "'";
        ResultSet rs = this.get(sql, 0);
        if (rs != null) {
            try {
                if (rs.next()) {
                    flag = true;
                }
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    private boolean isEmpty(UserObject item) {
        boolean flag = true;
        String sql = "SELECT product_id FROM tblproduct WHERE product_manager_id=" + item.getUser_id() + ";" + "SELECT section_id FROM tblsection WHERE section_manager_id=" + item.getUser_id() + ";" + "SELECT category_id FROM tblcategory WHERE category_manager_id=" + item.getUser_id() + ";" + "SELECT article_id FROM tblarticle WHERE article_author_name=?" + item.getUser_id() + ";";

        ArrayList<ResultSet> res = this.gets(sql);
        for (ResultSet rs : res) {
            try {
                if (rs != null && rs.next()) {
                    flag = false;
                    break;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }
}
