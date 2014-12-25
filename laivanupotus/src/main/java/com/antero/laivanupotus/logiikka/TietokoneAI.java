/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.logiikka;

import com.antero.laivanupotus.domain.Ruutu;

/**
 *
 * @author Antero Oikkonen
 */
public class TietokoneAI {

    private Pelikentta pelikentta;
    private Ruutu viimeksiOsuttu;

    public TietokoneAI(Pelikentta pelikentta) {
        this.pelikentta = pelikentta;
    }

    public boolean ammu() {
        Ruutu ammuttava = null;
        if (viimeksiOsuttu == null) {
            ammuttava = arvoAmmuttavaRuutu();
        } else {
            ammuttava = ammuViimeksiOsutunViereen();

        }
        boolean osuiko = pelikentta.ammu(ammuttava);
        if (osuiko) {
            viimeksiOsuttu = ammuttava;
        } else {
            viimeksiOsuttu = null;
        }
        return osuiko;
    }

    private Ruutu ammuViimeksiOsutunViereen() {
        int x = viimeksiOsuttu.getX();
        int y = viimeksiOsuttu.getY();
        boolean suunta = Arpoja.arvoBoolean();
        if (suunta && x < (pelikentta.getKenttaX() - 1)) {
            x++;
        } else if (suunta) {
            x--;
        }

        if (!suunta && y < (pelikentta.getKenttaY() - 1)) {
            y++;
        } else if (!suunta) {
            y--;
        }
        return new Ruutu(x,y);
    }

    private Ruutu arvoAmmuttavaRuutu() {
        int x;
        int y;
        int maksimiLoop = pelikentta.getKenttaX() * pelikentta.getKenttaY();
        int i = 0;
        do {
            i++;
            if (i > maksimiLoop) {
                return null;
            }
            x = Arpoja.arvoLuku(pelikentta.getKenttaX());
            y = Arpoja.arvoLuku(pelikentta.getKenttaY());
        } while (pelikentta.onkoAmmuttu(x, y));

        return new Ruutu(x, y);

    }
}
