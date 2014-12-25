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
public class ArpojaTest {
    
   
    
    public ArpojaTest() {
    }
    
    @Before
    public void setUp() {
    }

    @Test
    public void testaaArvoLuku() {
        int luku = 10;
        int arvottuLuku = Arpoja.arvoLuku(10);
        System.out.println("arvottuluku "+arvottuLuku);
        
        assertTrue(luku > arvottuLuku);
        assertTrue(luku > -1);
    }
    
}
