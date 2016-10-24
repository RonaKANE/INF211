drop table if exists MessageOffreDEmploi;
drop table if exists MessageCandidature;
drop table if exists SecteurCandidature;
drop table if exists Candidature;
drop table if exists SecteurEmploi;
drop table if exists OffreEmploi;
drop table if exists NiveauQualification;
drop table if exists SecteurActivite;
drop table if exists Entreprise;

-- +----------------------------------------------------------------------------------------------+
-- | Création des tables                                                                          |
-- +----------------------------------------------------------------------------------------------+

create table Entreprise
(
  id          serial PRIMARY KEY,
  nom             varchar(50) NOT NULL,
  descriptif      text,
  adresse_postale varchar(30) -- Pour simplifier, adresse_postale = ville.
);

create table SecteurActivite
(
	id 	serial PRIMARY KEY,
	intitule	text

);

create table NiveauQualification
(
	id 		serial PRIMARY KEY,
	intitule	text

);

create table OffreEmploi
(
	id			serial PRIMARY KEY,
	titre				text,
	descriptionMission	text,
	profilRecherche		text,
	dateDepot			date,
	id_Ent_fk			int  REFERENCES Entreprise(id),
	id_Niv_fk			int  REFERENCES NiveauQualification (id)
);

create table SecteurEmploi
(
	id_SecActi_fk 		int  REFERENCES SecteurActivite(id),
	id_Offre_fk 		int  REFERENCES OffreEmploi(id),
	primary key (id_SecActi_fk, id_Offre_fk)
);

create table Candidature
(
	id 			serial PRIMARY KEY,
	nom						text,
	prenom					text,
	dateNaissance			date,
	adresse_postale 		varchar(30),
	adresseEmail 			varchar(30),
	CV						text,
	dateDepot				date,
	id_Niv_fk				int  REFERENCES NiveauQualification (id)
);

create table MessageOffreDEmploi
(
	id			serial PRIMARY KEY,
	dateEnvoi				date,
	corpsMessage			text,
	id_Candidature_fk 		int REFERENCES Candidature (id),
	id_Offre_fk 			int REFERENCES OffreEmploi(id)
	
);

create table MessageCandidature
(
	id 					serial PRIMARY KEY,
	dateEnvoi					date,
	corpsMessage				text,
	id_Candidature_fk 			int REFERENCES Candidature (id),
	id_Offre_fk 				int REFERENCES OffreEmploi(id)
);


create table SecteurCandidature
(
	id_Candidature_fk 			int REFERENCES Candidature (id),
	id_SecActi_fk 				int REFERENCES SecteurActivite(id),
	primary key(id_Candidature_fk, id_SecActi_fk)
);

-- +----------------------------------------------------------------------------------------------+
-- | Insertion de quelques données de pour les tests                                              |
-- +----------------------------------------------------------------------------------------------+

-- Insertion des secteurs d'activité

insert into Entreprise (nom, descriptif, adresse_postale)   values ('Télécom Bretagne','Télécom Bretagne est une grande école pionnière en formation, en recherche et en entrepreneuriat.','Plouzané');
insert into Entreprise (nom, descriptif, adresse_postale)   values ('ENIB','Une école d''ingénieur juste à côté...','Plouzané');
insert into Entreprise (nom, descriptif, adresse_postale)   values ('INSA','Une école d''ingénieur pour les nuls','Lyon');
insert into Entreprise (nom, descriptif, adresse_postale)   values ('SFR','La meilleur entreprise que l humanité à connu','Les Ulis');
insert into Entreprise (nom, descriptif, adresse_postale)   values ('Orange','L entreprise des connards, je n ai rien dis','Paris');
insert into Entreprise (nom, descriptif, adresse_postale)   values ('Maroc Télécom','Beaucoup d avantages et moins de casse tête','Agadir');
insert into Entreprise (nom, descriptif, adresse_postale)   values ('Chine Télécom','Des arnaqueurs','Pékin');
insert into Entreprise (nom, descriptif, adresse_postale)   values ('Senegal Télécom','L afrique quoi !!!!! ','Dakar');
insert into SecteurActivite (intitule)   values ('Télécom');
insert into SecteurActivite (intitule)   values ('Réseau');
insert into SecteurActivite (intitule)   values ('Informatique');
insert into SecteurActivite (intitule)   values ('Sécurité');
insert into SecteurActivite (intitule)   values ('Santé');
insert into SecteurActivite (intitule)   values ('Aide à la personne');
insert into SecteurActivite (intitule)   values ('Ressources Humaines');
insert into SecteurActivite (intitule)   values ('Traitement de signal');
insert into SecteurActivite (intitule)   values ('Enseignement');
insert into NiveauQualification (intitule)   values ('BTS');
insert into NiveauQualification (intitule)   values ('DUT');
insert into NiveauQualification (intitule)   values ('Bac +1');
insert into NiveauQualification (intitule)   values ('Bac +2');
insert into NiveauQualification (intitule)   values ('Bac +3');
insert into NiveauQualification (intitule)   values ('Bac +4');
insert into NiveauQualification (intitule)   values ('Bac +5');
insert into NiveauQualification (intitule)   values ('Bac +6');
insert into NiveauQualification (intitule)   values ('Bac +7');
insert into NiveauQualification (intitule)   values ('Bac +8');

