package com.example.springBootJava.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste2")
public class Conflito {

    @GetMapping
    public String listarUsuarios() {
        return "2222";
    }
}
