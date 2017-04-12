package service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import metier.Client;
import metier.Compte;
import metier.CompteCourant;
import metier.CompteEpargne;
import service.ServiceConseillerClientele;
import service.exception.ClientNonEligibleException;
import service.exception.MontantNegatifException;
import service.exception.SoldeInsuffisantException;

public class ServiceConseillerClienteleTest {

	//Test avec un montant inférieur à 500 000 €
	@Test(expected = ClientNonEligibleException.class)
	public void testouvrirDroitPatrimoineClientMontantInferieur() throws ClientNonEligibleException {
		
		Client c = new Client(null, null, null, 0, null, null, null, null); 	
		CompteCourant cc = new CompteCourant(0,0.0f,null,0.0f);
		c.setCompteCourant(cc);
		ServiceConseillerClientele p =new ServiceConseillerClientele();

		p.ouvrirDroitPatrimoineClient(c);
		fail("Une exception doit être levée avant cette ligne");

	}
	
	//Test avec un montant supérieur à 1 000 000 €
	@Test
	public void testouvrirDroitPatrimoineClientMontantSuperieur() throws ClientNonEligibleException {
		
		Client c = new Client(null, null, null, 0, null, null, null, null); 	
		CompteCourant cc = new CompteCourant(0,1000000f,null,0.0f);
		c.setCompteCourant(cc);
		ServiceConseillerClientele p =new ServiceConseillerClientele();

		p.ouvrirDroitPatrimoineClient(c);

		Assert.assertNotNull(c.getPatrimoine());
	}
	
	@Test(expected = MontantNegatifException.class)
	public void testEffectuerVirementMontantNegatif() throws SoldeInsuffisantException, MontantNegatifException {
		

		ServiceConseillerClientele sc = new ServiceConseillerClientele();

		sc.effectuerVirement(0, 1, -1);
		fail("une exeption doit être levée avant cette ligne");

	}

	@Ignore //test non reporté en V2 car besoin d'interrroger la BDD avec un numéro de compte connu
	@Test(expected = SoldeInsuffisantException.class)
	public void testEffectuerVirementSoldeInsuffisant() throws SoldeInsuffisantException, MontantNegatifException {
		Compte cDebiteur = new CompteCourant(0, 1000, null);
		Compte cCrediteur = new CompteEpargne(0, 3000, null);

		ServiceConseillerClientele sc = new ServiceConseillerClientele();

		//sc.effectuerVirement(cDebiteur, cCrediteur, 2000);
		fail("une exeption doit être levée avant cette ligne");
	}

	@Ignore //test non reporté en V2 car besoin d'interrroger la BDD avec un numéro de compte connu
	@Test
	public void testEffectuerVirementCasNominal() throws SoldeInsuffisantException, MontantNegatifException {
		Compte cDebiteur = new CompteCourant(0, 1000, null);
		Compte cCrediteur = new CompteEpargne(0, 3000, null);

		
		ServiceConseillerClientele sc = new ServiceConseillerClientele();
		
		//sc.effectuerVirement(cDebiteur, cCrediteur, 500);
		
		
		assertEquals(500,cDebiteur.getSolde(),0);
		assertEquals(3500,cCrediteur.getSolde(),0);
	}

}
