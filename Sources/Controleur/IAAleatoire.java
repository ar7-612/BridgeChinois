package Controleur;

import java.util.Random;

class IAAleatoire extends IA {
	
	Random r;
	
	private int nbPiochesNonVide () {
		int c=0;
		for(int i=1;i<=6;i++) {
			if(!jeu.pilevide(i)) {
				c++;
			}
		}
		return c;
	}
	
	private int choisirPioche(int p) {
		for(int i=1;i<=6;i++) {
			if(!jeu.pilevide(i)) {
				p--;
				if(p==0) {
					return i;
				}
			}
		}
		return -1;
	}
	
	@Override
	public int jouerCoup() {
		if(r==null) {
			r=new Random();
		}
		int retour=-1;
		int codePhase = jeu.phasetour();
		if(nbPiochesNonVide()==0) {
			codePhase%=2;
		}
		switch(codePhase) {
			case 0:
				retour = r.nextInt(jeu.joueurDonneur().main().taille());
				break;
			case 1:
				retour = r.nextInt(jeu.joueurReceveur().main().taille());
				break;
			case 2:
			case 3:
				int numPioche = nbPiochesNonVide();
				if(numPioche>1) {
					numPioche=r.nextInt(numPioche-1)+1;
				}
				retour = choisirPioche(numPioche);
				break;
		}
		return retour;
	}
}
