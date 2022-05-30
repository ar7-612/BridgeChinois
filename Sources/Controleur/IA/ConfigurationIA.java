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
	
	
	private float esperanceMoy1(int carte, int atout, int joueurAdv , long infoCartes) {
		float espSur;
		float espInc = 0;
		//long cartesInc = cartesInconnuesJ(infoCartes, (joueur+1)%2);//18.1
		long cartesInc = cartesInconnues(infoCartes);//18.2
		boolean aCouleur =( mainsJ[joueurAdv] & CartesMIA.mCouleur(carte/13)) != 0;
		espSur = esperance(carte,atout,mainsJ[joueurAdv],!aCouleur);
		
		if(CartesMIA.nbCartes(cartesInc)!=0) {
			espInc = esperance(carte,atout,cartesInc,!aCouleur) * (11 - CartesMIA.nbCartes(mainsJ[joueurAdv])) / CartesMIA.nbCartes(cartesInc);
		}
		return espInc + espSur;
	}
	private float esperanceMoy2(int carte, int atout, int joueur , long infoCartes) {
		float esperance;
		long ensemble = mainsJ[(joueur+1)%2] | cartesInconnues(infoCartes) | cartesPioche();
		boolean aCouleur =( mainsJ[(joueur+1)%2] & CartesMIA.mCouleur(carte/13)) != 0;
		esperance = esperance(carte,atout,ensemble,!aCouleur);
		return esperance;
	}
	private float esperanceMoy3(int carte, int atout, int joueur , long infoCartes) {
		float esperance;
		long ensemble = mainsJ[(joueur+1)%2] | cartesInconnues(infoCartes) | cartesPioche();
		esperance = esperance(carte,atout,ensemble,true);
		return esperance;
	}
	public float esperanceMoy(int carte, int atout, int joueurAdv , long infoCartes) {
		// 169 avec pioche dans cartes inconnues
		// 160
		// 157
		return esperanceMoy1(carte,atout,joueurAdv,infoCartes);
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
		
		public long getDonnePossible() {
			if(estIA) {
				return getDonneCertaine();
			} else {
				return getDonneIncertaine() | getDonneCertaine();
			}
		}
		public long getDonneCertaine() {
			if(estIA) {
				return mainJ;
			} else {
				return info.cartesMain(joueur);
			}
		}
		public long getDonneIncertaine() {
			if(estIA) {
				return 0;
			} else {
				return info.cartesInconnuesJ(mainJ, joueur);
			}
		}
		public long getReponsePossible() {
			if(estIA) {
				return info.reponsePossible(carte, atout,mainJ);
			} else {
				return info.reponsePossible(carte, atout,info.cartesMain(joueur) | info.cartesInconnuesJ(mainJ, joueur));
			}
		}
		public long getReponseCertaine() {
			if(estIA) {
				return info.reponsePossible(carte, atout,mainJ);
			} else {
				return info.reponsePossible(carte, atout,info.cartesMain(joueur));
			}
		}
		public long getReponseIncertaine() {
			if(estIA) {
				return 0;
			} else {
				return info.reponsePossible(carte, atout,info.cartesInconnuesJ(mainJ, joueur));
			}
		}
		public long getPiocheCacheePossible(){
			return info.cartesInconnues(mainJ);
		}
		public long getPiochePossible() {
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
				if(carte2!=-1) {
					info.updatePioche(carte1, carte2);
				}
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
		
		private float[] esperanceEnsemble(long ensemble) {
			float[] tab = new float[CartesMIA.nbCartes(ensemble)];
			int indice = 0;
			for(int i=0;i<52;i++) {
				if((ensemble & (1<<i))==0) {
					tab[indice]=info.esperanceMoy(i, atout, joueur, mainJ);
					indice++;
				}
			}
			return tab;
		}
		float[] esperancePioches() {
			return esperanceEnsemble(info.cartesPioche());
		}
		/**
		 * FONCTIONNE BIEN UNIQUEMENT SI JOUEUR EST IA
		 */
		float[] esperanceMainDonne() {
			return esperanceEnsemble(getReponsePossible());
		}
		float[] esperanceMainReponse() {
			return esperanceEnsemble(getDonnePossible());
		}
		
		int esperanceToCartePioche(float eMoy) {
			long ensemble = info.cartesPioche();
			
			int iMin = -1;
			float eMin = Float.MIN_VALUE;
			
			for(int i=0;i<52;i++) {
				if((ensemble & (1L << i))!=0) {
					float e = info.esperanceMoy(i, atout, joueur, mainJ);
					if(Math.abs(e - eMoy) <= eMin) {
						iMin = i;
						eMin = Math.abs(e - eMoy);
					}
				}
			}
			return iMin;
		}
		
		float esperanceCarteInc() {
			float eMoy = 0;
			for(int i=0;i<52;i++) {
				if((info.cartesInconnues(mainJ) & (1L << i))!=0) {
					eMoy += info.esperanceMoy(i, atout, joueur, 0L);
				}
			}
			eMoy = eMoy / ((float) CartesMIA.nbCartes(info.cartesInconnues(mainJ)));
			return eMoy;
		}
		
		int carteIncMoy() {
			float eMoy = 0;
			eMoy = esperanceCarteInc();
			
			int iMin = -1;
			float eMin = Float.MIN_VALUE;
			
			for(int i=0;i<52;i++) {
				if((info.cartesInconnues(mainJ) & (1L << i))!=0) {
					float e = info.esperanceMoy(i, atout, joueur, mainJ);//mainJ
					if(Math.abs(e - eMoy) <= eMin) {
						iMin = i;
						eMin = Math.abs(e - eMoy);
					}
				}
			}
			
			return iMin;
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
		
		float esperanceMainJoueurIA() {
			float retour = 0;
			for(int i=0;i<52;i++) {
				if((mainJ & (1L << i))!=0) {
					retour += info.esperanceMoy(i, atout,estIA?(joueur+1)%2:(joueur), mainJ);
				}
			}
			return retour;
		}
		
		//FONCTIONNE UNIQUEMENT SI LE JOUEUR EST L'IA
		float esperanceMainAdverseIA() {
			float retour = 0;
			int numJoueurAdv = estIA?(joueur+1)%2:(joueur);
			long cartesCon = info.cartesMain(numJoueurAdv);
			long cartesInc = info.cartesInconnuesJ(mainJ, numJoueurAdv);
			
			if(CartesMIA.nbCartes(cartesInc)!=0) {
				int tmp = 0;
				for(int i=0;i<52;i++) {
					if((cartesInc & (1L << i))!=0) {
						retour += info.esperanceMoy(i, atout, (numJoueurAdv+1)%2, 0);
						tmp++;
					}
				}
				retour = retour * (11f-CartesMIA.nbCartes(cartesCon)) / CartesMIA.nbCartes(cartesInc);
				if(tmp != CartesMIA.nbCartes(cartesInc)) {
					System.out.println("Hum");
				}
			}
			for(int i=0;i<52;i++) {
				if((cartesCon & (1L << i))!=0) {
					retour += info.esperanceMoy(i, atout, (numJoueurAdv+1)%2, 0);
				}
			}
			return retour;
		}
		
		private float heuristiqueMain() {
			float heMain = 0;
			float heCarte = 0;
			
			heMain += esperanceMainJoueurIA();
			heMain -= esperanceMainAdverseIA();

			if(!estIA) {
				heMain*=-1;
			}
			if(phase==1) {
				heCarte -= info.esperanceMoy(carte, atout, joueur, mainJ);
			}
			return heMain + heCarte;
		}
		private float heuristiquePlis() {
			float hePlis = 0;
			//cartes dans les plis
			hePlis += CartesMIA.nbCartes(info.cartesPlis(joueur)) / 2f;
			hePlis -= CartesMIA.nbCartes(info.cartesPlis((joueur+1)%2)) / 2f;
			return hePlis;
		}
		float heuristiquePiocheVide() {
			float hePlis = heuristiquePlis();
			float heMain = 0f;
			if(!estFinal()) {
				heMain = heuristiqueMain();
			}
			return heMain + hePlis;
		}
		float heuristiquePartieEnCour1() {
			float hePlis = heuristiquePlis();
			float heMain = heuristiqueMain();
			return heMain + hePlis;
		}
		float heuristiquePartieEnCour2() {
			float hePlis = heuristiquePlis() / ((float)nbCartesInconnue());
			float heMain = heuristiqueMain();
			return heMain + hePlis;
		}
		float heuristiquePartieEnCour3() {
			float hePlis = 0;//heuristiquePlis();
			float heMain = heuristiqueMain();
			return heMain + hePlis;
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
			return 0;//(int) ((Float)(heuristique()*10)).compareTo(c.heuristique()*10);
		}
}
