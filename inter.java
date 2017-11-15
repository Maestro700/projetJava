package projet;
 
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class inter extends JPanel{
	private ImageIcon fond;
	private Image fondImg;
	private Image fondImg2;
	private Image chateau;
	
	public static Mario mario = new Mario(100, 320);
	public Tuyau tuyau1= new Tuyau(400, 302, "tuyauRouge.png");
	public Bloc bloc1= new Bloc(600, 250, "bloc.png");
	 
	public inter(){ 
		super();
		this.fond = new ImageIcon(getClass().getResource("/images/fondEcran.png"));
	 	this.fondImg = this.fond.getImage();
		this.fondImg2= this.fond.getImage();
		this.chateau=new ImageIcon(getClass().getResource("/images/chateau.png")).getImage();
		
		this.setFocusable(true);
		this.addKeyListener(mario.getClavier());
		
		Thread tauxRefresh= new Thread(new refresh());
		tauxRefresh.start();
	}
 		
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		mario.refreshImage();  
		mario.collison(tuyau1);
		mario.collison(bloc1);
		mario.saut();
		g.drawImage(fondImg, 0, 0, null);
		g.drawImage(tuyau1.img, tuyau1.x, tuyau1.y, null);
		g.drawImage(bloc1.img, bloc1.x, bloc1.y, null);
		g.drawImage(chateau, 0, 167, null);
		g.drawImage(fondImg2, 993, 0, null);
		g.drawImage(mario.getImg(), mario.x, mario.y, null);
	}
}