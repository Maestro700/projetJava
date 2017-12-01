package ephec.tp4;
/**
 * 
 * @author he201480
 *This class represents a student with this ID and 5results
 */

public class Etu {
	private static int nbEtu =0; //contenu commun avec toutes les instances de la classe
	private String nom;
	private String prenom;
	private int matricule;
	private int[] resultat;
	/**
	 * Constructor usezd to create a student
	 */
	
	public Etu(){
		this.nom = "";
		this.prenom = "";
		this.matricule= nbEtu;
		nbEtu ++;
		/**
		 * Constructor create used 
		 *@param nom the last name, matricule un entier
		 *
		 *
		 */
	}
	
	public Etu(String nom, String prenom){
		this.nom = nom;
		this.prenom = prenom;
		this.matricule = nbEtu;
		nbEtu ++;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getMatricule() {
		return matricule;
	}

	public void setMatricule(int matricule) {
		this.matricule = matricule;
	}

	public int[] getResultat() {
		return resultat;
	}

	public void setResultat(int[] resultat) {
		this.resultat = resultat;
	}
	/**
	 * this method computes the mean obtained by the student based on tis table of results
	 * @return the computed mean of the student. If the results are unknow, the mean value is 0
	 */
	public double moyenne(){
		if(this.resultat == null) return 0;
		
		double somme =0;
		for(int i:resultat){
			somme += i;
		}
		return somme/resultat.length;
	}
	/**
	 * Thisn method gives a texdtual representation of the student
	 */
	public String toString(){
		return ("Nom: "+this.nom+", Prenom: "+this.prenom+", Matricule: "+this.matricule);
	}
	/**
	 * this method checks the equality between 2 students
	 * @return true if both students have the same ID, false otherwise
	 */
	public boolean equals(Object obj){
		if(this == obj) return true;
		if(obj == null) return false;
		if(this.getClass() != obj.getClass()) return false;
		Etu other = (Etu)obj; //voir si il est de meme type, donc le caster en etudiant pour acceder dans les élement de Etu
		if(this.matricule != other.matricule) return false;
		return true;
	}
	/**
	 * Compare the current student with the argument student
	 * @param etu the student to make the comparaison with
	 * @return a positive number if the object is bigger than the arg student,
	 * 0 if they are the same , a negatio
	 */
	
	public int compareTo(Etu etu){
		if(this.equals(etu)) return 0;
		return this.matricule < etu.matricule ? -1:1;
		/*  si plus petit == -1, si non == 1 */
	}
	public static int getnbEtu(){
		return nbEtu;
	}
	public static void setnbEtu(int nbEtu){
		Etu.nbEtu = nbEtu;
	}
}


