package Controleur.IA;


class TestsIA {
	
	static private int c(String s) {
		int retour = 0;
		switch(s.charAt(0)) {
		case '2': retour += 0; break;
		case '3': retour += 1; break;
		case '4': retour += 2; break;
		case '5': retour += 3; break;
		case '6': retour += 4; break;
		case '7': retour += 5; break;
		case '8': retour += 6; break;
		case '9': retour += 7; break;
		case '0': retour += 8; break;
		case 'V': retour += 9; break;
		case 'D': retour += 10; break;
		case 'R': retour += 11; break;
		case 'A': retour += 12; break;
		}
		switch(s.charAt(1)) {
		case 'C' : retour += 0; break;
		case 'D' : retour += 13; break;
		case 'H' : retour += 26; break;
		case 'S' : retour += 39; break;
		}
		return retour;
	}
	
	static public String iToS(int c) {
		String s = "";
		
		switch(2 + c%13) {
		case 10 : s+="0"; break;
		case 11 : s+="V"; break;
		case 12 : s+="D"; break;
		case 13 : s+="R"; break;
		case 14 : s+="A"; break;
		default :
			s += (2 + c%13);
		}
		switch(c/13) {
		case 0 : s+="C"; break;
		case 1 : s+="D"; break;
		case 2 : s+="H"; break;
		case 3 : s+="S"; break;
		}
		return s;
	}
	
	static ConfigurationIA configFin(int atout, int[] cartesJ1,int[] connuesJ2) {
		ConfigurationIA config = null;
		long main = 0;
		
		int[] connuesJ1 = cartesJ1.clone();

		for(int i=0;i<cartesJ1.length;i++) {
			main |= 1L << cartesJ1[i];
		}
		
		config = new ConfigurationIA(main,0,0,-1,atout,true);
		
		for(int i=0;i<52;i++) {
			config.addPli(0, i);
			config.addPli(1, i);
		}
		
		for(int i=0;i<cartesJ1.length;i++) {
			main |= 1L << cartesJ1[i];
		}
		
		for(int i=0;i<connuesJ1.length;i++) {
			config.addMain(0,connuesJ1[i]);
			config.delPli(0, connuesJ1[i]);
			config.delPli(1, connuesJ1[i]);
		}
		
		for(int i=0;i<connuesJ2.length;i++) {
			config.addMain(1,connuesJ2[i]);
			config.delPli(0, connuesJ2[i]);
			config.delPli(1, connuesJ2[i]);
		}
		
		return config;
	}
	static ConfigurationIA configFin(int atout, int[] cartesJ1,int[] connuesJ2, int joueur, int carte, int phase) {
		ConfigurationIA config = null;
		long main = 0;
		
		int[] connuesJ1 = cartesJ1.clone();

		for(int i=0;i<cartesJ1.length;i++) {
			main |= 1L << cartesJ1[i];
		}
		
		config = new ConfigurationIA(main,joueur,phase,carte,atout,true);
		
		for(int i=0;i<52;i++) {
			config.addPli(0, i);
			config.addPli(1, i);
		}
		
		for(int i=0;i<cartesJ1.length;i++) {
			main |= 1L << cartesJ1[i];
		}
		
		for(int i=0;i<connuesJ1.length;i++) {
			config.addMain(0,connuesJ1[i]);
			config.delPli(0, connuesJ1[i]);
			config.delPli(1, connuesJ1[i]);
		}
		
		for(int i=0;i<connuesJ2.length;i++) {
			config.addMain(1,connuesJ2[i]);
			config.delPli(0, connuesJ2[i]);
			config.delPli(1, connuesJ2[i]);
		}
		
		return config;
	}
	
	
	static void afficherCartes(long m) {
		for(int i=0;i<52;i++) {
			if((m & (1L << i)) != 0) {
				System.out.println("valeur " + (2+i%13) + " couleur "+i/13);
			}
		}
	}
	
	/*********************
	 * TESTS PIOCHE VIDE *
	 *********************/
	
