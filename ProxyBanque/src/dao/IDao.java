package dao;

import java.util.List;

import metier.Client;
import metier.ConseillerClientele;

public interface IDao {

	public ConseillerClientele authentificationConseiller(String login, String motDePasse);
	public List<Client> listerClient(int IdConseiller);
	public void ajouterConseiller(ConseillerClientele cc, String login, String mdp);
}
