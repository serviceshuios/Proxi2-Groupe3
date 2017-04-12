package dao.test;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import dao.Dao;
import dao.IDao;
import metier.CompteCourant;
import metier.ConseillerClientele;

public class AjouterModifierEnBaseTest {

	private IDao idao = new Dao();
	
	@Test
	public void testAjouterCompteCourant() {
		//on test que deux lignes sont ajoutée (une dans la table compte l'autre dans la table comptecourant)
		CompteCourant cc = new CompteCourant();
		cc.setSolde(10);
		cc.setDecouvert(500);
		
		Assert.assertEquals(2, idao.ajouterCompteCourant(cc, 1));

	}
	
	@Test
	public void testAjouterConseiller() {
		//on test que une ligne est ajoutée
		ConseillerClientele cc = new ConseillerClientele();
		
		Assert.assertEquals(1, idao.ajouterConseiller(cc, "login", "mdp"));

	}
	
	@Test
	public void testmodifierClientExistant() {
		
		//on test que une ligne est modifiée
		Assert.assertEquals(1, idao.modifierClient(1, "nom", "prenom", "email", "adresse", 00000, "ville", "telephone"));

	}
	
	@Test
	public void testmodifierClientInconnu() {
		
		//on test que rien n'est modifié
		Assert.assertEquals(0, idao.modifierClient(-1, "nom", "prenom", "email", "adresse", 00000, "ville", "telephone"));

	}
	
}
