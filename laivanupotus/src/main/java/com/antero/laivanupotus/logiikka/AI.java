/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.logiikka;

import com.antero.laivanupotus.domain.Ruutu;

/**
 * Rajapinta pelin "älylle"
 *
 * @author Antero Oikkonen
 */
public interface AI {

    boolean ammu();

    Ruutu viimeksiAmmuttu();
}
