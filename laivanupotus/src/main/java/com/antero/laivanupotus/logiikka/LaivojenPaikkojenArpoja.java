/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.logiikka;

import com.antero.laivanupotus.domain.Laiva;
import com.antero.laivanupotus.domain.Suunta;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author Antero Oikkonen
 */
public class LaivojenPaikkojenArpoja {

    private Random random = new Random();

    public void alustaLaivat(Pelikentta peli) {
        peli.alustaRuudukko();
        Collection<Laiva> laivat = luoLaivat();
        Iterator<Laiva> iter = laivat.iterator();
        while (iter.hasNext()) {
            Laiva l = iter.next();
            do {
                arvoLaivanPaikka(peli, l);

            } while (peli.asetaLaiva(l) != true);
        }
    }

    private void arvoLaivanPaikka(Pelikentta peli, Laiva l) {
        int x = Arpoja.arvoLuku(peli.getKenttaX());
        int y = Arpoja.arvoLuku(peli.getKenttaY());
        //System.out.println("arvottu x="+x+":y="+y);
        int s = Arpoja.arvoLuku(2);
        if (s == 0) {
            l.asetaLaivanPaikka(x, y, Suunta.VAAKA);
        }
        if (s == 1) {
            l.asetaLaivanPaikka(x, y, Suunta.PYSTY);
        }
    }

    private Collection<Laiva> luoLaiva(int maara, int pituus) {
        Collection<Laiva> laivat = new ArrayList();
        for (int i = 0; i < maara; i++) {
            laivat.add(new Laiva(pituus));
        }
        return laivat;
    }

    private Collection<Laiva> luoLaivat() {

        Collection<Laiva> laivat = new ArrayList<>();

        laivat.addAll(luoLaiva(1, Laiva.LENTOTUKIALUS_PITUUS));
        laivat.addAll(luoLaiva(2, Laiva.RISTEILIJA_PITUUS));
        laivat.addAll(luoLaiva(3, Laiva.HAVITTAJA_PITUUS));
        laivat.addAll(luoLaiva(4, Laiva.SUKELLUSVENE_PITUUS));

        return laivat;

    }
}
