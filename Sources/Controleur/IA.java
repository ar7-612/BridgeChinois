package Controleur;

import Modele.Partie;

public abstract class IA {
	Partie jeu;
	
	public static IA creerIA (Partie j, String mode){
		IA retour=null;
		if(mode.equals("alea")) {
			retour = new IAAleatoire();
		} else {
			System.err.println("IA inconnue : " + mode);
		}
		/*switch(mode){
			case "alea":
				retour = new IAAleatoire();
			default :
				System.err.println("IA inconnue : "+mode);
				//ERREUR
		}*/
		retour.jeu = j;
		return retour;
	}
	
	public abstract int jouerCoup();
}