insert into OffreEmploi (titre, descriptionMission, profilRecherche, dateDepot, id_Ent_fk, id_Niv_fk) values ('Dictateur', 'Diriger le peuple', 'Africain', '10-10-2016', 1, 1);
insert into OffreEmploi (titre, descriptionMission, profilRecherche, dateDepot, id_Ent_fk, id_Niv_fk) values ('Acteur X', 'Devenir celebre en 5mn', '20cm minimum', '09-09-2016', 1, 2);
insert into OffreEmploi (titre, descriptionMission, profilRecherche, dateDepot, id_Ent_fk, id_Niv_fk) values ('FIpo	', 'Rejoindre les FIPs', 'attirance pour le meme sexe', '06-06-2016', 1, 3);

insert into OffreEmploi (titre, descriptionMission, profilRecherche, dateDepot, id_Ent_fk, id_Niv_fk) values ('Voleur', 'Gagner de largent facilement', 'Arabe', '10-10-2016', 2, 1);
insert into OffreEmploi (titre, descriptionMission, profilRecherche, dateDepot, id_Ent_fk, id_Niv_fk) values ('Escort Boy', 'Rencontre des hommes riches', 'Personne sociable', '09-09-2016', 2, 2);
insert into OffreEmploi (titre, descriptionMission, profilRecherche, dateDepot, id_Ent_fk, id_Niv_fk) values ('Clown	', 'Faire rire les enfants', 'Personne drôle', '06-06-2016', 2, 3);

insert into OffreEmploi (titre, descriptionMission, profilRecherche, dateDepot, id_Ent_fk, id_Niv_fk) values ('Bucheron', 'Couper du bois', 'Arabe de pref', '10-10-2016', 3, 1);
insert into OffreEmploi (titre, descriptionMission, profilRecherche, dateDepot, id_Ent_fk, id_Niv_fk) values ('Cuisiner', 'Preparer de bons petits plats', 'capable de faire du riz', '09-09-2016', 3, 2);
insert into OffreEmploi (titre, descriptionMission, profilRecherche, dateDepot, id_Ent_fk, id_Niv_fk) values ('Chomeur', 'Dormir toute la journée', 'Etudiant de Télécom Bretagne', '06-06-2016', 3, 3);
-----

insert into SecteurEmploi (id_SecActi_fk, id_Offre_fk) values (1, 1);
insert into SecteurEmploi (id_SecActi_fk, id_Offre_fk) values (1, 2);
insert into SecteurEmploi (id_SecActi_fk, id_Offre_fk) values (1, 3);

insert into SecteurEmploi (id_SecActi_fk, id_Offre_fk) values (2, 1);
insert into SecteurEmploi (id_SecActi_fk, id_Offre_fk) values (2, 2);
insert into SecteurEmploi (id_SecActi_fk, id_Offre_fk) values (2, 3);

insert into SecteurEmploi (id_SecActi_fk, id_Offre_fk) values (3, 1);
insert into SecteurEmploi (id_SecActi_fk, id_Offre_fk) values (3, 2);
insert into SecteurEmploi (id_SecActi_fk, id_Offre_fk) values (3, 3);

-----

insert into Candidature (nom, prenom, dateNaissance, adresse_postale, adresseEmail, CV, dateDepot, id_Niv_fk) values ('CHAO','Lim Kevin', '29-04-1994', 'mon adresse', 'limkevin.chao@gmail.com', 'mon CV', '2016-10-11', 1);
insert into Candidature (nom, prenom, dateNaissance, adresse_postale, adresseEmail, CV, dateDepot, id_Niv_fk) values ('AMHOUCHE', 'Lahoucine', '06-10-1994', 'mon adresse', 'lahoucine.amhouche@gmail.com', 'mon CV', '2016-10-11', 2);
insert into Candidature (nom, prenom, dateNaissance, adresse_postale, adresseEmail, CV, dateDepot, id_Niv_fk) values ('KANE', 'Arouna', '10-11-1994', 'mon adresse', 'arouna.kane@gmail.com', 'mon CV', '2016-10-11', 3);

