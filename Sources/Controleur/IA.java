package Controleur
abstract class IA {
	Manche partie;
	
	abstract IA creerIA (Manche m, String mode){
		partie = m;
		switch(mode){
			case "Aleatoire":
				return new IAAleatoire();
			default :
				System.err.println("IA \"",mode,"\" inconnue");
				//ERREUR
		}
		return null;
	}
	
	Coup jouerCoup(){
		return null;
	}
}