package com.eremkin.films_demo.servlet;

import com.eremkin.films_demo.model.Film;
import com.eremkin.films_demo.repository.FilmRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FilmListServlet extends HttpServlet {

    private final FilmRepository filmRepository;

    public FilmListServlet(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        List<Film> films = filmRepository.findAll();

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='ru'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Films Catalog</title>");
        out.println("<link rel='stylesheet' href='/style.css'>");
        out.println("</head>");
        out.println("<body>");

        out.println("<header class='header'>");
        out.println("<div class='container'>");
        out.println("<h1 class='logo'>🎬 Films Catalog</h1>");
        out.println("<p class='subtitle'>Рандомные фильмы</p>");
        out.println("</div>");
        out.println("</header>");

        out.println("<main class='container'>");

        out.println("<div class='catalog'>");

        for (Film film : films) {
            out.println("<div class='film-card'>");

            out.println("<div class='card-header'>");
            out.println("<h2>" + film.getTitle() + "</h2>");
            out.println("<span class='year'>" + film.getReleaseYear() + "</span>");
            out.println("</div>");

            out.println("<p class='director'>Director: " + film.getDirector() + "</p>");
            out.println("<p class='description'>" + film.getDescription() + "</p>");

            out.println("<form method='post' action='/delete'>");
            out.println("<input type='hidden' name='id' value='" + film.getId() + "'>");
            out.println("<button type='submit'>Delete</button>");
            out.println("</form>");

            out.println("</div>");
        }

        out.println("<div class='film-card'>");
        out.println("<h2>Add Film</h2>");
        out.println("<form method='post' action='/add'>");
        out.println("<input name='title' placeholder='Title' required><br><br>");
        out.println("<input name='director' placeholder='Director' required><br><br>");
        out.println("<input name='year' type='number' placeholder='Year' required><br><br>");
        out.println("<textarea name='description' placeholder='Description'></textarea><br><br>");
        out.println("<button type='submit'>Add</button>");
        out.println("</form>");
        out.println("</div>");

        out.println("</div>");
        out.println("</main>");

        out.println("<footer class='footer'>");
        out.println("<p>© 2026 Eremkin Films Demo</p>");
        out.println("</footer>");

        out.println("</body>");
        out.println("</html>");
    }
}
