package Controleur;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Enumeration;

//import Modele.Carte;
import Modele.Coup;

//joueur : 0 ou 1

class CartesM {
	public static long TREFLE = 0b1111111111111;
	public static long CARREAU = 0b1111111111111 << 13;
	public static long COEUR = 0b1111111111111 << 26;
	public static long PIQUE = 0b1111111111111 << 39;
	public static long ALL = ( ~0 << 12 ) >>> 12;
	
	public static int nbCartes(long ensemble){
		int s=0;
		for(int i=0;i<52;i++){
			s += (ensemble & (0b1 << i)) >>> i;
		}
		return s;
	}
	
	public static long mCouleur(int c) {
		switch(c){
			case -1: return 0;
			case 0 : return TREFLE;
			case 1 : return CARREAU;
			case 2 : return COEUR;
			case 3 : return PIQUE;
			default :
				System.err.println("couleur d'indice "+c+" inconnue");
				return 0;
		}
	}
	public static short tabToCarte(long tab) {
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
	long[] nmainsJ;
	
	InfoPlateau(){
		mainsJ = new long[2];
		plisJ = new long[2];
		pioche = new long[5];
		nmainsJ = new long[2];
	}
	
	void addMain (int joueur, int carte) {
		mainsJ[joueur] |= 1 << carte;
	}
	
	void addPli (int joueur, int carte) {
		plisJ[joueur] |= 1 << carte;
	}
	
	void delMain (int joueur, int carte) {
		mainsJ[joueur] &= ~(1 << carte);
	}
	
	void delPli (int joueur, int carte) {
		plisJ[joueur] &= ~(1 << carte);
	}
	
	void iniPioche (int c1,int c2,int c3,int c4,int c5,int c6) {
		pioche[4] = (1 << c1) | (1 << c2) | (1 << c3) | (1 << c4) | (1 << c5) | (1 << c6);
	}
	
	void updatePioche (int carte1, int carte2) {
		for(int i=0;i<5;i++) {
			if((pioche[i] & (1 << carte1))!=0) {
				pioche[i] &= ~(1 << carte1);
				if(i>0) {
					pioche[i-1] |= (1 << carte2);
				}
				return;
			}
		}
	}
	
	/*
	carte inconnues du point de vue du joueur j de main mainJ
	*/
	long cartesInconnuesJ(long mainJ, int j){//A refaire
		return ~mainJ & ~mainsJ[j] & ~mainsJ[(j+1)%2] & ~cartesPlis() & ~cartesPioche() & ~nmainsJ[j];
	}
	
	long cartesPioche(){
		return pioche[0] | pioche[1] | pioche[2] | pioche[3] | pioche[4];
	}
	
	long cartesPlis(){
		return plisJ[0] | plisJ[1];
	}
	
	float esperance(int carte, int atout, long ensemble, boolean countAtout) {
		long maskCouleur = CartesM.mCouleur(carte%13);
		float nbc;
		float nbcGagnante;
		if((ensemble & maskCouleur) == 0){
			if(countAtout){
				nbc = (float) CartesM.nbCartes(ensemble & ~maskCouleur);
				nbcGagnante = (float) CartesM.nbCartes(ensemble & atout);
				if(nbc==0){
					nbc = 1;
					nbcGagnante = 0;
				}
			} else {
				nbc = 1;
				nbcGagnante = 0;
			}
		} else {
			nbc = (float) CartesM.nbCartes(ensemble & maskCouleur);
			nbcGagnante = (float) CartesM.nbCartes((ensemble & maskCouleur) >>> carte);
		}
		return nbcGagnante / nbc;
	}
	
	float esperanceMoy(int carte, int atout, int joueur, long infoCartes) {
		float espSur;
		float espInc;// mainsJ[joueur%2]
		long cartesInc = cartesInconnuesJ(infoCartes, (joueur+1)%2);
		boolean aCouleur =( mainsJ[(joueur+1)%2] & CartesM.mCouleur(carte%13)) != 0;
		espSur = esperance(carte,atout,mainsJ[(joueur+1)%2],!aCouleur);
		espInc = esperance(carte,atout,cartesInc,!aCouleur) * (11 - CartesM.nbCartes(mainsJ[(joueur+1)%2])) / CartesM.nbCartes(cartesInc);
		return espInc + espSur;
	}
	
	boolean estFinal(){
		return CartesM.nbCartes(plisJ[0] & plisJ[1]) == 52;
	}
}

class Configuration {
	/*int avancementTour;
	32 bits : 100...0000033222222
	1 : joueur
	2 : carte (si coup de reponse)
	3 : phase
		0 -> joueur pose
		1 -> autre repond
		<mise a jour joueur>
		2 -> joueur pioche
		3 -> autre pioche
		
	public int getJoueur(){
		return avancementTour >>> 31;
	}
	public int getCarte(){
		return avancementTour & 0b111111;
	}
	public int getPhase(){
		return (avancementTour >>> 6) & 0b11;
	}
	*/
	long mainJ;
	InfoPlateau info;
	int joueur;
	int phase;
	int carte;
	int atout;
	
