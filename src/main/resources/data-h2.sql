insert into CLIENT(email,nom,prenom,password,telephone,adresse,code_postale,ville,pays,est_valide)
values('teliwal@gmail.com','telly','Diallo','password','+33752031094','38 rue Saint Roch','31400','Toulouse','France','OUI');
insert into CLIENT(email,nom,prenom,password,telephone,adresse,code_postale,ville,pays,est_valide)
values('sobhe@gmail.com','Sobhe','Golle','password','+33752031094','23 rue da la Riche','31400','Toulouse','France','NON');

insert into GERANT(login,nom,prenom,password,adresse,code,telephone,ville)
values('myLogin','Kikala','Sare','password','Cosa','12345678','+224622001122','Conakry');
insert into GERANT(login,nom,prenom,password,adresse,code,telephone,ville)
values('gerant','Fiya','Hollo','password','Kipe','12345678','+224622001121','Conakry');

insert into BOUTIQUE(nom,ville,description)
values('Cosa1','Conakry','La boutique de Cosa');
insert into BOUTIQUE(nom,ville,description)
values('Kipe1','Conakry','La boutique de Kipe');
insert into BOUTIQUE(nom,ville,description)
values('Fiya Hollo','Mamou','La boutique de petel');

insert into ADMIN(email,nom,prenom,password,telephone,adresse,ville,pays,est_valide)
values('admin@gmail.com','BAH','Abdou','password','0606060606','1 rue du Bonheur','Bomboli','France','YES');

insert into BENEFICIAIRE(nom,prenom,telephone,client_id)
values('Bah','Mariam','+224623097612','teliwal@gmail.com');
insert into BENEFICIAIRE(nom,prenom,telephone,client_id)
values('Fiya','Hollo','+224623097612','sobhe@gmail.com');
insert into BENEFICIAIRE(nom,prenom,telephone,client_id)
values('Noh','Feti','+224623097612','sobhe1@gmail.com');

insert into CONNEXION(login,password,role)
values('teliwal@gmail.com','password','CLIENT');
insert into CONNEXION(login,password,role)
values('myLogin','password','GERANT');
insert into CONNEXION(login,password,role)
values('admin@gmail.com','password','ADMIN');

