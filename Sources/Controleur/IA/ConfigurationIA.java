package Controleur.IA;

class InfoPlateau implements Cloneable{
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
		mainsJ[joueur] |= 1L << carte;
	}
	
	void addPli (int joueur, int carte) {
		plisJ[joueur] |= 1L << carte;
	}
	
	void delMain (int joueur, int carte) {
		mainsJ[joueur] &= ~(1L << carte);
	}
	
	void delPli (int joueur, int carte) {
		plisJ[joueur] &= ~(1L << carte);
	}
	
	void delCouleur(int joueur,int couleur) {
		nmainsJ[joueur] |= CartesMIA.mCouleur(couleur);
	}
	
	/*void iniPioche (int c1,int c2,int c3,int c4,int c5,int c6) {
		pioche[4] = (1L << c1) | (1L << c2) | (1L << c3) | (1L << c4) | (1L << c5) | (1L << c6);
	}*/
	
	void addPioche(int numPioche,int carte) {
		pioche[numPioche] |= (1L << carte);
	}
	
	void updatePioche (int carte1, int carte2) {
		for(int i=0;i<5;i++) {
			if((pioche[i] & (1L << carte1))!=0) {
				pioche[i] &= ~(1L << carte1);
				if(i>0) {
					pioche[i-1] |= (1L << carte2);
				}
				return;
			}
		}
	}
	
	/*
	carte inconnues du joueur j avec mainJ info en plus
	*/
	long cartesInconnuesJ(long mainJ, int j){//A refaire ?
		//return ~mainJ & ~mainsJ[j] & ~mainsJ[(j+1)%2] & ~cartesPlis() & ~cartesPioche() & ~nmainsJ[j];
		return cartesInconnues(mainJ) & ~nmainsJ[j];
	}
	
	long cartesInconnues(long mainJ){
		return CartesMIA.ALL & ~mainJ & ~cartesPioche() & ~cartesMain() & ~cartesPlis();
	}
	
	long cartesMain() {
		return mainsJ[0] | mainsJ[1];
	}
	
	long cartesMain(int j) {
		return mainsJ[j];
	}
	
	long cartesPioche(){
		return pioche[0] | pioche[1] | pioche[2] | pioche[3] | pioche[4];
	}
	
	long cartesPioche(int p){
		return pioche[p];
	}
	
	long cartesPlis(){
		return plisJ[0] | plisJ[1];
	}
	
	long cartesPlis(int j){
		return plisJ[j];
	}
	
	float esperance(int carte, int atout, long ensemble, boolean countAtout) {
		long maskCouleur = CartesMIA.mCouleur(carte/13);
		int nbc = 1;
		int nbcGagnante = 0;
		if((ensemble & maskCouleur) == 0){
			if(countAtout){
				nbc = CartesMIA.nbCartes(ensemble & ~maskCouleur);
				nbcGagnante = CartesMIA.nbCartes(ensemble & CartesMIA.mCouleur(atout));
			}
		} else {
			nbc = CartesMIA.nbCartes(ensemble & maskCouleur);
			nbcGagnante = CartesMIA.nbCartes((ensemble & maskCouleur) >>> carte);
		}
		if(nbc==0){
			nbc = 1;
			nbcGagnante = 0;
		}
		return 1f - (2f * ((float)nbcGagnante) / ((float) nbc));
	}

	
	public float esperanceMoy(int carte, int atout, int joueur , long infoCartes) {
		float espSur;
		float espInc = 0;
		long cartesInc = cartesInconnuesJ(infoCartes, (joueur+1)%2);
		boolean aCouleur =( mainsJ[(joueur+1)%2] & CartesMIA.mCouleur(carte/13)) != 0;
		espSur = esperance(carte,atout,mainsJ[(joueur+1)%2],!aCouleur);
		
		if(CartesMIA.nbCartes(cartesInc)!=0) {
			espInc = esperance(carte,atout,cartesInc,!aCouleur) * (11 - CartesMIA.nbCartes(mainsJ[(joueur+1)%2])) / CartesMIA.nbCartes(cartesInc);
		}
		return espInc + espSur;
	}
	
	public long reponsePossible(int carte, int atout, long ensemble) {
		long retour;
		retour = ensemble & CartesMIA.mCouleur(carte/13);
		if(retour == 0){
			retour = ensemble;
		}
		return retour;
	}
	
	
	
	boolean estFinal(){
		return CartesMIA.nbCartes(plisJ[0] & plisJ[1]) == 52;
	}

	@Override
	public boolean equals(Object o){
		if(getClass()!=o.getClass()){
			return false;
		}
		InfoPlateau p = (InfoPlateau) o;
		return	p.mainsJ[0]==mainsJ[0] &&
				p.mainsJ[1]==mainsJ[1] && 
				p.plisJ[0]==plisJ[0] && 
				p.plisJ[1]==plisJ[1] && 
				p.nmainsJ[0]==nmainsJ[0] &&
				p.nmainsJ[1]==nmainsJ[1] &&
				p.pioche[0]==pioche[0] &&
				p.pioche[1]==pioche[1] &&
				p.pioche[2]==pioche[2] &&
				p.pioche[3]==pioche[3] &&
				p.pioche[4]==pioche[4];
	}
	
	@Override
	public String toString() {
		return	mainsJ[0] + " " + mainsJ[1] + " | " +
				plisJ[0] + " " + plisJ[1] + " | " +
				nmainsJ[0] + " " + nmainsJ[1] + " | " +
				pioche[0] + " " + pioche[1] + " " + pioche[2] + " " + pioche[3] + " " + pioche[4];
	}
	
	@Override
	public InfoPlateau clone() {
		InfoPlateau c = new InfoPlateau();
		c.mainsJ[0] = mainsJ[0];
		c.mainsJ[1] = mainsJ[1];
		c.plisJ[0] = plisJ[0];
		c.plisJ[1] = plisJ[1];
		c.nmainsJ[0] = nmainsJ[0];
		c.nmainsJ[1] = nmainsJ[1];
		c.pioche[0] = pioche[0];
		c.pioche[1] = pioche[1];
		c.pioche[2] = pioche[2];
		c.pioche[3] = pioche[3];
		c.pioche[4] = pioche[4];
		return c;
	}
	
}

