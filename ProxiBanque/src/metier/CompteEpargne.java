package metier;
/**
 * Représente le compte épargne 
 * @author Stagiaire Théo VILLAR 23/03/2017
 *
 */
public class CompteEpargne extends Compte {
	
	/**
	 * Taux de rémunération du compte épargne par défaut à 3%
	 * 
	 */
	private float tauxRemuneration=3;
	
	public float getTauxRemuneration() {
		return tauxRemuneration;
	}

	public void setTauxRemuneration(float tauxRemuneration) {
		this.tauxRemuneration = tauxRemuneration;
	}

	/**
	 * Constructeur surchargé 
	 * @param numeroCompte : Identifiant du compte
	 * @param solde	: Montant du compte
	 * @param dateDouverture : Date d'ouverture du compte
	 * @param tauxRemuneration : Taux de rémunération du compte
	 */
	public CompteEpargne(int numeroCompte, float solde, String dateDouverture, float tauxRemuneration) {
		super(numeroCompte, solde, dateDouverture);
		this.tauxRemuneration = tauxRemuneration;
	}
	
	/**
	 * Constructeur : Identifiant du compte
	 * @param numeroCompte	: Montant du compte
	 * @param solde : Montant du compte
	 * @param dateDouverture : Date d'ouverture du compte
	 */
	public CompteEpargne(int numeroCompte, float solde, String dateDouverture) {
		super(numeroCompte, solde, dateDouverture);
	}

	//Constructeur vide
	public CompteEpargne() {
		super();
	} 
	
	
}
