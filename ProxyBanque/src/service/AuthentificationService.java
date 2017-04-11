/**
 * 
 */
package service;

import metier.ConseillerClientele;

/**
 * Permet l'autentification d'une personne
 * @author Pierre
 *
 *
 */
public class AuthentificationService implements IAuthentificationService {
	
	private IDao idao = new Dao();

	@Override
	public ConseillerClientele authentificationConseiller(String login, String mdp) {
		
		return idao.authentificationConseiller(login, mdp);
	}
	
	

}
