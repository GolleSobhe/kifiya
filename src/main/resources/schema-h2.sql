create table users
(
  nom varchar(30) not null,
  prenom varchar(30) not null,
  email varchar(30) not null,
  pays varchar(30),
  ville varchar(30) ,
  telephone varchar(255) ,
  adresse varchar(30) ,
  password varchar(100),
  primary key(email)
);
