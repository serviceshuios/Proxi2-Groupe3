/**
 * 
 */
package service.exception;

/**
 * Exception lev�e lors d'une op�ration n�cessitant un solde sup�rieur au solde
 * disponible
 * 
 * @author Pierre
 *
 */
@SuppressWarnings("serial") //On ne serialise pas pour le moment
public class SoldeInsuffisantException extends Exception {

	public SoldeInsuffisantException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
