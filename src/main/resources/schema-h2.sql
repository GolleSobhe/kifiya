create table TRANSFERT
(
	code varchar(36) not null,
	date_transfert datetime not null,
	date_validation datetime,
	montant_euros integer not null,
	montant bigint not null,
	taux double,
	prenom_beneficiaire varchar(80) not null,
	nom_beneficiare varchar(40) not null,
	telephone_beneficiaire varchar(40) not null,
	client_id varchar(60) not null,
	gerant_id varchar (50),
	primary key (code)
);

create table CLIENT
(
	email varchar(60),
	nom varchar(55),
	prenom varchar(100),
	password varchar(50),
	telephone varchar(30),
	adresse varchar(100),
	code_postale varchar(10),
	ville varchar(50),
	pays varchar(50),
	est_valide varchar(3),
	primary key (email)
);

create table GERANT
(
    login varchar(50),
    nom varchar(55),
	prenom varchar(100),
	password varchar(50),
	adresse varchar(100),
	code varchar(8),
	telephone varchar(30),
	ville varchar(50),
	primary key (login)
);

create table BOUTIQUE
(
	nom varchar(50),
	ville varchar(50),
	description varchar(150),
	primary key (nom)
);

