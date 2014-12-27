/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.kayttoliittyma;

import com.antero.laivanupotus.domain.Pelaaja;
import com.antero.laivanupotus.logiikka.Pelikentta;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Antero Oikkonen
 */
public class Alkunaytto extends javax.swing.JFrame {

    /**
     * Creates new form Alkunaytto
     */
    public Alkunaytto() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        nimiLabel = new javax.swing.JLabel();
        nimiKentta = new javax.swing.JTextField();
        okButton = new javax.swing.JButton();
        peruButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        textX = new javax.swing.JTextField();
        textY = new javax.swing.JTextField();
        virheilmoitusLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Laivanupotus");

        nimiLabel.setText("Anna nimesi:");

        nimiKentta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nimiKenttaActionPerformed(evt);
            }
        });

        okButton.setText("Ok");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        peruButton.setText("Peru");

        jLabel2.setText("Kentän koko:");

        textX.setText("10");
        textX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textXActionPerformed(evt);
            }
        });

        textY.setText("10");
        textY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textYActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(118, 118, 118)
                        .addComponent(textX, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(textY, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(peruButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel1))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(nimiLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(virheilmoitusLabel)
                                    .addComponent(nimiKentta, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(okButton))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nimiLabel)
                    .addComponent(nimiKentta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(okButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(peruButton)
                    .addComponent(jLabel2)
                    .addComponent(textX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(virheilmoitusLabel)
                .addContainerGap(176, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nimiKenttaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nimiKenttaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nimiKenttaActionPerformed

    private boolean onkoKokoOikein(Integer x, Integer y) {

        System.out.println("x " + x);
        System.out.println("y " + y);
        try {

            x = Integer.valueOf(textX.getText());
            y = Integer.valueOf(textY.getText());
        } catch (NumberFormatException e) {
            return false;
        }

        System.out.println("x " + x);
        System.out.println("y " + y);

        Integer i10 = new Integer(10);
        Integer i15 = new Integer(15);

        if (x < i10 || x > i15) {
            return false;
        }

        if (y < i10 || y > i15) {
            return false;
        }
        return true;
    }

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        // TODO add your handling code here:

        Integer x = new Integer(0);
        Integer y = new Integer(0);

        boolean kokoOk = onkoKokoOikein(x, y);
        if (!kokoOk) {
            virheilmoitusLabel.setText("Kenttiin tulee syöttää numerot 10...15");
            return;
        }

        x = Integer.valueOf(textX.getText());
        y = Integer.valueOf(textY.getText());

        System.out.println("x " + x);
        System.out.println("y " + y);

        Kayttoliittyma k = new Kayttoliittyma(x, y, false);
        // k.setPelaaja(new Pelaaja(nimiKentta.getText(), 0));
        muodostaKayttoliittyma2(k);
    }//GEN-LAST:event_okButtonActionPerformed

    private void muodostaKayttoliittyma1(Kayttoliittyma k) {
        JPanel panel = new JPanel(new GridLayout(3, 1));

        JPanel pelipanel = k.luoRuudukko();

        //k.getContentPane().add(pelipanel);
        Pistenaytto pistenaytto = new Pistenaytto();
        pistenaytto.setPelaaja(new Pelaaja(nimiKentta.getText(), 0));
        panel.add(pistenaytto);
        panel.add(pelipanel);
        //k.getContentPane().add(pistenaytto);
        k.getContentPane().add(panel);

        k.pack();
        k.setVisible(true);
    }

    private void muodostaKayttoliittyma2(Kayttoliittyma k) {
        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JPanel pelipanel = k.luoRuudukko();
        pelipanel.setBorder(BorderFactory.createLineBorder(Color.yellow, 2));

        //k.getContentPane().add(pelipanel);
        k.createPistenaytto();
        Pistenaytto pistenaytto = k.getPistenaytto();
        pistenaytto.setPelaaja(new Pelaaja(nimiKentta.getText(), 0));
        panel.add(pistenaytto);
        pistenaytto.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        //pistenaytto.add(Box.createHorizontalGlue());
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(pelipanel);
        //k.getContentPane().add(pistenaytto);
        k.getContentPane().add(panel);

        k.pack();
        k.setVisible(true);
    }


    private void textXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textXActionPerformed

    private void textYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textYActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textYActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Alkunaytto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Alkunaytto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Alkunaytto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Alkunaytto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Alkunaytto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField nimiKentta;
    private javax.swing.JLabel nimiLabel;
    private javax.swing.JButton okButton;
    private javax.swing.JButton peruButton;
    private javax.swing.JTextField textX;
    private javax.swing.JTextField textY;
    private javax.swing.JLabel virheilmoitusLabel;
    // End of variables declaration//GEN-END:variables
}
