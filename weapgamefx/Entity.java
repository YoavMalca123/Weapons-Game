package com.example.weapgamefx;

import javafx.scene.text.Text;

public class Entity {
    private char symbol;
    private int x;
    private int y;

    public Entity(char symbol, int x, int y) {
        this.symbol = symbol;
        this.x = x;
        this.y = y;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
