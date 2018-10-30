#drop database mbsystem;
use mbsystem;
#drop table if exists MOVIE;
#drop table if exists THEATRE;
#drop table if exists MSHOW;

/*
create table movie_ticket_db.movie
(
	movie_id int,
	name varchar(200),
	duration int
);

alter table movie_ticket_db.movie add 
(
	IMDB_rating int,
    screening_price double 
);*/
#use movie_ticket_db;
#rename table movie to movies_info
#drop database movie_ticket_db
#insert into movies_info(movie_id, name) values(1,'Wolverine');
#insert into movies_info values(1,'Thor',default)
#update movies_info set duration=6 where name='Thor'
#commit;
#select * from theatre inner join mshow, hall, movie where theatre.tshowId=mshow.showId and theatre.hallInfo=hall.hallId and mshow.showId = movie.mId; 
#insert into customer values(32815,ldkamsdl,5263256956,'daslkdmskal@lsakamdlask',dklasmd,laskdlkasmd);
#select * from theatre where tName='Aplex1';
#select * from theatre inner join mshow, hall, movie where theatre.tshowId=mshow.showId and theatre.hallInfo=hall.hallId and mshow.showId = movie.mId and mName='Movie4'; 
select * from theatre inner join mshow, hall, movie where theatre.tshowId=mshow.showId and theatre.hallInfo=hall.hallId and mshow.showId = movie.mId and mName='Movie1' and tName='Aplex1'
