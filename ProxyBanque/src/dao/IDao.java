package dao;

import java.util.Collection;

import metier.Client;
import metier.ConseillerClientele;

public interface IDao {

	public ConseillerClientele authentificationConseiller(String login, String motDePasse);
	public Collection<Client> listerClient(int IdConseiller);
}
