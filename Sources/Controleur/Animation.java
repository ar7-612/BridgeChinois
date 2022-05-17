package Controleur;

abstract class Animation {
	int nbFrame;
	int decompte;
	public Animation(int nbFrame){
		this.nbFrame=nbFrame;
		decompte=nbFrame;
	}
	
	abstract void miseAJour();
	
	public void tictac() {
		if(decompte==0) {
			decompte=nbFrame;
			miseAJour();
		} else {
			decompte--;
		}
	}
	
	public boolean estFini() {
		return false;
	}
}