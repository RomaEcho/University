package com.foxmindedjavaspring.university.service;

import java.util.List;

public interface GenericService<T> {
    void add(T object);

    void remove(T object);

    List<T> getAll();
}
