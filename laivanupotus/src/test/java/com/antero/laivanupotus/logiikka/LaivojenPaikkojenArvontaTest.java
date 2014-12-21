/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Antero Oikkonen
 */
public class LaivojenPaikkojenArvontaTest {
    
    private LaivojenPaikkojenArvonta laivojenPaikat;
    private Pelikentta peli;
    
    public LaivojenPaikkojenArvontaTest() {
    }
    
    @Before
    public void setUp() {
        laivojenPaikat = new LaivojenPaikkojenArvonta();
        peli = new Pelikentta(9, 9);
        laivojenPaikat.alustaLaivat(peli);
    }

    @Test
    public void onkoOikeaMaaraLaivoja() {
        assertEquals(10, peli.getLaivojenLkm());
    }
    
}
