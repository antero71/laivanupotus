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

    public Laiva(int pituus) {
        Ruutu r = null;
        ruudut = new TreeSet<Ruutu>();
        for (int i = 0; i < pituus; i++) {
            r = new Ruutu();
            r.setX(1);
            r.setY(i + 1);
            r.setLaivanOsa(true);
            ruudut.add(r);
        }
    }

    public int laivanPituus() {
        return ruudut.size();
    }

    public TreeSet<Ruutu> annaLaivanRuudut() {
        return ruudut;
    }

    public void asetaLaivanPaikka(int alkux, int alkuy, Enum suunta) {

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

}
