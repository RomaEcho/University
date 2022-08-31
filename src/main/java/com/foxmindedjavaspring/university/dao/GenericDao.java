package com.foxmindedjavaspring.university.dao;

import java.util.List;

public interface GenericDao<T> {
    int create(T object);

    int delete(long id);

    int delete(T object);

    T findById(long id);

    List<T> findAll();
}
