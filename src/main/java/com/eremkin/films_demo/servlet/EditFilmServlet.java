package com.eremkin.films_demo.servlet;

import com.eremkin.films_demo.model.Film;
import com.eremkin.films_demo.repository.FilmRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

public class EditFilmServlet extends HttpServlet {

    private final FilmRepository filmRepository;

    public EditFilmServlet(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        Film film = filmRepository.findById(id).orElseThrow();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h1>Edit Film</h1>");

        out.println("<form method='post'>");
        out.println("<input type='hidden' name='id' value='" + film.getId() + "'/>");
        out.println("Title: <input name='title' value='" + film.getTitle() + "'/><br>");
        out.println("Director: <input name='director' value='" + film.getDirector() + "'/><br>");
        out.println("Year: <input name='year' value='" + film.getReleaseYear() + "'/><br>");
        out.println("Description:<br>");
        out.println("<textarea name='description'>" + film.getDescription() + "</textarea><br>");
        out.println("<button type='submit'>Save</button>");
        out.println("</form>");

        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        Film film = filmRepository.findById(id).orElseThrow();

        film.setTitle(request.getParameter("title"));
        film.setDirector(request.getParameter("director"));
        film.setReleaseYear(Integer.parseInt(request.getParameter("year")));
        film.setDescription(request.getParameter("description"));

        filmRepository.save(film);

        response.sendRedirect("/films");
    }
}
