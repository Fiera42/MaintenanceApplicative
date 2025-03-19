package model.users;

import java.util.*;
import java.util.function.Consumer;

public class UserList implements Iterable<String> {
    private final HashMap<String, User> users = new HashMap<>();

    public User addUser(String name, String password) {
        User user = new User(name, password);

        if(users.containsKey(user.name())) return null;

        users.put(user.name(), user);
        return user;
    }

    public User login(String name, String password) {
        User user = new User(name, password);

        if(!users.containsKey(user.name())) return null;
        if(!users.get(user.name()).equals(user)) return null;
        return users.get(user.name());
    }

    public boolean userExists(String name) {
        User user = new User(name, "dummy");
        return users.containsKey(user.name());
    }

    /**
     * Return an iterator containing all the usernames, does not contain password
     * @return an iterator containing all the usernames, does not contain password
     */
    @Override
    public Iterator<String> iterator() {
        return new Iterator<>() {
            private final Iterator<User> it = users.values().iterator();

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public String next() {
                return it.next().name();
            }
        };
    }

    /**
     * Apply an action every username in the database
     * @param action The action to be performed for each element
     */
    @Override
    public void forEach(Consumer<? super String> action) {
        users.values().forEach(user -> action.accept(user.name()));
    }

    /**
     * Return a spliterator that perform action on every username in the database
     * @return The spliterator containing all the names
     */
    @Override
    public Spliterator<String> spliterator() {
        return new Spliterators.AbstractSpliterator<>(
                users.size(), Spliterator.ORDERED) {
            private final Iterator<User> it = users.values().iterator();

            @Override
            public boolean tryAdvance(Consumer<? super String> action) {
                if (it.hasNext()) {
                    action.accept(it.next().name());
                    return true;
                }
                return false;
            }
        };
    }
}
