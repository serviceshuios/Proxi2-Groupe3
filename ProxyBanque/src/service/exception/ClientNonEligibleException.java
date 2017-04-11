/**
 * 
 */
package service.exception;

/**
 * Exception levée lorsqu'un client n'est pas éligible au service demandé
 * 
 * @author Pierre
 *
 */
@SuppressWarnings("serial") //On ne serialise pas pour le moment
public class ClientNonEligibleException extends Exception {

	public ClientNonEligibleException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
