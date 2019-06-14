------------------------------------------------------------
-- Tables: roles
-- init
------------------------------------------------------------
insert into roles (role_name) values ('admin'), ('membre asso'), ('non membre');

------------------------------------------------------------
-- Tables: climb_user
-- init
------------------------------------------------------------
insert into climb_user (username, password, email, role_fk) values ('admin', MD5('admin'), 'admin@gmail.com', 1);


------------------------------------------------------------
-- Tables: rating & grade
-- init
------------------------------------------------------------

insert into rating (name) values ('facile'), ('perfectionnement'), ('difficile'), ('très difficile'), ('très haut niveau');
insert into grade (name, rating_fk) values ('3', 1), ('3+', 1), ('4a', 1), ('4b', 1), ('4c', 1), ('5a', 1);
insert into grade (name, rating_fk) values ('5b', 2), ('5c', 2), ('6a', 2), ('6a+', 2);
insert into grade (name, rating_fk) values ('6b', 3), ('6b+', 3), ('6c', 3), ('6c+', 3);
insert into grade (name, rating_fk) values ('7a', 4), ('7a+', 4), ('7b', 4), ('7b+', 4), ('7c', 4), ('7c+', 4);
insert into grade (name, rating_fk) values ('8a', 5), ('8a+', 5), ('8b', 5), ('8b+', 5), ('9a', 5), ('9a+', 5), ('9b', 5);