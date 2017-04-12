/**
 * 
 */
package service.exception;

/**
 * Exception lev�e lors de l'utilisation annormal s'un montant n�gatif
 * 
 * @author Pierre
 *
 */
@SuppressWarnings("serial") //On ne serialise pas pour le moment
public class MontantNegatifException extends Exception {

	public MontantNegatifException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
