/**
 * 
 */
package metier;

import java.util.ArrayList;
import java.util.List;

/**
 * Represente les conseillers clientele
 * 
 * @author Pierre
 *
 */
public class ConseillerClientele extends Personne {

	private int idConseiller;
	/**
	 * attribut d'association : représente le manager du conseiller clientele
	 */
	private Gerant gerant;
	/**
	 * attribut d'association : represente la liste des clients du conseiller
	 * clientele
	 */
	private List<Client> clients = new ArrayList<Client>();

	// assesseurs
	public int getIdConseiller() {
		return idConseiller;
	}

	public void setIdConseiller(int idConseiller) {
		this.idConseiller = idConseiller;
	}

	public Gerant getGerant() {
		return gerant;
	}

	public void setGerant(Gerant gerant) {
		this.gerant = gerant;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	/**
	 * Constructeur par défaut de la classe ConseillerClientele
	 */
	public ConseillerClientele() {
		super();
	}

	/**
	 * Constructeur surchargé de la classe ConseillerClientele
	 * 
	 * @param gerant
	 *            gerant du conseiller clientele
	 * @param clients
	 *            liste clients des du conseiller clientele
	 */
	public ConseillerClientele(Gerant gerant, List<Client> clients) {
		super();
		this.gerant = gerant;
		this.clients = clients;
	}

	/**
	 * Constructeur surchargé de la classe ConseillerClientele
	 * 
	 * @param nom
	 *            nom
	 * @param prenom
	 *            prenom
	 * @param adresse
	 *            adresse
	 * @param codePostal
	 *            code postal
	 * @param ville
	 *            ville
	 * @param telephone
	 *            telephone
	 */
	public ConseillerClientele(String nom, String prenom, String adresse, int codePostal, String ville,
			String telephone, String email) {
		super(nom, prenom, adresse, codePostal, ville, telephone, email);
	}

	/**
	 * Constructeur surchargé de la classe ConseillerClientele
	 * 
	 * @param nom
	 *            nom
	 * @param prenom
	 *            prenom
	 */
	public ConseillerClientele(String nom, String prenom) {
		super(nom, prenom);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return nom + " " + prenom + " clients associées : \n" + clients;
	}

}
