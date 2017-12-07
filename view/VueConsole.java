package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Observable;

import controller.Controller;
import model.Mario;

public class VueConsole extends VueGenerale {

	private String currentLine;
	private String [] tab;
	
	public VueConsole(Mario mario, Controller control) {
		super(mario, control);
		this.currentLine= "";
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		try {
			BufferedReader reader =
				        new BufferedReader(new InputStreamReader(new FileInputStream("C:/2T/java/Projet_V2/src/images/carte.txt")));
			BufferedWriter writer= new BufferedWriter(new FileWriter("C:/2T/java/Projet_V2/src/images/carte.txt"));
			while((currentLine = reader.readLine()) != null) {
				System.out.println(currentLine);
				if(!currentLine.split("-").equals("-")) {
					tab=currentLine.split("-");
				}
				for(int i=0; i<tab.length; i++) {
					if(tab[i].equals("M")) {
						//writer.write("-", tab[i].length(), 1);
					}
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
