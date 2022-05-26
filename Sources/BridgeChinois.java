import Controleur.ControleurMediateur;
import Modele.Partie;
import Vue.InterfaceTestIA;
import Vue.InterfaceTextuelle;

@SuppressWarnings("unused")
class BridgeChinois {

    public static void main(String[] args) {
        try {
            Partie j = new Partie();
            ControleurMediateur c = new ControleurMediateur(j);
            //InterfaceTextuelle.demarrer(j, c);
            InterfaceTestIA.demarrer(j, c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
