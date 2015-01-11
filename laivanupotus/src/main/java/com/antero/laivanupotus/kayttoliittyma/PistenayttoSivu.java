/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.kayttoliittyma;

import com.antero.laivanupotus.domain.Pelaaja;
import com.antero.laivanupotus.domain.Pistetaulukko;
import java.awt.GridLayout;
import java.util.TreeSet;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Näytä pisteet toiminto avaa tämän sivun. Sivulle muodostetaan pistetilanne
 * parametrina annettavan <code>Pistetaulukko</code> mukaan
 *
 * @author Antero Oikkonen
 */
public class PistenayttoSivu extends JFrame {

    Pistetaulukko pisteet;

    /**
     * Paramerina Pistetaulukko jonka mukaan pisteet tulostetaan näytölle.
     *
     * @param pisteet
     */
    public PistenayttoSivu(Pistetaulukko pisteet) {
        this.pisteet = pisteet;
        initComponents();
    }

    public void setPisteet(Pistetaulukko pisteet) {
        this.pisteet = pisteet;
    }

    private void initComponents() {
        TreeSet<Pelaaja> pelaajat = pisteet.getPelaajat();
        int size = pelaajat.size();
        GridLayout layout = null;
        JPanel panel = null;
        if (pelaajat.size() == 0) {
            size = 1;
            JLabel l = new JLabel();
            l.setText("Pistetaulukkoa ei ole vielä tallennettu");
            layout = new GridLayout(size, 1);

            panel = new JPanel(layout);
            panel.add(l);

        } else {

            layout = new GridLayout(size, 1);

            panel = new JPanel(layout);

            int i = 1;

            for (Pelaaja p : pelaajat) {
                JLabel label = new JLabel();
                label.setText(p.getNimi() + " " + p.getPisteet());
                panel.add(label);
            }
        }
        this.add(panel);

    }

    //public 
}
