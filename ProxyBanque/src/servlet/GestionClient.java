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
import service.IServiceConseillerClientele;
import service.ServiceConseillerClientele;

/**
 * Servlet implementation class GestionClient
 */
@WebServlet("/GestionClient")
public class GestionClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	//instanciation du service conseiller
	private IServiceConseillerClientele icc = new ServiceConseillerClientele(); 

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GestionClient() {
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
		
		
		String droits = (String)session.getAttribute("droits");
		int idConseiller = (int)session.getAttribute("idConseiller");
		
		if (action == null){
			//on forward � la jsp index
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		
		//on verifie que l'utilisateur � les droits CONSEILLER, si ce n'est pas le cas on sort en erreur
		else if (!droits.equals("CONSEILLER")){
			
			request.setAttribute("msgErreur","Vous n'avez pas les droits pour effectuer cette action, veuillez vous identifier");
			//on forward � la jsp erreur
			request.getRequestDispatcher("/erreur.jsp").forward(request, response);
			
		}
		
		else if (action.equals("ListerClients")){
			
			//on recup�re la listre des clients
			List<Client> clients = icc.listerClients(idConseiller);
			//on l'ajoute � la requete
			request.setAttribute("clients", clients);
			

			
			//on passe � la jsp listerClients
			request.getRequestDispatcher("/listerClients.jsp").forward(request, response);
			
		}
		//acc�s � la page de modification
		else if (action.equals("Editer")){

			//on initialise la variable client qui sera pass�e � la jsp
			Client client = null;
			
			//on recup�re le parametre idClient s'il existe
			String strIdClient = request.getParameter("idClient");
			
			//si un client � �t� pass� en param�tre on le retrouve et on l'ajoute � la requete
			if (strIdClient != null){
				int idClient = Integer.parseInt(strIdClient);
				client = icc.chercherClient(idClient);
				request.setAttribute("client", client);
			}
			
			//on forword � la jsp editer client
			request.getRequestDispatcher("/editerClient.jsp").forward(request, response);
			
		}
		//effectue la modification d'un client
		else if (action.equals("Modifier")){
			
			//recuperation des parametres
			String strIdClient = request.getParameter("idClient");
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String adresse = request.getParameter("adresse");
			String ville = request.getParameter("ville");
			String strCp = request.getParameter("cp");
			int cp = strCp.equals("") ? 0 : Integer.parseInt(strCp);
			String email = request.getParameter("email");
			String telephone = request.getParameter("telephone");
			
			//non utlisis�e pour le moment
			String typeClient = request.getParameter("typeClient");
			int idCons = Integer.parseInt(request.getParameter("idConseiller"));
			
			//si pas d'id pass� en param�tre on envoi la page d'erreur
			if (strIdClient == null || strIdClient.equals("")){
				
				request.setAttribute("msgErreur","Aucun client n'a �t� s�lectionn� pour la modification");
				//on forward � la jsp erreur
				request.getRequestDispatcher("/erreur.jsp").forward(request, response);
				
			}
			
			else {
				
				int idClient = Integer.parseInt(strIdClient);
				
				//si aucun enregistrement ne correspond en base on envoi la page d'erreur
				if (icc.chercherClient(idClient) == null){
					
					request.setAttribute("msgErreur","L'identifiant di client sp�cifi� ne correspond � aucun enregistrement.");
					//on forward � la jsp erreur
					request.getRequestDispatcher("/erreur.jsp").forward(request, response);
					
				}
				//si tout est bon on fait le traitement
				else {
					
					icc.modifierClient(idClient, nom, prenom, email, adresse, cp, ville, telephone);
					
					//on recup�re le client modifi�
					Client client = icc.chercherClient(idClient);
					//on le retransmet � la requete
					request.setAttribute("client",client);
					//on met un booleen � true pour notifier de la modification
					request.setAttribute("modified",true);
					//on forward � la jsp editerClient
					request.getRequestDispatcher("/editerClient.jsp").forward(request, response);
					
				}

				
			}
			
			
			
			
			
			
		}
		
		else {

			//dans les autres cas on forward � la jsp index
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
