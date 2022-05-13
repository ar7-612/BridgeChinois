package Modele;

import java.util.ArrayList;

public class Tour {
    ArrayList<Coup> Couptour;
    Partie p;

    Tour() {
        Couptour = new ArrayList<Coup>();
    }

    void fixerPartie(Partie par) {
        p = par;
    }

    public void faireCoup(int codecoup, int arg) {
        Couptour.add(new Coup(codecoup, arg));
    }

    public ArrayList<Coup> CoupTour() {
        return Couptour;
    }
}
