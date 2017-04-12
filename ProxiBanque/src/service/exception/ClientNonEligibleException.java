/**
 * 
 */
package service.exception;

/**
 * Exception lev�e lorsqu'un client n'est pas �ligible au service demand�
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
