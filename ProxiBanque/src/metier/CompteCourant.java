package metier;

/**
 * Repr�sente le compte courant 
 * @author Stagiaire Th�o VILLAR
 *
 */
public class CompteCourant extends Compte {
	
	// On initialise la variable � 1000 car c'est la valeur par d�faut du d�couvert autoris�	
	/**
	 * Variable d�couvert permettant de param�trer le d�couvert d'un compte
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
 * @param decouvert D�couvert autoris� du compte
 */
	//Constructeur du compte courant avec la prise en compte du d�couvert
	public CompteCourant(int numeroCompte, float solde, String dateDouverture, float decouvert) {
		super(numeroCompte, solde, dateDouverture);
		this.decouvert = decouvert;
	}
	
	//Constructeur du compte courant avec d�couvert par d�faut 
	public CompteCourant(int numeroCompte, float solde, String dateDouverture) {
		super(numeroCompte, solde, dateDouverture);
	}
	
	//Constructeur vide
	public CompteCourant() {
		super();
	}

	@Override
	public String toString() {
		return super.toString() + "CB : " + cb ;
	}
	
	
}
