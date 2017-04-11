/**
 * 
 */
package service.exception;

/**
 * Exception levée lors d'une opération nécessitant un solde supérieur au solde
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
