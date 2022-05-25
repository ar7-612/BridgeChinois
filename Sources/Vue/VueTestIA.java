package Vue;

import Modele.Partie;
import Patterns.Observateur;

class VueTestIA implements Observateur {
	Partie j;

    public VueTestIA(Partie j) {
        j.ajouteObservateur(this);
        this.j = j;

    }
    
    long debutPartie;
    long debutManche;

    @Override
    public void metAJour() {
    	System.out.print("|");
        if (j.manchefini()) {
        	System.out.println();
            afficheGagnantManche();
        }
        if (j.partifini()) {
           	afficheGagnantPartie();
        }
    }

    private void afficheGagnantManche() {
        System.out.print(j.nbmanche() - 1 +"("+(System.currentTimeMillis()-debutManche)+") = ");
        if (j.quiGagneManche() != 0) {
            System.out.print("joueur " + j.quiGagneManche() + " gagne");
        	System.out.print(" Scores : "+j.scoremanchej1()+" "+j.scoremanchej2());
        } else {
            System.out.print("egalite");
        }
        debutManche = System.currentTimeMillis();
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
