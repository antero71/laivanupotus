/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.logiikka;

import com.antero.laivanupotus.domain.Laiva;
import com.antero.laivanupotus.domain.Ruutu;
import com.antero.laivanupotus.domain.Suunta;
import java.util.Random;
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
        assertFalse("Laivaa ei voi asettaa yli pelialueen", pelikentta.asetaLaiva(laiva, false));
    }

    @Test
    public void testaaLaivanAsetusJosMahtuuPelialueelle() {
        Laiva laiva = new Laiva(2);
        laiva.asetaLaivanPaikka(2, 3, Suunta.VAAKA);
        pelikentta.alustaRuudukko();
        assertTrue("Laivan asetus epäonnistui", pelikentta.asetaLaiva(laiva, false));
    }

    @Test
    public void tarkistaOnkoKiellettyAsettaaLaivaaJosLaivaJoSamassaPaikassa() {
        Laiva laiva = new Laiva(3);
        laiva.asetaLaivanPaikka(3, 4, Suunta.VAAKA);
        pelikentta.alustaRuudukko();
        pelikentta.asetaLaiva(laiva, false);

        laiva = new Laiva(3);
        laiva.asetaLaivanPaikka(3, 4, Suunta.VAAKA);
        assertFalse("Toista laivaa ei voi asettaa jo olemassaolevan laivan paikalle", pelikentta.asetaLaiva(laiva, false));

    }

    @Test
    public void tarkistaOnkoKiellettyAsettaaLaivaaToisenLaivanViereen() {
        Laiva laiva = new Laiva(3);
        laiva.asetaLaivanPaikka(3, 4, Suunta.VAAKA);
        pelikentta.alustaRuudukko();
        pelikentta.asetaLaiva(laiva, false);

        laiva = new Laiva(3);
        laiva.asetaLaivanPaikka(3, 5, Suunta.VAAKA);
        assertFalse("Toista laivaa ei voi asettaa jo olemassaolevan laivan viereen", pelikentta.asetaLaiva(laiva, false));

    }

    @Test
    public void tarkistaOnkoKiellettyAsettaaLaivaaKulmittainToisenLaivanViereenYhdenRuudunPaahan() {
        Laiva laiva = new Laiva(3);
        laiva.asetaLaivanPaikka(2, 3, Suunta.VAAKA);
        pelikentta.alustaRuudukko();
        pelikentta.asetaLaiva(laiva, false);

        laiva = new Laiva(3);
        laiva.asetaLaivanPaikka(5, 4, Suunta.PYSTY);
        assertFalse("Toinen laiva ei voi olla kulmittain toisessa laivassa kiinni.", pelikentta.asetaLaiva(laiva, false));

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
        pelikentta.asetaLaiva(laiva, false);
        assertTrue("laivaan ei osunut vaikka laiva oli paikalla", pelikentta.ammu(4, 4));

    }

    @Test
    public void testaaAmmuJosHuti() {
        Laiva laiva = new Laiva(2);
        laiva.asetaLaivanPaikka(3, 4, Suunta.VAAKA);
        pelikentta.asetaLaiva(laiva, false);
        assertFalse("laivaan osui vaikka laivaa ei ollut paikalla", pelikentta.ammu(6, 7));
    }

    @Test
    public void voikoPelikentanReunaanAsettaaLaivaa() {
        Laiva laiva = new Laiva(3);
        laiva.asetaLaivanPaikka(1, 1, Suunta.PYSTY);
        assertTrue(pelikentta.asetaLaiva(laiva, false));

    }

    @Test
    public void voikoPelikentanOikeaanAlakulmaanAsettaaLaivaa() {
        Laiva laiva = new Laiva(1);
        laiva.asetaLaivanPaikka(9, 9, Suunta.PYSTY);
        assertTrue(pelikentta.asetaLaiva(laiva, false));

    }

    @Test
    public void uppoaakoLaiva() {
        Laiva laiva = new Laiva(2);
        laiva.asetaLaivanPaikka(2, 3, Suunta.PYSTY);
        pelikentta.asetaLaiva(laiva, false);
        assertTrue("Pitäisi osua", pelikentta.ammu(3, 2));
        assertTrue("Pitäisi osua", pelikentta.ammu(4, 2));

        assertTrue(laiva.upposiko());

        assertTrue(pelikentta.upposiko(2, 3));

    }

    @Test
    public void pysyykoPinnallaJosKokoLaivaanEiOleOsunut() {
        Laiva laiva = new Laiva(2);
        laiva.asetaLaivanPaikka(2, 3, Suunta.PYSTY);
        pelikentta.asetaLaiva(laiva, false);
        assertTrue("Pitäisi osua", pelikentta.ammu(3, 2));
        assertFalse(pelikentta.upposiko(2, 3));
        assertFalse("Ei pitäisi osua", pelikentta.ammu(2, 6));

        assertFalse(laiva.upposiko());

    }

    @Test
    public void testaaVoikoMennaYliAlueen() {
        Laiva laiva = new Laiva(2);
        laiva.asetaLaivanPaikka(10, 10, Suunta.VAAKA);
        assertFalse(pelikentta.asetaLaiva(laiva, false));
    }

    @Test
    public void testaaVoikoAsettaaSukellusvenettaLentotukialuksenViereen() {

        Laiva sukellusvene = new Laiva(Laiva.SUKELLUSVENE_PITUUS);

        sukellusvene.asetaLaivanPaikka(4, 3, Suunta.PYSTY);

        Laiva lentotukialus = new Laiva(Laiva.LENTOTUKIALUS_PITUUS);

        lentotukialus.asetaLaivanPaikka(2, 4, Suunta.VAAKA);

        assertTrue(pelikentta.asetaLaiva(lentotukialus, false));

        assertFalse(pelikentta.asetaLaiva(sukellusvene, false));
    }

    @Test
    public void testaaKiellettyAlue() {
        Laiva l = new Laiva(1);

        l.asetaLaivanPaikka(3, 3, Suunta.PYSTY);
        pelikentta.asetaLaiva(l, false);

        // System.out.println("kielletyt ruudut " + pelikentta.pelikenttaLaivatJaKielletytRuudutString());
        Laiva l2 = new Laiva(1);
        l2.asetaLaivanPaikka(4, 4, Suunta.PYSTY);

        assertFalse(pelikentta.asetaLaiva(l2, false));

        l2 = new Laiva(1);
        l2.asetaLaivanPaikka(2, 2, Suunta.VAAKA);

        assertFalse(pelikentta.asetaLaiva(l2, false));

    }

    @Test
    public void testaaVasemmanReunaAlueenKiellettyAlue() {
        Laiva l = new Laiva(4);
        l.asetaLaivanPaikka(0, 4, Suunta.PYSTY);
        pelikentta.asetaLaiva(l, true);

        Laiva l2 = new Laiva(1);
        l2.asetaLaivanPaikka(1, 3, Suunta.PYSTY);

       // assertTrue(pelikentta.isKielletty(1, 3));
        assertFalse(pelikentta.asetaLaiva(l2, false));

        Laiva l3 = new Laiva(1);
        l3.asetaLaivanPaikka(1, 4, Suunta.PYSTY);

        assertFalse(pelikentta.asetaLaiva(l3, false));

    }

    @Test
    public void testaaVoikoLaittaaKahtaLaivaaPerakkain() {
        Laiva l1 = new Laiva(3);
        Laiva l2 = new Laiva(3);

        l1.asetaLaivanPaikka(1, 4, Suunta.VAAKA);

        l2.asetaLaivanPaikka(5, 4, Suunta.VAAKA);

        pelikentta.asetaLaiva(l1, true);

        assertTrue(pelikentta.asetaLaiva(l2, true));

        Laiva l3 = new Laiva(1);

        l3.asetaLaivanPaikka(4, 4, Suunta.PYSTY);

        assertFalse(pelikentta.asetaLaiva(l3, false));

    }
    
    @Test
    public void voikoLaittaaLaivatVierekkain(){
        Laiva l1 = new Laiva(1);
        l1.asetaLaivanPaikka(0, 5, Suunta.PYSTY);
        
        Laiva l2 = new Laiva(2);
        l2.asetaLaivanPaikka(1, 5, Suunta.VAAKA);
        
        pelikentta.asetaLaiva(l1, false);
        
        assertFalse(pelikentta.asetaLaiva(l2, false));
    }

    @Test
    public void onkoLaivanOsa() {
        Laiva l = new Laiva(4);
        l.asetaLaivanPaikka(0, 0, Suunta.VAAKA);
        pelikentta.asetaLaiva(l, false);

        assertTrue(pelikentta.isLaiva(0, 0));
        assertTrue(pelikentta.isLaiva(1, 0));
        assertTrue(pelikentta.isLaiva(2, 0));
        assertTrue(pelikentta.isLaiva(3, 0));
        assertFalse(pelikentta.isLaiva(4, 0));

    }
    
    @Test
    public void testaaVoikoLaivaaAsettaaLimittain(){
        Laiva l1 = new Laiva(Laiva.LENTOTUKIALUS_PITUUS);
        
        l1.asetaLaivanPaikka(2, 6, Suunta.VAAKA);
        
        pelikentta.asetaLaiva(l1, false);
        
        Laiva l2 = new Laiva(3);
        
        l2.asetaLaivanPaikka(0, 5, Suunta.VAAKA);
        
        assertFalse(pelikentta.asetaLaiva(l2, false));
        
        
        
    }
    
      
    @Test
    public void testaaVoikoLaivaaAsettaaLimittain2(){
        Laiva l1 = new Laiva(Laiva.LENTOTUKIALUS_PITUUS);
        
        l1.asetaLaivanPaikka(2, 6, Suunta.VAAKA);
        
        pelikentta.asetaLaiva(l1, false);
        
        Laiva l2 = new Laiva(3);
        
        l2.asetaLaivanPaikka(5, 7, Suunta.VAAKA);
        
        assertFalse(pelikentta.asetaLaiva(l2, false));
        
        
        
    }
    
    @Test
    public void testaaAmmuJosMeneeAlustamattomaanRuutuun(){
        assertFalse(pelikentta.ammu(1, 1));
    }
    
    @Test
    public void testaaOnkoAmmuttuAlustamattomaanRuutuun(){
        assertFalse(pelikentta.onkoAmmuttu(1, 1));
        
    }
    
    @Test
    public void testaaOnkoLaivaAlustamattomaanRuutuun(){
        assertFalse(pelikentta.isLaiva(1, 1));
        
    }
    
    @Test
    public void testaaOnkoAmmuttuJosRuutuAlustettu(){
        Laiva l = new Laiva(1);
        l.asetaLaivanPaikka(1, 1, Suunta.PYSTY);
        
        pelikentta.asetaLaiva(l, false);
        
        assertFalse(pelikentta.onkoAmmuttu(1, 1));
    }
    
    @Test
    public void testaaUpposikoAlustamattomaanRuutuun(){
        assertFalse(pelikentta.upposiko(1,1));
        
    }
    
    @Test
    public void testaaUpposikoRuutuParametrilla(){
        Ruutu r = new Ruutu(1,1);
        
        assertFalse(pelikentta.upposiko(r));
        
    }

}
