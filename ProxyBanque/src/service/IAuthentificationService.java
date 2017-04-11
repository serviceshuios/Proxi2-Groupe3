/**
 * 
 */
package service;

import metier.ConseillerClientele;

/**
 * @author Pierre
 * Service d'authentification
 *
 */
public interface IAuthentificationService {

	/**
	 * authentifie un conseiller
	 * @param login
	 * @param mdp
	 * @return ConseillerClientele conseiller indentifi� ou null si probl�me d'identification
	 */
	public ConseillerClientele authentificationConseiller(String login, String mdp);

}
