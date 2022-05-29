/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Modele.Partie;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import static javax.swing.BorderFactory.createBevelBorder;
import static javax.swing.BorderFactory.createEtchedBorder;
import static javax.swing.BorderFactory.createTitledBorder;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author khebt
 */
public class InterfaceGraphique extends JFrame{
    JRadioButton humanJ1,IAJ1,humanJ2,IAJ2,radiobtn1,radiobtn2;
    JFrame reglesf,configf;
    JComboBox<String> typeIAJ1,typeIAJ2;
    JTextField nameJ1,nameJ2,points,manches;
    Partie j;
    CollecteurEvenements c;
    
    
    public InterfaceGraphique(Partie j, CollecteurEvenements c){
        initComponents();
        this.j = j;
        this.c = c;
        this.setLocationRelativeTo(null);
    }
            
    
    //LA fenetre principal
    private void initComponents() {
        
        JLabel titre = new JLabel();
        JButton newpartie = new JButton();
        JButton chargpartie = new JButton();
        JButton regles = new JButton();
        JLabel fond = new JLabel();
        
        reglesFrame();
        configFrame();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bridge Chinois");
        setResizable(false);
        setSize(1010,600);
        getContentPane().setLayout(null);

        newpartie.setFont(new Font("Times New Roman", 0, 20)); 
        newpartie.setText("Nouvelle partie");
        newpartie.setBorder(createBevelBorder(BevelBorder.RAISED));
        newpartie.setPreferredSize(new Dimension(200, 35));
        newpartie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                configf.setVisible(true);
                configf.setLocationRelativeTo(null);
                dispose();
            }
        });
        getContentPane().add(newpartie);
        newpartie.setBounds(110, 210, 200, 40);

        chargpartie.setFont(new Font("Times New Roman", 0, 20)); 
        chargpartie.setText("Charger une partie");
        chargpartie.setBorder(createBevelBorder(BevelBorder.RAISED));
        chargpartie.setPreferredSize(new Dimension(200, 35));
        chargpartie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               JFileChooser filec = new JFileChooser();
               filec.setCurrentDirectory(new File("C:\\Users\\khebt\\Desktop"));
               int rep = filec.showOpenDialog(null);//select file to open
               //int rep = filec.showSaveDialog(null); select file to save
               if(rep == JFileChooser.APPROVE_OPTION){
                    File file = new File(filec.getSelectedFile().getAbsolutePath());
                    System.out.println(file);
                 }
            }
        });
        getContentPane().add(chargpartie);
        chargpartie.setBounds(110, 280, 200, 40);

        regles.setFont(new Font("Times New Roman", 0, 20)); 
        regles.setText("Régles du jeu");
        regles.setBorder(createBevelBorder(BevelBorder.RAISED));
        regles.setPreferredSize(new Dimension(200, 35));
        regles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                reglesf.setVisible(true);
                reglesf.setLocationRelativeTo(null);
            }
        });
        getContentPane().add(regles);
        regles.setBounds(110, 350, 200, 40);

        titre.setFont(new Font("Times New Roman", 3, 48)); 
        titre.setHorizontalAlignment(SwingConstants.CENTER);
        titre.setText("Bridge Chinois");
        getContentPane().add(titre);
        titre.setBounds(10, 40, 430, 70);  

        fond.setIcon(new ImageIcon(getClass().getResource("/images/fond.jpg"))); 
        getContentPane().add(fond);
        fond.setBounds(0, 0, 1010, 580);

    }
    
    
    
    
    //LA fenetre des regles
    private void reglesFrame() {
        
        reglesf = new JFrame();
        JPanel panel = new JPanel();
        JButton prev = new JButton();
        JButton next = new JButton();
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();
        JTextArea text1 = new JTextArea();
        JTextArea text2 = new JTextArea();
        JTextArea text3 = new JTextArea();
        
        reglesf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        reglesf.setTitle("Bridge Chinois");
        reglesf.setResizable(false);
        reglesf.setSize(1070, 750);
        
        prev.setVisible(false);
        label3.setVisible(false);
        text3.setVisible(false); 

        panel.setBackground(new Color(58, 140, 140));
        panel.setLayout(null);

        label1.setFont(new Font("Times New Roman", 3, 36)); 
        label1.setForeground(new Color(255, 255, 255));
        label1.setText("Principe");
        panel.add(label1);
        label1.setBounds(150, 20, 190, 51);

        text1.setEditable(false);
        text1.setBackground(new Color(58, 140, 140));
        text1.setColumns(20);
        text1.setFont(new Font("Times New Roman", 1, 20)); 
        text1.setForeground(new Color(255, 255, 255));
        text1.setRows(5);
        text1.setText("Le bridge Chinois est un jeu de cartes qui se joue à deux joueurs en utilisant un paquet standart de 52 cartes. \n\nLe but étant de faire le maximum de plis et d'en posséder un plus grand nombre que son adversaire. \n\nUn pli est équivalent à un point. Chaque pli consiste en 2 cartes, chacune posée sur le plateau par l'un des joueurs, \nchoisie parmi les cartes qu'il a en main. Le pli est rammassé par le joueur qui le gagne. \n\nPour ce faire, si les deux cartes sont de même couleur, alors le joueur qui a la carte la plus forte remporte le pli. \n\nDans le cas où l'un des joueurs n'a pas la couleur demandée, il peut soit jouer une carte de la couleur de l'atout \npour couper et remporter le pli ou bien donner une carte de son choix, mais il perdra le pli.");
        text1.setBorder(null);
        text1.setFocusable(false);
        text1.setOpaque(false);
        panel.add(text1);
        text1.setBounds(70, 100, 970, 290);

        label2.setFont(new Font("Times New Roman", 3, 36)); 
        label2.setForeground(new Color(255, 255, 255));
        label2.setText("Mise en place");
        panel.add(label2);
        label2.setBounds(150, 400, 220, 51);

        text2.setEditable(false);
        text2.setBackground(new Color(58, 140, 140));
        text2.setColumns(20);
        text2.setFont(new Font("Times New Roman", 1, 20)); 
        text2.setForeground(new Color(255, 255, 255));
        text2.setRows(5);
        text2.setText("Le joueur désigné comme donneur, distribue les cartes une à la fois, de sorte que chaque joueur en dispose de 11. \n\nEnsuite, il dispose au centre du plateau le reste des cartes en 6 piles de 5 cartes faces cachées, puis il retourne la \ncarte au sommet de chaque pile. Parmi les 6 cartes visibles, la carte la plus forte donne la couleur de l'atout. \n\nNéanmoins, la partie peut se jouer sans atout si aucune des 6 cartes n'est supèrieure ou égale à 10.\n\nIl faut respecter la hiérarchie des couleurs : Pique, Coeur, Carreau ensuite Trèfle.\n\n\n\n\n\n\n\n\n\n");
        text2.setBorder(null);
        text2.setFocusable(false);
        text2.setOpaque(false);
        panel.add(text2);
        text2.setBounds(70, 470, 950, 200);

        label3.setFont(new Font("Times New Roman", 3, 36)); 
        label3.setForeground(new Color(255, 255, 255));
        label3.setText("Comment jouer");
        panel.add(label3);
        label3.setBounds(150, 20, 290, 51);

        text3.setEditable(false);
        text3.setBackground(new Color(58, 140, 140));
        text3.setColumns(20);
        text3.setFont(new Font("Times New Roman", 1, 20)); 
        text3.setForeground(new Color(255, 255, 255));
        text3.setRows(5);
        text3.setText("Le joueur qui a donné les cartes a initialement la main. Par la suite, elle est prise (ou gardée) par le joueur qui \na remporté le pli.\n\nLe joueur qui a la main choisit une des cartes en sa possession et la pose sur le plateau. L'autre joueur pose à \nson tour une carte (il doit obligatoirement fournir la couleur demandée par le premier joueur s'il le peut). \nLe joueur qui a gagné le pli le ramasse et le met de coté dans sa défausse.\n\nLes joueurs vont maintenant piocher une carte parmi les cartes révélées sur le plateau; le joueur qui vient de \ngagner le pli choisi sa carte en premier et la place dans sa main. \n\nIl retourne ensuite la carte qui se trouvait au dessous de la carte piochée. L'autre joueur choisi à son tour la \ncarte de son choix de la même manière.\n\nNote: Lorsque toutes les piles sont épuisées, la manche continue selon les mêmes règles, en supprimant la phase \nde choix/découverte des cartes sur le plateau.\n\nLa manche se termine lorsque les 2 joueurs n'ont plus de carte en main. Chacun des joueurs se voit attribuer \nun nombre de points correspondants au nombre de plis qu'il a gagné. \n\nOn peut alors démarrer une nouvelle manche. ");
        text3.setBorder(null);
        text3.setFocusable(false);
        text3.setOpaque(false);
        panel.add(text3);
        text3.setBounds(70, 100, 960, 480);

        next.setBackground(new Color(204, 204, 204));
        next.setIcon(new ImageIcon(getClass().getResource("/images/next.png"))); 
        next.setBorder(null);
        next.setBorderPainted(false);
        next.setContentAreaFilled(false);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                prev.setVisible(true);
                next.setVisible(false);
                label3.setVisible(true);
                text3.setVisible(true);
                label1.setVisible(false);
                text1.setVisible(false);
                label2.setVisible(false);
                text2.setVisible(false);
            }
        });
        panel.add(next);
        next.setBounds(990, 660, 60, 50);

        prev.setBackground(new Color(204, 204, 204));
        prev.setIcon(new ImageIcon(getClass().getResource("/images/previous.png"))); 
        prev.setBorder(null);
        prev.setBorderPainted(false);
        prev.setContentAreaFilled(false);
        prev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                prev.setVisible(false);
                next.setVisible(true);
                label3.setVisible(false);
                text3.setVisible(false);
                label1.setVisible(true);
                text1.setVisible(true);
                label2.setVisible(true);
                text2.setVisible(true);
            }
        });
        panel.add(prev);
        prev.setBounds(20, 660, 60, 50);

        GroupLayout layout = new GroupLayout(reglesf.getContentPane());
        reglesf.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel, GroupLayout.PREFERRED_SIZE, 1066, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panel, GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
        );
        reglesf.add(panel);
    }
    
    
    
    //LA fenetre pour configurer la partie 
    private void configFrame() {
        
        configf = new JFrame();
        ButtonGroup btnGrp1 = new ButtonGroup();
        ButtonGroup btnGrp2 = new ButtonGroup();
        ButtonGroup btnGrp3 = new ButtonGroup();
        JPanel Joueurs = new JPanel();
        JPanel condition = new JPanel();
        JLabel fond = new JLabel();
        JLabel titre = new JLabel();
        JLabel img = new JLabel();
        JButton newpartie = new JButton();
        JButton menu = new JButton();
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        humanJ1 = new JRadioButton();
        IAJ1 = new JRadioButton();
        humanJ2 = new JRadioButton();
        IAJ2 = new JRadioButton();
        nameJ1 = new JTextField();
        nameJ2 = new JTextField();
        typeIAJ1 = new JComboBox<>();
        typeIAJ2 = new JComboBox<>(); 
        radiobtn1 = new JRadioButton();
        radiobtn2 = new JRadioButton();
        points = new JTextField();
        manches = new JTextField();
        
        configf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        configf.setTitle("Bridge Chinois");
        configf.setResizable(false);
        configf.setSize(1010, 620);
        configf.getContentPane().setLayout(null);
        
        humanJ1.setSelected(true);
        humanJ2.setSelected(true);
        radiobtn1.setSelected(true);

        titre.setFont(new Font("Times New Roman", 3, 36)); 
        titre.setForeground(new Color(255, 255, 255));
        titre.setHorizontalAlignment(SwingConstants.CENTER);
        titre.setText("Configuration de la partie");
        configf.getContentPane().add(titre);
        titre.setBounds(280, 20, 387, 42);

        img.setIcon(new ImageIcon(getClass().getResource("/images/as-de-pique.png")));
        configf.getContentPane().add(img);
        img.setBounds(670, 0, 64, 64);

        condition.setBorder(createTitledBorder(createEtchedBorder(), "Condition de victoire", TitledBorder.CENTER, TitledBorder.TOP, new Font("Times New Roman", 3, 26), new Color(255, 255, 255)));
        condition.setOpaque(false);
        condition.setLayout(null);

        btnGrp3.add(radiobtn1);
        radiobtn1.setFont(new Font("Times New Roman", 1, 19));
        radiobtn1.setForeground(new Color(255, 255, 255));
        radiobtn1.setText("Point(s)");
        radiobtn1.setOpaque(false);
        radiobtn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                manches.setEnabled(false);
                manches.setText("5");
                points.setEnabled(true);
            }
        });
        condition.add(radiobtn1);
        radiobtn1.setBounds(71, 90, 113, 49);

        btnGrp3.add(radiobtn2);
        radiobtn2.setFont(new Font("Times New Roman", 1, 19));
        radiobtn2.setForeground(new Color(255, 255, 255));
        radiobtn2.setText("Manche(s)");
        radiobtn2.setOpaque(false);
        radiobtn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                points.setEnabled(false);
                points.setText("5");
                manches.setEnabled(true);
            }
        });
        condition.add(radiobtn2);
        radiobtn2.setBounds(71, 206, 113, 49);

        points.setFont(new Font("Times New Roman", 0, 20));
        points.setText("5");
        condition.add(points);
        points.setBounds(226, 90, 94, 49);

        manches.setFont(new Font("Times New Roman", 0, 20));
        manches.setText("5");
        manches.setEnabled(false);
        condition.add(manches);
        manches.setBounds(226, 206, 94, 49);

        configf.getContentPane().add(condition);
        condition.setBounds(550, 90, 392, 358);

        Joueurs.setBorder(createTitledBorder(createEtchedBorder(), "Joueurs", TitledBorder.CENTER, TitledBorder.TOP, new Font("Times New Roman", 3, 26), new Color(255, 255, 255)));
        Joueurs.setOpaque(false);
        Joueurs.setLayout(null);

        label1.setFont(new Font("Times New Roman", 1, 24)); 
        label1.setForeground(new Color(255, 255, 255));
        label1.setText("Joueur 1");
        Joueurs.add(label1);
        label1.setBounds(52, 44, 130, 42);

        btnGrp1.add(humanJ1);
        humanJ1.setFont(new Font("Times New Roman", 1, 19)); 
        humanJ1.setForeground(new Color(255, 255, 255));
        humanJ1.setText("Humain");
        humanJ1.setOpaque(false);
        humanJ1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                typeIAJ1.setEnabled(false);
                typeIAJ1.setSelectedIndex(0);
                nameJ1.setEnabled(true);
            }
        });
        Joueurs.add(humanJ1);
        humanJ1.setBounds(52, 93, 130, 42);
        
        nameJ1.setFont(new Font("Times New Roman", 0, 20)); 
        nameJ1.setText("joueur1");
        nameJ1.setSize(new Dimension(61, 30));
        Joueurs.add(nameJ1);
        nameJ1.setBounds(213, 92, 140, 43);

        btnGrp1.add(IAJ1);
        IAJ1.setFont(new Font("Times New Roman", 1, 19));
        IAJ1.setForeground(new Color(255, 255, 255));
        IAJ1.setText("Ordinateur");
        IAJ1.setOpaque(false);
        IAJ1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                typeIAJ1.setEnabled(true);
                nameJ1.setEnabled(false);
                nameJ1.setText("joueur1");
            }
        });
        Joueurs.add(IAJ1);
        IAJ1.setBounds(52, 141, 130, 42);

        typeIAJ1.setFont(new Font("Times New Roman", 0, 20));
        typeIAJ1.setModel(new DefaultComboBoxModel<>(new String[] { "Aléatoire", "Facile" }));
        typeIAJ1.setEnabled(false);
        Joueurs.add(typeIAJ1);
        typeIAJ1.setBounds(213, 141, 140, 43);

        label2.setFont(new Font("Times New Roman", 1, 24));
        label2.setForeground(new Color(255, 255, 255));
        label2.setText("Joueur 2");
        Joueurs.add(label2);
        label2.setBounds(52, 195, 130, 44);

        btnGrp2.add(humanJ2);
        humanJ2.setFont(new Font("Times New Roman", 1, 19));
        humanJ2.setForeground(new Color(255, 255, 255));
        humanJ2.setText("Humain");
        humanJ2.setOpaque(false);
        humanJ2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                typeIAJ2.setEnabled(false);
                typeIAJ2.setSelectedIndex(0);
                nameJ2.setEnabled(true);
            }
        });
        Joueurs.add(humanJ2);
        humanJ2.setBounds(52, 246, 130, 42);

        nameJ2.setFont(new Font("Times New Roman", 0, 20));
        nameJ2.setText("joueur2");
        nameJ2.setSize(new Dimension(61, 30));
        Joueurs.add(nameJ2);
        nameJ2.setBounds(213, 245, 140, 43);

        btnGrp2.add(IAJ2);
        IAJ2.setFont(new Font("Times New Roman", 1, 19));
        IAJ2.setForeground(new Color(255, 255, 255));
        IAJ2.setText("Ordinateur");
        IAJ2.setOpaque(false);
        IAJ2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                typeIAJ2.setEnabled(true);
                nameJ2.setEnabled(false);
                nameJ2.setText("joueur2");
            }
        });
        Joueurs.add(IAJ2);
        IAJ2.setBounds(52, 294, 130, 42);

        typeIAJ2.setFont(new Font("Times New Roman", 0, 20));
        typeIAJ2.setModel(new DefaultComboBoxModel<>(new String[] { "Aléatoire", "Facile" }));
        typeIAJ2.setEnabled(false);
        Joueurs.add(typeIAJ2);
        typeIAJ2.setBounds(213, 294, 140, 43);

        configf.getContentPane().add(Joueurs);
        Joueurs.setBounds(70, 90, 392, 358);

        newpartie.setFont(new Font("Times New Roman", 0, 20)); 
        newpartie.setText("Lancer partie");
        newpartie.setBorder(createBevelBorder(BevelBorder.RAISED));
        newpartie.setPreferredSize(new Dimension(80, 40));
        newpartie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                demarrer();
            }
        });
        configf.getContentPane().add(newpartie);
        newpartie.setBounds(430, 460, 150, 40);

        menu.setFont(new Font("Times New Roman", 0, 20));
        menu.setText("Menu principal");
        menu.setBorder(createBevelBorder(BevelBorder.RAISED));
        menu.setPreferredSize(new Dimension(80, 40));
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                InterfaceGraphique i = new InterfaceGraphique(j,c);
                i.setVisible(true);
                configf.dispose();
            }
        });
        configf.getContentPane().add(menu);
        menu.setBounds(430, 510, 150, 40);

        fond.setIcon(new ImageIcon(getClass().getResource("/images/hh.jpg")));
        configf.getContentPane().add(fond);
        fond.setBounds(0, 0, 1010, 580);
    }  
    
    
    
    public void demarrer() {
        try{
            VueGraphique gf = new VueGraphique(j,c);
            gf.nomJ1.setText(nameJ1.getText());
            gf.nomJ2.setText(nameJ2.getText());
            if(radiobtn1.isSelected()){
                gf.mode.setText(radiobtn1.getText());
                gf.nbr.setText(points.getText());
            } else{
                gf.mode.setText(radiobtn2.getText());
                gf.nbr.setText(manches.getText());
            }
            Configuration();
            gf.metAJour();
            gf.setVisible(true);
            gf.setLocationRelativeTo(null);
            configf.dispose();
        }catch(Exception e){
            JOptionPane.showMessageDialog(configf, "Vous devez remplir tout les champs !");
        }
    }
        

    public void Configuration() {
        //Condition de victoire
        String modeJeu = null;
        int nombre = 0;
        if(radiobtn1.isSelected()){
            modeJeu = "p";
            nombre = Integer.parseInt(points.getText());
        } else{
            if(radiobtn2.isSelected()){
                modeJeu = "m";
                nombre = Integer.parseInt(manches.getText());
            }
        }
        j.ModeV(modeJeu, nombre);

        //Type joueur1
        String typeJ1 = null;
        if(humanJ1.isSelected()){
            typeJ1 = "non";
        } else{
            switch (typeIAJ1.getSelectedItem().toString()) {
                case "Facile":
                    typeJ1 = "easy";
                    break;
                case "Aléatoire":
                    typeJ1 = "alea";
                    break;
                default:
                    typeJ1 = "alea";
            }
        }
        j.ModeJoueur(1, typeJ1);
        
        //Type joueur2
        String typeJ2 = null;
        if(humanJ2.isSelected()){
            typeJ2 = "non";
        } else{
            switch (typeIAJ2.getSelectedItem().toString()) {
                case "Facile":
                    typeJ2 = "easy";
                    break;
                case "Aléatoire":
                    typeJ2 = "alea";
                    break;
                default:
                    typeJ2 = "alea";
            }
        }
        j.ModeJoueur(2, typeJ2);
    }
}
