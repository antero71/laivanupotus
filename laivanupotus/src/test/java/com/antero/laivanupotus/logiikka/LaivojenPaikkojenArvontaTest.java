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
    
    private LaivojenPaikkojenArpoja laivojenPaikat;
    private Pelikentta peli;
    
    public LaivojenPaikkojenArvontaTest() {
    }
    
    @Before
    public void setUp() {
        laivojenPaikat = new LaivojenPaikkojenArpoja();
        peli = new Pelikentta(10, 10);
        laivojenPaikat.alustaLaivat(peli);
    }

    @Test
    public void onkoOikeaMaaraLaivoja() {
        assertEquals(10, peli.getLaivojenLkm());
    }
    
    @Test
    public void onkoArvottuLaivojaLiianLahelleToisiaan(){
        
    }
    
}
