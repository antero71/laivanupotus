/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.antero.laivanupotus.domain;

/**
 *
 * @author Antero Oikkonen
 */
public class Ruutu {
    
    private int x;
    private int y;
    
    private boolean kielletty;
    private boolean laivanOsa;

    public Ruutu(int x,int y){
        this.x = x;
        this.y = y;
    }
    
    public Ruutu(){
        
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isKielletty() {
        return kielletty;
    }

    public void setKielletty(boolean kielletty) {
        this.kielletty = kielletty;
    }

    public boolean isLaivanOsa() {
        return laivanOsa;
    }

    public void setLaivanOsa(boolean laivanOsa) {
        this.laivanOsa = laivanOsa;
    }
    
    
    
}
