package model.users;

public class AuthSystem {
    // ------------------------------------------------------- Instance
    private final static AuthSystem instance = new AuthSystem();

    public static AuthSystem getInstance() {
        return instance;
    }

    // ------------------------------------------------------- Class

    private final UserList userList = new UserList();
    private User currentUser = null;

    public boolean login(String name, String password) {
        if(currentUser != null) return false;

        var user = userList.login(name, password);
        if(user == null) return false;

        currentUser = user;
        return true;
    }

    public boolean logout() {
        if(currentUser == null) return false;
        currentUser = null;
        return true;
    }

    public boolean addUserAndConnect(String name, String password) {
        if(currentUser != null) return false;

        var user = userList.addUser(name, password);
        if(user == null) return false;

        currentUser = user;
        return true;
    }

    public boolean addUser(String name, String password) {
        var user = userList.addUser(name, password);
        return user != null;
    }

    public boolean userExists(String name) {
        return userList.userExists(name);
    }

    public String getCurrentUser() {
        if(currentUser == null) return "";
        return currentUser.name();
    }
}
