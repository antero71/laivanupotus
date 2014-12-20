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
        
        ruudut = new TreeSet<Ruutu>();
        for(int i = 0; i < pituus;i++){
            ruudut.add(new Ruutu());
        }
    }
    
    public int laivanPituus(){
        return ruudut.size();
    }
    
    
}
