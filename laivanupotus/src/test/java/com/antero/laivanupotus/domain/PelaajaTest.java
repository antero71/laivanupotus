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
    
    @Test
    public void testaaVertailuJosVertailtavallaEnemmanPisteita(){
        Pelaaja p = new Pelaaja("Kalle",100);
        
        Pelaaja p2 = new Pelaaja("Pekka",1000);
        
        assertEquals(1,p.compareTo(p2));
    }
    
     
    @Test
    public void testaaVertailuJosVertailtavallaVahemmanPisteita(){
        Pelaaja p = new Pelaaja("Kalle",1000);
        
        Pelaaja p2 = new Pelaaja("Pekka",100);
        
        assertEquals(-1,p.compareTo(p2));
    }
    
      @Test
    public void testaaVertailuJosVertailtavallaSamaMaaraPisteita(){
        Pelaaja p = new Pelaaja("Kalle",1000);
        
        Pelaaja p2 = new Pelaaja("Pekka",1000);
        
        assertEquals(0,p.compareTo(p2));
    }
    
    @Test
    public void testaaOletuskonstruktori(){
        Pelaaja p = new Pelaaja();
        assertEquals(null, p.getNimi());
        assertEquals(0, p.getPisteet());
    }

}
