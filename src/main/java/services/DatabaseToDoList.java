package main.java.services;

import main.java.models.Task;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class DatabaseToDoList implements IDatabase {
    private final ArrayList<Task> db;

    public DatabaseToDoList() {
        db = new ArrayList<Task>();
    }

    @Override
    public ArrayList<Task> getAll() {
        return db;
    }

    @Override
    public Task getById(Integer id) {
        Optional<Task> result = db.stream()
                .filter(t -> Objects.equals(t.getId(), id))
                .findFirst();
        return result.orElse(null);
    }

    @Override
    public void store(Object item) {

    }

    @Override
    public void update(Integer id, Object item) {

    }

    @Override
    public void delete(Integer id) {

    }
}
