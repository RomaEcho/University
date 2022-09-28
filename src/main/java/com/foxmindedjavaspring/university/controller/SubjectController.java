package com.foxmindedjavaspring.university.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.foxmindedjavaspring.university.dto.SubjectDto;
import com.foxmindedjavaspring.university.model.Subject;
import com.foxmindedjavaspring.university.service.SubjectDtoService;
import com.foxmindedjavaspring.university.service.SubjectService;

@Controller
@RequestMapping("/subjects")
public class SubjectController {
    private final SubjectService subjectService;
    private final SubjectDtoService subjecDtoService;

    public SubjectController(SubjectService subjectService, 
            SubjectDtoService subjecDtoService) {
        this.subjectService = subjectService;
        this.subjecDtoService = subjecDtoService;
    }

    @GetMapping()
    public String showAll(@ModelAttribute("subjectDto") SubjectDto subjectDto, 
            Model model) {
        List<Subject> subjects = subjectService.getAllSubjects();
        model.addAttribute("subjects", subjects);
        return "subjects/index";
    }

    @GetMapping("/add")
    public String showFormForAdd(@ModelAttribute("subjectDto") 
            SubjectDto subjectDto) {
        return "subjects/add";
    }

    @PostMapping("/save")
    public String addSubject(@ModelAttribute("subjectDto") 
            SubjectDto subjectDto) {
        subjectService.addSubject(subjecDtoService.convertToSubject(subjectDto));
        return "redirect:/subjects";
    }

    @GetMapping("/search/{name}")
    public String findByName(@RequestParam(value = "name") String name, 
            Model model) {
        List<Subject> subjects = subjectService.getByName(name);
        model.addAttribute("subjects", subjects);
        return "subjects/result";
    }

    @GetMapping("/result")
    public String about(@ModelAttribute("subjectDto") 
            SubjectDto subjectDto, Model model) {
        return "subjects/result";
    }

    @PostMapping("/update/form")
    public String showFormForUpdate(@ModelAttribute("subjectDto") 
            SubjectDto subjectDto) {
        return "subjects/update";
    }

    @PostMapping("/update")
    public String updateSubject(@ModelAttribute("subjectDto") 
            SubjectDto subjectDto) {
        subjectService.editSubject(subjectDto.getId(), 
                subjecDtoService.convertToSubject(subjectDto));
        return "redirect:/subjects";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("subjectDto") 
            SubjectDto subjectDto) {
        subjectService.removeSubject(subjectDto.getId());
        return "redirect:/subjects";
    }
}
