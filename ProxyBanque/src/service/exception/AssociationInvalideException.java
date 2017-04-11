/**
 * 
 */
package service.exception;

/**
 * Exception lev�e lors d'une op�ration d'association invalide
 * @author Pierre
 *
 */
@SuppressWarnings("serial") //On ne serialise pas pour le moment
public class AssociationInvalideException extends Exception {

	public AssociationInvalideException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
