insert into CLIENT(email,nom,prenom,telephone,adresse,code_postale,ville,pays)
values('teliwal@gmail.com','telly','Diallo','+33752031094','38 rue Saint Roch','31400','Toulouse','France');
insert into CLIENT(email,nom,prenom,telephone,adresse,code_postale,ville,pays)
values('sobhe@gmail.com','Sobhe','Golle','+33752031094','23 rue da la Riche','31400','Toulouse','France');

insert into TRANSFERT(code,date_transfert,montant_euros,taux,frais,client_id,etat_transfert,beneficiaire_id)
values('1298HB',sysdate,100,0.23,1000,'sobhe@gmail.com','ENCOURS',1);

insert into GERANT(email,nom,prenom,adresse,code,telephone,ville)
values('sobhe1@gmail.com','Kikala','Sare','Cosa','12345678','+224622001122','Conakry');
insert into GERANT(email,nom,prenom,adresse,code,telephone,ville)
values('sobhe2@gmail.com','Fiya','Hollo','Kipe','12345678','+224622001121','Conakry');

insert into ADMIN(email,nom,prenom,telephone,adresse,ville,pays)
values('admin@gmail.com','BAH','Abdou','0606060606','1 rue du Bonheur','Bomboli','France');

insert into BOUTIQUE(nom,ville,description)
values('Cosa1','Conakry','La boutique de Cosa');
insert into BOUTIQUE(nom,ville,description)
values('Kipe1','Conakry','La boutique de Kipe');
insert into BOUTIQUE(nom,ville,description)
values('Fiya Hollo','Mamou','La boutique de petel');

insert into BENEFICIAIRE(id,nom,prenom,telephone,client_id)
values(0,'Bah','Mariam','623-09-76-12','teliwal@gmail.com');
insert into BENEFICIAIRE(id,nom,prenom,telephone,client_id)
values(1,'Fiya','Hollo','623-09-76-13','sobhe@gmail.com');
insert into BENEFICIAIRE(id,nom,prenom,telephone,client_id)
values(2,'Noh','Feti','623-09-76-14','sobhe1@gmail.com');

insert into CONNEXION(email,password,active,role)
values('teliwal@gmail.com','$2a$10$FXh5s2hFiU9RbOkF660AEOj87u5pI1zCreIFRUjrSDUttfYysYS0q','1','CLIENT');
insert into CONNEXION(email,password,active,role)
values('sobhe@gmail.com','$2a$10$FXh5s2hFiU9RbOkF660AEOj87u5pI1zCreIFRUjrSDUttfYysYS0q','1','CLIENT');
insert into CONNEXION(email,password,active,role)
values('sobhe1@gmail.com','$2a$10$FXh5s2hFiU9RbOkF660AEOj87u5pI1zCreIFRUjrSDUttfYysYS0q','1','GERANT');
insert into CONNEXION(email,password,active,role)
values('sobhe2@gmail.com','$2a$10$FXh5s2hFiU9RbOkF660AEOj87u5pI1zCreIFRUjrSDUttfYysYS0q','1','GERANT');
insert into CONNEXION(email,password,active,role)
values('admin@gmail.com','$2a$10$FXh5s2hFiU9RbOkF660AEOj87u5pI1zCreIFRUjrSDUttfYysYS0q','1','ADMIN');

insert into PARAMETRE(id,taux,frais) values(0,10600, 0.05);