/**
 * 
 */
package metier;

/**
 * Represente un placement boursier
 * @author Pierre
 *
 */
public class Placement {
	
	
	private int idPlacement;
	/**
	 * patrimoine contenant le placement
	 */
	private Patrimoine patrimoine;

	public Placement(Patrimoine patrimoine) {
		super();
		this.patrimoine = patrimoine;
	}

	//assesseurs
	public Patrimoine getPatrimoine() {
		return patrimoine;
	}

	public void setPatrimoine(Patrimoine patrimoine) {
		this.patrimoine = patrimoine;
	}

	public int getIdPlacement() {
		return idPlacement;
	}

	public void setIdPlacement(int idPlacement) {
		this.idPlacement = idPlacement;
	}
	
	

}
