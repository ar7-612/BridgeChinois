package Modele;

public class Coup {
    int codecoup, argument;
    static final int Premierdepos = 0;
    static final int Seconddepos = 1;
    static final int PremierPioche = 2;
    static final int SecondPioche = 3;

    Coup(int cdcoup, int arg) {
        codecoup = cdcoup;
        argument = arg;
    }
}
