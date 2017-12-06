package view;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;

import controller.Controller;
import model.Mario;

public class VueConsole extends VueGenerale {

	private String currentLine;
	
	public VueConsole(Mario mario, Controller control) {
		super(mario, control);
		this.currentLine= "";
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		/*try {
			BufferedReader reader =
				        new BufferedReader(new InputStreamReader(new FileInputStream("C:/2T/java/Projet/src/carte.txt")));
			while((currentLine = reader.readLine()) != null) {
				System.out.println(currentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
}
