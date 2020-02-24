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
insert into user (discriminator, name, surname, email, username, password, city, state, is_activated, title, admin_confirmation, longit, lat)  values ('U','Marko', 'Markovic', 'marko@gmail.com', 'marko', '$2a$10$mLgw3ovM1W0Lun/3MZYVA.a0hZfjPBTs/QjLz97o.Prjrs87YMNHq', 'Novi Sad', 'Srbija', true, NULL, NULL, 20.4568974, 44.8178131);
insert into user_roles(user_id, roles_id) values (1,1);

-- Recenzenti --
insert into user(discriminator, name, surname, email, username, password, city, state, is_activated, title, admin_confirmation, longit, lat) values ('R','Petar', 'Peric', 'pera@gmail.com','pera','$2a$10$mLgw3ovM1W0Lun/3MZYVA.a0hZfjPBTs/QjLz97o.Prjrs87YMNHq', 'Beograd', 'Srbija', true , 'titula1', true , 20.460480, 44.815071);
insert into user_roles(user_id, roles_id) values (2,2); --Recenzent
insert into user_areas_of_interest (user_id, areas_of_interest_id) values (2, 1); --Drustvene nauke
insert into user_areas_of_interest (user_id, areas_of_interest_id) values (2, 4); --Tehnolose nauke

insert into user(discriminator, name, surname, email, username, password, city, state, is_activated, title, admin_confirmation, longit, lat) values ('R','Jovan', 'Jovanovic', 'jovan@gmail.com','jovan','$2a$10$mLgw3ovM1W0Lun/3MZYVA.a0hZfjPBTs/QjLz97o.Prjrs87YMNHq', 'Zagreb', 'Hrvatska', true , 'titula2', true , 15.966568, 45.815399);
insert into user_roles(user_id, roles_id) values (3,2); --Recenzent
insert into user_areas_of_interest (user_id, areas_of_interest_id) values (3, 4); --Tehnolose nauke

insert into user(discriminator, name, surname, email, username, password, city, state, is_activated, title, admin_confirmation, longit, lat) values ('R','Marija', 'Maric', 'marija@gmail.com','marija','$2a$10$mLgw3ovM1W0Lun/3MZYVA.a0hZfjPBTs/QjLz97o.Prjrs87YMNHq', 'Skoplje', 'Makedonija', true , 'titula3', true , 21.43141, 41.99646);
insert into user_roles(user_id, roles_id) values (4,2); --Recenzent
insert into user_areas_of_interest (user_id, areas_of_interest_id) values (4, 1); --Drustvene nauke

insert into user(discriminator, name, surname, email, username, password, city, state, is_activated, title, admin_confirmation, longit, lat) values ('R','Petar', 'Petrovic', 'pera@gmail.com','petar','$2a$10$mLgw3ovM1W0Lun/3MZYVA.a0hZfjPBTs/QjLz97o.Prjrs87YMNHq', 'Pariz', 'Francuska', true , 'titula1', true , 2.295138, 48.876965);
insert into user_roles(user_id, roles_id) values (5,2); --Recenzent
insert into user_areas_of_interest (user_id, areas_of_interest_id) values (5, 2); --Prirodne nauke
insert into user_areas_of_interest (user_id, areas_of_interest_id) values (5, 4); --Tehnolose nauke

insert into user(discriminator, name, surname, email, username, password, city, state, is_activated, title, admin_confirmation, longit, lat) values ('R','John', 'Dark', 'john@gmail.com','john','$2a$10$mLgw3ovM1W0Lun/3MZYVA.a0hZfjPBTs/QjLz97o.Prjrs87YMNHq', 'New york', 'USA', true , 'titula1', true ,-73.935242, 40.730610);
insert into user_roles(user_id, roles_id) values (6,2); --Recenzent
insert into user_areas_of_interest (user_id, areas_of_interest_id) values (6, 2); --Prirodne nauke
insert into user_areas_of_interest (user_id, areas_of_interest_id) values (6, 1); --Drustvene nauke
insert into user_areas_of_interest (user_id, areas_of_interest_id) values (6, 4); --Tehnolose nauke

