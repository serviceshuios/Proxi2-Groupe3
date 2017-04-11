/**
 * 
 */
package metier;

import java.util.ArrayList;
import java.util.List;

/**
 * Represente le portefeuil de placement d'un client
 * 
 * @author Pierre
 *
 */
public class Patrimoine {

	/**
	 * proprietaire du patrimoine
	 */
	private Client client;

	/**
	 * lise des placements du patrimoine
	 */
	private List<Placement> placements = new ArrayList<Placement>();

	/**
	 * Constructeur surchargé de la classe Patrimoine
	 * 
	 * @param client
	 *            proprietaire du patrimoine
	 */
	public Patrimoine(Client client) {
		super();
		this.client = client;
	}

	//
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Placement> getPlacements() {
		return placements;
	}

	public void setPlacements(List<Placement> placements) {
		this.placements = placements;
	}

}
