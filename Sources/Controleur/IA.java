package Controleur;

import Modele.Partie;
import Modele.Historique;
import Modele.Coup;

public abstract class IA {
	Partie jeu;
	Historique<Coup> historique;
	public static IA creerIA (Partie j,Historique<Coup> h, String mode){
		IA retour=null;
		if(mode.equals("alea")) {
			retour = new IAAleatoire();
		} else if(mode.equals("easy")) {
			retour = new IAMinMax();
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
		retour.historique = h;
		return retour;
	}
	
	public abstract int jouerCoup();
}