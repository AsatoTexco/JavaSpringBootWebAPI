package com.example.springBootJava.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class TesteController {

    @GetMapping
    public String listarVeiculos() {


        String var_concat = String.format("<button onClick=%s>Click</button>","alert('OPAAAA!')");
        return var_concat;
    }

}
