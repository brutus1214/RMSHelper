package com.jcsoftware.rmshelper;

/**
 * Created by brutu on 9/21/2016.
 * OK
 */

class ItemDetailSupplier {
    private String name;
    private int mpq;
    private int cost;

    ItemDetailSupplier() {

    }

    ItemDetailSupplier(String name, int mpq, int cost) {
        this.name = name;
        this.mpq = mpq;
        this.cost = cost;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCost(int cost){
        this.cost= cost;
    }

    public void setMPQ(int mpq){
        this.mpq = mpq;
    }

    public String getName() {
        return this.name;
    }

    public int getCost(){
        return this.cost;
    }

    public int setMPQ(){
        return this.mpq;
    }

}
