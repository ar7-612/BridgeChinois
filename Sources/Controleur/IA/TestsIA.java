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
	
	static ConfigurationIA config1() {
		/* Attendu : Doit choisir l'As de carreau en 1er
		 * Resultat : OK
		 */
		ConfigurationIA config = null;
		long main = 0;
		int id1 = 12;//As trefle
		int id2 = 25;//As carreau
		int id3 = 38;//As coeur
		int id4 = 24;//Roi carreau
		main |= 1L << id1;
		main |= 1L << id2;
		config = new ConfigurationIA(main,0,0,-1,1);
		
		for(int i=0;i<52;i++) {
			config.addPli(0, i);
			config.addPli(1, i);
		}
		
		config.addMain(0,id1); config.delPli(0, id1);
		config.addMain(0,id2); config.delPli(0, id2);
		
		config.addMain(1,id3); config.delPli(1, id3);
		config.addMain(1,id4); config.delPli(1, id4);
		
		return config;
	}
	
	static ConfigurationIA config2() {
		/* Attendu : 
		 * Resultat : 
		 */
		ConfigurationIA config = null;
		long main = 0;
		
		int atout = 1;
		int[] cartesJ1 = {c("AD"),c("AC")};
		int[] connuesJ1 = {c("AD"),c("AC")};
		int[] connuesJ2 = {c("AH"), c("RD")};

		for(int i=0;i<cartesJ1.length;i++) {
			main |= 1L << cartesJ1[i];
		}
		
		config = new ConfigurationIA(main,0,0,-1,atout);
		
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
		}
		
		for(int i=0;i<connuesJ2.length;i++) {
			config.addMain(1,connuesJ2[i]);
			config.delPli(1, connuesJ2[i]);
		}
		
		return config;
	}
	
}
