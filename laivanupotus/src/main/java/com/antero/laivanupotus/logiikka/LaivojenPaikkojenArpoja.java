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

    final static private int SUKELLUSVENE_PITUUS = 1;
    final static private int HAVITTAJA_PITUUS = 2;
    final static private int RISTEILIJA_PITUUS = 3;
    final static private int LENTOTUKIALUS_PITUUS = 4;

    private Random random = new Random();

    public void alustaLaivat(Pelikentta peli) {
        peli.alustaRuudukko();
        Collection<Laiva> laivat = luoLaivat();
        Iterator<Laiva> iter = laivat.iterator();
        while (iter.hasNext()) {
            Laiva l = iter.next();
            arvoLaivanPaikka(peli, l);
            //System.out.println("laiva "+l.toString());
            while (peli.asetaLaiva(l) != true) {
                arvoLaivanPaikka(peli, l);
            }
        }
    }

    private void arvoLaivanPaikka(Pelikentta peli, Laiva l) {
        int x = arvoLuku(peli.getKenttaX());
        int y = arvoLuku(peli.getKenttaY());
        int s = arvoLuku(2);
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

        laivat.addAll(luoLaiva(1, LENTOTUKIALUS_PITUUS));
        laivat.addAll(luoLaiva(2, RISTEILIJA_PITUUS));
        laivat.addAll(luoLaiva(3, HAVITTAJA_PITUUS));
        laivat.addAll(luoLaiva(4, SUKELLUSVENE_PITUUS));

        return laivat;

    }

    /**
     * arvotaan luku 1 - maksimi
     *
     * @param maksimi
     * @return
     */
    private int arvoLuku(int maksimi) {
        return random.nextInt(maksimi);
    }

}
