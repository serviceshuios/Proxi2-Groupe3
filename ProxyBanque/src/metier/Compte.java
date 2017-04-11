package metier;

/**
 * 
 * @author Stagiaire Théo VILLAR
 *
 */
public abstract class Compte {
	
	//Attributs de la classe Compte --------------------------------------------

	protected int numeroCompte;
	protected float solde;
	protected String dateDouverture;
	private Client client;
	
	//Getters & Setters de la classe Compte ------------------------------------
	
	
	
	public int getNumeroCompte() {
		return numeroCompte;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public void setNumeroCompte(int numeroCompte) {
		this.numeroCompte = numeroCompte;
	}
	public float getSolde() {
		return solde;
	}
	public void setSolde(float solde) {
		this.solde = solde;
	}
	public String getDateDouverture() {
		return dateDouverture;
	}
	public void setDateDouverture(String dateDouverture) {
		this.dateDouverture = dateDouverture;
	}
	
	//Constructeurs avec et sans arguments -------------------------------------
	
	/**
	 * Constructeur de la classe Compte
	 * @param numeroCompte => Argument numeroCompte de la classe Compte
	 * @param solde => Argument solde de la classe Compte
	 * @param dateDouverture => Argument dateDouverture de la classe Compte
	 */
	public Compte(int numeroCompte, float solde, String dateDouverture) {
		super();
		this.numeroCompte = numeroCompte;
		this.solde = solde;
		this.dateDouverture = dateDouverture;
	}

	/**
	 * Constructeur sans argument de la classe Compte
	 */
	public Compte() {
		super();
	}
	@Override
	public String toString() {
		return "Compte [numeroCompte=" + numeroCompte + ", solde=" + solde + ", dateDouverture=" + dateDouverture + "]";
	}
	

}
