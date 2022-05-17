package Modele;

//import java.util.concurrent.ExecutionException;

public class Coup extends Commande {
    int codecoup, argument;
    Partie partie;
  
    static final int Premierdepos = 0;
    static final int Seconddepos = 1;
    static final int PremierPioche = 2;
    static final int SecondPioche = 3;

    Coup(int cdcoup, int arg) {
        codecoup = cdcoup;
        argument = arg;
    }
    
    public int getCode() {
    	return codecoup;
    }
    
    public int getArg() {
    	return argument;
    }

    public void fixerpartie(Partie p) {
        partie = p;
    }

    public void Execute() {
        partie.debutpartie = false;
        partie.testFinPartie();
        if (!partie.finpartie) {
            if (partie.manchefini()) {
                partie.testFinPartie();
                partie.nouvellemanche();
                partie.miseAJour();
            } else {
                switch (codecoup) {
                    case Coup.Premierdepos:
                        partie.manchecourante.jouerCoupPremier(argument);
                        partie.phasetour = 1;
                        partie.testFinPartie();
                        break;
                    case Coup.PremierPioche:
                        partie.manchecourante.piocheGagnant(argument);
                        partie.phasetour = 3;
                        partie.testFinPartie();
                        break;
                    case Coup.Seconddepos:
                        partie.manchecourante.jouerCoupSec(argument);
                        partie.phasetour = 2;
                        partie.manchecourante.gagnantPli();
                        if (partie.manchefini()) {
                            partie.nbmanche++;
                        }
                        partie.testFinPartie();
                        break;
                    case Coup.SecondPioche:
                        partie.manchecourante.piochePerdant(argument);
                        partie.phasetour = 0;
                        if (partie.manchecourante.toutelespilesontvide()) {
                            partie.phase = partie.phase / 2;
                        }
                        partie.testFinPartie();
                        break;

                }
            }
        }
    }

    public void DesExecute() {
        partie.testFinPartie();
        if (!partie.finpartie) {
            if (partie.manchefini()) {
                partie.testFinPartie();
                partie.nouvellemanche();
                partie.miseAJour();
            } else {
                switch (codecoup) {
                    case Coup.Premierdepos:
                        partie.manchecourante.annulleCoupPremier(argument);
                        partie.phasetour = 0;
                        if (partie.manchecourante.toutelespilesontvide()) {
                            partie.phase = partie.phase / 2;
                        }
                        partie.testFinPartie();
                        break;
                    case Coup.PremierPioche:
                        partie.manchecourante.annullepiocheGagnant(argument);
                        partie.phasetour = 2;
                        partie.testFinPartie();
                        break;
                    case Coup.Seconddepos:
                        partie.manchecourante.annullegagnantPli();
                        partie.manchecourante.annullejouerCoupSec(argument);
                        partie.phasetour = 1;
                        partie.testFinPartie();
                        break;
                    case Coup.SecondPioche:
                        partie.manchecourante.annullepiochePerdant(argument);
                        partie.phasetour = 3;
                        partie.testFinPartie();
                        break;

                }
            }
        }

    }
}
