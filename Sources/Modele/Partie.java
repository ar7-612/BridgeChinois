package Modele;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import Controleur.IAbase;
import Patterns.Observable;


public class Partie extends Observable implements Serializable{

    private Historique<Coup> histo;

    ArrayList<Manche> Manches;
    ArrayList<Carte> cartepiochevisible;
    Manche manchecourante;
    Joueur j1, j2;
    String Modedejeu;
    boolean manchefin;
    public boolean debutpartie, finpartie;
    int phase, phasetour, nbmanche, nMax, score1, score2;
    int donneurdebut;
    boolean J1EstIA;
    boolean J2EstIA;
    IAbase joueur1IA;
    IAbase joueur2IA;
    Boolean pause;
    int reflechie1,reflechie2;

    private String modeia2;

    private String modeia1;

    private int reflechie;

    private int entier;

    public Partie() {
        j1 = new Joueur();
        j2 = new Joueur();
        donneurdebut = 1;
        manchecourante = new Manche(j1, j2, donneurdebut);
        phase = 4;
        phasetour = 0;
        nbmanche = 1;
        score1 = 0;
        pause=false;
        score2 = 0;
        debutpartie = true;
        finpartie = false;
        histo = new Historique<Coup>();

        Manches = new ArrayList<Manche>();
        Manches.add(manchecourante);
    }
    public void nouvellePartie(){
        j1 = new Joueur();
        j2 = new Joueur();
        donneurdebut = 1;
        manchecourante = new Manche(j1, j2, donneurdebut);
        phase = 4;
        phasetour = 0;
        nbmanche = 1;
        score1 = 0;
        pause=false;
        score2 = 0;
        debutpartie = true;
        finpartie = false;
        histo = new Historique<Coup>();

        Manches = new ArrayList<Manche>();
        Manches.add(manchecourante);
    }
    public void pause(){
        pause=!pause;
    }
    public Carte[] CartevisiblePile() {
    	return manchecourante.CartevisiblePile();
    }
    
    public int atout() {
        return manchecourante.atout;
    }

