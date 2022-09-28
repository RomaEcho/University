package com.foxmindedjavaspring.university.dao;

import java.util.List;

import com.foxmindedjavaspring.university.model.Subject;

/**
 * SubjectDao interface extends GenericDao for object Subject by
 * update(Long id, Subject subject)
 * and
 * findByName(String name)
 * methods
 */
public interface SubjectDao extends GenericDao<Subject> {
    /**
     * the method updating the Subject with id = @param id by @param subject fields
     */
    int update(Long id, Subject subject);

    /**
     * the method @return all the Subjects which name contains @param name
     */
    List<Subject> findByName(String name);
}
