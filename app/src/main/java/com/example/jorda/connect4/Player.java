package com.example.jorda.connect4;

import java.io.Serializable;

/**
 * Created by branden on 3/12/18.
 */

public class Player implements Serializable{


    private String name;
    private int pNumber;


    public Player(int pNumber){
        this.pNumber = pNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getpNumber() {
        return pNumber;
    }


    public String getName() {
        return name;
    }
}
