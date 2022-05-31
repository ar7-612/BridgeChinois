package Controleur.IA;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import Controleur.IAbase;
import Modele.Carte;
import Modele.Main;

class IAAdHoc extends IAbase implements Serializable {
	
	float infinie(){
		// doit etre superieur a l'heuristique max
		return Float.MAX_VALUE;
	}
	
	int chercherCarte(ConfigurationIA config) {
		int carte = -10;
		float esperancePiocheCachee = config.esperanceCarteInc();
		float esperancePiocheMax = -infinie();
		int cartePiocheMax;
		
		float[] esperancePioche = config.esperancePioches();
		for(int i=0;i<esperancePioche.length;i++) {
			esperancePiocheMax = Math.max(esperancePiocheMax, esperancePioche[i]);
		}
		cartePiocheMax = config.esperanceToCartePioche(esperancePiocheMax);
		
		float esperanceMainMin = infinie();
		int carteMainMin;
		float[] esperanceMain;
		
		switch(config.getPhase()) {
		case 0:
			esperanceMain = config.esperanceMainDonne();
			break;
		case 1:
			esperanceMain = config.esperanceMainReponse();
			break;
		case 2:
		case 3:
			return cartePiocheMax;
		default:
			throw new RuntimeException("phase inconnue");
		}
		
		for(int i=0;i<esperanceMain.length;i++) {
			esperanceMainMin = Math.min(esperanceMainMin, esperanceMain[i]);
		}
		carteMainMin = config.esperanceToCartePioche(esperanceMainMin);
		
		return carte;
	}
	
	
	
	/*
	 * 
	 */
	
	long trouverMain() {
		long retour = 0;
		Main m = (joueurIA==1 ? joueur1 : joueur2).main();
		for(int i=0;i<m.taille();i++) {
			retour |= 1L << (carteToInt(m.carte(i)));// -1 ?
			//System.out.println(i+" Carte "+carteToInt(m.carte(i)) + " Retour "+retour);
		}
		return retour;
	}
	int carteToInt (Carte c) {
		return c.valeur()-2 + (c.couleur()-1) * 13;
	}
	ConfigurationIA trouverConfiguration() { //A faire ?
		ConfigurationIA config = null;
		//debugAfficherCartes(trouverMain());
		int carte = -1;
		switch(jeu.phasetourterm()) {
		case 0: break;
		case 1:
			carte = carteToInt(jeu.cartePrem());
			break;
		case 2: break;
		case 3: break;
		}
		if(trouverMain()==0) {
			while(true) {
				//System.err.println("okay");
			}
		}
		//System.err.println(""trouverMain());
		config = new ConfigurationIA(trouverMain(),joueurIA-1,jeu.phasetourterm(),carte,jeu.atout()-1,true);
		
		for(int i=0;i<4;i++) {
			if(joueur1.getTabCouleur()[i]) {
				config.delCouleur(0,i);
			}
		}
		for(int i=0;i<4;i++) {
			if(joueur2.getTabCouleur()[i]) {
				config.delCouleur(1,i);
			}
		}
		Iterator<Carte> it = joueur1.getCartesPiochees().iterateur();
		while(it.hasNext()) {
			Carte courant = it.next();
			config.addMain(0,carteToInt(courant));
		}
		it = joueur2.getCartesPiochees().iterateur();
		while(it.hasNext()) {
			Carte courant = it.next();
			config.addMain(1,carteToInt(courant));
		}
		it = joueur1.getPli().iterateur();
		while(it.hasNext()) {
			Carte courant = it.next();
			config.delMain(0,carteToInt(courant));
			config.delMain(1,carteToInt(courant));
			config.addPli(0,carteToInt(courant));
		}
		it = joueur2.getPli().iterateur();
		while(it.hasNext()) {
			Carte courant = it.next();
			config.delMain(0,carteToInt(courant));
			config.delMain(1,carteToInt(courant));
			config.addPli(1,carteToInt(courant));
		}
		for(int i=0;i<6;i++) {
			if(!jeu.pilevide(i+1)) {
				config.addPioche(jeu.taillePile(i+1)-1, carteToInt(jeu.CartevisiblePile()[i]));
			}
		}
		
		config.delMain(0, carte);
		config.delMain(1, carte);
		
		//System.out.println("Debut : "+config);
		
		return config;
	}
	int trouverIndice(int idCarte) {
		int retour = -10;
		switch(jeu.phasetourterm()) {
		case 0:
		case 1:
			Main m = (joueurIA==1 ? joueur1 : joueur2).main();
			for(int i=0;i<m.taille();i++) {
				if(carteToInt(m.carte(i))==idCarte) {
					retour = i;
					break;
				}
			}
			break;
		case 2:
		case 3:
			for(int i=0;i<jeu.CartevisiblePile().length;i++) { 
				if(!jeu.pilevide(i+1) && carteToInt(jeu.CartevisiblePile()[i])==idCarte) {
					retour = i+1;
					break;
				}
			}
			break;
		}
		return retour;
	}
	
	/*
	 * 
	 */
	
	Hashtable<ConfigurationIA,ConfigurationIA> configurations;
	Random r;
	/* 100 500 100 17.3
	 * 500 1000 100 17
	 * 
	 */
	long startTime;
	long delayVise = 500;// en ms
	long delayMax = 1000;// en ms
	int profondeurMax = 100;
	void checkTime() throws TimeCheckException {
		if(System.currentTimeMillis() - startTime > delayMax) {
			throw new TimeCheckException("trop long");
		}
	}
	
	@Override
	public int jouerCoup() {
		return -10;
	}
}
