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

	public void ajouterConseiller(ConseillerClientele cc, String login, String mdp);

	public void modifierClient(int id, String nom, String prenom, String email, String adresse, int codepostal,
			String ville, String telephone);

	public Client chercherClient(int id);

	public CompteCourant chercherCompteCourant(int idclient);

	public CompteEpargne chercherCompteEpargne(int idclient);

	public void ajouterCompteCourant(CompteCourant compteC, int idclient);

	public void virementCompte(Compte CompteADebiter, Compte CompteACrediter);

	public Compte chercherCompteNum(int numCompte);
}
