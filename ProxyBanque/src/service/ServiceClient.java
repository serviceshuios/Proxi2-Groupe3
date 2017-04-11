/**
 * 
 */
package service;

import java.util.ArrayList;
import java.util.List;

import metier.Client;
import metier.Compte;
import metier.CompteCourant;
import metier.CompteEpargne;

/**
 * Service sur les clients
 * 
 * @author Pierre
 *
 */
public class ServiceClient {

	/**
	 * Donne la somme des soldes des comptes du client
	 * 
	 * @param client
	 *            client
	 * @return somme des soldes des comptes du client
	 */
	public float getTotalDepots(Client client) {

		// represente le solde total
		float total = 0;

		// container pour les comptes client
		Compte c;

		// affecte le compte courant du client � c et v�rifie qu'il existe, le
		// cas �ch�ant ajoute son solde au total
		if ((c = client.getCompteCourant()) != null) {
			total += c.getSolde();
		}
		// affecte le compte epargne du client � c et v�rifie qu'il existe, le
		// cas �ch�ant ajoute son solde au total
		if ((c = client.getCompteEpargne()) != null) {
			total += c.getSolde();
		}

		return total;
	}

	/**
	 * donne le bilan de l'audit d'un client
	 * 
	 * @param client
	 * @return liste des comptes dans le rouge avec le nom du client
	 */
	public List<Compte> auditer(Client client) {

		//liste des comptes d�passant les seuils d'alerte
		List<Compte> resultat = new ArrayList<Compte>();
		
		CompteCourant cc;
		CompteEpargne ce;
		int seuil;

		// on d�finit le seuil � ne pas d�passer : -50 000 pour les entreprises,
		// -5 000 par d�faut
		if (client.getTypeClient().equals("ENTREPRISE")) {
			seuil = -50_000;
		} else {
			seuil = -5_000;
		}

		if ((cc = client.getCompteCourant()) != null) {
			if (cc.getSolde() < seuil) {
				resultat.add(cc);
			}
		}
		if ((ce = client.getCompteEpargne()) != null) {
			if (ce.getSolde() < seuil) {
				resultat.add(ce);
			}
		}

		return resultat;
	}

	/**
	 * Donne la liste des information du client
	 * 
	 * @param client
	 *            client
	 * @return liste des information du client
	 */
	public String getInfoClient(Client client) {

		//informations sur l'identit� du client
		String resultat = "Type de client : " + client.getTypeClient() + " Nom :" + client.getNom() + " Prenom :"
				+ client.getPrenom() + " Adresse :" + client.getAdresse() + " Code postal :" + client.getCodePostal()
				+ " Ville :" + client.getVille() + " T�lephone :" + client.getTelephone();

		//informations sur son conpte courant
		if (client.getCompteCourant() != null) // On test si notre client a un
												// compte courant
		{
			CompteCourant cc = client.getCompteCourant();
			resultat += "\nInformation de votre compte courant : Solde : " + cc.getSolde() + "Le d�couvert : "
					+ cc.getDecouvert() + "Le num�ro de compte :" + cc.getNumeroCompte() + "La date d'ouverture :"
					+ cc.getDateDouverture() + "Type de CB :" + cc.getCb();
		}
		if (client.getCompteEpargne() != null) // On test si notre client a un
												// compte �pargne
		{
			CompteEpargne ce = client.getCompteEpargne();
			resultat += "\nInformation de votre compte �pargne : Solde : " + ce.getSolde() + "Taux de r�mun�ration : "
					+ ce.getTauxRemuneration() + "Le num�ro de compte :" + ce.getNumeroCompte()
					+ "La date d'ouverture :" + ce.getDateDouverture();
		}
		return resultat;
	}

}
