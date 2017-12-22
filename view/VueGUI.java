package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;
import model.Bloc;
import model.DrapeauFin;
import model.Ennemi;
import model.Mario;
import model.Objet;
import model.Piece;
import model.Son;
import model.Sql;
import model.Tuyau;
import model.goomba;
import model.koopa;
/**
 * 
 * @author Wyart Guillaume et Jacobs David
 * Cette classe permet de créer l'interface graphique de notre jeu, mario. Elle étend Keylistener pour être à l'écoute du clavier et ActionListener pour être à l'éoute des boutons de cette classe.
 */
public class VueGUI extends VueGenerale implements KeyListener, ActionListener{
	
	//Cette variable nous donne le chiffre du niveau actuelle.
	private int level;
	//Cette variable nous permet fixer un temps imparti pour gagner la niveau actuel. 
	private int chrono;
	
	//Cette variable définitla police des JPanel.
	private Font police;
	//Cette variable crée un bouton restart qui apparait lorsque l'on a perdu le niveau.
	private JButton restart;
	//Cette variable crée un bouton pour passer le niveau lorsque celui-ci est gagné.
	private JButton nextLevel;
	//Cette variable crée un bouton pour lancer la partie en solo.
	private JButton solo;
	//Cette variable crée un bouton pour lancer la partie en multijoueur.
	private JButton multijoueur;
	//Cette variable valide les identifiants.
	private JButton valider;
	
	//Cette variable crée la fenètre de jeu, intitulé mario.
	private JFrame frame;
	//Cette variable crée le conteneur dans la fenètre qui nous permet d'afficher des éléments.
	private JPanel conteneur;
	//Cette variable crée le conteneur dans la fenètre qui se lance au début de la partie pour savoir si on joue en solo ou multijoueur.
	private JPanel menu;
	//Cette variable crée le conteneur dans la fenètre qui se lance lorque l'on a fini tout les niveaux.
	private JPanel menuFin;
	//Cette variable crée le conteneur dans la fenètre qui se lance dés le début pour se connecter.
	private JPanel menuConnection;
	
	//Cette variable crée l'icon de l'image de fond.
	private ImageIcon fond;
	
	//Cette variable crée l'image pour le solo.
	private Image fondMenuSolo;
	//Cette variable crée l'image pour le multijoueur.
	private Image fondMenuMulti;
	//Cette variable crée l'image pour mettre le nom du jeu.
	private Image fondMenuLabel;
	//Ces variables créent l'image de fond du jeu.
	private Image fondImg;
	private Image fondImg2;
	//Cette variable crée l'image du chateau.
	private Image chateau;
	//Cette variable crée l'image de la flèche de départ.
	private Image depart;
	//Cette variable crée ll'image du chateau de fin.
	private Image chateauFin;
	//Cette variable crée l'image si l'on a perdu la partie.
	private Image gameOver;
	//Cette variable crée l'image si l'on a gagné la partie.
	private Image victory;
	//Cette variable crée l'image de la vie actuelle de mario.
	private Image coeur;
	//Cette variable crée l'image si l'on arrive à la fin de tout les niveaux.
	private Image congratulation;
	//Cette variable crée l'image du menu ou l'on se connecte.
	private Image marioMenu;
	
	//Cette variable crée le son si l'on a perdu la partie.
	private Son fini;
	//Cette variable crée le son si l'on a gagné la partie.
	private Son perdu;
	//Cette variable crée le son du menu.
	private Son menuSon;
	//Cette variable crée le son si l'on a fait tout les niveaux.
	private Son congrats;
	
	//Cette variable sert a se connecter avec son nom d'utilisateur.
	private JTextField username;
	//Cette variable sert a se connecter avec le mot de passe lié à l'utilisateur.
	private JTextField password;
	
	//Cette variable crée le tableau qui regroupe tout les objets du niveau.
	private ArrayList <Objet> tab;
	//Cette variable crée le tableau qui regroupe tout les ennemis du niveau.
	private ArrayList <Ennemi> tabEnnemi;
	//Cette variable crée le tableau qui regroupe toutes les pièces du niveau.
	private ArrayList <Piece> piece;
	
	//Cette variable crée une instance de la classe Sql.
	private Sql sql;
	
