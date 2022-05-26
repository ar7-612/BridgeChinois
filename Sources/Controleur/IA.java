package Controleur;

import Modele.Partie;

import java.io.Serializable;

import Modele.Joueur;


public abstract class IA  implements Serializable{
	Partie jeu;
	Joueur joueur1;
	Joueur joueur2;
	int joueurIA;
	public static IA creerIA (Partie j,Joueur j1,Joueur j2,int numJ, String mode){
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
		retour.joueur1 = j1;
		retour.joueur2 = j2;
		retour.joueurIA = numJ;
		return retour;
	}
	
	public abstract int jouerCoup();
}