/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.kayttoliittyma.kuuntelijat;

import com.antero.laivanupotus.domain.Pelaaja;
import com.antero.laivanupotus.domain.Pistetaulukko;
import com.antero.laivanupotus.domain.Ruutu;
import com.antero.laivanupotus.kayttoliittyma.Kayttoliittyma;
import com.antero.laivanupotus.kayttoliittyma.NaytonRuutu;
import com.antero.laivanupotus.logiikka.PeliLogiikka;
import com.antero.laivanupotus.tiedostot.Tiedosto;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author Antero Oikkonen
 */
public class Ammuskuuntelija implements ActionListener {

    private int x;
    private int y;
    private Kayttoliittyma kali;
    private PeliLogiikka logiikka;
    private final NaytonRuutu naytonRuutu;
    private static Collection<NaytonRuutu> osutut = new ArrayList();

    @Override
    public void actionPerformed(ActionEvent e) {

        if (kali.getPelilogiikka().getTietokoneenPeli().ammu(naytonRuutu.getRuutu())) {
            naytonRuutu.setText("X");
            naytonRuutu.setBackground(Color.RED);
            naytonRuutu.setBorder(null);
            osutut.add(naytonRuutu);

            muutaPisteita(100);
            if (kali.getPelilogiikka().getTietokoneenPeli().upposiko(naytonRuutu.getRuutu())) {
                muutaPisteita(1000);
                muutaOsututMustiksi();
                osutut.clear();
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
            boolean merkattu = false;
            while (logiikka.getAi().ammu()) {

                merkkaaAmmunta(true);
                merkattu = true;

            }
            if (!merkattu) {
                merkkaaAmmunta(false);
            }

        }
    }

    private void printComponents(Component[] comp) {
        for (int i = 0; i < comp.length; i++) {
            System.out.println(comp[i].getName());
        }
    }

    private void muutaPisteita(int muutos) {
        int pisteet = Integer.parseInt(kali.getPistenaytto().getPelaajanPisteet().getText());
        pisteet += muutos;
        kali.getPistenaytto().getPelaajanPisteet().setText("" + pisteet);

    }

    public Ammuskuuntelija(Kayttoliittyma kl2, int x, int y, NaytonRuutu naytonRuutu, PeliLogiikka logiikka) {
        this.kali = kl2;
        this.x = x;
        this.y = y;
        this.naytonRuutu = naytonRuutu;
        this.logiikka = logiikka;

    }

    private Pistetaulukko luoPistetaulukko() {
        String pisteet = kali.getPistenaytto().getPelaajanPisteet().getText();
        String nimi = kali.getPistenaytto().getPelaajanNimi().getText();

        Pelaaja p = new Pelaaja(nimi, Integer.parseInt(pisteet));
        Pistetaulukko pistaul = new Pistetaulukko();
        pistaul.lisaaPelaajaPistetaulukkoon(p);
        return pistaul;

    }

    private void muutaOsututMustiksi() {
        for (NaytonRuutu r : osutut) {
            r.setBackground(Color.BLACK);
        }
    }

    private void merkkaaAmmunta(boolean osui) {

        Ruutu r = logiikka.getAi().viimeksiAmmuttu();
        JPanel panel = kali.getPelaajanRuudukkoPanel();
        
        System.out.println("ammuttu "+r.getX()+","+r.getY());
        
        int x = 0;
        int y = 0;
        
        if(r.getX()==0){
            x = 1;
        }else{
            x = r.getX();
        }
        
        if(r.getY()==0){
            y = 1;
        }else{
            y = r.getY();
        }
            
        
        System.out.println(""+panel.getComponentAt(x*30, y*30).getName());
        System.out.println("dimension "+panel.getBounds());
        
        x*=30;
        y*=30;
        
        System.out.println("haettava komponentti "+x+","+y);
        
        NaytonRuutu nt = (NaytonRuutu) panel.getComponentAt(x, y);
        if (osui) {
            nt.setBorder(null);
            nt.setText("X");
            nt.setBackground(Color.red);
        } else {
            nt.setText("o");
        }

    }

}
