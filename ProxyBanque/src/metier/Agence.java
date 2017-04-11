/**
 * 
 */
package metier;

/**
 * Represente une agence de la banque
 * 
 * @author Pierre
 *
 */
public class Agence {

	/**
	 * identifiant unique de l'agence
	 */
	private String idAgence;
	/**
	 * date de craetion de l'agence
	 */
	private String dateCreation;
	/**
	 * gerant de l'agence
	 */
	private Gerant gerant;

	/**
	 * Constructeur surchargé de la calsse Agence
	 * 
	 * @param idAgence
	 *            identifiant de l'agence
	 * @param dateCreation
	 *            date de creation de l'agence
	 */
	public Agence(String idAgence, String dateCreation) {
		super();
		this.idAgence = idAgence;
		this.dateCreation = dateCreation;
	}

	
	//assesseurs
	public String getIdAgence() {
		return idAgence;
	}

	public void setIdAgence(String idAgence) {
		this.idAgence = idAgence;
	}

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Gerant getGerant() {
		return gerant;
	}

	public void setGerant(Gerant gerant) {
		this.gerant = gerant;
	}


	@Override
	public String toString() {
		return "idAgence : " + idAgence + " créée le " + dateCreation;
	}

}
