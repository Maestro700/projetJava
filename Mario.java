package projet;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Mario extends Personnage{
	
	private Clavier clavier;
	private boolean saut;
	private int  TempsSaut;
	private String str;
	private Image img;
	
	
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
	}

	public boolean estAuSol(Objet obj) {
		return false;
	}
	
	public boolean collison(Objet obj) {
		return false;
	}

	public void saut() {
		if(saut==true && this.TempsSaut<40) {
			this.TempsSaut++;
			this.y=this.y-clavier.getDy();
			System.out.println(this.TempsSaut);
		}
		if(saut==true && this.TempsSaut==40) {
			while(this.TempsSaut!=0) {
				this.TempsSaut--;
 	 			this.y= this.y-1;
 	 			System.out.println(this.TempsSaut);
			}
			if(this.TempsSaut==0) {
				this.saut=false;
				this.TempsSaut=0;
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

	@Override
	public void reculer() {
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
