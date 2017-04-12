package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import metier.ConseillerClientele;
import service.AuthentificationService;
import service.IAuthentificationService;

/**
 * Servlet de gestion des authentifications
 * Servlet implementation class Authentification
 */
@WebServlet("/Authentification")
public class Authentification extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Authentification() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//r�cuperation des param�tres
		String action = request.getParameter("action");
		
		String login = request.getParameter("login");
		String mdp = request.getParameter("mdp");
		
		//
		IAuthentificationService ica = new AuthentificationService();
		
		
		//si pas de parametre action on 
		if (action == null){
			//on forward � la jsp authentification
			request.getRequestDispatcher("/authentification.jsp").forward(request, response);
		}
		
		//si la page � �t� appel�e depuis authentifier
		else if (action.equals("authentifier")){
		
			if (login.equals("") || mdp.equals("")){
				//ajout d'un message d'erreur et forward � la jsp erreur
				request.setAttribute("msgErreur","Saisie incorrecte");
				request.getRequestDispatcher("/erreur.jsp").forward(request, response);
				
			}
			else{
				
				ConseillerClientele conseiller = ica.authentificationConseiller(login, mdp);
				
				//si l'authentification �choue
				if (conseiller == null){
					
					//ajout d'un message d'erreur et forward � la jsp erreur
					request.setAttribute("msgErreur","Echec authentification Conseiller. Essayer � nouveau");
					request.getRequestDispatcher("/erreur.jsp").forward(request, response);
					
				}
				else {
					
					//r�cuperation de la session
					HttpSession session = request.getSession();
					
					//ajout d'attributs � la session
					//droits attribu�s
					session.setAttribute("droits", "CONSEILLER");
					//
					session.setAttribute("idConseiller", conseiller.getIdConseiller());
					session.setAttribute("nom", conseiller.getNom());
					session.setAttribute("prenom", conseiller.getPrenom());
					
					//on forward � la jsp authentification
					request.getRequestDispatcher("/authentification.jsp").forward(request, response);
					
				}
				
			}
			
			
		}
		//si la page � �t� appel�e depuis deconnecter
		else if (action.equals("deconnecter")){
		
			//recuperation de la session
			HttpSession session = request.getSession();
			//destruction de la session
			session.invalidate();
			//on forward � la jsp authentification
			request.getRequestDispatcher("/authentification.jsp").forward(request, response);
		
		}
		//si la page � �t� appel�e d'un autre endroit on forward � la jsp authentification
		
		
		else {
			request.getRequestDispatcher("/authentification.jsp").forward(request, response);
		}

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
