/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.logiikka;

import com.antero.laivanupotus.domain.Laiva;
import com.antero.laivanupotus.domain.Ruutu;
import com.antero.laivanupotus.domain.Suunta;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Antero Oikkonen
 */
public class TietokoneAITest {

    private AI ai;
    private Pelikentta pelikentta;
    private LaivojenPaikkojenArpoja arpoja;

    public TietokoneAITest() {
    }

    @Before
    public void setUp() {
        pelikentta = new Pelikentta(10, 10);
        ai = new TietokoneAI(pelikentta);
    }

    @Test
    public void testGetPelikentta() {
    }

    @Test
    public void testaaAmmu() {
        Laiva laiva1 = new Laiva(2);
        laiva1.asetaLaivanPaikka(0, 0, Suunta.PYSTY);

        Laiva laiva2 = new Laiva(4);
        laiva2.asetaLaivanPaikka(4, 5, Suunta.VAAKA);
        pelikentta.asetaLaiva(laiva1, false);
        pelikentta.asetaLaiva(laiva2, false);
        boolean osui = ai.ammu();
        Ruutu r = ai.viimeksiAmmuttu();
        if (laiva1.getLaivanRuudut().contains(r) || laiva2.getLaivanRuudut().contains(r)) {
            assertTrue(osui);
        } else {
            assertFalse(osui);
        }
    }

    @Test
    public void testaaAmmuKunEnsimmainenOsuu() {
        Laiva l = new Laiva(2);
        l.asetaLaivanPaikka(3, 3, Suunta.VAAKA);
        pelikentta.asetaLaiva(l, false);
        assertTrue(((TietokoneAI) ai).ammu(3, 3));
        boolean osui = ai.ammu();
        Ruutu r = ai.viimeksiAmmuttu();
        int ammuttuX = r.getX();
        int ammuttuY = r.getY();

        if (ammuttuX == 3) {
            assertEquals(4, ammuttuY);
        }
        if (ammuttuX == 4) {
            assertEquals(3, ammuttuY);
        }

        if (ammuttuY == 3) {
            assertTrue(pelikentta.upposiko(3, 3));
            assertTrue(osui);
        }
        if(ammuttuY == 4){
            assertFalse(pelikentta.upposiko(3, 3));
            assertFalse(osui);
        }

    }

}
