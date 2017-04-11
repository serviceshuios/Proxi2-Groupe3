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
	 * @return ConseillerClientele conseiller indentifié ou null si problème d'identification
	 */
	public ConseillerClientele authentificationConseiller(String login, String mdp);

}
