package com.foxmindedjavaspring.university.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.foxmindedjavaspring.university.model.University;
import com.foxmindedjavaspring.university.service.UniversityService;

@Controller
public class UniversityController {
    
    private final UniversityService universityService;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }
    
    @GetMapping("/university")
    public String show(Model model) {
        University university = universityService.getAllUniversities().get(0);
        model.addAttribute("university", university);
        return "university/about";
    }
}
