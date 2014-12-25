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
 * Luokka joka huolehtii Pelikentän käsittelystä
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

    /**
     * pelikentän X-koordinaatin koko (vaakasuunta)
     *
     * @return int X-koordinaatinkoko
     */
    public int getKenttaX() {
        return kenttaX;
    }

    /**
     * pelikentän Y-koordinaatin koko (pystysuunta)
     *
     * @return Y-koordinaatin koko
     */
    public int getKenttaY() {
        return kenttaY;
    }

    /**
     * palauttaa monta laivaa on pelialueella
     *
     * @return laivojen lukumäärä pelialueella
     */
    public int getLaivojenLkm() {
        return laivojenLkm;
    }

    /**
     * asetataan laiva laivan sisältämän koordinaatiston perusteella
     * pelialueelle. Jos menee pelialueen yli, palautuu false ja laivaa ei
     * aseteta pelialueelle
     *
     * jos laiva mahtuu pelialueelle palautuu true ja laiva asetetaan
     * pelialueelle siihen mihin laivan koordinaatisto osoittaa.
     *
     * @param laiva
     * @return
     */
    public boolean asetaLaiva(Laiva laiva) {
        TreeSet<Ruutu> laivanRuudut = laiva.annaLaivanRuudut();
        // System.out.println(laivanRuudut);

        if (tarkistaMeneekoRuudukonUlkopuolelle(laiva)) {
            //System.out.println("tarkistaMeneekoRuudukonUlkopuolelle palauttaa true");
            return false;
        }
        if (tarkistaOnkoKiellettyAsettaaLaivaa(laiva)) {
            //System.out.println("tarkistaOnkoKiellettyAsettaaLaivaa palauttaa true");
            return false;
        }

        Iterator<Ruutu> iter = laivanRuudut.iterator();

        while (iter.hasNext()) {
            Ruutu r = iter.next();
            laivat.put(r, laiva);
            int x = r.getX();
            int y = r.getY();
            //System.out.println("asetalaiva x=" + x + ",y=" + y);
            alustaRuutu(r, x, y, true, true, false);
        }
        asetaLaivanKieltoalue(laiva);
        laivojenLkm++;
        return true;
    }

    /**
     * ammutaan Ruutu olion koordinaattien osoittamaan paikkaan
     *
     * @param r Ruutu
     * @return boolean, true osui, false ohi meni
     */
    public boolean ammu(Ruutu r) {
        return ammu(r.getX(), r.getY());
    }

    /**
     * ammutaan annettuihin koordinaatteihin, jos osuu, palautuu true muuten
     * false
     *
     * @param x x-koordinaatti 0...(kenttaX-1)
     * @param y y-koordinaatti 0...(kenttaY-1)
     * @return boolena true jos osuu, muuten false
     */
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
            alustaRuutu(r, x, y, false, false, false);
        }
        r.setAmmuttu(true);
        return osui;
    }

    /**
     * palauttaa true jos annettuihin koordinaatteihin on ammuttu
     *
     * @param x x-koordinaatti 0...(kenttaX-1)
     * @param y y-koordinaatti 0...(kenttaY-1)
     * @return boolean true jos ammuttu, muuten false
     */
    public boolean onkoAmmuttu(int x, int y) {
        if (ruudut[x][y] == null) {
            return false;
        }
        if (!ruudut[x][y].isAmmuttu()) {
            return false;
        }
        return true;
    }

    /**
     * Palauttaa upposiko laiva jonka jokin osa on koordinaateissa x,y
     *
     * Jos ko. koordinaateissa ei ole laivaa palautuu false
     *
     * Jos laivan osa on koordinaateissa, tarkistataan ko. laivan status
     *
     * @param x x-koordinaatti 0...(kenttaX-1)
     * @param y y-koordinaatti 0...(kenttaY-1)
     * @return boolean, true jos koordinaateissa oleva laiva upposi, muuten false
     */
    public boolean upposiko(int x, int y) {
        Ruutu r = ruudut[x][y];
        if (r != null) {
            return laivat.get(r).upposiko();
        }
        return false;
    }

    /**
     * palauttaa pelikentän Ruudut -taulukon
     *
     * @return pelikentän ruudut taulukko
     */
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

    private boolean tarkistaMeneekoRuudukonUlkopuolelle(Laiva laiva) {

        TreeSet<Ruutu> laivanRuudut = laiva.annaLaivanRuudut();
        int alkux = laivanRuudut.first().getX();
        int alkuy = laivanRuudut.first().getY();

        int loppux = laivanRuudut.last().getX();
        int loppuy = laivanRuudut.last().getY();
        int ypituus = kenttaY;
        int xpituus = kenttaX;

        if (loppux == 10 || loppuy == 10) {
            int i = 0;
        }

        if (alkux < 1 || alkuy < 0) {
            return true;
        }
        if (alkux > xpituus || alkuy > ypituus) {
            return true;
        }

        if (loppux > (xpituus - 1) || loppuy > (ypituus - 1)) {
            return true;
        }

        return false;
    }

    /**
     * laivan ympärillä olevat ruudut merkitään kielletyiksi
     *
     * @param laiva
     */
    public void asetaLaivanKieltoalue(Laiva laiva) {
        // laiva voidaan asettaa jos toista laivaa ei ole lähempänä kuin 
        // 1 ruudun päässä
        int[] koordinaatit = laskeAlkuXYJaLoppuXYymparoivalleAlueelle(laiva);

        // Lisätään loppukoordinaatteihin yksi jotta voidaan 
        // käyttäää < vertailua
        int vertailux = kenttaX - 1;
        int vertailuy = kenttaY - 1;

        if (koordinaatit[1] < vertailux) {
            koordinaatit[1]++;
        }

        if (koordinaatit[1] < vertailuy) {
            koordinaatit[3]++;
        }

        TreeSet<Ruutu> laivanRuudut = laiva.annaLaivanRuudut();
        Ruutu ruutu = null;
        for (int i = koordinaatit[0]; i < koordinaatit[1]; i++) {
            for (int j = koordinaatit[2]; j < koordinaatit[3]; j++) {
                //System.out.println("x = "+1+" y = "+j);
                ruutu = new Ruutu(i, j);
                if (!laivanRuudut.contains(ruutu)) {
                    alustaRuutu(ruutu, i, j, false, true, false);
                }

            }
        }

    }

    private boolean tarkistaOnkoKiellettyAsettaaLaivaa(Laiva laiva) {
        // laiva voidaan asettaa jos toista laivaa ei ole lähempänä kuin 
        // 1 ruudun päässä

        int[] koordinaatit = laskeAlkuXYJaLoppuXYymparoivalleAlueelle(laiva);

        for (int i = koordinaatit[0]; i < koordinaatit[1]; i++) {
            for (int j = koordinaatit[2]; j < koordinaatit[3]; j++) {

                if (ruudut[i][j] != null && ruudut[i][j].isKielletty() && ruudut[i][j].isLaivanOsa()) {
                    return true;
                }
            }
        }

        return false;

    }

    /**
     * palauttaa int[] jossa alkiossa 0 on alkux, 1:ssa loppux 2:ssa alkuy ja
     * 3:ssa loppuy
     */
    private int[] laskeAlkuXYJaLoppuXYymparoivalleAlueelle(Laiva laiva) {
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

        if ((kenttaX - 1) > loppux) {
            loppux++;
        }
        if ((kenttaY - 1) > loppuy) {
            loppuy++;
        }

        if (loppux == 10 || loppuy == 10) {
            System.out.println("alkux " + alkux);
            System.out.println("loppyx " + loppux);
            System.out.println("alkuy " + alkuy);
            System.out.println("loppuy " + loppuy);
            System.out.println("KenttaX " + kenttaX);
            System.out.println("KenttaY " + kenttaY);
        }

        int[] koordinaatit = new int[4];
        koordinaatit[0] = alkux;
        koordinaatit[1] = loppux;
        koordinaatit[2] = alkuy;
        koordinaatit[3] = loppuy;
        return koordinaatit;

    }

    private void alustaRuutu(Ruutu r, int x, int y, boolean laivanOsa, boolean kielletty, boolean ammuttu) {
        if (r == null) {
            r = new Ruutu();
        }
        r.setX(x);
        r.setY(y);
        r.setAmmuttu(ammuttu);
        r.setLaivanOsa(laivanOsa);
        r.setKielletty(kielletty);
        ruudut[x][y] = r;
    }

    @Override
    public String toString() {
        StringBuffer pelikentta = kokoPelikenttaOsumatString();
        pelikentta.append("\n\n");
        pelikentta.append(pelikenttaLaivatString());

        return pelikentta.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    private StringBuffer kokoPelikenttaOsumatString() {
        StringBuffer b = new StringBuffer();
        for (int x = 0; x < kenttaX; x++) {
            for (int y = 0; y < kenttaY; y++) {
                if (ruudut[x][y] == null || (ruudut[x][y].isLaivanOsa() && !ruudut[x][y].isOsui())) {
                    b.append(" ");
                }
                if (ruudut[x][y] != null && ruudut[x][y].isOsui()) {
                    b.append("X");
                }
                if (ruudut[x][y] != null && !ruudut[x][y].isOsui() && ruudut[x][y].isAmmuttu()) {
                    b.append("O");
                }
            }
            b.append("\n");
        }
        return b;
    }

    private StringBuffer pelikenttaLaivatString() {
        StringBuffer b = new StringBuffer();
        for (int x = 0; x < kenttaX; x++) {
            for (int y = 0; y < kenttaY; y++) {
                if (ruudut[x][y] == null || !ruudut[x][y].isLaivanOsa()) {
                    b.append(" ");
                } else if (ruudut[x][y].isLaivanOsa()) {
                    b.append("X");
                }

            }
            b.append("\n");
        }
        return b;
    }

    public StringBuffer pelikenttaLaivatJaKielletytRuudutString() {
        StringBuffer b = new StringBuffer();
        for (int x = 0; x < kenttaX; x++) {
            for (int y = 0; y < kenttaY; y++) {
                if (ruudut[x][y] == null || (!ruudut[x][y].isLaivanOsa() && !ruudut[x][y].isKielletty())) {
                    b.append(" ");
                } else if (ruudut[x][y].isLaivanOsa()) {
                    b.append("X");
                } else if (ruudut[x][y].isKielletty() && !ruudut[x][y].isLaivanOsa()) {
                    b.append("K");
                }

            }
            b.append("\n");
        }
        return b;
    }

}
