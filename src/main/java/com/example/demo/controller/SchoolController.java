package com.example.demo.controller;

import com.example.demo.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/school")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;

    @PostMapping()
    public void createMultipleSchool(){
        schoolService.createAll();
    }

    @GetMapping()
    public void getAllSchool() {
        schoolService.getAll();
    }
}
