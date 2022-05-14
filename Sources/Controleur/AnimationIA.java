package Controleur;
public class AnimationIA extends Animation{
	
	ControleurMediateur controleur;
	IA joueur;
	
	AnimationIA(int l,IA j,ControleurMediateur c) {
		super(l);
		joueur = j;
		controleur = c;
	}
	
	
	@Override
	void miseAJour() {
		int aJouer = joueur.jouerCoup();
		if(aJouer==-1) {
			System.err.println("ERREUR : impossible de trouver un coup");
			return;//ERREUR
		}
		controleur.commande(aJouer);
	}
}