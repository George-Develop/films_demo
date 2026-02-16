package com.eremkin.films_demo.config;

import com.eremkin.films_demo.repository.FilmRepository;
import com.eremkin.films_demo.servlet.*;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean<FilmListServlet> filmListServlet(FilmRepository repository) {
        return new ServletRegistrationBean<>(
                new FilmListServlet(repository), "/films");
    }
    @Bean
    public ServletRegistrationBean<AddFilmServlet> addFilm(FilmRepository repo) {
        return new ServletRegistrationBean<>(new AddFilmServlet(repo), "/add");
    }

    @Bean
    public ServletRegistrationBean<DeleteFilmServlet> deleteFilm(FilmRepository repo) {
        return new ServletRegistrationBean<>(new DeleteFilmServlet(repo), "/delete");
    }

    @Bean
    public ServletRegistrationBean<EditFilmServlet> editFilm(FilmRepository repo) {
        return new ServletRegistrationBean<>(new EditFilmServlet(repo), "/edit");
    }

}
