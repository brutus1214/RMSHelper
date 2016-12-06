package com.jcsoftware.rmshelper;

import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


/**
 * Created by brutu on 11/29/2016.
 */

public class POSelectorAsyncTask extends AsyncTask<String, Void, List<PO>> {
    private POSelectorActivity parent;
    private LoginData logindata;
    private Calendar start;
    private Calendar end;

    POSelectorAsyncTask(POSelectorActivity p, LoginData ld, Calendar s, Calendar e, List<PO> list){
        parent = p;
        logindata = ld;
        start = s;
        end = e;
    }

    @Override
    protected List<PO> doInBackground(String... params) {

        Log.i("Android"," POSelectorAsyncTask. Start");
        Connection conn = null;
        Statement stmt = null;
        List<PO> POList = null;
        PO po = null;

        try {
            String driver = "net.sourceforge.jtds.jdbc.Driver";
            Class.forName(driver).newInstance();
            String connString = "jdbc:jtds:sqlserver://" + logindata.getServer() + "/" + logindata.getDb();
            String username = logindata.getName();
            String password = logindata.getPwd();
            conn = DriverManager.getConnection(connString,username,password);
            Log.w("Connection","open");

            int yearS = start.get(Calendar.YEAR); // get the current year
            int monthS = start.get(Calendar.MONTH); // month...
            int dayS = start.get(Calendar.DAY_OF_MONTH); // current day in the month

            int yearE = end.get(Calendar.YEAR); // get the current year
            int monthE = end.get(Calendar.MONTH); // month...
            int dayE = end.get(Calendar.DAY_OF_MONTH); // current day in the month

            stmt = conn.createStatement();
            ResultSet reset = stmt.executeQuery("SELECT " +
                    "ID, PONumber, POTitle, SupplierID, DateCreated FROM PurchaseOrder " +
                    "WHERE " +
                    "DateCreated BETWEEN '" + yearS + "-" + monthS + "-" + dayS +
                    "' AND '" + yearE + "-" + monthE + "-" + dayE +
                    "'"
            );

            while(reset.next()){
                po = new PO();
                po.setID(Integer.parseInt(reset.getString("ID")));
                po.setVendorID(Integer.parseInt(reset.getString("SupplierID")));
                po.setPONumber(Integer.parseInt(reset.getString("PONumber")));
                po.setPODate(reset.getString("DateCreated"));
                POList.add(po);
            }


            // close stmt
            stmt.close();
            // close connection
            conn.close();
            return POList;

        } catch (ClassNotFoundException | SQLException | java.lang.InstantiationException | IllegalAccessException e) {
            Log.e("EXCEPTION", Log.getStackTraceString(e));
            return null;
        }  finally {
            Log.i("Android"," POSelectorAsyncTask. End ");
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                Log.e("EXCEPTION", Log.getStackTraceString(e));
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
                Log.e("EXCEPTION", Log.getStackTraceString(e));
            }
            publishProgress();
        }

    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        displayProgressBar("Connecting...");
    }

    private void displayProgressBar(String string) {
        // TODO Auto-generated method stub

    }

    private void updateProgressBar(Integer integer) {
        // TODO Auto-generated method stub

    }


    @Override
    protected void onPostExecute(List<PO> list) {
        super.onPostExecute(list);
        parent.POSelectorConfirmation(list);
        dismissProgressBar();
    }


    private void dismissProgressBar() {
        // TODO Auto-generated method stub

    }




}
