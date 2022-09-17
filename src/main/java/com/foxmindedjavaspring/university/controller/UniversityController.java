package com.foxmindedjavaspring.university.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
