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
		if (arg == -1) {
			Coup cp = jeu.determinerCoup(jeu.phasetourterm(), arg);
			annulleCoup(cp);
			return 0;
		} else if (arg == -2) {
			Coup cp = jeu.determinerCoup(jeu.phasetourterm(), arg);
			refaireCoup(cp);
			return 0;
		} else if (jeu.manchefini()) {
			if (arg == 1) {
				faitcoup(jeu.phasetourterm(), arg);
				return 0;
			} else if (arg == 2) {
				return 0;
			} else {
				return CodeErrCommandeInconnu;
			}

		} else if (jeu.phasetourterm() == 0) {
			if (arg >= 0 && arg < jeu.joueurDonneur().main().taille()) {
				faitcoup(jeu.phasetour() % jeu.phase(), arg);
				return 0;
			} else {
				return CodeErrMauvaiseChoix;
			}
		} else if (jeu.phasetourterm() >= 2) {
			if (!jeu.pilevide(arg)) {
				faitcoup(jeu.phasetourterm(), arg);
				return 0;
			} else {
				return CodeErrMauvaisePioche;
			}
		} else if (jeu.phasetourterm() == 1) {
			if (arg >= 0 && arg < jeu.joueurReceveur().main().taille()) {
				if (jeu.JouableSec(arg)) {
					faitcoup(jeu.phasetourterm(), arg);
					return 0;
				} else
					return CodeErrMauvaiseChoixSec;
			} else
				return CodeErrMauvaiseChoix;
		} else
			return CodeErrCommandeInconnu;
	}

	private void refaireCoup(Coup cp) {
		jeu.refaireCoup(cp);
	}

	private void annulleCoup(Coup cp) {
		jeu.annulleCoup(cp);
	}
}