	/**
	 * Cette méthode est le constructeur de la classe et définit toute les variables
	 * et crée via les paintComponent des JPanel l'affichage de tout les éléments.
	 * @param mario permet de récupérer le tableau des marios.
	 * @param control permet de récupérer le controller qui gère le lien entre cette classe et le model.
	 */
	@SuppressWarnings("serial")
	public VueGUI(ArrayList <Mario> mario, Controller control) {
		
		super(mario, control);
		
		sql= new Sql();
		//crée la connexion à notre db.
		sql.connexion();
		
		this.police= new Font("GILL SANS ULTRA BOLD CONDENSED", Font.PLAIN, 18);
		this.level=1;
		this.chrono=60;
		
		this.fond= new ImageIcon(getClass().getResource("/images/fondEcran.png"));
		this.fondMenuSolo= new ImageIcon(getClass().getResource("/images/marioMenu.png")).getImage();
		this.fondMenuMulti= new ImageIcon(getClass().getResource("/images/multiMenu.png")).getImage();
		this.fondMenuLabel= new ImageIcon(getClass().getResource("/images/marioMenuLabel.png")).getImage();
		this.fondImg = this.fond.getImage();
		this.fondImg2= this.fond.getImage();
		this.chateau=new ImageIcon(getClass().getResource("/images/chateau.png")).getImage();
		this.depart=new ImageIcon(getClass().getResource("/images/depart.png")).getImage();
		this.chateauFin=new ImageIcon(getClass().getResource("/images/chateauFin.png")).getImage();
		this.gameOver=new ImageIcon(getClass().getResource("/images/gameOver.png")).getImage();
		this.victory=new ImageIcon(getClass().getResource("/images/Victory.png")).getImage();
		this.coeur= new ImageIcon(getClass().getResource("/images/coeur.png")).getImage();
		this.congratulation= new ImageIcon(getClass().getResource("/images/congrats.png")).getImage();
		this.marioMenu= new ImageIcon(getClass().getResource("/images/marioMenuConnection.png")).getImage();
		
		frame = new JFrame("mario");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1996, 437);
		frame.setLocationRelativeTo(null);
	 	frame.setAlwaysOnTop(true);              
		frame.setResizable(false);
		frame.setVisible(true);
		
		restart= new JButton("restart");
		restart.setBounds(850, 300, 100, 30);
		restart.addActionListener(this);
		
		nextLevel= new JButton("Next Level");
		nextLevel.setBounds(850, 300, 100, 30);
		nextLevel.addActionListener(this);
		
		solo = new JButton("solo");
		solo.setBounds(200, 200, 100, 30);
		solo.addActionListener(this);
		solo.setBackground(new Color(44, 62, 80));
		solo.setForeground(Color.WHITE);
		
		multijoueur= new JButton("multijoueur");
		multijoueur.setBounds(1625, 200, 100, 30);
		multijoueur.addActionListener(this);
		multijoueur.setBackground(new Color(44, 62, 80));
		multijoueur.setForeground(Color.WHITE);
		
		menuSon= new Son("/son/marioMenuSon.wav");
		
		valider = new JButton("Valider");
		valider.setBounds(825, 240, 100, 30);
		valider.addActionListener(this);
		valider.setBackground(new Color(44, 62, 80));
		valider.setForeground(Color.WHITE);
		
		username= new JTextField();
		username.setBounds(800, 150, 150, 30);
		username.setBackground(new Color(44, 62, 80));
		username.setForeground(Color.WHITE);
		
		password= new JTextField();
		password.setBounds(800, 200, 150, 30);
		password.setBackground(new Color(44, 62, 80));
		password.setForeground(Color.WHITE);
		
		menuConnection= new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponents(g);
				g.setFont(police);
				g.setColor(Color.WHITE);
				g.drawImage(marioMenu, 260, -20, 1300, 480, null);
				g.drawString("Username : ", 655, 170);
				g.drawString("Password : ", 655, 220);
				
