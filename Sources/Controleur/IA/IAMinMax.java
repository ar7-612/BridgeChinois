package Controleur.IA;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import Controleur.IAbase;
import Modele.Carte;
import Modele.Main;

//joueur : 0 ou 1

public class IAMinMax extends IAbase {
	
	//FONCTIONNE UNIQUEMENT SI CONFIG VALIDE
	float heuristique(ConfigurationIA config){
		float h = config.heuristique(config.joueur+1 == joueurIA);
		//System.out.println("Heutistique : "+h);
		return h;
	}

	List<ConfigurationIA> trouverFils(ConfigurationIA config){ //A faire
		//update(int carte1,int carte2)
		List<ConfigurationIA> retour = new ArrayList<>();
		long ensemble1 = 0;
		long ensemble2 = 0;
		
		if(config.mainJ==0) {
			System.err.println(" AhAO ! "+config);
		}
		
		switch(config.getPhase()) {
			case 0 :
				if(config.getJoueur()==joueurIA-1) {
					ensemble1 = config.getPossibleDonne(true);
				} else {
					ensemble1 = config.getPossibleDonne(false);
				}
				break;
			case 1 :
				if(config.getJoueur()==joueurIA-1) {
					ensemble1 = config.getPossibleReponse(true);
				} else {
					ensemble1 = config.getPossibleReponse(false);
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
					config2.update(i, -1, config2.getJoueur()==joueurIA-1);
					retour.add(config2);
				} else {
					for(int j=0;j<52;j++) {
						if((ensemble2 & (1L<<j))!=0) {
							ConfigurationIA config2 = config.clone();
							config2.update(i, j, config2.getJoueur()==joueurIA-1);
							retour.add(config2);
						}
					}
				}
			}
		}
		
		//REFAIRE COMPARAISONS
		//retour.sort(null);
		if(retour.isEmpty()) {
			System.err.println(" AhA ! "+config);
		}
		
		return retour;
	}
	
	float infinie(){
		// doit etre superieur a l'heuristique
		return Float.MAX_VALUE;
	}
	
	String prec;
	
	long nbConfigs;
	
	float minMax(ConfigurationIA config, float borneMax, float borneMaxAdv, int profondeur,int debugHauteur){
		
	//debug debut
		//System.err.println(bornesMax[0] + " " + bornesMax[1] + " ");
		nbConfigs++;
	//debug fin
		
		
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
		List<ConfigurationIA> fils = trouverFils(config);
		Iterator<ConfigurationIA> it = fils.iterator();
		
		float max = -infinie();//-borneMaxAdv;
		while(it.hasNext()){
			ConfigurationIA configF = it.next();
			Float tmp = configurations.get(configF);
			if(tmp==null){
				//printDebug1(debugHauteur);System.out.println("Joueur "+config.getJoueur()+" joue "+TestsIA.iToS(configF.getCarte()));
				if(config.getJoueur()==configF.getJoueur()) {
					tmp =  minMax(configF,borneMax,borneMaxAdv,profondeur-1,debugHauteur+1);
				} else {
					tmp = -minMax(configF,borneMaxAdv,borneMax,profondeur-1,debugHauteur+1);
				}
				//printDebug1(debugHauteur);System.err.println("Joueur "+config.getJoueur()+" joue "+TestsIA.iToS(configF.getCarte()) + " resultat : "+tmp);
				configurations.put(configF, tmp);
			}
			if(tmp > borneMax){// alpha/beta reduction
				return infinie();//tmp;//A commenter/decommenter
			} else {
				//System.out.println();
			}
			max = Math.max(max,tmp);
			borneMaxAdv = -max;
		}
		return max;
	}
	
	int trouverProfondeur(ConfigurationIA config){
		int retour = 3;
		return retour;
	}
	
	int minMaxInitial(ConfigurationIA config){
		int carteAJouer = -1;
		int profondeur  = trouverProfondeur(config);
		
		List<ConfigurationIA> fils = trouverFils(config);
		Iterator<ConfigurationIA> it = fils.iterator();
		
		float borneMax = infinie();
		float borneMaxAdv = infinie();
		
		float max = -infinie();
		while(it.hasNext()){
			ConfigurationIA configF = it.next();
			Float tmp = configurations.get(configF);
			if(tmp==null){
				//printDebug1(100);System.out.println("Joueur "+config.getJoueur()+" joue "+TestsIA.iToS(configF.getCarte()));
				if(config.getJoueur()==configF.getJoueur()) {
					tmp = minMax(configF,borneMax,borneMaxAdv,profondeur,1);
				} else {
					tmp = -minMax(configF,borneMaxAdv,borneMax,profondeur,1);
				}
				configurations.put(configF, tmp);
				//printDebug1(100);System.err.println("Joueur "+config.getJoueur()+" joue "+TestsIA.iToS(configF.getCarte()) + " resultat : "+tmp);
			}
			
			//System.err.println("Carte : "+TestsIA.iToS(configF.getCarte())  + " Valeur : " + tmp);
			if(tmp > max){
				max = tmp;
				carteAJouer = configF.getCarte();
				//System.err.print(" Superieur !");
			}
			//System.err.println();
			max = Math.max(max,tmp);
			borneMaxAdv = -max;
		}
		//System.err.println(max);
		return carteAJouer;
	}
	
	void printDebug1(int h) {
		for(int i=0;i<h;i++) {
			System.out.print("|");
		}
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
				System.err.println("okay");
			}
		}
		//System.err.println(trouverMain());
		config = new ConfigurationIA(trouverMain(),joueurIA-1,jeu.phasetourterm(),carte,jeu.atout()-1);
				
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

	Hashtable<ConfigurationIA,Float> configurations;
	Random r;
	
	@Override
	public int jouerCoup() { //A faire / finir
		if(r==null) { // Initialisation
			r=new Random();
			configurations = new Hashtable<>();
		} else {
			configurations.clear();
		}
		
		nbConfigs=0;
		
		int idCarte = minMaxInitial(trouverConfiguration());
		int retour=trouverIndice(idCarte);
		
		System.err.println("Carte : "+TestsIA.iToS(idCarte)+", indice : "+retour);
		return retour;
	}
}
