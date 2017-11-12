package projet;

import java.awt.Image;
import javax.swing.ImageIcon;

public abstract class Objet {
	protected int x;
	protected int y;
	protected Image img;
	protected ImageIcon icon;
	
	public Objet() {
		
	}
	
	protected abstract boolean collison(int x, int y);
}
