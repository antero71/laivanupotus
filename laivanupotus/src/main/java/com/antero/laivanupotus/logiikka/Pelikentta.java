/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.logiikka;

import com.antero.laivanupotus.domain.Laiva;
import com.antero.laivanupotus.domain.Ruutu;
import com.antero.laivanupotus.domain.Suunta;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/**
 * Luokka joka huolehtii Pelikentän käsittelystä
 *
 * Pelikenttä luodaan käyttäjän antaman kokoiseksi, oletuksena on 10 x 10
 * ruudukko.
 *
 * @author Antero Oikkonen
 */
public class Pelikentta {

    private Ruutu[][] ruudut = new Ruutu[10][10];
    private HashMap<Ruutu, Laiva> laivat = new HashMap<Ruutu, Laiva>();
    private int laivojenLkm = 0;
    private int kenttaX;
    private int kenttaY;

    private Collection<Laiva> laivatCol = new ArrayList();

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

    public boolean isLaiva(int x, int y) {
        if (ruudut[x][y] != null && x > -1 && x < kenttaX && y > -1 && y < kenttaY) {
            return ruudut[x][y].isLaivanOsa();
        } else {
            return false;
        }
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
    public boolean asetaLaiva(Laiva laiva, boolean printDebug) {
        TreeSet<Ruutu> laivanRuudut = laiva.getLaivanRuudut();
        // System.out.println(laivanRuudut);

        if (tarkistaMeneekoRuudukonUlkopuolelle(laiva)) {
            //System.out.println("tarkistaMeneekoRuudukonUlkopuolelle palauttaa true");
            return false;
        }
        if (tarkistaOnkoKiellettyAsettaaLaivaa(laiva, printDebug)) {
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
            alustaRuutu(r, x, y, true, false);
        }
        //asetaLaivanKieltoalue(laiva, printDebug);
        laivojenLkm++;
        laivatCol.add(laiva);
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
        Ruutu r = ruudut[y][x];
        boolean osui = false;
        if (r != null) {
            if (r.isLaivanOsa()) {
                r.osui(true);
                osui = true;
            }
        } else {
            r = new Ruutu();
            alustaRuutu(r, y, x, false, true);
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
     * onko ruutuun ammuttu vai ei
     * @param ruutu
     * @return 
     */
    
    public boolean onkoAmmuttu(Ruutu ruutu) {
        return onkoAmmuttu(ruutu.getY(), ruutu.getX());
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
     * @return boolean, true jos koordinaateissa oleva laiva upposi, muuten
     * false
     */
    public boolean upposiko(int x, int y) {
        Ruutu r = ruudut[x][y];
        if (r != null) {
            if (laivat.get(r) != null) {
                return laivat.get(r).upposiko();
            }
            return false;
        }
        return false;
    }

    /**
     * upposiko Ruudun osoittamassa paikassa oleva laiva?
     *
     * @param r
     * @return true jos laiva upposi, false muussa tapauksessa
     */
    public boolean upposiko(Ruutu r) {
        return upposiko(r.getX(), r.getY());
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
        //if (1 == 1) {
        //    return;
        //}
        Ruutu r = null;
        int xraja = 10;
        int yraja = 10;
        for (int x = 0; x < xraja; x++) {
            for (int y = 0; y < yraja; y++) {
                r = new Ruutu();
                r.setX(x);
                r.setY(y);
                r.setLaivanOsa(false);
                //System.out.println("x = " + x + ", y = " + y);
                ruudut[x][y] = r;
            }

        }
        // for(int i = 0; i < ruudut.)
    }

    private boolean tarkistaMeneekoRuudukonUlkopuolelle(Laiva laiva) {

        TreeSet<Ruutu> laivanRuudut = laiva.getLaivanRuudut();
        int alkux = laivanRuudut.first().getX();
        int alkuy = laivanRuudut.first().getY();

        int loppux = laivanRuudut.last().getX();
        int loppuy = laivanRuudut.last().getY();
        int ypituus = kenttaY;
        int xpituus = kenttaX;

        if (alkux < 0 || alkuy < 0) {
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
     * laivan asetuksessa laitateen kielletty alue, joten tässä metodissa
     * tarkastetaan vain tulevan laivan ruutujen alue.
     *
     * @param laiva
     * @return
     */
    public boolean tarkistaOnkoKiellettyAsettaaLaivaa(Laiva laiva, boolean printDebug) {
        // laiva voidaan asettaa jos toista laivaa ei ole lähempänä kuin 
        // 1 ruudun päässä

        int[] koordinaatit = laskeAlkuXYJaLoppuXYymparoivalleAlueelle(laiva, printDebug);

        if (koordinaatit[1] < kenttaX - 1) {
            koordinaatit[1]++;
        }

        if (koordinaatit[3] < kenttaY - 1) {
            koordinaatit[3]++;
        }

        for (int i = koordinaatit[0]; i < koordinaatit[1]; i++) {
            for (int j = koordinaatit[2]; j < koordinaatit[3]; j++) {

                if (ruudut[i][j] != null && ruudut[i][j].isLaivanOsa()) {
                    if (printDebug) {
                        System.out.println(i + "," + j + "" + ruudut[i][j].isLaivanOsa());
                    }
                    return true;
                }
                if (ruudut[i][j] != null && printDebug) {
                    System.out.println(i + "," + j + "" + ruudut[i][j].isLaivanOsa());
                }

            }
        }

        return false;
    }

    /**
     * palauttaa int[] jossa alkiossa 0 on alkux, 1:ssa loppux 2:ssa alkuy ja
     * 3:ssa loppuy
     */
    private int[] laskeAlkuXYJaLoppuXYymparoivalleAlueelle(Laiva laiva, boolean print) {

        int[] koor = annaLaivanKoordinaatit(laiva);

        if (print) {
            System.out.println("laiva alkux  " + koor[0]);
            System.out.println("laiva loppux " + koor[1]);
            System.out.println("laiva alkuy  " + koor[2]);
            System.out.println("laiva loppuy " + koor[3]);
        }
        if (koor[0] > 0) {
            koor[0]--;
        }
        if (koor[2] > 0) {
            koor[2]--;
        }

        if ((kenttaX - 1) > koor[1]) {
            koor[1]++;
        }
        if ((kenttaY - 1) > koor[3]) {
            koor[3]++;
        }

        if (print) {

            System.out.println("ymp alkux  " + koor[0]);
            System.out.println("ymp loppux " + koor[1]);
            System.out.println("ymp alkuy  " + koor[2]);
            System.out.println("ymp loppuy " + koor[3]);

        }
        return koor;

    }

    private void alustaRuutu(Ruutu r, int x, int y, boolean laivanOsa, boolean ammuttu) {
        if (r == null) {
            r = new Ruutu();
        }
        r.setX(x);
        r.setY(y);
        r.setAmmuttu(ammuttu);
        r.setLaivanOsa(laivanOsa);
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
                if (ruudut[x][y] == null || (!ruudut[x][y].isLaivanOsa())) {
                    b.append(" ");
                } else if (ruudut[x][y].isLaivanOsa()) {
                    b.append("X");
                }

            }
            b.append("\n");
        }
        return b;
    }

    /**
     * tutkii onko kaikki laivat uponneet
     *
     * @return true jos kaikki laivat ovat uponneet, muuten false
     */
    public boolean isPeliPaattynyt() {
        boolean paattynyt = true;
        for (Laiva l : laivatCol) {
            if (!l.upposiko()) {
                paattynyt = false;
                break;
            }
        }
        return paattynyt;
    }

    public static void main(String[] args) {
        Pelikentta kentta = new Pelikentta(10, 10);
        Laiva l = new Laiva(4);

        int x = 0;
        int y = 4;

        l.asetaLaivanPaikka(x, y, Suunta.PYSTY);

        kentta.asetaLaiva(l, false);

        Laiva l2 = new Laiva(1);
        l2.asetaLaivanPaikka(x, y, Suunta.PYSTY);

        System.out.println(kentta.asetaLaiva(l2, false));

        System.out.println(kentta.isLaiva(x, y));

    }

    private int[] annaLaivanKoordinaatit(Laiva laiva) {
        TreeSet<Ruutu> laivanRuudut = laiva.getLaivanRuudut();
        Ruutu alku = laivanRuudut.first();
        int alkux = alku.getX();
        int alkuy = alku.getY();
        Ruutu loppu = laivanRuudut.last();
        int loppux = loppu.getX();
        int loppuy = loppu.getY();

        int[] koordinaatit = new int[4];

        koordinaatit[0] = alkux;
        koordinaatit[1] = loppux;
        koordinaatit[2] = alkuy;
        koordinaatit[3] = loppuy;

        return koordinaatit;
    }
}
