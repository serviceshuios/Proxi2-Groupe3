#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: Personne
#------------------------------------------------------------

CREATE TABLE Personne(
        Nom          Varchar (25) ,
        Prenom       Varchar (25) ,
        IdPersonne   int (11) Auto_increment  NOT NULL ,
        Adresse      Varchar (50) ,
        CodePostal   Varchar (5) ,
        Ville        Varchar (25) ,
        Telephone    Varchar (10) ,
        Email        Varchar (50) ,
        IdConseiller Int ,
        IdClient     Int ,
        PRIMARY KEY (IdPersonne )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Conseiller
#------------------------------------------------------------

CREATE TABLE Conseiller(
        IdConseiller int (11) Auto_increment  NOT NULL ,
        Login        Varchar (50) ,
        MotDePasse   Varchar (50) ,
        IdPersonne   Int ,
        PRIMARY KEY (IdConseiller )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Client
#------------------------------------------------------------

CREATE TABLE Client(
        IdClient     int (11) Auto_increment  NOT NULL ,
        TypeClient   Varchar (25) ,
        IdPersonne   Int ,
        IdConseiller Int ,
        PRIMARY KEY (IdClient )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Compte
#------------------------------------------------------------

CREATE TABLE Compte(
        NumeroCompte    int (11) Auto_increment  NOT NULL ,
        Solde           Float NOT NULL ,
        DateOuverture   Date ,
        IdClient        Int ,
        IdCompteEpargne Int ,
        IdCompteCourant Int ,
        PRIMARY KEY (NumeroCompte )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: CompteCourant
#------------------------------------------------------------

CREATE TABLE CompteCourant(
        DecouvertAutorise Float ,
        IdCompteCourant   int (11) Auto_increment  NOT NULL ,
        NumeroCompte      Int NOT NULL ,
        PRIMARY KEY (IdCompteCourant )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: CompteEpargne
#------------------------------------------------------------

CREATE TABLE CompteEpargne(
        TauxRemuneration Float ,
        IdCompteEpargne  int (11) Auto_increment  NOT NULL ,
        NumeroCompte     Int NOT NULL ,
        PRIMARY KEY (IdCompteEpargne )
)ENGINE=InnoDB;

ALTER TABLE Personne ADD CONSTRAINT FK_Personne_IdConseiller FOREIGN KEY (IdConseiller) REFERENCES Conseiller(IdConseiller);
ALTER TABLE Personne ADD CONSTRAINT FK_Personne_IdClient FOREIGN KEY (IdClient) REFERENCES Client(IdClient);
ALTER TABLE Conseiller ADD CONSTRAINT FK_Conseiller_IdPersonne FOREIGN KEY (IdPersonne) REFERENCES Personne(IdPersonne);
ALTER TABLE Client ADD CONSTRAINT FK_Client_IdPersonne FOREIGN KEY (IdPersonne) REFERENCES Personne(IdPersonne);
ALTER TABLE Client ADD CONSTRAINT FK_Client_IdConseiller FOREIGN KEY (IdConseiller) REFERENCES Conseiller(IdConseiller);
ALTER TABLE Compte ADD CONSTRAINT FK_Compte_IdClient FOREIGN KEY (IdClient) REFERENCES Client(IdClient);
ALTER TABLE Compte ADD CONSTRAINT FK_Compte_IdCompteEpargne FOREIGN KEY (IdCompteEpargne) REFERENCES CompteEpargne(IdCompteEpargne);
ALTER TABLE Compte ADD CONSTRAINT FK_Compte_IdCompteCourant FOREIGN KEY (IdCompteCourant) REFERENCES CompteCourant(IdCompteCourant);
ALTER TABLE CompteCourant ADD CONSTRAINT FK_CompteCourant_NumeroCompte FOREIGN KEY (NumeroCompte) REFERENCES Compte(NumeroCompte);
ALTER TABLE CompteEpargne ADD CONSTRAINT FK_CompteEpargne_NumeroCompte FOREIGN KEY (NumeroCompte) REFERENCES Compte(NumeroCompte);
