package com.foxmindedjavaspring.university.repository;

import com.foxmindedjavaspring.university.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * SubjectRepository interface extends JpaRepository for object Subject by
 * methods used to search in subject's name.
 */
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    /**
     * This method is used to search some string in existing subjects names
     * @param name First parameter contains string used in the operation
     * @return List<Subject> Return the list of found Subjects 
     */
    @Query("SELECT s FROM Subject s WHERE lower(s.name) LIKE lower(concat('%', :subName,'%'))")
    List<Subject> findByName(@Param("subName") String name);
}
