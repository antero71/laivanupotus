/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.logiikka;

import com.antero.laivanupotus.domain.Ruutu;
import java.util.TreeSet;

/**
 *
 * @author Antero Oikkonen
 */
public class Pelikentta {

    private TreeSet<Ruutu> ruudut = new TreeSet();
    private int kenttaX = 10;
    private int kenttaY = 10;

    /**
     * mahdollistetaan pelikentän koon muutos (oletuksena 10 x 10 ruutua
     *
     * @param ruudut
     */
    public Pelikentta(int x, int y) {
        kenttaX = x;
        kenttaY = y;
    }

    public TreeSet<Ruutu> getRuudut() {
        return ruudut;
    }

    
    
    public void alustaRuudukko() {
        Ruutu r = null;
        for (int x = 1; x <= kenttaX; x++) {
            for (int y = 1; y <= kenttaY; y++) {
                r = new Ruutu();
                r.setX(x);
                r.setY(y);
                r.setKielletty(false);
                r.setLaivanOsa(false);
                ruudut.add(r);
            }

        }
        // for(int i = 0; i < ruudut.)
    }
    
    
}
