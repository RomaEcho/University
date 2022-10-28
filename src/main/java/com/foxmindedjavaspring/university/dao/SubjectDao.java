package com.foxmindedjavaspring.university.dao;

import java.util.List;

import com.foxmindedjavaspring.university.model.Subject;

/**
 * SubjectDao interface extends GenericDao for object Subject by methods used 
 * to search in subject's name. 
 */
public interface SubjectDao extends GenericDao<Subject> {
    /**
     * This method is used to search some string in existing subjects names
     * @param name First parameter contains string used in the operation
     * @return List<Subject> Return the list of found Subjects 
     */
    List<Subject> findByName(String name);
}
