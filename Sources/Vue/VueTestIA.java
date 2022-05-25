package Vue;

import Modele.Partie;
import Patterns.Observateur;

class VueTestIA implements Observateur {
	Partie j;

    public VueTestIA(Partie j) {
        j.ajouteObservateur(this);
        this.j = j;
        debutPartie = System.currentTimeMillis();
        debutManche = System.currentTimeMillis();
        debutCoup =  System.currentTimeMillis();
        maxDelay = 0;
    }
    
    long debutPartie;
    long debutManche;
    long debutCoup;
    long maxDelay;

    @Override
    public void metAJour() {
    	long delay = System.currentTimeMillis()-debutCoup;
    	maxDelay = Math.max(maxDelay, delay);
    	System.out.print("|");
        if (j.manchefini()) {
        	System.out.println();
            afficheGagnantManche();
        }
        if (j.partifini()) {
           	afficheGagnantPartie();
        }
        debutCoup = System.currentTimeMillis();
    }

    private void afficheGagnantManche() {
    	long delay = System.currentTimeMillis()-debutManche;
        System.out.print(j.nbmanche() - 1 + "  ");
        if (j.quiGagneManche() != 0) {
            System.out.print("joueur " + j.quiGagneManche() + " gagne");
        	System.out.print(" Scores : "+j.scoremanchej1()+" "+j.scoremanchej2());
        } else {
            System.out.print("egalite");
        }
        System.out.print(" "+delay + " " + maxDelay);
        maxDelay = 0;
        debutManche = System.currentTimeMillis();
        debutCoup =  System.currentTimeMillis();
        System.out.println();
    }

    private void afficheGagnantPartie() {
        System.out.println("Fin De la Partie ");
        if (j.quiGagnePartie() != 0)
            System.out.println("Le joueur " + j.quiGagnePartie() + " a gagne la partie ");
        else
            System.out.println("Il y a une egalité personne ne gagne cette partie");
        System.out.println("Le joueur 1 a gagne" + j.nbmanchegagnej1() + " manches , Le joueur 2 a gagne "
                + j.nbmanchegagnej2() + "manches");
        System.out.println("Score joueur 1 : " + j.scorePartiej1() + " Score joueur 2 : " + j.scorePartiej2());
        System.out.println("Temp = " + (System.currentTimeMillis() - debutPartie) + " (ms)");
    }
}
