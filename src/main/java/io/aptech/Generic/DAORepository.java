package io.aptech.Generic;

import javafx.collections.ObservableList;

public interface DAORepository<T> {
    public void insert(T t);
    public void update(T t);
    public T getById(int id);
    public void delete(T t);
    public ObservableList<T> getAll();
}
