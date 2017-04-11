package dao.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dao.Dao;
import dao.IDao;
import metier.Client;
import metier.ConseillerClientele;
import service.AuthentificationService;
import service.IAuthentificationService;
import service.ServiceClient;

public class AuthentificationConseillerTEST {

	private ServiceClient sc;
	private Client c;
	private ConseillerClientele cc;

	private IDao idao = new Dao();
	private IAuthentificationService ias = new AuthentificationService();
	@Before
	public void before() {
		sc = new ServiceClient();
	
		
	}

	@Test
	public void testAjoutConseiller()  {
		cc = new ConseillerClientele("tutu", "tati", "12 rue de la tata", 63000, "Clermont-Ferrand", "0390501412", "tutu");
		idao.ajouterConseiller(cc, "tada", "toudou");
		ConseillerClientele aut = idao.authentificationConseiller("tada", "toudou");
	Assert.assertEquals(cc, aut);
		}

	
}
