/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.kayttoliittyma.kuuntelijat;

import com.antero.laivanupotus.domain.Laiva;
import com.antero.laivanupotus.kayttoliittyma.Kayttoliittyma;
import com.antero.laivanupotus.kayttoliittyma.NaytonRuutu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Antero Oikkonen
 */
public class AsetaLaivaKuuntelija implements ActionListener {

    private int x;
    private int y;
    private Kayttoliittyma kali;
    private final NaytonRuutu naytonRuutu;

    public AsetaLaivaKuuntelija(int x, int y, Kayttoliittyma kali, NaytonRuutu naytonRuutu) {
        this.x = x;
        this.y = y;
        this.kali = kali;
        this.naytonRuutu = naytonRuutu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        NaytonRuutu r = (NaytonRuutu)e.getSource();
        ///Laiva l = new Laiva();
        
        ///kali.getPelilogiikka().getPelaajanPeli().asetaLaiva(null, true)
        
        
    }

}
