#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: Conseiller
#------------------------------------------------------------

CREATE TABLE Conseiller(
        IdConseiller int (11) Auto_increment  NOT NULL ,
        Nom          Varchar (25) ,
        Prenom       Varchar (25) ,
        Adresse      Varchar (50) ,
        CodePostal   Varchar (5)  ,
        Ville        Varchar (25) ,
        Telephone    Varchar (10) ,
        Email        Varchar (50) ,
        Login        Varchar (50) ,
        MotDePasse   Varchar (50) ,
        PRIMARY KEY (IdConseiller )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Client
#------------------------------------------------------------

CREATE TABLE Client(
        IdClient     int (11) Auto_increment  NOT NULL ,
        Nom          Varchar (25) ,
        Prenom       Varchar (25) ,
        Adresse      Varchar (50) ,
        CodePostal   Varchar (5)  ,
        Ville        Varchar (25) ,
        Telephone    Varchar (10) ,
        Email        Varchar (50) ,
        TypeClient   Varchar (25) ,
        IdConseiller Int ,
        PRIMARY KEY (IdClient )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Compte
#------------------------------------------------------------

CREATE TABLE Compte(
        NumeroCompte  int (11) Auto_increment  NOT NULL ,
        Solde         Float ,
        DateOuverture Date ,
        IdClient      Int ,
        PRIMARY KEY (NumeroCompte )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: CompteCourant
#------------------------------------------------------------

CREATE TABLE CompteCourant(
        DecouvertAutorise Float ,
        NumeroCompte      Int NOT NULL ,
        PRIMARY KEY (NumeroCompte )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: CompteEpargne
#------------------------------------------------------------

CREATE TABLE CompteEpargne(
        TauxRemuneration Float ,
        NumeroCompte     Int NOT NULL ,
        PRIMARY KEY (NumeroCompte )
)ENGINE=InnoDB;

ALTER TABLE Client ADD CONSTRAINT FK_Client_IdConseiller FOREIGN KEY (IdConseiller) REFERENCES Conseiller(IdConseiller);
ALTER TABLE Compte ADD CONSTRAINT FK_Compte_IdClient FOREIGN KEY (IdClient) REFERENCES Client(IdClient);
ALTER TABLE CompteCourant ADD CONSTRAINT FK_CompteCourant_NumeroCompte FOREIGN KEY (NumeroCompte) REFERENCES Compte(NumeroCompte);
ALTER TABLE CompteEpargne ADD CONSTRAINT FK_CompteEpargne_NumeroCompte FOREIGN KEY (NumeroCompte) REFERENCES Compte(NumeroCompte);
