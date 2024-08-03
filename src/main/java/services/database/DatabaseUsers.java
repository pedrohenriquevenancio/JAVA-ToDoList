package main.java.services.database;

import main.java.models.User;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class DatabaseUsers implements IDatabase {

    public ArrayList<User> db;

    public DatabaseUsers() {
        db = new ArrayList<User>();
        db.add(new User("pedro","12345","Pedro"));
    }

    public User getAuth(String username, String password) {
        Optional<User> user = db.stream()
                .filter(u -> Objects.equals(username, u.getUsername()) && Objects.equals(password, u.getPassword()))
                .findFirst();
        return user.orElse(null);
    }

    @Override
    public ArrayList<User> getAll() { return db; }

    @Override
    public User getById(int id) {
        Optional<User> result = db.stream()
                .filter(u -> Objects.equals(u.getId(), id))
                .findFirst();
        return result.orElse(null);
    }

    @Override
    public void store(Object item) {
      db.add((User) item);
    }

    @Override
    public void update(int id, Object item) {
        User user = (User) item;
        db = (ArrayList<User>) db.stream()
                .map(u -> {
                    if (Objects.equals(u.getId(), id)) {
                        return user;
                    }
                    return u;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void delete(int id) {
        db = (ArrayList<User>) db.stream().filter(u -> !Objects.equals(u.getId(), id)).collect(Collectors.toList());
    }
}
