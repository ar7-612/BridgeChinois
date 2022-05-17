package Modele;

import Structure.Sequence;
import Structure.SequenceListe;

public class Historique<E extends Commande> {
    Sequence<E> passe, futur;

    Historique() {
        passe = new SequenceListe<>();
        futur = new SequenceListe<>();
    }

    void nouveau(E c) {
        passe.insereTete(c);
        c.Execute();
        while (!futur.estVide())
            futur.extraitTete();
    }

    public boolean peutAnnuler() {
        return !passe.estVide();
    }

    E annuler() {
        if (peutAnnuler()) {
            E c = passe.extraitTete();
            c.DesExecute();
            futur.insereTete(c);
            return c;
        } else {
            return null;
        }
    }

    public boolean peutRefaire() {
        return !futur.estVide();
    }

    E refaire() {
        if (peutRefaire()) {
            E c = futur.extraitTete();
            c.Execute();
            passe.insereTete(c);
            return c;
        } else {
            return null;
        }
    }
}
