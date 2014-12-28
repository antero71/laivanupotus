/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.kayttoliittyma.kuuntelijat;

import com.antero.laivanupotus.domain.Pelaaja;
import com.antero.laivanupotus.domain.Pistetaulukko;
import com.antero.laivanupotus.kayttoliittyma.Kayttoliittyma;
import com.antero.laivanupotus.kayttoliittyma.NaytonRuutu;
import com.antero.laivanupotus.tiedostot.Tiedosto;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Antero Oikkonen
 */
public class Ammuskuuntelija implements ActionListener {

    private int x;
    private int y;
    private Kayttoliittyma kali;
    private final NaytonRuutu naytonRuutu;

    @Override
    public void actionPerformed(ActionEvent e) {

        if (kali.getPelilogiikka().getTietokoneenPeli().ammu(naytonRuutu.getRuutu())) {
            naytonRuutu.setText("X");
            naytonRuutu.setBackground(Color.RED);
            naytonRuutu.setBorder(null);

            muutaPisteita(100);
            if (kali.getPelilogiikka().getTietokoneenPeli().upposiko(naytonRuutu.getRuutu())) {
                muutaPisteita(1000);
            }
            if (kali.getPelilogiikka().getTietokoneenPeli().isPeliPaattynyt()) {
                kali.getPistenaytto().getIlmoitus().setText("Peli päättyi!");
                Pistetaulukko pisteet = luoPistetaulukko();
                Tiedosto.tallennaTiedosto(pisteet);
                return;
            }
        } else {
            naytonRuutu.setText("O");
            muutaPisteita(-50);
        }
    }

    private void muutaPisteita(int muutos) {
        int pisteet = Integer.parseInt(kali.getPistenaytto().getPelaajanPisteet().getText());
        pisteet += muutos;
        kali.getPistenaytto().getPelaajanPisteet().setText("" + pisteet);

    }

    public Ammuskuuntelija(Kayttoliittyma kl2, int x, int y, NaytonRuutu naytonRuutu) {
        this.kali = kl2;
        this.x = x;
        this.y = y;
        this.naytonRuutu = naytonRuutu;
    }

    private Pistetaulukko luoPistetaulukko() {
        String pisteet = kali.getPistenaytto().getPelaajanPisteet().getText();
        String nimi = kali.getPistenaytto().getPelaajanNimi().getText();
       
        Pelaaja p = new Pelaaja(nimi, Integer.parseInt(pisteet));
        Pistetaulukko pistaul = new Pistetaulukko();
        pistaul.lisaaPelaajaPistetaulukkoon(p);
        return pistaul;
        
    }

}
