package metier;

/**
 * Classe de la carte bancaire
 * 
 * @author Stagiaire Th�o VILLAR 23/03/2017
 *
 */
public abstract class CarteBancaire {

	private CompteCourant compte;
	private boolean actif;

	public CompteCourant getCompte() {
		return compte;
	}

	public void setCompte(CompteCourant compte) {
		this.compte = compte;
	}

	public boolean isActif() {
		return actif;
	}

	public void activer() {
		this.actif = true;
	}
	/**
	 * desactiver() => M�thode permettant de desactiver la carte
	 */
	public void desactiver() {
		actif = false;
		System.out.println("La carte a �t� d�sactiv�e");
	}

	public CarteBancaire(CompteCourant compte, boolean actif) {
		super();
		this.compte = compte;
		this.actif = actif;
	}

	public CarteBancaire() {
		super();
	}

	@Override
	public String toString() {
		return "CarteBancaire [actif=" + actif + "]";
	}

}
