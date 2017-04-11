package service;

import java.util.ArrayList;
import java.util.List;

import dao.Dao;
import dao.IDao;
import metier.CarteBancaire;
import metier.Client;
import metier.Compte;
import metier.CompteCourant;
import metier.CompteEpargne;
import metier.ConseillerClientele;
import metier.Patrimoine;
import metier.Placement;
import metier.VisaElectron;
import metier.VisaPremier;
import service.exception.AssociationInvalideException;
import service.exception.ClientNonEligibleException;
import service.exception.MontantNegatifException;
import service.exception.SoldeInsuffisantException;

public class ServiceConseillerClientele implements IServiceConseillerClientele {

	private IDao idao = new Dao();

	@Override
	public Client creerClient(String nom, String prenom, String adresse, int codePostal, String ville, String telephone,
			String email, String typeClient) {
		// On crait un client particulier
		Client ccp = new Client(nom, prenom, adresse, codePostal, ville, telephone, email, typeClient);
		return ccp;
	}

	/**
	 * @author Pierre
	 */
	@Override
	public void associerConseillerClient(ConseillerClientele conseiller, Client client)
			throws AssociationInvalideException {

		// lev�e d'exception
		if (conseiller.getClients().size() >= 10) {
			throw new AssociationInvalideException("le conseiller � d�j� 10 clients sous sa responsabilit�");
		} else // association
		{
			conseiller.getClients().add(client);
			client.setConseiller(conseiller);
		}

	}

	@Override
	public CompteCourant creerCompteCourant(int numeroCompte, float solde, String dateDouverture) {
		// On crait un compte courant
		CompteCourant ccc = new CompteCourant(numeroCompte, solde, dateDouverture);
		return ccc;
	}

	@Override
	public CompteEpargne creerCompteEpargne(int numeroCompte, float solde, String dateDouverture) {
		// On crait un compte �pargne
		CompteEpargne ccep = new CompteEpargne(numeroCompte, solde, dateDouverture);
		return ccep;
	}

	@Override
	public void associerCompteClient(Compte compte, Client client) throws AssociationInvalideException {

		// On test le type de compte. Est-ce que c'est un CompteEpargne ? Ca
		// renvoit directement True ou False
		if (compte instanceof CompteEpargne)

		{
			// On test si le client � d�j� un compte �pargne ou pas
			if (client.getCompteEpargne() == null)

			{
				// On r�alise l'association Client - Compte �pargne
				client.setCompteEpargne((CompteEpargne) compte);
				// On r�alise l'association Compte �pargne - Client
				compte.setClient(client);

			} else {
				// Si le compte �pargne existe d�j� on envoit une exception
				throw new AssociationInvalideException("Le compte �pargne existe d�j�");

			}
		}

		// On test le type de compte
		if (compte instanceof CompteCourant) {
			// On test si le client � d�j� un compte courant ou pas
			if (client.getCompteCourant() == null)

			{
				// On r�alise l'association Client - Compte courant
				client.setCompteCourant((CompteCourant) compte);
				// On r�alise l'association Compte - Client
				compte.setClient(client);
			} else {
				// Si le compte courant existe d�j� on envoit une exception
				throw new AssociationInvalideException("Le compte courant existe d�j�");
			}
		}
	}

	@Override
	public CarteBancaire creerCarteVisaPremier() {
		// On crait une carte bancaire Visa Premier
		CarteBancaire ccvp = new VisaPremier();
		return ccvp;
	}

	@Override
	public CarteBancaire creerCarteVisaElectron() {
		// On crait une carte bancaire Visa �lectron
		CarteBancaire ccve = new VisaElectron();
		return ccve;
	}

	@Override
	public void associerCarteCompte(CompteCourant compteCourant, CarteBancaire cb) throws AssociationInvalideException {
		if (cb.getCompte() == null) // On test si la carte est associ�e � un
									// compte
		{
			cb.setCompte(compteCourant); // Association entre cb et compte
											// courant
			compteCourant.setCb(cb); // Association entre compte courant et cb
		} else {
			throw new AssociationInvalideException("La carte est dej� associ�e � un compte");
		}

	}

	@Override
	public String lireInfoClient(Client client) {
		ServiceClient lic = new ServiceClient();
		String lics = lic.getInfoClient(client);
		return lics;
	}

	@Override
	public void modifierNomClient(Client client, String nouveauNom) {
		client.setNom(nouveauNom);
	}

