package Modele;

import java.util.Stack;
import java.util.Enumeration;

public class Historique<E extends Commande> {
	Stack<E> passe, futur;

    Historique() {
        passe = new Stack<>();
        futur = new Stack<>();
    }
    
    public Enumeration<E> getEnumPasse () {
    	return passe.elements();
    }

    void nouveau(E c) {
        passe.push(c);
        c.Execute();
        while (!futur.isEmpty())
            futur.pop();
    }

    public boolean peutAnnuler() {
        return !passe.isEmpty();
    }

    E annuler() {
        if (peutAnnuler()) {
            E c = passe.pop();
            c.DesExecute();
            futur.push(c);
            return c;
        } else {
            return null;
        }
    }

    public boolean peutRefaire() {
        return !futur.isEmpty();
    }

    E refaire() {
        if (peutRefaire()) {
            E c = futur.pop();
            c.Execute();
            passe.push(c);
            return c;
        } else {
            return null;
        }
    }
}
