package com.antero.laivanupotus.domain;

import java.util.HashSet;
import java.util.TreeSet;

/**
 *
 * @author Antero Oikkonen
 */
public class Laiva {
    private TreeSet<Ruutu> ruudut;
    
    public Laiva(int pituus){
        Ruutu r = null;
        ruudut = new TreeSet<Ruutu>();
        for(int i = 0; i < pituus;i++){
            r = new Ruutu();
            r.setX(1);
            r.setY(i+1);
            r.setLaivanOsa(true);
            ruudut.add(r);
        }
    }
    
    public int laivanPituus(){
        return ruudut.size();
    }
    
    
}
