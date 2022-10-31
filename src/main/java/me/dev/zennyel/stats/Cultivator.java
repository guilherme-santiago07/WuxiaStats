package me.dev.zennyel.stats;

import org.bukkit.entity.Player;

public class Cultivator {

    private int level;
    private double experience;
    private double health;
    private double actualHealth = health;
    private double mana;
    private double actualMana = mana;
    private double power;
    private int points;


    public Cultivator(int level, double health, double mana, double power, int points, double experience){
        this.experience = experience;
        this.mana = mana;
        this.power = power;
        this.level = level;
        this.health = health;
        this.points = points;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getActualHealth() {
        return actualHealth;
    }

    public void setActualHealth(double actualHealth) {
        this.actualHealth = actualHealth;
    }

    public double getMana() {
        return mana;
    }

    public void setMana(double mana) {
        this.mana = mana;
    }

    public double getActualMana() {
        return actualMana;
    }

    public void setActualMana(double actualMana) {
        this.actualMana = actualMana;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
