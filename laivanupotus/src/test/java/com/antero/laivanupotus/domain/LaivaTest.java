/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.domain;

import java.util.TreeSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Antero Oikkonen
 */
public class LaivaTest {

    private Laiva laiva;

    public LaivaTest() {
    }

    @Before
    public void setUp() {
        laiva = new Laiva(1);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testaaLaivanPituusAlustuksenJalkeen() {
        assertEquals(1, laiva.laivanPituus());
    }

    @Test
    public void testaaLaivanPituusKunLaiva2ruutuaPitka() {
        laiva = new Laiva(2);
        assertEquals(2, laiva.laivanPituus());
    }

    @Test
    public void testaaLaivanPaikanAntoOnkoAlkupaaOikeassaPaikassa() {
        laiva.asetaLaivanPaikka(2, 3, Suunta.PYSTY);
        TreeSet<Ruutu> ruudut = laiva.annaLaivanRuudut();
        Ruutu alku = ruudut.first();
        assertEquals("X-arvo väärin", 2, alku.getX());
        assertEquals("Y-arvo väärin", 3, alku.getY());

    }

    @Test
    public void testaaAlkupaanPaikkaKunLaivanPituus2() {
        laiva = new Laiva(2);
        laiva.asetaLaivanPaikka(2, 3, Suunta.VAAKA);
        assertEquals("X-arvo väärin", 2, laiva.annaLaivanRuudut().first().getX());
        assertEquals("Y-arvo väärin", 3, laiva.annaLaivanRuudut().first().getY());
    }

    @Test
    public void testaaLaivanLoppupaanPaikkaKunLaivanPituus2() {
        laiva = new Laiva(2);
        laiva.asetaLaivanPaikka(2, 3, Suunta.VAAKA);
        assertEquals("X-arvo väärin", 3, laiva.annaLaivanRuudut().last().getX());
        assertEquals("Y-arvo väärin", 3, laiva.annaLaivanRuudut().last().getY());
    }

    @Test
    public void testaaLaivanLoppupaanPaikkaKunLaivanPituus2Pysty() {
        laiva = new Laiva(2);
        laiva.asetaLaivanPaikka(2, 3, Suunta.PYSTY);
        assertEquals("X-arvo väärin", 2, laiva.annaLaivanRuudut().last().getX());
        assertEquals("Y-arvo väärin", 4, laiva.annaLaivanRuudut().last().getY());
    }

    @Test
    public void testaaLaivanLoppupaanPaikkaKunLaivanPituus4JaPystyssa() {
        laiva = new Laiva(4);
        laiva.asetaLaivanPaikka(2, 3, Suunta.PYSTY);
        assertEquals("X-arvo väärin", 2, laiva.annaLaivanRuudut().last().getX());
        assertEquals("Y-arvo väärin", 6, laiva.annaLaivanRuudut().last().getY());
    }

    @Test
    public void kaannaLaivaEiMuutaLaivanKokoaEikaPaikkaaJosPituus1() {
        laiva.asetaLaivanPaikka(4, 5, Suunta.PYSTY); // suunnalla ei tässä tapauksessa väliä
        laiva.kaannaLaiva();

        // alkupää
        assertEquals("X-arvo väärin", 4, laiva.annaLaivanRuudut().first().getX());
        assertEquals("Y-arvo väärin", 5, laiva.annaLaivanRuudut().first().getY());

        // loppupää
        assertEquals("X-arvo väärin", 4, laiva.annaLaivanRuudut().last().getX());
        assertEquals("Y-arvo väärin", 5, laiva.annaLaivanRuudut().last().getY());

    }

    @Test
    public void kaannaLaivaKaantaaJosPituus2() {
        laiva = new Laiva(2);
        laiva.asetaLaivanPaikka(4, 5, Suunta.PYSTY);
        assertEquals("loppupään X-arvo väärin", 4, laiva.annaLaivanRuudut().last().getX());
        assertEquals("loppupään Y-arvo väärin", 6, laiva.annaLaivanRuudut().last().getY());

        laiva.kaannaLaiva();

        // alkupää
        assertEquals("alkupään X-arvo väärin", 4, laiva.annaLaivanRuudut().first().getX());
        assertEquals("alkupään Y-arvo väärin", 5, laiva.annaLaivanRuudut().first().getY());

        // loppupää
        assertEquals("loppupään X-arvo väärin", 5, laiva.annaLaivanRuudut().last().getX());
        assertEquals("loppupään Y-arvo väärin", 5, laiva.annaLaivanRuudut().last().getY());

    }

    @Test(expected = IllegalArgumentException.class)
    public void konstruktoriHeittaaPoikkeuksenJosPituusLiianPieni() {
        laiva = new Laiva(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void konstruktoriHeittaaPoikkeuksenJosPituusLiianSuuri() {
        laiva = new Laiva(10);
    }

}
