package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//creation de cette classe pour alléger les répétitions de la Dao
public class DaoConnexion {
	// on créé des attributs fixes pour les données qui seront utilisés
	private static final String PILOTE = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/bddproxibanque";
	private static final String USER = "root";
	private static final String PWD = "";
	// on créé une variable connexion qui est nulle
	private static Connection conn = null;
	// connexion
	public static Connection getConnection() {
		// si la connexion est nulle, il faut en créer une
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
			// sinon inutile d'en créer une et utiliser celle qui existe déjà
		} else {
			return conn;
		}
	}
	// fermer la connexion
	public static void closeConnection() {
		// si une connexion existe
		if (conn != null) {
			try {
				// la fermer et la réinitialiser
				conn.close();
				conn = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
