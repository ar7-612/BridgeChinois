package Vue;

public interface CollecteurEvenements {
	static final int CodeErrMauvaisePioche = 1;
	static final int CodeErrMauvaiseChoix = 2;
	static final int CodeErrCommandeInconnu = 3;
	static final int CodeErrMauvaiseChoixSec = 4;

	int commande(int c);

	void fixerInterfaceUtilisateur(InterfaceUtilisateur i);
}
