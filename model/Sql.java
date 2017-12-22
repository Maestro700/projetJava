package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 
 * @author Wyart Guillaume et Jacobs David
 * Cette classe permet de se connecter � une base de donn�e et de faire des select et update.
 */
public class Sql {
	
	//Cette variable r�cup�re l'adresse de la base de donn�e via le driver JConnect.
	private Connection con;
	//Cette variable nous permet de cr�er un statement qui permet de cr�er des requ�tes SQL.
	private Statement myStm= null;
	//Cette variable permet d'�x�cuter les requ�tes SQL.
	private ResultSet executeSql= null;
	//Cette variable r�cup�re la requ�te que l'on ins�rer.
	private String sql;
	//Cette variable r�cup�re l'id actuel du user qui joue au jeu.
	private int id;
	//Cette variable r�cup�re le nombre de mort actuel du user qui joue au jeu.
	private int nbMortDB;
	//Cette variable r�cup�re le score total du user qui joue au jeu.
	private int scoreTot;
	
	/**
	 * Cette m�thode �talit la connexion avec la base de donn�e.
	 */
	public void connexion() {
		try {
			Class.forName("com.sybase.jdbc4.jdbc.SybDriver");
			con = DriverManager.getConnection("jdbc:sybase:Tds:localhost:2638/projet_java", "DBA", "sql");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Cette m"thode v�rifie si le username que l'on a ins�r� dans le champ texte du login existe d�ja ou pas.
	 * @param username le nom du user
	 * @return true si il esiste d�ja ou false au sinon
	 */
	public boolean verif(String username) {
		try {
			myStm= con.createStatement();
			executeSql = myStm.executeQuery("SELECT * FROM \"tbPlayer\"");
			while(executeSql.next()) {
				if(executeSql.getString(2).contains(username)) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Cette m�thode r�cup�re l'id a utilis� pour le prochain insert.
	 * @return l'id a utilis�
	 */
	public int recupNbId() {
		try {
			myStm= con.createStatement();
			executeSql = myStm.executeQuery("SELECT count(idPlayer) as id FROM \"tbPlayer\"");
			if(executeSql.next()) {
				return executeSql.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * Cette m�thode cr�e un joueur s'il n'existe pas encore dans la base de donn�e au sinon renvoie juste les donn�es du joueur existant.
	 * @param username le nom du user
	 * @param password le mot de passe du user
	 */
	public void login(String username, String password) {
		try {
			myStm= con.createStatement();
			executeSql = myStm.executeQuery("SELECT * FROM \"tbPlayer\"");
			if(executeSql.next()) {
				if(this.verif(username)==false) {
					sql= "INSERT INTO \"tbPlayer\" VALUES ("+this.recupNbId()+", '"+username+"', '"+password+"', 0, 0)";
					this.id=this.recupNbId();
					myStm.executeUpdate(sql);
				}
				else {
					myStm= con.createStatement();
					executeSql = myStm.executeQuery("SELECT * FROM \"tbPlayer\" WHERE username LIKE '"+username+"' OR password LIKE '"+password+"'");
					if(executeSql.next()) {
						this.id=executeSql.getInt(1);
						this.nbMortDB= executeSql.getInt(5);
						this.scoreTot= executeSql.getInt(4);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	/**
	 * Cette m�thode met � jour la base de donn�e en fonction des donn�es r�colt�es dans le jeu.
	 */
	public void update() {
		try {
			if(scoreTot<Mario.getScore()) {
				scoreTot= Mario.getScore();
			}
			myStm= con.createStatement();
			sql= "UPDATE tbPlayer SET score="+scoreTot+",nbMort="+nbMortDB+"WHERE idPlayer="+ id;
			myStm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*****GETTER*****/
	public int getNbMortDB() {
		return nbMortDB;
	}
	
	public int getScoreTot() {
		return scoreTot;
	}
	
	/*****SETTER*****/
	public void setNbMortDB(int nbMortDB) {
		this.nbMortDB = nbMortDB;
	}

	public void setScoreTot(int scoreTot) {
		this.scoreTot = scoreTot;
	}
}

