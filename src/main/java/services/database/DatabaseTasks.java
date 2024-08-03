package main.java.services.database;

import main.java.models.Task;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class DatabaseTasks implements IDatabase {
    private ArrayList<Task> db;

    public DatabaseTasks() {
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
        db.add((Task) item);
    }

    @Override
    public void update(Integer id, Object item) {
        Task task = (Task) item;
        db = (ArrayList<Task>) db.stream()
                .map(t -> {
                    if (Objects.equals(t.getId(), id)) {
                        return task;
                    }
                    return t;
                }).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        db = (ArrayList<Task>) db.stream().filter(t -> !Objects.equals(t.getId(), id)).collect(Collectors.toList());
    }
}
