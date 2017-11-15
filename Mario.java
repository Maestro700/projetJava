package projet;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Mario extends Personnage{
	
	private Clavier clavier;
	private boolean saut;
	private boolean collision;
	private int  TempsSaut;
	private String str;
	private Image img;
	private int nbSaut;
	
	
	public Mario(int x, int y) {
		super.x= x;
		super.y= y;
		this.str="marioMarcheDroite.png";
		this.img=new ImageIcon(getClass().getResource("/images/"+this.str)).getImage();
		super.gravite= 0.95f;
		super.speed=1;
		super.HP= 3;
		this.clavier= new Clavier();
		this.saut= false;
		this.TempsSaut=0;
		this.nbSaut=0;
		this.collision= false;
	}

	public boolean estAuSol(Objet obj) {
		return false;
	}
	
	public void collison(Objet obj) {
		super.hitBox= new Rectangle(this.x, this.y, 28, 50);
		if(this.hitBox.intersects(obj.hitBox)) {
			this.collision = true;
			clavier.setDx(0);
			clavier.setDy(0);
		}
		else {
			this.collision = false;
			this.avancer();
			this.saut();
		}
	}

	public void saut() {
		if(this.nbSaut==0) {
			if(this.saut==true) {
				if(this.TempsSaut<=35) {
					this.TempsSaut++;
					this.y=this.y-clavier.getDy();
				}
				else {
					this.saut=false;
					this.nbSaut=1;
				}
			}
		}
		else {
			this.TempsSaut=0;
			if(this.y!=320) {
				this.y=this.y+clavier.getDy();
				this.saut=false;
			}
			else {
				this.nbSaut=0;
			}
		}
	}
	
	public void refreshImage() {
		this.img=new ImageIcon(getClass().getResource("/images/"+this.str)).getImage();
	}
	
	@Override
	public void avancer() {
		this.x=this.x+clavier.getDx();
	}
	
	//*****GETTERS*****
	public Clavier getClavier() {
		return clavier;
	}
	
	public Image getImg() {
		return img;
	}
	
	public boolean getSaut() {
		return saut;
	}
	
	public String getStr() {
		return str;
	}
	
	public int getTempsSaut() {
		return TempsSaut;
	}

	//*****SETTERS*****
	public void setSaut(boolean saut) {
		this.saut = saut;
	}

	public void setClavier(Clavier clavier) {
		this.clavier = clavier;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public void setImg(Image img) {
		this.img = img;
	}
	
	public void setTempsSaut(int tempsSaut) {
		TempsSaut = tempsSaut;
	}
}
