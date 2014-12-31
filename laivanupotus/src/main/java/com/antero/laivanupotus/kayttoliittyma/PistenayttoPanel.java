/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.kayttoliittyma;

import com.antero.laivanupotus.domain.Pelaaja;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Antero Oikkonen
 */
public class PistenayttoPanel extends JPanel {

    private JLabel pelaajanNimi = new JLabel();
    private JLabel pelaajanPisteet = new JLabel();
    private JLabel ilmoitus = new JLabel();

    BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);

    public PistenayttoPanel() {
        this.setLayout(layout);
        this.add(pelaajanNimi);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(pelaajanPisteet);
        add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(ilmoitus);
        //pelaajanNimi.setText("Testi");
        //pelaajanPisteet.setText("109");
    }

    public JLabel getPelaajanPisteet() {
        return pelaajanPisteet;
    }

    public JLabel getPelaajanNimi() {
        return pelaajanNimi;
    }

    public JLabel getIlmoitus() {
        return ilmoitus;
    }

    public void setPelaaja(Pelaaja pelaaja) {
        pelaajanNimi.setText(pelaaja.getNimi());
        pelaajanPisteet.setText("" + pelaaja.getPisteet());

    }
}
