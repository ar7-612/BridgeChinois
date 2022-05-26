package Controleur.IA;

import java.util.Deque;
//import java.util.HashMap;
import java.util.Hashtable;
import java.util.ArrayDeque;
import java.util.ArrayList;
//import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

//import javax.management.RuntimeErrorException;

import Controleur.IAbase;
import Modele.Carte;
import Modele.Main;

//joueur : 0 ou 1

class TimeCheckException extends Exception {
	/**
	 * utile pour sortir de la recurtion si le temp d'attente est trop important
	 */
	private static final long serialVersionUID = 1L; // a verifier

	public TimeCheckException(String m) {
		super(m);
	}
}

public class IAMinMax extends IAbase {
	
	/* 
	 * fonctions utiles
	 */
	
	void printDebug1(int h) {
		for(int i=0;i<h;i++) {
			System.out.print("|");
		}
	}
	int unDans(long ensemble) {
		int taille = CartesMIA.nbCartes(ensemble);
		if(taille==0) {
			return -1;
		}
		int nb = r.nextInt(taille);
		for(int i=0;i<52;i++) {
			if((ensemble & (1L<<i))!=0L) {
				if(nb==0) {
					return i;
				} else {
					nb--;
				}
			}
		}
		return -1;
	}
	float infinie(){
		// doit etre superieur a l'heuristique max
		return Float.MAX_VALUE;
	}
	
	/* 
	 * fonctions minMax
	 */
	
	float heuristique(ConfigurationIA config){
		float h;
		if(config.nbCartesInconnue()==0) {
			h = config.heuristiquePiocheVide();
		} else {
			h = config.heuristiquePartieEnCour1();
		}
		//System.out.println("Heutistique : "+h);
		return h;
	}

	Deque<ConfigurationIA> trouverFilsV0_1(ConfigurationIA config){ // ne pas utilisee
		Deque<ConfigurationIA> retour = new ArrayDeque<ConfigurationIA>();
		long ensemble1 = 0;
		long ensemble2 = 0;
		switch(config.getPhase()) {
			case 0 :
				if(config.getJoueur()==joueurIA-1) {
					ensemble1 = config.getPossibleDonne();
				} else {
					ensemble1 = config.getPossibleDonne();
				}
				break;
			case 1 :
				if(config.getJoueur()==joueurIA-1) {
					ensemble1 = config.getPossibleReponse();
				} else {
					ensemble1 = config.getPossibleReponse();
				}
				break;
			case 2 : 
				if(config.getJoueur()==joueurIA-1) {
					ensemble1 = config.getPossiblePioche();
					ensemble2 = config.getPossiblePiocheCachee();
				} else {
					ensemble1 = config.getPossiblePioche();
					ensemble2 = config.getPossiblePiocheCachee();
				}
				break;
			case 3 :
				if(config.getJoueur()==joueurIA-1) {
					ensemble1 = config.getPossiblePioche();
					ensemble2 = config.getPossiblePiocheCachee();
				} else {
					ensemble1 = config.getPossiblePioche();
					ensemble2 = config.getPossiblePiocheCachee();
				}
				break;
		}
		for(int i=0;i<52;i++) {
			if((ensemble1 & (1L<<i))!=0L) {
				if(ensemble2==0L) {
					ConfigurationIA config2 = config.clone();
					config2.update(i, -1);
					retour.addFirst(config2);
				} else {
					for(int j=0;j<52;j++) {
						if((ensemble2 & (1L<<j))!=0) {
							ConfigurationIA config2 = config.clone();
							config2.update(i, j);
							retour.addFirst(config2);
						}
					}
				}
			}
		}
		//REFAIRE COMPARAISONS
		//retour.sort(null);
		if(retour.isEmpty()) {
			//System.err.println(" AhA ! "+config);
		}
		return retour;
	}
	Deque<ConfigurationIA> trouverFilsV1_0(ConfigurationIA config){
		Deque<ConfigurationIA> retour = new ArrayDeque<ConfigurationIA>();
		long ensemble1 = 0;
		int cartePioche = -1;
		
		switch(config.getPhase()) {
			case 0 :
				if(config.getJoueur()==joueurIA-1) {
					ensemble1 = config.getPossibleDonne();
				} else {
					ensemble1 = config.getPossibleDonne();
				}
				break;
			case 1 :
				if(config.getJoueur()==joueurIA-1) {
					ensemble1 = config.getPossibleReponse();
				} else {
					ensemble1 = config.getPossibleReponse();
				}
				break;
			case 2 : 
				if(config.getJoueur()==joueurIA-1) {
					ensemble1 = config.getPossiblePioche();
					//cartePioche = config.carteIncMax();
					cartePioche = config.carteIncMoy();
				} else {
					ensemble1 = config.getPossiblePioche();
					//cartePioche = config.carteIncMin();
					cartePioche = config.carteIncMoy();
				}
				break;
			case 3 :
				if(config.getJoueur()==joueurIA-1) {
					ensemble1 = config.getPossiblePioche();
					//cartePioche = unDans(config.getPossiblePiocheCachee());
					cartePioche = config.carteIncMoy();
				} else {
					ensemble1 = config.getPossiblePioche();
					//cartePioche = unDans(config.getPossiblePiocheCachee());
					cartePioche = config.carteIncMoy();
				}
				break;
		}
		for(int i=0;i<52;i++) {
			if((ensemble1 & (1L<<i))!=0L) {
				ConfigurationIA config2 = config.clone();
				config2.update(i, cartePioche);
				retour.addFirst(config2);
			}
		}
		return retour;
	}
	Deque<ConfigurationIA> trouverFils(ConfigurationIA config){ //A faire
		return trouverFilsV1_0(config);
	}
	
