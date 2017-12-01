package ephec.tp4;

public class EtuMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Etu david = new Etu("Jacobs", "David");
		Etu filsDecorde = new Etu("Bouterfa", "Youssef");
		david.setResultat(new int[]{14, 14, 14, 14, 14});
		filsDecorde.setResultat(new int[]{12, 12, 12,12 , 12});
		System.out.println(david);
		System.out.println(filsDecorde);
		System.out.println("Moyenne de david: "+david.moyenne());
		System.out.println("Moyenne de Bouterfa: "+filsDecorde.moyenne());
		System.out.println("David et Bouterfa sont-ils egaux? "+david.equals(filsDecorde));
		
		if(david.compareTo(david) > 0){
			System.out.println("David est devant Bouterfa");
		}
		else{
			System.out.println("David est derriere Bouterfa");
		}
		
		Etu cloneDavid = new Etu();
		cloneDavid.setMatricule(david.getMatricule());
		System.out.println("David sont-ils identiques ?"+david.equals(cloneDavid));
		
		System.out.println("Nbre d'étudiant créées: "+Etu.getnbEtu());

	}

}
