/**
 * 
 */
package metier;

/**
 * 
 * Représente un personne physique
 * @author Pierre
 *
 */
public class Personne {
	
	/**
	 * nom de la personne
	 */
	protected String nom;
	/**
	 * prenom de la  personne
	 */
	protected String prenom;
	/**
	 * adresse de la personne
	 */
	protected String adresse;
	/**
	 * code postal de la personne
	 */
	protected int codePostal;
	/**
	 * ville de résidence de la personne
	 */
	protected String ville;
	/**
	 * numero de telephone de la personne
	 */
	protected String telephone;
	/**
	 * email dela personne
	 */
	protected String email;
	
	
	/**
	 * Constructeur par defaut de la classe Personne
	 */
	public Personne() {
		super();
	}
	/**
	 * Constructeur surchargé de la classe Personne
	 * @param nom nom de la personne
	 * @param prenom prenom de la personne
	 * @param adresse adresse de la personne
	 * @param codePostal code postal de la personne
	 * @param ville ville de la personne
	 * @param telephone numero de telephone de la personne
	 * @param email email de la personne
	 */
	public Personne(String nom, String prenom, String adresse, int codePostal, String ville, String telephone,
			String email) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.telephone = telephone;
		this.email = email;
	}
	/**
	 * Constructeur surchargé de la classe Personne
	 * @param nom nom de la personne
	 * @param prenom prenom de la personne
	 */
	public Personne(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}
	
	//assesseurs de la classe Personne
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public int getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Personne [nom=" + nom + ", prenom=" + prenom + "]";
	}
	
	
	
	

}
