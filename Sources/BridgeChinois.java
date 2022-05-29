import Controleur.ControleurMediateur;
import Modele.Partie;
import Vue.InterfaceGraphique;
import Vue.InterfaceTextuelle;

@SuppressWarnings("unused")
class BridgeChinois {

    public static void main(String[] args) {
        try {
            Partie j = new Partie();
            ControleurMediateur c = new ControleurMediateur(j);
            InterfaceGraphique ig = new InterfaceGraphique(j,c);
            ig.setVisible(true);
            //InterfaceTextuelle.demarrer(j, c);
            //InterfaceTestIA.demarrer(j, c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
