/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.tiedostot;

import com.antero.laivanupotus.domain.Pelaaja;
import com.antero.laivanupotus.domain.Pistetaulukko;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Antero Oikkonen
 */
public class Tiedosto {

    public static void tallennaTiedosto(Pistetaulukko pisteet) {

        Path file = Paths.get(System.getProperty("user.home"), "laivanupotus", "pisteet.txt");
        
        try {
            // Create the empty file with default permissions, etc.
            Files.createFile(file);
        } catch (FileAlreadyExistsException x) {
            System.err.format("file named %s"
                    + " already exists%n", file);
        } catch (IOException x) {
            // Some other sort of failure, such as permissions.
            System.err.format("createFile error: %s%n", x);
        }

        Charset charset = Charset.forName("UTF-8");
        //String s = ...;
        BufferedWriter writer = null;
        try {
            writer = Files.newBufferedWriter(file, charset);

            TreeSet<Pelaaja> pelaajat = pisteet.getPelaajat();

            for (Pelaaja p : pelaajat) {
                writer.write(p.getNimi(), 0, p.getNimi().length());
                String pist = "" + p.getPisteet();
                writer.write(pist, 0, pist.length());
            }

        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ex) {
                    Logger.getLogger(Tiedosto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

}
