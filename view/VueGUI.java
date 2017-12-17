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

import controller.Controller;
import model.Bloc;
import model.DrapeauFin;
import model.Ennemi;
import model.Mario;
import model.Objet;
import model.Piece;
import model.Son;
import model.Tuyau;
import model.goomba;
import model.koopa;

public class VueGUI extends VueGenerale implements KeyListener, ActionListener{
	
	private int level;
	private int chrono;
	
	private Font police;
	private JButton restart;
	private JButton nextLevel;
	private JButton solo;
	private JButton multijoueur;
	
	private JFrame frame;
	private JPanel conteneur;
	private JPanel menu;
	
	private ImageIcon fond;
	
	private Image fondMenuSolo;
	private Image fondMenuMulti;
	private Image fondMenuLabel;
	private Image fondImg;
	private Image fondImg2;
	private Image chateau;
	private Image depart;
	private Image chateauFin;
	private Image gameOver;
	private Image victory;
	private Image coeur;
	
	private Son fini;
	private Son perdu;
	private Son menuSon;
	
	private Mario mario2;
	
	private Piece piece1=new Piece(460, 272, "piece.png");
	private Piece piece2=new Piece(1010, 272, "piece.png");
	
	private ArrayList <Objet> tab;
	private ArrayList <Ennemi> tabEnnemi;
	
	@SuppressWarnings("serial")
	public VueGUI(ArrayList <Mario> mario, Controller control) {
		
		super(mario, control);
		
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
				g.drawString("Votre score acuel est :"+Mario.getScore(), 0, 20);
				g.drawString("HP : ", 450, 18);
				g.drawString("Temps restant : "+chrono, 1000, 18);
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
								fini = new Son("/son/gameVictory.wav");
								g.drawImage(victory, 650, 0,500, 300, null);
								conteneur.add(nextLevel);
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
				for(int i=0; i<Mario.getHP(); i++) {
					g.drawImage(coeur, 500+i*20, 5, null);
				}
				for(int i=0; i< mario.size(); i++) {
					if(mario.get(i).isVivant()==true) {
						g.drawImage(mario.get(i).getImg(), mario.get(i).getX(), mario.get(i).getY(), 28, 50, null);
					}
					else {
						Mario.setRunning(false);
						perdu= new Son("/son/gameOver.wav");
						mario.get(i).changeImg("marioMeurt.png");
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
		
		frame.setContentPane(menu);
	}
	
	public void createJoueur2() {
		mario2= new Mario(50, 320, "luigiArretDroite.png");
		mario2.setNbJoueur(2);
		this.mario.add(mario2);
	}

	public void createLevel() {
		if(this.level==1) {
			this.tab=new ArrayList<Objet>();
			this.tab.add(new Tuyau(450, 302, "tuyauRouge.png"));
			this.tab.add(new Tuyau(1000, 302, "tuyauRouge.png"));
			this.tab.add(new Tuyau(1300, 302, "tuyauRouge.png"));
			this.tab.add(new Bloc(600, 250, "bloc.png"));
			this.tab.add(new Bloc(900, 302, "bloc.png"));
			this.tab.add(new Bloc(250, 337, "bloc.png"));
			this.tab.add(new Bloc(280, 337, "bloc.png"));
			this.tab.add(new DrapeauFin(1700, 187, "drapeau.png"));
			this.tab.add(piece1);
			this.tab.add(piece2);
			piece1.movePiece();
			piece2.movePiece();
			for(int i=0; i<mario.size(); i++) {
				mario.get(i).setTabObjSize(tab.size());
			}
			
			this.tabEnnemi=new ArrayList<Ennemi>();
			this.tabEnnemi.add(new koopa(600, 340, "champMarcheDroite.png"));
			this.tabEnnemi.add(new koopa(900, 340, "champMarcheDroite.png"));
			this.tabEnnemi.add(new koopa(270, 340, "champMarcheDroite.png"));
			this.tabEnnemi.add(new koopa(1100, 340, "champMarcheDroite.png"));
			this.tabEnnemi.add(new koopa(1050, 340, "champMarcheDroite.png"));
			this.tabEnnemi.add(new goomba(850, 320, "tortueMarcheDroite.png"));
			this.tabEnnemi.add(new goomba(550, 320, "tortueMarcheDroite.png"));
			this.tabEnnemi.add(new goomba(1250, 320, "tortueMarcheDroite.png"));
		}
		if(this.level==2) {
			for(int i=0; i<mario.size(); i++) {
				mario.get(i).setFin(false);
			}
			
			this.tab=new ArrayList<Objet>();
			this.tab.add(new Tuyau(450, 302, "tuyauRouge.png"));
			this.tab.add(new Tuyau(1000, 302, "tuyauRouge.png"));
			this.tab.add(new Tuyau(1300, 302, "tuyauRouge.png"));
			this.tab.add(new Bloc(600, 250, "bloc.png"));
			this.tab.add(new Bloc(900, 302, "bloc.png"));
			this.tab.add(new Bloc(750, 308, "bloc.png"));
			this.tab.add(new Bloc(280, 337, "bloc.png"));
			this.tab.add(new DrapeauFin(1700, 187, "drapeau.png"));
			this.tab.add(piece1);
			this.tab.add(piece2);
			piece1.movePiece();
			piece2.movePiece();
			for(int i=0; i<mario.size(); i++) {
				mario.get(i).setTabObjSize(tab.size());
			}
			
			this.tabEnnemi=new ArrayList<Ennemi>();
			this.tabEnnemi.add(new koopa(600, 340, "champMarcheDroite.png"));
			this.tabEnnemi.add(new koopa(900, 340, "champMarcheDroite.png"));
			//this.tabEnnemi.add(new koopa(270, 340, "champMarcheDroite.png"));
			this.tabEnnemi.add(new koopa(1100, 340, "champMarcheDroite.png"));
			//this.tabEnnemi.add(new koopa(1050, 340, "champMarcheDroite.png"));
			this.tabEnnemi.add(new goomba(850, 320, "tortueMarcheDroite.png"));
			this.tabEnnemi.add(new goomba(550, 320, "tortueMarcheDroite.png"));
			//this.tabEnnemi.add(new goomba(1250, 320, "tortueMarcheDroite.png"));
		}
	}
	
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

	@Override
	public void keyPressed(KeyEvent e) {
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
	}
	
	public JPanel getConteneur() {
		return conteneur;
	}

	public void setConteneur(JPanel conteneur) {
		this.conteneur = conteneur;
	}

	public JButton getRestart() {
		return restart;
	}

	public void setRestart(JButton restart) {
		this.restart = restart;
	}

	public ArrayList<Objet> getTab() {
		return tab;
	}

	public void setTab(ArrayList<Objet> tab) {
		this.tab = tab;
	}

	public ArrayList<Ennemi> getTabEnnemi() {
		return tabEnnemi;
	}

	public void setTabEnnemi(ArrayList<Ennemi> tabEnnemi) {
		this.tabEnnemi = tabEnnemi;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public JButton getNextLevel() {
		return nextLevel;
	}

	public void setNextLevel(JButton nextLevel) {
		this.nextLevel = nextLevel;
	}

	public int getChrono() {
		return chrono;
	}

	public void setChrono(int chrono) {
		this.chrono = chrono;
	}

	public Son getFini() {
		return fini;
	}

	public void setFini(Son fini) {
		this.fini = fini;
	}

	public Son getPerdu() {
		return perdu;
	}

	public void setPerdu(Son perdu) {
		this.perdu = perdu;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
