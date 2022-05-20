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
	
	Hashtable<ConfigurationIA,Float> configurations;
	
	//FONCTIONNE UNIQUEMENT SI CONFIG VALIDE
	float heuristique(ConfigurationIA config){ 
		return config.heuristique();
	}

	List<ConfigurationIA> trouverFils(ConfigurationIA config){ //A faire
		//update(int carte1,int carte2)
		List<ConfigurationIA> retour = new ArrayList<>();
		long ensemble1 = 0;
		long ensemble2 = 0;
		
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
			if((ensemble1 & (1L<<i))!=0) {
				if(ensemble2==0) {
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
		
		return retour;
	}
	
	int infinie(){
		// doit etre superieur a l'heuristique
		return Integer.MAX_VALUE;
	}
	
	float [] bornesMax;
	String prec;
	
	long nbConfigs;
	
	float minMax(ConfigurationIA config, int profondeur){
		
	//debug debut
		//System.err.println(bornesMax[0] + " " + bornesMax[1] + " ");
		nbConfigs++;
	//debug fin
		
		
		if(config.estFinal()) {
			return heuristique(config);
		}
		if(profondeur==0) {
			if(config.getJoueur() == joueurIA-1 && config.getPhase()==0) { //Trop gourmand...
				return heuristique(config);
			} else {
				profondeur++;
			}
		}
		List<ConfigurationIA> fils = trouverFils(config);
		Iterator<ConfigurationIA> it = fils.iterator();
		float max = -bornesMax[(config.getJoueur()+1)%2];
		while(it.hasNext()){
			ConfigurationIA configF = it.next();
			Float tmp = configurations.get(configF);
			if(tmp==null){
				tmp = minMax(configF,profondeur-1);
				if(config.getJoueur()!=configF.getJoueur()) {
					tmp*=-1;
				}
				configurations.put(configF, tmp);
			}
			if(tmp > bornesMax[config.getJoueur()]){// alpha/beta reduction
				return max;
			} else {
				//System.out.println();
			}
			max = Math.max(max,tmp);
			bornesMax[(config.getJoueur()+1)%2] = Math.max(-max,bornesMax[(config.getJoueur()+1)%2]);
		}
		return max;
	}
	
	int minMaxInitial(ConfigurationIA config, int profondeur){
		int carteAJouer = -1;
		List<ConfigurationIA> fils = trouverFils(config);
		Iterator<ConfigurationIA> it = fils.iterator();
		bornesMax[(config.getJoueur()+1)%2]=infinie();
		bornesMax[config.getJoueur()]=infinie();
		float max = -infinie();
		while(it.hasNext()){
			ConfigurationIA configF = it.next();
			Float tmp = configurations.get(configF);
			if(tmp==null){
				tmp = minMax(configF,profondeur-1);
				if(config.getJoueur()!=configF.getJoueur()) {
					tmp*=-1;
				}
				configurations.put(configF, tmp);
			}
			System.err.print("Carte : "+configF.getCarte());
			if(tmp > max){
				max = tmp;
				carteAJouer = configF.getCarte();
				System.err.print(" Superieur !");
			}
			System.err.println();
			max = Math.max(max,tmp);
			bornesMax[(config.getJoueur()+1)%2] = Math.max(-max,bornesMax[(config.getJoueur()+1)%2]);
		}
		return carteAJouer;
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
	
	void debugAfficherCartes(long m) {
		for(int i=0;i<52;i++) {
			if((m & (1L << i)) != 0) {
				System.out.println("valeur " + (2+i%13) + " couleur "+i/13);
			}
		}
	}
	
	ConfigurationIA trouverConfiguration() { //A faire
		ConfigurationIA config = null;
		//debugAfficherCartes(trouverMain());
		config = new ConfigurationIA(trouverMain(),joueurIA-1,jeu.phasetourterm(),-1,jeu.atout()-1);
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

	
	
	Random r;
	@Override
	public int jouerCoup() { //A faire / finir
		if(r==null) { // Initialisation
			r=new Random();
			configurations = new Hashtable<>();
			bornesMax = new float[2];
		}
		
		nbConfigs=0;
		
		int retour=-10;
		int idCarte = minMaxInitial(TestsIA.config2(),3);
		Main m = (joueurIA==1 ? joueur1 : joueur2).main();
		for(int i=0;i<m.taille();i++) {
			if(carteToInt(m.carte(i))==idCarte) {
				retour = i;
				break;
			}
		}
		System.err.println("retour = "+ retour+" (id="+idCarte+")");
		return retour;
	}
}
