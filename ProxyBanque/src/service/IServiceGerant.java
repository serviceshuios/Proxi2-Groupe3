/**
 * 
 */
package service;

import java.util.List;

import metier.Agence;
import metier.Compte;
import service.exception.MaillonManquantException;

/**
 * Interface de services disponible pour les gérants
 * 
 * @author Pierre
 *
 */
public interface IServiceGerant {
	
	/**
	 * Effectue l'audit d'une agence
	 * @param agence agence à auditer
	 * @return liste des comptes dépassants les seuils d'alerte de débit
	 */
	public List<Compte> auditer(Agence agence) throws MaillonManquantException;
	

}
