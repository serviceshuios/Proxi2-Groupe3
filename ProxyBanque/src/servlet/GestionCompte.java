package servlet;

import java.io.IOException;
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

/**
 * Servlet de gestion des actions relatives aux comptes
 * Servlet implementation class GestionCompte
 */
@WebServlet("/GestionCompte")
public class GestionCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// instanciation du service conseiller
	private IServiceConseillerClientele icc = new ServiceConseillerClientele();

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

		// r�cuperation des param�tres
		String action = request.getParameter("action");

		String droits = (String) session.getAttribute("droits");
		int idConseiller = (int) session.getAttribute("idConseiller");

		if (action == null) {
			// on forward � la jsp index
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}

		// on verifie que l'utilisateur � les droits CONSEILLER, si ce n'est pas
		// le cas on sort en erreur
		else if (!droits.equals("CONSEILLER")) {

			request.setAttribute("msgErreur",
					"Vous n'avez pas les droits pour effectuer cette action, veuillez vous identifier");
			// on forward � la jsp erreur
			request.getRequestDispatcher("/erreur.jsp").forward(request, response);

		}

		else if (action.equals("Lister")) {

			// on recup�re le parametre idClient s'il existe
			String strIdClient = request.getParameter("idClient");

			// on pr�pare les variable pour le transport des comptes et des infos client
			CompteEpargne compteEpargne = null;
			CompteCourant compteCourant = null;
			Client client = null;

			// si aucun client n'� �t� pass� en param�tre onsort en erreur
			if (strIdClient == null) {

				request.setAttribute("msgErreur", "Aucun client sp�cifi�");

				// on forward � la jsp erreur
				request.getRequestDispatcher("/erreur.jsp").forward(request, response);

			} else {
				int idClient = Integer.parseInt(strIdClient);

				//on r�cup�re la liste des comptes du client
				compteEpargne = icc.chercherCompteEpargne(idClient);
				compteCourant = icc.chercherCompteCourant(idClient);
				//on r�cup�re le client
				client = icc.chercherClient(idClient);

				//on ajoute � la requete
				request.setAttribute("compteEpargne", compteEpargne);
				request.setAttribute("compteCourant", compteCourant);
				request.setAttribute("client", client);

				// on forward � la jsp listeComptes
				request.getRequestDispatcher("/listeComptes.jsp").forward(request, response);
			}

		}
		
		else if (action.equals("Virement")) {

			// on recup�re le parametre idClient s'il existe
			String strIdClient = request.getParameter("idClient");

			// on pr�pare les variable pour le transport des comptes et des infos client
			List<Compte> comptes;
			Client client = null;

			// si aucun client n'� �t� pass� en param�tre onsort en erreur
			if (strIdClient == null) {

				request.setAttribute("msgErreur", "Aucun client sp�cifi�");

				// on forward � la jsp erreur
				request.getRequestDispatcher("/erreur.jsp").forward(request, response);

			} else {
				int idClient = Integer.parseInt(strIdClient);

				//on r�cup�re la liste des comptes du client
				
				comptes = icc.chercherComptes(idClient);
				//on r�cup�re le client
				client = icc.chercherClient(idClient);

				//on ajoute � la requete
				request.setAttribute("comptes", comptes);
				request.setAttribute("client", client);

				// on forward � la jsp virementCompteACompteComptes
				request.getRequestDispatcher("/virementCompteACompteComptes.jsp").forward(request, response);
			}

		}
		
		else {

			// dans les autres cas on forward � la jsp index
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
