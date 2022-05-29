package Modele;

public class Tour {
    int donneur, receveur, gagnant, perdant, piochePremier, piocheSecond,
            coupmainPremier, coupmainSec;
    Carte cartePremier, carteSeconde;

    public void fixdonneur(int i) {
        donneur = i;
        if (donneur == 1)
            receveur = 2;
        else
            receveur = 1;
    }

    public void fixcartePremier(Carte i) {
        cartePremier = i;
    }

    public void fixcarteSec(Carte i) {
        carteSeconde = i;
    }

    public void fixgagnant(int i) {
        gagnant = i;
        if (gagnant == 1)
            perdant = 2;
        else
            perdant = 1;
    }

    public void fixcoupMainpremier(int i) {
        coupmainPremier = i;
    }

    public void fixcoupMainSec(int i) {
        coupmainSec = i;
    }

    public void fixpiochePremier(int i) {
        piochePremier = i;
    }

    public void fixpiocheSec(int i) {
        piocheSecond = i;

    }
}