	float minMax(ConfigurationIA config, float borneMax, float borneMaxAdv, int profondeur,int debugHauteur) throws TimeCheckException{
		checkTime();
		if(config.estFinal()) {
			return heuristique(config);
		}
		if(profondeur==0) {
			if(config.getJoueur() == joueurIA-1 && config.getPhase() == 0) { //Trop gourmand...
				return heuristique(config);
			} else {
				profondeur++;
			}
			return heuristique(config);
		}
		float max = -infinie();
		
		Deque<ConfigurationIA> fils = trouverFils(config);
		ConfigurationIA cMax = configurations.get(config);
		if(cMax!=null) {
			fils.addFirst(cMax);
		}
		cMax = fils.getFirst();
		Iterator<ConfigurationIA> it = fils.iterator();
		
		while(it.hasNext()){
			ConfigurationIA configF = it.next();
			Float tmp;
			
			if(config.getJoueur()==configF.getJoueur()) {
				tmp =  minMax(configF,borneMax,borneMaxAdv,profondeur-1,debugHauteur+1);
			} else {
				tmp = -minMax(configF,borneMaxAdv,borneMax,profondeur-1,debugHauteur+1);
			}
			
			if(tmp > borneMax){// alpha/beta reduction
				max = infinie();
				cMax = configF;
				break;
			} else {
				//System.out.println();
			}
			max = Math.max(max,tmp);
			borneMaxAdv = -max;
		}
		if(cMax==null) {
			System.out.println("okay");
		}
		configurations.put(config, cMax);
		return max;
	}
	int minMaxInitial(ConfigurationIA config, int profondeur) throws TimeCheckException{
		checkTime();
		float max = -infinie();
		float borneMax = infinie();
		float borneMaxAdv = infinie();
		List<Couple<Integer,Float>> tableCartes = new ArrayList<>();
		
		Deque<ConfigurationIA> fils = trouverFils(config);
		ConfigurationIA cMax = configurations.get(config);
		if(cMax!=null) {
			fils.addFirst(cMax);
		}
		cMax = fils.getFirst();
		Iterator<ConfigurationIA> it = fils.iterator();
		
		while(it.hasNext()){
			ConfigurationIA configF = it.next();
			Float tmp;
			
			if(config.getJoueur()==configF.getJoueur()) {
				tmp = minMax(configF,borneMax,borneMaxAdv,profondeur,1);
			} else {
				tmp = -minMax(configF,borneMaxAdv,borneMax,profondeur,1);
			}

			//System.err.println("Carte : "+TestsIA.iToS(configF.getCarte())  + " Valeur : " + tmp);
			if(tmp != -infinie()) {
				tableCartes.add(new Couple<>(configF.getCarte(),tmp));
			}
			if(tmp>max) {
				cMax = configF;
				max = tmp;
			}
			borneMaxAdv = -max;
		}
		configurations.put(config,cMax);
		
		int retour = -10;
		tableCartes.sort(null);
		if(tableCartes.size()==0) {
			System.err.println("Erreur critique : l'IA ne trouve pas de coups");
		} else {
			retour = tableCartes.get(tableCartes.size() - 1).v;
		}
		
		return retour;
	}
	
	/*
	 * fonctions d'initialisation
	 */
	
	int trouverProfondeur(ConfigurationIA config){
		int retour = 0;
		int nbInc = retour = config.nbCartesInconnue();
		//retour = config.nbCartesInconnue()!=0 ? 3 : 100;//(50 - config.nbCartesMain());
		if(nbInc>10) {
			retour = 5;
		} else if (nbInc>5) {
			retour = 7;
		} else if (nbInc>0) {
			retour = 7;
		} else {
			retour = 100;
		}
		return retour;
	}
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
	
	long startTime;
	long delayVise = 2000;// en ms
	long delayMax = 5000;// en ms
	void checkTime() throws TimeCheckException {
		if(System.currentTimeMillis() - startTime > delayMax) {
			throw new TimeCheckException("trop long");
		}
	}
	
	@Override
	public int jouerCoup() {
		
		if(r==null) { // Initialisation
			r=new Random(6699);
			configurations = new Hashtable<>();
		} else {
			configurations = new Hashtable<>();
		}
		
		
		
        ConfigurationIA config = trouverConfiguration();
        int idCarte = -10;
        int retour = -10;
        
        startTime = System.currentTimeMillis();
        int profondeur = 3;
		
        try {
        	while(System.currentTimeMillis() - startTime < delayVise) {
        		idCarte = minMaxInitial(config,profondeur);
        		profondeur++;
            }
        } catch(TimeCheckException e) {
        	if(idCarte==-10) {
        		throw new RuntimeException("l'IA n'as pas eu le temps de trouver un coup");
        	}
        }
        
       System.err.print(profondeur);
        
        retour=trouverIndice(idCarte);
		
		//System.err.println("Carte : "+TestsIA.iToS(idCarte)+", indice : "+retour);
		return retour;
	}
}
