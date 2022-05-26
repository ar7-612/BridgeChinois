package Modele;

import java.io.Serializable;

public abstract class Commande implements Serializable{
    abstract void Execute();

    abstract void DesExecute();
}
