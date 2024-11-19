package ads.User;

import ads.Object.UserObject;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface User {
    boolean addUser(UserObject item);

    boolean editUser(UserObject item);

    boolean delUser(UserObject item);

    ArrayList<ResultSet> getUsers(String multiSelect);

    ArrayList<ResultSet> getUsers(UserObject similar, int at, byte total);

    ResultSet getUser(int id);

    ResultSet getUser(String username, String userpass);
}
