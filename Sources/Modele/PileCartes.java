package Modele;

import java.util.ArrayList;
import java.util.Iterator;
import static Modele.Carte.*;
import java.util.Random;

public class PileCartes implements Cloneable {
    ArrayList<Carte> pile;

    public PileCartes() {
        pile = new ArrayList<Carte>(5);
    }

    public PileCartes(Carte c) {
        pile = new ArrayList<Carte>(5);
        pile.add(c);
    }

    public void ajouter(Carte c) {
        pile.add(c);
    }

    public void ajouterdeb(Carte c) {
        pile.add(0, c);
    }

    public boolean vide() {
        return pile.isEmpty();
    }

    public Iterator<Carte> iterateur() {
        Iterator<Carte> it = pile.iterator();
        return it;
    }

    public void retirer(Carte c) {
        pile.remove(c);
    }

    public Carte retirer() {
        Carte res = null;
        if (!pile.isEmpty()) {
            Iterator<Carte> it = pile.iterator();
            res = it.next();
            pile.remove(res);
            System.out.println(res.couleur + "|" + res.valeur);
        }
        return res;
    }

    public void paquet() {
        pile.clear();

        for (int i = 2; i < 15; i++) {
            pile.add(new Carte(COEUR, i));
        }
        for (int i = 2; i < 15; i++) {
            pile.add(new Carte(TREFLE, i));
        }
        for (int i = 2; i < 15; i++) {
            pile.add(new Carte(PIQUE, i));
        }
        for (int i = 2; i < 15; i++) {
            pile.add(new Carte(CARREAU, i));
        }
    }

    @Override
    public PileCartes clone() {
        PileCartes p = new PileCartes();
        p.pile = (ArrayList<Carte>) pile.clone();
        return p;
    }

    public Carte aleatoire(boolean visible) {
        Random r = new Random(100);
        int i = r.nextInt(pile.size());
        Carte res = pile.get(i);
        pile.remove(res);
        if (res.visible != visible) {
            res.visible = visible;
        }

        return res;
    }

    public Carte premiere() {
        Carte res = null;
        if (!pile.isEmpty()) {
            Iterator<Carte> it = pile.iterator();
            res = it.next();
        }
        return res;
    }

}
