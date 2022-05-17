package Modele;


//import java.util.ArrayList;

public class Manche {
    PileCartes pile1, pile2, pile3, pile4, pile5, pile6;
    Joueur j1, j2;
    int atout, gagnant, donneur, perdant, receveur, joueurGagnantPli, nbpilereste, nbtour;
    Tour[] tours;

    Manche(Joueur joueur1, Joueur joueur2) {
        j1 = joueur1;
        j2 = joueur2;

        tours = new Tour[26];

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
        nbtour = 0;

        donneur = 1;
        receveur = 2;
        gagnant = 0;
        perdant = 0;
        distribuer();
        DefAtout();
        nouvtour();

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

        tours[nbtour].fixcartePremier(c);
    }

    public void carteSec(Carte c) {
        tours[nbtour].fixcarteSec(c);

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

            j1.Pli.ajouter(tours[nbtour].cartePremier);
            j1.Pli.ajouter(tours[nbtour].carteSeconde);
            j1.ajouterScore();
        } else {
            j2.Pli.ajouter(tours[nbtour].cartePremier);
            j2.Pli.ajouter(tours[nbtour].carteSeconde);

            j2.ajouterScore();
        }
    }

    public void gagnantPli() {

        if (tours[nbtour].cartePremier.couleur == tours[nbtour].carteSeconde.couleur) {
            if (tours[nbtour].cartePremier.valeur > tours[nbtour].carteSeconde.valeur) {
                gagnant = tours[nbtour].donneur;
                perdant = tours[nbtour].receveur;
            } else {
                gagnant = tours[nbtour].receveur;
                perdant = tours[nbtour].donneur;
            }
        } else if (tours[nbtour].carteSeconde.couleur == atout) {
            gagnant = tours[nbtour].receveur;
            perdant = tours[nbtour].donneur;
        } else {
            gagnant = tours[nbtour].donneur;
            perdant = tours[nbtour].receveur;
        }
        tours[nbtour].fixgagnant(gagnant);

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

        tours[nbtour].fixcoupMainpremier(argument);
        if (tours[nbtour].donneur == 1) {

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

        tours[nbtour].fixcoupMainSec(argument);
        if (tours[nbtour].receveur == 1) {

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


    public void nouvtour() {
        tours[nbtour] = new Tour();
        tours[nbtour].fixdonneur(donneur);
    }

    public void piocheGagnant(int numPioche) {
        Carte c;
        tours[nbtour].fixpiochePremier(numPioche);
        switch (numPioche) {
            case 1:
                c = pile1.retirer();
                if (tours[nbtour].gagnant == 1) {
                    if (tours[nbtour].donneur == tours[nbtour].gagnant)
                        j1.main.ajouterpos(c, tours[nbtour].coupmainPremier);
                    else
                        j1.main.ajouterpos(c, tours[nbtour].coupmainSec);
                } else {
                    if (tours[nbtour].donneur == tours[nbtour].gagnant)
                        j2.main.ajouterpos(c, tours[nbtour].coupmainPremier);
                    else
                        j2.main.ajouterpos(c, tours[nbtour].coupmainSec);

                }
                break;
            case 2:
                c = pile2.retirer();
                if (gagnant == 1) {

                    if (tours[nbtour].donneur == tours[nbtour].gagnant)
                        j1.main.ajouterpos(c, tours[nbtour].coupmainPremier);
                    else
                        j1.main.ajouterpos(c, tours[nbtour].coupmainSec);
                } else {
                    if (tours[nbtour].donneur == tours[nbtour].perdant)
                        j2.main.ajouterpos(c, tours[nbtour].coupmainPremier);
                    else
                        j2.main.ajouterpos(c, tours[nbtour].coupmainSec);

                }
                break;
            case 3:
                c = pile3.retirer();
                if (gagnant == 1) {

                    if (tours[nbtour].donneur == tours[nbtour].gagnant)
                        j1.main.ajouterpos(c, tours[nbtour].coupmainPremier);
                    else
                        j1.main.ajouterpos(c, tours[nbtour].coupmainSec);
                } else {
                    if (tours[nbtour].donneur == tours[nbtour].perdant)
                        j2.main.ajouterpos(c, tours[nbtour].coupmainPremier);
                    else
                        j2.main.ajouterpos(c, tours[nbtour].coupmainSec);

                }
                break;
            case 4:
                c = pile4.retirer();
                if (gagnant == 1) {

                    if (tours[nbtour].donneur == tours[nbtour].gagnant)
                        j1.main.ajouterpos(c, tours[nbtour].coupmainPremier);
                    else
                        j1.main.ajouterpos(c, tours[nbtour].coupmainSec);
                } else {
                    if (tours[nbtour].donneur == tours[nbtour].perdant)
                        j2.main.ajouterpos(c, tours[nbtour].coupmainPremier);
                    else
                        j2.main.ajouterpos(c, tours[nbtour].coupmainSec);

                }
                break;
            case 5:
                c = pile5.retirer();
                if (gagnant == 1) {

                    if (tours[nbtour].donneur == tours[nbtour].gagnant)
                        j1.main.ajouterpos(c, tours[nbtour].coupmainPremier);
                    else
                        j1.main.ajouterpos(c, tours[nbtour].coupmainSec);
                } else {
                    if (tours[nbtour].donneur == tours[nbtour].perdant)
                        j2.main.ajouterpos(c, tours[nbtour].coupmainPremier);
                    else
                        j2.main.ajouterpos(c, tours[nbtour].coupmainSec);

                }
                break;
            case 6:
                c = pile6.retirer();

                if (tours[nbtour].gagnant == 1) {
                    if (tours[nbtour].donneur == tours[nbtour].gagnant)
                        j1.main.ajouterpos(c, tours[nbtour].coupmainPremier);
                    else
                        j1.main.ajouterpos(c, tours[nbtour].coupmainSec);
                } else {
                    if (tours[nbtour].donneur == tours[nbtour].perdant)
                        j2.main.ajouterpos(c, tours[nbtour].coupmainPremier);
                    else
                        j2.main.ajouterpos(c, tours[nbtour].coupmainSec);

                }
                break;
        }
    }

    public void piochePerdant(int numPioche) {
        Carte c;

        tours[nbtour].fixpiocheSec(numPioche);
        switch (numPioche) {
            case 1:
                c = pile1.retirer();
                if (tours[nbtour].perdant == 1) {
                    if (tours[nbtour].donneur == tours[nbtour].perdant)
                        j1.main.ajouterpos(c, tours[nbtour].coupmainPremier);
                    else
                        j1.main.ajouterpos(c, tours[nbtour].coupmainSec);
                } else {
                    if (tours[nbtour].donneur == tours[nbtour].perdant)
                        j2.main.ajouterpos(c, tours[nbtour].coupmainPremier);
                    else
                        j2.main.ajouterpos(c, tours[nbtour].coupmainSec);

                }
                break;
            case 2:
                c = pile2.retirer();

                if (tours[nbtour].perdant == 1) {
                    if (tours[nbtour].donneur == tours[nbtour].perdant)
                        j1.main.ajouterpos(c, tours[nbtour].coupmainPremier);
                    else
                        j1.main.ajouterpos(c, tours[nbtour].coupmainSec);
                } else {
                    if (tours[nbtour].donneur == tours[nbtour].perdant)
                        j2.main.ajouterpos(c, tours[nbtour].coupmainPremier);
                    else
                        j2.main.ajouterpos(c, tours[nbtour].coupmainSec);

                }
                break;
            case 3:
                c = pile3.retirer();

                if (tours[nbtour].perdant == 1) {
                    if (tours[nbtour].donneur == tours[nbtour].perdant)
                        j1.main.ajouterpos(c, tours[nbtour].coupmainPremier);
                    else
                        j1.main.ajouterpos(c, tours[nbtour].coupmainSec);
                } else {
                    if (tours[nbtour].donneur == tours[nbtour].perdant)
                        j2.main.ajouterpos(c, tours[nbtour].coupmainPremier);
                    else
                        j2.main.ajouterpos(c, tours[nbtour].coupmainSec);

                }
                break;
            case 4:
                c = pile4.retirer();
                if (perdant == 1) {

                    if (tours[nbtour].donneur == tours[nbtour].perdant)
                        j1.main.ajouterpos(c, tours[nbtour].coupmainPremier);
                    else
                        j1.main.ajouterpos(c, tours[nbtour].coupmainSec);
                } else {
                    if (tours[nbtour].donneur == perdant)
                        j2.main.ajouterpos(c, tours[nbtour].coupmainPremier);
                    else
                        j2.main.ajouterpos(c, tours[nbtour].coupmainSec);

                }
                break;
            case 5:
                c = pile5.retirer();

                if (tours[nbtour].perdant == 1) {
                    if (tours[nbtour].donneur == tours[nbtour].perdant)
                        j1.main.ajouterpos(c, tours[nbtour].coupmainPremier);
                    else
                        j1.main.ajouterpos(c, tours[nbtour].coupmainSec);
                } else {
                    if (tours[nbtour].donneur == tours[nbtour].perdant)
                        j2.main.ajouterpos(c, tours[nbtour].coupmainPremier);
                    else
                        j2.main.ajouterpos(c, tours[nbtour].coupmainSec);

                }
                break;
            case 6:
                c = pile6.retirer();

                if (tours[nbtour].perdant == 1) {
                    if (tours[nbtour].donneur == tours[nbtour].perdant)
                        j1.main.ajouterpos(c, tours[nbtour].coupmainPremier);
                    else
                        j1.main.ajouterpos(c, tours[nbtour].coupmainSec);
                } else {
                    if (tours[nbtour].donneur == tours[nbtour].perdant)
                        j2.main.ajouterpos(c, tours[nbtour].coupmainPremier);
                    else
                        j2.main.ajouterpos(c, tours[nbtour].coupmainSec);
                }
                break;
        }
        donneur = tours[nbtour].gagnant;
        receveur = tours[nbtour].perdant;
        nbtour++;
        nouvtour();

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


    public void annulleCoupPremier(int argument) {
        Carte card;
        card = tours[nbtour].cartePremier;
        if (tours[nbtour].donneur == 1) {
            j1.main.ajouterpos(card, tours[nbtour].coupmainPremier);
        } else {
            j2.main.ajouterpos(card, tours[nbtour].coupmainPremier);
        }
    }

    public void annullepiocheGagnant(int argument) {
        Carte card;
        if (tours[nbtour].gagnant == 1) {
            if (tours[nbtour].donneur == tours[nbtour].gagnant)
                card = j1.main.retirer(tours[nbtour].coupmainPremier);
            else
                card = j1.main.retirer(tours[nbtour].coupmainSec);
            switch (tours[nbtour].piochePremier) {
                case 1:
                    pile1.ajouterdeb(card);
                    break;
                case 2:
                    pile2.ajouterdeb(card);
                    break;
                case 3:
                    pile3.ajouterdeb(card);
                    break;
                case 4:
                    pile4.ajouterdeb(card);
                    break;
                case 5:
                    pile5.ajouterdeb(card);
                    break;
                case 6:
                    pile6.ajouterdeb(card);
                    break;
            }
        } else {
            if (tours[nbtour].donneur == tours[nbtour].gagnant)
                card = j2.main.retirer(tours[nbtour].coupmainPremier);
            else
                card = j2.main.retirer(tours[nbtour].coupmainSec);
            switch (tours[nbtour].piochePremier) {
                case 1:
                    pile1.ajouterdeb(card);
                    break;
                case 2:
                    pile2.ajouterdeb(card);
                    break;
                case 3:
                    pile3.ajouterdeb(card);
                    break;
                case 4:
                    pile4.ajouterdeb(card);
                    break;
                case 5:
                    pile5.ajouterdeb(card);
                    break;
                case 6:
                    pile6.ajouterdeb(card);
                    break;
            }
        }

    }

    public void annullejouerCoupSec(int argument) {
        Carte card;
        card = tours[nbtour].carteSeconde;
        if (tours[nbtour].donneur == 1) {
            j2.main.ajouterpos(card, tours[nbtour].coupmainSec);
        } else {
            j1.main.ajouterpos(card, tours[nbtour].coupmainSec);
        }
    }

    /*
     * public void {
     * if (cartePremier.couleur == carteSeconde.couleur) {
     * if (cartePremier.valeur > carteSeconde.valeur) {
     * gagnant = donneur;
     * perdant = receveur;
     * } else {
     * gagnant = receveur;
     * perdant = donneur;
     * }
     * } else if (carteSeconde.couleur == atout) {
     * gagnant = receveur;
     * perdant = donneur;
     * } else {
     * gagnant = donneur;
     * perdant = receveur;
     * }
     * ajoutepli();
     * }
     */
    public void annullegagnantPli() {
        if (tours[nbtour].gagnant == 1) {
            j1.Pli.retirer();
            j1.Pli.retirer();
            j1.scoreManche--;
            j1.scorePartie--;
        } else {
            j2.Pli.retirer();
            j2.Pli.retirer();
            j2.scoreManche--;
            j2.scorePartie--;

        }
    }

    public void annullepiochePerdant(int argument) {
        nbtour--;
        Carte card;
        int poscd = 10 - nbtour;
        if (poscd < 0) {
            poscd = poscd + 11;
        }
        if (tours[nbtour].perdant == 1) {
            if (tours[nbtour].donneur == tours[nbtour].perdant)
                card = j1.main.retirer(tours[nbtour].coupmainPremier);
            else
                card = j1.main.retirer(tours[nbtour].coupmainSec);
            switch (tours[nbtour].piocheSecond) {
                case 1:
                    pile1.ajouterdeb(card);
                    break;
                case 2:
                    pile2.ajouterdeb(card);
                    break;
                case 3:
                    pile3.ajouterdeb(card);
                    break;
                case 4:
                    pile4.ajouterdeb(card);
                    break;
                case 5:
                    pile5.ajouterdeb(card);
                    break;
                case 6:
                    pile6.ajouterdeb(card);
                    break;
            }
        } else {
            if (tours[nbtour].donneur == tours[nbtour].perdant)
                card = j2.main.retirer(tours[nbtour].coupmainPremier);
            else
                card = j2.main.retirer(tours[nbtour].coupmainSec);
            switch (tours[nbtour].piocheSecond) {
                case 1:
                    pile1.ajouterdeb(card);
                    break;
                case 2:
                    pile2.ajouterdeb(card);
                    break;
                case 3:
                    pile3.ajouterdeb(card);
                    break;
                case 4:
                    pile4.ajouterdeb(card);
                    break;
                case 5:
                    pile5.ajouterdeb(card);
                    break;
                case 6:
                    pile6.ajouterdeb(card);
                    break;
            }
        }
    }

    public int quiDonne() {
        return tours[nbtour].donneur;
    }

    public int quiRecois() {
        return tours[nbtour].receveur;
    }

    public Carte cartePremier() {
        return tours[nbtour].cartePremier;
    }

    public Carte carteSeconde() {
        return tours[nbtour].carteSeconde;
    }

    public int quigagnetour() {
        return tours[nbtour].gagnant;
    }

    public int receveur() {
        return tours[nbtour].donneur;
    }

    public int donneur() {
        return tours[nbtour].receveur;
    }

}
