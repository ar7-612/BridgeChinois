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

    public void retirer(Carte c) {
        int i = 0;
        while (i < taille && !main[i].equals(c)) {
            i++;
        }
        while (i < (taille - 1)) {
            main[i] = main[i + 1];
            i++;
        }
        taille = taille - 1;

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

    public void retirer(int i) {
        while (i < taille - 1) {
            main[i] = main[i + 1];
            i++;
        }
        main[i] = null;
        taille = taille - 1;
    }
}
