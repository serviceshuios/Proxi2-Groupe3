/**
 * 
 */
package service;

import java.util.List;

import metier.*;
import service.exception.AssociationInvalideException;
import service.exception.ClientNonEligibleException;
import service.exception.MontantNegatifException;
import service.exception.SoldeInsuffisantException;

/**
 * Interface de services disponible pour les conseillers
 * 
 * @author Pierre
 *
 */
public interface IServiceConseillerClientele {

	/**
	 * 
	 * Cree un Client
	 * 
	 * @param nom
	 *            nom du client
	 * @param prenom
	 *            prenom du client
	 * @param adresse
	 *            adresse
	 * @param codePostal
	 *            code postal
	 * @param ville
	 *            ville
	 * @param telephone
	 *            telephone
	 * @param typeClient
	 *            PARTICULIER ou ENTREPRISE
	 * 
	 * @return Client
	 */
	public Client creerClient(String nom, String prenom, String adresse, int codePostal, String ville, String telephone,
			String email, String typeClient);

	/**
	 * Associe un conseiller avec un client
	 * 
	 * @param conseiller
	 *            conseiller � associer
	 * @param client
	 *            client � associer
	 * @throws AssociationInvalideException
	 *             l�ve une exception si le conseiller � d�j� 10 clients
	 */
	public void associerConseillerClient(ConseillerClientele conseiller, Client client)
			throws AssociationInvalideException;

	/**
	 * Cree un compte courant
	 * 
	 * @return Compte courant
	 */
	public CompteCourant creerCompteCourant(int numeroCompte, float solde, String dateDouverture);

	/**
	 * Cree un compte epargne
	 * 
	 * @return Compte epargne
	 */
	public CompteEpargne creerCompteEpargne(int numeroCompte, float solde, String dateDouverture);

	/**
	 * Associe un compte � un client et vice-versa. Accepte n'importe quel type
	 * de compte et n'importe quel type de client.
	 * 
	 * @param compte
	 *            compte � associer
	 * @param client
	 *            client � associer
	 * @throws AssociationInvalideException
	 *             l�ve une exception si le client poss�de dej� un compte du
	 *             m�metype
	 */
	public void associerCompteClient(Compte compte, Client client) throws AssociationInvalideException;

	/**
	 * Cree une carte Visa Premier
	 * 
	 * @return CarteBancaire de type VisaPremier
	 */
	public CarteBancaire creerCarteVisaPremier();

	/**
	 * cree une carte Visa Electron
	 * 
	 * @return CarteBancaire de type VisaElectron
	 */
	public CarteBancaire creerCarteVisaElectron();

	/**
	 * Associe une carte avec un compte courant. Une carte ne peut etre associ�e
	 * qu'� un compte et un compte ne peut avoir au maximum qu'une seule carte
	 * 
	 * @param compteCourant
	 *            Compte de type CompteCourant � associer a la carte
	 * @param cb
	 *            carte bancaire � associer au compte
	 * @throws AssociationInvalideException
	 *             leve une exception si la carte est d�j� associ�e � un compte
	 *             ou que le compte est d�j� associ� � une carte
	 */
	public void associerCarteCompte(CompteCourant compteCourant, CarteBancaire cb) throws AssociationInvalideException;;

	/**
	 * Donne lesinformation d'un client
	 * 
	 * @param client
	 *            client dont on d�sire obtenir les information
	 * @return chaine decaract�re liste des information client
	 */
	public String lireInfoClient(Client client);

	/**
	 * modifie dans le cas d'un particulier le nom du client
	 * 
	 * @param client
	 *            client dont le nom doit �tre modifi�
	 * @param nouveauNom
	 *            nouveau nomdu client
	 */
	public void modifierNomClient(Client client, String nouveauNom);

	/**
	 * modifie dans le cas d'un particulier le prenom du client
	 * 
	 * @param client
	 *            client dont le pr�nom doit �tre modifi�
	 * @param nouveauPrenom
	 *            nouveau pr�nom du client
	 */
	public void modifierPrenomClient(Client client, String nouveauPrenom);

