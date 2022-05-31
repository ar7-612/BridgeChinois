package Controleur.IA;

import java.util.Deque;
//import java.util.HashMap;
import java.util.Hashtable;
import java.io.Serializable;
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

class TimeCheckException extends Exception  implements Serializable {
	/**
	 * utile pour sortir de la recurtion si le temp d'attente est trop important
	 */
	private static final long serialVersionUID = 1L; // a verifier
	public TimeCheckException(String m) {
		super(m);
	}
}

public class IAMinMax extends IAbase {
	/* IAMinMax (constructeur)
	 * heuristique 
	 * incertitude 
	 */
	int difficulte;
	long delayVise;
	long delayMax;
	int profondeurMax;
	public IAMinMax(int difficulte){
		this.difficulte = difficulte;
		switch(difficulte) {
		case 0 :
			delayVise = 500;
			delayMax = 1000;
			profondeurMax = 120;
			break;
		case 1 :
			delayVise = 500;
			delayMax = 1000;
			profondeurMax = 120;
			break;
		case 2 :
			delayVise = 500;
			delayMax = 1000;
			profondeurMax = 120;
			break;
		default :
			delayVise = 500;
			delayMax = 1000;
			profondeurMax = 120;
			break;
		}
	}
	
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
	float incertitude(ConfigurationIA config, ConfigurationIA configF) {
		float retour = 1;
		long ensembleIncertain = 0;
		switch(config.getPhase()) {
		case 0:
			ensembleIncertain = config.getDonneIncertaine();
			break;
		case 1:
			ensembleIncertain = config.getReponseIncertaine();
			break;
		case 2:
		case 3:
			ensembleIncertain = 0;
			break;
		}
		if((ensembleIncertain & (1L << configF.getCarte()))!=0) {
			/* caractere>1 : pessimiste
			 * caractere<1 : optimiste
			 * caractere=1 : realiste
			 * caractere=0 : "si c'est pas sur d'arriver, ca n'arriveras pas"
			 * caractere=taille : "si ca peut arriver, ca arriverras"
			 */
			float caractere;
			switch(difficulte) {
			case 0:
				caractere = 0f;
				//caractere = 1f;
				break;
			case 1:
				caractere = CartesMIA.nbCartes(ensembleIncertain);
				//caractere = 1f;
				break;
			case 2:
			default:
				caractere = 1f;
				break;
			}
			retour = Math.min(1f, caractere / CartesMIA.nbCartes(ensembleIncertain));
		}
		return retour;
	}
	float heuristique(ConfigurationIA config){
		float h;
		if(config.nbCartesInconnue()==0) {
			h = config.heuristiquePiocheVide();
		} else {
			/* heuristiquePartieEnCour1 : prend en compte autant les plis que cartes en main
			 * heuristiquePartieEnCour2 : prend plus en compte les cartes en main que les plis
			 * heuristiquePartieEnCour3 : prend uniquement en compte les cartes en main
			 */
			switch(difficulte) {
			case 0:
				h = config.heuristiquePartieEnCour1();
				break;
			case 1:
				h = config.heuristiquePartieEnCour1();
				break;
			case 2:
			default:
				h = config.heuristiquePartieEnCour1();
				break;
			}
		}
		return h;
	}

	Deque<ConfigurationIA> trouverFilsV1_0(ConfigurationIA config){
		Deque<ConfigurationIA> retour = new ArrayDeque<ConfigurationIA>();
		long ensemble1 = 0;
		int cartePioche = -1;
		
		switch(config.getPhase()) {
			case 0 :
				if(config.getJoueur()==joueurIA-1) {
					ensemble1 = config.getDonnePossible();
				} else {
					ensemble1 = config.getDonnePossible();
				}
				break;
			case 1 :
				if(config.getJoueur()==joueurIA-1) {
					ensemble1 = config.getReponsePossible();
				} else {
					ensemble1 = config.getReponsePossible();
				}
				break;
			case 2 : 
				if(config.getJoueur()==joueurIA-1) {
					ensemble1 = config.getPiochePossible();
					if(difficulte==-12) {
						cartePioche = config.carteIncMax();
					} else {
						cartePioche = config.carteIncMoy();
					}
					
				} else {
					ensemble1 = config.getPiochePossible();
					if(difficulte==-12) {
						cartePioche = config.carteIncMin();
					} else {
						cartePioche = config.carteIncMoy();
					}
				}
				break;
			case 3 :
				if(config.getJoueur()==joueurIA-1) {
					ensemble1 = config.getPiochePossible();
					//cartePioche = unDans(config.getPossiblePiocheCachee());
					cartePioche = config.carteIncMoy();
				} else {
					ensemble1 = config.getPiochePossible();
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
			
			tmp = tmp * incertitude(config,configF);
			
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
	List<Couple<Integer,Float>> minMaxInitial(ConfigurationIA config, int profondeur) throws TimeCheckException{
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
		
		//System.err.println("---------------------");
		
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
		
		return tableCartes;
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

	/*
	 * 
	 */
	
	Hashtable<ConfigurationIA,ConfigurationIA> configurations;
	Random r;
	long startTime;
	
	void checkTime() throws TimeCheckException {
		if(System.currentTimeMillis() - startTime > delayMax) {
			throw new TimeCheckException("trop long");
		}
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
	/*private int choisirCarte(List<Couple<Integer,Float>> listeCartes) {
		int retour;
		int i=listeCartes.size()-1;
		retour = listeCartes.get(i).v;
		while(i!=0) {
			float borneMax; 
			switch(difficulte) {
	        case 0:
	        	borneMax = 2.0f;
	        	break;
	        case 1:
	        	borneMax = 1.5f;
	        	break;
	        case 2:
	        default:// <1 => toujours meilleur carte
	        	borneMax = 0.5f;
	        	break;
	        }
			if(r.nextFloat(borneMax)<1f) {
				i = 0;
			} else {
				i--;
				retour = listeCartes.get(i).v;
			}
		}
		return retour;
	}*/
	@Override
 	public int jouerCoup() {
		if(r==null) { // Initialisation
			r=new Random();
			configurations = new Hashtable<>();
		} else {
			configurations = new Hashtable<>();
		}
        ConfigurationIA config = trouverConfiguration();
        List<Couple<Integer,Float>> listeCartes = null;
        int retour = -10;
        startTime = System.currentTimeMillis();
        int profondeur = 0;
        //config = TestsIA.config4();
        //profondeurMax = profondeur;
        try {
        	while(System.currentTimeMillis() - startTime < delayVise && profondeur<=profondeurMax) {
        		listeCartes = minMaxInitial(config,profondeur);
        		profondeur++;
            }
        } catch(TimeCheckException e) {
        	if(listeCartes==null) {
        		throw new RuntimeException("l'IA n'as pas eu le temps de trouver un coup");
        	}
        }
        if(listeCartes.size()==0) {
        	throw new RuntimeException("l'IA n'as pas trouvee de coups possible");
        }
        listeCartes.sort(null);
        retour=trouverIndice(listeCartes.get(listeCartes.size()-1).v);
		//System.err.print(profondeur + " ");
		//System.err.println("Carte : "+TestsIA.iToS(listeCartes.get(listeCartes.size()-1).v)+", indice : "+retour);
		return retour;
	}
}
