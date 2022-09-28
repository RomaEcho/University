package com.foxmindedjavaspring.university.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foxmindedjavaspring.university.dto.SubjectDto;
import com.foxmindedjavaspring.university.model.Subject;
import com.foxmindedjavaspring.university.service.SubjectService;

@Controller
@RequestMapping("subjects")
public class SubjectController {
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
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
        subjectService.addSubject(subjectDto.convertToSubject());
        return "redirect:/subjects";
    }

    @PostMapping("/search")
    public String find(@ModelAttribute("subjectDto") SubjectDto subjectDto,
            Model model) {
        Subject subject = subjectService.getSubject(subjectDto.getId());
        model.addAttribute("subject", subject);
        return "subjects/about";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "subjects/about";
    }

    @GetMapping("/update/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, 
            @ModelAttribute("subjectDto") SubjectDto subjectDto) {
        return "subjects/update";
    }

    @PostMapping("/update")
    public String updateSubject(@ModelAttribute("subjectDto") 
            SubjectDto subjectDto) {
        subjectService.editSubject(subjectDto.getId(), 
                subjectDto.convertToSubject());
        return "redirect:/subjects";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable( value = "id") long id) {
        subjectService.removeSubject(id);
        return "redirect:/subjects";
    }
}
