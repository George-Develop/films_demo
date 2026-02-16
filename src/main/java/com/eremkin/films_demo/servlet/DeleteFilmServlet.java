package com.eremkin.films_demo.servlet;

import com.eremkin.films_demo.repository.FilmRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;

public class DeleteFilmServlet extends HttpServlet {

    private final FilmRepository filmRepository;

    public DeleteFilmServlet(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        filmRepository.deleteById(id);

        response.sendRedirect("/films");
    }
}
