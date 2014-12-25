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
        int x = 0;
        int y = 0;
        if (viimeksiOsuttu == null) {
            x = Arpoja.arvoLuku(pelikentta.getKenttaX());
            y = Arpoja.arvoLuku(pelikentta.getKenttaY());

        } else {
            x = viimeksiOsuttu.getX();
            y = viimeksiOsuttu.getY();
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

        }
        boolean osuiko = pelikentta.ammu(x, y);
        if (osuiko) {
            viimeksiOsuttu = new Ruutu(x, y);
        } else {
            viimeksiOsuttu = null;
        }
        return osuiko;
    }
}
