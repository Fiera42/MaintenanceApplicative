package model.users;

public class AuthSystem {
    // ------------------------------------------------------- Instance
    private static AuthSystem instance = new AuthSystem();

    private AuthSystem() {}

    public static void resetAuthSystem() {
        instance = new AuthSystem();
    }

    // ------------------------------------------------------- Class

    private final UserList userList = new UserList();
    private User currentUser = null;

    public static boolean login(String name, String password) {
        if(instance.currentUser != null) return false;

        var user = instance.userList.login(name, password);
        if(user == null) return false;

        instance.currentUser = user;
        return true;
    }

    public static boolean logout() {
        if(instance.currentUser == null) return false;
        instance.currentUser = null;
        return true;
    }

    public static boolean addUserAndConnect(String name, String password) {
        if(instance.currentUser != null) return false;

        var user = instance.userList.addUser(name, password);
        if(user == null) return false;

        instance.currentUser = user;
        return true;
    }

    public static boolean addUser(String name, String password) {
        var user = instance.userList.addUser(name, password);
        return user != null;
    }

    public static boolean userExists(String name) {
        return instance.userList.userExists(name);
    }

    public static String getCurrentUser() {
        if(instance.currentUser == null) return "";
        return instance.currentUser.name();
    }
}
