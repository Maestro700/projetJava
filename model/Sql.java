package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 
 * @author Wyart Guillaume et Jacobs David
 * Cette classe permet de se connecter à une base de donnée et de faire des select et update.
 */
public class Sql {
	
	//Cette variable récupère l'adresse de la base de donnée via le driver JConnect.
	private Connection con;
	//Cette variable nous permet de créer un statement qui permet de créer des requêtes SQL.
	private Statement myStm= null;
	//Cette variable permet d'éxécuter les requêtes SQL.
	private ResultSet executeSql= null;
	//Cette variable récupère la requète que l'on insérer.
	private String sql;
	//Cette variable récupère l'id actuel du user qui joue au jeu.
	private int id;
	//Cette variable récupère le nombre de mort actuel du user qui joue au jeu.
	private int nbMortDB;
	//Cette variable récupère le score total du user qui joue au jeu.
	private int scoreTot;
	
	/**
	 * Cette méthode étalit la connexion avec la base de donnée.
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
	 * Cette m"thode vérifie si le username que l'on a inséré dans le champ texte du login existe déja ou pas.
	 * @param username le nom du user
	 * @return true si il esiste déja ou false au sinon
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
	 * Cette méthode récupère l'id a utilisé pour le prochain insert.
	 * @return l'id a utilisé
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
	 * Cette méthode crée un joueur s'il n'existe pas encore dans la base de donnée au sinon renvoie juste les données du joueur existant.
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
	 * Cette méthode met à jour la base de donnée en fonction des données récoltées dans le jeu.
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

