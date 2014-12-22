/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.logiikka;

import com.antero.laivanupotus.domain.Laiva;
import com.antero.laivanupotus.domain.Ruutu;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/**
 *
 * @author Antero Oikkonen
 */
public class Pelikentta {

    private Ruutu[][] ruudut = new Ruutu[10][10];
    private HashMap<Ruutu, Laiva> laivat = new HashMap<Ruutu, Laiva>();
    private int laivojenLkm = 0;
    private int kenttaX;
    private int kenttaY;

    /**
     * mahdollistetaan pelikentän koon muutos (oletuksena 10 x 10 ruutua
     *
     * @param ruudut
     */
    public Pelikentta(int x, int y) {
        kenttaX = x;
        kenttaY = y;
        ruudut = new Ruutu[kenttaX][kenttaY];
    }

    public int getKenttaX() {
        return kenttaX;
    }

    public int getKenttaY() {
        return kenttaY;
    }

    public int getLaivojenLkm() {
        return laivojenLkm;
    }

    public boolean asetaLaiva(Laiva laiva) {
        TreeSet<Ruutu> laivanRuudut = laiva.annaLaivanRuudut();
        System.out.println(laivanRuudut);

        if (tarkistaMeneekoRuudukonUlkopuolelle(laiva)) {
            System.out.println("tarkistaMeneekoRuudukonUlkopuolelle palauttaa true");
            return false;
        }
        if (tarkistaOnkoKiellettyAsettaaLaivaa(laiva)) {
            System.out.println("tarkistaOnkoKiellettyAsettaaLaivaa palauttaa true");
            return false;
        }

        Iterator<Ruutu> iter = laivanRuudut.iterator();

        while (iter.hasNext()) {
            Ruutu r = iter.next();
            laivat.put(r, laiva);
            int x = r.getX();
            int y = r.getY();
            alustaRuutu(r, x, y, true, true);
        }
        laivojenLkm++;
        return true;
    }

    public boolean ammu(int x, int y) {
        Ruutu r = ruudut[x][y];
        boolean osui = false;
        if (r != null) {
            if (r.isLaivanOsa()) {
                r.osui(true);
                osui = true;
            }
        } else {
            r = new Ruutu();
            alustaRuutu(r, x, y, false, false);
        }
        r.setAmmuttu(true);
        return osui;
    }

    public boolean upposiko(int x, int y) {
        Ruutu r = ruudut[x][y];
        if (r != null) {
            return laivat.get(r).upposiko();
        }
        return false;
    }

    public Ruutu[][] getRuudut() {
        return ruudut;
    }

    /**
     * tämä jostain syystä ei toimi, poistettu käytöstä toistaiseksi
     */
    public void alustaRuudukko() {
        if (1 == 1) {
            return;
        }
        Ruutu r = null;
        int xraja = 10;
        int yraja = 10;
        for (int x = 0; x < xraja; x++) {
            for (int y = 0; y < yraja; y++) {
                r = new Ruutu();
                r.setX(x);
                r.setY(y);
                r.setKielletty(false);
                r.setLaivanOsa(false);
                //System.out.println("x = " + x + ", y = " + y);
                ruudut[x][y] = r;
            }

        }
        // for(int i = 0; i < ruudut.)
    }

    public boolean tarkistaMeneekoRuudukonUlkopuolelle(Laiva laiva) {

        TreeSet<Ruutu> laivanRuudut = laiva.annaLaivanRuudut();
        int alkux = laivanRuudut.first().getX();
        int alkuy = laivanRuudut.first().getY();

        int loppux = laivanRuudut.last().getX();
        int loppuy = laivanRuudut.last().getY();
        int ypituus = kenttaY;
        int xpituus = kenttaX;

        if (loppuy == 9) {
            int i = 0;
        }

        if (alkux < 1 || alkuy < 1) {
            return true;
        }
        if (alkux > xpituus || alkuy > ypituus) {
            return true;
        }

        if (loppux > xpituus || loppuy > ypituus) {
            return true;
        }

        return false;
    }

    public boolean tarkistaOnkoKiellettyAsettaaLaivaa(Laiva laiva) {
        // laiva voidaan asettaa jos toista laivaa ei ole lähempänä kuin 
        // 1 ruudun päässä
        TreeSet<Ruutu> laivanRuudut = laiva.annaLaivanRuudut();
        Ruutu alku = laivanRuudut.first();
        int alkux = alku.getX();
        int alkuy = alku.getY();
        Ruutu loppu = laivanRuudut.last();
        int loppux = loppu.getX();
        int loppuy = loppu.getY();

        if (alkux > 1) {
            alkux--;
        }
        if (alkuy > 1) {
            alkuy--;
        }

        if (loppux < kenttaX) {
            loppux++;
        }
        if (loppuy < kenttaY) {
            loppuy++;
        }

        for (int i = alkux; i < loppux; i++) {
            for (int j = alkuy; j < loppuy; j++) {
                //System.out.println("x = "+1+" y = "+j);

                if (ruudut[i][j] != null && ruudut[i][j].isKielletty()) {
                    return true;
                }
            }
        }

        return false;

    }

    private void alustaRuutu(Ruutu r, int x, int y, boolean laivanOsa, boolean kielletty) {
        if (r == null) {
            r = new Ruutu();
        }
        r.setX(x);
        r.setY(y);
        r.setAmmuttu(true);
        r.setLaivanOsa(laivanOsa);
        r.setKielletty(kielletty);
        ruudut[x][y] = r;
    }

}
