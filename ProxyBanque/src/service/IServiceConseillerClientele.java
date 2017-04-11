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
	 * @param typeClient PARTICULIER ou ENTREPRISE
	 *            
	 * @return Client
	 */
	public Client creerClient(String nom, String prenom, String adresse, int codePostal, String ville, String telephone,
			String email, String typeClient);

	/**
	 * Associe un conseiller avec un client
	 * 
	 * @param conseiller
	 *            conseiller à associer
	 * @param client
	 *            client à associer
	 * @throws AssociationInvalideException
	 *             lève une exception si le conseiller à déjà 10 clients
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
	 * Associe un compte à un client et vice-versa. Accepte n'importe quel type
	 * de compte et n'importe quel type de client.
	 * 
	 * @param compte
	 *            compte à associer
	 * @param client
	 *            client à associer
	 * @throws AssociationInvalideException
	 *             lève une exception si le client possède dejà un compte du
	 *             mêmetype
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
	 * Associe une carte avec un compte courant. Une carte ne peut etre associée
	 * qu'à un compte et un compte ne peut avoir au maximum qu'une seule carte
	 * 
	 * @param compteCourant
	 *            Compte de type CompteCourant à associer a la carte
	 * @param cb
	 *            carte bancaire à associer au compte
	 * @throws AssociationInvalideException
	 *             leve une exception si la carte est déjà associée à un compte
	 *             ou que le compte est déjà associé à une carte
	 */
	public void associerCarteCompte(CompteCourant compteCourant, CarteBancaire cb) throws AssociationInvalideException;;

	/**
	 * Donne lesinformation d'un client
	 * 
	 * @param client
	 *            client dont on désire obtenir les information
	 * @return chaine decaractère liste des information client
	 */
	public String lireInfoClient(Client client);


	/**
	 * modifie dans le cas d'un particulier le nom du client
	 * 
	 * @param client
	 *            client dont le nom doit être modifié
	 * @param nouveauNom
	 *            nouveau nomdu client
	 */
	public void modifierNomClient(Client client, String nouveauNom);

	/**
	 * modifie dans le cas d'un particulier le prenom du client
	 * 
	 * @param client
	 *            client dont le prénom doit être modifié
	 * @param nouveauPrenom
	 *            nouveau prénom du client
	 */
	public void modifierPrenomClient(Client client, String nouveauPrenom);

	/**
	 * modifie dans le cas d'un particulier l'adresse du client
	 * 
	 * @param client
	 *            client à modifier
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
	 *            client dont le telephone doit être modifié
	 * @param nouveauPrenom
	 *            nouveau numero du client
	 */
	public void modifierTelefoneClient(Client client, String nouveauNumero);

	/**
	 * Supprime un client, supprime également ses comptes ainsi que son
	 * patrimoine et désactive ses cartes bancaires
	 * 
	 * @param client
	 *            client à supprimer
	 */
	public void supprimerClient(Client client);

	/**
	 * Transfère une somme d'argent du compte à débiter au compte à créditer.
	 * 
	 * @param compteADebite
	 *            compte à débiter
	 * @param CompteACrediter
	 *            compte à crediter
	 * @param somme
	 *            somme à transferer
	 * @throws SoldeInsuffisantException
	 *             leve une exception si le solde du compte à débiter est
	 *             insuffisant
	 * @throws MontantNegatifException
	 *             leve une exception si la somme est négative
	 */
	public void effectuerVirement(Compte compteADebite, Compte CompteACrediter, float somme)
			throws SoldeInsuffisantException, MontantNegatifException;

	/**
	 * simule un crédit à la consommation
	 */
	public void simulerCreditConsommation();

	/**
	 * simule un crédit immobilier
	 */
	public void simulerCreditImmobilier();

	/**
	 * Ouvre les droit à gestion du patrimoine d'un client
	 * 
	 * @param client
	 *            client
	 * @throws ClientNonEligibleException
	 *             leve une exception si le client a moins de 500_000€ en dépot
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
	 *             leve une exception si le client n'a pas de droit à la gestion
	 *             du patrimoine
	 */
	public void ajouterPlacementAuPatrimoineClient(Client client, Placement placement)
			throws ClientNonEligibleException;
	/**
	 * liste les clients d'un conseiller
	 * @param idConseiller
	 * @return Liste des clients du conseiller
	 */
	public List<Client> listerClients(int idConseiller);

}
