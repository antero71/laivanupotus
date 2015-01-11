/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.kayttoliittyma.kuuntelijat;

import com.antero.laivanupotus.domain.Laiva;
import com.antero.laivanupotus.kayttoliittyma.Kayttoliittyma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Luokka ei ole käytössä (11.1.2014) koska laivat arvotaan
 * pelaajalle.
 * 
 * @author Antero Oikkonen
 */
public class ValitseLaivaKuuntelija implements ActionListener {

    private Kayttoliittyma kali;

    public ValitseLaivaKuuntelija(Kayttoliittyma kali) {
        this.kali = kali;
    }


    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton)e.getSource();
        String text = button.getText();
        Laiva l = new Laiva(text.length());
        kali.setValittuLaiva(l);
        button.setSelected(true);
    }

}