				menuConnection.add(valider);
				menuConnection.add(username);
				menuConnection.add(password);
			}
		};
		
		menuFin= new JPanel() {
			public void paintComponent(Graphics g) {
				g.setFont(police);
				g.drawImage(congratulation, 600, 50,600, 275, null);
				g.drawString("Votre score total est : "+Mario.getScore(), 780, 300);
				g.drawString("Meilleur score : "+sql.getScoreTot(), 810, 320);
				g.drawString("Vous êtes mort "+Mario.getNbMort()+ " fois dans cette partie", 750, 350);
				g.drawString("Vous êtes mort "+sql.getNbMortDB()+ " fois au total", 770, 370);
				setCongrats(new Son("/son/congrats.wav"));
			}
		};
		
		menu = new JPanel() {
			public void paintComponent(Graphics h) {
				menu.paintComponents(h);
				h.setFont(police);
				h.drawImage(fondMenuLabel, 350, -50, 1200, 500, null);
				h.drawImage(fondMenuSolo, 200, 0, 300, 400, null);
				h.drawImage(fondMenuMulti, 1300, 0, null);
				
				menu.add(solo);
				menu.add(multijoueur);
			}
		};
		
		conteneur = new JPanel() {
			public void paintComponent(Graphics g) {
				conteneur.paintComponents(g);
				g.drawImage(fondImg, 0, 0, null);
				g.drawImage(fondImg2, 993, 0, null);
				g.drawImage(chateau, 0, 167, null);
				g.drawImage(depart, 200, 305, null);
				g.drawImage(chateauFin, 1800, 218, null);
				g.setFont(police);
				g.setColor(new Color(44, 62, 80));
				g.drawString("Votre score acuel est : "+Mario.getScore(), 250, 20);
				g.drawString(username.getText(), 0, 20);
				g.drawString("HP : ", 650, 18);
				g.drawString("Temps restant : "+chrono, 1000, 18);
				g.drawString("Votre niveau actuel : "+level, 1700, 20);
				for(int i=0; i<tab.size(); i++) {
					if(tab.get(i).getClass().getName()!="model.Piece") {
						g.drawImage(tab.get(i).getImg(), tab.get(i).getX(), tab.get(i).getY(), null);
					}
					else {
						Piece piece= (Piece) tab.get(i);
						if(piece.isEstRamasse()==false) {
							g.drawImage(tab.get(i).getImg(), tab.get(i).getX(), tab.get(i).getY(), null);
						}
					}
					if(tab.get(i).getClass().getName()=="model.DrapeauFin") {
						for(int j=0; j<mario.size(); j++) {
							if(mario.get(j).isFin()==true) {
								if(level<3) {
									fini = new Son("/son/gameVictory.wav");
									g.drawImage(victory, 650, 0,500, 300, null);
									conteneur.add(nextLevel);
								}
								else {
									frame.setContentPane(menuFin);
									menuFin.requestFocusInWindow();
									frame.setVisible(true);
									sql.update();
								}
							}
						}
					}
				}
				for(int i=0; i<tabEnnemi.size(); i++) {
					if(tabEnnemi.get(i).isVivant()==true) {
						g.drawImage(tabEnnemi.get(i).getImg(), tabEnnemi.get(i).getX(), tabEnnemi.get(i).getY(), null);
					}	
					else {
						if(tabEnnemi.get(i).getClass().getName()=="model.koopa") {
							tabEnnemi.get(i).changeImg("champEcraseDroite.png");
							g.drawImage(tabEnnemi.get(i).getImg(), tabEnnemi.get(i).getX(), tabEnnemi.get(i).getY()+17, null);
						}
						else {
							tabEnnemi.get(i).changeImg("tortueFermee.png");
							g.drawImage(tabEnnemi.get(i).getImg(), tabEnnemi.get(i).getX(), tabEnnemi.get(i).getY()+25, null);
						}
					}
				}
				for(int i=0; i< mario.size(); i++) {
					if(mario.get(i).isVivant()==true) {
						g.drawImage(mario.get(i).getImg(), mario.get(i).getX(), mario.get(i).getY(), 28, 50, null);
						for(int j=0; j<Mario.getHP(); j++) {
							g.drawImage(coeur, 700+j*20, 5, null);
						}
					}
					else {
						Mario.setRunning(false);
						perdu= new Son("/son/gameOver.wav");
						mario.get(i).changeImg("tombe.png");
						g.drawImage(mario.get(i).getImg(), mario.get(i).getX(), mario.get(i).getY()+10, null);
						g.drawImage(gameOver, 750, 0, 300,300, null);
						conteneur.add(restart);
					}
					if(chrono==0) {
						conteneur.add(restart);
						Mario.setRunning(false);
						perdu= new Son("/son/gameOver.wav");
						g.drawImage(gameOver, 750, 0, 300,300, null);
					}
				}
			}
		};
		
		conteneur.addKeyListener(this);
		conteneur.setFocusable(true);
		
		frame.setContentPane(menuConnection);
	}
	
	/**
	 * Cette méthode permet de créer le 2eme joueur et le rajoute dans le tableau.
	 */
	public void createJoueur2() {
		Mario mario2= new Mario(50, 320, "luigiArretDroite.png");
		mario2.setNbJoueur(2);
		this.mario.add(mario2);
	}

	/**
	 * Cette méthode crée les niveaux.
	 */
	public void createLevel() {
		if(this.level==1) {
			this.tab=new ArrayList<Objet>();
			this.piece= new ArrayList<Piece>();
			this.tab.add(new Tuyau(450, 302, "tuyauRouge.png"));
			this.tab.add(new Tuyau(1000, 302, "tuyauRouge.png"));
			this.tab.add(new Tuyau(1300, 302, "tuyauRouge.png"));
			this.tab.add(new Bloc(600, 250, "bloc.png"));
			this.tab.add(new Bloc(900, 240, "bloc.png"));
			this.tab.add(new Bloc(1500, 270, "bloc.png"));
			this.tab.add(new Bloc(250, 337, "bloc.png"));
			this.tab.add(new Bloc(280, 337, "bloc.png"));
			this.tab.add(new DrapeauFin(1700, 187, "drapeau.png"));
			this.piece.add(new Piece(460, 272, "piece.png"));
			this.piece.add(new Piece(904, 212, "piece.png"));
			this.piece.add(new Piece(700, 272, "piece.png"));
			this.piece.add(new Piece(270, 250, "piece.png"));
			this.piece.add(new Piece(1505, 300, "piece.png"));
			this.piece.add(new Piece(1200, 272, "piece.png"));
			this.tab.addAll(piece);
			
			for(int i=0; i < piece.size(); i++) {
				piece.get(i).movePiece();
			}
			
			for(int i=0; i < mario.size(); i++) {
				for(int j=0; j < tab.size(); j++) {
					mario.get(i).getIsCollision().add(false);
				}
			}
			
			this.tabEnnemi=new ArrayList<Ennemi>();
			this.tabEnnemi.add(new koopa(600, 340, "champMarcheDroite.png"));
			this.tabEnnemi.add(new koopa(1500, 340, "champMarcheDroite.png"));
			this.tabEnnemi.add(new koopa(1600, 340, "champMarcheDroite.png"));
			this.tabEnnemi.add(new koopa(900, 340, "champMarcheDroite.png"));
			this.tabEnnemi.add(new koopa(320, 340, "champMarcheDroite.png"));
			this.tabEnnemi.add(new koopa(1100, 340, "champMarcheDroite.png"));
			this.tabEnnemi.add(new koopa(1050, 340, "champMarcheDroite.png"));
			this.tabEnnemi.add(new koopa(1400, 340, "champMarcheDroite.png"));
			this.tabEnnemi.add(new goomba(850, 320, "tortueMarcheDroite.png"));
			this.tabEnnemi.add(new goomba(550, 320, "tortueMarcheDroite.png"));
			this.tabEnnemi.add(new goomba(1250, 320, "tortueMarcheDroite.png"));
			
			for(int i=0; i < tabEnnemi.size(); i++) {
				for(int j=0; j < tab.size(); j++) {
					tabEnnemi.get(i).getIsCollision().add(false);
				}
			}
		}
		if(this.level==2) {
			
			for(int i=0; i<mario.size(); i++) {
				mario.get(i).setFin(false);
			}
			
			this.tab=new ArrayList<Objet>();
			this.piece= new ArrayList<Piece>();
			this.tab.add(new Tuyau(250, 302, "tuyauRouge.png"));
			this.tab.add(new Tuyau(1000, 302, "tuyauRouge.png"));
			this.tab.add(new Tuyau(1610, 302, "tuyauRouge.png"));
			this.tab.add(new Bloc(1100, 220, "bloc.png"));
			this.tab.add(new Bloc(1430, 290, "bloc.png"));
			this.tab.add(new Bloc(1150, 138, "bloc.png"));
			this.tab.add(new Bloc(310, 220, "bloc.png"));
			this.tab.add(new Bloc(620, 310, "bloc.png"));
			this.tab.add(new Bloc(500, 220, "bloc.png"));
			this.tab.add(new Bloc(690, 220, "bloc.png"));
			this.tab.add(new Bloc(880, 220, "bloc.png"));
			this.tab.add(new DrapeauFin(1700, 187, "drapeau.png"));
			this.piece.add(new Piece(503, 190, "piece.png"));
			this.piece.add(new Piece(1010, 272, "piece.png"));
			this.piece.add(new Piece(750, 272, "piece.png"));
			this.piece.add(new Piece(260, 272, "piece.png"));
			this.piece.add(new Piece(1153, 110, "piece.png"));
			this.piece.add(new Piece(1250, 230, "piece.png"));
			this.tab.addAll(piece);

			for(int i=0; i < piece.size(); i++) {
				piece.get(i).movePiece();
			}
			
			for(int i=0; i < mario.size(); i++) {
				for(int j=0; j < tab.size(); j++) {
					mario.get(i).getIsCollision().add(false);
				}
			}
			
			this.tabEnnemi=new ArrayList<Ennemi>();
			this.tabEnnemi.add(new koopa(600, 340, "champMarcheDroite.png"));
			this.tabEnnemi.add(new koopa(1350, 340, "champMarcheDroite.png"));
			this.tabEnnemi.add(new koopa(1485, 340, "champMarcheDroite.png"));
			this.tabEnnemi.add(new koopa(900, 340, "champMarcheDroite.png"));
			this.tabEnnemi.add(new koopa(500, 340, "champMarcheDroite.png"));
			this.tabEnnemi.add(new koopa(700, 340, "champMarcheDroite.png"));
			this.tabEnnemi.add(new koopa(270, 340, "champMarcheDroite.png"));
			this.tabEnnemi.add(new koopa(1100, 340, "champMarcheDroite.png"));
			this.tabEnnemi.add(new koopa(1050, 340, "champMarcheDroite.png"));
			this.tabEnnemi.add(new goomba(850, 320, "tortueMarcheDroite.png"));
			this.tabEnnemi.add(new goomba(550, 320, "tortueMarcheDroite.png"));
			this.tabEnnemi.add(new goomba(1400, 320, "tortueMarcheDroite.png"));
			this.tabEnnemi.add(new goomba(1250, 320, "tortueMarcheDroite.png"));
			this.tabEnnemi.add(new goomba(800, 320, "tortueMarcheDroite.png"));
			this.tabEnnemi.add(new goomba(360, 320, "tortueMarcheDroite.png"));
			this.tabEnnemi.add(new goomba(740, 320, "tortueMarcheDroite.png"));
			
			for(int i=0; i < tabEnnemi.size(); i++) {
				for(int j=0; j < tab.size(); j++) {
					tabEnnemi.get(i).getIsCollision().add(false);
				}
			}
		}
		
		if(this.level==3) {
			for(int i=0; i<mario.size(); i++) {
			    mario.get(i).setFin(false);
			}
			this.tab=new ArrayList<Objet>();
			this.piece= new ArrayList<Piece>();
			this.tab.add(new Tuyau(250, 302, "tuyauRouge.png"));
			this.tab.add(new Tuyau(790, 175, "tuyauRouge.png"));
			this.tab.add(new Tuyau(950, 302, "tuyauRouge.png"));
			this.tab.add(new Tuyau(448, 175, "tuyauRouge.png"));
			this.tab.add(new Tuyau(1150, 302, "tuyauRouge.png"));
			this.tab.add(new Bloc(300, 210, "bloc.png"));
			this.tab.add(new Bloc(450, 240, "bloc.png"));
			this.tab.add(new Bloc(480, 240, "bloc.png"));
			this.tab.add(new Bloc(510, 240, "bloc.png"));
			this.tab.add(new Bloc(540, 240, "bloc.png"));
			this.tab.add(new Bloc(570, 240, "bloc.png"));
			this.tab.add(new Bloc(600, 240, "bloc.png"));
			this.tab.add(new Bloc(630, 240, "bloc.png"));
			this.tab.add(new Bloc(660, 240, "bloc.png"));
			this.tab.add(new Bloc(690, 240, "bloc.png"));
			this.tab.add(new Bloc(710, 240, "bloc.png"));
			this.tab.add(new Bloc(740, 240, "bloc.png"));
			this.tab.add(new Bloc(770, 240, "bloc.png"));
			this.tab.add(new Bloc(800, 240, "bloc.png"));
			this.tab.add(new Bloc(675, 140, "bloc.png"));
			this.tab.add(new Bloc(1300, 220, "bloc.png"));
			this.tab.add(new Bloc(1470, 220, "bloc.png"));
			this.tab.add(new Bloc(900, 120, "bloc.png"));
			this.tab.add(new DrapeauFin(1700, 187, "drapeau.png"));
			this.piece.add(new Piece(957, 272, "piece.png"));
			this.piece.add(new Piece(640, 280, "piece.png"));
			this.piece.add(new Piece(258, 272, "piece.png"));
			this.piece.add(new Piece(678, 110, "piece.png"));
			this.piece.add(new Piece(1473, 190, "piece.png"));
			this.tab.addAll(piece);
			
			for(int i=0; i < piece.size(); i++) {
				piece.get(i).movePiece();
			}
			
			for(int i=0; i < mario.size(); i++) {
				for(int j=0; j < tab.size(); j++) {
					mario.get(i).getIsCollision().add(false);
				}
			}
			
			this.tabEnnemi=new ArrayList<Ennemi>();
			this.tabEnnemi.add(new koopa(600, 340, "champMarcheDroite.png"));
			this.tabEnnemi.add(new koopa(450, 340, "champMarcheDroite.png"));
			this.tabEnnemi.add(new koopa(1485, 340, "champMarcheDroite.png"));
			this.tabEnnemi.add(new koopa(470, 210, "champMarcheDroite.png"));
			this.tabEnnemi.add(new koopa(485, 210, "champMarcheDroite.png"));
			this.tabEnnemi.add(new koopa(600, 210, "champMarcheDroite.png"));
			this.tabEnnemi.add(new koopa(700, 210, "champMarcheDroite.png"));
			this.tabEnnemi.add(new koopa(1000, 340, "champMarcheDroite.png"));
			this.tabEnnemi.add(new koopa(900, 340, "champMarcheDroite.png"));
			this.tabEnnemi.add(new koopa(850, 340, "champMarcheDroite.png"));
			this.tabEnnemi.add(new koopa(1300, 340, "champMarcheDroite.png"));
			this.tabEnnemi.add(new koopa(1450, 340, "champMarcheDroite.png"));
			this.tabEnnemi.add(new goomba(950, 320, "tortueMarcheDroite.png"));
			this.tabEnnemi.add(new goomba(500, 190, "tortueMarcheDroite.png"));
			this.tabEnnemi.add(new goomba(750, 190, "tortueMarcheDroite.png"));
			this.tabEnnemi.add(new goomba(1400, 320, "tortueMarcheDroite.png"));
			this.tabEnnemi.add(new goomba(1250, 320, "tortueMarcheDroite.png"));
			this.tabEnnemi.add(new goomba(1600, 320, "tortueMarcheDroite.png"));
			this.tabEnnemi.add(new goomba(800, 320, "tortueMarcheDroite.png"));
			
			for(int i=0; i < tabEnnemi.size(); i++) {
				for(int j=0; j < tab.size(); j++) {
					tabEnnemi.get(i).getIsCollision().add(false);
				}
			}
		}
	}
	
	/**
	 * Cette méthode crée un thread qui permet de décrémenté le chrono toutes les secondes.
	 */
	public void chrono() {
		Thread timer= new Thread(new Runnable() {
			public void run() {
				while(Mario.isRunning()) {
					chrono--;
					try {
						Thread.sleep(1000);
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}	
		});
		timer.start();
	}
	
	/**
	 * Cette méthode est appelé à chaque fois que le modèle la demande via le notifyObserver() et permet de rafraichir les images.
	 */
	@Override
	public void update(Observable observ, Object obj) {
		if(frame.getContentPane()==conteneur) {
			for(int i=0; i<mario.size(); i++) {
				mario.get(i).collison(tab);
			}
			for(int j=0; j<tabEnnemi.size(); j++) {
				if(tabEnnemi.get(j).isVivant()==true) {
					tabEnnemi.get(j).collison(tab);
					control.collisionEnnemi(tabEnnemi.get(j));
				}
			}
		}
		frame.getContentPane().repaint();
	}
	
	/**
	 * Cette méthode est appelée dés que l'on appuie sur les touches.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_T) {
			
		}
		for(int i=0; i < mario.size(); i++) {
			if(mario.get(i).getNbJoueur()==1) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_ENTER :
					if(mario.get(i).getNbSaut()==0) {
						mario.get(i).setTouches(true, 2);
					}
					break;
				case KeyEvent.VK_LEFT :
					mario.get(i).setTouches(true, 1);
					mario.get(i).setLastKeypressed(false);
					break;
				case KeyEvent.VK_RIGHT :
					mario.get(i).setTouches(true, 0);
					mario.get(i).setLastKeypressed(true);
					break;
				}
			}
			else {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_SPACE :
					if(mario.get(i).getNbSaut()==0) {
						mario.get(i).setTouches(true, 2);
					}
					break;
				case KeyEvent.VK_Q :
					mario.get(i).setTouches(true, 1);
					mario.get(i).setLastKeypressed(false);
					break;
				case KeyEvent.VK_D :
					mario.get(i).setTouches(true, 0);
					mario.get(i).setLastKeypressed(true);
					break;
				}
			}
		}
	}
	
	/**
	 * Cette méthode est appelée dés que l'on relâche un touche.
	 */
	@Override	
	public void keyReleased(KeyEvent e) {
		for(int i=0; i < mario.size(); i++) {
			if(mario.get(i).getNbJoueur()==1) {
				switch(e.getKeyCode()) {
					case KeyEvent.VK_ENTER :
						mario.get(i).setTouches(false, 2);
						mario.get(i).setNbSaut(1);
						break;
					case KeyEvent.VK_LEFT :
						mario.get(i).setTouches(false, 1);
						break;
					case KeyEvent.VK_RIGHT :
						mario.get(i).setTouches(false, 0);
						break;
				}	
			}
			else {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_SPACE :
					mario.get(i).setTouches(false, 2);
					mario.get(i).setNbSaut(1);
					break;
				case KeyEvent.VK_Q :
					mario.get(i).setTouches(false, 1);
					break;
				case KeyEvent.VK_D :
					mario.get(i).setTouches(false, 0);
					break;
			}	
			}
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	/**
	 * Cette méthode est apellée dés que l'on appuie su un bouton.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== restart) {
			perdu.arreteSon();
			control.restart();
		}
		if(e.getSource()== nextLevel) { 
			fini.arreteSon();
			control.nextLevel();
		}
		if(e.getSource()== solo) { 
			control.solo();
			menuSon.arreteSon();
		}
		if(e.getSource()== multijoueur) { 
			control.multi();
			menuSon.arreteSon();
		}
		if(e.getSource()== valider) {
			if(!username.getText().toString().equals("") &&  !password.getText().toString().equals("")) {
				sql.login(username.getText().toString(), password.getText().toString());
				menuConnection.setVisible(false);
				frame.setContentPane(menu);
				frame.setVisible(true);
			}
		}
	}
	
	/*****GETTERS*****/
	public JPanel getConteneur() {
		return conteneur;
	}
	
	public JButton getRestart() {
		return restart;
	}
	
	public ArrayList<Objet> getTab() {
		return tab;
	}
	
	public ArrayList<Ennemi> getTabEnnemi() {
		return tabEnnemi;
	}
	
	public int getLevel() {
		return level;
	}
	
	public JButton getNextLevel() {
		return nextLevel;
	}
	
	public int getChrono() {
		return chrono;
	}
	
	public Son getPerdu() {
		return perdu;
	}
	
	public Son getFini() {
		return fini;
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public ArrayList<Piece> getPiece() {
		return piece;
	}
	
	public Son getCongrats() {
		return congrats;
	}
	
	public Sql getSql() {
		return sql;
	}
	
	/*****SETTERS*****/
	public void setConteneur(JPanel conteneur) {
		this.conteneur = conteneur;
	}

	public void setRestart(JButton restart) {
		this.restart = restart;
	}

	public void setTab(ArrayList<Objet> tab) {
		this.tab = tab;
	}

	public void setTabEnnemi(ArrayList<Ennemi> tabEnnemi) {
		this.tabEnnemi = tabEnnemi;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setNextLevel(JButton nextLevel) {
		this.nextLevel = nextLevel;
	}

	public void setChrono(int chrono) {
		this.chrono = chrono;
	}

	public void setFini(Son fini) {
		this.fini = fini;
	}

	public void setPerdu(Son perdu) {
		this.perdu = perdu;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public void setPiece(ArrayList<Piece> piece) {
		this.piece = piece;
	}

	public void setCongrats(Son congrats) {
		this.congrats = congrats;
	}

	public void setSql(Sql sql) {
		this.sql = sql;
	}
	
}