	static ConfigurationIA config1() {
		/* Attendu : As Diamond (25)
		 * Resultat : OK
		 */
		int atout = 1;
		int[] cartesJ1 = {c("AD"),c("AC")};
		int[] connuesJ2 = {c("AH"), c("RD")};
		return configFin(atout,cartesJ1,connuesJ2);
	}
	static ConfigurationIA config1_1() {
		/* Attendu : As Diamond 25
		 * Resultat : OK
		 */
		int atout = 1;
		int[] cartesJ1 = {c("AD"),c("2C")};
		int[] connuesJ2 = {c("RD"),c("2H")};
		return configFin(atout,cartesJ1,connuesJ2);
	}
	static ConfigurationIA config1_2() {
		/* Attendu : As Diamond 25
		 * Resultat : OK
		 */
		int atout = 1;
		int[] cartesJ1 = {c("AD"),c("3C"),c("2C")};
		int[] connuesJ2 = {c("RD"),c("3H"),c("2H")};
		return configFin(atout,cartesJ1,connuesJ2);
	}
	static ConfigurationIA config1_3() {
		/* Attendu : As Diamond 25
		 * Resultat : OK toutes profondeurs
		 */
		int atout = 1;
		int[] cartesJ1 = {c("AD"),c("2C"),c("3C"),c("4C"),c("5C")};
		int[] connuesJ2 = {c("RD"),c("2H"),c("3H"),c("4H"),c("5H")};
		return configFin(atout,cartesJ1,connuesJ2);
	}
	static ConfigurationIA config2() {
		/* Attendu : ?
		 * Resultat : 2C si p<6 10D si 5<p<11 limite vers 10 (ordre voisins aleatoire)
		 * 13H avec tri (tres) naif
		 * (mains choisies au hazard)
		 */
		int atout = 2;
		int[] cartesJ1  = {c("6S"),c("DC"),c("5S"),c("AS"),c("3C"),c("0D"),c("2D"),c("AD"),c("AH"),c("RH"),c("4C")};
		int[] connuesJ2 = {c("5C"),c("7D"),c("7C"),c("7H"),c("4D"),c("DS"),c("RC"),c("6C"),c("RS"),c("8S"),c("VH")};
		return configFin(atout,cartesJ1,connuesJ2);
	}
	static ConfigurationIA config2_1() {
		/* Attendu : ?
		 * Resultat : idem, pas de limite pour p
		 * (mains choisies au hazard) limite de temp
		 */
		int atout = 2;
		int[] cartesJ1  = {c("6S"),c("DC"),c("5S"),c("AS"),c("3C"),c("0D"),c("2D"),c("AD")};
		int[] connuesJ2 = {c("5C"),c("7D"),c("7C"),c("7H"),c("4D"),c("DS"),c("RC"),c("6C")};
		return configFin(atout,cartesJ1,connuesJ2);
	}
	static ConfigurationIA config3() {
		/* Attendu : 8C ?
		 * Resultat : Non ? (toutes egales a -1)
		 * A verifier
		 */
		int atout = -1;
		int[] cartesJ1 = {c("RH"),c("2H"),c("8C"),c("3C"),c("7S"),c("5S"),c("9S")};
		int[] connuesJ2 = {c("AH"),c("3H"),c("VH"),c("4C"),c("8S"),c("6S"),c("0S")};
		return configFin(atout,cartesJ1,connuesJ2);
	}
	static ConfigurationIA config3_1() {
		/* Attendu : 8C (2-2)
		 * Resultat : fun fact : 3C marche aussi
		 * A verifier
		 */
		int atout = -1;
		int[] cartesJ1  = {c("RH"),c("2H"),c("8C"),c("3C")};
		int[] connuesJ2 = {c("AH"),c("3H"),c("VH"),c("4C")};
		return configFin(atout,cartesJ1,connuesJ2);
	}
	static ConfigurationIA config3_2() {
		/* Attendu : 8C
		 * Resultat : ok
		 * 
		 */
		int atout = -1;
		int[] cartesJ1  = {c("2H"),c("8C"),c("3C")};
		int[] connuesJ2 = {c("3H"),c("VH"),c("4C")};
		return configFin(atout,cartesJ1,connuesJ2);
	}
	static ConfigurationIA config3_3() {
		/* Attendu : 8C (2-1)
		 * Resultat : ok
		 * 
		 */
		int atout = -1;
		int[] cartesJ1  = {c("RH"),c("8C"),c("3C")};
		int[] connuesJ2 = {c("AH"),c("VH"),c("4C")};
		return configFin(atout,cartesJ1,connuesJ2);
	}
	
	static ConfigurationIA config4() {
		/* Attendu : 8C (2-1)
		 * Resultat : ok
		 * 
		 */
		int atout = 3;
		int[] cartesJ1  = {c("8C"),c("7H"),c("9H"),c("5C"),c("7C"),c("2S"),c("DS"),c("4S"),c("6H"),c("3S"),c("RS")};
		int[] connuesJ2 = {c("8D"),c("9S"),c("3D"),c("AS"),c("5S"),c("AC"),c("RC"),c("2H"),c("8S"),c("VS")};//c("VH")
		return configFin(atout,cartesJ1,connuesJ2,0,c("DD"),1);
	}
	
	static ConfigurationIA configTest1() {
		/* Attendu : 
		 * Resultat : 
		 * 11111
		 * 111111
		 */
		
		ConfigurationIA config = null;
		//debugAfficherCartes(trouverMain());
		
		long mainJoueur =1692709962121320L;
		long connueJ0	=1692708888379432L;
		long connueJ1	=391426207139840L;
		long plisJ0		=158574493271168L;
		long plisJ1		=9089142760215L;
		long nconnueJ0	=4503599560261632L;
		long nconnueJ1	=0;
		long pioche0	=0;
		long pioche1	=0;
		long pioche2	=0;
		long pioche3	=0;
		long pioche4	=0;
		
		config = new ConfigurationIA(mainJoueur,0,1,23,3,true);
		
		for(int i=0;i<52;i++) {
			if((nconnueJ0 & (1L << i))!=0) {
				config.delCouleur(0, i/13);
			}
			if((nconnueJ1 & (1L << i))!=0) {
				config.delCouleur(1, i/13);
			}
			if((connueJ0 & (1L << i))!=0) {
				config.addMain(0, i);
			}
			if((connueJ1 & (1L << i))!=0) {
				config.addMain(1, i);
			}
			if((plisJ0 & (1L << i))!=0) {
				config.addPli(0, i);
			}
			if((plisJ1 & (1L << i))!=0) {
				config.addPli(1, i);
			}
			if((pioche0 & (1L << i))!=0) {
				config.addPioche(0, i);
			}
			if((pioche1 & (1L << i))!=0) {
				config.addPioche(1, i);
			}
			if((pioche2 & (1L << i))!=0) {
				config.addPioche(2, i);
			}
			if((pioche3 & (1L << i))!=0) {
				config.addPioche(3, i);
			}
			if((pioche4 & (1L << i))!=0) {
				config.addPioche(4, i);
			}
		}
		System.out.println("Debut : "+config);
		return config;
	}
	
	
	
	/************************
	 * TESTS PIOCHE PARTIEL *
	 ************************/
	
	
	
}
