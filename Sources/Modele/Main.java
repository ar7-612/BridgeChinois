package Modele;

public class Main {
    Carte[] main;
    int taille;

    Main() {
        main = new Carte[11];
        taille = 0;
    }

    public void ajouter(Carte c) {
        if (taille != 11)
            main[taille] = c;
        taille++;
    }

    public void ajouterpos(Carte c, int i) {
        Carte[] main2 = new Carte[11];
        for (int j = 0; j < i; j++) {
            main2[j] = main[j];
        }
        main2[i] = c;
        for (int j = i + 1; j < 11; j++) {
            main2[j] = main[j - 1];
        }
        main = main2;
        taille++;
    }

    public Carte retirer(Carte c) {
        int i = 0;
        Carte card;
        while (i < taille && !main[i].equals(c)) {
            i++;
        }
        card = main[i];

        while (i < (taille - 1)) {
            main[i] = main[i + 1];
            i++;
        }
        taille = taille - 1;

        return card;


    }

    public Carte carte(int i) {
        return main[i];
    }

    public int taille() {
        return taille;
    }

    public Carte[] tabmain() {
        return main;
    }

    public Carte retirer(int i) {
        Carte card = main[i];

        while (i < taille - 1) {
            main[i] = main[i + 1];
            i++;
        }
        main[i] = null;
        taille = taille - 1;

        return card;

    }
}
