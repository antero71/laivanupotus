package com.antero.laivanupotus.domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

/**
 *
 * @author Antero Oikkonen
 */
public class Laiva {

    private TreeSet<Ruutu> ruudut;
    private Enum suunta;
    private int maxpituus = 5; // laivan maksimipituuden oletus

    /**
     * alustetaan laiva oletuksena alkamaan ruudusta (1,1)
     * ja olemaan pystyssä
     * @param pituus laivan pituus (1-4(5))
     */
    
    public Laiva(int pituus) {
        if(pituus<1 || pituus > maxpituus)
            throw new IllegalArgumentException("laivan pituuden pitää olla vähintään 1 ja enintään "+maxpituus);
        Ruutu r = null;
        ruudut = new TreeSet<Ruutu>();
        for (int i = 0; i < pituus; i++) {
            r = new Ruutu();
            r.setX(1);
            r.setY(i + 1);
            r.setLaivanOsa(true);
            ruudut.add(r);
        }
        suunta=Suunta.PYSTY;
    }

    public int getMaxpituus() {
        return maxpituus;
    }

    public void setMaxpituus(int maxpituus) {
        this.maxpituus = maxpituus;
    }

    
    
    public int laivanPituus() {
        return ruudut.size();
    }

    public TreeSet<Ruutu> annaLaivanRuudut() {
        return ruudut;
    }

    /**
     * asettaa laivan paikoilleen alkamaan koordinaateista
     * (alkux,alkuy) ja suunnan mukaisesti pystyyn tai vaakaan
     * @param alkux
     * @param alkuy
     * @param suunta 
     */
    
    public void asetaLaivanPaikka(int alkux, int alkuy, Enum suunta) {

        this.suunta = suunta;
        Iterator<Ruutu> ruudutIter = ruudut.iterator();

        Ruutu r = null;
        int i = 0;
        while (ruudutIter.hasNext()) {
            r = ruudutIter.next();
            i++;
            if (i == 1) {
                r.setX(alkux);
                r.setY(alkuy);
            } else {
                if (suunta.equals(Suunta.PYSTY)) {
                    r.setX(alkux);
                    r.setY(++alkuy);
                } else {
                    r.setX(++alkux);
                    r.setY(alkuy);
                }
            }

        }

    }

    /**
     * kääntää laivan, siten että alkupää pysyy paikallaan
     * alkupää on pää joka on lähinnä ruutua (1,1)
     */
    
    public void kaannaLaiva() {

        Iterator<Ruutu> iter = ruudut.iterator();
        int alkux=0;
        int alkuy=0;

        int i = 0;
        while (iter.hasNext()) {
            i++;
            Ruutu r = iter.next();

            if (i == 1) {
                alkux = r.getX();
                alkuy = r.getY();
                continue;
            }
            if (suunta.equals(Suunta.PYSTY)) {
                r.setX(++alkux);
                r.setY(alkuy);
            } else {
                r.setX(alkux);
                r.setY(++alkuy);
            }

        }

        if (suunta.equals(Suunta.PYSTY)) {
            suunta = Suunta.VAAKA;
        } else {
            suunta = Suunta.PYSTY;
        }

    }

}
