insert into science_area (name) values ('Drustvene nauke');
insert into science_area (name) values ('Prirodne nauke');
insert into science_area (name) values ('Medicina');
insert into science_area (name) values ('Tehnoloske nauke');

insert into payment_method (name) values ('Bitcoin');
insert into payment_method (name) values ('PayPal');
insert into payment_method (name) values ('Card');

insert into role (name) values ('ROLE_USER');
insert into role (name) values ('ROLE_REVIEWER');
insert into role (name) values ('ROLE_AUTOR');
insert into role (name) values ('ROLE_EDITOR');
insert into role (name) values ('ROLE_ADMIN');


insert into user (discriminator, name, surname, email, username, password, city, state, is_activated, title, admin_confirmation)  values ('U','Marko', 'Markovic', 'marko@gmail.com', 'marko', '$2a$10$mLgw3ovM1W0Lun/3MZYVA.a0hZfjPBTs/QjLz97o.Prjrs87YMNHq', 'Minhen', 'Nemacka', true, NULL, NULL );
insert into user_roles(user_id, roles_id) values (1,1);

-- Recenzenti --
insert into user(discriminator, name, surname, email, username, password, city, state, is_activated, title, admin_confirmation) values ('R','Pera', 'Peric', 'pera@gmail.com','pera','$2a$10$mLgw3ovM1W0Lun/3MZYVA.a0hZfjPBTs/QjLz97o.Prjrs87YMNHq', 'Novi Sad', 'Srbija', true , 'titula1', true );
insert into user_roles(user_id, roles_id) values (2,2); --Recenzent
insert into user_areas_of_interest (user_id, areas_of_interest_id) values (2, 2); --Prirodne nauke
insert into user_areas_of_interest (user_id, areas_of_interest_id) values (2, 4); --Tehnolose nauke

insert into user(discriminator, name, surname, email, username, password, city, state, is_activated, title, admin_confirmation) values ('R','Jovan', 'Jovanovic', 'jovan@gmail.com','jovan','$2a$10$mLgw3ovM1W0Lun/3MZYVA.a0hZfjPBTs/QjLz97o.Prjrs87YMNHq', 'Atina', 'Grcka', true , 'titula2', true );
insert into user_roles(user_id, roles_id) values (3,2); --Recenzent
insert into user_areas_of_interest (user_id, areas_of_interest_id) values (3, 2); --Prirodne nauke

insert into user(discriminator, name, surname, email, username, password, city, state, is_activated, title, admin_confirmation) values ('R','Marija', 'Maric', 'marija@gmail.com','marija','$2a$10$mLgw3ovM1W0Lun/3MZYVA.a0hZfjPBTs/QjLz97o.Prjrs87YMNHq', 'Rim', 'Italija', true , 'titula3', true );
insert into user_roles(user_id, roles_id) values (4,2); --Recenzent
insert into user_areas_of_interest (user_id, areas_of_interest_id) values (4, 1); --Drustvene nauke
insert into user_areas_of_interest (user_id, areas_of_interest_id) values (4, 3); --Medicina

--  Urednici  --
insert into user (discriminator, name, surname, email, username, password, city, state, is_activated, title, admin_confirmation)  values ('E','Jelena', 'Jelenic', 'jelena@gmail.com', 'jelena', '$2a$10$mLgw3ovM1W0Lun/3MZYVA.a0hZfjPBTs/QjLz97o.Prjrs87YMNHq', 'Beograd', 'Srbija', true , "Neka titula", true );
insert into user_roles(user_id, roles_id) values (5,4);
insert into user_areas_of_interest (user_id, areas_of_interest_id) values (5, 2); --Prirodne nauke

insert into user (discriminator, name, surname, email, username, password, city, state, is_activated, title, admin_confirmation)  values ('E','Milica', 'Milic', 'milica@gmail.com', 'milica', '$2a$10$mLgw3ovM1W0Lun/3MZYVA.a0hZfjPBTs/QjLz97o.Prjrs87YMNHq', 'Beograd', 'Srbija', true , "Neka titula", true );
insert into user_roles(user_id, roles_id) values (6,4);
insert into user_areas_of_interest (user_id, areas_of_interest_id) values (6, 2); --Prirodne nauke

-- Admin --
insert into user (discriminator, name, surname, email, username, password, city, state, is_activated, title, admin_confirmation)  values ('U','Danica', 'Markovic', 'danica@gmail.com', 'danica', '$2a$10$mLgw3ovM1W0Lun/3MZYVA.a0hZfjPBTs/QjLz97o.Prjrs87YMNHq', 'Novi Sad', 'Srbija', true , "Neka titula", NULL );
insert into user_roles(user_id, roles_id) values (6,5);

