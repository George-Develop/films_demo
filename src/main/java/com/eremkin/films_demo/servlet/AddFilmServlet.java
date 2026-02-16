package com.eremkin.films_demo.servlet;

import com.eremkin.films_demo.model.Film;
import com.eremkin.films_demo.repository.FilmRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;

public class AddFilmServlet extends HttpServlet {

    private final FilmRepository filmRepository;

    public AddFilmServlet(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String title = request.getParameter("title");
        String director = request.getParameter("director");
        Integer year = Integer.parseInt(request.getParameter("year"));
        String description = request.getParameter("description");

        Film film = new Film(title, director, year, description);
        filmRepository.save(film);

        response.sendRedirect("/films");
    }
}
