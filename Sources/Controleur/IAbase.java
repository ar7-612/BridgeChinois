package Controleur;

import Modele.Partie;

import java.io.Serializable;

import Controleur.IA.IAAleatoire;
import Controleur.IA.IAMinMax;
import Modele.Joueur;


public abstract class IAbase extends Thread implements Serializable  {
	protected Partie jeu;
	protected Joueur joueur1;
	protected Joueur joueur2;
	protected int joueurIA;
	public static IAbase creerIA (Partie j,Joueur j1,Joueur j2,int numJ, String mode){
		IAbase retour=null;
		if(mode.equals("alea")) {
			retour = new IAAleatoire();
		} else if(mode.equals("easy")) {
			retour = new IAMinMax(0);
		} 
	 	else if(mode.equals("medium")) {
		retour = new IAMinMax(1);
			}
		 else if(mode.equals("hard")) {
			retour = new IAMinMax(2);
		}else {
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
	@Override
	public void run(){
		jeu.reflechie1();
		jeu.entierfix(jouerCoup());
		jeu.reflechie0();
	}
	public abstract int jouerCoup();
}