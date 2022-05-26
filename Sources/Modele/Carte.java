package Modele;

import java.io.Serializable;

public class Carte implements Cloneable, Comparable<Carte>, Serializable {
    int valeur;
    int couleur;
    boolean visible;
    static final int V = 11;
    static final int D = 12;
    static final int R = 13;
    static final int AS = 14;

    static final int PIQUE = 4;
    static final int COEUR = 3;
    static final int CARREAU = 2;
    static final int TREFLE = 1;

    public Carte(int couleur, int valeur) {
        this.valeur = valeur;
        this.couleur = couleur;

    }

    @Override
    public Carte clone() {
        Carte res = new Carte(couleur, valeur);
        res.visible = visible;

        return res;
    }

    @Override
    public int compareTo(Carte o) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        Carte c = (Carte) o;

        return this.valeur == c.valeur && this.couleur == c.couleur;
    }

    public int valeur() {
        return valeur;
    }

    public int couleur() {
        return couleur;
    }
}
