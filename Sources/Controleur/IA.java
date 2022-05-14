package Controleur;

import Modele.Partie;

abstract class IA {
	Partie jeu;
	
	public IA creerIA (Partie j, String mode){
		IA retour=null;
		switch(mode){
			case "Aleatoire":
				retour = new IAAleatoire();
			default :
				System.err.println("IA inconnue : "+mode);
				//ERREUR
		}
		retour.jeu = j;
		return retour;
	}
	
	abstract int jouerCoup();
}