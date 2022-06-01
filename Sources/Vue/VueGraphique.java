/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Modele.Carte;
import Modele.Partie;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import static javax.swing.BorderFactory.createEtchedBorder;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;



/**
 *
 * @author khebt
 */
public class VueGraphique extends JFrame implements InterfaceUtilisateur , ActionListener {
    Partie j;
    CollecteurEvenements c;
    JLabel J1c1,J1c2,J1c3,J1c4,J1c5,J1c6,J1c7,J1c8,J1c9,J1c10,J1c11,J2c1,J2c2,J2c3,J2c4,J2c5,J2c6,J2c7,J2c8,J2c9,J2c10,J2c11;
    JLabel pile1,pile2,pile3,pile4,pile5,pile6,crtpile1,crtpile2,crtpile3,crtpile4,crtpile5,crtpile6,carteJ1,carteJ2;    
    JLabel J1pli,J2pli,J1nbrpli,J2nbrpli,tourJ1,tourJ2,quitour,quigagne,action,action2;
    JLabel nummanche,atout,namej1,namej2,nomJ1,nomJ2,scorej1,scorej2,winner,trophy,nbr,mode;
    JTextField ptJ1,ptJ2,j1m1,j2m1,j1m2,j2m2,j1m3,j2m3,j1m4,j2m4;
    JButton contin,hide,hide2;
    JToggleButton pause;
    int indiceC,nb;
    int p1=4,p2=4,p3=4,p4=4,p5=4,p6=4;
    int pli1=1,pli2=1;
    boolean tourj1 = false, tourj2 = false, piocher = false, dosj1 = false, dosj2 = false;
    JFrame histof,partief;
    int r = 1, r2 = 1;
    private int reflechie;
    Timer time;
    int ref;
    
    
    public VueGraphique(Partie j, CollecteurEvenements c)  {
        this.j = j;
        this.c = c;
        ref=0;
        initComponents();
    }
       
        
    public String afficherCarte(Carte c){
        String color,val,carte;
        switch (c.couleur()) {
            case 1:
                color = "TREFLE";
                break;
            case 2:
                color = "CARREAU";
                break;
            case 3:
                color = "COEUR";
                break;
            case 4:
                color = "PIQUE";
                break;
            default :
                color = null;
        }
        switch (c.valeur()) {
            case 11: 
                val = "VALET";
                break;
            case 12: 
                val = "DAME";
                break;
            case 13: 
                val = "ROI";
                break;
            case 14: 
                val = "AS";
                break;
            default:
                val = Integer.toString(c.valeur());
        }
        carte = color+"_"+val;
        return carte;
    }
    
  
    
