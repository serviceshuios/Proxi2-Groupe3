/**
 * 
 */
package service;

import java.util.ArrayList;
import java.util.List;

import metier.Agence;
import metier.Client;
import metier.Compte;
import metier.ConseillerClientele;
import metier.Gerant;
import service.exception.MaillonManquantException;


/**
 * @author Pierre
 *
 */
public class ServiceGerant implements IServiceGerant {

	/* (non-Javadoc)
	 * @see service.IServiceGerant#auditer(metier.Agence)
	 */
	@Override
	public List<Compte> auditer(Agence agence) throws MaillonManquantException {
		
		//resultat listera tous les comptes dont le débit dépasse le niveau d'alerte
		List<Compte> resultat =  new ArrayList<Compte>();
		//l'algo retrouve la liste des client d'une agence en passant par la chaine d'association Agence-Gerant-ConseillerClientele-Client
		
		//niveau Gerant
		Gerant gerant = agence.getGerant();
		
		//exception levée si la chaine d'association est rompue
		if (gerant == null){
			throw new MaillonManquantException("aucun gérant associé à l'agence");
		}
		
		//niveau ConseillersClientele
		List<ConseillerClientele> conseillers = gerant.getConseilersClientele();
		
		//exception levée si la chaine d'association est rompue
		if (conseillers.size()==0){
			throw new MaillonManquantException("aucun conseiller associé au gérant");
		}
		
		//niveau Client
		List<Client> clients = new ArrayList<Client>();
		
		// pour chaque conseillers
		for (ConseillerClientele cc : conseillers){
			 //on retrouve les clients du conseillers
			List<Client> clientsCc = cc.getClients();
			
			//on ajoute les clients du conseiller à la liste des clients
			clients.addAll(clientsCc);
			
		}

		//instanciation d'un ServiceClient pour analyser les comptes des client
		ServiceClient sc = new ServiceClient();
		
		//pour chaque client on obtient le bilan d'audit personnel
		for (Client client : clients){
			
			resultat.addAll(sc.auditer(client));
			
		}
		

		return resultat;
	}

}
