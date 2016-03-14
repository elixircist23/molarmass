package com.example.eli.molarmasscalculator;

/**
 * Created by Eli on 3/14/2016.
 *
 * class to hold element states
 */
public class Element {

    String e;
    double m;

    public Element(String E, double M){
        e = E;
        m = M;
    }

    public String getE(){
        return e;
    }

    public double getM(){
        return m;
    }

}
