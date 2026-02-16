package com.eremkin.films_demo.controller;

import com.eremkin.films_demo.model.Film;
import com.eremkin.films_demo.model.Actor;
import com.eremkin.films_demo.model.FilmActor;
import com.eremkin.films_demo.repository.FilmRepository;
import com.eremkin.films_demo.repository.ActorRepository;
import com.eremkin.films_demo.repository.FilmActorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/films")
public class FilmListController {

    private final FilmRepository filmRepository;
    private final ActorRepository actorRepository;
    private final FilmActorRepository filmActorRepository;

    public FilmListController(FilmRepository filmRepository,
                              ActorRepository actorRepository,
                              FilmActorRepository filmActorRepository) {
        this.filmRepository = filmRepository;
        this.actorRepository = actorRepository;
        this.filmActorRepository = filmActorRepository;
    }

    @GetMapping
    public String getFilms(Model model) {
        List<Actor> actors = actorRepository.findAll();

        // Преобразуем в DTO с минимальным набором полей
        List<Map<String, Object>> actorDtos = actors.stream()
                .map(a -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", a.getId());
                    map.put("name", a.getName());
                    return map;
                })
                .collect(Collectors.toList());

        ObjectMapper mapper = new ObjectMapper();
        String actorsJson = mapper.writeValueAsString(actorDtos); // Это уже корректный JS-массив

        model.addAttribute("films", filmRepository.findAll());
        model.addAttribute("actors", actors);
        model.addAttribute("actorsJson", actorsJson);

        return "filmList";
    }


    @PostMapping("/add")
    public String addFilm(@RequestParam String title,
                          @RequestParam String director,
                          @RequestParam Integer year,
                          @RequestParam String description,
                          @RequestParam(required = false) List<Long> actorIds,
                          @RequestParam(required = false) List<String> roleNames) {

        // Создаём фильм
        Film film = new Film(title, director, year, description);
        filmRepository.save(film);

        // Если есть актеры — добавляем их
        if (actorIds != null && roleNames != null) {
            for (int i = 0; i < actorIds.size(); i++) {
                Long actorId = actorIds.get(i);
                String role = roleNames.get(i);
                Actor actor = actorRepository.findById(actorId).orElse(null);
                if (actor != null) {
                    FilmActor fa = new FilmActor(film, actor, role);
                    filmActorRepository.save(fa);
                }
            }
        }

        return "redirect:/films";
    }


    @PostMapping("/delete")
    public String deleteFilm(@RequestParam Long id) {
        filmRepository.deleteById(id);
        return "redirect:/films";
    }

    @PostMapping("/addActor")
    public String addActorToFilm(@RequestParam Long filmId,
                                 @RequestParam Long actorId,
                                 @RequestParam String roleName) {
        Film film = filmRepository.findById(filmId).orElse(null);
        Actor actor = actorRepository.findById(actorId).orElse(null);
        if (film != null && actor != null) {
            FilmActor fa = new FilmActor(film, actor, roleName);
            filmActorRepository.save(fa);
        }
        return "redirect:/films";
    }

    @PostMapping("/removeActor")
    public String removeActorFromFilm(@RequestParam Long filmActorId) {
        filmActorRepository.deleteById(filmActorId);
        return "redirect:/films";
    }
}
