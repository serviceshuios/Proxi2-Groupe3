
#------------------------------------------------------------
# Donnees de demo
#------------------------------------------------------------


INSERT INTO `conseiller` (`IdConseiller`, `Nom`, `Prenom`, `Adresse`, `CodePostal`, `Ville`, `Telephone`, `Email`, `Login`, `MotDePasse`) 
VALUES (NULL, 'Jeanjacquot', 'Pierre', '73 rue Duverger', '01330', 'VILLARS LES DOMBES', '0602030405', 'pierre.jeanjacquot@exemple.fr', 'PJJ', 'pjj');

INSERT INTO `conseiller` (`IdConseiller`, `Nom`, `Prenom`, `Adresse`, `CodePostal`, `Ville`, `Telephone`, `Email`, `Login`, `MotDePasse`) 
VALUES (NULL, 'Bourne', 'Jason', '68 rue durang', '69000', 'LYON', '0602030405', 'Jason.Bourne@exemple.fr', 'JASON', 'BOURNE');

INSERT INTO `conseiller` (`IdConseiller`, `Nom`, `Prenom`, `Adresse`, `CodePostal`, `Ville`, `Telephone`, `Email`, `Login`, `MotDePasse`) 
VALUES (NULL, 'Dupond', 'Paul', '29 rue du pont', '38000', 'GRENOBLE', '0602030405', 'paul.Dupond@exemple.fr', 'TITI', '123');

INSERT INTO `client` (`IdClient`, `Nom`, `Prenom`, `Adresse`, `CodePostal`, `Ville`, `Telephone`, `Email`, `TypeClient`, `IdConseiller`)
VALUES (NULL, 'Hochon', 'Paul', 'inconnu', '01000', 'BOURG EN BRESSE', '0679845123', NULL, 'PARTICULIER', '2');

INSERT INTO `client` (`IdClient`, `Nom`, `Prenom`, `Adresse`, `CodePostal`, `Ville`, `Telephone`, `Email`, `TypeClient`, `IdConseiller`)
VALUES (NULL, 'WAYNE', 'Bruce', 'batcave', '01000', 'GOTHAM', '0679845123', NULL, 'PARTICULIER', '2');

INSERT INTO `client` (`IdClient`, `Nom`, `Prenom`, `Adresse`, `CodePostal`, `Ville`, `Telephone`, `Email`, `TypeClient`, `IdConseiller`)
VALUES (NULL, 'KENT', 'Clark', 'Ranch', '48621', 'SMALLVILLE', '0679845123', NULL, 'PARTICULIER', '2');

INSERT INTO `client` (`IdClient`, `Nom`, `Prenom`, `Adresse`, `CodePostal`, `Ville`, `Telephone`, `Email`, `TypeClient`, `IdConseiller`)
VALUES (NULL, 'McDonald', 'Ronald', 'Ranch', '461', 'HUSTON', '9873565', NULL, 'ENTREPRISE', '2');

INSERT INTO `compte` (`NumeroCompte`, `Solde`, `DateOuverture`, `IdClient`) VALUES (NULL, '489312.48', '2017-04-11', '1');
INSERT INTO `comptecourant` (`DecouvertAutorise`, `NumeroCompte`) VALUES ('500', '1');

INSERT INTO `compte` (`NumeroCompte`, `Solde`, `DateOuverture`, `IdClient`) VALUES (NULL, '489312.48', '2017-04-11', '2');
INSERT INTO `comptecourant` (`DecouvertAutorise`, `NumeroCompte`) VALUES ('500', '2');

INSERT INTO `compte` (`NumeroCompte`, `Solde`, `DateOuverture`, `IdClient`) VALUES (NULL, '15.48', '2017-04-11', '3');
INSERT INTO `comptecourant` (`DecouvertAutorise`, `NumeroCompte`) VALUES ('150', '3');

INSERT INTO `compte` (`NumeroCompte`, `Solde`, `DateOuverture`, `IdClient`) VALUES (NULL, '999999.48', '2017-04-11', '2');
INSERT INTO `compteepargne` (`TauxRemuneration`, `NumeroCompte`) VALUES ('0.75', '4');

INSERT INTO `compte` (`NumeroCompte`, `Solde`, `DateOuverture`, `IdClient`) VALUES (NULL, '12349.10', '2017-04-11', '4');
INSERT INTO `compteepargne` (`TauxRemuneration`, `NumeroCompte`) VALUES ('3.5', '5');