class ConfigurationIA implements Cloneable, Comparable<ConfigurationIA> {
		long mainJ;
		InfoPlateau info;
		int joueur;
		int phase;
		int carte;
		int atout;
		boolean estIA;
		
		public ConfigurationIA(long m, int j, int p,int c,int a,boolean estIA){
			info = new InfoPlateau();
			mainJ = m;
			joueur = j;
			phase = p;
			carte = c;
			atout = a;
			this.estIA=estIA;
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
		
		void delCouleur(int joueur,int couleur) {
			info.delCouleur(joueur, couleur);
		}
		
		void addPioche(int numPioche,int carte) {
			info.addPioche(numPioche, carte);
		}
		
		public long getPossibleDonne() {
			if(estIA) {
				return mainJ;
			} else {
				return info.cartesInconnuesJ(mainJ, joueur) | info.cartesMain(joueur);
			}
		}
		
		public long getPossibleReponse() {
			if(estIA) {
				return info.reponsePossible(carte, atout,mainJ);
			} else {
				return info.reponsePossible(carte, atout,info.cartesMain(joueur) | info.cartesInconnuesJ(mainJ, joueur));
			}
		}
		
		public long getPossiblePiocheCachee(){
			return info.cartesInconnues(mainJ);
		}
		
		public long getPossiblePioche() {
			return info.cartesPioche();
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
			retour = retour || (c2/13 == c1/13) && (c1%13 > c2%13);// si de meme couleur
			retour = retour || (c1/13 != c2/13) && (c2/13!=atout); // si de differentes couleur
			return retour;
		}
		
		public void update(int carte1,int carte2){
			switch(phase) {
			case 0 :
				delMain(joueur,carte1);
				if(estIA) {
					mainJ &= ~(1L<<carte1);
				}
				joueur = (joueur+1)%2;
				estIA = !estIA;
				info.addPli(joueur, carte1);//temporaitement, supprimer en phase 1
				info.addPli((joueur+1)%2, carte1);//temporaitement, supprimer en phase 1
				this.carte = carte1;
				break;
			case 1 :
				delMain(joueur,carte1);
				if(estIA) {
					mainJ &= ~(1L<<carte1);
				}
				info.delPli(joueur, this.carte);//suppression de la carte placee temporairement en phase 0
				info.delPli((joueur+1)%2, this.carte);//suppression de la carte placee temporairement en phase 0
				
				//System.err.print(TestsIA.iToS(this.carte)+" VS "+TestsIA.iToS(carte1)+" ");
				if(isGreater(this.carte,carte1)) {
					//System.out.print(TestsIA.iToS(this.carte));
					joueur = (joueur+1)%2;
					estIA = !estIA;
				} else {
					//System.out.print(TestsIA.iToS(carte1));
				}
				//System.out.println(" gagne");
				info.addPli(joueur, this.carte);
				info.addPli(joueur, carte1);
				this.carte = carte1;
				break;
			case 2 :
			case 3 :
				addMain(joueur,carte1);
				if(estIA) {
					mainJ |= (1L<<carte1);
				}
				info.updatePioche(carte1, carte2);
				joueur = (joueur+1)%2;
				estIA = !estIA;
				this.carte = carte1;
			}
			if(info.cartesPioche()==0) {
				phase = (phase + 1) % 2;
			} else {
				phase = (phase + 1) % 4;
			}
		}
		
		public boolean estFinal(){
			return info.cartesMain()==0L && mainJ==0L;
		}
		
		int carteIncMax() {
			int iMax = -1;
			float eMax = Float.MIN_VALUE;
			for(int i=0;i<52;i++) {
				if((info.cartesInconnues(mainJ) & (1L << i))!=0) {
					float e = info.esperance(i, atout, info.cartesInconnues(0L), true);
					if(e >= eMax) {
						eMax = e;
						iMax = i;
					}
				}
			}
			return iMax;
		}
		
		int carteIncMin() {
			int iMin = -1;
			float eMin = Float.MAX_VALUE;
			for(int i=0;i<52;i++) {
				if((info.cartesInconnues(mainJ) & (1L << i))!=0) {
					float e = info.esperance(i, atout, info.cartesInconnues(0L), true);
					if(e <= eMin) {
						eMin = e;
						iMin = i;
					}
				}
			}
			return iMin;
		}
		
		//FONCTIONNE UNIQUEMENT SI LE JOUEUR EST L'IA
		float esperanceMainJoueur() {
			float retour = 0;
			for(int i=0;i<52;i++) {
				if((mainJ & (1L << i))!=0) {
					retour += info.esperanceMoy(i, atout, joueur, mainJ);
				}
			}
			return retour;
		}
		
		//FONCTIONNE UNIQUEMENT SI LE JOUEUR EST L'IA
		float esperanceMainAdverse() {
			float retour = 0;
			long cartesCon = info.cartesMain((joueur + 1) % 2);
			long cartesInc = info.cartesInconnuesJ(mainJ, (joueur + 1) % 2);
			
			if(CartesMIA.nbCartes(cartesInc)!=0) {
				for(int i=0;i<52;i++) {
					if((cartesInc & (1L << i))!=0) {
						retour += info.esperanceMoy(i, atout, (joueur+1)%2, 0);
					}
				}
				retour = retour * (11-CartesMIA.nbCartes(cartesCon)) / CartesMIA.nbCartes(cartesInc); 
			}
			
			
			cartesCon = info.cartesMain((joueur + 1) % 2);
			for(int i=0;i<52;i++) {
				if((cartesCon & (1L << i))!=0) {
					retour += info.esperanceMoy(i, atout, (joueur+1)%2, 0);
				}
			}
			return retour;
		}
		
		//FONCTIONNE UNIQUEMENT SI LE JOUEUR EST L'IA EN PHASE 0
		float heuristique() {
			float he = 0;
			if(!this.estFinal()) {
				he += esperanceMainJoueur();
				he -= esperanceMainAdverse();
			}
			
			if(!estIA) {
				he*=-1;
			}
			
			he += CartesMIA.nbCartes(info.cartesPlis(joueur)) / 2f;
			he -= CartesMIA.nbCartes(info.cartesPlis((joueur+1)%2)) / 2f;
			
			return he;
		}
		
		int nbCartesInconnue() {
			return CartesMIA.nbCartes(info.cartesInconnues(mainJ));
		}
		
		int nbCartesMain() {
			return CartesMIA.nbCartes(mainJ);
		}
		
		@Override
		public int hashCode(){
			return this.toString().hashCode();
		}
		
		@Override
		public boolean equals(Object o){
			if(getClass()!=o.getClass()){
				return false;
			}
			ConfigurationIA c = (ConfigurationIA) o;
			return	(c.carte==carte || phase==1) && 
					c.mainJ==mainJ && 
					c.joueur==joueur && 
					c.phase==phase && 
					c.atout==atout &&
					c.estIA==estIA &&
					c.info.equals(info);
		}

		@Override
		public ConfigurationIA clone() {
			ConfigurationIA c;
			c = new ConfigurationIA(mainJ,joueur,phase,carte,atout,estIA);
			c.info = info.clone();
			return c;
		}
		
		@Override
		public String toString() {
			return mainJ + " " + joueur + " " + phase + " " + carte + " " + atout + " " + estIA +  " | " + info;
		}

		@Override
		public int compareTo(ConfigurationIA c) { // A REFAIRE
			return (int) ((Float)(heuristique()*10)).compareTo(c.heuristique()*10);
		}
}
