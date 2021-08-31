create table usuario (
 id int(11) not null auto_increment,
 nombre varchar(100) not null,
 clave varchar(45) not null,
 fecha_creacion datetime null,
 primary key (id)
);


create TABLE movie(
	id_movie INT AUTO_INCREMENT PRIMARY KEY,
    name_movie VARCHAR(60),
    type_movie VARCHAR(60),
    lenght VARCHAR(30),
    rating int
);