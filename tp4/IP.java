package ephec.tp4;

public class IP {
	private int firstOctet;
	private int secoondOctet;
	private int tirthOctet;
	private int fourthOctet;
	
	public IP(String IP){
		String[] tab = IP.split("-");
		this.firstOctet = Integer.parseInt(tab[0]);
		this.secoondOctet = Integer.parseInt(tab[1]);
		this.tirthOctet = Integer.parseInt(tab[2]);
		this.fourthOctet = Integer.parseInt(tab[3]);
	}
	
	public String toString(){
		return("adresse ip: "+this.firstOctet+"."+this.secoondOctet+"."+this.tirthOctet+"."+this.fourthOctet);
	}
	
	public static void main(String[] args){
		IP monIP = new IP("10-42-21-93");
		System.out.println(monIP);
	}
}
