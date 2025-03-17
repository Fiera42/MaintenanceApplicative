package model.users;

import java.util.Locale;
import java.util.Objects;

public record User(String name, String password) {
    public User {
        Objects.requireNonNull(name);
        Objects.requireNonNull(password);

        name = name.trim();
        password = password.trim();

        if (name.isBlank()) throw new IllegalArgumentException("Username cannot be blank.");
        if (password.isBlank()) throw new IllegalArgumentException("Password cannot be blank.");

        name = name.substring(0, 1).toUpperCase(Locale.ROOT) + name.substring(1).toLowerCase(Locale.ROOT);
    }
}
