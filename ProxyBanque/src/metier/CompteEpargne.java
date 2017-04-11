package metier;
/**
 * Repr�sente le compte �pargne 
 * @author Stagiaire Th�o VILLAR 23/03/2017
 *
 */
public class CompteEpargne extends Compte {
	
	/**
	 * Taux de r�mun�ration du compte �pargne par d�faut � 3%
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
	 * Constructeur surcharg� 
	 * @param numeroCompte : Identifiant du compte
	 * @param solde	: Montant du compte
	 * @param dateDouverture : Date d'ouverture du compte
	 * @param tauxRemuneration : Taux de r�mun�ration du compte
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
	
}