	public Configuration(long m, int j, int p,int c,int a){
		info = new InfoPlateau();
		mainJ = m;
		joueur = j;
		phase = p;
		carte = c;
		atout = a;
	}
	
	void addMain (int joueur, int carte) {
		info.addMain(joueur,carte);
	}
	
	void addPli (int joueur, int carte) {
		info.addPli(joueur,carte);
	}
	
	void delMain (int joueur, int carte) {
		info.delMain(joueur, carte);
	}
	
	void delPli (int joueur, int carte) {
		info.delPli(joueur, carte);
		
	}
	
	void iniPioche (int c1,int c2,int c3,int c4,int c5,int c6) {
		info.iniPioche(c1,c2,c3,c4,c5,c6);
	}
	
	public int getJoueur(){ 
		return joueur;
	}
	
	public int getPhase() {
		return phase;
	}
	
	public int getCarte() {
		return carte;
	}
	
	private boolean isGreater(int c1,int c2) {
		boolean retour = false;
		retour = c1 > c2 && (c2%13!=atout || c1%13==atout);
		return retour;
	}
	
	//carte suppose valide
	public void update(int carte1,int carte2){
		switch(phase) {
		case 0 :
			this.carte = carte1;
			delMain(joueur,carte1);
			mainJ &= ~(1<<carte1);
			joueur = (joueur+1)%2;
			break;
		case 1 :
			this.carte = carte1;
			delMain(joueur,carte1);
			mainJ &= ~(1<<carte1);
			if(!isGreater(this.carte,carte1)) {
				joueur = (joueur+1)%2;
			}
			break;
		case 2 :
		case 3 :
			this.carte = carte1;
			addMain(joueur,carte1);
			mainJ |= (1<<carte1);
			info.updatePioche(carte1, carte2);
			joueur = (joueur+1)%2;
		
		}
		if(info.cartesPioche()==0) {
			phase = (phase + 1) % 2;
		} else {
			phase = (phase + 1) % 4;
		}
	}
	
	public boolean estFinal(){
		return info.estFinal();
	}
}

public class IAMinMax extends IA {
	
	short joueurIA;// 0 ou 1
	
	Hashtable<Configuration,Float> configurations;
	
	float heuristique(Configuration config){ //A faire
		return 0;
	}
	
	List<Configuration> trouverFils(Configuration config){ //A faire
		return null;
	}
	
	int infinie(){
		// doit etre superieur a l'heuristique
		return 100000;
	}
	
	float minMax(Configuration config, int profondeur, float borneMax, float borneMaxAdv){
		if(config.estFinal() || profondeur==0){
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
	
	int minMaxInitial(Configuration config, int profondeur){
		int carteAJouer = -1;
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
				carteAJouer = configF.getCarte();
			}
			max = Math.max(max,tmp);
		}
		return carteAJouer;
	}
	
	
	Random r;
	
	InfoPlateau compterCarte () { //A faire
		return null;
	}
	
	Configuration trouverConfiguration() { //A faire
		Configuration config = null;
		Enumeration<Coup> en = historique.getEnumPasse();
		
		while(en.hasMoreElements()) {
			//Coup courant = en.nextElement();
		}
		return config;
	}
	
	@Override
	public int jouerCoup() { //A faire / finir
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
