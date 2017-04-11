/**
 * 
 */
package service.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import metier.Agence;
import metier.Client;
import metier.Compte;
import metier.CompteCourant;
import metier.CompteEpargne;
import metier.ConseillerClientele;
import metier.Gerant;
import service.ServiceGerant;
import service.exception.MaillonManquantException;

/**
 * @author Stagiaire
 *
 */
public class ServiceGerantTest {

	private ServiceGerant sg = new ServiceGerant();
	private Agence a;

	@Before
	public void before() {
		a = new Agence(null, null);
	}

	/**
	 * Test de la levée d'exception lorsque le lien Agence-Gerant n'est pas fait
	 * 
	 * @throws MaillonManquantException impossible de parcourrir la chaine d'associations
	 */
	@Test(expected = MaillonManquantException.class)
	public void testAuditerAgenceSansGerant() throws MaillonManquantException {

		sg.auditer(a);
		fail("une exeption doit être levée avant cette ligne");

	}

	/**
	 * Test de la levée d'exception lorsque le lien Gerant-ConseillersClientele
	 * n'est pas fait
	 * 
	 * @throws MaillonManquantException impossible de parcourrir la chaine d'associations
	 */
	@Test(expected = MaillonManquantException.class)
	public void testAuditerAgenceSansConseiller() throws MaillonManquantException {

		a.setGerant(new Gerant());
		sg.auditer(a);
		fail("une exeption doit être levée avant cette ligne");

	}

	/**
	 * Test du cas audit avec des comptes dépassant les seuils de débit
	 * autorisés
	 * 
	 * @throws MaillonManquantException impossible de parcourrir la chaine d'associations
	 */
	@Test
	public void testAuditerAgenceCasNominalAgenceKO() throws MaillonManquantException {

		// création des objets
		List<Compte> expected = new ArrayList<Compte>(); 
		
		Gerant g = new Gerant();

		ConseillerClientele cc1 = new ConseillerClientele();
		ConseillerClientele cc2 = new ConseillerClientele();
		ConseillerClientele cc3 = new ConseillerClientele();

		Client ceOk = new Client("nom", "prenom", "adresse", 00000, "ville", "telephone", "ENTREPRISE", "toto");
		Client ceAlerte = new Client("nom", "prenom", "adresse", 00000, "ville",
				"telephone","ENTREPRISE", "toto");
		Client cpOk = new Client("OK", "particulier", "adresse", 00000, "ville", "telephone", "PARTICULIER", "toto");
		Client cpAlerte = new Client("alerte", "particulier", "adresse", 00000, "ville", "telephone", "PARTICULIER", "toto");

		Client cpSansCompte = new Client("SansCompte", "particulier", "adresse", 00000, "ville",
				"telephone","PARTICULIER", "toto");

		// affectation des comptes aux clients

		// comptes devant apparaitre dans l'audit
		ceAlerte.setCompteCourant(new CompteCourant(0, -50_000.01f, null));
		cpAlerte.setCompteCourant(new CompteCourant(0, -5_000.02f, null));
		ceAlerte.setCompteEpargne(new CompteEpargne(0, -50_000.03f, null));
		cpAlerte.setCompteEpargne(new CompteEpargne(0, -5_000.04f, null));
		
		
		expected.add(cpAlerte.getCompteCourant());  
		expected.add(cpAlerte.getCompteEpargne());
		expected.add(ceAlerte.getCompteCourant());
		expected.add(ceAlerte.getCompteEpargne());
		

		// comptes ne devant aps apparaitre dans l'audit
		cpOk.setCompteCourant(new CompteCourant(0, -5_000, null));
		ceOk.setCompteEpargne(new CompteEpargne(0, -50_000, null));

		// affectation des clients aux conseillers
		List<Client> lc1 = new ArrayList<Client>();
		List<Client> lc3 = new ArrayList<Client>();
		lc1.add(cpAlerte);
		lc1.add(ceOk);
		lc1.add(cpSansCompte);
		lc3.add(ceAlerte);
		lc3.add(cpOk);

		cc1.setClients(lc1);
		cc3.setClients(lc3);

		// affectaction des conseillers au gerant
		List<ConseillerClientele> lcc = new ArrayList<ConseillerClientele>();
		lcc.add(cc1);
		lcc.add(cc2);
		lcc.add(cc3);

		g.setConseilersClientele(lcc);

		// affectation du gerant au conseiller
		a.setGerant(g);

		assertEquals(expected,sg.auditer(a));


	}

	/**
	 * Test du cas audit sans compte dépassant les seuils de débit autorisés
	 * 
	 * @throws MaillonManquantException impossible de parcourrir la chaine d'associations
	 */
	@Test
	public void testAuditerAgenceCasNominalAgenceOK() throws MaillonManquantException {

		List<Compte> expected = new ArrayList<Compte>();
		// création des objets
		Gerant g = new Gerant();

		ConseillerClientele cc1 = new ConseillerClientele();
		ConseillerClientele cc2 = new ConseillerClientele();
		ConseillerClientele cc3 = new ConseillerClientele();

		Client ceOk = new Client("nom", "prenom", "adresse", 00000, "ville", "telephone", "ENTREPRISE", "toto");

		Client cpOk = new Client("OK", "particulier", "adresse", 00000, "ville", "telephone", "PARTICULIER", "toto");

		Client cpSansCompte = new Client("SansCompte", "particulier", "adresse", 00000, "ville",
				"telephone", "PARTICULIER", "toto");

		// affectation des comptes aux clients

		// comptes ne devant pas apparaitre dans l'audit
		cpOk.setCompteCourant(new CompteCourant(0, -5_000, null));
		ceOk.setCompteEpargne(new CompteEpargne(0, -50_000, null));

		// affectation des clients aux conseillers
		List<Client> lc1 = new ArrayList<Client>();
		List<Client> lc3 = new ArrayList<Client>();
		lc1.add(ceOk);
		lc1.add(cpSansCompte);
		lc3.add(cpOk);

		cc1.setClients(lc1);
		cc3.setClients(lc3);

		// affectaction des conseillers au gerant
		List<ConseillerClientele> lcc = new ArrayList<ConseillerClientele>();
		lcc.add(cc1);
		lcc.add(cc2);
		lcc.add(cc3);

		g.setConseilersClientele(lcc);

		// affectation du gerant au conseiller
		a.setGerant(g);

		assertEquals(expected, sg.auditer(a));

	}

}