insert into user(discriminator, name, surname, email, username, password, city, state, is_activated, title, admin_confirmation, longit, lat) values ('R','Momčilo', 'Momčilović', 'momcilo@gmail.com','momcilo','$2a$10$mLgw3ovM1W0Lun/3MZYVA.a0hZfjPBTs/QjLz97o.Prjrs87YMNHq', 'Shanghai', 'China', true , 'titula1', true ,121.469170, 31.224361);
insert into user_roles(user_id, roles_id) values (7,2); --Recenzent
insert into user_areas_of_interest (user_id, areas_of_interest_id) values (7, 3); --Medicina nauke


--  Urednici  --
insert into user (discriminator, name, surname, email, username, password, city, state, is_activated, title, admin_confirmation, longit, lat)  values ('E','Jelena', 'Jelenic', 'jelenaEditor@gmail.com', 'jelena', '$2a$10$mLgw3ovM1W0Lun/3MZYVA.a0hZfjPBTs/QjLz97o.Prjrs87YMNHq', 'Novi Sad', 'Srbija', true , "Neka titula", true , 20.4568974, 44.8178131);
insert into user_roles(user_id, roles_id) values (8,4);
insert into user_areas_of_interest (user_id, areas_of_interest_id) values (8, 2); --Prirodne nauke
insert into user_areas_of_interest (user_id, areas_of_interest_id) values (8, 1); --Drustvene nauke

insert into user (discriminator, name, surname, email, username, password, city, state, is_activated, title, admin_confirmation, journal_id, longit, lat)  values ('E','Milica', 'Milic', 'danicaam13@gmail.com', 'milica', '$2a$10$mLgw3ovM1W0Lun/3MZYVA.a0hZfjPBTs/QjLz97o.Prjrs87YMNHq', 'Novi Sad', 'Srbija', true , "Neka titula", true, 1 , 20.4568974, 44.8178131);
insert into user_roles(user_id, roles_id) values (9,4);
insert into user_areas_of_interest (user_id, areas_of_interest_id) values (9, 1); --Drustvene nauke
insert into user_areas_of_interest (user_id, areas_of_interest_id) values (9, 2); --Prirodne nauke

-- Admin --
insert into user (discriminator, name, surname, email, username, password, city, state, is_activated, title, admin_confirmation, longit, lat)  values ('U','Danica', 'Markovic', 'danica@gmail.com', 'danica', '$2a$10$mLgw3ovM1W0Lun/3MZYVA.a0hZfjPBTs/QjLz97o.Prjrs87YMNHq', 'Novi Sad', 'Srbija', true , "Neka titula", NULL , 20.4568974, 44.8178131);
insert into user_roles(user_id, roles_id) values (10,5);

--Autor --
insert into user (discriminator, name, surname, email, username, password, city, state, is_activated, title, admin_confirmation, longit, lat)  values ('U','Nikolina', 'Nikolic', 'nikolinaAuthor@gmail.com', 'nikolina', '$2a$10$mLgw3ovM1W0Lun/3MZYVA.a0hZfjPBTs/QjLz97o.Prjrs87YMNHq', 'Novi Sad', 'Srbija', true , "Neka titula", NULL , 20.4568974, 44.8178131);
insert into user_roles(user_id, roles_id) values (11,3);

-- Casopisi --
insert into journal(is_activated, is_open_access, issn, name, main_editor_id, price) values (true, true, '1111-2222', 'Nacionalna geografija' ,8,10);
insert into journal_science_areas (journal_id,science_areas_id) values (1,1); -- drustvene nauke
insert into journal_science_areas (journal_id,science_areas_id) values (1,2); -- prirodne nauke
insert into journal_science_areas (journal_id,science_areas_id) values (1,3); -- tehnoloske nauke
insert into journal_science_areas (journal_id,science_areas_id) values (1,4); -- Medicina 
insert into journal_payment_methods (journal_id, payment_methods_id) values (1, 2); --paypal

insert into journal(is_activated, is_open_access, issn, name, main_editor_id, price) values (true, false, '1111-3333', 'Hello' ,8, 5);
insert into journal_science_areas (journal_id,science_areas_id) values (2,1); -- drustvene nauke
insert into journal_science_areas (journal_id,science_areas_id) values (2,2); -- prirodne nauke
insert into journal_payment_methods (journal_id, payment_methods_id) values (1, 1); --bitcoin


