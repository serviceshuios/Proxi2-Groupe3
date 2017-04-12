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

		// levée d'exception
		if (conseiller.getClients().size() >= 10) {
			throw new AssociationInvalideException("le conseiller à déjà 10 clients sous sa responsabilité");
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
		// On crait un compte épargne
		CompteEpargne ccep = new CompteEpargne(numeroCompte, solde, dateDouverture);
		return ccep;
	}

	@Override
	public void associerCompteClient(Compte compte, Client client) throws AssociationInvalideException {

		// On test le type de compte. Est-ce que c'est un CompteEpargne ? Ca
		// renvoit directement True ou False
		if (compte instanceof CompteEpargne)

		{
			// On test si le client à déjà un compte épargne ou pas
			if (client.getCompteEpargne() == null)

			{
				// On réalise l'association Client - Compte épargne
				client.setCompteEpargne((CompteEpargne) compte);
				// On réalise l'association Compte épargne - Client
				compte.setClient(client);

			} else {
				// Si le compte épargne existe déjà on envoit une exception
				throw new AssociationInvalideException("Le compte épargne existe déjà");

			}
		}

		// On test le type de compte
		if (compte instanceof CompteCourant) {
			// On test si le client à déjà un compte courant ou pas
			if (client.getCompteCourant() == null)

			{
				// On réalise l'association Client - Compte courant
				client.setCompteCourant((CompteCourant) compte);
				// On réalise l'association Compte - Client
				compte.setClient(client);
			} else {
				// Si le compte courant existe déjà on envoit une exception
				throw new AssociationInvalideException("Le compte courant existe déjà");
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
		// On crait une carte bancaire Visa électron
		CarteBancaire ccve = new VisaElectron();
		return ccve;
	}

	@Override
	public void associerCarteCompte(CompteCourant compteCourant, CarteBancaire cb) throws AssociationInvalideException {
		if (cb.getCompte() == null) // On test si la carte est associée à un
									// compte
		{
			cb.setCompte(compteCourant); // Association entre cb et compte
											// courant
			compteCourant.setCb(cb); // Association entre compte courant et cb
		} else {
			throw new AssociationInvalideException("La carte est dejà associée à un compte");
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
		// carte associée au compte courant
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
			throw new MontantNegatifException("Montant négatif");
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

		// instanciation d'un service client pour obtenir le total des dépots
		ServiceClient sc = new ServiceClient();

		// exception client non fortuné
		if (sc.getTotalDepots(client) < 500000) {
			throw new ClientNonEligibleException(
					"seuls les clients disposant de plus de 500000€ de dépot peuvent prétendre à la gestion du patrimoine");
		} else
		// on instancie un patrimoine lié au client
		{
			client.setPatrimoine(new Patrimoine(client));
		}

	}

	@Override
	public void ajouterPlacementAuPatrimoineClient(Client client, Placement placement)
			throws ClientNonEligibleException {
		// test si un patrimoine a été déclaré pour le client
		if (client.getPatrimoine() == null) {
			throw new ClientNonEligibleException("Les droits à la gestion du patrimoine n'ont pas été ouverts");
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

	@Override
	public void modifierClient(int id, String nom, String prenom, String email, String adresse, int codepostal,
			String ville, String telephone) {
		idao.modifierClient(id, nom, prenom, email, adresse, codepostal, ville, telephone);
	}

	@Override
	public CompteEpargne chercherCompteEpargne(int idClient) {
		
		return idao.chercherCompteEpargne(idClient);
	}

	@Override
	public CompteCourant chercherCompteCourant(int idClient) {
		
		return idao.chercherCompteCourant(idClient);
	}

	@Override
	public List<Compte> chercherComptes(int idClient) {
		
		List<Compte> comptes = new ArrayList<Compte>();
		
		CompteCourant cc = idao.chercherCompteCourant(idClient);
		CompteEpargne ce = idao.chercherCompteEpargne(idClient);
		
		if (cc != null){
			comptes.add(cc);
		}
		if (ce != null){
			comptes.add(ce);
		}
		
		return comptes;
	}
	
	

}
