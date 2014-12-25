/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.kayttoliittyma;

import com.antero.laivanupotus.domain.Pelaaja;
import com.antero.laivanupotus.domain.Ruutu;
import com.antero.laivanupotus.kayttoliittyma.kuuntelijat.Ammuskuuntelija;
import com.antero.laivanupotus.logiikka.AI;
import com.antero.laivanupotus.logiikka.LaivojenPaikkojenArpoja;
import com.antero.laivanupotus.logiikka.PeliLogiikka;
import com.antero.laivanupotus.logiikka.Pelikentta;
import com.antero.laivanupotus.logiikka.TietokoneAI;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.MenuBar;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author Antero Oikkonen
 */
public class Kayttoliittyma2 extends JFrame {

    private PeliLogiikka pelilogiikka;
    private Alkunaytto alkunaytto;
    private Pelaaja pelaaja;
    private Ammuskuuntelija tahtain;

    public Kayttoliittyma2() {
        pelilogiikka = new PeliLogiikka(10, 10, null);
        Pelikentta p = pelilogiikka.getTietokoneenPeli();
        AI ai = new TietokoneAI(p);
        pelilogiikka.setAi(ai);
        initComponents();
    }

    JMenu aloitaMenu = new javax.swing.JMenu();
    JMenuItem uusiPeliItem = new javax.swing.JMenuItem();
    private javax.swing.JMenuBar menuBar = new JMenuBar();

    JPanel panel = new JPanel();
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JLabel tervetuloaLabel;
    //private javax.swing.JMenuItem uusiPeliItem;
    // End of variables dec

    // private javax.swing.JLabel tervetuloaLabel = new JLabel();
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        tervetuloaLabel = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        aloitaMenu = new javax.swing.JMenu();
        uusiPeliItem = new javax.swing.JMenuItem();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel.add(aloitaMenu);
        panel.add(tervetuloaLabel);
        aloitaMenu.setText("aloita");

        uusiPeliItem.setText("uusi peli");
        uusiPeliItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uusiPeliItemActionPerformed(evt);
            }
        });
        aloitaMenu.add(uusiPeliItem);

        menuBar.add(aloitaMenu);

        setJMenuBar(menuBar);

        aloitaMenu.add(uusiPeliItem);

        jMenuBar2.add(aloitaMenu);

        setJMenuBar(jMenuBar2);
        this.setTitle("Laivanupotus");
        pack();

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
                new Kayttoliittyma2().setVisible(true);
            }
        });
    }

    public void setPelaaja(Pelaaja pelaaja) {
        this.pelaaja = pelaaja;
        tervetuloaLabel.setText("Tervetuloa: " + pelaaja.getNimi());
    }

    public void luoRuudukko() {
        GridLayout layout = new GridLayout(pelilogiikka.getPelaajanPeli().getKenttaX(), pelilogiikka.getPelaajanPeli().getKenttaY());
        JPanel alusta = new JPanel(layout);
        //this.peli.valmisteleAlusta();
        pelilogiikka.alustaLaivat();

        for (int i = 0; i < pelilogiikka.getPelaajanPeli().getKenttaX(); i++) {
            for (int j = 0; j < pelilogiikka.getPelaajanPeli().getKenttaY(); j++) {
                Ruutu ruutu = new Ruutu(i, j);
                NaytonRuutu naytonRuutu = new NaytonRuutu(ruutu);
                naytonRuutu.setSize(30, 30);
                tahtain = new Ammuskuuntelija(this, i, j, naytonRuutu);
                naytonRuutu.addActionListener(tahtain);
                alusta.add(naytonRuutu);
            }

        }

        this.add(alusta,BorderLayout.SOUTH);
        this.pack();
    }

}
