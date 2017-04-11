/**
 * 
 */
package service.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import metier.Client;
import metier.CompteCourant;
import metier.CompteEpargne;
import service.ServiceClient;

/**
 * Regroupeles tests unitaires de la classe ServiceClient
 * 
 * @author Pierre
 *
 */
public class ServiceClientTest {

	private ServiceClient sc;
	private Client c;

	@Before
	public void before() {
		sc = new ServiceClient();
		c = new Client(null, null, null, 0, null, null, null, null);
	}

	@Test
	public void testGetTotalDepots2comptes() {

		CompteCourant cc = new CompteCourant(0, 0, null);
		CompteEpargne ce = new CompteEpargne(0, 0, null);

		cc.setSolde(150f);
		ce.setSolde(-48.2f);

		c.setCompteCourant(cc);
		c.setCompteEpargne(ce);

		Assert.assertEquals(cc.getSolde() + ce.getSolde(), sc.getTotalDepots(c), 0);

	}

	@Test
	public void testGetTotalDepotsCompteCourantSeul() {

		CompteCourant cc = new CompteCourant(0, 0, null);

		cc.setSolde(150f);

		c.setCompteCourant(cc);

		Assert.assertEquals(cc.getSolde(), sc.getTotalDepots(c), 0);

	}

	@Test
	public void testGetTotalDepotsCompteEpargneSeul() {

		CompteEpargne ce = new CompteEpargne(0, 0, null);

		ce.setSolde(-48.2f);

		c.setCompteEpargne(ce);

		Assert.assertEquals(ce.getSolde(), sc.getTotalDepots(c), 0);
	}

	@Test
	public void testGetTotalDepotsAucunCompte() {

		Assert.assertEquals(0, sc.getTotalDepots(c), 0);
	}



}
