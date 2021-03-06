/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.logiikka;

import com.antero.laivanupotus.apuluokat.DeterministinenRandom;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Antero Oikkonen
 */
public class LaivojenPaikkojenArvontaTest {
    
    private LaivojenPaikkojenArpoja laivojenPaikatArpoja;
    private Pelikentta peli;
    
    public LaivojenPaikkojenArvontaTest() {
    }
    
    @Before
    public void setUp() {
        // kolmas, 0 vaaka, 1 pysty
        
        //int [] laivojenPaikatInt = new int []{0,3,1,2,1,0,4,7,1,1,8,0,7,0,1,6,8,0,5,4,0,2,5,0,8,6,0,9,3,0};
                                                                           // 4  ,  3  ,  3  ,  2  ,  2  ,  2  ,  1  ,  1  ,  1  ,  1  
        //DeterministinenRandom random = new DeterministinenRandom(new int []{9,6,1,2,1,0,4,6,1,1,8,0,7,1,1,6,6,0,4,3,0,2,3,0,2,5,0,7,4,0});
        laivojenPaikatArpoja = new LaivojenPaikkojenArpoja(new Random());
        peli = new Pelikentta(10, 10);
        laivojenPaikatArpoja.alustaLaivat(peli);
    }

    @Test
    public void onkoOikeaMaaraLaivoja() {
        assertEquals(10, peli.getLaivojenLkm());
    }
    
    @Test
    public void onkoArvottuLaivojaLiianLahelleToisiaan(){
        
    }
    
}
