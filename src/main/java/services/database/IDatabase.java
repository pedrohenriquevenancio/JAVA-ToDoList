package main.java.services.database;
import java.util.ArrayList;

public interface IDatabase<T> {
    ArrayList<T> getAll();
    T getById(int id);
    void store(T item);
    void update(int id, T item);
    void delete(int id);
    default void deleteAll() {    };
}