-----

insert into MessageOffreDEmploi (dateEnvoi, corpsMessage, id_Candidature_fk, id_Offre_fk) values ('10-11-2016', 'Test', 1, 1);
insert into MessageOffreDEmploi (dateEnvoi, corpsMessage, id_Candidature_fk, id_Offre_fk) values ('10-11-2016', 'Test', 1, 2);
insert into MessageOffreDEmploi (dateEnvoi, corpsMessage, id_Candidature_fk, id_Offre_fk) values ('10-11-2016', 'Test', 1, 3);

insert into MessageOffreDEmploi (dateEnvoi, corpsMessage, id_Candidature_fk, id_Offre_fk) values ('10-11-2016', 'Test', 2, 1);
insert into MessageOffreDEmploi (dateEnvoi, corpsMessage, id_Candidature_fk, id_Offre_fk) values ('10-11-2016', 'Test', 2, 2);
insert into MessageOffreDEmploi (dateEnvoi, corpsMessage, id_Candidature_fk, id_Offre_fk) values ('10-11-2016', 'Test', 2, 3);

insert into MessageOffreDEmploi (dateEnvoi, corpsMessage, id_Candidature_fk, id_Offre_fk) values ('10-11-2016', 'Test', 3, 1);
insert into MessageOffreDEmploi (dateEnvoi, corpsMessage, id_Candidature_fk, id_Offre_fk) values ('10-11-2016', 'Test', 3, 2);
insert into MessageOffreDEmploi (dateEnvoi, corpsMessage, id_Candidature_fk, id_Offre_fk) values ('10-11-2016', 'Test', 3, 3);

-----
insert into MessageCandidature (dateEnvoi, corpsMessage, id_Candidature_fk, id_Offre_fk) values ('10-11-2016', 'Test', 1, 1);
insert into MessageCandidature (dateEnvoi, corpsMessage, id_Candidature_fk, id_Offre_fk) values ('10-11-2016', 'Test', 1, 2);
insert into MessageCandidature (dateEnvoi, corpsMessage, id_Candidature_fk, id_Offre_fk) values ('10-11-2016', 'Test', 1, 3);

insert into MessageCandidature (dateEnvoi, corpsMessage, id_Candidature_fk, id_Offre_fk) values ('10-11-2016', 'Test', 2, 1);
insert into MessageCandidature (dateEnvoi, corpsMessage, id_Candidature_fk, id_Offre_fk) values ('10-11-2016', 'Test', 2, 2);
insert into MessageCandidature (dateEnvoi, corpsMessage, id_Candidature_fk, id_Offre_fk) values ('10-11-2016', 'Test', 2, 3);

insert into MessageCandidature (dateEnvoi, corpsMessage, id_Candidature_fk, id_Offre_fk) values ('10-11-2016', 'Test', 3, 1);
insert into MessageCandidature (dateEnvoi, corpsMessage, id_Candidature_fk, id_Offre_fk) values ('10-11-2016', 'Test', 3, 2);
insert into MessageCandidature (dateEnvoi, corpsMessage, id_Candidature_fk, id_Offre_fk) values ('10-11-2016', 'Test', 3, 3);

-----

insert into SecteurCandidature (id_Candidature_fk, id_SecActi_fk) values (1, 1);
insert into SecteurCandidature (id_Candidature_fk, id_SecActi_fk) values (1, 2);
insert into SecteurCandidature (id_Candidature_fk, id_SecActi_fk) values (1, 3);

insert into SecteurCandidature (id_Candidature_fk, id_SecActi_fk) values (2, 1);
insert into SecteurCandidature (id_Candidature_fk, id_SecActi_fk) values (2, 2);
insert into SecteurCandidature (id_Candidature_fk, id_SecActi_fk) values (2, 3);

insert into SecteurCandidature (id_Candidature_fk, id_SecActi_fk) values (3, 1);
insert into SecteurCandidature (id_Candidature_fk, id_SecActi_fk) values (3, 2);
insert into SecteurCandidature (id_Candidature_fk, id_SecActi_fk) values (3, 3);

