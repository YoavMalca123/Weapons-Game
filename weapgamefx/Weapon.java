package com.example.weapgamefx;

public class Weapon {
    private char symbol;

    public Weapon(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public boolean stronger(Weapon other) {
        if (this.symbol == 'F' && other.symbol == 'S') {
            return true;
        } else if (this.symbol == 'R' && other.symbol == 'F') {
            return true;
        } else if (this.symbol == 'S' && other.symbol == 'R') {
            return true;
        }
        return false;
    }
}
