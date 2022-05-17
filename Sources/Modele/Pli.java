package Modele;

public class Pli {
    Carte gagnant, perdant;
    int joueur;

    Pli(Carte gag, Carte perd, int jou) {
        gagnant = gag;
        perdant = perd;
        joueur = jou;
    }
}