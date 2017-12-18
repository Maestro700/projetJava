package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
		public static void main(String[] args) {
			try {
				Socket socket = new Socket("localhost", 12345);
				BufferedReader in= new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out= new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
				String str= "Bonjour";
				for(int i=0; i<10; i++){
					out.println(str);
				}
				System.out.println("END");
				out.println("END");
				in.close();
				out.close();
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
