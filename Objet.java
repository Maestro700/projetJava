package model;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Objet {
	protected int x;
	protected int y;
	protected Rectangle hitBox;
	protected Image img;
	protected String str;
	
	public Objet(int x, int y, String str) {
		this.x=x;
		this.y=y;
		this.str=str;
		this.img=new ImageIcon(getClass().getResource("/images/"+this.str)).getImage();
	}
}
