package Controleur;

import java.util.Random;

class IAAleatoire extends IA {
	
	Random r;
	
	private int nbPiochesVide () {
		int c=0;
		for(int i=0;i<6;i++) {
			if(jeu.pilevide(i)) {
				c++;
			}
		}
		return c;
	}
	
	@Override
	int jouerCoup() {
		int retour=-1;
		switch(jeu.phase()) {
			case 0:
				retour = r.nextInt(jeu.joueurDonneur().main().taille());
				break;
			case 1:
				retour = r.nextInt(jeu.joueurReceveur().main().taille());
				break;
			case 2:
				retour = r.nextInt(nbPiochesVide()-1);
				break;
			case 3:
				retour = r.nextInt(nbPiochesVide()-1);
				break;
		}
		return retour;
	}
}
