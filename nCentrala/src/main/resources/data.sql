insert into science_area (name) values ('Drustvene nauke');
insert into science_area (name) values ('Prirodne nauke');
insert into science_area (name) values ('Medicina');
insert into science_area (name) values ('Tehnoloske nauke');

insert into payment_method (name) values ('Bitcoin');
insert into payment_method (name) values ('PayPal');
insert into payment_method (name) values ('Card');

insert into role (name) values ('ROLE_USER');
insert into role (name) values ('ROLE_REVIEWER');
insert into role (name) values ('ROLE_AUTHOR');
insert into role (name) values ('ROLE_EDITOR');
insert into role (name) values ('ROLE_ADMIN');

--sifre svuda 123
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
insert into user (discriminator, name, surname, email, username, password, city, state, is_activated, title, admin_confirmation)  values ('E','Jelena', 'Jelenic', 'jelenaEditor@gmail.com', 'jelena', '$2a$10$mLgw3ovM1W0Lun/3MZYVA.a0hZfjPBTs/QjLz97o.Prjrs87YMNHq', 'Beograd', 'Srbija', true , "Neka titula", true);
insert into user_roles(user_id, roles_id) values (5,4);
insert into user_areas_of_interest (user_id, areas_of_interest_id) values (5, 2); --Prirodne nauke
insert into user_areas_of_interest (user_id, areas_of_interest_id) values (5, 1); --Drustvene nauke

insert into user (discriminator, name, surname, email, username, password, city, state, is_activated, title, admin_confirmation, journal_id)  values ('E','Milica', 'Milic', 'danicaam13@gmail.com', 'milica', '$2a$10$mLgw3ovM1W0Lun/3MZYVA.a0hZfjPBTs/QjLz97o.Prjrs87YMNHq', 'Beograd', 'Srbija', true , "Neka titula", true, 1);
insert into user_roles(user_id, roles_id) values (6,4);
insert into user_areas_of_interest (user_id, areas_of_interest_id) values (6, 1); --Drustvene nauke
insert into user_areas_of_interest (user_id, areas_of_interest_id) values (6, 2); --Prirodne nauke

-- Admin --
insert into user (discriminator, name, surname, email, username, password, city, state, is_activated, title, admin_confirmation)  values ('U','Danica', 'Markovic', 'danica@gmail.com', 'danica', '$2a$10$mLgw3ovM1W0Lun/3MZYVA.a0hZfjPBTs/QjLz97o.Prjrs87YMNHq', 'Novi Sad', 'Srbija', true , "Neka titula", NULL );
insert into user_roles(user_id, roles_id) values (7,5);

--Autor --
insert into user (discriminator, name, surname, email, username, password, city, state, is_activated, title, admin_confirmation)  values ('U','Nikolina', 'Nikolic', 'nikolinaAuthor@gmail.com', 'nikolina', '$2a$10$mLgw3ovM1W0Lun/3MZYVA.a0hZfjPBTs/QjLz97o.Prjrs87YMNHq', 'Novi Sad', 'Srbija', true , "Neka titula", NULL );
insert into user_roles(user_id, roles_id) values (8,3);

-- Casopisi --
insert into journal(is_activated, is_open_access, issn, name, main_editor_id, price) values (true, true, '1111-2222', 'Nacionalna geografija' ,5,10);
insert into journal_science_areas (journal_id,science_areas_id) values (1,1); -- drustvene nauke
insert into journal_science_areas (journal_id,science_areas_id) values (1,2); -- prirodne nauke
insert into journal_payment_methods (journal_id, payment_methods_id) values (1, 2); --paypal

insert into journal(is_activated, is_open_access, issn, name, main_editor_id, price) values (true, false, '1111-3333', 'Hello' ,5, 5);
insert into journal_science_areas (journal_id,science_areas_id) values (2,1); -- drustvene nauke
insert into journal_science_areas (journal_id,science_areas_id) values (2,2); -- prirodne nauke
insert into journal_payment_methods (journal_id, payment_methods_id) values (1, 1); --bitcoin


--recenzenti casopisa --
insert into user_journal_review(reviewer_id, journal_review_id) values (2,1); --nacionalna geografija casopis
insert into user_journal_review(reviewer_id, journal_review_id) values (3,1);
insert into user_journal_review(reviewer_id, journal_review_id) values (4,1);

insert into user_journal_review(reviewer_id, journal_review_id) values (3,2); -- hello casopis --
insert into user_journal_review(reviewer_id, journal_review_id) values (4,2)

-- radovi --
--insert into article (accepted, apstract, key_words,pdf_path, title, journal_id, science_area_id) values (1,'apstrakt','as' ,'D:\MASTER\UDD\PROJEKAT\Naucna_centrala\nCentrala\files\ČLANAK 1.pdf', 'Article 1',1,2);

-- recenzije --
--insert into review (authorc,editorc,recommendation, article_id, user_id) values ('pera za autora','pera za editora','acceptSmall',1,2);
--insert into review (authorc,editorc,recommendation, article_id, user_id) values ('marija za autora','marija za editora','accept',1,4);

--coathor--







