/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.kayttoliittyma.kuuntelijat;

import com.antero.laivanupotus.domain.Laiva;
import com.antero.laivanupotus.domain.Suunta;
import com.antero.laivanupotus.kayttoliittyma.Kayttoliittyma;
import com.antero.laivanupotus.kayttoliittyma.NaytonRuutu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

/**
 *
 * Luokka on tällä hetkellä käyttämättä (11.1.2015)
 * laivat asetetaan tietokoneen arvonnalla sekä pelaajalle että
 * tietokoneelle koska en saanut tätä laivan asetusta toimimaan.
 * 
 * @author Antero Oikkonen
 */
public class AsetaLaivaKuuntelija implements ActionListener {

    private int x;
    private int y;
    private Kayttoliittyma kali;
    private final NaytonRuutu naytonRuutu;
    private JPanel ruudukko;

    public AsetaLaivaKuuntelija(int x, int y, Kayttoliittyma kali, NaytonRuutu naytonRuutu, JPanel pelaajanRuudukkoPanel) {
        this.x = x;
        this.y = y;
        this.kali = kali;
        this.naytonRuutu = naytonRuutu;
        this.ruudukko = pelaajanRuudukkoPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        NaytonRuutu r = (NaytonRuutu) e.getSource();
        Laiva l = kali.getValittuLaiva();

        if (l != null) {
            l.asetaLaivanPaikka(r.getRuutu().getY(), r.getRuutu().getX(), Suunta.VAAKA);

            System.out.println("laivan paikka x " + r.getRuutu().getX() + ", y " + r.getRuutu().getY());

            if (kali.getPelilogiikka().getPelaajanPeli().asetaLaiva(l, true)) {
                NaytonRuutu ruutu = (NaytonRuutu) ruudukko.getComponentAt(r.getX(), r.getY());
                int pituus = l.getLaivanPituus();
                int x = r.getX();
                for (int i = 0; i < pituus; i++) {

                    ruutu = (NaytonRuutu) ruudukko.getComponentAt(x, r.getY());
                    ruutu.setText("X");
                    x += 30;
                }
                kali.setValittuLaiva(null);
            } else {
                NaytonRuutu ruutu = (NaytonRuutu) ruudukko.getComponentAt(r.getX(), r.getY());
                ruutu.setText("" + l.getLaivanPituus());
            }
        }
        ///Laiva l = new Laiva();
        ///kali.getPelilogiikka().getPelaajanPeli().asetaLaiva(null, true)
    }

}
