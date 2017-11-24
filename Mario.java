package model;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Mario extends Personnage{
	
	private String str;
	private Image img;
	
	public Mario(int x, int y) {
		super.x= x;
		super.y= y;
		super.speed= 7;
		this.str="marioMarcheDroite.png";
		this.img=new ImageIcon(getClass().getResource("/images/"+this.str)).getImage();
	}
	
	public void changeImg(String str) {
		this.img=new ImageIcon(getClass().getResource("/images/"+str)).getImage();
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	@Override
	public void avancer(int dx) {
		this.x=this.x+(dx*speed);
		setChanged();
		notifyObservers();
	}
	
	public void saut(int dx) {
		this.y-=dx;
		setChanged();
		notifyObservers();
	}
	
	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
}