package ads;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionPoolImpl implements ConnectionPool {
    //Singleton design pattern
    private static ConnectionPool cp = null;
    private final String driver;
    private final String userName;
    private final String passWord;
    private final String url;
    private final Stack<Connection> pool;

    private ConnectionPoolImpl() {
        this.driver = "com.mysql.cj.jdbc.Driver";
        this.url = "jdbc:mysql://localhost:3306/tranhanh_data?allowMultiQueries=true";

        // xác định tk làm việc
        this.userName = "tranhanh_hoing";
        this.passWord = "@12#$%65";
        // nạp trinh điều khiển
        try {
            Class.forName(this.driver);
        } catch (ClassNotFoundException e) {
            // handle exception
            e.printStackTrace();
        }

        this.pool = new Stack<Connection>();
//		ArrayList<Connection> cons = new ArrayList<Connection>();
//		List<Connection> cons = new ArrayList<Connection>();
//		List<Connection> cons = new Stack<Connection>();
    }

    public static ConnectionPool getInstance() {
        if (cp == null) {
            synchronized (ConnectionPoolImpl.class) {
                if (cp == null) {
                    cp = new ConnectionPoolImpl();
                }
            }
        }
        return cp;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    public Connection getConnection(String objectName) throws SQLException {
        // TODO Auto-generated method stub
        if (this.pool.isEmpty()) {
            // khởi tạo
            System.out.println(objectName + " have created a new connection.");
            return DriverManager.getConnection(this.url, this.userName, this.passWord);
        } else {
            // lấy kết nối đã được lưu trữ
            System.out.println(objectName + " have popped the connection.");
            return this.pool.pop();
        }
    }

    public void releaseConnection(Connection con, String objectName) throws SQLException {
        // TODO Auto-generated method stub
        System.out.println(objectName + " have pushed the connection.");
        this.pool.push(con);
    }

}
