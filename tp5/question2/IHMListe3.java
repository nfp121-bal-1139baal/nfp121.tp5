package question2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.ArrayList;
/**
 * Classe IHMListe2.
 * 
 * @author Oziol Joris
 * @version 07/11/2016
 */
public class IHMListe3 extends JPanel implements ActionListener, ItemListener {

    private JPanel cmd = new JPanel();
    private JLabel afficheur = new JLabel();
    private JTextField saisie = new JTextField();

    private JPanel panelBoutons = new JPanel();
    private JButton boutonRechercher = new JButton("rechercher");
    private JButton boutonRetirer = new JButton("retirer");

    private CheckboxGroup mode = new CheckboxGroup();
    private Checkbox ordreCroissant = new Checkbox("croissant", mode, false);
    private Checkbox ordreDecroissant = new Checkbox("décroissant", mode, false);

    private JButton boutonOccurrences = new JButton("occurrence");

    private JButton boutonAnnuler = new JButton("annuler");

    private TextArea texte = new TextArea();

    private List<String> liste;
    private Map<String, Integer> occurrences;

    

    private Originator originator;
    private Caretaker caretaker ;
    
    
    public IHMListe3(List<String> liste, Map<String, Integer> occurrences) {
        this.liste = liste;
        this.occurrences = occurrences;

        //Nouvelle pile
        
        originator = new Originator();
        caretaker = new Caretaker();
        
        
        
        cmd.setLayout(new GridLayout(3, 1));
        cmd.add(afficheur);
        cmd.add(saisie);

        panelBoutons.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelBoutons.add(boutonRechercher);
        panelBoutons.add(boutonRetirer);
        panelBoutons.add(new JLabel("tri du texte :"));
        panelBoutons.add(ordreCroissant);
        panelBoutons.add(ordreDecroissant);
        panelBoutons.add(boutonOccurrences);
        panelBoutons.add(boutonAnnuler);
        cmd.add(panelBoutons);

        afficheur.setText(liste.getClass().getName() + " et "
            + occurrences.getClass().getName());
        texte.setText(liste.toString());

        setLayout(new BorderLayout());

        add(cmd, "North");
        add(texte, "Center");

        boutonRechercher.addActionListener(this);
        boutonRetirer.addActionListener(this);
        boutonOccurrences.addActionListener(this);
        ordreCroissant.addItemListener(this);
        ordreDecroissant.addItemListener(this);

        saisie.addActionListener(this);//pour la touche ENTER
        boutonAnnuler.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        try {

            boolean res = false;
            if (ae.getSource() == boutonRechercher || ae.getSource() == saisie) {

                res = liste.contains(saisie.getText());
                Integer occur = occurrences.get(saisie.getText());
                afficheur.setText("résultat de la recherche de : "
                    + saisie.getText() + " -->  " + res);

            } else if (ae.getSource() == boutonRetirer) {
                List<String> listeBis = new ArrayList<String>(this.liste);
                res = retirerDeLaListeTousLesElementsCommencantPar(saisie.getText());
                if(res) {sauvegarder(listeBis);}
                afficheur.setText("résultat du retrait de tous les éléments commençant par -->  "
                    + saisie.getText() + " : " + res );

            } else if (ae.getSource() == boutonOccurrences) {

                Integer occur = occurrences.get(saisie.getText());
                if (occur != null ){
                    afficheur.setText(" -->  " + occur + " occurrence(s)");
                }else{
                    afficheur.setText(" -->  ??? ");
                }
            }else if(ae.getSource() == boutonAnnuler){
                //Bouton annuler
                
                try{
                    if(!caretaker.isEmpty()){
                        liste = originator.restoreFromMemento(caretaker.getMemento());
                        occurrences = Chapitre2CoreJava2.occurrencesDesMots(this.liste); //recalcule des occurrences systématique donc pas besoin de les sauver !!!!!!!

                    } else{
                    }
                } catch (Exception e){}
            }
            texte.setText(liste.toString());

        } catch (Exception e) {
            afficheur.setText(e.toString());
        }
    }

    public void itemStateChanged(ItemEvent ie) {
         List<String> listeBis = new ArrayList<String>(this.liste);
        boolean res = false;
        if (ie.getSource() == ordreCroissant){
            res = true;
            if(res) {sauvegarder( listeBis);}
            Collections.sort(this.liste);
        }else if (ie.getSource() == ordreDecroissant){
            res = true;
            if(res) {sauvegarder( listeBis);}
            Collections.sort(this.liste, Collections.reverseOrder());
        }
        texte.setText(liste.toString());
    }

    private boolean retirerDeLaListeTousLesElementsCommencantPar(String prefixe) {
        boolean resultat = false;
        List<String> temp = this.liste;
        Iterator<String> iter = temp.iterator();
        while(iter.hasNext()) {
            String s = iter.next();

            if (s.startsWith(prefixe)) {
                iter.remove();
                resultat = true;
                this.occurrences.put(s, 0);
            }
        }
        return resultat;
    }

    private void sauvegarder(List<String> listeBis){
         listeBis = new ArrayList<>(this.liste);
        originator.set(listeBis);//memento// 
        caretaker.addMemento(originator.saveToMemento());//memento
    }
}