package com.example.weapgamefx;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Player {
    private String name;
    private Queue<Weapon> weapons;
    private int x;
    private int y;

    private int prevX;
    private int prevY;

    public Player(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.prevX = x;
        this.prevY = y;
        weapons = new LinkedList<>();
    }

    public void setPosition(int x, int y) {
        this.prevX = this.x;
        this.prevY = this.y;
        this.x = x;
        this.y = y;
    }


    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
    }


    public Weapon getWeapon() {
        Weapon lastWeapon = null;
        for (Weapon weapon : weapons) {
            lastWeapon = weapon;
        }
        return lastWeapon;
    }
    public String printWeaponQueue() {
        String word = "";
        word += (this.getName() + "'s deck is:");
        //System.out.print(this.getName() + "'s deck is:");
        for (Weapon weapon : weapons) {
            //System.out.print(weapon.getSymbol() + " ");
            word+=(weapon.getSymbol() + " ");
        }
        //System.out.println(word);
        return word;
    }
    public void giveWeapons(Player winner)
    {
        while (!weapons.isEmpty())
        {
            winner.addWeapon(weapons.poll());

        }
    }
}


