/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.domain;

import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Antero Oikkonen
 */
public class PistetaulukkoTest {

    Pistetaulukko pisteet;

    public PistetaulukkoTest() {

    }

    @Before
    public void setUp() {
        pisteet = new Pistetaulukko();
    }

    @Test
    public void testaaLisaa11PelaajaaPistetaulukkoon() {
        Random r = new Random();

        Pelaaja p = null;
        for (int i = 0; i < 11; i++) {
            p = new Pelaaja();
            p.setNimi("pelaaja " + i);
            p.setPisteet(r.nextInt(1000));
            if (i < 10) {
                assertTrue(pisteet.lisaaPelaajaPistetaulukkoon(p));
            } else {
                int viimeisenPisteet = pisteet.annaPieninPistemaara();
                if (p.getPisteet() > viimeisenPisteet) {
                    assertTrue(pisteet.lisaaPelaajaPistetaulukkoon(p));
                } else {
                    assertFalse(pisteet.lisaaPelaajaPistetaulukkoon(p));
                }
            }
        }
    }

    @Test
    public void testaaToString() {
        Pelaaja p = new Pelaaja("Lokki",1200);
    

        StringBuffer b = new StringBuffer();

        lisaaVertailuStringiin(b, p);

        pisteet.lisaaPelaajaPistetaulukkoon(p);

        p = new Pelaaja("Antero",900);

        lisaaVertailuStringiin(b, p);

        pisteet.lisaaPelaajaPistetaulukkoon(p);
 
        assertEquals(b.toString(), pisteet.toString());

    }

    private void lisaaVertailuStringiin(StringBuffer b, Pelaaja p) {
        b.append(p.getNimi());
        b.append(": ");
        b.append(p.getPisteet());
        b.append("\n");
    }
    
    @Test
    public void testaaAnnaPieninPistemaara(){
        Pelaaja p = new Pelaaja("Antero",1000);
        pisteet.lisaaPelaajaPistetaulukkoon(p);
        
        p = new Pelaaja("Tarmo",900);
        pisteet.lisaaPelaajaPistetaulukkoon(p);
        
        assertEquals(900, pisteet.annaPieninPistemaara());
    }

}
