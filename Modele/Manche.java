package Modele;

public class Manche {
    PileCartes pile1, pile2, pile3, pile4, pile5, pile6;
    Joueur j1, j2;
    Carte cartePremier, carteSeconde;
    int atout, gagnant, donneur, perdant, receveur, joueurGagnantPli, nbpilereste;

    Manche(Joueur joueur1, Joueur joueur2) {
        j1 = joueur1;
        j2 = joueur2;
        initManche();
    }

    public void initManche() {
        j1.main = new Main();
        j2.main = new Main();
        j1.Pli = new PileCartes();
        j2.Pli = new PileCartes();
        pile1 = new PileCartes();
        pile2 = new PileCartes();
        pile3 = new PileCartes();
        pile4 = new PileCartes();
        pile5 = new PileCartes();
        pile6 = new PileCartes();
        j1.scoreManche = 0;
        j2.scoreManche = 0;
        nbpilereste = 6;
        donneur = 1;
        receveur = 2;
        gagnant = 0;
        perdant = 0;
        distribuer();
        DefAtout();
    }

    public Boolean Manchefini() {
        if (j1.scoreManche > j2.scoreManche) {
            j1.manchesGagnees++;
        } else {
            j2.manchesGagnees++;
        }
        return j1.main.taille == 0 && j2.main.taille == 0;
    }

    public void cartePrems(Carte c) {
        cartePremier = c;
    }

    public void carteSec(Carte c) {
        carteSeconde = c;
    }

    public void DefAtout() {
        Carte carteatout;
        if (pile1.premiere().valeur >= 10 || pile2.premiere().valeur >= 10 || pile3.premiere().valeur >= 10
                || pile4.premiere().valeur >= 10 || pile5.premiere().valeur >= 10 || pile6.premiere().valeur >= 10) {
            carteatout = pile1.premiere();
            if (pile2.premiere().valeur > carteatout.valeur)
                carteatout = pile2.premiere();
            else if (pile2.premiere().valeur == carteatout.valeur && pile2.premiere().couleur > carteatout.couleur)
                carteatout = pile2.premiere();

            if (pile3.premiere().valeur > carteatout.valeur)
                carteatout = pile3.premiere();
            else if (pile3.premiere().valeur == carteatout.valeur && pile2.premiere().couleur > carteatout.couleur)
                carteatout = pile3.premiere();

            if (pile4.premiere().valeur > carteatout.valeur)
                carteatout = pile4.premiere();
            else if (pile4.premiere().valeur == carteatout.valeur && pile2.premiere().couleur > carteatout.couleur)
                carteatout = pile4.premiere();

            if (pile5.premiere().valeur > carteatout.valeur)
                carteatout = pile5.premiere();
            else if (pile5.premiere().valeur == carteatout.valeur && pile2.premiere().couleur > carteatout.couleur)
                carteatout = pile5.premiere();

            if (pile6.premiere().valeur > carteatout.valeur)
                carteatout = pile6.premiere();
            else if (pile6.premiere().valeur == carteatout.valeur && pile2.premiere().couleur > carteatout.couleur)
                carteatout = pile6.premiere();
            atout = carteatout.couleur;
        } else
            atout = 0;
    }

    public void ajoutepli() {
        if (gagnant == 1) {
            j1.Pli.ajouter(cartePremier);
            j1.Pli.ajouter(carteSeconde);
            j1.ajouterScore();
        } else {
            j2.Pli.ajouter(cartePremier);
            j2.Pli.ajouter(carteSeconde);
            j2.ajouterScore();
        }
    }

    public void gagnantPli() {
        if (cartePremier.couleur == carteSeconde.couleur) {
            if (cartePremier.valeur > carteSeconde.valeur) {
                gagnant = donneur;
                perdant = receveur;
            } else {
                gagnant = receveur;
                perdant = donneur;
            }
        } else if (carteSeconde.couleur == atout) {
            gagnant = receveur;
            perdant = donneur;
        } else {
            gagnant = donneur;
            perdant = receveur;
        }
        donneur = gagnant;
        receveur = perdant;
        ajoutepli();
    }

    public void distribuer() {
        PileCartes paquet = new PileCartes();
        paquet.paquet();
        for (int i = 0; i < 11; i++) {
            j1.main.ajouter(paquet.aleatoire(false));
        }
        for (int i = 0; i < 11; i++) {
            j2.main.ajouter(paquet.aleatoire(false));
        }
        for (int i = 0; i < 5; i++) {
            pile1.ajouter(paquet.aleatoire(true));
        }
        for (int i = 0; i < 5; i++) {
            pile2.ajouter(paquet.aleatoire(true));
        }
        for (int i = 0; i < 5; i++) {
            pile3.ajouter(paquet.aleatoire(true));
        }
        for (int i = 0; i < 5; i++) {
            pile4.ajouter(paquet.aleatoire(true));
        }
        for (int i = 0; i < 5; i++) {
            pile5.ajouter(paquet.aleatoire(true));
        }
        for (int i = 0; i < 5; i++) {
            pile6.ajouter(paquet.aleatoire(true));
        }
    }

