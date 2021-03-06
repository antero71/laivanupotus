/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.kayttoliittyma;

import com.antero.laivanupotus.domain.Laiva;
import com.antero.laivanupotus.domain.Pelaaja;
import com.antero.laivanupotus.domain.Pistetaulukko;
import com.antero.laivanupotus.domain.Ruutu;
import com.antero.laivanupotus.kayttoliittyma.kuuntelijat.Ammuskuuntelija;
import com.antero.laivanupotus.kayttoliittyma.kuuntelijat.AsetaLaivaKuuntelija;
import com.antero.laivanupotus.logiikka.AI;
import com.antero.laivanupotus.logiikka.LaivojenPaikkojenArpoja;
import com.antero.laivanupotus.logiikka.PeliLogiikka;
import com.antero.laivanupotus.logiikka.Pelikentta;
import com.antero.laivanupotus.logiikka.TietokoneAI;
import com.antero.laivanupotus.tiedostot.Tiedosto;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * Alussa luokka näyttää valikon ja tervetuloaviestin. Kun peli aloitetaan,
 * näyttöön piirretään tietokoneen pelialue sekä pelaajan pelialue sekä
 * pistenäytöt.
 *
 * Alkunäyttö -luokka muodostaa Käyttöliittymän kun pelaaja on antanut nimensä
 * ja valinnut pelialueen koon.
 *
 * @author Antero Oikkonen
 */
public class Kayttoliittyma extends JFrame {

    private PeliLogiikka pelilogiikka;
    private PistenayttoPanel pistenaytto;
    private PistenayttoPanel tietokoneenPistenaytto;
    private Pelaaja pelaaja;

    private JMenu aloitaMenu = new javax.swing.JMenu();
    private JMenuItem uusiPeliItem = new javax.swing.JMenuItem();
    private javax.swing.JMenuBar menuBar = new JMenuBar();

    private JPanel panel = new JPanel();

    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem lopetaItem;
    private javax.swing.JLabel tervetuloaLabel;
    private javax.swing.JMenuItem naytaPisteetItem;
    private JPanel pelaajanRuudukkoPanel;
    private Laiva valittuLaiva;

    public Kayttoliittyma() {
        this(10, 10, true);
    }

    public Laiva getValittuLaiva() {
        return valittuLaiva;
    }

    public PeliLogiikka getPelilogiikka() {
        return pelilogiikka;
    }

    public PistenayttoPanel getPistenaytto() {
        return pistenaytto;
    }

    public PistenayttoPanel getTietokoneenPistenaytto() {
        return tietokoneenPistenaytto;
    }

    public void createPistenaytto() {
        if (pistenaytto == null) {
            pistenaytto = new PistenayttoPanel();
        }

    }

    public void createTietokoneenPistenaytto() {
        if (tietokoneenPistenaytto == null) {
            tietokoneenPistenaytto = new PistenayttoPanel();
        }
    }

    public Kayttoliittyma(int x, int y, boolean naytaTervetuloa) {
        pelilogiikka = new PeliLogiikka(x, y, null);
        Pelikentta p = pelilogiikka.getPelaajanPeli();
        AI ai = new TietokoneAI(p);
        pelilogiikka.setAi(ai);
        initComponents(naytaTervetuloa);
    }

    // End of variables dec
    // private javax.swing.JLabel tervetuloaLabel = new JLabel();
    private void initComponents(boolean naytaTervetuloaNaytto) {

        lopetaItem = new javax.swing.JMenuItem();
        tervetuloaLabel = new javax.swing.JLabel();
        luoAloitaMenu();

        menuBar.add(aloitaMenu);

        jMenuBar2.add(aloitaMenu);

        setJMenuBar(jMenuBar2);
        this.setTitle("Laivanupotus");

        if (naytaTervetuloaNaytto) {
            JPanel tervetuloaPanel = new JPanel();
            JLabel tervetuloaLabel = new JLabel();
            tervetuloaLabel.setText("Tervetuloa laivanupotuspeliin!");
            tervetuloaPanel.add(tervetuloaLabel);
            this.getContentPane().add(tervetuloaPanel);
        }
        // setPreferredSize(new Dimension(300,300));
        pack();

        setVisible(true);

    }

    private void luoAloitaMenu() {
        jMenuBar2 = new javax.swing.JMenuBar();
        aloitaMenu = new javax.swing.JMenu();
        uusiPeliItem = new javax.swing.JMenuItem();
        naytaPisteetItem = new javax.swing.JMenuItem();
        naytaPisteetItem.setText("Pelin pisteet");

        lopetaItem.setText("Lopeta");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel.add(aloitaMenu);
        panel.add(tervetuloaLabel);
        aloitaMenu.setText("Laivanupotus");

        uusiPeliItem.setText("Uusi peli");
        uusiPeliItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uusiPeliItemActionPerformed(evt);
            }
        });

        lopetaItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
            }
        });

        naytaPisteetItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                naytaPisteetItemActionPerformed(evt);
            }

            private void naytaPisteetItemActionPerformed(ActionEvent evt) {

                Pistetaulukko pisteet = Tiedosto.luePistetaulukkoTiedostosta();

                PistenayttoSivu pistanaytto = new PistenayttoSivu(pisteet);
                pistanaytto.pack();
                pistanaytto.setVisible(true);

            }
        });

        aloitaMenu.add(uusiPeliItem);
        aloitaMenu.add(naytaPisteetItem);
        aloitaMenu.add(lopetaItem);
    }

    private void uusiPeliItemActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
            /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Alkunaytto().setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Kayttoliittyma().setVisible(true);
            }
        });
    }

    public void setPelaaja(Pelaaja pelaaja) {
        this.pelaaja = pelaaja;
        tervetuloaLabel.setText("Tervetuloa: " + pelaaja.getNimi());
    }

    public JPanel luoRuudukko(boolean tietokoneenPeli) {
        GridLayout layout = new GridLayout(pelilogiikka.getPelaajanPeli().getKenttaX(), pelilogiikka.getPelaajanPeli().getKenttaY());
        JPanel pelipanel = new JPanel(layout);
        //this.peli.valmisteleAlusta();
        if (tietokoneenPeli) {
            pelilogiikka.alustaTietokoneenLaivat();
        } else {
            pelilogiikka.alustaPelaajanLaivat();
        }

        for (int i = 0; i < pelilogiikka.getPelaajanPeli().getKenttaY(); i++) {
            for (int j = 0; j < pelilogiikka.getPelaajanPeli().getKenttaX(); j++) {
                Ruutu ruutu = new Ruutu(i, j);
                NaytonRuutu naytonRuutu = new NaytonRuutu(ruutu);
                naytonRuutu.setPreferredSize(new Dimension(30, 30));
                naytonRuutu.setName(j + "," + i);

                if (!tietokoneenPeli) {
                    if (pelilogiikka.getPelaajanPeli().isLaiva(j, i)) {
                        //System.out.println("laiva " + j + "," + i);
                        naytonRuutu.setText("X");
                    }
                }

                if (tietokoneenPeli) {
                    naytonRuutu.addActionListener(new Ammuskuuntelija(this, i, j, naytonRuutu, pelilogiikka));
                }
                pelipanel.add(naytonRuutu);
            }

        }

        return pelipanel;
    }

    public void setValittuLaiva(Laiva l) {
        this.valittuLaiva = l;
    }

    public void setPelaajanRuudukko(JPanel pelaajanRuudukkoPanel) {
        this.pelaajanRuudukkoPanel = pelaajanRuudukkoPanel;
    }

    public JPanel getPelaajanRuudukkoPanel() {
        return pelaajanRuudukkoPanel;
    }

}
