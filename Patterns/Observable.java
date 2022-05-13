package Patterns;

import java.util.ArrayList;
import java.util.Iterator;

public class Observable {
    ArrayList<Observateur> observateurs;

    public Observable() {
        observateurs = new ArrayList<Observateur>();
    }

    public void ajouteObservateur(Observateur l) {
        observateurs.add(l);
    }

    public void miseAJour() {
        Iterator<Observateur> it;
        it = observateurs.iterator();
        while (it.hasNext()) {
            it.next().metAJour();
        }
    }
}
