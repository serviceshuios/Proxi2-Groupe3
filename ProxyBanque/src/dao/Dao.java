package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import metier.Client;
import metier.Compte;
import metier.CompteCourant;
import metier.CompteEpargne;
import metier.ConseillerClientele;

public class Dao implements IDao {

	@Override
	public ConseillerClientele authentificationConseiller(String login, String motDePasse) {
		// TODO Auto-generated method stub
		ConseillerClientele cc = null;
		try {
			Connection conn = DaoConnexion.getConnection();
			PreparedStatement ps = conn
					.prepareStatement("SELECT IdConseiller, Nom, Prenom, Adresse, CodePostal, Ville, Telephone, Email "
							+ "FROM conseiller WHERE Login=? and MotDePasse=? ");
			ps.setString(1, login);
			ps.setString(2, motDePasse);
			// 4-Executer la requete
			ResultSet rs = ps.executeQuery();
			// 5-Presenter les resultats

			if (rs.next()) {
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
	public List<Client> listerClient(int IdConseiller) {
		List<Client> cl = new ArrayList<Client>();
		try {
			Connection conn = DaoConnexion.getConnection();
			PreparedStatement ps = conn.prepareStatement(
					"SELECT IdClient, TypeClient, Nom, Prenom, Adresse, CodePostal, Ville, Telephone, Email "
							+ "FROM client WHERE IdConseiller=? ");
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
			PreparedStatement ps = conn.prepareStatement("INSERT INTO conseiller(Nom, Prenom, Adresse, "
					+ "CodePostal, Ville, Telephone, Email, Login, MotDePasse) " + "VALUES (?,?,?,?,?,?,?,?,?)");
			ps.setString(1, cc.getNom());
			ps.setString(2, cc.getPrenom());
			ps.setString(3, cc.getAdresse());
			ps.setInt(4, cc.getCodePostal());
			ps.setString(5, cc.getVille());
			ps.setString(6, cc.getTelephone());
			ps.setString(7, cc.getEmail());
			ps.setString(8, login);
			ps.setString(9, mdp);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// code qui est executé quelles que soient les étapes précédentes
			// 6-Fermer la connexion
			DaoConnexion.closeConnection();
		}
	}

	@Override
	public void modifierClient(int id, String nom, String prenom, String email, String adresse, int codepostal,
			String ville, String telephone) {
		try {
			Connection conn = DaoConnexion.getConnection();
			// 3-Creer la requete
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE client SET Nom =? , Prenom = ?, Adresse = ? , CodePostal = ?, Ville= ?, Telephone = ?, Email = ? WHERE IdClient = ?");
			ps.setString(1, nom);
			ps.setString(2, prenom);
			ps.setString(3, adresse);
			ps.setInt(4, codepostal);
			ps.setString(5, ville);
			ps.setString(6, telephone);
			ps.setString(7, email);
			ps.setInt(8, id);
			// 4-Executer la requete
			ps.executeUpdate();
			// 5-Presenter les resultats
			// 6-Fermer la connexion
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// code qui est executé quelles que soient les étapes précédentes
			// 6-Fermer la connexion
			DaoConnexion.closeConnection();
		}
	}

	@Override
	public Client chercherClient(int id) {
		Client c = null;
		try {
			Connection conn = DaoConnexion.getConnection();
			// 3-Creer la requete
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM client WHERE IdClient = ?");
			ps.setInt(1, id);
			// 4-Executer la requete
			ResultSet rs = ps.executeQuery();
			// 5-Presenter les resultats
			if (rs.next()) {
				c = new Client();
				c.setIdClient(rs.getInt("IdClient"));
				c.setNom(rs.getString("Nom"));
				c.setPrenom(rs.getString("Prenom"));
				c.setAdresse(rs.getString("Adresse"));
				c.setCodePostal(rs.getInt("CodePostal"));
				c.setVille(rs.getString("Ville"));
				c.setTelephone(rs.getString("Telephone"));
				c.setEmail(rs.getString("Email"));
				c.setTypeClient(rs.getString("TypeClient"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DaoConnexion.closeConnection();
		}
		return c;
	}

	@Override
	public CompteCourant chercherCompteCourant(int idclient) {
		CompteCourant compteC = null;
		try {
			Connection conn = DaoConnexion.getConnection();
			// 3-Creer la requete
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM compte, comptecourant WHERE compte.NumeroCompte=comptecourant.NumeroCompte "
							+ "and compte.IdClient = ?");
			ps.setInt(1, idclient);
			// 4-Executer la requete
			ResultSet rs = ps.executeQuery();
			// 5-Presenter les resultats
			if (rs.next()) {
				compteC = new CompteCourant();
				compteC.setNumeroCompte(rs.getInt("compte.NumeroCompte"));
				compteC.setDateDouverture(rs.getString("DateOuverture"));
				compteC.setDecouvert(rs.getFloat("DecouvertAutorise"));
				compteC.setSolde(rs.getFloat("Solde"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DaoConnexion.closeConnection();
		}
		return compteC;
	}

	@Override
	public CompteEpargne chercherCompteEpargne(int idclient) {
		CompteEpargne compteE = null;
		try {
			Connection conn = DaoConnexion.getConnection();
			// 3-Creer la requete
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM compte, compteepargne WHERE compte.NumeroCompte=compteepargne.NumeroCompte "
							+ "and compte.IdClient = ?");
			ps.setInt(1, idclient);
			// 4-Executer la requete
			ResultSet rs = ps.executeQuery();
			// 5-Presenter les resultats
			if (rs.next()) {
				compteE = new CompteEpargne();
				compteE.setNumeroCompte(rs.getInt("compte.NumeroCompte"));
				compteE.setDateDouverture(rs.getString("DateOuverture"));
				compteE.setTauxRemuneration(rs.getFloat("TauxRemuneration"));
				compteE.setSolde(rs.getFloat("Solde"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DaoConnexion.closeConnection();
		}
		return compteE;
	}

	@Override
	public void ajouterCompteCourant(CompteCourant compteC, int idclient) {
		PreparedStatement ps = null;
		try {
			Connection conn = DaoConnexion.getConnection();
			ps = conn.prepareStatement("INSERT INTO compte(DateOuverture, Solde) VALUES (?,?) WHERE compte.IdClient= ?",
					ps.RETURN_GENERATED_KEYS);
			ps.setString(1, compteC.getDateDouverture());
			ps.setFloat(2, compteC.getSolde());
			ps.setInt(3, idclient);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs != null && rs.first()) {
				// on récupère l'id généré
				long generatedId = rs.getLong(1);

				PreparedStatement ps2 = conn
						.prepareStatement("INSERT INTO comptecourant(DecouvertAutorise, NumeroCompte) VALUES (?,?)");
				ps2.setFloat(1, compteC.getDecouvert());
				ps2.setInt(2, (int) generatedId);
				ps2.executeUpdate();
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

	@Override
	public Compte chercherCompteNum(int numCompte) {
		Compte compte = null;
		try {

			// attributs du compte
			int numeroCompte = 0;
			float solde = 0;
			String dateDouverture = "";

			Connection conn = DaoConnexion.getConnection();
			// 3-Creer la requete
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM compte WHERE NumeroCompte = ?");
			ps.setInt(1, numCompte);
			// 4-Executer la requete
			ResultSet rs = ps.executeQuery();
			// 5-Presenter les resultats
			if (rs.next()) {

				numeroCompte = rs.getInt("NumeroCompte");
				solde = rs.getFloat("Solde");
				dateDouverture = rs.getString("DateOuverture");

				// on recherche les info complementaire du compte dansles autres
				// tables
				PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM comptecourant WHERE NumeroCompte = ?");
				ps1.setInt(1, numeroCompte);
				ResultSet rs1 = ps1.executeQuery();

				PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM compteepargne WHERE NumeroCompte = ?");
				ps2.setInt(1, numeroCompte);
				ResultSet rs2 = ps2.executeQuery();

				//cas où le compte est un compte courant
				if (rs1.next()) {

					compte = new CompteCourant();

					compte.setNumeroCompte(numeroCompte);
					compte.setSolde(solde);
					compte.setDateDouverture(dateDouverture);

					((CompteCourant) compte).setDecouvert(rs1.getInt("DecouvertAutorise"));

				}
				//cas où le compte est un compte epragne
				if (rs2.next()) {

					compte = new CompteEpargne();

					compte.setNumeroCompte(numeroCompte);
					compte.setSolde(solde);
					compte.setDateDouverture(dateDouverture);

					((CompteEpargne) compte).setTauxRemuneration(rs2.getInt("TauxRemuneration"));
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DaoConnexion.closeConnection();
		}
		return compte;
	}

	public void virementCompte(Compte CompteADebiter, Compte CompteACrediter) {
		try {
			Connection conn = DaoConnexion.getConnection();
			PreparedStatement ps = conn.prepareStatement("UPDATE compte SET Solde =? WHERE NumeroCompte= ?");
			ps.setFloat(1, CompteADebiter.getSolde());
			ps.setInt(2, CompteADebiter.getNumeroCompte());
			ps.executeUpdate();
			PreparedStatement ps2 = conn.prepareStatement("UPDATE compte SET Solde =? WHERE NumeroCompte= ?");
			ps2.setFloat(1, CompteACrediter.getSolde());
			ps2.setInt(2, CompteACrediter.getNumeroCompte());
			ps2.executeUpdate();
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