    public void afficherCarteDonneur(){
        if(j.quiDonne() == 1){
            switch (j.joueurDonneur().main().taille()) {
                case 11:
                    J1c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(0))+".png")));
                    J1c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(1))+".png")));
                    J1c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(2))+".png")));
                    J1c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(3))+".png")));
                    J1c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(4))+".png")));
                    J1c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(5))+".png")));
                    J1c7.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(6))+".png")));
                    J1c8.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(7))+".png")));
                    J1c9.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(8))+".png")));
                    J1c10.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(9))+".png")));
                    J1c11.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(10))+".png")));  
                    break;
                case 10:
                    J1c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(0))+".png")));
                    J1c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(1))+".png")));
                    J1c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(2))+".png")));
                    J1c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(3))+".png")));
                    J1c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(4))+".png")));
                    J1c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(5))+".png")));
                    J1c7.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(6))+".png")));
                    J1c8.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(7))+".png")));
                    J1c9.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(8))+".png")));
                    J1c10.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(9))+".png")));
                    J1c11.setIcon(null);  
                    break;
                case 9:
                    J1c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(0))+".png")));
                    J1c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(1))+".png")));
                    J1c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(2))+".png")));
                    J1c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(3))+".png")));
                    J1c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(4))+".png")));
                    J1c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(5))+".png")));
                    J1c7.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(6))+".png")));
                    J1c8.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(7))+".png")));
                    J1c9.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(8))+".png")));
                    J1c10.setIcon(null);
                    J1c11.setIcon(null);  
                    break;
                case 8:
                    J1c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(0))+".png")));
                    J1c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(1))+".png")));
                    J1c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(2))+".png")));
                    J1c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(3))+".png")));
                    J1c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(4))+".png")));
                    J1c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(5))+".png")));
                    J1c7.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(6))+".png")));
                    J1c8.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(7))+".png")));
                    J1c9.setIcon(null);
                    J1c10.setIcon(null);
                    J1c11.setIcon(null);  
                    break;
                case 7:
                    J1c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(0))+".png")));
                    J1c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(1))+".png")));
                    J1c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(2))+".png")));
                    J1c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(3))+".png")));
                    J1c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(4))+".png")));
                    J1c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(5))+".png")));
                    J1c7.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(6))+".png")));
                    J1c8.setIcon(null);
                    J1c9.setIcon(null);
                    J1c10.setIcon(null);
                    J1c11.setIcon(null);  
                    break;
                case 6:
                    J1c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(0))+".png")));
                    J1c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(1))+".png")));
                    J1c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(2))+".png")));
                    J1c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(3))+".png")));
                    J1c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(4))+".png")));
                    J1c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(5))+".png")));
                    J1c7.setIcon(null);
                    J1c8.setIcon(null);
                    J1c9.setIcon(null);
                    J1c10.setIcon(null);
                    J1c11.setIcon(null); 
                    break;
                case 5:
                    J1c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(0))+".png")));
                    J1c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(1))+".png")));
                    J1c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(2))+".png")));
                    J1c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(3))+".png")));
                    J1c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(4))+".png")));
                    J1c6.setIcon(null);
                    J1c7.setIcon(null);
                    J1c8.setIcon(null);
                    J1c9.setIcon(null);
                    J1c10.setIcon(null);
                    J1c11.setIcon(null); 
                    break;
                case 4:
                    J1c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(0))+".png")));
                    J1c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(1))+".png")));
                    J1c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(2))+".png")));
                    J1c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(3))+".png")));
                    J1c5.setIcon(null);
                    J1c6.setIcon(null);
                    J1c7.setIcon(null);
                    J1c8.setIcon(null);
                    J1c9.setIcon(null);
                    J1c10.setIcon(null);
                    J1c11.setIcon(null); 
                    break;
                case 3:
                    J1c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(0))+".png")));
                    J1c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(1))+".png")));
                    J1c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(2))+".png")));
                    J1c4.setIcon(null);
                    J1c5.setIcon(null);
                    J1c6.setIcon(null);
                    J1c7.setIcon(null);
                    J1c8.setIcon(null);
                    J1c9.setIcon(null);
                    J1c10.setIcon(null);
                    J1c11.setIcon(null); 
                    break;
                case 2:
                    J1c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(0))+".png")));
                    J1c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(1))+".png")));
                    J1c3.setIcon(null);
                    J1c4.setIcon(null);
                    J1c5.setIcon(null);
                    J1c6.setIcon(null);
                    J1c7.setIcon(null);
                    J1c8.setIcon(null);
                    J1c9.setIcon(null);
                    J1c10.setIcon(null);
                    J1c11.setIcon(null);
                    break;
                case 1:
                    J1c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(0))+".png")));
                    J1c2.setIcon(null);
                    J1c3.setIcon(null);
                    J1c4.setIcon(null);
                    J1c5.setIcon(null);
                    J1c6.setIcon(null);
                    J1c7.setIcon(null);
                    J1c8.setIcon(null);
                    J1c9.setIcon(null);
                    J1c10.setIcon(null);
                    J1c11.setIcon(null);
                    break;
                case 0:
                    J1c1.setIcon(null);
                    J1c2.setIcon(null);
                    J1c3.setIcon(null);
                    J1c4.setIcon(null);
                    J1c5.setIcon(null);
                    J1c6.setIcon(null);
                    J1c7.setIcon(null);
                    J1c8.setIcon(null);
                    J1c9.setIcon(null);
                    J1c10.setIcon(null);
                    J1c11.setIcon(null);
                    break;
            }
        } else{
            switch (j.joueurDonneur().main().taille()) {
                case 11:
                    J2c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(0))+".png")));
                    J2c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(1))+".png")));
                    J2c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(2))+".png")));
                    J2c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(3))+".png")));
                    J2c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(4))+".png")));
                    J2c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(5))+".png")));
                    J2c7.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(6))+".png")));
                    J2c8.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(7))+".png")));
                    J2c9.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(8))+".png")));
                    J2c10.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(9))+".png")));
                    J2c11.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(10))+".png")));  
                    break;
                case 10:
                    J2c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(0))+".png")));
                    J2c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(1))+".png")));
                    J2c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(2))+".png")));
                    J2c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(3))+".png")));
                    J2c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(4))+".png")));
                    J2c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(5))+".png")));
                    J2c7.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(6))+".png")));
                    J2c8.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(7))+".png")));
                    J2c9.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(8))+".png")));
                    J2c10.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(9))+".png")));
                    J2c11.setIcon(null);  
                    break;
                case 9:
                    J2c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(0))+".png")));
                    J2c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(1))+".png")));
                    J2c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(2))+".png")));
                    J2c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(3))+".png")));
                    J2c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(4))+".png")));
                    J2c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(5))+".png")));
                    J2c7.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(6))+".png")));
                    J2c8.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(7))+".png")));
                    J2c9.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(8))+".png")));
                    J2c10.setIcon(null);
                    J2c11.setIcon(null);  
                    break;
                case 8:
                    J2c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(0))+".png")));
                    J2c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(1))+".png")));
                    J2c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(2))+".png")));
                    J2c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(3))+".png")));
                    J2c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(4))+".png")));
                    J2c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(5))+".png")));
                    J2c7.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(6))+".png")));
                    J2c8.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(7))+".png")));
                    J2c9.setIcon(null);
                    J2c10.setIcon(null);
                    J2c11.setIcon(null);  
                    break;
                case 7:
                    J2c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(0))+".png")));
                    J2c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(1))+".png")));
                    J2c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(2))+".png")));
                    J2c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(3))+".png")));
                    J2c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(4))+".png")));
                    J2c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(5))+".png")));
                    J2c7.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(6))+".png")));
                    J2c8.setIcon(null);
                    J2c9.setIcon(null);
                    J2c10.setIcon(null);
                    J2c11.setIcon(null);  
                    break;
                case 6:
                    J2c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(0))+".png")));
                    J2c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(1))+".png")));
                    J2c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(2))+".png")));
                    J2c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(3))+".png")));
                    J2c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(4))+".png")));
                    J2c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(5))+".png")));
                    J2c7.setIcon(null);
                    J2c8.setIcon(null);
                    J2c9.setIcon(null);
                    J2c10.setIcon(null);
                    J2c11.setIcon(null); 
                    break;
                case 5:
                    J2c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(0))+".png")));
                    J2c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(1))+".png")));
                    J2c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(2))+".png")));
                    J2c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(3))+".png")));
                    J2c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(4))+".png")));
                    J2c6.setIcon(null);
                    J2c7.setIcon(null);
                    J2c8.setIcon(null);
                    J2c9.setIcon(null);
                    J2c10.setIcon(null);
                    J2c11.setIcon(null); 
                    break;
                case 4:
                    J2c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(0))+".png")));
                    J2c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(1))+".png")));
                    J2c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(2))+".png")));
                    J2c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(3))+".png")));
                    J2c5.setIcon(null);
                    J2c6.setIcon(null);
                    J2c7.setIcon(null);
                    J2c8.setIcon(null);
                    J2c9.setIcon(null);
                    J2c10.setIcon(null);
                    J2c11.setIcon(null); 
                    break;
                case 3:
                    J2c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(0))+".png")));
                    J2c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(1))+".png")));
                    J2c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(2))+".png")));
                    J2c4.setIcon(null);
                    J2c5.setIcon(null);
                    J2c6.setIcon(null);
                    J2c7.setIcon(null);
                    J2c8.setIcon(null);
                    J2c9.setIcon(null);
                    J2c10.setIcon(null);
                    J2c11.setIcon(null); 
                    break;
                case 2:
                    J2c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(0))+".png")));
                    J2c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(1))+".png")));
                    J2c3.setIcon(null);
                    J2c4.setIcon(null);
                    J2c5.setIcon(null);
                    J2c6.setIcon(null);
                    J2c7.setIcon(null);
                    J2c8.setIcon(null);
                    J2c9.setIcon(null);
                    J2c10.setIcon(null);
                    J2c11.setIcon(null);
                    break;
                case 1:
                    J2c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(0))+".png")));
                    J2c2.setIcon(null);
                    J2c3.setIcon(null);
                    J2c4.setIcon(null);
                    J2c5.setIcon(null);
                    J2c6.setIcon(null);
                    J2c7.setIcon(null);
                    J2c8.setIcon(null);
                    J2c9.setIcon(null);
                    J2c10.setIcon(null);
                    J2c11.setIcon(null);
                    break;
                case 0:
                    J2c1.setIcon(null);
                    J2c2.setIcon(null);
                    J2c3.setIcon(null);
                    J2c4.setIcon(null);
                    J2c5.setIcon(null);
                    J2c6.setIcon(null);
                    J2c7.setIcon(null);
                    J2c8.setIcon(null);
                    J2c9.setIcon(null);
                    J2c10.setIcon(null);
                    J2c11.setIcon(null);
                    break;
            }
        }
    }
      
    public void afficherCarteReceveur(){
        if(j.quiRecois() == 1){
            switch (j.joueurReceveur().main().taille()) {
                case 11:
                    J1c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(0))+".png")));
                    J1c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(1))+".png")));
                    J1c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(2))+".png")));
                    J1c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(3))+".png")));
                    J1c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(4))+".png")));
                    J1c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(5))+".png")));
                    J1c7.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(6))+".png")));
                    J1c8.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(7))+".png")));
                    J1c9.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(8))+".png")));
                    J1c10.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(9))+".png")));
                    J1c11.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(10))+".png"))); 
                    break;
                case 10:
                    J1c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(0))+".png")));
                    J1c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(1))+".png")));
                    J1c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(2))+".png")));
                    J1c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(3))+".png")));
                    J1c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(4))+".png")));
                    J1c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(5))+".png")));
                    J1c7.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(6))+".png")));
                    J1c8.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(7))+".png")));
                    J1c9.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(8))+".png")));
                    J1c10.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(9))+".png")));
                    J1c11.setIcon(null);  
                    break;
                case 9:
                    J1c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(0))+".png")));
                    J1c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(1))+".png")));
                    J1c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(2))+".png")));
                    J1c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(3))+".png")));
                    J1c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(4))+".png")));
                    J1c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(5))+".png")));
                    J1c7.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(6))+".png")));
                    J1c8.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(7))+".png")));
                    J1c9.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(8))+".png")));
                    J1c10.setIcon(null);
                    J1c11.setIcon(null);  
                    break;
                case 8:
                    J1c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(0))+".png")));
                    J1c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(1))+".png")));
                    J1c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(2))+".png")));
                    J1c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(3))+".png")));
                    J1c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(4))+".png")));
                    J1c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(5))+".png")));
                    J1c7.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(6))+".png")));
                    J1c8.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(7))+".png")));
                    J1c9.setIcon(null);
                    J1c10.setIcon(null);
                    J1c11.setIcon(null);  
                    break;
                case 7:
                    J1c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(0))+".png")));
                    J1c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(1))+".png")));
                    J1c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(2))+".png")));
                    J1c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(3))+".png")));
                    J1c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(4))+".png")));
                    J1c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(5))+".png")));
                    J1c7.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(6))+".png")));
                    J1c8.setIcon(null);
                    J1c9.setIcon(null);
                    J1c10.setIcon(null);
                    J1c11.setIcon(null);  
                    break;
                case 6:
                    J1c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(0))+".png")));
                    J1c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(1))+".png")));
                    J1c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(2))+".png")));
                    J1c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(3))+".png")));
                    J1c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(4))+".png")));
                    J1c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(5))+".png")));
                    J1c7.setIcon(null);
                    J1c8.setIcon(null);
                    J1c9.setIcon(null);
                    J1c10.setIcon(null);
                    J1c11.setIcon(null); 
                    break;
                case 5:
                    J1c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(0))+".png")));
                    J1c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(1))+".png")));
                    J1c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(2))+".png")));
                    J1c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(3))+".png")));
                    J1c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(4))+".png")));
                    J1c6.setIcon(null);
                    J1c7.setIcon(null);
                    J1c8.setIcon(null);
                    J1c9.setIcon(null);
                    J1c10.setIcon(null);
                    J1c11.setIcon(null); 
                    break;
                case 4:
                    J1c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(0))+".png")));
                    J1c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(1))+".png")));
                    J1c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(2))+".png")));
                    J1c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(3))+".png")));
                    J1c5.setIcon(null);
                    J1c6.setIcon(null);
                    J1c7.setIcon(null);
                    J1c8.setIcon(null);
                    J1c9.setIcon(null);
                    J1c10.setIcon(null);
                    J1c11.setIcon(null); 
                    break;
                case 3:
                    J1c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(0))+".png")));
                    J1c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(1))+".png")));
                    J1c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(2))+".png")));
                    J1c4.setIcon(null);
                    J1c5.setIcon(null);
                    J1c6.setIcon(null);
                    J1c7.setIcon(null);
                    J1c8.setIcon(null);
                    J1c9.setIcon(null);
                    J1c10.setIcon(null);
                    J1c11.setIcon(null); 
                    break;
                case 2:
                    J1c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(0))+".png")));
                    J1c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(1))+".png")));
                    J1c3.setIcon(null);
                    J1c4.setIcon(null);
                    J1c5.setIcon(null);
                    J1c6.setIcon(null);
                    J1c7.setIcon(null);
                    J1c8.setIcon(null);
                    J1c9.setIcon(null);
                    J1c10.setIcon(null);
                    J1c11.setIcon(null);
                    break;
                case 1:
                    J1c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(0))+".png")));
                    J1c2.setIcon(null);
                    J1c3.setIcon(null);
                    J1c4.setIcon(null);
                    J1c5.setIcon(null);
                    J1c6.setIcon(null);
                    J1c7.setIcon(null);
                    J1c8.setIcon(null);
                    J1c9.setIcon(null);
                    J1c10.setIcon(null);
                    J1c11.setIcon(null);
                    break;
                case 0:
                    J1c1.setIcon(null);
                    J1c2.setIcon(null);
                    J1c3.setIcon(null);
                    J1c4.setIcon(null);
                    J1c5.setIcon(null);
                    J1c6.setIcon(null);
                    J1c7.setIcon(null);
                    J1c8.setIcon(null);
                    J1c9.setIcon(null);
                    J1c10.setIcon(null);
                    J1c11.setIcon(null);
                    break;
            }
        } else{
            switch (j.joueurReceveur().main().taille()) {
                case 11:
                    J2c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(0))+".png")));
                    J2c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(1))+".png")));
                    J2c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(2))+".png")));
                    J2c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(3))+".png")));
                    J2c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(4))+".png")));
                    J2c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(5))+".png")));
                    J2c7.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(6))+".png")));
                    J2c8.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(7))+".png")));
                    J2c9.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(8))+".png")));
                    J2c10.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(9))+".png")));
                    J2c11.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(10))+".png")));
                    break;
                case 10:
                    J2c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(0))+".png")));
                    J2c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(1))+".png")));
                    J2c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(2))+".png")));
                    J2c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(3))+".png")));
                    J2c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(4))+".png")));
                    J2c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(5))+".png")));
                    J2c7.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(6))+".png")));
                    J2c8.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(7))+".png")));
                    J2c9.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(8))+".png")));
                    J2c10.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(9))+".png")));
                    J2c11.setIcon(null);  
                    break;
                case 9:
                    J2c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(0))+".png")));
                    J2c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(1))+".png")));
                    J2c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(2))+".png")));
                    J2c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(3))+".png")));
                    J2c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(4))+".png")));
                    J2c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(5))+".png")));
                    J2c7.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(6))+".png")));
                    J2c8.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(7))+".png")));
                    J2c9.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(8))+".png")));
                    J2c10.setIcon(null);
                    J2c11.setIcon(null);  
                    break;
                case 8:
                    J2c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(0))+".png")));
                    J2c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(1))+".png")));
                    J2c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(2))+".png")));
                    J2c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(3))+".png")));
                    J2c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(4))+".png")));
                    J2c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(5))+".png")));
                    J2c7.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(6))+".png")));
                    J2c8.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(7))+".png")));
                    J2c9.setIcon(null);
                    J2c10.setIcon(null);
                    J2c11.setIcon(null);  
                    break;
                case 7:
                    J2c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(0))+".png")));
                    J2c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(1))+".png")));
                    J2c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(2))+".png")));
                    J2c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(3))+".png")));
                    J2c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(4))+".png")));
                    J2c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(5))+".png")));
                    J2c7.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(6))+".png")));
                    J2c8.setIcon(null);
                    J2c9.setIcon(null);
                    J2c10.setIcon(null);
                    J2c11.setIcon(null);  
                    break;
                case 6:
                    J2c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(0))+".png")));
                    J2c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(1))+".png")));
                    J2c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(2))+".png")));
                    J2c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(3))+".png")));
                    J2c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(4))+".png")));
                    J2c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(5))+".png")));
                    J2c7.setIcon(null);
                    J2c8.setIcon(null);
                    J2c9.setIcon(null);
                    J2c10.setIcon(null);
                    J2c11.setIcon(null); 
                    break;
                case 5:
                    J2c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(0))+".png")));
                    J2c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(1))+".png")));
                    J2c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(2))+".png")));
                    J2c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(3))+".png")));
                    J2c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(4))+".png")));
                    J2c6.setIcon(null);
                    J2c7.setIcon(null);
                    J2c8.setIcon(null);
                    J2c9.setIcon(null);
                    J2c10.setIcon(null);
                    J2c11.setIcon(null); 
                    break;
                case 4:
                    J2c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(0))+".png")));
                    J2c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(1))+".png")));
                    J2c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(2))+".png")));
                    J2c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(3))+".png")));
                    J2c5.setIcon(null);
                    J2c6.setIcon(null);
                    J2c7.setIcon(null);
                    J2c8.setIcon(null);
                    J2c9.setIcon(null);
                    J2c10.setIcon(null);
                    J2c11.setIcon(null); 
                    break;
                case 3:
                    J2c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(0))+".png")));
                    J2c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(1))+".png")));
                    J2c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(2))+".png")));
                    J2c4.setIcon(null);
                    J2c5.setIcon(null);
                    J2c6.setIcon(null);
                    J2c7.setIcon(null);
                    J2c8.setIcon(null);
                    J2c9.setIcon(null);
                    J2c10.setIcon(null);
                    J2c11.setIcon(null); 
                    break;
                case 2:
                    J2c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(0))+".png")));
                    J2c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(1))+".png")));
                    J2c3.setIcon(null);
                    J2c4.setIcon(null);
                    J2c5.setIcon(null);
                    J2c6.setIcon(null);
                    J2c7.setIcon(null);
                    J2c8.setIcon(null);
                    J2c9.setIcon(null);
                    J2c10.setIcon(null);
                    J2c11.setIcon(null);
                    break;
                case 1:
                    J2c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(0))+".png")));
                    J2c2.setIcon(null);
                    J2c3.setIcon(null);
                    J2c4.setIcon(null);
                    J2c5.setIcon(null);
                    J2c6.setIcon(null);
                    J2c7.setIcon(null);
                    J2c8.setIcon(null);
                    J2c9.setIcon(null);
                    J2c10.setIcon(null);
                    J2c11.setIcon(null);
                    break;
                case 0:
                    J2c1.setIcon(null);
                    J2c2.setIcon(null);
                    J2c3.setIcon(null);
                    J2c4.setIcon(null);
                    J2c5.setIcon(null);
                    J2c6.setIcon(null);
                    J2c7.setIcon(null);
                    J2c8.setIcon(null);
                    J2c9.setIcon(null);
                    J2c10.setIcon(null);
                    J2c11.setIcon(null);
                    break;
            }
        }
    }
    
    
    public void cacherCarteJ(){
        if(j.quiJoue() == 2){
            dosj1 = true;
            switch (j.joueurReceveur().main().taille()) {
                case 11:
                    J1c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c7.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c8.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c9.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c10.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c11.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    break;
                case 10:
                    J1c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c7.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c8.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c9.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c10.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    break;
                case 9:
                    J1c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c7.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c8.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c9.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    break;
                case 8:
                    J1c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c7.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c8.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    break;
                case 7:
                    J1c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c7.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));  
                    break;
                case 6:
                    J1c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    break;
                case 5:
                    J1c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    break;
                case 4:
                    J1c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    break;
                case 3:
                    J1c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    break;
                case 2:
                    J1c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J1c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    break;
                case 1:
                    J1c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    break;
            }
        } else {
            dosj2 = true;
            switch (j.joueurReceveur().main().taille()) {
                case 11:
                    J2c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c7.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c8.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c9.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c10.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c11.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    break;
                case 10:
                    J2c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c7.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c8.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c9.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c10.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    break;
                case 9:
                    J2c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c7.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c8.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c9.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    break;
                case 8:
                    J2c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c7.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c8.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    break;
                case 7:
                    J2c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c7.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));  
                    break;
                case 6:
                    J2c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    break;
                case 5:
                    J2c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    break;
                case 4:
                    J2c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    break;
                case 3:
                    J2c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    break;
                case 2:
                    J2c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    J2c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    break;
                case 1:
                    J2c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/dos.png")));
                    break;
            }
        }
    }
    
    
    public void afficherCarteG(){
        if(j.quiDonne() == 1){
            if(j.quiGagnetour() == 1){
                J1c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(0))+".png")));
                J1c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(1))+".png")));
                J1c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(2))+".png")));
                J1c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(3))+".png")));
                J1c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(4))+".png")));
                J1c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(5))+".png")));
                J1c7.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(6))+".png")));
                J1c8.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(7))+".png")));
                J1c9.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(8))+".png")));
                J1c10.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(9))+".png")));
                J1c11.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(10))+".png")));  
            } else {
                J2c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(0))+".png")));
                J2c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(1))+".png")));
                J2c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(2))+".png")));
                J2c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(3))+".png")));
                J2c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(4))+".png")));
                J2c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(5))+".png")));
                J2c7.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(6))+".png")));
                J2c8.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(7))+".png")));
                J2c9.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(8))+".png")));
                J2c10.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(9))+".png")));
                J2c11.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(10))+".png")));
            }
        } else {
            if(j.quiGagnetour() == 2){
                J2c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(0))+".png")));
                J2c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(1))+".png")));
                J2c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(2))+".png")));
                J2c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(3))+".png")));
                J2c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(4))+".png")));
                J2c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(5))+".png")));
                J2c7.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(6))+".png")));
                J2c8.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(7))+".png")));
                J2c9.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(8))+".png")));
                J2c10.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(9))+".png")));
                J2c11.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(10))+".png")));  
            } else {
                J1c1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(0))+".png")));
                J1c2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(1))+".png")));
                J1c3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(2))+".png")));
                J1c4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(3))+".png")));
                J1c5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(4))+".png")));
                J1c6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(5))+".png")));
                J1c7.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(6))+".png")));
                J1c8.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(7))+".png")));
                J1c9.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(8))+".png")));
                J1c10.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(9))+".png")));
                J1c11.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(10))+".png")));
            }
        }
    }
    
    
    public void afficherPile() {
    	Pile();
        Carte[] carteVisible = j.cartevisiblepioche();
        if(!j.pilevide(1)){
            crtpile1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(carteVisible[0])+".png")));
        } else {
            crtpile1.setIcon(null);
        }
        if(!j.pilevide(2)){
            crtpile2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(carteVisible[1])+".png")));
        } else {
            crtpile2.setIcon(null);
        }
        if(!j.pilevide(3)){
            crtpile3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(carteVisible[2])+".png")));
        } else {
            crtpile3.setIcon(null);
        }
        if(!j.pilevide(4)){
            crtpile4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(carteVisible[3])+".png")));
        } else {
            crtpile4.setIcon(null);
        }
        if(!j.pilevide(5)){
            crtpile5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(carteVisible[4])+".png")));
        } else {
            crtpile5.setIcon(null);
        }
        if(!j.pilevide(6)){
            crtpile6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(carteVisible[5])+".png"))); 
        } else {
            crtpile6.setIcon(null);
        }
    }
    
    
    public void afficherAtout(JLabel l) {
        switch (j.atout()) {
            case 1:
                l.setIcon(new ImageIcon(getClass().getResource("/images/ATOUT_TREFLE.png")));
                break;
            case 2:
                l.setIcon(new ImageIcon(getClass().getResource("/images/ATOUT_CARREAU.png")));
                break;
            case 3:
                l.setIcon(new ImageIcon(getClass().getResource("/images/ATOUT_COEUR.png")));
                break;
            case 4:
                l.setIcon(new ImageIcon(getClass().getResource("/images/ATOUT_PIQUE.png")));
                break;
            default:
                l.setIcon(new ImageIcon(getClass().getResource("")));
                l.setText("Pas d'atout");
                break;
        }
    }
    
    
    public void afficherGagnantPlis() {
        if (!j.debutpartie){
            if(j.quiGagnetour() == 1){
                quigagne.setText(nomJ1.getText()+" a gagné le pli");
                ptJ1.setText(Integer.toString(j.scorePartiej1()));
                pli1++;
                if(pli1<5){
                    J1pli.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_"+pli1+".png")));
                }
                J1nbrpli.setText(Integer.toString(j.scorePartiej1()));
            } else {
                quigagne.setText(nomJ2.getText()+" a gagné le pli");
                ptJ2.setText(Integer.toString(j.scorePartiej2()));
                pli2++;
                if(pli2<5){
                    J2pli.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_"+pli2+".png")));
                }
                J2nbrpli.setText(Integer.toString(j.scorePartiej2()));
            }
        }
    }
    
    
    private void afficherGagnantPlisav() {
        if (!j.debutpartie){
            if(j.quiGagnetourav() == 1){
                ptJ1.setText(Integer.toString(j.scorePartiej1()));
                quigagne.setText(nomJ1.getText()+" a gagné le pli");
                J1nbrpli.setText(Integer.toString(j.scorePartiej1()));
            } else{
                ptJ2.setText(Integer.toString(j.scorePartiej2()));
                quigagne.setText(nomJ2.getText()+" a gagné le pli");
                J2nbrpli.setText(Integer.toString(j.scorePartiej2()));
            }
        }
    }
        
    
    private void afficherGagnantManche() {
        if (j.quiGagneManche() != 0){
            if(j.quiGagneManche() == 1){
                quigagne.setText(nomJ1.getText()+" a gagné la manche");
            } else {
                quigagne.setText(nomJ2.getText()+" a gagné la manche");
            }
        } else{
            quigagne.setText("Egalité! Pas de gagnant");
        }
    }

    
    private void afficherGagnantPartie(){
        finPartieFrame();
        partief.setLocationRelativeTo(null);
        if (j.quiGagnePartie() != 0){
            trophy.setIcon(new ImageIcon(getClass().getResource("/images/trophe.png")));
            if(j.quiGagnePartie() == 1){
                winner.setText(nomJ1.getText() + " a gagné cette partie");
                ptJ1.setText(Integer.toString(j.scorePartiej1()));
                J1nbrpli.setText(Integer.toString(j.scorePartiej1()));
            } else {
                winner.setText(nomJ2.getText() + " a gagné cette partie");
                ptJ2.setText(Integer.toString(j.scorePartiej2()));
                J2nbrpli.setText(Integer.toString(j.scorePartiej2()));
            }
            if(!mode.equals("Point(s)")){
                scorej1.setText("Points "+nomJ1.getText()+ " : " +j.scorePartiej1());
                scorej2.setText("Points "+nomJ2.getText()+ " : " +j.scorePartiej2());
            } else {
                scorej1.setText("Manches "+nomJ1.getText()+ " : " +j.scoremanchej1());
                scorej2.setText("Manches "+nomJ2.getText()+ " : " +j.scoremanchej1());
            }
        } else{
            winner.setText("Egalité! Pas de gagnant pour cette partie");
        }
    }
    
    
    @Override
    public void Configuration() {
        InterfaceGraphique i = new InterfaceGraphique(j,c);
        i.Configuration();
    }
            
    
    
    @Override
    public void jouePartie() {
        time = new Timer(1000, this);
        c.fixerInterfaceUtilisateur(this);
        if (j.estIA(j.quiJoue()) && !j.manchefini()) {
            if (indiceC == -1 || indiceC == -2) {
                reflechie=0;
                nb = indiceC;
                switch (c.commande(nb)) {
                    case 0:
                        break;
                    case 1  :
                        action.setText("Vous n'avez pas choisi de");
                        action2.setText("carte sur l'une des piles");
                        break; 
                    case 2:
                        action.setText("Vous n'avez pas choisi de carte");
                        break; 
                    case 4:
                        if((j.quiRecois() == 1 && dosj1 != true) || (j.quiRecois() == 2 && dosj2 != true)){
                            afficherCarteReceveur();
                        } else {
                            cacherCarteJ();
                        }
                        if(j.quiRecois() == 1){
                            carteJ1.setIcon(null);
                        } else{
                            carteJ2.setIcon(null);
                        }
                        action.setText("Vous devez jouer la couleur");
                        action2.setText(j.cartePremCouleur());
                        break; 
                }
                indiceC=-10;
                metAJour();
            } else {
                if(reflechie!=1 && ref==0){
                nb = j.getCoupIA(j.quiJoue());
                time.start();}
            }
            /*while (System.currentTimeMillis() - timer < delayIA) {
               // A "threder" pour ne pas freeze l'interface graphique
            }*/
        } else {
            nb = indiceC;
            switch (c.commande(nb)) {
                case 0:
                    break;
                case 1  :
                    action.setText("Vous n'avez pas choisi de");
                    action2.setText("carte sur l'une des piles");
                    break; 
                case 2:
                    action.setText("Vous n'avez pas choisi de carte");
                    break; 
                case 4:
                    if((j.quiRecois() == 1 && dosj1 != true) || (j.quiRecois() == 2 && dosj2 != true)){
                        afficherCarteReceveur();
                    } else {
                        cacherCarteJ();
                    }
                    if(j.quiRecois() == 1){
                        carteJ1.setIcon(null);
                    } else{
                        carteJ2.setIcon(null);
                    }
                    action.setText("Vous devez jouer la couleur");
                    action2.setText(j.cartePremCouleur());
                    break; 
            }
            metAJour();
        }
    }
        
    
    public void debuter(){
        if(j.phase() == 2){
            afficherGagnantPlisav();
        }
        action.getText();
        action2.getText();
        quigagne.getText();
        ptJ1.setText(Integer.toString(j.scorePartiej1()));
        if(j.getpause() == true){
            pause.setSelected(true);
        }
        if(j.scorePartiej1() != 0){
            if(j.scorePartiej1() == 1){
                J1pli.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_2.png")));
            } else if(j.scorePartiej1() >= 2){
                J1pli.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_4.png")));
            } 
        } else {
            J1pli.setIcon(null);
        }
        ptJ2.setText(Integer.toString(j.scorePartiej2()));
        if(j.scorePartiej2() != 0){
            if(j.scorePartiej2() == 1){
                J2pli.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_2.png")));
            } else if(j.scorePartiej1() >= 2){
                J2pli.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_4.png")));
            } 
        } else {
            J2pli.setIcon(null);
        }
        J1nbrpli.setText(Integer.toString(j.scorePartiej1()));
        J2nbrpli.setText(Integer.toString(j.scorePartiej2()));
        afficherCarteDonneur();
        afficherCarteReceveur();
        Pile();
        afficherPile();
        afficherAtout(atout);
        if (j.quiDonne() == 1) {
            tourj1 = true;
            tourJ1.setVisible(true);
            tourJ2.setVisible(false);
            quitour.setText(nomJ1.getText());
            hide2.setEnabled(true);
        } else {
            tourj2 = true;
            tourJ1.setVisible(false);
            tourJ2.setVisible(true);
            quitour.setText(nomJ2.getText());
            hide.setEnabled(true);
        }
        if(j.estIA(j.quiJoue())){
            jouePartie();
        }
    }
    
    
    public void metAJour() {
        if (!j.partifini() && (!j.tourfini() || j.debutpartie)) {
            switch (j.phasetourterm()) {
                case 0:
                    if(j.phase() == 2){
                        afficherGagnantPlisav();
                    }
                    action.setText("Choisissez une carte");
                    afficherCarteDonneur();
                    afficherCarteReceveur();
                    Pile();
                    afficherPile();
                    afficherAtout(atout);
                    if (j.quiDonne() == 1) {
                        tourj1 = true;
                        tourJ1.setVisible(true);
                        tourJ2.setVisible(false);
                        quitour.setText(nomJ1.getText());
                        hide2.setEnabled(true);
                    } else {
                        tourj2 = true;
                        tourJ1.setVisible(false);
                        tourJ2.setVisible(true);
                        quitour.setText(nomJ2.getText());
                        hide.setEnabled(true);
                    }
                    if(j.estIA(j.quiJoue())&& !j.partifini()){
                        jouePartie();
                    }
                    //piocher = false;
                    break;
                case 1:
                    afficherPile();
                    if (j.quiDonne() == 2) {
                        tourj2 = false;
                        tourj1 = true;
                        tourJ1.setVisible(true);
                        tourJ2.setVisible(false);
                        quitour.setText(nomJ1.getText());
                        hide.setEnabled(false);
                        hide2.setEnabled(true);
                    } else {
                        tourj1 = false;
                        tourj2 = true;
                        tourJ1.setVisible(false);
                        tourJ2.setVisible(true);
                        quitour.setText(nomJ2.getText());
                        hide2.setEnabled(false);
                        hide.setEnabled(true);
                    }
                    if(j.estIA(j.quiJoue()) && !j.partifini()){
                        jouePartie();
                    }
                    //piocher = false;
                    break;
                case 2:
                    action.setText("Piochez une carte dans une");
                    action2.setText("des piles");
                    afficherGagnantPlis();
                    afficherPile();
                    if (j.quiGagnetour() == 1) {
                        tourj1 = true;
                        tourj2 = false;
                        tourJ1.setVisible(true);
                        tourJ2.setVisible(false);
                        quitour.setText(nomJ1.getText());
                        hide.setEnabled(false);
                        hide2.setEnabled(true);
                    } else {
                        tourj1 = false;
                        tourj2 = true;
                        tourJ1.setVisible(false);
                        tourJ2.setVisible(true);
                        quitour.setText(nomJ2.getText());
                        hide.setEnabled(true);
                        hide2.setEnabled(false);
                    }
                    //piocher = !(j.pilevide(1) && j.pilevide(2) && j.pilevide(3) && j.pilevide(4) && j.pilevide(5) && j.pilevide(6));
                    if(j.estIA(j.quiJoue())&& !j.partifini()){
                        jouePartie();
                    }
                    break;
                case 3:
                    quigagne.setText("");
                    carteJ1.setIcon(null);
                    carteJ2.setIcon(null);
                    afficherPile();
                    if (j.quiGagnetour() == 2) {
                        tourj1 = true;
                        tourj2 = false;
                        tourJ1.setVisible(true);
                        tourJ2.setVisible(false);
                        quitour.setText(nomJ1.getText());
                        hide.setEnabled(false);
                        hide2.setEnabled(true);
                    } else {
                        tourj1 = false;
                        tourj2 = true;
                        tourJ1.setVisible(false);
                        tourJ2.setVisible(true);
                        quitour.setText(nomJ2.getText());
                        hide.setEnabled(true);
                        hide2.setEnabled(false);
                    }
                    if(j.estIA(j.quiJoue())&& !j.partifini()){
                        jouePartie();
                    }
                    if((tourj1 != true && dosj1 != true) || (tourj2 != true && dosj2 != true)){
                        afficherCarteG();
                    } else {
                        cacherCarteJ();
                    }
                    //piocher = !(j.pilevide(1) && j.pilevide(2) && j.pilevide(3) && j.pilevide(4) && j.pilevide(5) && j.pilevide(6));
                    break;
            }
            piocher = j.phasetourterm()==2 || j.phasetourterm()==3;
        } else {
            if (!j.partifini()) {
                if (j.phase() == 2 && !j.manchefini()) {
                    afficherGagnantPlisav();
                    afficherCarteDonneur();
                }
                if (j.manchefini()) {
                    afficherGagnantPlis();
                    afficherGagnantManche();
                    contin.setEnabled(true);
                    action.setText("Pour continuer appuyez sur");
                    action2.setText("CONTINUER");
                } else {
                    carteJ1.setIcon(null);
                    carteJ2.setIcon(null);
                    action.setText("Choisissez une carte");
                    action2.setText("");
                    afficherPile();
                    if((j.quiRecois() == 1 && dosj1 != true) || (j.quiRecois() == 2 && dosj2 != true)){
                        afficherCarteReceveur();
                    } else {
                        cacherCarteJ();
                    }
                    piocher = false;
                    if(j.quiDonne()== 1){
                        tourj1 = true;
                        tourj2 = false;
                        tourJ1.setVisible(true);
                        tourJ2.setVisible(false);
                        quitour.setText(nomJ1.getText());
                        hide.setEnabled(false);
                        hide2.setEnabled(true);
                    } else {
                        tourj1 = false;
                        tourj2 = true;
                        tourJ1.setVisible(false);
                        tourJ2.setVisible(true);
                        quitour.setText(nomJ2.getText());
                        hide.setEnabled(true);
                        hide2.setEnabled(false);
                    }
                    if(j.estIA(j.quiJoue())&& !j.partifini()){
                        jouePartie();
                    }
                }
            } else {
                afficherGagnantManche();
                afficherGagnantPartie();
                tourj1 = true;
                tourj2 = true;
            }
        }
    }   
                
   
    private void apercuCarte(int x, int y, JLabel l){
        l.setLocation(x, y);
    }
    
    
    private void deplaceCarte(JLabel carte, JLabel dest){
       dest.setIcon(carte.getIcon());
       carte.setIcon(null);
    }
    
    private void deplaceCarteJ(JLabel carte, int i, JLabel dest){
        if(j.quiJoue() == 1){
            if(j.quiDonne() == 1){
                switch(i){
                    case 0:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(0))+".png")));
                        carte.setIcon(null);
                        break;
                    case 1:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(1))+".png")));
                        carte.setIcon(null);
                        break;
                    case 2:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(2))+".png")));
                        carte.setIcon(null);
                        break;
                    case 3:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(3))+".png")));
                        carte.setIcon(null);
                        break;
                    case 4:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(4))+".png")));
                        carte.setIcon(null);
                        break;
                    case 5:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(5))+".png")));
                        carte.setIcon(null);
                        break;
                    case 6:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(6))+".png")));
                        carte.setIcon(null);
                        break;
                    case 7:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(7))+".png")));
                        carte.setIcon(null);
                        break;
                    case 8:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(8))+".png")));
                        carte.setIcon(null);
                        break;
                    case 9:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(9))+".png")));
                        carte.setIcon(null);
                        break;
                    case 10:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(10))+".png")));
                        carte.setIcon(null);
                        break;
                }
            } else {
                switch(i){
                    case 0:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(0))+".png")));
                        carte.setIcon(null);
                        break;
                    case 1:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(1))+".png")));
                        carte.setIcon(null);
                        break;
                    case 2:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(2))+".png")));
                        carte.setIcon(null);
                        break;
                    case 3:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(3))+".png")));
                        carte.setIcon(null);
                        break;
                    case 4:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(4))+".png")));
                        carte.setIcon(null);
                        break;
                    case 5:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(5))+".png")));
                        carte.setIcon(null);
                        break;
                    case 6:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(6))+".png")));
                        carte.setIcon(null);
                        break;
                    case 7:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(7))+".png")));
                        carte.setIcon(null);
                        break;
                    case 8:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(8))+".png")));
                        carte.setIcon(null);
                        break;
                    case 9:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(9))+".png")));
                        carte.setIcon(null);
                        break;
                    case 10:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(10))+".png")));
                        carte.setIcon(null);
                        break;
                }
            }
        } else {
            if(j.quiDonne() == 2){
                switch(i){
                    case 0:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(0))+".png")));
                        carte.setIcon(null);
                        break;
                    case 1:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(1))+".png")));
                        carte.setIcon(null);
                        break;
                    case 2:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(2))+".png")));
                        carte.setIcon(null);
                        break;
                    case 3:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(3))+".png")));
                        carte.setIcon(null);
                        break;
                    case 4:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(4))+".png")));
                        carte.setIcon(null);
                        break;
                    case 5:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(5))+".png")));
                        carte.setIcon(null);
                        break;
                    case 6:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(6))+".png")));
                        carte.setIcon(null);
                        break;
                    case 7:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(7))+".png")));
                        carte.setIcon(null);
                        break;
                    case 8:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(8))+".png")));
                        carte.setIcon(null);
                        break;
                    case 9:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(9))+".png")));
                        carte.setIcon(null);
                        break;
                    case 10:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurDonneur().main().carte(10))+".png")));
                        carte.setIcon(null);
                        break;
                }
            } else {
                switch(i){
                    case 0:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(0))+".png")));
                        carte.setIcon(null);
                        break;
                    case 1:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(1))+".png")));
                        carte.setIcon(null);
                        break;
                    case 2:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(2))+".png")));
                        carte.setIcon(null);
                        break;
                    case 3:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(3))+".png")));
                        carte.setIcon(null);
                        break;
                    case 4:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(4))+".png")));
                        carte.setIcon(null);
                        break;
                    case 5:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(5))+".png")));
                        carte.setIcon(null);
                        break;
                    case 6:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(6))+".png")));
                        carte.setIcon(null);
                        break;
                    case 7:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(7))+".png")));
                        carte.setIcon(null);
                        break;
                    case 8:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(8))+".png")));
                        carte.setIcon(null);
                        break;
                    case 9:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(9))+".png")));
                        carte.setIcon(null);
                        break;
                    case 10:
                        dest.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(j.joueurReceveur().main().carte(10))+".png")));
                        carte.setIcon(null);
                        break;
                }
            }
        }
   }
   
    void peutJouerCarte(int i){
        if(j.quiJoue() == 1){
            if(j.JouableSec(i) == true){
                switch(i){
                    case 0:
                        apercuCarte(250, 580, J1c1);
                        break;
                    case 1:
                        apercuCarte(330, 580, J1c2);
                        break;
                    case 2:
                        apercuCarte(400, 580, J1c3);
                        break;
                    case 3:
                        apercuCarte(470, 580, J1c4);
                        break;
                    case 4:
                        apercuCarte(540, 580, J1c5);
                        break;
                    case 5:
                        apercuCarte(620, 580, J1c6);
                        break;
                    case 6:
                        apercuCarte(690, 580, J1c7);
                        break;
                    case 7:
                        apercuCarte(770, 580, J1c8);
                        break;
                    case 8:
                        apercuCarte(840, 580, J1c9);
                        break;
                    case 9:
                        apercuCarte(920, 580, J1c10);
                        break;
                    case 10:
                        apercuCarte(990, 580, J1c11);
                        break;
                }
            }
        } else {
            if(j.JouableSec(i) == true){
                switch(i){
                    case 0:
                        apercuCarte(240, 60, J2c1);
                        break;
                    case 1:
                        apercuCarte(320, 60, J2c2);
                        break;
                    case 2:
                        apercuCarte(390, 60, J2c3);
                        break;
                    case 3:
                        apercuCarte(460, 60, J2c4);
                        break;
                    case 4:
                        apercuCarte(530, 60, J2c5);
                        break;
                    case 5:
                        apercuCarte(610, 60, J2c6);
                        break;
                    case 6:
                        apercuCarte(680, 60, J2c7);
                        break;
                    case 7:
                        apercuCarte(760, 60, J2c8);
                        break;
                    case 8:
                        apercuCarte(830, 60, J2c9);
                        break;
                    case 9:
                        apercuCarte(910, 60, J2c10);
                        break;
                    case 10:
                        apercuCarte(980, 60, J2c11);
                        break;
                }
            }
        }
    }
    
    private void actionCartePileIA(int i){
        switch(i){
            case 1:
                p1--;
                if(p1>0){
                    pile1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_"+p1+".png")));
                } else {
                    if(p1 == 0){
                        pile1.setIcon(null);
                    }
                }
                break;
            case 2:
                p2--;
                if(p2>0){
                    pile2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_"+p2+".png")));
                } else {
                    if(p2 == 0){
                        pile2.setIcon(null);
                    }
                }
                break;
            case 3:
                p3--;
                if(p3>0){
                    pile3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_"+p3+".png")));
                } else {
                    if(p3 == 0){
                        pile3.setIcon(null);
                    }
                }
                break;
            case 4:
                p4--;
                if(p4>0){
                    pile4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_"+p4+".png")));
                } else {
                    if(p4 == 0){
                        pile4.setIcon(null);
                    }
                }
                break;
            case 5:
                p5--;
                if(p5>0){
                    pile5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_"+p5+".png")));
                } else {
                    if(p5 == 0){
                        pile5.setIcon(null);
                    }
                }
                break;
            case 6:
                p6--;
                if(p6>0){
                    pile6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_"+p6+".png")));
                } else {
                    if(p6 == 0){
                        pile6.setIcon(null);
                    }
                }
                break;
        } 
    }
    
    private void actionCartePile(int i, boolean pioche){
        if(pioche == true){
            switch(i){
                case 1:
                    indiceC = 1;
                    p1--;
                    if(p1>0){
                        pile1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_"+p1+".png")));
                    } else {
                        if(p1 == 0){
                            pile1.setIcon(null);
                        }
                    }
                    jouePartie();
                    break;
                case 2:
                    indiceC = 2;
                    p2--;
                    if(p2>0){
                        pile2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_"+p2+".png")));
                    } else {
                        if(p2 == 0){
                            pile2.setIcon(null);
                        }
                    }
                    jouePartie();
                    break;
                case 3:
                    indiceC = 3;
                    p3--;
                    if(p3>0){
                        pile3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_"+p3+".png")));
                    } else {
                        if(p3 == 0){
                            pile3.setIcon(null);
                        }
                    }
                    jouePartie();
                    break;
                case 4:
                    indiceC = 4;
                    p4--;
                    if(p4>0){
                        pile4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_"+p4+".png")));
                    } else {
                        if(p4 == 0){
                            pile4.setIcon(null);
                        }
                    }
                    jouePartie();
                    break;
                case 5:
                    indiceC = 5;
                    p5--;
                    if(p5>0){
                        pile5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_"+p5+".png")));
                    } else {
                        if(p5 == 0){
                            pile5.setIcon(null);
                        }
                    }
                    jouePartie();
                    break;
                case 6:
                    indiceC = 6;
                    p6--;
                    if(p6>0){
                        pile6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_"+p6+".png")));
                    } else {
                        if(p6 == 0){
                            pile6.setIcon(null);
                        }
                    }
                    jouePartie();
                    break;
                    
            } 
        }
    }
    
    private void actionCarte(JLabel carte, JLabel dest, int i, boolean tour, boolean pioche, boolean dosc){
        if(tour != true && pioche != true){
            indiceC = i;
            if(dest == carteJ1){
                if(dosc == true){
                    deplaceCarteJ(carte, i, dest);
                } else {
                    deplaceCarte(carte, dest);
                }
            } else {
                if(dosc == true){
                    deplaceCarteJ(carte, i, dest);
                } else {
                    deplaceCarte(carte, dest);
                }
            }
            jouePartie();
        }
    }
    
    
    public void actionCarteIA(int nb){
        if(j.quiJoue() == 1){
            switch(nb){
                case 0:
                    deplaceCarteJ(J1c1, 0, carteJ1);
                    break;
                case 1:
                    deplaceCarteJ(J1c2, 1, carteJ1);
                    break;
                case 2:
                    deplaceCarteJ(J1c3, 2, carteJ1);
                    break;
                case 3:
                    deplaceCarteJ(J1c4, 3, carteJ1);
                    break; 
                case 4:
                    deplaceCarteJ(J1c5, 4, carteJ1);
                    break;
                case 5:
                    deplaceCarteJ(J1c6, 5, carteJ1);
                    break;
                case 6:
                    deplaceCarteJ(J1c7, 6, carteJ1);
                    break; 
                case 7:
                    deplaceCarteJ(J1c8, 7, carteJ1);
                    break;
                case 8:
                    deplaceCarteJ(J1c9, 8, carteJ1);
                    break;
                case 9:
                    deplaceCarteJ(J1c10, 9, carteJ1);
                    break; 
                case 10:
                    deplaceCarteJ(J1c11, 10, carteJ1);
                    break;
            }
        } else {
            switch(nb){
                case 0:
                    deplaceCarteJ(J2c1, 0, carteJ2);
                    break;
                case 1:
                    deplaceCarteJ(J2c2, 1, carteJ2);
                    break;
                case 2:
                    deplaceCarteJ(J2c3, 2, carteJ2);
                    break;
                case 3:
                    deplaceCarteJ(J2c4, 3, carteJ2);
                    break; 
                case 4:
                    deplaceCarteJ(J2c5, 4, carteJ2);
                    break;
                case 5:
                    deplaceCarteJ(J2c6, 5, carteJ2);
                    break;
                case 6:
                    deplaceCarteJ(J2c7, 6, carteJ2);
                    break; 
                case 7:
                    deplaceCarteJ(J2c8, 7, carteJ2);
                    break;
                case 8:
                    deplaceCarteJ(J2c9, 8, carteJ2);
                    break;
                case 9:
                    deplaceCarteJ(J2c10, 9, carteJ2);
                    break; 
                case 10:
                    deplaceCarteJ(J2c11, 10, carteJ2);
                    break;
            }
        }
    }

    
    
    private void continuerManche(){
        nummanche.setText(Integer.toString(j.nbmanche()));
        carteJ1.setIcon(null);
        carteJ2.setIcon(null);
        ptJ1.setText("0");
        ptJ2.setText("0");
        J1pli.setIcon(null);
        J2pli.setIcon(null);
        J1nbrpli.setText("0");
        J2nbrpli.setText("0");
        pile1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_4.png"))); 
        pile2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_4.png"))); 
        pile3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_4.png"))); 
        pile4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_4.png"))); 
        pile5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_4.png"))); 
        pile6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_4.png")));
        p1 = 4; p2 = 4; p3 = 4; p4 = 4; p5 = 4; p6 = 4; p6 = 4;
        pli1 = 1; pli2 = 1;
        afficherCarteReceveur();
        afficherCarteDonneur();
        afficherPile();
        afficherAtout(atout);
    }
    
    private void annulerCoup(){
        Carte cartej1,cartej2;
        if(j.quiJoue() == 1){
            if(j.quiDonne() == 1){
                afficherCarteDonneur();
            } else {
                afficherCarteReceveur();
            }
            carteJ1.setIcon(null);
        } else {
            if(j.quiDonne() == 2){
                afficherCarteDonneur();
            } else {
                afficherCarteReceveur();
            }
            carteJ2.setIcon(null);
        } 
        if(j.quiDonne() == 1) {
                    cartej1=j.cartePrem();
                    cartej2=j.carteSec();
                } else {
                    cartej1=j.carteSec();
                    cartej2=j.cartePrem();
                }
                if(cartej1!=null) {
                    carteJ1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(cartej1)+".png")));
                }
                if (cartej2!=null){carteJ2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(cartej2)+".png")));}
        ptJ1.setText(Integer.toString(j.scorePartiej1()));
        ptJ2.setText(Integer.toString(j.scorePartiej2()));
        J1nbrpli.setText(Integer.toString(j.scorePartiej1()));
        J2nbrpli.setText(Integer.toString(j.scorePartiej2()));
        if(j.scorePartiej1() == 0){
            J1pli.setIcon(null);
        } else {
            if(j.scorePartiej1() == 1){
                J1pli.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_2.png"))); 
            } else if(j.scorePartiej1() >=2){
                J1pli.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_3.png"))); 
            }
        }
        if(j.scorePartiej2() == 0){
            J2pli.setIcon(null);
        } else {
            if(j.scorePartiej2() == 1){
                J2pli.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_2.png"))); 
            } else if(j.scorePartiej2() >=2){
                J2pli.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_3.png"))); 
            }
        }
        afficherCarteReceveur();
        afficherCarteDonneur();
        afficherPile();
    }
    
    
   private void refaireCoup(){
        if(j.peutRefaire()){
                Carte cartej1,cartej2;
                boolean tourJ1;
                if(j.phasetourterm()==2 && j.quiGagnetour()==j.quiJoue()) {
                    tourJ1=j.quiJoue()==1;
                } else {
                    tourJ1=!(j.quiJoue()==1);
                }
                if(j.quiDonne() == 1) {
                    cartej1=j.cartePrem();
                    cartej2=j.carteSec();
                } else {
                    cartej1=j.carteSec();
                    cartej2=j.cartePrem();
                }
                if(cartej1!=null ) {
                    carteJ1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(cartej1)+".png")));
                }
                if (cartej2!=null){carteJ2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/"+afficherCarte(cartej2)+".png")));}
                for(int i=0;i<11;i++) {
                       JLabel label;
                       switch(i) {
                       case 0: label = tourJ1?J1c1:J2c1;break;
                       case 1: label = tourJ1?J1c2:J2c2;break;
                       case 2: label = tourJ1?J1c3:J2c3;break;
                       case 3: label = tourJ1?J1c4:J2c4;break;
                       case 4: label = tourJ1?J1c5:J2c5;break;
                       case 5: label = tourJ1?J1c6:J2c6;break;
                       case 6: label = tourJ1?J1c7:J2c7;break;
                       case 7: label = tourJ1?J1c8:J2c8;break;
                       case 8: label = tourJ1?J1c9:J2c9;break;
                       case 9: label = tourJ1?J1c10:J2c10;break;
                       case 10: label= tourJ1?J1c11:J2c11;break;
                       default: label = null;
                       }
                       if(j.joueurDonneur().main().carte(i)!=null ){
                           label.setIcon(null);
                    }
                       
               }
               ptJ1.setText(Integer.toString(j.scorePartiej1()));
               ptJ2.setText(Integer.toString(j.scorePartiej2()));
                J1nbrpli.setText(Integer.toString(j.scorePartiej1()));
                J2nbrpli.setText(Integer.toString(j.scorePartiej2()));
        if(j.scorePartiej1() == 0){
            J1pli.setIcon(null);
        } else {
            if(j.scorePartiej1() == 1){
                J1pli.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_2.png"))); 
            } else if(j.scorePartiej1() >=2){
                J1pli.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_3.png"))); 
            }
        }
        if(j.scorePartiej2() == 0){
            J2pli.setIcon(null);
        } else {
            if(j.scorePartiej2() == 1){
                J2pli.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_2.png"))); 
            } else if(j.scorePartiej2() >=2){
                J2pli.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_3.png"))); 
            }
        }
             afficherCarteDonneur();
             afficherCarteReceveur();
        }
    }
    
    
    
    //LA fenetre de jeu
    private void initComponents(){
        JPanel panel = new JPanel();
        JPanel resume = new JPanel();
        JPanel options = new JPanel();
        pause = new JToggleButton();
        JButton annuler = new JButton();
        JButton refaire = new JButton();
        contin = new JButton();
        hide = new JButton();
        hide2 = new JButton();
        carteJ2 = new JLabel();
        carteJ1 = new JLabel();
        J2pli = new JLabel();
        J1pli = new JLabel();
        J1nbrpli = new JLabel();
        J2nbrpli = new JLabel();
        J2c11 = new JLabel();
        J2c10 = new JLabel();
        J2c9 = new JLabel();
        J2c8 = new JLabel();
        J2c7 = new JLabel();
        J2c6 = new JLabel();
        J2c5 = new JLabel();
        J2c4 = new JLabel();
        J2c3 = new JLabel();
        J2c2 = new JLabel();
        J2c1 = new JLabel();
        JSeparator jSeparator1 = new JSeparator();
        JSeparator jSeparator2 = new JSeparator();
        nbr = new JLabel();
        mode = new JLabel();
        quitour = new JLabel();
        quigagne = new JLabel();
        action = new JLabel();
        action2 = new JLabel();
        nummanche = new JLabel();
        atout = new JLabel();
        nomJ1 = new JLabel();
        nomJ2 = new JLabel();
        ptJ1 = new JTextField();
        ptJ2 = new JTextField();
        JLabel jLabel1 = new JLabel();
        JLabel jLabel2 = new JLabel();
        JLabel jLabel3 = new JLabel();
        JLabel jLabel4 = new JLabel();
        JLabel jLabel5 = new JLabel();
        JLabel jLabel6 = new JLabel();
        JLabel jLabel7 = new JLabel();
        JLabel jLabel8 = new JLabel();
        carteJ2 = new JLabel();
        carteJ1 = new JLabel();
        crtpile1 = new JLabel();
        crtpile2 = new JLabel();
        crtpile3 = new JLabel();
        crtpile4 = new JLabel();
        crtpile5 = new JLabel();
        crtpile6 = new JLabel();
        pile1 = new JLabel();
        pile2 = new JLabel();
        pile3 = new JLabel();
        pile4 = new JLabel();
        pile5 = new JLabel();
        pile6 = new JLabel();
        J1c11 = new JLabel();
        J1c10 = new JLabel();
        J1c9 = new JLabel();
        J1c8 = new JLabel();
        J1c7 = new JLabel();
        J1c6 = new JLabel();
        J1c5 = new JLabel();
        J1c4 = new JLabel();
        J1c3 = new JLabel();
        J1c2 = new JLabel();
        J1c1 = new JLabel();
        tourJ1 = new JLabel();
        tourJ2 = new JLabel();
        JLabel fond = new JLabel();
        JMenuBar jMenuBar1 = new JMenuBar();
        JMenu jMenu1 = new JMenu();
        JMenu jMenu2 = new JMenu();
        JMenuItem newpartie = new JMenuItem();
        JMenuItem save = new JMenuItem();
        JMenuItem historique = new JMenuItem();
               
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bridge Chinois");
        setResizable(false);        

        panel.setLayout(null);
        
        jLabel7.setVisible(false);
        jLabel8.setVisible(false);
        J1nbrpli.setVisible(false);
        J2nbrpli.setVisible(false);
        tourJ2.setVisible(false);

        options.setBackground(new Color(204, 204, 204));
        options.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Options", TitledBorder.CENTER, TitledBorder.TOP, new Font("Times New Roman", 3, 24), new Color(255, 255, 255))); 
        options.setOpaque(false);
        options.setLayout(null);

        pause.setFont(new Font("Times New Roman", 1, 16)); 
        pause.setText("PAUSE");
        pause.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                j.pause();
            }
        });
        options.add(pause);
        pause.setBounds(170, 40, 110, 45);

        annuler.setFont(new Font("Times New Roman", 1, 16)); 
        annuler.setText("ANNULER");
        annuler.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
        annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                indiceC = -1;
                jouePartie();
                metAJour();
                annulerCoup();
            }
        });

        options.add(annuler);
        annuler.setBounds(40, 100, 110, 45);

        refaire.setFont(new Font("Times New Roman", 1, 16)); 
        refaire.setText("REFAIRE");
        refaire.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
        refaire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                indiceC = -2;
                jouePartie();
                refaireCoup();
            }
        });
        options.add(refaire);
        refaire.setBounds(170, 100, 110, 45);
        
        contin.setFont(new Font("Times New Roman", 1, 16)); 
        contin.setText("CONTINUER");
        contin.setEnabled(false);
        contin.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
        contin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                indiceC = 1;
                jouePartie();
                continuerManche();
            }
        });
        options.add(contin);
        contin.setBounds(40, 40, 110, 45);
        
        panel.add(options);
        options.setBounds(1365, 655, 310, 170);
        panel.add(carteJ2);
        carteJ2.setBounds(15, 100, 140, 200);
        panel.add(carteJ1);
        carteJ1.setBounds(15, 550, 140, 200);
        
        
        hide2.setFont(new Font("Times New Roman", 1, 16)); 
        hide2.setText("CACHER");
        hide2.setEnabled(false);
        hide2.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
        hide2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(tourj1 == true){
                    r2++;
                    if(r2 % 2 == 0){
                        cacherCarteJ();
                    } else {
                        dosj2 = false;
                        if(j.quiRecois() == 2){
                            afficherCarteReceveur();
                        } else if(j.quiDonne() == 2){
                            afficherCarteDonneur();
                        }
                    }
                } 
            }
        });
        panel.add(hide2);
        hide2.setBounds(155, 180, 80, 30);
        
        
        hide.setFont(new Font("Times New Roman", 1, 16)); 
        hide.setText("CACHER");
        hide.setEnabled(false);
        hide.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
        hide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(tourj2 == true){
                    r++;
                    if(r % 2 == 0){
                        cacherCarteJ();
                    } else {
                        dosj1 = false;
                        if(j.quiRecois() == 1){
                            afficherCarteReceveur();
                        } else if(j.quiDonne() == 1){
                            afficherCarteDonneur();
                        }
                    }
                }
            }
        });
        panel.add(hide);
        hide.setBounds(165, 623, 80, 30);
        
        
        J2pli.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                if(J2pli.getIcon() != null){
                    jLabel8.setVisible(true);
                    J2nbrpli.setVisible(true);
                }
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                jLabel8.setVisible(false);
                J2nbrpli.setVisible(false);
            }
        });
        panel.add(J2pli);
        J2pli.setBounds(1150, 20, 141, 196);

        J1pli.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                if(J1pli.getIcon() != null){
                    jLabel7.setVisible(true);
                    J1nbrpli.setVisible(true);
                }
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                jLabel7.setVisible(false);
                J1nbrpli.setVisible(false);
            }
        });
        panel.add(J1pli);
        J1pli.setBounds(1160, 620, 141, 196);
        
        J2c11.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                if(tourj1 != true && piocher != true && !j.estIA(2)){
                    if(j.quiRecois() == 2 && J2c11.getIcon() != null){
                        peutJouerCarte(10);
                    } else {
                        apercuCarte(980, 60, J2c11);
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                apercuCarte(980, 20, J2c11);
            }
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(!j.estIA(2)){
                    actionCarte(J2c11, carteJ2, 10, tourj1, piocher, dosj2);
                }
            }
        });
        panel.add(J2c11);
        J2c11.setBounds(980, 20, 136, 197);

        J2c10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                if(tourj1 != true && piocher != true && !j.estIA(2)){
                    if(j.quiRecois() == 2 && J2c10.getIcon() != null){
                        peutJouerCarte(9);
                    } else {
                        apercuCarte(910, 60, J2c10);
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                apercuCarte(910, 20, J2c10);
            }
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(!j.estIA(2)){
                    actionCarte(J2c10, carteJ2, 9, tourj1, piocher, dosj2);
                }
            }
        });
        panel.add(J2c10);
        J2c10.setBounds(910, 20, 136, 196);

        J2c9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                if(tourj1 != true && piocher != true && !j.estIA(2)){
                    if(j.quiRecois() == 2 && J2c9.getIcon() != null){
                        peutJouerCarte(8);
                    } else {
                        apercuCarte(830, 60, J2c9);
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                apercuCarte(830, 20, J2c9);
            }
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(!j.estIA(2)){
                    actionCarte(J2c9, carteJ2, 8, tourj1, piocher, dosj2);
                }
            }
        });
        panel.add(J2c9);
        J2c9.setBounds(830, 20, 136, 196);

        J2c8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt ) {
                if(tourj1 != true && piocher != true && !j.estIA(2)){
                    if(j.quiRecois() == 2 && J2c8.getIcon() != null){
                        peutJouerCarte(7);
                    } else {
                        apercuCarte(760, 60, J2c8);
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                apercuCarte(760, 20, J2c8);
            }
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(!j.estIA(2)){
                    actionCarte(J2c8, carteJ2, 7, tourj1, piocher, dosj2);
                }
            }
        });
        panel.add(J2c8);
        J2c8.setBounds(760, 20, 136, 196);

        J2c7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                if(tourj1 != true && piocher != true && !j.estIA(2)){
                    if(j.quiRecois() == 2 && J2c7.getIcon() != null){
                        peutJouerCarte(6);
                    } else {
                        apercuCarte(680, 60, J2c7);
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                apercuCarte(680, 20, J2c7);
            }
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(!j.estIA(2)){
                    actionCarte(J2c7, carteJ2, 6, tourj1, piocher, dosj2);
                }
            }
        });
        panel.add(J2c7);
        J2c7.setBounds(680, 20, 136, 197);        

        J2c6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                if(tourj1 != true && piocher != true && !j.estIA(2)){
                    if(j.quiRecois() == 2 && J2c6.getIcon() != null){
                        peutJouerCarte(5);
                    } else {
                        apercuCarte(610, 60, J2c6);
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                apercuCarte(610, 20, J2c6);
            }
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(!j.estIA(2)){
                    actionCarte(J2c6, carteJ2, 5, tourj1, piocher, dosj2);
                }
            }
        });
        panel.add(J2c6);
        J2c6.setBounds(610, 20, 136, 197);

        J2c5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                if(tourj1 != true && piocher != true && !j.estIA(2)){
                    if(j.quiRecois() == 2 && J2c5.getIcon() != null){
                        peutJouerCarte(4);
                    } else {
                        apercuCarte(530, 60, J2c5);
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                apercuCarte(530, 20, J2c5);
            }
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(!j.estIA(2)){
                    actionCarte(J2c5, carteJ2, 4, tourj1, piocher, dosj2);
                }
            }
        });
        panel.add(J2c5);
        J2c5.setBounds(530, 20, 136, 197);
        
        J2c4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                if(tourj1 != true && piocher != true && !j.estIA(2)){
                    if(j.quiRecois() == 2 && J2c4.getIcon() != null){
                        peutJouerCarte(3);
                    } else {
                        apercuCarte(460, 60, J2c4);
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                apercuCarte(460, 20, J2c4);
            }
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(!j.estIA(2)){
                    actionCarte(J2c4, carteJ2, 3, tourj1, piocher, dosj2);
                }
            }
        });
        panel.add(J2c4);
        J2c4.setBounds(460, 20, 136, 196);

        J2c3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                if(tourj1 != true && piocher != true && !j.estIA(2)){
                    if(j.quiRecois() == 2 && J2c3.getIcon() != null){
                        peutJouerCarte(2);
                    } else {
                        apercuCarte(390, 60, J2c3);
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                apercuCarte(390, 20, J2c3);
            }
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(!j.estIA(2)){
                    actionCarte(J2c3, carteJ2, 2, tourj1, piocher, dosj2);
                }
            }
        });
        panel.add(J2c3);
        J2c3.setBounds(390, 20, 136, 196);

        J2c2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                if(tourj1 != true && piocher != true && !j.estIA(2)){
                    if(j.quiRecois() == 2 && J2c2.getIcon() != null){
                        peutJouerCarte(1);
                    } else {
                        apercuCarte(320, 60, J2c2);
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                apercuCarte(320, 20, J2c2);
            }
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(!j.estIA(2)){
                    actionCarte(J2c2, carteJ2, 1, tourj1, piocher, dosj2);
                }
            }
        });
        panel.add(J2c2);
        J2c2.setBounds(320, 20, 136, 197);

        J2c1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                if(tourj1 != true && piocher != true && !j.estIA(2)){
                    if(j.quiRecois() == 2 && J2c1.getIcon() != null){
                        peutJouerCarte(0);
                    } else {
                        apercuCarte(240, 60, J2c1);
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                apercuCarte(240, 20, J2c1);
            }
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(!j.estIA(2)){
                    actionCarte(J2c1, carteJ2, 0, tourj1, piocher, dosj2);
                }
            }
        });
        panel.add(J2c1);
        J2c1.setBounds(240, 20, 136, 197);

        resume.setBackground(new Color(204, 204, 204));
        resume.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Résumé", TitledBorder.CENTER, TitledBorder.TOP, new Font("Times New Roman", 3, 26), new Color(255, 255, 255))); 
        resume.setOpaque(false);
        resume.setLayout(null);

        jLabel1.setFont(new Font("Times New Roman", 1, 20)); 
        jLabel1.setForeground(new Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("Manche");
        resume.add(jLabel1);
        jLabel1.setBounds(86, 80, 105, 30);

        nummanche.setFont(new Font("Times New Roman", 1, 20)); 
        nummanche.setForeground(new Color(255, 255, 255));
        nummanche.setHorizontalAlignment(SwingConstants.CENTER);
        nummanche.setText("1");
        resume.add(nummanche);
        nummanche.setBounds(192, 80, 26, 30);

        atout.setFont(new Font("Times New Roman", 1, 20)); 
        atout.setForeground(new Color(255, 255, 255));
        atout.setHorizontalAlignment(SwingConstants.CENTER);
        resume.add(atout);
        atout.setBounds(78, 116, 161, 47);

        jLabel2.setFont(new Font("Times New Roman", 1, 20)); 
        jLabel2.setForeground(new Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel2.setText("L'atout");
        resume.add(jLabel2);
        jLabel2.setBounds(109, 169, 95, 32);

        jSeparator1.setForeground(new Color(255, 255, 255));
        resume.add(jSeparator1);
        jSeparator1.setBounds(6, 219, 298, 12);

        jLabel3.setFont(new Font("Times New Roman", 3, 26)); 
        jLabel3.setForeground(new Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel3.setText("Indications");
        resume.add(jLabel3);
        jLabel3.setBounds(82, 237, 138, 35);

        jLabel4.setFont(new Font("Times New Roman", 1, 20)); 
        jLabel4.setForeground(new Color(255, 255, 255));
        jLabel4.setText("Partie en ");
        resume.add(jLabel4);
        jLabel4.setBounds(32, 33, 91, 41);

        nbr.setFont(new Font("Times New Roman", 1, 20)); 
        nbr.setForeground(new Color(255, 255, 255));
        nbr.setHorizontalAlignment(SwingConstants.CENTER);
        resume.add(nbr);
        nbr.setBounds(129, 33, 41, 41);

        mode.setFont(new Font("Times New Roman", 1, 20)); 
        mode.setForeground(new Color(255, 255, 255));
        resume.add(mode);
        mode.setBounds(176, 33, 107, 41);

        jLabel5.setFont(new Font("Times New Roman", 1, 20)); 
        jLabel5.setForeground(new Color(255, 255, 255));
        jLabel5.setText("Tour de ");
        resume.add(jLabel5);
        jLabel5.setBounds(30, 280, 90, 41);

        quitour.setFont(new Font("Times New Roman", 1, 20)); 
        quitour.setForeground(new Color(255, 255, 255));
        resume.add(quitour);
        quitour.setBounds(120, 280, 132, 41);

        jSeparator2.setForeground(new Color(255, 255, 255));
        resume.add(jSeparator2);
        jSeparator2.setBounds(6, 470, 298, 9);

        jLabel6.setFont(new Font("Times New Roman", 3, 26)); 
        jLabel6.setForeground(new Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel6.setText("Points");
        resume.add(jLabel6);
        jLabel6.setBounds(100, 480, 103, 41);

        nomJ1.setFont(new Font("Times New Roman", 1, 20)); 
        nomJ1.setForeground(new Color(255, 255, 255));
        resume.add(nomJ1);
        nomJ1.setBounds(80, 530, 95, 35);

        nomJ2.setFont(new Font("Times New Roman", 1, 20)); 
        nomJ2.setForeground(new Color(255, 255, 255));
        resume.add(nomJ2);
        nomJ2.setBounds(80, 580, 95, 31);

        ptJ1.setBackground(new Color(204, 204, 204));
        ptJ1.setFont(new Font("Times New Roman", 1, 20)); 
        ptJ1.setForeground(new Color(255, 255, 255));
        ptJ1.setHorizontalAlignment(JTextField.CENTER);
        ptJ1.setText("0");
        ptJ1.setBorder(BorderFactory.createEtchedBorder());
        ptJ1.setFocusable(false);
        ptJ1.setOpaque(false);
        resume.add(ptJ1);
        ptJ1.setBounds(180, 530, 44, 31);

        ptJ2.setBackground(new Color(204, 204, 204));
        ptJ2.setFont(new Font("Times New Roman", 1, 20)); 
        ptJ2.setForeground(new Color(255, 255, 255));
        ptJ2.setHorizontalAlignment(JTextField.CENTER);
        ptJ2.setText("0");
        ptJ2.setBorder(BorderFactory.createEtchedBorder());
        ptJ2.setFocusable(false);
        ptJ2.setOpaque(false);
        resume.add(ptJ2);
        ptJ2.setBounds(180, 580, 44, 31);

        quigagne.setFont(new Font("Times New Roman", 1, 20)); 
        quigagne.setForeground(new Color(255, 255, 255));
        resume.add(quigagne);
        quigagne.setBounds(30, 330, 260, 41);
        
        action.setFont(new Font("Times New Roman", 1, 20)); 
        action.setForeground(new Color(255, 255, 255));
        action.setText("vueillez choisir la couleur");
        resume.add(action);
        action.setBounds(30, 385, 260, 41);
        
        action2.setFont(new Font("Times New Roman", 1, 20)); 
        action2.setForeground(new Color(255, 255, 255));
        action2.setHorizontalAlignment(SwingConstants.CENTER);
        resume.add(action2);
        action2.setBounds(70, 415, 180, 41);

        panel.add(resume);
        resume.setBounds(1365, 10, 310, 640);

        jLabel7.setFont(new Font("Times New Roman", 1, 18)); 
        jLabel7.setForeground(new Color(255, 255, 255));
        jLabel7.setText("Nombre de pli :");
        panel.add(jLabel7);
        jLabel7.setBounds(1160, 585, 121, 40);

        J1nbrpli.setFont(new Font("Times New Roman", 1, 18)); 
        J1nbrpli.setForeground(new Color(255, 255, 255));
        J1nbrpli.setText("0");
        panel.add(J1nbrpli);
        J1nbrpli.setBounds(1290, 585, 40, 40);

        jLabel8.setFont(new Font("Times New Roman", 1, 18)); 
        jLabel8.setForeground(new Color(255, 255, 255));
        jLabel8.setText("Nombre de pli :");
        panel.add(jLabel8);
        jLabel8.setBounds(1150, 210, 121, 40);

        J2nbrpli.setFont(new Font("Times New Roman", 1, 18)); 
        J2nbrpli.setForeground(new Color(255, 255, 255));
        J2nbrpli.setText("0");
        panel.add(J2nbrpli);
        J2nbrpli.setBounds(1280, 210, 40, 40);

        crtpile1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                if(piocher == true){
                    if(tourj1 == true && !j.estIA(1) || tourj2 == true && !j.estIA(2)){
                        apercuCarte(150, 280, crtpile1);
                    } 
                } 
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                apercuCarte(150, 320, crtpile1);
            }
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(tourj2 == true && !j.estIA(2) || tourj1 == true && !j.estIA(1)){
                    actionCartePile(1,piocher);
                }
            }
        });
        panel.add(crtpile1);
        crtpile1.setBounds(150, 320, 136, 197);

        crtpile2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                if(piocher == true){
                    if(tourj1 == true && !j.estIA(1) || tourj2 == true && !j.estIA(2)){
                        apercuCarte(340, 280, crtpile2);
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                apercuCarte(340, 320, crtpile2);
            }
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(tourj2 == true && !j.estIA(2) || tourj1 == true && !j.estIA(1)){
                    actionCartePile(2,piocher);
                }
            }
        });
        panel.add(crtpile2);
        crtpile2.setBounds(340, 320, 136, 196);

        crtpile3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                if(piocher == true){
                    if(tourj1 == true && !j.estIA(1) || tourj2 == true && !j.estIA(2)){
                        apercuCarte(530, 280, crtpile3);
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                apercuCarte(530, 320, crtpile3);
            }
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(tourj2 == true && !j.estIA(2) || tourj1 == true && !j.estIA(1)){
                    actionCartePile(3,piocher);
                }
            }
        });
        panel.add(crtpile3);
        crtpile3.setBounds(530, 320, 136, 196);

        crtpile4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                if(piocher == true){
                    if(tourj1 == true && !j.estIA(1) || tourj2 == true && !j.estIA(2)){
                        apercuCarte(720, 280, crtpile4); 
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                apercuCarte(720, 320, crtpile4);
            }
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(tourj2 == true && !j.estIA(2) || tourj1 == true && !j.estIA(1)){
                    actionCartePile(4,piocher);
                }
            }
        });
        panel.add(crtpile4);
        crtpile4.setBounds(720, 320, 136, 197);

        crtpile5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                if(piocher == true){
                    if(tourj1 == true && !j.estIA(1) || tourj2 == true && !j.estIA(2)){
                        apercuCarte(910, 280, crtpile5);
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                apercuCarte(910, 320, crtpile5);
            }
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(tourj2 == true && !j.estIA(2) || tourj1 == true && !j.estIA(1)){
                    actionCartePile(5,piocher);
                } 
            }
        });
        panel.add(crtpile5);
        crtpile5.setBounds(910, 320, 136, 196);

        crtpile6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                if(piocher == true){
                    if(tourj1 == true && !j.estIA(1) || tourj2 == true && !j.estIA(2)){
                        apercuCarte(1090, 280, crtpile6);
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                apercuCarte(1090, 320, crtpile6);
            }
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(tourj2 == true && !j.estIA(2) || tourj1 == true && !j.estIA(1)){
                    actionCartePile(6,piocher);
                }
            }
        });
        panel.add(crtpile6);
        crtpile6.setBounds(1090, 320, 136, 196);
        
        
        panel.add(pile1);
        pile1.setBounds(170, 320, 146, 196);
        panel.add(pile2);
        pile2.setBounds(360, 320, 146, 196);
        panel.add(pile3);
        pile3.setBounds(550, 320, 146, 196);
        panel.add(pile4);
        pile4.setBounds(740, 320, 146, 196);
        panel.add(pile5);
        pile5.setBounds(930, 320, 146, 196);
        panel.add(pile6);
        pile6.setBounds(1110, 320, 146, 196);
        
        J1c11.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                if(tourj2 != true && piocher != true && !j.estIA(1)){
                    if(j.quiRecois() == 1 && J1c11.getIcon() != null){
                        peutJouerCarte(10);
                    } else {
                        apercuCarte(990, 580, J1c11);
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                apercuCarte(990, 620, J1c11);
            }
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(!j.estIA(1)){
                    actionCarte(J1c11, carteJ1, 10, tourj2, piocher, dosj1);
                }
            }
        });
        panel.add(J1c11);
        J1c11.setBounds(990, 620, 136, 197);

        J1c10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                if(tourj2 != true && piocher != true && !j.estIA(1)){
                    if(j.quiRecois() == 1 && J1c10.getIcon() != null){
                        peutJouerCarte(9);
                    } else {
                        apercuCarte(920, 580, J1c10);
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                apercuCarte(920, 620, J1c10);
            }
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(!j.estIA(1)){
                    actionCarte(J1c10, carteJ1, 9, tourj2, piocher, dosj1);
                }
            }
        });
        panel.add(J1c10);
        J1c10.setBounds(920, 620, 136, 196);
        J1c9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                if(tourj2 != true && piocher != true && !j.estIA(1)){
                    if(j.quiRecois() == 1 && J1c9.getIcon() != null){
                        peutJouerCarte(8);
                    } else {
                        apercuCarte(840, 580, J1c9);
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                apercuCarte(840, 620, J1c9);
            }
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(!j.estIA(1)){
                    actionCarte(J1c9, carteJ1, 8, tourj2, piocher, dosj1);
                }
            }
        });
        panel.add(J1c9);
        J1c9.setBounds(840, 620, 136, 196);

        J1c8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                if(tourj2 != true && piocher != true && !j.estIA(1)){
                    if(j.quiRecois() == 1 && J1c8.getIcon() != null){
                        peutJouerCarte(7);
                    } else {
                        apercuCarte(770, 580, J1c8);
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                apercuCarte(770, 620, J1c8);
            }
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(!j.estIA(1)){
                    actionCarte(J1c8, carteJ1, 7, tourj2, piocher, dosj1);
                }
            }
        });
        panel.add(J1c8);
        J1c8.setBounds(770, 620, 136, 196);

        J1c7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                if(tourj2 != true && piocher != true && !j.estIA(1)){
                    if(j.quiRecois() == 1 && J1c7.getIcon() != null){
                        peutJouerCarte(6);
                    } else {
                        apercuCarte(690, 580, J1c7);
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                apercuCarte(690, 620, J1c7);
            }
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(!j.estIA(1)){
                    actionCarte(J1c7, carteJ1, 6, tourj2, piocher, dosj1);
                }
            }
        });
        panel.add(J1c7);
        J1c7.setBounds(690, 620, 136, 197);

        J1c6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                if(tourj2 != true && piocher != true && !j.estIA(1)){
                    if(j.quiRecois() == 1 && J1c6.getIcon() != null){
                        peutJouerCarte(5);
                    } else {
                        apercuCarte(620, 580, J1c6);
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                apercuCarte(620, 620, J1c6);
            }
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(!j.estIA(1)){
                    actionCarte(J1c6, carteJ1, 5, tourj2, piocher, dosj1);
                }
            }
        });
        panel.add(J1c6);
        J1c6.setBounds(620, 620, 136, 196);

        J1c5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                if(tourj2 != true && piocher != true && !j.estIA(1)){
                    if(j.quiRecois() == 1 && J1c5.getIcon() != null){
                        peutJouerCarte(4);
                    } else {
                        apercuCarte(540, 580, J1c5);
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                apercuCarte(540, 620, J1c5);
            }
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(!j.estIA(1)){
                    actionCarte(J1c5, carteJ1, 4, tourj2, piocher, dosj1);
                }
            }
        });
        panel.add(J1c5);
        J1c5.setBounds(540, 620, 136, 197);

        J1c4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                if(tourj2 != true && piocher != true && !j.estIA(1)){
                    if(j.quiRecois() == 1 && J1c4.getIcon() != null){
                        peutJouerCarte(3);
                    } else {
                        apercuCarte(470, 580, J1c4);
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                apercuCarte(470, 620, J1c4);
            }
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(!j.estIA(1)){
                    actionCarte(J1c4, carteJ1, 3, tourj2, piocher, dosj1);
                }
            }
        });
        panel.add(J1c4);
        J1c4.setBounds(470, 620, 136, 196);

        J1c3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                if(tourj2 != true && piocher != true && !j.estIA(1)){
                    if(j.quiRecois() == 1 && J1c3.getIcon() != null){
                        peutJouerCarte(2);
                    } else {
                        apercuCarte(400, 580, J1c3);
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                apercuCarte(400, 620, J1c3);
            }
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(!j.estIA(1)){
                    actionCarte(J1c3, carteJ1, 2, tourj2, piocher, dosj1);
                }
            }
        });
        panel.add(J1c3);
        J1c3.setBounds(400, 620, 136, 196);

        J1c2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                if(tourj2 != true && piocher != true && !j.estIA(1)){
                    if(j.quiRecois() == 1 && J1c2.getIcon() != null){
                        peutJouerCarte(1);
                    } else {
                        apercuCarte(330, 580, J1c2);
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                apercuCarte(330, 620, J1c2);
            }
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(!j.estIA(1)){
                    actionCarte(J1c2, carteJ1, 1, tourj2, piocher, dosj1);
                }
            }
        });
        panel.add(J1c2);
        J1c2.setBounds(330, 620, 136, 196);

        J1c1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                if(tourj2 != true && piocher != true && !j.estIA(1)){
                    if(j.quiRecois() == 1 && J1c1.getIcon() != null){
                        peutJouerCarte(0);
                    } else {
                        apercuCarte(250, 580, J1c1);
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                apercuCarte(250, 620, J1c1);
            }
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(!j.estIA(1)){
                    actionCarte(J1c1, carteJ1, 0, tourj2, piocher, dosj1);
                }
            }
        });
        panel.add(J1c1);
        J1c1.setBounds(250, 620, 136, 197);

        tourJ1.setIcon(new ImageIcon(getClass().getResource("/images/tour.png"))); 
        panel.add(tourJ1);
        tourJ1.setBounds(170, 750, 70, 64);

        tourJ2.setIcon(new ImageIcon(getClass().getResource("/images/tour.png"))); 
        panel.add(tourJ2);
        tourJ2.setBounds(160, 20, 64, 64);

        fond.setIcon(new ImageIcon(getClass().getResource("/images/fond3.jpg"))); 
        fond.setPreferredSize(new Dimension(1356, 1000));
        panel.add(fond);
        fond.setBounds(0, 0, 1700, 850);

        jMenuBar1.setBackground(new Color(204, 204, 204));
        jMenuBar1.setFont(new Font("Times New Roman", 0, 24)); 
        jMenuBar1.setOpaque(false);

        jMenu1.setText("Partie");
        jMenu1.setFont(new Font("Times New Roman", 1, 20)); 
        jMenu1.setPreferredSize(new Dimension(65, 35));

        newpartie.setFont(new Font("Times New Roman", 1, 18)); 
        newpartie.setText("Nouvelle partie");
        newpartie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                j.nouvellePartie();
                afficherAtout(atout);
                ptJ1.setText("0");
                ptJ2.setText("0");
                carteJ1.setIcon(null);
                carteJ2.setIcon(null);
                J1pli.setIcon(null);
                J2pli.setIcon(null);
                J1nbrpli.setText("0");
                J2nbrpli.setText("0");
                piocher=false;
                tourj1=true;
                tourj2=false;
                pause.setSelected(false);
                metAJour();
            }
        });
        jMenu1.add(newpartie);

        save.setFont(new Font("Times New Roman", 1, 18)); 
        save.setText("Sauvegarder");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                JFileChooser filec = new JFileChooser();
                filec.setCurrentDirectory(new File(System.getProperty("user.dir")));
                int rep = filec.showSaveDialog(null); 
                if(rep == JFileChooser.APPROVE_OPTION){
                     File file = new File(filec.getSelectedFile().getAbsolutePath());
                     try {
                        j.sauvegarder(file.toString());
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
        jMenu1.add(save);

        historique.setFont(new Font("Times New Roman", 1, 18)); 
        historique.setText("Historique");
        historique.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                histoFrame();
                histof.setLocationRelativeTo(null);
                namej1.setText(nomJ1.getText());
                namej2.setText(nomJ2.getText());
                switch(j.nbmanche()){
                    case 1:
                        j1m1.setText(Integer.toString(j.scorePartiej1()));
                        j2m1.setText(Integer.toString(j.scorePartiej2()));
                        break;
                    case 2:
                        j1m2.setText(Integer.toString(j.scorePartiej1()));
                        j2m2.setText(Integer.toString(j.scorePartiej2()));
                        break;
                    case 3:
                        j1m3.setText(Integer.toString(j.scorePartiej1()));
                        j2m3.setText(Integer.toString(j.scorePartiej2()));
                        break;
                    case 4:
                        j1m4.setText(Integer.toString(j.scorePartiej1()));
                        j2m4.setText(Integer.toString(j.scorePartiej2()));
                        break;
                }              
            }
        });
        jMenu1.add(historique);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Regles");
        jMenu2.setFont(new Font("Times New Roman", 1, 20)); 
        jMenu2.setPreferredSize(new Dimension(70, 35));
        jMenu2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                InterfaceGraphique i = new InterfaceGraphique(j,c);
                i.reglesf.setVisible(true);
                i.reglesf.setLocationRelativeTo(null);
            }
        });
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panel, GroupLayout.PREFERRED_SIZE, 1700, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel, GroupLayout.PREFERRED_SIZE, 850, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }
    
    
    //LA fenetre fin partie
    private void finPartieFrame() {
        partief = new JFrame();
        winner = new JLabel();
        scorej2 = new JLabel();
        scorej1 = new JLabel();
        trophy = new JLabel();
        JLabel fond = new JLabel();

        partief.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        partief.setTitle("Fin de partie");
        partief.setResizable(false);
        partief.setSize(510,480);
        partief.getContentPane().setLayout(null);

        winner.setFont(new Font("Times New Roman", 3, 24));
        winner.setForeground(new Color(255, 255, 255));
        winner.setHorizontalAlignment(SwingConstants.CENTER);
        partief.getContentPane().add(winner);
        winner.setBounds(30, 260, 450, 50);

        scorej2.setFont(new Font("Times New Roman", 3, 24));
        scorej2.setForeground(new Color(255, 255, 255));
        partief.getContentPane().add(scorej2);
        scorej2.setBounds(150, 370, 210, 50);

        scorej1.setFont(new Font("Times New Roman", 3, 24));
        scorej1.setForeground(new Color(255, 255, 255));
        partief.getContentPane().add(scorej1);
        scorej1.setBounds(150, 320, 210, 50);

        partief.getContentPane().add(trophy);
        trophy.setBounds(170, 20, 190, 240);

        fond.setIcon(new ImageIcon(getClass().getResource("/images/fond3.jpg")));
        partief.getContentPane().add(fond);
        fond.setBounds(0, 0, 520, 450);
        partief.setVisible(true);
    }
    
    
    private void Pile(){
        switch(j.taillepile(1)){
            case 1:
                pile1.setIcon(null); 
                break;
            case 2:
                pile1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_1.png"))); 
                break;
            case 3:
                pile1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_2.png"))); 
                break;
            case 4:
                pile1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_3.png"))); 
                break;
            case 5:
                pile1.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_4.png"))); 
                break;
        }
        switch(j.taillepile(2)){
            case 1:
                pile2.setIcon(null); 
                break;
            case 2:
                pile2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_1.png"))); 
                break;
            case 3:
                pile2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_2.png"))); 
                break;
            case 4:
                pile2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_3.png"))); 
                break;
            case 5:
                pile2.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_4.png"))); 
                break;
        }
        switch(j.taillepile(3)){
            case 1:
                pile3.setIcon(null); 
                break;
            case 2:
                pile3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_1.png"))); 
                break;
            case 3:
                pile3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_2.png"))); 
                break;
            case 4:
                pile3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_3.png"))); 
                break;
            case 5:
                pile3.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_4.png"))); 
                break;
        }
        switch(j.taillepile(4)){
            case 1:
                pile4.setIcon(null); 
                break;
            case 2:
                pile4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_1.png"))); 
                break;
            case 3:
                pile4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_2.png"))); 
                break;
            case 4:
                pile4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_3.png"))); 
                break;
            case 5:
                pile4.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_4.png"))); 
                break;
        }
        switch(j.taillepile(5)){
            case 1:
                pile5.setIcon(null); 
                break;
            case 2:
                pile5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_1.png"))); 
                break;
            case 3:
                pile5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_2.png"))); 
                break;
            case 4:
                pile5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_3.png"))); 
                break;
            case 5:
                pile5.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_4.png"))); 
                break;
        }
        switch(j.taillepile(6)){
            case 1:
                pile6.setIcon(null); 
                break;
            case 2:
                pile6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_1.png"))); 
                break;
            case 3:
                pile6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_2.png"))); 
                break;
            case 4:
                pile6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_3.png"))); 
                break;
            case 5:
                pile6.setIcon(new ImageIcon(getClass().getResource("/images/cartes/pile_4.png"))); 
                break;
        }
    }
    
    private void histoFrame() {
        
        histof = new JFrame();
        namej1 = new JLabel();
        namej2 = new JLabel();
        JLabel jLabel3 = new JLabel();
        JLabel jLabel4 = new JLabel();
        JLabel jLabel5 = new JLabel();
        JLabel jLabel6 = new JLabel();
        j1m1 = new JTextField();
        j1m2 = new JTextField();
        j1m3 = new JTextField();
        j1m4 = new JTextField();
        j2m1 = new JTextField();
        j2m2 = new JTextField();
        j2m3 = new JTextField();
        j2m4 = new JTextField();
        JLabel fond = new JLabel();

        histof.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        histof.setTitle("Historique");
        histof.setResizable(false);
        histof.setSize(500,440);
        histof.getContentPane().setLayout(null);

        namej1.setFont(new Font("Times New Roman", 3, 24));
        namej1.setForeground(new Color(255, 255, 255));
        namej1.setText("Joueur 1");
        histof.getContentPane().add(namej1);
        namej1.setBounds(250, 40, 100, 30);

        namej2.setFont(new Font("Times New Roman", 3, 24));
        namej2.setForeground(new Color(255, 255, 255));
        namej2.setText("Joueur 2");
        histof.getContentPane().add(namej2);
        namej2.setBounds(370, 40, 100, 30);


        jLabel3.setFont(new Font("Times New Roman", 3, 24));
        jLabel3.setForeground(new Color(255, 255, 255));
        jLabel3.setText("Points manche 4 :");
        histof.getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 270, 180, 50);

        jLabel4.setFont(new Font("Times New Roman", 3, 24));
        jLabel4.setForeground(new Color(255, 255, 255));
        jLabel4.setText("Points manche 3 :");
        histof.getContentPane().add(jLabel4);
        jLabel4.setBounds(30, 210, 180, 50);

        jLabel5.setFont(new Font("Times New Roman", 3, 24));
        jLabel5.setForeground(new Color(255, 255, 255));
        jLabel5.setText("Points manche 2 :");
        histof.getContentPane().add(jLabel5);
        jLabel5.setBounds(30, 150, 180, 50);

        jLabel6.setFont(new Font("Times New Roman", 3, 24));
        jLabel6.setForeground(new Color(255, 255, 255));
        jLabel6.setText("Points manche 1 :");
        histof.getContentPane().add(jLabel6);
        jLabel6.setBounds(30, 90, 180, 50);

        j1m1.setBackground(new Color(204, 204, 204));
        j1m1.setFont(new Font("Times New Roman", 1, 22));
        j1m1.setForeground(new Color(255, 255, 255));
        j1m1.setHorizontalAlignment(JTextField.CENTER);
        j1m1.setText("0");
        j1m1.setBorder(createEtchedBorder());
        j1m1.setFocusable(false);
        j1m1.setOpaque(false);
        histof.getContentPane().add(j1m1);
        j1m1.setBounds(270, 90, 40, 40);

        j1m2.setBackground(new Color(204, 204, 204));
        j1m2.setFont(new Font("Times New Roman", 1, 22));
        j1m2.setForeground(new Color(255, 255, 255));
        j1m2.setHorizontalAlignment(JTextField.CENTER);
        j1m2.setText("0");
        j1m2.setBorder(createEtchedBorder());
        j1m2.setFocusable(false);
        j1m2.setOpaque(false);
        histof.getContentPane().add(j1m2);
        j1m2.setBounds(270, 150, 40, 40);

        j1m3.setBackground(new Color(204, 204, 204));
        j1m3.setFont(new Font("Times New Roman", 1, 22));
        j1m3.setForeground(new Color(255, 255, 255));
        j1m3.setHorizontalAlignment(JTextField.CENTER);
        j1m3.setText("0");
        j1m3.setBorder(createEtchedBorder());
        j1m3.setFocusable(false);
        j1m3.setOpaque(false);
        histof.getContentPane().add(j1m3);
        j1m3.setBounds(270, 210, 40, 40);

        j1m4.setBackground(new Color(204, 204, 204));
        j1m4.setFont(new Font("Times New Roman", 1, 22));
        j1m4.setForeground(new Color(255, 255, 255));
        j1m4.setHorizontalAlignment(JTextField.CENTER);
        j1m4.setText("0");
        j1m4.setBorder(createEtchedBorder());
        j1m4.setFocusable(false);
        j1m4.setOpaque(false);
        histof.getContentPane().add(j1m4);
        j1m4.setBounds(270, 270, 40, 40);

        j2m1.setBackground(new Color(204, 204, 204));
        j2m1.setFont(new Font("Times New Roman", 1, 22));
        j2m1.setForeground(new Color(255, 255, 255));
        j2m1.setHorizontalAlignment(JTextField.CENTER);
        j2m1.setText("0");
        j2m1.setBorder(createEtchedBorder());
        j2m1.setFocusable(false);
        j2m1.setOpaque(false);
        histof.getContentPane().add(j2m1);
        j2m1.setBounds(390, 90, 40, 40);

        j2m2.setBackground(new Color(204, 204, 204));
        j2m2.setFont(new Font("Times New Roman", 1, 22));
        j2m2.setForeground(new Color(255, 255, 255));
        j2m2.setHorizontalAlignment(JTextField.CENTER);
        j2m2.setText("0");
        j2m2.setBorder(createEtchedBorder());
        j2m2.setFocusable(false);
        j2m2.setOpaque(false);
        histof.getContentPane().add(j2m2);
        j2m2.setBounds(390, 150, 40, 40);

        j2m3.setBackground(new Color(204, 204, 204));
        j2m3.setFont(new Font("Times New Roman", 1, 22));
        j2m3.setForeground(new Color(255, 255, 255));
        j2m3.setHorizontalAlignment(JTextField.CENTER);
        j2m3.setText("0");
        j2m3.setBorder(createEtchedBorder());
        j2m3.setFocusable(false);
        j2m3.setOpaque(false);
        histof.getContentPane().add(j2m3);
        j2m3.setBounds(390, 210, 40, 40);

        j2m4.setBackground(new Color(204, 204, 204));
        j2m4.setFont(new Font("Times New Roman", 1, 22));
        j2m4.setForeground(new Color(255, 255, 255));
        j2m4.setHorizontalAlignment(JTextField.CENTER);
        j2m4.setText("0");
        j2m4.setBorder(createEtchedBorder());
        j2m4.setFocusable(false);
        j2m4.setOpaque(false);
        histof.getContentPane().add(j2m4);
        j2m4.setBounds(390, 270, 40, 40);

        fond.setIcon(new ImageIcon(getClass().getResource("/images/fond3.jpg")));
        histof.getContentPane().add(fond);
        fond.setBounds(0, 0, 500, 400);
        histof.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        reflechie = j.getreflechie();
        int nb =j.getentier();
        ref=1;
        if(reflechie!=1 && !j.getpause() && !j.partifini()){
            if(piocher != true){
                actionCarteIA(nb);
            } else {
                actionCartePileIA(nb);
            }
            switch (c.commande(nb)) {
                case 0:
                    break;
                case 1  :
                    action.setText("Vous n'avez pas choisi de");
                    action2.setText("carte sur l'une des piles");
                    break; 
                case 2:
                    action.setText("Vous n'avez pas choisi de carte");
                    break; 
                case 4:
                    afficherCarteReceveur();
                    if(j.quiRecois() == 1){
                        carteJ1.setIcon(null);
                    } else{
                        carteJ2.setIcon(null);
                    }
                    action.setText("Vous devez jouer la couleur");
                    action2.setText(j.cartePremCouleur());
                    break; 
                }
            this.ref=0;
            time.stop();
            metAJour();
        }
    }
}
