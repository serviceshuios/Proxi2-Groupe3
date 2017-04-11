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
		
		// récuperation des paramètres
		String action = request.getParameter("action");
		
		
		String droits = (String)session.getAttribute("droits");
		int idConseiller = (int)session.getAttribute("idConseiller");
		
		if (action == null){
			//on forward à la jsp index
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		
		//on verifie que l'utilisateur à les droits CONSEILLER
		else if (!droits.equals("CONSEILLER")){
			
			request.setAttribute("msgErreur","Vous n'avez pas les droits pour effectuer cette action, veuillez vous identifier");
			//on forward à la jsp erreur
			request.getRequestDispatcher("/erreur.jsp").forward(request, response);
			
		}
		
		else if (action.equals("ListerClients")){
			
			//on recupère la listre des clients
			List<Client> clients = icc.listerClients(idConseiller);
			//on l'ajoute à la requete
			request.setAttribute("clients", clients);
			

			
			//on passe à la jsp listerClients
			request.getRequestDispatcher("/listerClients.jsp").forward(request, response);
			
		}
		else if (action.equals("modifierClient")){
			
			//TODO
			
		}
		
		else {
			System.out.println("test");
			//dans les autres cas on forward à la jsp index
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
