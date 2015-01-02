/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.apuluokat;

import java.util.Random;

/**
 *
 * @author Antero Oikkonen
 */
public class DeterministinenRandom extends Random{
    
    private int[] palautettavatNumerot;
    private int index;

    public DeterministinenRandom(int[] palautettavatNumerot) {
        this.palautettavatNumerot = palautettavatNumerot;
        this.index = 0;
    }

    @Override
    public int nextInt(int n) {
        return palautettavatNumerot[index++];
    }
    
    
    
}