--recenzenti casopisa --
insert into user_journal_review(reviewer_id, journal_review_id) values (2,1); --nacionalna geografija casopis
insert into user_journal_review(reviewer_id, journal_review_id) values (3,1);
insert into user_journal_review(reviewer_id, journal_review_id) values (4,1);

insert into user_journal_review(reviewer_id, journal_review_id) values (3,2); -- hello casopis --
insert into user_journal_review(reviewer_id, journal_review_id) values (4,2);

-- radovi --
	--prihvaceni--
insert into article (accepted, apstract, key_words,pdf_path, title, journal_id, science_area_id, author_id) values (2,'neki apstrakt','tigrovi, zemlje, sila, Tajvan, Hong Kong, Južna Koreja, Sigapur' ,'D:\\MASTER\\UDD\\PROJEKAT\\Naucna_centrala\\nCentrala\\files\\AZIJSKI TIGROVI.pdf', 'Azijski tigrovi',1,2, 11);
insert into article (accepted, apstract, key_words,pdf_path, title, journal_id, science_area_id, author_id) values (2,'apstrakt','Podmornica, prevoz, voda, more, brod' ,'D:\\MASTER\\UDD\\PROJEKAT\\Naucna_centrala\\nCentrala\\files\\Podmornica.pdf', 'Podmornica',2,1, 11);
insert into article (accepted, apstract, key_words,pdf_path, title, journal_id, science_area_id, author_id) values (2,'apstrakt','grad, Hongkong, kolonija' ,'D:\\MASTER\\UDD\\PROJEKAT\\Naucna_centrala\\nCentrala\\files\\Hong Kong.pdf', 'Hong Kong',2,1, 11);
insert into article (accepted, apstract, key_words,pdf_path, title, journal_id, science_area_id, author_id) values (2,'aaaaaaaaaaaaaaa','vodozemac, voda, škrge' ,'D:\\MASTER\\UDD\\PROJEKAT\\Naucna_centrala\\nCentrala\\files\\Vodozemci.pdf', 'Vodozemci',1,1, 11);
insert into article (accepted, apstract, key_words,pdf_path, title, journal_id, science_area_id, author_id) values (2,'apstrakt ponovo','životinja, čovek, biće, snaga' ,'D:\\MASTER\\UDD\\PROJEKAT\\Naucna_centrala\\nCentrala\\files\\Najjača bića na svetu.pdf', 'Najjača bića na svetu',1,2, 11);
insert into article (accepted, apstract, key_words,pdf_path, title, journal_id, science_area_id, author_id) values (2,'апстракт','мачка, čovek, гепард, тигар' ,'D:\\MASTER\\UDD\\PROJEKAT\\Naucna_centrala\\nCentrala\\files\\Велике мачке.pdf', 'Велике мачке',1,2, 11);
insert into article (accepted, apstract, key_words,pdf_path, title, journal_id, science_area_id, author_id) values (2,'апстракт опет','прст, рука, кост' ,'D:\\MASTER\\UDD\\PROJEKAT\\Naucna_centrala\\nCentrala\\files\\Прст.pdf', 'Prsti',1,3, 11);
insert into article (accepted, apstract, key_words,pdf_path, title, journal_id, science_area_id, author_id) values (2,'apstrakt','Kina, grad, Hong Kong, Peking, Wuhan' ,'D:\\MASTER\\UDD\\PROJEKAT\\Naucna_centrala\\nCentrala\\files\\Gradovi kine.pdf', 'Gradovi kine',1,1, 11);


	--odbijeni--
insert into article (accepted, apstract, key_words,pdf_path, title, journal_id, science_area_id, author_id) values (3,'odbijeno','razlog, test, tigrovi, zemlja, gepard, zivotinja' ,'D:\\MASTER\\UDD\\PROJEKAT\\Naucna_centrala\\nCentrala\\files\\Odbijeni pdf.pdf', 'Neki rad',2,2, 11);
insert into article (accepted, apstract, key_words,pdf_path, title, journal_id, science_area_id, author_id) values (3,'apstrakt odbijenog','odbijen rad, zivotinja' ,'D:\\MASTER\\UDD\\PROJEKAT\\Naucna_centrala\\nCentrala\\files\\Odbijen rad.pdf', 'Slepo kucanje i tigrovi',1,1, 11); --mlt pretraga--

