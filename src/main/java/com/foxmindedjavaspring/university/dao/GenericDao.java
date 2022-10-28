package com.foxmindedjavaspring.university.dao;

import java.util.List;

public interface GenericDao<T> {
    void create(T object);

    void delete(T object);

    T update(T object);

    T findById(Long id);

    List<T> findAll();
}
