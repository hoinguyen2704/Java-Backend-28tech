package ads.User;

import ads.Object.UserObject;

public class UserModel {
    private final User u;

    public UserModel() {
        this.u = new UserImpl();
    }

    public void releaseConnection() {

    }

    public boolean addUser(UserObject user) {
        return this.u.addUser(user);
    }

    public boolean editUser(UserObject user) {
        return this.u.editUser(user);
    }

    public boolean deleteUser(UserObject user) {
        return this.u.delUser(user);
    }


    //Java tuple
}
