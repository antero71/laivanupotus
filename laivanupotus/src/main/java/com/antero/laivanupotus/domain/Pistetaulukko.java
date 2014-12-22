/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.domain;

import java.util.Iterator;
import java.util.TreeSet;

/**
 *
 * @author Antero Oikkonen
 */
/**
 *
 * @author Antero Oikkonen
 */
/**
 * pistetaulukko muistaa 10 parhaan pelaajan pisteet
 *
 * @author Antero Oikkonen
 */
public class Pistetaulukko {

    TreeSet<Pelaaja> pelaajat;

    public Pistetaulukko() {
        pelaajat = new TreeSet<Pelaaja>();
    }

    /**
     * pelaaja lisätään pistetaulukkoon vain siinä tapauksessa että hänen
     * pisteensä on paremmat kuin viimeisenä taulukossa olevan pelaajan
     *
     * @param p
     * @return palauttaa true jos pelaaja lisättiin taulukkoon, muuten false
     */
    public boolean lisaaPelaajaPistetaulukkoon(Pelaaja p) {

        boolean lisattiinkoPelaaja = false;

        if (pelaajat.size() == 10 && p.getPisteet() > pelaajat.last().getPisteet()) {
            pelaajat.remove(pelaajat.last());
            pelaajat.add(p);
            lisattiinkoPelaaja = true;
        } else if (pelaajat.size() < 10) {
            pelaajat.add(p);
            lisattiinkoPelaaja = true;
        }
        return lisattiinkoPelaaja;

    }

    public int annaPieninPistemaara(){
        return pelaajat.last().getPisteet();
    }
    
    @Override
    public String toString() {
        String pelaajaString = kokoaPelaajienTulokset();
        return pelaajaString;
    }

    private String kokoaPelaajienTulokset() {
        Iterator<Pelaaja> iter = pelaajat.iterator();
        StringBuffer b = new StringBuffer();
        while (iter.hasNext()) {
            Pelaaja p = iter.next();
            b.append(p.getNimi());
            b.append(": ");
            b.append(p.getPisteet());
            b.append("\n");
        }
        return b.toString();
    }
}
