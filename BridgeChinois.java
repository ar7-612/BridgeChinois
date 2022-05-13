import Controleur.ControleurMediateur;
import Modele.Partie;
import Vue.InterfaceTextuelle;

class BridgeChinois {

    public static void main(String[] args) {
        try {
            Partie j = new Partie();
            ControleurMediateur c = new ControleurMediateur(j);
            InterfaceTextuelle.demarrer(j, c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
