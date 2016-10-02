package com.jcsoftware.rmshelper;

import java.util.Date;
import java.util.List;

/**
 * Created by brutu on 9/21/2016.
 * OK
 */

class ItemDetail {
    private String itemlookupcode;
    private String description;
    private String subdescription1;
    private String subdescription2;
    private String subdescription3;
    private String extendeddescription;
    private double price;
    private double saleprice;
    private double cost;
    private Date lastsold;
    private String dept;
    private Date salestart;
    private Date saleend;
    private Boolean inactive;
    private List<ItemDetailSupplier> supplier;

    ItemDetail() {
    }

    ItemDetail(String ilc, String desc, String sd1, String sd2, String sd3, String ed, double price, double sp, double cost, Date ls, String dept, Date ss, Date se) {
        //Boolean inactive) {
        //List<ItemDetailSupplier> s) {
        this.itemlookupcode = ilc;
        this.description = desc;
        this.subdescription1 = sd1;
        this.subdescription2 = sd2;
        this.subdescription3 = sd3;
        this.extendeddescription = ed;
        this.price = price;
        this.saleprice = sp;
        this.cost = cost;
        this.lastsold = ls;
        this.dept = dept;
        this.salestart = ss;
        this.saleend = se;
        //this.inactive = inactive;
        //this.supplier = s;
    }

    void setItemLookupCode(String ilc){
        this.itemlookupcode = ilc;
    }

    void setDescription(String d){
        this.description = d;
    }

    void setSubDescription1(String d){
        this.subdescription1 = d;
    }

    void setSubDescription2(String d){
        this.subdescription2 = d;
    }

    void setSubDescription3(String d){
        this.subdescription3 = d;
    }

    void setExtendedDescription(String d){
        this.extendeddescription = d;
    }

    void setPrice(double price){
        this.price = price;
    }

    void setSalePrice(double price){
        this.saleprice = price;
    }

    void setCost(double c){
        this.cost = c;
    }

    void setLastSold(Date ls){
        this.lastsold = ls;
    }

    public void setDept(String d){
        this.dept = d;
    }

    public void setSaleStart(Date ss){
        this.salestart = ss;
    }

    public void setSaleEnd(Date se){
        this.saleend = se;
    }

    void setInactive(Boolean i){
        this.inactive = i;
    }

    public void setSupplier(List<ItemDetailSupplier> s){
        this.supplier = s;
    }

    public String getItemLookupCode(){
        return this.itemlookupcode;
    }

    String getDescription(){
        return this.description;
    }

    String getSubDescription1(){
        return this.subdescription1;
    }

    String getSubDescription2(){
        return this.subdescription2;
    }

    String getSubDescription3(){
        return this.subdescription3;
    }

    String getExtendedDescription(){
        return this.extendeddescription;
    }

    double getPrice(){
        return this.price;
    }

    double getSalePrice(){
        return this.saleprice;
    }

    double getCost(){
        return this.cost;
    }

    Date getLastSold(){
        return this.lastsold;
    }

    public String getDept(){
        return this.dept;
    }

    public Date getSaleStart(){
        return this.salestart;
    }

    public Date getSaleEnd(){
        return this.saleend;
    }

    public Boolean getInactive(){
        return this.inactive;
    }

    public List<ItemDetailSupplier> getSupplier(){
        return this.supplier;
    }

}
