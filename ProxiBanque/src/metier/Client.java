/**
 * 
 */
package metier;

/**
 * represente un client qui est une personne physique
 * 
 * @author Pierre
 *
 */
public class Client extends Personne {

	/**
	 * identifiant du client
	 */
	private int idClient;
	/**
	 * represente la patrimoine du client, null tant que le client à moins de
	 * 500000€ sur ses comptes
	 */
	private Patrimoine patrimoine = null;

	/**
	 * compte courant du client, null si le client n'a pas de compte courant
	 */
	private CompteCourant compteCourant = null;

	/**
	 * compte epargne du client, null si le client n'a pas de compte epargne
	 */
	private CompteEpargne compteEpargne = null;

	/**
	 * attribut d'association conseiller
	 */
	private ConseillerClientele conseiller;

	/**
	 * sert à distinguer les particuliers des entreprises "PARTICULIER" ou
	 * "ENTREPRISE"
	 */
	private String typeClient;

	public Client() {
		super();
	}

	/**
	 * Constructeur surchargé
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
	public Client(String nom, String prenom, String adresse, int codePostal, String ville, String telephone,
			String email, String typeClient) {
		super(nom, prenom, adresse, codePostal, ville, telephone, email);
		this.typeClient = typeClient;
	}

	// assesseurs
	public Patrimoine getPatrimoine() {
		return patrimoine;
	}

	public void setPatrimoine(Patrimoine patrimoine) {
		this.patrimoine = patrimoine;
	}

	public CompteCourant getCompteCourant() {
		return compteCourant;
	}

	public void setCompteCourant(CompteCourant compteCourant) {
		this.compteCourant = compteCourant;
	}

	public CompteEpargne getCompteEpargne() {
		return compteEpargne;
	}

	public void setCompteEpargne(CompteEpargne compteEpargne) {
		this.compteEpargne = compteEpargne;
	}

	public String getNomClient() {
		return this.nom + " " + this.prenom;
	}

	public ConseillerClientele getConseiller() {
		return conseiller;
	}

	public void setConseiller(ConseillerClientele conseiller) {
		this.conseiller = conseiller;
	}

	public String getTypeClient() {
		return typeClient;
	}

	public void setTypeClient(String typeClient) {
		this.typeClient = typeClient;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	@Override
	public String toString() {
		return "ClientParticulier [patrimoine=" + patrimoine + ", compteCourant=" + compteCourant + ", compteEpargne="
				+ compteEpargne + "]";
	}

}
