create table usuario (
 id int(11) not null auto_increment,
 nombre varchar(100) not null,
 clave varchar(45) not null,
 fecha_creacion datetime null,
 primary key (id)
);


create TABLE IF NOT EXISTS movie(
	id_movie int AUTO_INCREMENT PRIMARY KEY,
    name_movie VARCHAR(60),
    type_movie VARCHAR(60),
    lenght VARCHAR(30),
    rating int
);

CREATE TABLE IF NOT EXISTS client_movies (
    id_client INT AUTO_INCREMENT PRIMARY KEY,
    name_client VARCHAR(30),
    number_client VARCHAR(30),
    email_client  VARCHAR(30)
);

CREATE TABLE IF NOT EXISTS ticket (
    id_ticket INT AUTO_INCREMENT PRIMARY KEY,
    id_client INT,
    amount DOUBLE,
    id_movie_projector int
);

CREATE TABLE IF NOT EXISTS cinema(
  id_cinema INT AUTO_INCREMENT PRIMARY KEY,
  name_cinema VARCHAR(30),
  place VARCHAR(30),
  price_function double
);

CREATE TABLE IF NOT EXISTS movie_projector(
	id_movie_projector INT AUTO_INCREMENT PRIMARY KEY,
	date_movie_projector date,
    hour_movie_projector time,
    id_movie int,
    id_cinema int
);

CREATE TABLE IF NOT EXISTS seats(
   id_seats INT AUTO_INCREMENT PRIMARY KEY,
   number_seat VARCHAR(30),
   available int,
   id_ticket int,
   id_movie_projector int
);
