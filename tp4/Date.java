package ephec.tp4;

public class Date {
	private int jour;
	private int mois;
	private int annee;
	
	public Date(String date){
		String[] tab = date.split("/"); //permet de separet les != instance par le symbole "/"
		this.jour = Integer.parseInt(tab[0]);
		this.mois = Integer.parseInt(tab[1]);
		this.annee = Integer.parseInt(tab[2]);
	}
	public String toString(){
		return(this.jour+"-"+this.mois+"-"+this.annee);
	}
	public static void main(String[] args) {
		Date ddn = new Date("1/2/2001");
		System.out.println(ddn);
	}

}
