package Vue;

import Modele.Partie;
import Patterns.Observateur;

class VueTestIA implements Observateur {
	
	boolean showProgression = true;
	boolean showTime = true;
	boolean showText = true;
	
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
    	if(showProgression) {
    		System.out.print("|");
    	}
        if (j.manchefini()) {
        	if(showProgression) {
        		System.out.println();
        	}
            afficheGagnantManche();
        }
        if (j.partifini()) {
        	if(showText) {
        		afficheGagnantPartie();
        	}
        }
        debutCoup = System.currentTimeMillis();
    }
    
    
    
    private void afficheGagnantManche() {
    	long delay = System.currentTimeMillis()-debutManche;
    	if(showText) {
    		System.out.print(j.nbmanche() - 1 + "	");
    	}
        if (j.quiGagneManche() != 0) {
        	if(showText) {
        		System.out.print("joueur " + j.quiGagneManche() + " gagne");
             	System.out.print(" Scores : "+j.scoremanchej1()+"	"+j.scoremanchej2()+"	");
        	} else {
        		System.out.print(j.scoremanchej1()+" "+j.scoremanchej2()+" ");
        	}
        } else {
        	if(showText) {
        		System.out.print("egalite                   	  	");
        	} else {
        		System.out.print(13+" "+13+" ");
        	}
        }
        if(showTime) {
        	System.out.print(" temp : "+delay + " " + maxDelay);
        }
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
        System.out.println("Le joueur 1 a gagne " + j.nbmanchegagnej1() + " manches , Le joueur 2 a gagne "
                + j.nbmanchegagnej2() + " manches");
        System.out.println("Score joueur 1 : " + j.scorePartiej1() + " Score joueur 2 : " + j.scorePartiej2());
        if(showTime) {
        	System.out.println("Temp total = " + (System.currentTimeMillis() - debutPartie) + " (ms)");
        }
    }
}
