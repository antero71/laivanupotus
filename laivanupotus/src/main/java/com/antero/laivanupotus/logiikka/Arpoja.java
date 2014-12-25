/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.logiikka;

import java.util.Random;

/**
 * Apuluokka Random toiminnallisuudelle
 * @author Antero Oikkonen
 */
public class Arpoja {

    private static Random rand = new Random();

    /**
     * arpoo luvun väliltä nolla - (luku - 1)
     *
     * @param luku
     * @return
     */
    public static int arvoLuku(int luku) {
        return rand.nextInt(luku);
    }
    
    public static boolean arvoBoolean(){
        return rand.nextBoolean();
    }
}
