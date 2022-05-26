package Vue;

import Modele.*;
import Patterns.Observateur;

public class VueTextuelle implements Observateur {
    Partie j;

    public VueTextuelle(Partie j) {
        j.ajouteObservateur(this);
        this.j = j;

    }

    public void afficherCarte(Carte c) {
        switch (c.couleur()) {
            case 1:
                System.out.print("TREFLE");
                break;
            case 2:
                System.out.print("CARREAU");
                break;
            case 3:
                System.out.print("COEUR");
                break;
            case 4:
                System.out.print("PIQUE");
                break;
        }
        System.out.print("|");
        switch (c.valeur()) {
            case 11:
                System.out.println("VALET");
                break;
            case 12:
                System.out.println("DAME");
                break;
            case 13:
                System.out.println("ROI");
                break;
            case 14:
                System.out.println("AS");
                break;
            default:
                System.out.println(c.valeur());
        }
    }

    public void afficherCarteDonneur() {
        if (j.quiDonne() == 1) {
            System.out.println("Au tour du joueur 1");
            System.out.println("Main Joueur 1");
        } else {
            System.out.println("Au tour du joueur 2");
            System.out.println("Main Joueur 2");
        }
        for (int i = 0; i < j.joueurDonneur().main().taille(); i++) {
            System.out.print("[" + (i) + "] ");
            afficherCarte(j.joueurDonneur().main().carte(i));
        }
    }

    public void affichePile() {
        System.out.println("Les Carte visible de la pioche sont");
        Carte[] Cartevis = j.cartevisiblepioche();
        int i = 0;
        while (i < 6) {
            if (Cartevis[i] != null) {
                System.out.print("[" + (i + 1) + "] ");
                afficherCarte(Cartevis[i]);
            }
            i++;
        }

    }

    public void afficheGagnantPlis() {
        if (!j.debutpartie)

            System.out.println("Le joueur " + j.quiGagnetour() + " a gagne le plis avec");

        afficherCarte(j.cartePrem());
        afficherCarte(j.carteSec());

    }

    public void afficheCarteReceveur() {
        if (j.quiRecois() == 1) {
            System.out.println("Au tour du joueur 1");
            System.out.println("Main Joueur 1");
        } else {
            System.out.println("Au tour du joueur 2");
            System.out.println("Main Joueur 2");
        }
        for (int i = 0; i < j.joueurReceveur().main().taille(); i++) {
            System.out.print("[" + (i) + "] ");
            afficherCarte(j.joueurReceveur().main().carte(i));
        }
    }

    @Override
    public void metAJour() {
        afficheretourligne();
        if (!j.partifini() && (!j.tourfini() || j.debutpartie)) {
            switch (j.phasetourterm()) {
                case 0:
                    if (j.phase() == 2) {
                        afficheGagnantPlisav();

                    }
                    affichePile();
                    afficherCarteDonneur();
                    if (j.quiDonne() == 1) {
                        afficheAtout();

                        System.out.println(
                                "Joueur 1 Choisisez une carte a jouer | -1 pour annuler le dernier coup | -2 pour refaire le coup annuler");
                    } else {
                        afficheAtout();
                        System.out.println(
                                "Joueur 2 Choisisez une carte a jouer | -1 pour annuler le dernier coup | -2 pour refaire le coup annuler");

                    }
                    break;
                case 1:
                    System.out.print("carte jouee : ");
                	afficherCarte(j.cartePrem());
                	System.out.println();
                    affichePile();
                    afficheCarteReceveur();
                    if (j.quiDonne() == 2) {
                        afficheAtout();

                        System.out.println(
                                "Joueur 1 Choisisez une carte a jouer | -1 pour annuler le dernier coup | -2 pour refaire le coup annuler");
                    } else {
                        afficheAtout();
                        System.out.println(
                                "Joueur 2 Choisisez une carte a jouer | -1 pour annuler le dernier coup | -2 pour refaire le coup annuler");

                    }
                    break;
                case 2:
                    afficheGagnantPlis();
                    affichePile();
                    if (j.quiGagnetour() == 1) {
                        afficheAtout();

                        System.out.println(
                                "Joueur 1 Choisisez une carte a piocher | -1 pour annuler le dernier coup | -2 pour refaire le coup annuler");
                    } else {
                        afficheAtout();
                        System.out.println(
                                "Joueur 2 Choisisez une carte a piocher | -1 pour annuler le dernier coup | -2 pour refaire le coup annuler");

                    }
                    break;
                case 3:
                    affichePile();
                    if (j.quiGagnetour() == 2) {
                        afficheAtout();

                        System.out.println(
                                "Joueur 1 Choisisez une carte a piocher | -1 pour annuler le dernier coup | -2 pour refaire le coup annuler");
                    } else {
                        afficheAtout();
                        System.out.println(
                                "Joueur 2 Choisisez une carte a piocher | -1 pour annuler le dernier coup | -2 pour refaire le coup annuler");

                    }
                    break;
            }
        } else {
            if (!j.partifini()) {
                if (j.phase() == 2 && !j.manchefini()) {
                    afficheGagnantPlisav();

                }
                if (j.manchefini()) {
                    afficheGagnantPlis();
                    afficheGagnantManche();
                } else {

                    affichePile();
                    afficherCarteDonneur();
                    afficheAtout();
                    System.out.println("Choisisez une carte a jouer");
                }
            } else {
                afficheGagnantManche();
                afficheGagnantPartie();
            }
        }
    }

    private void afficheGagnantPlisav() {
        if (!j.debutpartie)

        System.out.println("Le joueur " + j.quiGagnetourav() + " a gagne le plis avec");

        afficherCarte(j.cartePremav());
        afficherCarte(j.carteSecav());
        
    }

    private void afficheAtout() {
        switch (j.atout()) {
            case 1:
                System.out.println("l'atout est TREFLE");
                break;
            case 2:
                System.out.println("l'atout est CARREAU");
                break;
            case 3:
                System.out.println("l'atout est COEUR");
                break;
            case 4:
                System.out.println("l'atout est PIQUE");
                break;
            default:
                System.out.println("Il n'y a pas d'atout dans cette manche");
                break;
        }
    }

    private void afficheGagnantManche() {
        System.out.println("Fin Manche " + (j.nbmanche() - 1));
        if (j.quiGagneManche() != 0)
            System.out.println("Le joueur " + j.quiGagneManche() + " a gagne la manche ");
        else
            System.out.println("Il y a une egalité personne ne gagne cette manche");
        System.out.println("Score joueur 1 : " + j.scoremanchej1() + " Score joueur 2 : " + j.scoremanchej2());
        if (!j.partifini()) {
            System.out.println(" tapez 1 pour rejouer , 2 pour quitter");
        }
    }

    private void afficheGagnantPartie() {
        System.out.println("Fin De la Partie ");
        if (j.quiGagnePartie() != 0)
            System.out.println("Le joueur " + j.quiGagnePartie() + " a gagne la partie ");
        else
            System.out.println("Il y a une egalité personne ne gagne cette partie");
        System.out.println("Le joueur 1 a gagne " + j.nbmanchegagnej1() + " manches , Le joueur 2 a gagne "
                + j.nbmanchegagnej2() + " manches");
        System.out.println("Score joueur 1 : " + j.scorePartiej1() + " Score joueur 2 : " + j.scorePartiej2());
    }

    private void afficheretourligne() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }
}