    public void jouerCoupPremier(int argument) { // Carte card en +
        Carte card;
        if (donneur == 1) {
            card = j1.main.carte(argument);
            j1.main.retirer(argument);
        } else {
            card = j2.main.carte(argument);
            j2.main.retirer(argument);
        }
        cartePrems(card);

        // j1.main.trier();
        // j2.main.trier();
    }

    public void jouerCoupSec(int argument) { // Carte card en +
        Carte card;
        if (receveur == 1) {
            card = j1.main.carte(argument);
            j1.main.retirer(argument);
        } else {
            card = j2.main.carte(argument);
            j2.main.retirer(argument);
        }
        carteSec(card);

        // j1.main.trier();
        // j2.main.trier();
    }

    public void piocheGagnant(int numPioche) {
        Carte c;
        switch (numPioche) {
            case 1:
                c = pile1.retirer();
                if (gagnant == 1) {
                    j1.main.ajouter(c);
                } else {
                    j2.main.ajouter(c);
                }
                break;
            case 2:
                c = pile2.retirer();
                if (gagnant == 1) {
                    j1.main.ajouter(c);
                } else {
                    j2.main.ajouter(c);
                }
                break;
            case 3:
                c = pile3.retirer();
                if (gagnant == 1) {
                    j1.main.ajouter(c);
                } else {
                    j2.main.ajouter(c);
                }
                break;
            case 4:
                c = pile4.retirer();
                if (gagnant == 1) {
                    j1.main.ajouter(c);
                } else {
                    j2.main.ajouter(c);
                }
                break;
            case 5:
                c = pile5.retirer();
                if (gagnant == 1) {
                    j1.main.ajouter(c);
                } else {
                    j2.main.ajouter(c);
                }
                break;
            case 6:
                c = pile6.retirer();
                if (gagnant == 1) {
                    j1.main.ajouter(c);
                } else {
                    j2.main.ajouter(c);
                }
                break;
        }
    }

    public void piochePerdant(int numPioche) {
        Carte c;
        switch (numPioche) {
            case 1:
                c = pile1.retirer();
                if (perdant == 1) {
                    j1.main.ajouter(c);
                } else {
                    j2.main.ajouter(c);
                }
                break;
            case 2:
                c = pile2.retirer();
                if (perdant == 1) {
                    j1.main.ajouter(c);
                } else {
                    j2.main.ajouter(c);
                }
                break;
            case 3:
                c = pile3.retirer();
                if (perdant == 1) {
                    j1.main.ajouter(c);
                } else {
                    j2.main.ajouter(c);
                }
                break;
            case 4:
                c = pile4.retirer();
                if (perdant == 1) {
                    j1.main.ajouter(c);
                } else {
                    j2.main.ajouter(c);
                }
                break;
            case 5:
                c = pile5.retirer();
                if (perdant == 1) {
                    j1.main.ajouter(c);
                } else {
                    j2.main.ajouter(c);
                }
                break;
            case 6:
                c = pile6.retirer();
                if (perdant == 1) {
                    j1.main.ajouter(c);
                } else {
                    j2.main.ajouter(c);
                }
                break;
        }
    }

    public Boolean toutelespilesontvide() {
        return pile1.vide() && pile2.vide() && pile3.vide() && pile4.vide() && pile5.vide() && pile6.vide();
    }

    public Carte[] CartevisiblePile() {
        Carte[] cartespiles = new Carte[6];
        nbpilereste = 0;
        if (!pile1.vide()) {
            cartespiles[0] = (pile1.premiere());
            nbpilereste++;
        }
        if (!pile2.vide()) {
            cartespiles[1] = (pile2.premiere());
            nbpilereste++;
        }
        if (!pile3.vide()) {
            cartespiles[2] = (pile3.premiere());
            nbpilereste++;
        }
        if (!pile4.vide()) {
            cartespiles[3] = (pile4.premiere());
            nbpilereste++;
        }
        if (!pile5.vide()) {
            cartespiles[4] = (pile5.premiere());
            nbpilereste++;
        }
        if (!pile6.vide()) {
            cartespiles[5] = (pile6.premiere());
            nbpilereste++;
        }
        return cartespiles;
    }

    public Boolean pilevide(int arg) {
        switch (arg) {
            case 1:
                return pile1.vide();
            case 2:
                return pile2.vide();
            case 3:
                return pile3.vide();
            case 4:
                return pile4.vide();
            case 5:
                return pile5.vide();
            case 6:
                return pile6.vide();
            default:
                return true;
        }
    }

}
