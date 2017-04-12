package dao;

import java.util.List;

import metier.Client;
import metier.Compte;
import metier.CompteCourant;
import metier.CompteEpargne;
import metier.ConseillerClientele;

public interface IDao {

	public ConseillerClientele authentificationConseiller(String login, String motDePasse);

	public List<Client> listerClient(int IdConseiller);

	public int ajouterConseiller(ConseillerClientele cc, String login, String mdp);

	public int modifierClient(int id, String nom, String prenom, String email, String adresse, int codepostal,
			String ville, String telephone);

	public Client chercherClient(int id);

	public CompteCourant chercherCompteCourant(int idclient);

	public CompteEpargne chercherCompteEpargne(int idclient);

	public int ajouterCompteCourant(CompteCourant compteC, int idclient);

	public int virementCompte(Compte CompteADebiter, Compte CompteACrediter);

	public Compte chercherCompteNum(int numCompte);
}
