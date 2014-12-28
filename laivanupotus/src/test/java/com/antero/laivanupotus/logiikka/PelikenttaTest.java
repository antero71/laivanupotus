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

    //@Test
    public void testaaRuudukonAlustus() {
        Ruutu[][] ruudut = pelikentta.getRuudut();

        Ruutu alkuruutu = ruudut[0][0];
        System.out.println("ruudut.length " + ruudut.length);
        Ruutu loppuruutu = ruudut[9][9];
        assertEquals("Alkuruudun x-koordinaatti ei ole 0", 0, alkuruutu.getX());
        assertEquals("Alkuruudun y-koordinaatti ei ole 0", 0, alkuruutu.getY());

        assertEquals("Loppuruudun x-koordinaatti ei ole 9", 9, loppuruutu.getX());
        assertEquals("Loppuruudun y-koordinaatti ei ole 9", 9, loppuruutu.getY());

    }

    @Test
    public void testaaLaivanAsetusJosMeneeYliRuudukon() {
        Laiva laiva = new Laiva(3);
        laiva.asetaLaivanPaikka(9, 9, Suunta.PYSTY);
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
    public void tarkistaOnkoKiellettyAsettaaLaivaaKulmittainToisenLaivanViereenYhdenRuudunPaahan() {
        Laiva laiva = new Laiva(3);
        laiva.asetaLaivanPaikka(2, 3, Suunta.VAAKA);
        pelikentta.alustaRuudukko();
        pelikentta.asetaLaiva(laiva);

        laiva = new Laiva(3);
        laiva.asetaLaivanPaikka(5, 4, Suunta.PYSTY);
        assertFalse("Toinen laiva ei voi olla kulmittain toisessa laivassa kiinni.", pelikentta.asetaLaiva(laiva));

    }

    @Test
    public void tarkistaOnkoKiellettyAsettaaLaivaaAlkamanVasemmastaYlanurkasta() {
        Laiva laiva = new Laiva(2);
        laiva.asetaLaivanPaikka(1, 1, Suunta.PYSTY);

    }

    @Test
    public void testaaAmmuJosOsuu() {
        Laiva laiva = new Laiva(2);
        laiva.asetaLaivanPaikka(3, 4, Suunta.VAAKA);
        pelikentta.asetaLaiva(laiva);
        assertTrue("laivaan ei osunut vaikka laiva oli paikalla", pelikentta.ammu(4, 4));

    }

    @Test
    public void testaaAmmuJosHuti() {
        Laiva laiva = new Laiva(2);
        laiva.asetaLaivanPaikka(3, 4, Suunta.VAAKA);
        pelikentta.asetaLaiva(laiva);
        assertFalse("laivaan osui vaikka laivaa ei ollut paikalla", pelikentta.ammu(6, 7));
    }

    @Test
    public void voikoPelikentanReunaanAsettaaLaivaa() {
        Laiva laiva = new Laiva(3);
        laiva.asetaLaivanPaikka(1, 1, Suunta.PYSTY);
        assertTrue(pelikentta.asetaLaiva(laiva));

    }

    @Test
    public void voikoPelikentanOikeaanAlakulmaanAsettaaLaivaa() {
        Laiva laiva = new Laiva(1);
        laiva.asetaLaivanPaikka(9, 9, Suunta.PYSTY);
        assertTrue(pelikentta.asetaLaiva(laiva));

    }

    @Test
    public void uppoaakoLaiva() {
        Laiva laiva = new Laiva(2);
        laiva.asetaLaivanPaikka(2, 3, Suunta.PYSTY);
        pelikentta.asetaLaiva(laiva);
        assertTrue("Pitäisi osua", pelikentta.ammu(2, 3));
        assertTrue("Pitäisi osua", pelikentta.ammu(2, 4));

        assertTrue(laiva.upposiko());

        assertTrue(pelikentta.upposiko(2, 3));

    }

    @Test
    public void pysyykoPinnallaJosKokoLaivaanEiOleOsunut() {
        Laiva laiva = new Laiva(2);
        laiva.asetaLaivanPaikka(2, 3, Suunta.PYSTY);
        pelikentta.asetaLaiva(laiva);
        assertTrue("Pitäisi osua", pelikentta.ammu(2, 3));
        assertFalse(pelikentta.upposiko(2, 3));
        assertFalse("Ei pitäisi osua", pelikentta.ammu(2, 6));

        assertFalse(laiva.upposiko());

    }

    @Test
    public void testaaVoikoMennaYliAlueen() {
        Laiva laiva = new Laiva(2);
        laiva.asetaLaivanPaikka(10, 10, Suunta.VAAKA);
        assertFalse(pelikentta.asetaLaiva(laiva));
    }

    @Test
    public void testaaToString() {
        LaivojenPaikkojenArpoja arpoja = new LaivojenPaikkojenArpoja();
        arpoja.alustaLaivat(pelikentta);

        System.out.println("pelikentta " + pelikentta);
    }
    
    @Test
    public void testaaVoikoAsettaaSukellusvenettaLentotukialuksenViereen(){
        Laiva sukellusvene = new Laiva(1);
        
        sukellusvene.asetaLaivanPaikka(4,3,Suunta.PYSTY);
        
        Laiva lentotukialus = new Laiva(Laiva.LENTOTUKIALUS_PITUUS);
        
        lentotukialus.asetaLaivanPaikka(2, 4, Suunta.VAAKA);
        
        assertTrue(pelikentta.asetaLaiva(lentotukialus));
        
        assertFalse(pelikentta.asetaLaiva(sukellusvene));
    }

    @Test
    public void testaaKiellettyAlue() {
        Laiva l = new Laiva(1);

        l.asetaLaivanPaikka(3, 3, Suunta.PYSTY);
        pelikentta.asetaLaiva(l);

        System.out.println("kielletyt ruudut " + pelikentta.pelikenttaLaivatJaKielletytRuudutString());

        Laiva l2 = new Laiva(1);
        l2.asetaLaivanPaikka(4,4,Suunta.PYSTY);
        
        assertFalse(pelikentta.asetaLaiva(l2));
        
        l2.asetaLaivanPaikka(2, 2, Suunta.VAAKA);
        
        assertFalse(pelikentta.asetaLaiva(l2));
        
        
    }
    
    @Test
    public void testaaVasemmanReunaAlueenKiellettyAlue(){
        Laiva l = new Laiva(4);
        l.asetaLaivanPaikka(0, 3, Suunta.PYSTY);
        pelikentta.asetaLaiva(l);
        
        Laiva l2 = new Laiva(1);
        l2.asetaLaivanPaikka(1, 4, Suunta.PYSTY);
        
        assertFalse(pelikentta.asetaLaiva(l2));
        
    }

}
