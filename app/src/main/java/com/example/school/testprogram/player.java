package com.example.school.testprogram;

/**
 * Created by soyoungkim on 2018. 1. 24..
 */

public class player {

    public static String player_id;
    public static int board_size_column;
    public static int board_size_row;
    public static int disk_position; //column number to insert a disk

    public void choose_board_size(){

    }

    public void choose_disk_size(){

    }

    public static String getPlayer_id() {
        return player_id;
    }

    public static void setPlayer_id(String player_id) {
        player.player_id = player_id;
    }

    public static int getBoard_size_column() {
        return board_size_column;
    }

    public static void setBoard_size_column(int board_size_column) {
        player.board_size_column = board_size_column;
    }

    public static int getBoard_size_row() {
        return board_size_row;
    }

    public static void setBoard_size_row(int board_size_row) {
        player.board_size_row = board_size_row;
    }

    public static int getDisk_position() {
        return disk_position;
    }

    public static void setDisk_position(int disk_position) {
        player.disk_position = disk_position;
    }
}
