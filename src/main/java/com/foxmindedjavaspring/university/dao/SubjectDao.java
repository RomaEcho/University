package com.foxmindedjavaspring.university.dao;

import java.util.List;

import com.foxmindedjavaspring.university.model.Subject;

/**
 * SubjectDao interface extends GenericDao for object Subject by methods used 
 * to update subject and search in subject's name. 
 */
public interface SubjectDao extends GenericDao<Subject> {
    /**
     * This method is used to update existing in the database subject
     * @param id First parameter points to subject which will be updated
     * @param subject Second parameter contains in it's fields new values
     * @return int Return the number of rows affected
     */
    int update(Long id, Subject subject);

    /**
     * This method is used to search some string in existing subjects names
     * @param name First parameter contains string used in the operation
     * @return List<Subject> Return the list of found Subjects 
     */
    List<Subject> findByName(String name);
}
