package com.jcsoftware.rmshelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.text.ParseException;
/**
 * Created by brutu on 11/27/2016.
 */

public class PO {
    private Boolean isSelected;
    private Integer id;
    private Integer PONumber;
    private Integer vendorID;
    private String vendorName;
    private Calendar PODate;

    PO () {
        isSelected = Boolean.FALSE;
        id = 0;
        PONumber = 0;
        vendorID = 0;
        vendorName = "NOBODY";
        PODate = Calendar.getInstance();
    }

    PO (Boolean is, Integer poid, Integer num, Integer id, String name, Calendar d) {
        isSelected = is;
        id = poid;
        PONumber = num;
        vendorID = id;
        vendorName = name;
        PODate = d;
    }

    // setter and  getter
    void setIsSelected(Boolean is) { isSelected = is; }
    void setID(Integer poid) { id = poid; }
    void setPONumber(Integer num) { PONumber = num; }
    void setVendorID(Integer id) { vendorID = id; }
    void setVendorName(String name) { vendorName = name; }
    void setPODate(Calendar d) { PODate = d; }
    void setPODate(String d) {
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy", Locale.US);
            PODate.setTime(dateFormatter.parse(d));
        } catch (ParseException e) {
            // log something
        }
    }

    Boolean getIsSelected() { return isSelected; }
    Integer getID() { return id; }
    Integer getPONumber() { return PONumber; }
    Integer getVendorID() { return vendorID; }
    String getVendorName() { return vendorName; }
    Calendar getPODate() { return PODate; }


}
