package com.eremkin.films_demo.controller;

import com.eremkin.films_demo.repository.FilmRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    private final FilmRepository filmRepository;

    public MainController(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("films", filmRepository.findAll());
        return "index";
    }
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "WORKS";
    }
}
