package ads.Basic;

import ads.ConnectionPool;
import ads.ConnectionPoolImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Basicimpl implements Basic {
    // đối tượng làm việc với basic
    private final String objectName;
    // bộ quản lý kết nối được chia sẻ
    private final ConnectionPool cp = ConnectionPoolImpl.getInstance();
    // kết nối của riêng Basic sử dụng
    protected Connection con;

    public Basicimpl(String objectName) {
        // xác định đối tượng làm việc
        this.objectName = objectName;

        // xin kết nối
        try {
            this.con = this.cp.getConnection(this.objectName);

            // k tra chế độ thực thi của kêt nối

            if (this.con.getAutoCommit()) {
                this.con.setAutoCommit(false);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    public boolean exe(PreparedStatement pre) {
        // TODO Auto-generated method stub
        if (pre != null) {
            // lấy số luọng bản ghi đc tác động
            try {
                int result = pre.executeUpdate();
                if (result == 0) {
                    this.con.rollback();
                    return false;
                }
                this.con.commit();
                return true;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                // comeback trạng thái an toàn of hệ thống
                try {
                    this.con.rollback();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public boolean add(PreparedStatement pre) {
        // TODO Auto-generated method stub
        return this.exe(pre);
    }

    @Override
    public boolean edit(PreparedStatement pre) {
        // TODO Auto-generated method stub
        return this.exe(pre);
    }

    @Override
    public boolean del(PreparedStatement pre) {
        // TODO Auto-generated method stub
        return this.exe(pre);
    }

    @Override
    public ArrayList<ResultSet> gets(String multilSelect) {
        // TODO Auto-generated method stub

        ArrayList<ResultSet> res = new ArrayList<>();
        try {
            PreparedStatement stmt = this.con.prepareStatement(multilSelect);
            boolean results = stmt.execute();
            do {
                if (results) {
                    res.add(stmt.getResultSet());
                }
                results = stmt.getMoreResults(PreparedStatement.KEEP_CURRENT_RESULT);

            } while (results);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public ResultSet get(String sql, int value) {
        // TODO Auto-generated method stub

        // biên dịch câu lệnh
        try {
            PreparedStatement pre = this.con.prepareStatement(sql);
            if (value > 0) {
                pre.setInt(1, value);

            }
            return pre.executeQuery();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultSet get(String sql, String name, String pass) {
        // TODO Auto-generated method stub
        try {
            PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setString(1, pass);
            pre.setString(2, pass);
            return pre.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return null;
    }

    @Override
    public void releaseConnection() {
        // TODO Auto-generated method stub
        try {
            this.cp.releaseConnection(this.con, this.objectName);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    protected void finalize() throws Throwable {
        // this.releaseConnection();

    }

}
