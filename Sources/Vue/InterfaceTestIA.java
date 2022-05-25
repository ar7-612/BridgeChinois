package Vue;

import java.util.Scanner;

import Modele.Partie;

public class InterfaceTestIA implements InterfaceUtilisateur {
	Partie j;
    static Scanner s;
    CollecteurEvenements c;
    static VueTestIA vue;

    InterfaceTestIA(Partie j, CollecteurEvenements c) {
        this.j = j;
        this.c = c;
    }

    public static void demarrer(Partie j, CollecteurEvenements c) {
    	s = new Scanner(System.in);
    	InterfaceTestIA i = new InterfaceTestIA(j, c);
        c.fixerInterfaceUtilisateur(i);
        s = new Scanner(System.in);
        vue = new VueTestIA(j);
        i.Configuration();
        vue.metAJour();
        i.jouePartie();
        s.close();
    }

    @Override
    public void jouePartie() {
    	
        long delayIA = 0;// en ms
        long timer;

        c.fixerInterfaceUtilisateur(this);
                
        int entier = -10;
        
        // while (!j.finpartie && s.hasNextLine()) {
        while (!j.finpartie) {
            try {
                
            	if (j.manchefini()) {
            		entier = 1;
            	} else {
                	timer = System.currentTimeMillis();
                    if (entier == -1 || entier == -2) {
                        // On change rien
                    } else {
                        entier = j.getCoupIA(j.quiJoue());
                    }
                    while (System.currentTimeMillis() - timer < delayIA) {
                        // A "threder" pour ne pas freeze l'interface graphique
                    }
                }
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
                // e.printStackTrace();
            }
        }
    }
    
    //Pour test IA
    @Override
    public void Configuration() {
    	String modeJ1 = "easy";
    	String modeJ2 = "alea";
    	int nbManches = 10;
    	/*
    	System.out.println("modes : easy, alea");
    	System.out.print("mode ia 1 : ");
    	modeJ1 = s.nextLine();
        System.out.print("mode ia 2 : ");
        modeJ2 = s.nextLine();
        System.out.print("nombre manches : ");
        nbManches = s.nextInt();
        */
    	for(int i=0;i<82;i++) {
    		System.out.print("|");
    	}
    	System.out.println("|");
        j.ModeV("m", nbManches);
        j.ModeJoueur(1, modeJ1);
        j.ModeJoueur(2, modeJ2);
    }
}
