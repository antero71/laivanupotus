/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antero.laivanupotus.domain;

/**
 * Pelin ruudut,
 *
 * @author Antero Oikkonen
 */
public class Ruutu implements Comparable<Ruutu> {

    private int x;
    private int y;


    private boolean laivanOsa = false;
    private boolean osui = false;
    private boolean ammuttu = false;

    public Ruutu(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Ruutu() {

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

    public boolean isLaivanOsa() {
        return laivanOsa;
    }

    public void setLaivanOsa(boolean laivanOsa) {
        this.laivanOsa = laivanOsa;
    }

    public boolean isOsui() {
        return osui;
    }

    public boolean isAmmuttu() {
        return ammuttu;
    }

    public void setAmmuttu(boolean ammuttu) {
        this.ammuttu = ammuttu;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ruutu other = (Ruutu) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Ruutu o) {
        int x = o.getX();
        int y = o.getY();

        if (this.x == x && this.y < y) {
            return -1;
        }

        if (this.x < x && this.y == y) {
            return -1;
        }

        if (this.x == x && this.y == y) {
            return 0;
        }

        return 1;
    }

    @Override
    public String toString() {
        return "x= " + x + ": y=" + y + ": isLaiva = " + laivanOsa + ", ammuttu = " + ammuttu + ", osui = " + osui;
    }

    /**
     * päivitetään osoiko ruutuun vai ei
     *
     * @param osui
     */
    public void osui(boolean osui) {
        this.osui = osui;
    }

}
