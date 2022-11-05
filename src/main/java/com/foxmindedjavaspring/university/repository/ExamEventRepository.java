package com.foxmindedjavaspring.university.repository;

import com.foxmindedjavaspring.university.model.ExamEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamEventRepository extends JpaRepository<ExamEvent, Long> {

}
