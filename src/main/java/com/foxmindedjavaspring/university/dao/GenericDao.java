package com.foxmindedjavaspring.university.dao;

import java.util.List;

public interface GenericDao<T> {
    int create(T object);

    int delete(long id);

    T findById(long id);

    List<T> findAll();
}