insert into article (accepted, apstract, key_words,pdf_path, title, journal_id, science_area_id, author_id) values (2,'neki apstrakt','tigrovi, zemlje, sila, Tajvan, Hong Kong, Južna Koreja, Sigapur' ,'D:\\MASTER\\UDD\\PROJEKAT\\Naucna_centrala\\nCentrala\\files\\AZIJSKI TIGROVI.pdf', 'Azijski tigrovi',2,2, 11); --isti rad, objavljen u drugom casopisu, za mlt search
	
-- recenzije --
--insert into review (authorc,editorc,recommendation, article_id, user_id) values ('pera za autora','pera za editora','acceptSmall',7,6); --John, Prsti, prihvacen 
insert into review (authorc,editorc,recommendation, article_id, user_id) values ('marija za autora','marija za editora','accept',1,4);
--insert into review (authorc,editorc,recommendation, article_id, user_id) values ('marija za autora','marija za editora','accept',6,6); -- John, velike macke
insert into review (authorc,editorc,recommendation, article_id, user_id) values ('marija za autora','marija za editora','accept',10,2); --pera, -> odbijen 
insert into review (authorc,editorc,recommendation, article_id, user_id) values ('marija za autora','marija za editora','accept',10,7); --momcilo, -> odbijen 
insert into review (authorc,editorc,recommendation, article_id, user_id) values ('marija za autora','marija za editora','accept',3,7); --momcilo, Hong Kong-> 

--coathor--
insert into coauthor(city, email, name, state,surname, article_id, longit, latt) values ('Subotica','coautor1@gmail.com', 'Popaj', 'Srbija', 'Peric',1,19.667587 ,46.100376);
insert into coauthor(city, email, name, state,surname, article_id, longit, latt) values ('Kragujevac', 'coautor2@gmail.com', 'Jovan', 'Srbija', 'Jovanovic', 2, 20.91667, 44.01667);
insert into coauthor(city, email, name, state,surname, article_id, longit, latt) values ('Kragujevac', 'coautor2@gmail.com', 'Jovan', 'Srbija', 'Jovanovic', 3, 20.91667, 44.01667);
insert into coauthor(city, email, name, state,surname, article_id, longit, latt) values ('Kragujevac', 'coautor2@gmail.com', 'Jovan', 'Srbija', 'Jovanovic', 4, 20.91667, 44.01667);
insert into coauthor(city, email, name, state,surname, article_id, longit, latt) values ('Kumanovo', 'coautor4@gmail.com', 'Mirjana', 'Rusija', 'Miric', 5, 21.71444, 42.13222);
insert into coauthor(city, email, name, state,surname, article_id, longit, latt) values ('Podgorica', 'coautor4@gmail.com', 'Bojan', 'Crna Gora', 'Bojanić', 6, 19.26361, 42.44111);
insert into coauthor(city, email, name, state,surname, article_id, longit, latt) values ('Podgorica', 'coautor4@gmail.com', 'Bojan', 'Crna Gora', 'Bojanić', 7, 19.26361, 42.44111);
insert into coauthor(city, email, name, state,surname, article_id, longit, latt) values ('Karlovac', 'coautor4@gmail.com', 'Višnja', 'Hrvatska', 'Višnjić', 8, 15.5499978, 45.4666648);
insert into coauthor(city, email, name, state,surname, article_id, longit, latt) values ('Subotica','coautor1@gmail.com', 'Popaj', 'Srbija', 'Peric',9,19.667587 ,46.100376);
insert into coauthor(city, email, name, state,surname, article_id, longit, latt) values ('Subotica','coautor1@gmail.com', 'Popaj', 'Srbija', 'Peric',10,19.667587 ,46.100376);
insert into coauthor(city, email, name, state,surname, article_id, longit, latt) values ('Subotica','coautor1@gmail.com', 'Popaj', 'Srbija', 'Peric',11,19.667587 ,46.100376);








