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
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Antero Oikkonen
 */
public class Tiedosto {

    /**
     * luetaan tiedostosta pistetaulukon tiedot
     *
     * @return
     */
    public static Pistetaulukko luePistetaulukkoTiedostosta() {
        Path file = luoTiedosto();

        Pistetaulukko pisteet = new Pistetaulukko();
        try {
            Scanner lukija = new Scanner(file);
            String rivi = null;

            Pelaaja p = null;
            while (lukija.hasNext()) {
                rivi = lukija.nextLine();
                p = muodostaPelaajaRivinTiedoista(rivi);
                pisteet.lisaaPelaajaPistetaulukkoon(p);
            }
        } catch (IOException ex) {
            Logger.getLogger(Tiedosto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pisteet;
    }

    /**
     * tallennetaan Pistetaulukon pisteet tiedostoon
     *
     * @param pisteet
     */
    public static void tallennaTiedosto(Pistetaulukko pisteet) {

        Path file = luoTiedosto();

        Charset charset = Charset.forName("UTF-8");
        //String s = ...;
        BufferedWriter writer = null;
        try {

            writer = Files.newBufferedWriter(file, charset, StandardOpenOption.APPEND);

            TreeSet<Pelaaja> pelaajat = pisteet.getPelaajat();

            for (Pelaaja p : pelaajat) {
                writer.write(p.getNimi(), 0, p.getNimi().length());
                writer.write(" ", 0, " ".length());
                String pist = "" + p.getPisteet();
                writer.write(pist, 0, pist.length());
                writer.write("\n", 0, "\n".length());
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

    private static Path luoTiedosto() {
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
            Path dir = Paths.get(System.getProperty("user.home"), "laivanupotus");

            try {
                Files.createDirectories(dir);
                Path file2 = Paths.get(System.getProperty("user.home"), "laivanupotus", "pisteet.txt");
                Files.createFile(file2);
            } catch (IOException ex) {
                Logger.getLogger(Tiedosto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return file;
    }

    private static Pelaaja muodostaPelaajaRivinTiedoista(String rivi) {
        String[] palat = rivi.split(" ");
        Pelaaja p = new Pelaaja();
        p.setNimi(palat[0]);
        p.setPisteet(Integer.parseInt(palat[1]));
        return p;
    }

}
