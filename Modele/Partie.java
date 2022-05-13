package Modele;

import java.util.ArrayList;

import Patterns.Observable;

public class Partie extends Observable {
    ArrayList<Manche> Manches;
    ArrayList<Carte> cartepiochevisible;
    Manche manchecourante;
    Joueur j1, j2;
    String Modedejeu;
    boolean manchefin;
    public boolean debutpartie, finpartie;
    int phase, phasetour, nbmanche, nMax, score1, score2;

    public Partie() {
        j1 = new Joueur();
        j2 = new Joueur();
        manchecourante = new Manche(j1, j2);
        phase = 4;
        phasetour = 0;
        nbmanche = 1;
        score1 = 0;
        score2 = 0;
        debutpartie = true;
        finpartie = false;
        Manches = new ArrayList<Manche>();
        Manches.add(manchecourante);
    }

    public int atout() {
        return manchecourante.atout;
    }

    public Boolean JouableSec(int arg) {
        if (manchecourante.donneur == 1) {
            if (manchecourante.j2.jouables(manchecourante.cartePremier.couleur))
                return manchecourante.j2.main.carte(arg).couleur == manchecourante.cartePremier.couleur;
            else
                return true;
        } else {
            if (manchecourante.j1.jouables(manchecourante.cartePremier.couleur))
                return manchecourante.j1.main.carte(arg).couleur == manchecourante.cartePremier.couleur;
            else
                return true;
        }
    }

    public int scoremanchej1() {
        return manchecourante.j1.scoreManche;
    }

    public int scoremanchej2() {
        return manchecourante.j2.scoreManche;
    }

    public int nbmanche() {
        return nbmanche;
    }

    public void jouerCoup(Coup cp) {
        debutpartie = false;
        testFinPartie();
        if (!finpartie) {
            if (manchefini()) {
                testFinPartie();
                nouvellemanche();
                miseAJour();
            } else {
                switch (cp.codecoup) {
                    case Coup.Premierdepos:
                        manchecourante.jouerCoupPremier(cp.argument);
                        phasetour = 1;
                        testFinPartie();
                        miseAJour();
                        break;
                    case Coup.PremierPioche:
                        manchecourante.piocheGagnant(cp.argument);
                        phasetour = 3;
                        testFinPartie();
                        miseAJour();
                        break;
                    case Coup.Seconddepos:
                        manchecourante.jouerCoupSec(cp.argument);
                        phasetour = 2;
                        manchecourante.gagnantPli();
                        if (manchefini()) {
                            nbmanche++;
                        }
                        testFinPartie();
                        miseAJour();
                        break;
                    case Coup.SecondPioche:
                        manchecourante.piochePerdant(cp.argument);
                        phasetour = 0;
                        if (manchecourante.toutelespilesontvide()) {
                            phase = phase / 2;
                        }
                        testFinPartie();
                        miseAJour();
                        break;

                }
            }
        }
    }

    private void nouvellemanche() {
        manchecourante = new Manche(j1, j2);
        Manches.add(manchecourante);
        phasetour = 0;
        phase = 4;
    }

    public Carte[] cartevisiblepioche() {
        return manchecourante.CartevisiblePile();
    }

    public Joueur joueurDonneur() {
        if (manchecourante.donneur == 1)
            return manchecourante.j1;
        else
            return manchecourante.j2;
    }

    public Joueur joueurReceveur() {
        if (manchecourante.receveur == 1)
            return manchecourante.j1;
        else
            return manchecourante.j2;
    }

    public void testFinPartie() {
        if (nbmanche == nMax + 1 && Modedejeu.equals("m")) {
            finpartie = true;
        } else if ((manchecourante.j1.scorePartie == nMax || manchecourante.j2.scorePartie == nMax)
                && Modedejeu.equals("p")) {
            finpartie = true;
        } else {
            finpartie = false;
        }

    }

    public Coup determinerCoup(int codecoup, int arg) {
        Coup resultat = new Coup(codecoup, arg);
        return resultat;
    }

    public int phasetour() {
        return phasetour;
    }

    public int phase() {
        return phase;
    }

    public int quiDonne() {
        return manchecourante.donneur;
    }

    public int quiRecois() {
        return manchecourante.receveur;
    }

    public Boolean pilevide(int arg) {
        return manchecourante.pilevide(arg);
    }

    public boolean manchefini() {
        return manchecourante.Manchefini();
    }

    public boolean tourfini() {
        return phasetour % phase == 0;
    }

    public String cartePremCouleur() {
        String s = null;
        switch (manchecourante.cartePremier.couleur()) {
            case 1:
                s = "TREFLE";
                return s;
            case 2:
                s = "CARREAU";
                return s;
            case 3:
                s = "COEUR";
                return s;
            case 4:
                s = "PIQUE";
                return s;
            default:
                return s;
        }
    }

    public Carte cartePrem() {
        return manchecourante.cartePremier;
    }

    public Carte carteSec() {
        return manchecourante.carteSeconde;
    }

    public void ModeV(String mode, int nombre) {
        Modedejeu = mode;
        nMax = nombre;
        testFinPartie();
    }

    public boolean partifini() {
        return finpartie;
    }

    public int scorePartiej1() {
        return manchecourante.j1.scorePartie;
    }

    public int scorePartiej2() {
        return manchecourante.j2.scorePartie;
    }

    public int quiGagnePartie() {
        if (Modedejeu.equals("m")) {
            if (manchecourante.j1.manchesGagnees > manchecourante.j2.manchesGagnees)
                return 1;
            else {
                return 2;
            }
        } else {
            if (manchecourante.j1.scorePartie > manchecourante.j2.scorePartie)
                return 1;
            else {
                return 2;
            }
        }
    }

    public int quiGagneManche() {
        if (manchecourante.j1.scoreManche > manchecourante.j2.scoreManche)
            return 1;
        else {
            return 2;
        }
    }
}
