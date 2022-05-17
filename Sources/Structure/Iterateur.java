package Structure;

public abstract class Iterateur<Tata> {
	boolean peutSupprimer;

	public abstract boolean aProchain();

	public Tata prochain() {
		peutSupprimer = true;
		return null;
	}

	public void supprime() {
		if (!peutSupprimer) {
			throw new IllegalStateException("Deux suppressions d'affilée");
		}
		peutSupprimer = false;
	}
}
