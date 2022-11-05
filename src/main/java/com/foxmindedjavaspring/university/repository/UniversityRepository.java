package com.foxmindedjavaspring.university.repository;

import com.foxmindedjavaspring.university.model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {

}
