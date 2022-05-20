package Controleur.IA;

public class CartesMIA {
	public static final long TREFLE = 0b1111111111111;
	public static final long CARREAU = 0b1111111111111 << 13L;
	public static final long COEUR = 0b1111111111111 << 26L;
	public static final long PIQUE = 0b1111111111111 << 39L;
	public static final long ALL = ( ~0L << 12L ) >>> 12L;
	
	public static int nbCartes(long ensemble){
		/*int s=0;
		for(int i=0;i<52;i++){
			s += (ensemble & (1L << i)) >>> i;
		}
		return s;*/
		return Long.bitCount(ensemble);
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
}