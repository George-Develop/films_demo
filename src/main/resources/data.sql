create table film
(
    title        VARCHAR not null,
    director     VARCHAR not null,
    RELEASE_YEAR INTEGER not null,
    description  VARCHAR not null
);
INSERT INTO film (title, director, RELEASE_YEAR, description) VALUES
                                                          ('Inception', 'Christopher Nolan', 2010, 'A thief who steals corporate secrets through dream-sharing technology.'),
                                                          ('The Matrix', 'Lana Wachowski, Lilly Wachowski', 1999, 'A hacker discovers the world is a simulation.'),
                                                          ('Interstellar', 'Christopher Nolan', 2014, 'A team travels through a wormhole in space.');
