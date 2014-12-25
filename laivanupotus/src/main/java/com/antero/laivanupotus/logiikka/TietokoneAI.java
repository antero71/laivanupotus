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
public class TietokoneAI implements AI{

    private Pelikentta pelikentta;
    private Ruutu viimeksiOsuttu;
    private Ruutu viimeksiAmmuttu;

    public TietokoneAI(Pelikentta pelikentta) {
        this.pelikentta = pelikentta;
    }

    public Pelikentta getPelikentta() {
        return pelikentta;
    }

    
    /**
     * apumetodi testausta helpottamaan
     * @param x
     * @param y
     * @return 
     */
    
    public boolean ammu(int x,int y){
        if(pelikentta.ammu(x, y)){
            viimeksiOsuttu = new Ruutu(x,y);
            viimeksiAmmuttu = viimeksiOsuttu;
            return true;
        }else{
            viimeksiAmmuttu = new Ruutu(x,y);
            viimeksiOsuttu = null;
            return false;
        }
       
    }
    
    @Override
    public boolean ammu() {
        Ruutu ammuttava = null;
        if (viimeksiOsuttu == null) {
            ammuttava = arvoAmmuttavaRuutu();
        } else {
            ammuttava = ammuViimeksiOsutunViereen();

        }
        if(ammuttava==null)
            return false;
        boolean osuiko = pelikentta.ammu(ammuttava);
        if (osuiko) {
            viimeksiOsuttu = ammuttava;
        } else {
            viimeksiOsuttu = null;
        }
        viimeksiAmmuttu = ammuttava;
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

    @Override
    public Ruutu viimeksiAmmuttu() {
        return viimeksiAmmuttu;
    }
}
