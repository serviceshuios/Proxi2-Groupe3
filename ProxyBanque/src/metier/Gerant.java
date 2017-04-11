/**
 * 
 */
package metier;

import java.util.ArrayList;
import java.util.List;

/**
 * Represente le gerant d'une agence
 * 
 * @author Pierre
 *
 */
public class Gerant extends Personne {

	private int idGerant;
	/**
	 * represente l'agence de rattachement du gerant
	 */
	private Agence agence;
	/**
	 * represente la liste des Conseillers sous laresponsabilité du gerant
	 */
	private List<ConseillerClientele> conseilersClientele = new ArrayList<ConseillerClientele>();

	/**
	 * Constructeur par défaut de la classe Gerant
	 */
	public Gerant() {
		super();
	}

	/**
	 * Constructeur surchargé de la classe Gerant
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
	 *            numero de telephone
	 */
	public Gerant(String nom, String prenom, String adresse, int codePostal, String ville, String telephone,
			String email, int idGerant) {
		super(nom, prenom, adresse, codePostal, ville, telephone, email);
		this.idGerant = idGerant;
	}

	/**
	 * Constructeur surchargé de la classe Gerant
	 * 
	 * @param nom
	 *            nom
	 * @param prenom
	 *            prenom
	 */
	public Gerant(String nom, String prenom) {
		super(nom, prenom);
		// TODO Auto-generated constructor stub
	}

	// assesseurs de la classe Gerant
	public int getIdGerant() {
		return idGerant;
	}

	public void setIdGerant(int idGerant) {
		this.idGerant = idGerant;
	}

	public Agence getAgence() {
		return agence;
	}

	public void setAgence(Agence agence) {
		this.agence = agence;
	}

	public List<ConseillerClientele> getConseilersClientele() {
		return conseilersClientele;
	}

	public void setConseilersClientele(List<ConseillerClientele> conseilersClientele) {
		this.conseilersClientele = conseilersClientele;
	}

	@Override
	public String toString() {
		return nom + " " + prenom + " gerant de l'agence " + agence + "\nconseilersClientele :" + conseilersClientele;
	}

}
