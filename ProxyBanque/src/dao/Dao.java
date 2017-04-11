package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import metier.Client;
import metier.ConseillerClientele;

public class Dao implements IDao {

	@Override
	public ConseillerClientele authentificationConseiller(String login, String motDePasse) {
		// TODO Auto-generated method stub
		ConseillerClientele cc = null;
		try {
			Connection conn = DaoConnexion.getConnection();
			PreparedStatement ps = conn.prepareStatement(
					"SELECT conseiller.IdConseiller, Nom, Prenom, Adresse, CodePostal, Ville, Telephone, Email "
							+ "FROM conseiller, personne WHERE conseiller.IdPersonne = personne.IdPersonne "
							+ "and  Login=? and MotDePasse=? ");
			ps.setString(1, login);
			ps.setString(2, motDePasse);
			// 4-Executer la requete
			ResultSet rs = ps.executeQuery();
			// 5-Presenter les resultats
			if (rs != null) {
				cc = new ConseillerClientele();
				cc.setIdConseiller(rs.getInt("IdConseiller"));
				cc.setNom(rs.getString("Nom"));
				cc.setPrenom(rs.getString("Prenom"));
				cc.setAdresse(rs.getString("Adresse"));
				cc.setCodePostal(rs.getInt("CodePostal"));
				cc.setVille(rs.getString("Ville"));
				cc.setTelephone(rs.getString("Telephone"));
				cc.setEmail(rs.getString("Email"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// code qui est executé quelles que soient les étapes précédentes
			// 6-Fermer la connexion
			DaoConnexion.closeConnection();
		}
		return cc;
	}

	@Override
	public Collection<Client> listerClient(int IdConseiller) {
		Collection<Client> cl = new ArrayList<Client>();
		try {
			Connection conn = DaoConnexion.getConnection();
			PreparedStatement ps = conn.prepareStatement(
					"SELECT client.IdClient, TypeClient, Nom, Prenom, Adresse, CodePostal, Ville, Telephone, Email "
							+ "FROM client, personne WHERE client.IdPersonne = personne.IdPersonne "
							+ "and client.IdConseiller=? ");
			ps.setInt(1, IdConseiller);
			// 4-Executer la requete
			ResultSet rs = ps.executeQuery();
			// 5-Presenter les resultats
			while (rs.next()) {
				Client c = new Client();
				c.setIdClient(rs.getInt("IdClient"));
				c.setTypeClient(rs.getString("TypeClient"));
				c.setNom(rs.getString("nom"));
				c.setPrenom(rs.getString("Prenom"));
				c.setAdresse(rs.getString("Adresse"));
				c.setCodePostal(rs.getInt("CodePostal"));
				c.setVille(rs.getString("Ville"));
				c.setTelephone(rs.getString("Telephone"));
				c.setEmail(rs.getString("Email"));
				cl.add(c);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// code qui est executé quelles que soient les étapes précédentes
			// 6-Fermer la connexion
			DaoConnexion.closeConnection();
		}
		return cl;
	}

	@Override
	public void ajouterConseiller(ConseillerClientele cc, String login, String mdp) {
		try {
			Connection conn = DaoConnexion.getConnection();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO personne(Nom, Prenom, Adresse, "
					+ "CodePostal, Ville, Telephone, Email) " + "VALUES (?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, cc.getNom());
			ps.setString(2, cc.getPrenom());
			ps.setString(3, cc.getAdresse());
			ps.setInt(4, cc.getCodePostal());
			ps.setString(5, cc.getVille());
			ps.setString(6, cc.getTelephone());
			ps.setString(7, cc.getEmail());

			int affectedRows = ps.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating user failed, no rows affected.");
			}

			ResultSet generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				int idpersonne = (int) generatedKeys.getLong(1);

				PreparedStatement ps2 = conn.prepareStatement(
						"INSERT INTO conseiller(Login, MotDePasse)" + "VALUES (?,?) WHERE conseiller.IdPersonne=?");
				ps2.setString(1, login);
				ps2.setString(2, mdp);
				ps2.setInt(3, idpersonne);
				ps2.executeUpdate();
			} else {
				throw new SQLException("Creating user failed, no ID obtained.");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// code qui est executé quelles que soient les étapes précédentes
			// 6-Fermer la connexion
			DaoConnexion.closeConnection();
		}
	}
}
