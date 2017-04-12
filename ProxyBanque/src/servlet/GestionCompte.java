package servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import metier.Client;
import metier.Compte;
import metier.CompteCourant;
import metier.CompteEpargne;
import service.IServiceConseillerClientele;
import service.ServiceConseillerClientele;
import service.exception.MontantNegatifException;
import service.exception.SoldeInsuffisantException;

/**
 * Servlet de gestion des actions relatives aux comptes Servlet implementation
 * class GestionCompte
 */
@WebServlet("/GestionCompte")
public class GestionCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// instanciation du service conseiller
	private IServiceConseillerClientele icc = new ServiceConseillerClientele();

	// création d'un formatage pour les float
	
	public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GestionCompte() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		// récuperation des paramètres
		String action = request.getParameter("action");

		String droits = (String) session.getAttribute("droits");
		int idConseiller = (int) session.getAttribute("idConseiller");

		if (action == null) {
			// on forward à la jsp index
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}

		// on verifie que l'utilisateur à les droits CONSEILLER, si ce n'est pas
		// le cas on sort en erreur
		else if (!droits.equals("CONSEILLER")) {

			request.setAttribute("msgErreur",
					"Vous n'avez pas les droits pour effectuer cette action, veuillez vous identifier");
			// on forward à la jsp erreur
			request.getRequestDispatcher("/erreur.jsp").forward(request, response);

		}

		else if (action.equals("Lister")) {

			// on recupère le parametre idClient s'il existe
			String strIdClient = request.getParameter("idClient");

			// on prépare les variable pour le transport des comptes et des
			// infos client
			CompteEpargne compteEpargne = null;
			CompteCourant compteCourant = null;
			Client client = null;

			// si aucun client n'à été passé en paramètre onsort en erreur
			if (strIdClient == null) {

				request.setAttribute("msgErreur", "Aucun client spécifié");

				// on forward à la jsp erreur
				request.getRequestDispatcher("/erreur.jsp").forward(request, response);

			} else {
				int idClient = Integer.parseInt(strIdClient);

				// on récupère la liste des comptes du client
				compteEpargne = icc.chercherCompteEpargne(idClient);
				compteCourant = icc.chercherCompteCourant(idClient);
				// on récupère le client
				client = icc.chercherClient(idClient);

				// on ajoute à la requete
				request.setAttribute("compteEpargne", compteEpargne);
				request.setAttribute("compteCourant", compteCourant);
				request.setAttribute("client", client);

				// on forward à la jsp listeComptes
				request.getRequestDispatcher("/listeComptes.jsp").forward(request, response);
			}

		}

		else if (action.equals("Virement")) {

			// on recupère le parametre idClient s'il existe
			String strIdClient = request.getParameter("idClient");

			// on prépare les variable pour le transport des comptes et des
			// infos client
			List<Compte> comptes;
			Client client = null;

			// si aucun client n'à été passé en paramètre onsort en erreur
			if (strIdClient == null) {

				request.setAttribute("msgErreur", "Aucun client spécifié");

				// on forward à la jsp erreur
				request.getRequestDispatcher("/erreur.jsp").forward(request, response);

			} else {
				int idClient = Integer.parseInt(strIdClient);

				// on récupère la liste des comptes du client

				comptes = icc.chercherComptes(idClient);
				// on récupère le client
				client = icc.chercherClient(idClient);

				// on ajoute à la requete
				request.setAttribute("comptes", comptes);
				request.setAttribute("client", client);

				// on forward à la jsp virementCompteACompteComptes
				request.getRequestDispatcher("/virementCompteACompte.jsp").forward(request, response);
			}

		}

		else if (action.equals("EffectuerVirementInterne")) {

			// on recupère le parametre
			String strCompteADebiter = request.getParameter("compteADebiter");
			String strCompteACrediter = request.getParameter("compteACrediter");
			String strMontant = request.getParameter("montant");

			int compteADebiter = 0;
			int compteACrediter = 0;
			float montant = 0;

			// en cas de saisie incomplete on envoit une erreur
			if (strCompteADebiter == null || strCompteACrediter == null || strMontant == null
					|| strCompteADebiter.equals("") || strCompteACrediter.equals("") || strMontant.equals("")) {

				request.setAttribute("msgErreur", "Saisie incomplète");

				// on forward à la jsp erreur
				request.getRequestDispatcher("/erreur.jsp").forward(request, response);

			}

			else {

				// on essaye de parser les info recuperées et on envoie une
				// erreur en cas de problème
				try {
					
					montant = Float.parseFloat(strMontant);
					//arrondi à 2 décimales
					montant = round(montant, 2);
					// test de l'arrondis System.out.println(montant);
					compteADebiter = Integer.parseInt(strCompteADebiter);
					compteACrediter = Integer.parseInt(strCompteACrediter);
				} catch (IllegalArgumentException e) {

					e.getStackTrace();

					request.setAttribute("msgErreur", "Saisie incorrecte");
					// on forward à la jsp erreur
					request.getRequestDispatcher("/erreur.jsp").forward(request, response);
				}

				// on verifie que les comptes à débiter et à créditer sont
				// différents
				if (compteADebiter == compteACrediter) {

					request.setAttribute("msgErreur",
							"le compte à débiter et le compte à créditer doivent être différents");
					// on forward à la jsp erreur
					request.getRequestDispatcher("/erreur.jsp").forward(request, response);
				}

				else {

					try {
						icc.effectuerVirement(compteADebiter, compteACrediter, montant);
					} catch (SoldeInsuffisantException e) {

						request.setAttribute("msgErreur", e.getMessage());
						// on forward à la jsp erreur
						request.getRequestDispatcher("/erreur.jsp").forward(request, response);

					} catch (MontantNegatifException e) {

						request.setAttribute("msgErreur", e.getMessage());
						// on forward à la jsp erreur
						request.getRequestDispatcher("/erreur.jsp").forward(request, response);
					}

					// on forward à la jsp virementCompteACompteComptes
					request.getRequestDispatcher("/virementCompteACompte.jsp").forward(request, response);
				}
			}

		}

		else {

			// dans les autres cas on forward à la jsp index
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
