/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.kayttoliittyma;

import com.antero.laivanupotus.domain.Ruutu;
import javax.swing.JButton;

/**
 *
 * @author Antero Oikkonen
 */
public class NaytonRuutu extends JButton{
    Ruutu r = null;
    
    public NaytonRuutu(Ruutu r){
        super();
        this.r=r;
    }

    public Ruutu getRuutu() {
        return r;
    }
    
    
    
}
