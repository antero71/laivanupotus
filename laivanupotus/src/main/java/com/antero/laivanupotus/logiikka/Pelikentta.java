/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.logiikka;

import com.antero.laivanupotus.domain.Laiva;
import com.antero.laivanupotus.domain.Ruutu;
import java.util.Iterator;
import java.util.TreeSet;

/**
 *
 * @author Antero Oikkonen
 */
public class Pelikentta {

    private Ruutu[][] ruudut = new Ruutu[10][10];
    private TreeSet<Laiva> laivat = new TreeSet();
    private int kenttaX = 10;
    private int kenttaY = 10;

    /**
     * mahdollistetaan pelikentän koon muutos (oletuksena 10 x 10 ruutua
     *
     * @param ruudut
     */
    public Pelikentta(int x, int y) {
        kenttaX = x+1;
        kenttaY = y+1;
        ruudut = new Ruutu[x+1][y+1];
    }
    
    public boolean asetaLaiva(Laiva laiva){
        TreeSet<Ruutu> laivanRuudut = laiva.annaLaivanRuudut();
        if(tarkistaMeneekoRuudukonUlkopuolelle(laiva)){
            return false;
        }
        Iterator <Ruutu>iter = laivanRuudut.iterator();
     
        while(iter.hasNext()){
            Ruutu r = iter.next();
            int x = r.getX();
            int y = r.getY();
            ruudut[x][y]=r;  
        }
        return true;
    }

    public Ruutu[][] getRuudut() {
        return ruudut;
    }

    
    
    public void alustaRuudukko() {
        Ruutu r = null;
        for (int x = 1; x < kenttaX; x++) {
            for (int y = 1; y < kenttaY; y++) {
                r = new Ruutu();
                r.setX(x);
                r.setY(y);
                r.setKielletty(false);
                r.setLaivanOsa(false);
                System.out.println("x = "+x +" y = "+y);
                ruudut[x][y]=r;
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
         
         int ypituus = ruudut.length;
         int xpituus = ruudut[kenttaX-1].length;
         
         if(alkux > xpituus || alkuy > ypituus || loppux > xpituus || loppuy > ypituus){
             return true;
         }
         return false;
    }
    
    
}
