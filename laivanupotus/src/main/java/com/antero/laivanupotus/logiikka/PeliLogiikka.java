/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.logiikka;

import com.antero.laivanupotus.domain.Laiva;
import com.antero.laivanupotus.domain.Pistetaulukko;
import java.util.Random;

/**
 * Luokka yhdistää tietokoneen ja pelaajan pelin, laivojen arvonnan sekä
 * tekoälyn ja pistelaskennan yhteen luokkaan.
 *
 * @author Antero Oikkonen
 */
public class PeliLogiikka {

    private Pelikentta tietokoneenPeli;
    private Pelikentta pelaajanPeli;
    private LaivojenPaikkojenArpoja arpoja;
    private Pistetaulukko pisteet;
    private AI ai;

    /**
     * alustetaan pelialueen koko ja käytettävä tekoäly
     * 
     * @param x
     * @param y
     * @param ai 
     */
    
    public PeliLogiikka(int x, int y, AI ai) {
        tietokoneenPeli = new Pelikentta(x, y);
        pelaajanPeli = new Pelikentta(x, y);
        arpoja = new LaivojenPaikkojenArpoja(new Random());
        pisteet = new Pistetaulukko();
        this.ai = ai;
    }

    public AI getAi() {
        return ai;
    }

    public void setAi(AI ai) {
        this.ai = ai;
    }

    /**
     * alustaa tietokoneen pelialueen laivat
     */
    
    public void alustaTietokoneenLaivat() {
        arpoja.alustaLaivat(tietokoneenPeli);
    }

    public boolean lisaaLaiva(Laiva laiva) {
        return pelaajanPeli.asetaLaiva(laiva, false);
    }

    public Pelikentta getTietokoneenPeli() {
        return tietokoneenPeli;
    }

    public Pelikentta getPelaajanPeli() {
        return pelaajanPeli;
    }

    public Pistetaulukko getPisteet() {
        return pisteet;
    }

    /**
     * alustaa pelaajan pelialueen laivat
     */
    
    public void alustaPelaajanLaivat() {
        arpoja.alustaLaivat(pelaajanPeli);
    }

}
