package com.foxmindedjavaspring.university.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.foxmindedjavaspring.university.model.University;
import com.foxmindedjavaspring.university.service.UniversityService;

@Controller
public class UniversityController {
    // private final UniversityService universityService;

    // public UniversityController(UniversityService universityService) {
    //     this.universityService = universityService;
    // }
    
    @GetMapping("/")
    public String show() {
        // Optional<University> university = universityService.getAllUniversities()
        //         .stream().findFirst();
        // model.addAttribute("university", university);
        return "about";
    }
}
