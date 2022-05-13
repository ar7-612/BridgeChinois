package Controleur;

import Modele.Coup;
import Modele.Partie;
import Vue.CollecteurEvenements;
import Vue.InterfaceUtilisateur;

public class ControleurMediateur implements CollecteurEvenements {
	Partie jeu;
	InterfaceUtilisateur inter;

	public ControleurMediateur(Partie j) {
		jeu = j;
	}

	@Override
	public void fixerInterfaceUtilisateur(InterfaceUtilisateur i) {
		inter = i;
	}

	void jouerCoup(Coup cp) {
		jeu.jouerCoup(cp);
	}

	void faitcoup(int codecoup, int arg) {
		Coup cp = jeu.determinerCoup(codecoup, arg);
		if (cp != null) {
			jouerCoup(cp);
		}
	}

	@Override
	public int commande(int arg) {
		if (jeu.manchefini()) {
			if (arg == 1) {
				faitcoup(jeu.phasetour() % jeu.phase(), arg);
				return 0;
			} else if (arg == 2) {
				return 0;
			} else {
				return CodeErrCommandeInconnu;
			}

		} else if (jeu.phasetour() % jeu.phase() == 0) {
			if (arg >= 0 && arg < 11) {
				faitcoup(jeu.phasetour() % jeu.phase(), arg);
				return 0;
			} else {
				return CodeErrMauvaiseChoix;
			}
		} else if (jeu.phasetour() % jeu.phase() >= 2) {
			if (!jeu.pilevide(arg)) {
				faitcoup(jeu.phasetour() % jeu.phase(), arg);
				return 0;
			} else {
				return CodeErrMauvaisePioche;
			}
		} else if (jeu.phasetour() % jeu.phase() == 1) {
			if (arg >= 0 && arg < 11) {
				if (jeu.JouableSec(arg)) {
					faitcoup(jeu.phasetour() % jeu.phase(), arg);
					return 0;
				} else
					return CodeErrMauvaiseChoixSec;
			} else
				return CodeErrMauvaiseChoix;
		} else
			return CodeErrCommandeInconnu;
	}
}