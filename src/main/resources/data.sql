CREATE TABLE film
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    title        VARCHAR NOT NULL,
    director     VARCHAR NOT NULL,
    release_year INTEGER NOT NULL,
    description  VARCHAR(2000) NOT NULL
);

CREATE TABLE actor
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE film_actor
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    film_id  BIGINT NOT NULL,
    actor_id BIGINT NOT NULL,
    role_name VARCHAR NOT NULL,
    CONSTRAINT fk_film FOREIGN KEY (film_id) REFERENCES film(id) ON DELETE CASCADE,
    CONSTRAINT fk_actor FOREIGN KEY (actor_id) REFERENCES actor(id) ON DELETE CASCADE
);

INSERT INTO film (title, director, release_year, description) VALUES
('Inception', 'Christopher Nolan', 2010, 'A thief who steals corporate secrets through dream-sharing technology.'),
('The Matrix', 'Lana Wachowski, Lilly Wachowski', 1999, 'A hacker discovers the world is a simulation.'),
('Interstellar', 'Christopher Nolan', 2014, 'A team travels through a wormhole in space.');

INSERT INTO actor (name) VALUES
('Leonardo DiCaprio'),
('Joseph Gordon-Levitt'),
('Ellen Page'),
('Keanu Reeves'),
('Laurence Fishburne'),
('Carrie-Anne Moss'),
('Matthew McConaughey'),
('Anne Hathaway');

INSERT INTO film_actor (film_id, actor_id, role_name) VALUES

(1, 1, 'Dom Cobb'),
(1, 2, 'Arthur'),
(1, 3, 'Ariadne'),

(2, 4, 'Neo'),
(2, 5, 'Morpheus'),
(2, 6, 'Trinity'),

(3, 7, 'Cooper'),
(3, 8, 'Brand');
