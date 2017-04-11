/**
 * 
 */
package service;

import java.util.List;

import metier.Agence;
import metier.Compte;
import service.exception.MaillonManquantException;

/**
 * Interface de services disponible pour les g�rants
 * 
 * @author Pierre
 *
 */
public interface IServiceGerant {
	
	/**
	 * Effectue l'audit d'une agence
	 * @param agence agence � auditer
	 * @return liste des comptes d�passants les seuils d'alerte de d�bit
	 */
	public List<Compte> auditer(Agence agence) throws MaillonManquantException;
	

}
