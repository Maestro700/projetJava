package projet;
 
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class inter extends JPanel{
	private ImageIcon fond;
	private Image fondImg;
	private Image fondImg2;
	
	public static Mario mario = new Mario(100, 320);
	
	public inter(){ 
		super();
		this.fond = new ImageIcon(getClass().getResource("/images/fondEcran.png"));
	 	this.fondImg = this.fond.getImage();
		this.fondImg2= this.fond.getImage();
		
		this.setFocusable(true);
		this.addKeyListener(mario.getClavier());
		
		Thread tauxRefresh= new Thread(new refresh());
		tauxRefresh.start();
	}
		
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		mario.refreshImage();
	 	mario.saut();
	 	mario.avancer();
		mario.reculer();
		g.drawImage(fondImg, 0, 0, null);
		g.drawImage(fondImg2, 993, 0, null);
		g.drawImage(mario.getImg(), mario.x, mario.y, null);
	}
}