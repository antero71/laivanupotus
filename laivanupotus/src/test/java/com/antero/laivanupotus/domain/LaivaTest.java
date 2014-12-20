/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.domain;

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
    public void testaaLaivanPituusAlustuksenJalkeen(){
        assertEquals(1, laiva.laivanPituus());
    }
}
