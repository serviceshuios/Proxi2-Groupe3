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

public class AuthentificationConseillerTest {

	private ConseillerClientele cc;

	private IDao idao = new Dao();

	@Test
	public void identificationDUnConseillerEnregistre() {
		
		cc = new ConseillerClientele();
		
		//enregistrement d'un conseiller en base avec login + mdp
		idao.ajouterConseiller(cc, "tada", "toudou");
		
		ConseillerClientele aut = idao.authentificationConseiller("tada", "toudou");

		Assert.assertNotNull(aut);
	}
	
	@Test
	public void identificationDUnConseillerNonEnregistre() {
		
		ConseillerClientele aut = idao.authentificationConseiller("unknown", "unknown");

		Assert.assertNull(aut);
	}
	
	@Test
	public void echecIdentificationDUnConseillerEnregistreInvalidPassword() {
		
		cc = new ConseillerClientele();
		
		//enregistrement d'un conseiller en base avec login + mdp
		idao.ajouterConseiller(cc, "tada", "toudou");
		
		ConseillerClientele aut = idao.authentificationConseiller("tada", "invalid");

		Assert.assertNull(aut);
	}
	
	@Test
	public void echecIdentificationDUnConseillerEnregistreLoginInconnu() {
		
		cc = new ConseillerClientele();
		
		//enregistrement d'un conseiller en base avec login + mdp
		idao.ajouterConseiller(cc, "tada", "toudou");
		
		ConseillerClientele aut = idao.authentificationConseiller("unknown", "toudou");

		Assert.assertNull(aut);
	}
	

}
