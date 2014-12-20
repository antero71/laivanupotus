/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.logiikka;

import com.antero.laivanupotus.domain.Ruutu;
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
public class PelikenttaTest {

    private Pelikentta pelikentta;

    public PelikenttaTest() {
    }

    @Before
    public void setUp() {
        pelikentta = new Pelikentta(10, 10);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testaaRuudukonAlustus() {
        pelikentta.alustaRuudukko();
        TreeSet<Ruutu> ruudut = pelikentta.getRuudut();

        assertEquals("Alkuruudun x-koordinaatti ei ole 1", 1, ruudut.first().getX());
        assertEquals("Alkuruudun y-koordinaatti ei ole 1", 1, ruudut.first().getY());

        assertEquals("Loppuruudun x-koordinaatti ei ole 10", 10, ruudut.last().getX());
        assertEquals("Loppuruudun y-koordinaatti ei ole 10", 10, ruudut.last().getY());

    }


}
