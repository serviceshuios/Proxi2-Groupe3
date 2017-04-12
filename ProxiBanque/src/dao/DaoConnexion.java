package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//creation de cette classe pour all�ger les r�p�titions de la Dao
public class DaoConnexion {
	// on cr�� des attributs fixes pour les donn�es qui seront utilis�s
	private static final String PILOTE = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/bddproxibanque";
	private static final String USER = "root";
	private static final String PWD = "";
	// on cr�� une variable connexion qui est nulle
	private static Connection conn = null;
	// connexion
	public static Connection getConnection() {
		// si la connexion est nulle, il faut en cr�er une
		if (conn == null) {
			try {
				// 1-Charger le pilote
				Class.forName(PILOTE);
				// 2-Creer la connexion
			conn = DriverManager.getConnection(URL, USER, PWD);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return conn;
			// sinon inutile d'en cr�er une et utiliser celle qui existe d�j�
		} else {
			return conn;
		}
	}
	// fermer la connexion
	public static void closeConnection() {
		// si une connexion existe
		if (conn != null) {
			try {
				// la fermer et la r�initialiser
				conn.close();
				conn = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
