/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.logiikka;

import com.antero.laivanupotus.domain.Laiva;
import com.antero.laivanupotus.domain.Ruutu;
import com.antero.laivanupotus.domain.Suunta;
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
    private int pelikenttaX = 10;
    private int pelikenttaY = 10;
    

    public PelikenttaTest() {
    }

    @Before
    public void setUp() {
        pelikentta = new Pelikentta(pelikenttaX, pelikenttaY);
        pelikentta.alustaRuudukko();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testaaRuudukonAlustus() {
        Ruutu[][] ruudut = pelikentta.getRuudut();

        Ruutu alkuruutu = ruudut[1][1];
        Ruutu loppuruutu = ruudut[10][10];
        assertEquals("Alkuruudun x-koordinaatti ei ole 1", 1, alkuruutu.getX());
        assertEquals("Alkuruudun y-koordinaatti ei ole 1", 1, alkuruutu.getY());

        assertEquals("Loppuruudun x-koordinaatti ei ole 10", 10, loppuruutu.getX());
        assertEquals("Loppuruudun y-koordinaatti ei ole 10", 10, loppuruutu.getY());

    }

    @Test
    public void testaaLaivanAsetusJosMeneeYliRuudukon() {
        Laiva laiva = new Laiva(3);
        laiva.asetaLaivanPaikka(10, 10, Suunta.PYSTY);
        assertFalse("Laivaa ei voi asettaa yli pelialueen", pelikentta.asetaLaiva(laiva));
    }

    @Test
    public void testaaLaivanAsetusJosMahtuuPelialueelle() {
        Laiva laiva = new Laiva(2);
        laiva.asetaLaivanPaikka(2, 3, Suunta.VAAKA);
        pelikentta.alustaRuudukko();
        assertTrue("Laivan asetus epäonnistui", pelikentta.asetaLaiva(laiva));
    }

    @Test
    public void tarkistaOnkoKiellettyAsettaaLaivaaJosLaivaJoSamassaPaikassa() {
        Laiva laiva = new Laiva(3);
        laiva.asetaLaivanPaikka(3, 4, Suunta.VAAKA);
        pelikentta.alustaRuudukko();
        pelikentta.asetaLaiva(laiva);

        laiva = new Laiva(3);
        laiva.asetaLaivanPaikka(3, 4, Suunta.VAAKA);
        assertFalse("Toista laivaa ei voi asettaa jo olemassaolevan laivan paikalle", pelikentta.asetaLaiva(laiva));

    }

    @Test
    public void tarkistaOnkoKiellettyAsettaaLaivaaToisenLaivanViereen() {
        Laiva laiva = new Laiva(3);
        laiva.asetaLaivanPaikka(3, 4, Suunta.VAAKA);
        pelikentta.alustaRuudukko();
        pelikentta.asetaLaiva(laiva);

        laiva = new Laiva(3);
        laiva.asetaLaivanPaikka(3, 5, Suunta.VAAKA);
        assertFalse("Toista laivaa ei voi asettaa jo olemassaolevan laivan viereen", pelikentta.asetaLaiva(laiva));

    }
    
      @Test
    public void tarkistaOnkoKiellettyAsettaaLaivaaToisenLaivanViereenYhdenRuudunPaahan() {
        Laiva laiva = new Laiva(3);
        laiva.asetaLaivanPaikka(3, 4, Suunta.VAAKA);
        pelikentta.alustaRuudukko();
        pelikentta.asetaLaiva(laiva);

        laiva = new Laiva(3);
        laiva.asetaLaivanPaikka(3, 6, Suunta.VAAKA);
        assertTrue("Toinen laiva voi olla yhden ruudun päässä toisesta laivasta", pelikentta.asetaLaiva(laiva));

    }
    
    
   @Test
   public void tarkistaOnkoKiellettyAsettaaLaivaaAlkamanVasemmastaYlanurkasta(){
       Laiva laiva = new Laiva(2);
       laiva.asetaLaivanPaikka(1, 1, Suunta.PYSTY);
       
   }
   
   @Test
   public void testaaAmmuJosOsuu(){
       Laiva laiva = new Laiva(2);
       laiva.asetaLaivanPaikka(3, 4, Suunta.VAAKA);
       pelikentta.asetaLaiva(laiva);
       assertTrue("laivaan ei osunut vaikka laiva oli paikalla",pelikentta.ammu(4, 4));
       
   }
   
   @Test
   public void testaaAmmuJosHuti(){
       Laiva laiva = new Laiva(2);
       laiva.asetaLaivanPaikka(3, 4, Suunta.VAAKA);
       pelikentta.asetaLaiva(laiva);
       assertFalse("laivaan osui vaikka laivaa ei ollut paikalla",pelikentta.ammu(6, 8));   
   }
   
   @Test
   public void voikoPelikentanReunaanAsettaaLaivaa(){
       Laiva laiva = new Laiva(3);
       laiva.asetaLaivanPaikka(1, 1, Suunta.PYSTY);
       assertTrue(pelikentta.asetaLaiva(laiva));
       
   }
   
   @Test
   public void voikoPelikentanOikeaanAlakulmaanAsettaaLaivaa(){
       Laiva laiva = new Laiva(1);
       laiva.asetaLaivanPaikka(pelikenttaX, pelikenttaY, Suunta.PYSTY);
       assertTrue(pelikentta.asetaLaiva(laiva));
       
   }
   

}