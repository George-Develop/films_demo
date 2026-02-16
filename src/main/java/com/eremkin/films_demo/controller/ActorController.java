package com.eremkin.films_demo.controller;

import com.eremkin.films_demo.model.Actor;
import com.eremkin.films_demo.repository.ActorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/actors")
public class ActorController {

    private final ActorRepository actorRepository;

    public ActorController(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @GetMapping
    public String getActors(Model model) {
        model.addAttribute("actors", actorRepository.findAll());
        return "actorList";
    }

    @PostMapping("/add")
    public String addActor(@RequestParam String name) {
        actorRepository.save(new Actor(name));
        return "redirect:/actors";
    }

    @PostMapping("/delete")
    public String deleteActor(@RequestParam Long id) {
        actorRepository.deleteById(id);
        return "redirect:/actors";
    }

    @PostMapping("/update")
    public String updateActor(@RequestParam Long id, @RequestParam String name) {
        Actor actor = actorRepository.findById(id).orElse(null);
        if (actor != null) {
            actor.setName(name);
            actorRepository.save(actor);
        }
        return "redirect:/actors";
    }
}
