/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.kayttoliittyma.kuuntelijat;

import com.antero.laivanupotus.kayttoliittyma.Kayttoliittyma;
import com.antero.laivanupotus.logiikka.Pelikentta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Luokka ei ole käytössä, luokan tarkoitus on kääntää laiva kun se on ensin
 * asetettu pelaajan pelialuueelle.
 *
 * @author Antero Oikkonen
 */
public class KaannaLaivaListener implements ActionListener {

    private Kayttoliittyma kali;

    public KaannaLaivaListener(Kayttoliittyma kali) {
        this.kali = kali;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        kali.getValittuLaiva().kaannaLaiva();

    }

}
