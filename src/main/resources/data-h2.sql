insert into CLIENT(email,nom,prenom,password,telephone,adresse,code_postale,ville,pays,est_valide)
values('teliwal@gmail.com','telly','Diallo','pasword','+33752031094','38 rue Saint Roch','31400','Toulouse','France','OUI');
insert into CLIENT(email,nom,prenom,password,telephone,adresse,code_postale,ville,pays,est_valide)
values('sobhe@gmail.com','Sobhe','Golle','pasword','+33752031094','23 rue da la Riche','31400','Toulouse','France','NON');

insert into GERANT(login,nom,prenom,password,adresse,code,telephone,ville)
values('myLogin','Kikala','Sare','password','Cosa','12345678','+224622001122','Conakry');
insert into GERANT(login,nom,prenom,password,adresse,code,telephone,ville)
values('gerant','Fiya','Hollo','password','Kipe','12345678','+224622001121','Conakry');

insert into BOUTIQUE(nom,ville,description)
values('Cosa1','Conakry','La boutique de Cosa');
insert into BOUTIQUE(nom,ville,description)
values('Kipe1','Conakry','La boutique de Kipe');

insert into ADMIN(email,nom,prenom,password,telephone,adresse,ville,pays,est_valide)
values('admin@gmail.com','BAH','Abdou','admin','0606060606','1 rue du Bonheur','Bomboli','France','YES');