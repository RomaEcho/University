package com.foxmindedjavaspring.university.controller;

import com.foxmindedjavaspring.university.dto.SubjectDto;
import com.foxmindedjavaspring.university.model.Subject;
import com.foxmindedjavaspring.university.service.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/subjects")
public class SubjectController {
    private final SubjectService subjectService;
    private static final Logger LOG = LoggerFactory.getLogger(
            SubjectController.class);

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
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
    public String addSubject(@Valid
                             @ModelAttribute("subjectDto")
                             SubjectDto subjectDto,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            LOG.trace("subjectDto has validation errors, the fields- name: {}, number: {}, errors: {}",
                    subjectDto.getName(), subjectDto.getNumber(),
                    bindingResult.getAllErrors());
            return "subjects/add";
        }
        subjectService.addSubject(subjectDto);
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
    public String updateSubject(@Valid
                                @ModelAttribute("subjectDto")
                                SubjectDto subjectDto,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            LOG.trace("subjectDto has validation errors, the fields- name: {}, number: {}, errors: {}",
                    subjectDto.getName(), subjectDto.getNumber(),
                    bindingResult.getAllErrors());
            return "subjects/update";
        }
        subjectService.addSubject(subjectDto);
        return "redirect:/subjects";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("subjectDto") 
            SubjectDto subjectDto) {
        subjectService.removeSubject(subjectDto);
        return "redirect:/subjects";
    }
}
