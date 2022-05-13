package Vue;

import java.lang.module.Configuration;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import Modele.Partie;

public class InterfaceTextuelle implements InterfaceUtilisateur {
    Partie j;
    static Scanner s;
    CollecteurEvenements c;
    static VueTextuelle vueTextuelle;

    InterfaceTextuelle(Partie j, CollecteurEvenements c) {
        this.j = j;
        this.c = c;
    }

    public static void demarrer(Partie j, CollecteurEvenements c) {
        InterfaceTextuelle i = new InterfaceTextuelle(j, c);
        c.fixerInterfaceUtilisateur(i);
        s = new Scanner(System.in);
        vueTextuelle = new VueTextuelle(j);
        i.Configuration();
        vueTextuelle.metAJour();
        i.jouePartie();
        s.close();
    }

    @Override
    public void jouePartie() {
        c.fixerInterfaceUtilisateur(this);
        s = new Scanner(System.in);
        while (!j.finpartie && s.hasNextLine()) {
            try {
                int entier = Integer.parseInt(s.nextLine());
                switch (c.commande(entier)) {
                    case 0:
                        break;
                    case 1:
                        System.out.println("pile de pioche choisie incorrect");
                        break;
                    case 2:
                        System.out.println("carte choisie incorrect");
                        break;
                    case 3:
                        System.out.println("commande inconnue");
                        break;
                    case 4:
                        System.out.println(
                                "Votre adversaire a jouer " + j.cartePremCouleur()
                                        + " Vous avez cette couleur vous etes obliger de la jouer !");
                        break;
                    default:
                        System.out.println("commande inconnu");
                        break;

                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    @Override
    public void Configuration() {
        System.out.println(
                "Mode de jeu :  tapez m pour victoire en nombre de manche et p pour victoire en nombre de point");
        String mode = s.nextLine();
        while (!mode.equals("p") && !mode.equals("m")) {
            System.out.println("commande inconnu");
            mode = s.nextLine();
        }
        String modes;
        if (mode.equals("p")) {
            System.out.println(
                    "Mode de jeu :  tapez le nombre de point maximal pour qu'un joueur puisse gagne | Exemple : 20 ");
            modes = "points";
        } else {
            System.out.println(
                    "Mode de jeu :  tapez combien de manche la partie durera");
            modes = "manches";
        }
        int nombre = s.nextInt();
        System.out.println("vous avez choisis de gagner en " + nombre + " " + modes);
        j.ModeV(mode, nombre);

    }

}