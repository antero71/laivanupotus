/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Antero Oikkonen
 */
public class PelaajaTest {
    
    private Pelaaja pelaaja;
    
    
    public PelaajaTest() {
    }
    
    @Before
    public void setUp() {
        pelaaja=new Pelaaja();
    }


    @Test
    public void testaaLisaaPisteita() {
        pelaaja.lisaaPisteita(100);
        assertEquals(100, pelaaja.getPisteet());
    }
    
    @Test
    public void testaaLisaaPisteitaKaksiKertaa(){
        pelaaja.lisaaPisteita(100);
        pelaaja.lisaaPisteita(1000);
        assertEquals(1100, pelaaja.getPisteet());
    }

}