	/**
	 * modifie dans le cas d'un particulier l'adresse du client
	 * 
	 * @param client
	 *            client � modifier
	 * @param nouvelleAdresse
	 *            nouvelle adresse du client
	 * @param nouveauCodePostal
	 *            nouveau code postal du client
	 * @param nouvelleVille
	 *            nouvelle ville du client
	 */
	public void modifierAdresseClient(Client client, String nouvelleAdresse, int nouveauCodePostal,
			String nouvelleVille);

	/**
	 * modifie dans le cas d'un particulier le numero de telephone du client
	 * 
	 * @param client
	 *            client dont le telephone doit �tre modifi�
	 * @param nouveauPrenom
	 *            nouveau numero du client
	 */
	public void modifierTelefoneClient(Client client, String nouveauNumero);

	/**
	 * Supprime un client, supprime �galement ses comptes ainsi que son
	 * patrimoine et d�sactive ses cartes bancaires
	 * 
	 * @param client
	 *            client � supprimer
	 */
	public void supprimerClient(Client client);

	/**
	 * Transf�re une somme d'argent du compte � d�biter au compte � cr�diter.
	 * 
	 * @param compteADebite
	 *            compte � d�biter
	 * @param CompteACrediter
	 *            compte � crediter
	 * @param somme
	 *            somme � transferer
	 * @throws SoldeInsuffisantException
	 *             leve une exception si le solde du compte � d�biter est
	 *             insuffisant
	 * @throws MontantNegatifException
	 *             leve une exception si la somme est n�gative
	 */
	public void effectuerVirement(Compte compteADebite, Compte CompteACrediter, float somme)
			throws SoldeInsuffisantException, MontantNegatifException;

	/**
	 * simule un cr�dit � la consommation
	 */
	public void simulerCreditConsommation();

	/**
	 * simule un cr�dit immobilier
	 */
	public void simulerCreditImmobilier();

	/**
	 * Ouvre les droit � gestion du patrimoine d'un client
	 * 
	 * @param client
	 *            client
	 * @throws ClientNonEligibleException
	 *             leve une exception si le client a moins de 500_000� en d�pot
	 */
	public void ouvrirDroitPatrimoineClient(Client client) throws ClientNonEligibleException;

	/**
	 * Ajoute un placement au patrimoine d'un client
	 * 
	 * @param client
	 *            client
	 * @param placement
	 *            placement
	 * @throws ClientNonEligibleException
	 *             leve une exception si le client n'a pas de droit � la gestion
	 *             du patrimoine
	 */
	public void ajouterPlacementAuPatrimoineClient(Client client, Placement placement)
			throws ClientNonEligibleException;

	/**
	 * liste les clients d'un conseiller
	 * 
	 * @param idConseiller
	 * @return Liste des clients du conseiller
	 */
	public List<Client> listerClients(int idConseiller);

	/**
	 * retrouve un client � partir de son id
	 * @param idClient
	 * @return
	 */
	public Client chercherClient(int idClient);
	
	/**
	 * Modifie le client dont l'id est sp�cifi� avec de nouveaux param�tres
	 * @param id
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param adresse
	 * @param codepostal
	 * @param ville
	 * @param telephone
	 */
	public void modifierClient(int id, String nom, String prenom, String email, String adresse, int codepostal,
			String ville, String telephone);

	/**
	 * renvoi le compte Epargne d'un client s'il existe ou null dans le cas contraire
	 * @param idClient
	 * @return
	 */
	public CompteEpargne chercherCompteEpargne(int idClient);
	
	/**
	 * renvoi le compte Courant d'un client s'il existe ou null dans le cas contraire
	 * @param idClient
	 * @return
	 */
	public CompteCourant chercherCompteCourant(int idClient);
	
	/**
	 * renvoi la liste des comptes d'un client
	 * @param idClient
	 * @return
	 */
	public List<Compte> chercherComptes(int idClient);
	
	
}
