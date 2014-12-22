/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.domain;

/**
 *
 * @author Antero Oikkonen
 */
public class Pelaaja implements Comparable<Pelaaja> {

    private int pisteet;
    private String nimi;

    public Pelaaja(String nimi,int pisteet) {
        this.pisteet = pisteet;
        this.nimi = nimi;
    }

    
    public Pelaaja(){
        
    }
    
    
    public int getPisteet() {
        return pisteet;
    }

    public void setPisteet(int pisteet) {
        this.pisteet = pisteet;
    }

    public void lisaaPisteita(int lisattava) {
        pisteet += lisattava;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    
    /**
     * suurin pistemäärä ensin järjestettyihin Collectioneihin
     * @param o
     * @return 
     */
    
    @Override
    public int compareTo(Pelaaja o) {
        int value = 0;
        if (pisteet == o.getPisteet()) {
            value = 0;
        }

        if (pisteet > o.getPisteet()) {
            value = -1;
        }

        if (pisteet < o.getPisteet()) {
            value = 1;
        }
        return value;
    }

}
