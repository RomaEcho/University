package com.foxmindedjavaspring.university.repository;

import com.foxmindedjavaspring.university.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

}
