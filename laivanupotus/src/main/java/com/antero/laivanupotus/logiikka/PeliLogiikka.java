/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.logiikka;

import com.antero.laivanupotus.domain.Pistetaulukko;

/**
 *
 * @author Antero Oikkonen
 */
public class PeliLogiikka {

    private Pelikentta tietokoneenPeli;
    private LaivojenPaikkojenArpoja arpoja;
    private Pistetaulukko pisteet;

    public PeliLogiikka(int x, int y) {
        tietokoneenPeli = new Pelikentta(x, y);
        arpoja = new LaivojenPaikkojenArpoja();
        pisteet = new Pistetaulukko();
    }

    public void alustaLaivat() {
        arpoja.alustaLaivat(tietokoneenPeli);
    }

}
