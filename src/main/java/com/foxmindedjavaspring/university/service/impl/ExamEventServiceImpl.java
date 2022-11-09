package com.foxmindedjavaspring.university.service.impl;

import com.foxmindedjavaspring.university.repository.ExamEventRepository;
import com.foxmindedjavaspring.university.model.ExamEvent;
import com.foxmindedjavaspring.university.service.ExamEventService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExamEventServiceImpl implements ExamEventService {
    static final String GET_EXAM_EVENT_ERROR = "::Error while getting exam_event with id";
    private final ExamEventRepository examEventRepository;

    public ExamEventServiceImpl(ExamEventRepository examEventRepository) {
        this.examEventRepository = examEventRepository;
    }

    @Override
    public void addExamEvent(@NonNull ExamEvent examEvent) {
        examEventRepository.save(examEvent);
    }

    @Override
    public void removeExamEvent(@NonNull ExamEvent examEvent) {
        examEventRepository.delete(examEvent);
    }

    @Override
    public ExamEvent getExamEvent(Long id) {
        return examEventRepository.findById(id).orElseThrow(
                () -> new IllegalStateException(String.format("%s: %s",
                        GET_EXAM_EVENT_ERROR, id)));
    }

    @Override
    public List<ExamEvent> getAllExamEvents() {
        return examEventRepository.findAll();
    }

    @Override
    public void editExamEvent(@NonNull ExamEvent examEvent) {
        examEventRepository.save(examEvent);
    }
}
