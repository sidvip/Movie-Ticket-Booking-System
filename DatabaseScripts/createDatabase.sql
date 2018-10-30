drop database if exists mbsystem;
create database mbsystem;
use mbsystem;

drop table if exists MOVIE;
create table MOVIE 
  ( mId int not null,
	mName varchar(250) not null,
    directorName varchar(100),
    IMDBRating int,
    language varchar(2),
    primary key (mId)
    );

drop table if exists MSHOW;
create table MSHOW
   ( showId int not null,
     showDate text not null,
     showTime text not null,
     duration float,
     showPrice double,
     primary key (showId),
     foreign key (showId) references MOVIE(mId)
    );
    
drop table if exists HALL;
create table HALL
	( hallId int not null,
	  maxnumOfSeats int,
      seatClass1 text,
	  seatNumbers1 int,
      seatClass1Price float,
      seatClass2 text,
	  seatNumbers2 int,
      seatClass2Price float,
      seatClass3 text,
	  seatNumbers3 int,
	  seatClass3Price float,
      primary key (hallId)
    );

drop table if exists THEATRE;
create table THEATRE
  ( tshowId int not null,
	tName varchar(200),
    tAddress text,
    numberOfHalls int,
    hallInfo int,
	primary key (tshowId),
    foreign key (tshowId) references MSHOW(showId),
	foreign key (hallInfo) references HALL(hallId)
  );
    
drop table if exists CUSTOMER;
create table CUSTOMER
   ( cName text not null,
     mobileNum varchar(10) not null,
     emailId text,
     userName text,
     userPass text,
     primary key (mobileNum)
	);
    
   
drop table if exists BOOKTICEKT;
create table BOOKTICKET
   (
    custMobNum varchar(10) not null,
	movieName text not null,
    theatreName text not null,
    seatClass text not null
   );