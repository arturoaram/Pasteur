package com.example.arturo.supermarket;

/**
 * Created by Arturo on 10/11/2015.
 */
public class product {
    String upc; // Quitar
    String name;    //MANDATORY
    String category; //necesito
    int lifeSpan;   //necesito
    double price;   // MANDATORY

    public product(String iUpc, String iName, String iCategory, int iLifeSpan, Double iPrice){
        this.upc = iUpc;
        this.name=iName;
        this.category = iCategory;
        this.lifeSpan = iLifeSpan;
        this.price = iPrice;
    }

    public product(String iName, String iCategory, int iLifeSpan, Double iPrice){
        this.name=iName;
        this.category = iCategory;
        this.lifeSpan = iLifeSpan;
        this.price = iPrice;
    }

    public String getUpc() {
        return upc;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getLifeSpan() {
        return lifeSpan;
    }

    public double getPrice() {
        return price;
    }


    public void setUpc(String upc) {
        this.upc = upc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLifeSpan(int lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
