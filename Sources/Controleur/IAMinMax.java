package Controleur;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

//joueur : 0 ou 1

class MasquesCartes {
	public static long MC_trefle = 0b1111111111111;
	public static long MC_carreau = 0b1111111111111 << 13;
	public static long MC_coeur = 0b1111111111111 << 26;
	public static long MC_pique = 0b1111111111111 << 39;
	public static long M_all = ( ~0 << 12 ) >>> 12;
	
	public int nbCartes(long ensemble){
		int s=0;
		for(int i=0;i<52;i++){
			s += (ensemble & (0b1 << i)) >>> i;
		}
		return s;
	}
	
	public long Mfunc_Couleur(int c) {
		switch(c){
			case -1: return 0;
			case 0 : return MC_trefle;
			case 1 : return MC_carreau;
			case 2 : return MC_coeur;
			case 3 : return MC_pique;
			default :
				System.err.println("couleur d'indice "+c+" inconnue");
				return 0;
		}
	}
	public short tabToCarte(long tab) {
		for(short i=0;i<52;i++){
			if((tab & (0b1 << i)) >>> i == 1){
				return i;
			}
		}
		//ERREUR
		return -1;
	}
}

class InfoPlateau {
	long[] mainsJ;
	long[] plisJ;
	long[] pioche;
	
	InfoPlateau(){
		mainsJ = new long[2];
		plisJ = new long[2];
		pioche = new long[5];
	}
	
	/*
	carte inconnues du point de vue du joueur j de main mainJ
	*/
	long cartesInconnues(long mainJ, int j){
		return ~mainJ & ~mainsJ[j] & ~mainsJ[(j+1)%2] & ~cartesPlis() & ~cartesPioche();
	}
	
	long cartesPioche(){
		return pioche[0] | ~pioche[1] | pioche[2] | pioche[3] | pioche[4];
	}
	
	long cartesPlis(){
		return plisJ[0] | plisJ[1];
	}
	
	float[] esperanceSup (long mainJ, int joueur, int atout) {
		//float[] esperances = new float[52];
		//A faire
		return null;
	}
	
	float[] esperanceInf (long mainJ, int joueur, int atout) {
		//float[] esperances = new float[52];
		//A faire
		return null;
	}
	
	float[] esperanceRng (long mainJ, int joueur, int atout) {
		//float[] esperances = new float[52];
		//A faire
		return null;
	}
	
	long cartesMains(long mainJ){
		return mainJ | mainsJ[0] | mainsJ[1];
	}
}

class Configuration {
	long mainJ;
	int avancementTour;
	/*32 bits : 100...0000033222222
	1 : joueur
	2 : carte (si coup de reponse)
	3 : phase
		0 -> joueur pose
		1 -> autre repond
		<mise a jour joueur>
		2 -> joueur pioche
		3 -> autre pioche
	*/
	InfoPlateau info;
	int getJoueur(){
		return avancementTour >>> 15;
	}
	int getCarte(){
		return avancementTour & 0b111111;
	}
	int getPhase(){
		return (avancementTour >>> 6) & 0b11;
	}
}

class IAMinMax extends IA {
	
	short joueurIA;// 0 ou 1
	
	Hashtable<Configuration,Float> configurations;
	
	
	
	boolean estFinal(Configuration config){
		return config.info.cartesMains(config.mainJ)==0;//A refaire
	}
	
	int heuristique(Configuration config){
		//A faire
		return 0;
	}
	
	List<Configuration> trouverFils(Configuration config){
		//A faire
		return null;
	}
	
	int infinie(){
		//A chercher
		return 0;
	}
	
	//Max A faire ?
	
	short trouverCarteAJouer(Configuration config,Configuration configF){
		//A faire / finir
		if((config.getPhase()+1)%4 != configF.getPhase()){
			System.err.println("Les configurations ne se suivent pas");
			//ERREUR CRITIQUE
		}
		switch(config.getPhase()){
			case 0 :
				//return config.info.mainsJ[config.getJoueur()] & ~configF.info.mainsJ[config.getJoueur()];
			case 1 :
				//return config.info.mainsJ[config.getJoueur()] & ~configF.info.mainsJ[config.getJoueur()];
			case 2 :
				
			case 3 :
				
			default :
				System.err.println("configurations inconnue : "+config.getPhase());
				//ERREUR CRITIQUE
		}
		return 0;
	}
	
	float minMax(Configuration config, int profondeur, float borneMax, float borneMaxAdv){
		if(estFinal(config) || profondeur==0){
			return heuristique(config);
		}
		List<Configuration> fils = trouverFils(config);
		Iterator<Configuration> it = fils.iterator();
		float max = -borneMaxAdv;
		while(it.hasNext()){
			Configuration configF = it.next();
			Float tmp = configurations.get(configF);
			if(tmp==null){
				tmp = minMax(configF,profondeur-1,-max, borneMax);
			}
			if(tmp > borneMax){// alpha/beta reduction
				return max;
			}
			max = Math.max(max,tmp);
		}
		return max;
	}
	
	short minMaxInitial(Configuration config, int profondeur){
		short carteAJouer = -1;
		List<Configuration> fils = trouverFils(config);
		Iterator<Configuration> it = fils.iterator();
		float max = -infinie();
		while(it.hasNext()){
			Configuration configF = it.next();
			Float tmp = configurations.get(configF);
			if(tmp==null){
				minMax(configF,profondeur-1,-max, infinie());
			}
			if(tmp > max){
				max = tmp;
				carteAJouer = trouverCarteAJouer(config,configF);
			}
			max = Math.max(max,tmp);
		}
		return carteAJouer;
	}
	
	InfoPlateau compteureDeCarte;
	Random r;
	
	@Override
	public int jouerCoup() {
		
		if(r==null) { // Initialisation
			r=new Random();
		}
		
		int retour=-1;
		switch(jeu.phasetour()%jeu.phase()) {
			case 0:
				break;
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
		}
		
		System.err.println("IA MIN MAX NON IMPLENTE");
		
		return retour;
	}

}
