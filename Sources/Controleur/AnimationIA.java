package Controleur
public class AnimationIA {
	
	ControleurMediateur controleur;
	IA joueur;
	
	AnimationIA(int l,IA j,ControleurMediateur c) {
		joueur = j;
		controleur = c;
		super(l);
	}
	
	
	Coup aJouer;
	@Override
	public void miseAJour() {
		if(aJouer==null){
			aJouer = joueur.jouerCoup();
			if(aJouer==null){
				//ERREUR
			}
		}
		controleur.jouerCoup(aJouer);
		aJouer = null;
	}
}