	@Override
	public void modifierPrenomClient(Client client, String nouveauPrenom) {
		client.setPrenom(nouveauPrenom);
	}

	@Override
	public void modifierAdresseClient(Client client, String nouvelleAdresse, int nouveauCodePostal,
			String nouvelleVille) {
		client.setAdresse(nouvelleAdresse);
		client.setCodePostal(nouveauCodePostal);
		client.setVille(nouvelleVille);

	}

	@Override
	public void modifierTelefoneClient(Client client, String nouveauNumero) {
		client.setTelephone(nouveauNumero);

	}

	/**
	 * @author Pierre
	 */
	@Override
	public void supprimerClient(Client client) {

		// 1/ lors de la suppression on doit supprimer tout les liens du client
		// avec d'autres objets
		// conseiller
		ConseillerClientele conseiller = client.getConseiller();
		// compte courant
		CompteCourant compteCourant = client.getCompteCourant();
		// carte associ�e au compte courant
		CarteBancaire cb = null;
		if (compteCourant != null) {
			cb = compteCourant.getCb();
		}
		// compte epargne
		CompteEpargne compteEpargne = client.getCompteEpargne();
		// patrimoine et placement s'ils existent
		Patrimoine patrimoine = null;
		List<Placement> placements = new ArrayList<Placement>();
		if (client.getTypeClient().equals("PARTICULIER")) {
			patrimoine = client.getPatrimoine();
			if (patrimoine != null) {
				placements = patrimoine.getPlacements();
			}
		}

		// 2/ Supressions de lien et d'objets
		// desactivation des CB
		if (cb != null) {
			cb.desactiver();
			// suppression du lien avec le compte
			cb.setCompte(null);
		}
		// suppression des comptes
		if (compteCourant != null) {
			compteCourant = null;
		}
		if (compteEpargne != null) {
			compteEpargne = null;
		}
		// Suppression du patrimoine et des placements
		if (patrimoine != null) {

			for (Placement placement : placements) {
				placement = null;
			}
			patrimoine = null;
		}

		// suppression du lien avec le conseiller
		if (conseiller != null) {
			conseiller.getClients().remove(client);
		}
		// suppression du client
		client = null;

		// appel du garbage collector
		System.gc();

	}

	/**
	 * @author Pierre
	 */
	@Override
	public void effectuerVirement(Compte compteADebite, Compte CompteACrediter, float somme)
			throws SoldeInsuffisantException, MontantNegatifException {

		if (somme < 0) {
			throw new MontantNegatifException("Montant n�gatif");
		} else if (somme > compteADebite.getSolde()) {
			throw new SoldeInsuffisantException("Solde insuffisant");
		} else {
			compteADebite.setSolde(compteADebite.getSolde() - somme);
			CompteACrediter.setSolde(CompteACrediter.getSolde() + somme);
		}

	}

	@Override
	public void simulerCreditConsommation() {
		// TODO Auto-generated method stub

	}

	@Override
	public void simulerCreditImmobilier() {
		// TODO Auto-generated method stub

	}

	/**
	 * @author Pierre
	 */
	@Override
	public void ouvrirDroitPatrimoineClient(Client client) throws ClientNonEligibleException {

		// instanciation d'un service client pour obtenir le total des d�pots
		ServiceClient sc = new ServiceClient();

		// exception client non fortun�
		if (sc.getTotalDepots(client) < 500000) {
			throw new ClientNonEligibleException(
					"seuls les clients disposant de plus de 500000� de d�pot peuvent pr�tendre � la gestion du patrimoine");
		} else
		// on instancie un patrimoine li� au client
		{
			client.setPatrimoine(new Patrimoine(client));
		}

	}

	@Override
	public void ajouterPlacementAuPatrimoineClient(Client client, Placement placement)
			throws ClientNonEligibleException {
		// test si un patrimoine a �t� d�clar� pour le client
		if (client.getPatrimoine() == null) {
			throw new ClientNonEligibleException("Les droits � la gestion du patrimoine n'ont pas �t� ouverts");
		} else {
			client.getPatrimoine().getPlacements().add(placement);
		}

	}

	@Override
	public List<Client> listerClients(int idConseiller) {

		return idao.listerClient(idConseiller);
	}

	@Override
	public Client chercherClient(int idClient) {
		
		return idao.chercherClient(idClient);
	}

}
