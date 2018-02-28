package com.TeamAwesome.ConnectFour.view;

import android.widget.Switch;

/**
 * Created by soyoungkim on 2018. 2. 25..
 */

public class MenuController {
    private String player1_color; //if the color is black, it goes first
    private String player2_color;
    private String player1;
    private String player2;
    private String player1_difficulty;
    private String player2_difficulty;
    private String Board_size;
    private int num_rounds=0; //needed to be implemented
    private String first_player; //Either player1 or player2

    private int Board_row;
    private int Board_column;

    public MenuController() {
    }

    public void setBoard_rows_columns(){
        if(Board_size.equals("Little")) {
            this.Board_row = 6;
            this.Board_column = 7;

        }else if(Board_size.equals("Middle")) {
            this.Board_row = 7;
            this.Board_column = 8;
        }else if(Board_size.equals("Bigly")){
            this.Board_row = 8;
            this.Board_column = 10;
        }else{
            this.Board_row = 0;
            this.Board_column =0;
        }

    }

    public String decide_first_player(){
        if(player1_color.equals("Black")) {
            return "player1";
        }else if(player2_color.equals("Black")) {
            return "player2";
        }else{
            return "none";
        }
    }

    public String getFirst_player() {
        return first_player;
    }

    public int getBoard_row() {
        return Board_row;
    }

    public int getBoard_column() {
        return Board_column;
    }

    public String getPlayer1_color() {
        return player1_color;
    }

    public void setPlayer1_color(String player1_color) {
        this.player1_color = player1_color;
        this.first_player = decide_first_player();
    }

    public String getPlayer2_color() {
        return player2_color;
    }

    public void setPlayer2_color(String player2_color) {
        this.player2_color = player2_color;
        this.first_player = decide_first_player();
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public String getPlayer1_difficulty() {
        return player1_difficulty;
    }

    public void setPlayer1_difficulty(String player1_difficulty) {
        this.player1_difficulty = player1_difficulty;
    }

    public String getPlayer2_difficulty() {
        return player2_difficulty;
    }

    public void setPlayer2_difficulty(String player2_difficulty) {
        this.player2_difficulty = player2_difficulty;
    }

    public String getBoard_size() {
        return Board_size;
    }

    public void setBoard_size(String board_size) {
        Board_size = board_size;
        //added to decide how many rows and columns
        setBoard_rows_columns();
    }

    public int getNum_rounds() {
        return num_rounds;
    }

    public void setNum_rounds(int num_rounds) {
        this.num_rounds = num_rounds;
    }



}
