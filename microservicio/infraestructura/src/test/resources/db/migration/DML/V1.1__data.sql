insert into usuario(nombre,clave,fecha_creacion) values('test','1234',now());
insert into movie(name_movie,type_movie,lenght,rating) VALUES ('Joker','Comedy','1:40',4);
insert into movie_projector(date_movie_projector,hour_movie_projector,id_movie,id_cinema) VALUES (TO_DATE('05-09-2021','dd-MM-yyyy'),PARSEDATETIME('12:30', 'HH:MM'),1,1);
insert into movie_projector(date_movie_projector,hour_movie_projector,id_movie,id_cinema) VALUES (TO_DATE('29-09-2021','dd-MM-yyyy'),PARSEDATETIME('06:30', 'HH:MM'),1,1);
insert into seats(number_seat,available,id_movie_projector) VALUES('A1',1,1);
insert into seats(number_seat,available,id_movie_projector) VALUES('A2',1,1);
insert into seats(number_seat,available,id_movie_projector) VALUES('A3',1,1);
insert into seats(number_seat,available,id_movie_projector) VALUES('A4',1,1);
insert into seats(number_seat,available,id_movie_projector) VALUES('A5',1,1);
insert into cinema(name_cinema,place,price_function) VALUES('FLORIDA','ROBLEDO',15000.00);