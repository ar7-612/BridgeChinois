package Modele;

public class Joueur {
    PileCartes Pli, cartesPiochees;
    boolean[] tabcouleur;
    int scoreManche, scorePartie, manchesGagnees, lg;
    int[] scores;
    String nom;
    Main main;
    
    public PileCartes getPli() {
    	return Pli;
    }
    
    public PileCartes getCartesPiochees() {
    	return cartesPiochees;
    }
    
    public boolean[] getTabCouleur() {
    	return tabcouleur;
    }

    public Joueur() {
        main = new Main();
        Pli = new PileCartes();
        tabcouleur = new boolean[4];
        cartesPiochees = new PileCartes();
        scoreManche = 0;
        scorePartie = 0;
        manchesGagnees = 0;
        nom = "";
        lg = 0;
        scores = new int[500];
    }

    /**
     * Renvoie un tableau des cartes de la main du joueur qu'il a le droit de jouer
     * 
     * @param couleur la couleur de la carte posée par le premier joueur
     * @return Tableau des cartes jouables
     */
    public Boolean jouables(int couleur) {

        for (int i = 0; i < main.taille; i++) {
            if (main.carte(i).couleur == couleur)
                return true;
        }
        return false;
    }

    public Main main() {
        return main;
    }

    public void ajouterScore() {
        scoreManche++;
        scorePartie++;
    }
}