    public Boolean JouableSec(int arg) {
        if (manchecourante.donneur == 1) {

            if (manchecourante.j2.jouables(manchecourante.cartePremier().couleur) > 0)
                return manchecourante.j2.main.carte(arg).couleur == manchecourante.cartePremier().couleur;
            else
                return true;
        } else {
            if (manchecourante.j1.jouables(manchecourante.cartePremier().couleur) > 0)
                return manchecourante.j1.main.carte(arg).couleur == manchecourante.cartePremier().couleur;

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

    public void sauvegarder(String str) throws IOException{

        File fichier =  new File(str) ;

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichier));

        oos.writeObject(this);
        oos.close();
    }
    public void sauvegarder() throws IOException{
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Entrez le nom du fichier de sauvegarde:");
        String str = sc.nextLine();
        File fichier =  new File(str) ;

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichier));

        oos.writeObject(this);
        oos.close();
    }
    
    /**
     * Charger la configuration contenue dans un fichier dont le nom est demandé
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public void charger(String fichier) throws IOException, ClassNotFoundException{


        // ouverture d'un flux sur un fichier
       // ouverture d'un flux sur un fichier
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichier));

        // désérialization de l'objet
        Partie e = (Partie) ois.readObject();
        fixerPartiechargement(e);
        ois.close();
    }
    public void charger() throws IOException, ClassNotFoundException{
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Entrez le nom du fichier à charger:");
        String str = sc.nextLine();
        File fichier =  new File(str) ;

        // ouverture d'un flux sur un fichier
       // ouverture d'un flux sur un fichier
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichier));

        // désérialization de l'objet
        Partie e = (Partie) ois.readObject();
        fixerPartiechargement(e);
        ois.close();
    }
    void fixerPartiechargement(Partie p){
        histo=p.histo;

        Manches=p.Manches;
        cartepiochevisible=p.cartepiochevisible;
        manchecourante=p.manchecourante;
        j1=p.j1; j2=p.j2;
        Modedejeu=p.Modedejeu;
        manchefin=p.manchefin;
        debutpartie=p.debutpartie; finpartie=p.finpartie;
        phase=p.phase; phasetour=p.phasetour; nbmanche=p.nbmanche; nMax=p.nMax; score1=p.score1; score2=p.score2;
        donneurdebut=p.donneurdebut;
        J1EstIA=p.J1EstIA;
        J2EstIA=p.J2EstIA;
        modeia1=p.modeia1;
        modeia2=p.modeia2;
        pause=p.pause;
        reflechie=p.reflechie;
        entier=p.entier;
        if(modeia1!=null)
        joueur1IA=IAbase.creerIA(this, j1, j2, 1, modeia1);
        if(modeia2!=null)        joueur2IA=IAbase.creerIA(this, j1, j2, 2, modeia2);
    }
    /**
     * Renvoie la liste des fichiers contenus dans le dossier sauvegardes
     * @return un tableau de String contenant les noms des fichiers contenus dans le dossier sauvegardes
     */
    public String[] afficheSauvegardes(){
        File dossier=new File("sauvegardes");
        if (dossier.exists() && dossier.isDirectory()){ //Si le dossier sauvegarde existe
            File[] files=dossier.listFiles();
            String[] res = new String[files.length];
            for(int i=0; i<files.length;i++){
                res[i] = files[i].getName();
            }
            return res;
        }else{
            System.out.println("Le dossier \"sauvegardes\" n'existe pas");
            return null;
        }
    }
    
    public void jouerCoup(Coup cp) {

        cp.fixerpartie(this);
        histo.nouveau(cp);
        miseAJour();
    }

    void nouvellemanche() {
        if (donneurdebut == 1)
            donneurdebut = 2;
        manchecourante = new Manche(j1, j2, donneurdebut);
        System.out.println("hh");
        histo=new Historique<>();
        Manches.add(manchecourante);
        phasetour = 0;
        phase = 4;
    }

    public Carte[] cartevisiblepioche() {
        return manchecourante.CartevisiblePile();
    }

    public Joueur joueurDonneur() {
        if (manchecourante.quiDonne() == 1)

            return manchecourante.j1;
        else
            return manchecourante.j2;
    }

    public Joueur joueurReceveur() {
        if (manchecourante.quiRecois() == 1)

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
                    if(finpartie){if (manchecourante.j1.scoreManche > manchecourante.j2.scoreManche
                    && manchecourante.Manchefini()) {
                manchecourante.j1.manchesGagnees++;
            } else if (manchecourante.Manchefini()
                    && manchecourante.j1.scoreManche < manchecourante.j2.scoreManche) {
               manchecourante.j2.manchesGagnees++;
            } }

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

        return manchecourante.quiDonne();
    }

    public int quiRecois() {
        return manchecourante.quiRecois();

    }

    public Boolean pilevide(int arg) {
        return manchecourante.pilevide(arg);
    }
    
    public int taillePile(int arg) {
    	return manchecourante.taillePile(arg);
    }

    public boolean manchefini() {
        return manchecourante.Manchefini();
    }

    public boolean tourfini() {
        return phasetour % phase == 0;
    }

    public String cartePremCouleur() {
        String s = null;

        switch (manchecourante.cartePremier().couleur()) {

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

        return manchecourante.cartePremier();
    }

    public Carte carteSec() {
        return manchecourante.carteSeconde();

    }

    public void ModeV(String mode, int nombre) {
        Modedejeu = mode;
        nMax = nombre;
        testFinPartie();
    }

    public int quiJoue() {
        switch (phasetour()) {
            case 0:
                return quiDonne();
            case 1:
                return quiRecois();
            case 2:
                return quiGagnetour();
            case 3:
                if (quiGagnetour() == 1)
                    return 2;
                else
                    return 1;
            default:
                return -1;
        }
    }

    public void ModeJoueur(int joueur, String mode) {
    	if(mode.equals("non")) {
    		if(joueur==1) {
        		J1EstIA=false;
                        joueur1IA=null;
        	}else {
        		J2EstIA=false;
                         joueur2IA=null;
        	}
    	} else {
    		if(joueur==1) {
                modeia1=mode;
        		J1EstIA=true;
        		joueur1IA = IAbase.creerIA(this,j1,j2,1,mode);
        	}else {
                modeia2=mode;
        		J2EstIA=true;
        		joueur2IA = IAbase.creerIA(this,j1,j2,2,mode);
        	}
    	}
    	
    }

    public boolean estIA(int joueur) {
        if (joueur == 1) {
            return J1EstIA;
        } else {
            return J2EstIA;
        }
    }

    public int getCoupIA(int joueur) {
        if (joueur == 1) {

            if(!joueur1IA.isAlive())
                {   
                    joueur1IA=IAbase.creerIA(this, j1, j2, 1, modeia1);
                    joueur1IA.start();}
            return getreflechie();
        } else {
            reflechie2=1;
            if(!joueur2IA.isAlive())
                {   
                    joueur2IA=IAbase.creerIA(this, j1, j2, 2, modeia2);
                    joueur2IA.start();}
            return getreflechie();
        }
    }

    public boolean partifini() {
        return finpartie;
    }

    public int scorePartiej1() {
        return manchecourante.j1.scorePartie;
    }

    public int phasetourterm() {

        return phasetour % phase;
    }
  
    public int scorePartiej2() {
        return manchecourante.j2.scorePartie;
    }

    public int quiGagnePartie() {
        if (Modedejeu.equals("m")) {
            if (manchecourante.j1.manchesGagnees > manchecourante.j2.manchesGagnees)
                return 1;
            else if (manchecourante.j1.manchesGagnees < manchecourante.j2.manchesGagnees) {
                return 2;
            }

        } else {
            if (manchecourante.j1.scorePartie > manchecourante.j2.scorePartie)
                return 1;
            else {
                return 2;
            }
        }
        return 0;
    }

    public int quiGagneManche() {
        if (manchecourante.j1.scoreManche > manchecourante.j2.scoreManche)
            return 1;
        else if (manchecourante.j1.scoreManche < manchecourante.j2.scoreManche) {
            return 2;
        }
        return 0;
    }

    public void annulleCoup(Coup cp) {
        cp.fixerpartie(this);
        histo.annuler();
        miseAJour();
    }

    public void refaireCoup(Coup cp) {
        cp.fixerpartie(this);
        histo.refaire();
        miseAJour();
    }

    public int quiGagnetour() {
        return manchecourante.quigagnetour();
    }

    public int nbmanchegagnej1() {
        return manchecourante.nbmanchegagnej1();
    }

    public int nbmanchegagnej2() {
        return manchecourante.nbmanchegagnej2();
    }
    public int quiGagnetourav() {
        return manchecourante.quigagnetourav();
    }
    public Carte cartePremav() {
        return manchecourante.cartePremierav();
    }
    public Carte carteSecav() {
        return manchecourante.carteSecondeav();
    }
    public int getreflechie() {
        if (quiJoue()==1){
            return reflechie;
        }
        else{
            return reflechie;
        }
    }
    public void reflechie1() {
        reflechie=1;
    }
    public void reflechie0() {
        reflechie=0;
    }
    public int getentier() {
        if (quiJoue()==1){
            return entier;
        }
        else{
            return entier;
        }
    }
    public void entierfix(int jouerCoup) {
        entier=jouerCoup;
    }
    public boolean getpause() {
        return pause;
    }
    public boolean IsIa(int quiJoue) {
        if(quiJoue==1)
           { if(J1EstIA)
                return true;
            else    
                return false;

           }else
            {
                if(J2EstIA)
                    return true;
                else 
                    return false;
            }
    }
    public boolean peutRefaire() {
        return histo.peutRefaire();
    }
    public int taillepile(int pile)
    {
        switch(pile)
        {
            case 1:
             return manchecourante.pile1.taille();
            case 2:
             return manchecourante.pile2.taille();
            case 3:
             return manchecourante.pile3.taille();
             case 4:
             return manchecourante.pile4.taille();
             case 5:
             return manchecourante.pile5.taille();
             case 6:
             return manchecourante.pile6.taille();
             default:
                return 0;
        }
    }

}
