package main.java.services;

import java.util.ArrayList;
import java.util.Optional;

public interface IDatabase<T> {
    ArrayList<T> getAll();
    T getById(Integer id);
    void store(T item);
    void update(Integer id, T item);
    void delete(Integer id);
    default void deleteAll() {    };
}
