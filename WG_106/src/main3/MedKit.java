package main3;

import required.Consumable;
import required.Figure;
import required.Thing;

public class MedKit extends Consumable {
    private double healthMultiplier; // pengali untuk menentukan nilai healthValue
    private int healthValue; // nilai asli yang akan ditambahkan pada class Figure

    public MedKit(String vName, String vDescription, Thing vOwner, int vPrice, String vRarity) {
        super(vName, vDescription, vOwner, vPrice, vRarity);
    }

    @Override
    public void describe() {
        super.describe();
        System.out.println("Multiplier : " + this.healthMultiplier);
        System.out.println("Value : " + this.healthValue);
    }

    @Override
    public void used(Thing newOwner) {
        Thing tempOwner = getOwner(); // owner awal -> inventory player
        setOwner(newOwner); // owner baru -> Hero yang hendak menggunakan
        healPower();

        Figure tempFigure = (Figure) newOwner;
        tempFigure.setHealth(tempFigure.getHealth() + healthValue);

        if (tempFigure.getHealth() > tempFigure.getMaxHealth()) {
            tempFigure.setHealth(tempFigure.getMaxHealth());
        }

        tempOwner.getArrThing().remove(this);
    }

    public void healPower() {
        healthValue = (int) (((Figure) getOwner()).getHealth() * healthMultiplier);
    }

    // Getter & Setter
    public double getHealthMultiplier() {
        return healthMultiplier;
    }

    public void setHealthMultiplier(double vHealthMultiplier) {
        healthMultiplier = vHealthMultiplier;
    }

    public int getHealthValue() {
        return healthValue;
    }

    public void setHealthValue(int vHealthValue) {
        healthValue = vHealthValue;
    }
}
