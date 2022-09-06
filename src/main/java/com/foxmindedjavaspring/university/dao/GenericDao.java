package com.foxmindedjavaspring.university.dao;

import java.util.List;

public interface GenericDao<T> {
    int create(T object);

    int delete(Long id);

    T findById(Long id);

    List<T> findAll();
}
