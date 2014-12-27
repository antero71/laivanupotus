/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.kayttoliittyma.kuuntelijat;

import com.antero.laivanupotus.kayttoliittyma.Kayttoliittyma2;
import com.antero.laivanupotus.kayttoliittyma.NaytonRuutu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Antero Oikkonen
 */
public class Ammuskuuntelija implements ActionListener{

    private int x;
    private int y;
    private Kayttoliittyma2 kl2;
    private final NaytonRuutu naytonRuutu;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
         
    }

    public Ammuskuuntelija(Kayttoliittyma2 kl2, int x, int y, NaytonRuutu naytonRuutu) {
        this.kl2=kl2;
        this.x = x;
        this.y = y;
        this.naytonRuutu = naytonRuutu;
    }
    
    
    
}
