package metier;

/**
 * Représente le compte courant 
 * @author Stagiaire Théo VILLAR
 *
 */
public class CompteCourant extends Compte {
	
	// On initialise la variable à 1000 car c'est la valeur par défaut du découvert autorisé	
	/**
	 * Variable découvert permettant de paramétrer le découvert d'un compte
	 */
	private float decouvert=1000; 
	//Association entre la classe CompteCourant et la classe CarteBancaire
	private CarteBancaire cb=null;
	


	public CarteBancaire getCb() {
		return cb;
	}

	public void setCb(CarteBancaire cb) {
		this.cb = cb;
	}

	//Getter & Setter 
	public float getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(float decouvert) {
		this.decouvert = decouvert;
	}
/**
 * 
 * @param numeroCompte Identification du compte
 * @param solde	Montant du compte
 * @param dateDouverture Date d'ouverture du compte
 * @param decouvert Découvert autorisé du compte
 */
	//Constructeur du compte courant avec la prise en compte du découvert
	public CompteCourant(int numeroCompte, float solde, String dateDouverture, float decouvert) {
		super(numeroCompte, solde, dateDouverture);
		this.decouvert = decouvert;
	}
	
	//Constructeur du compte courant avec découvert par défaut 
	public CompteCourant(int numeroCompte, float solde, String dateDouverture) {
		super(numeroCompte, solde, dateDouverture);
	}

	@Override
	public String toString() {
		return super.toString() + "CB : " + cb ;
	}
	
	
}
