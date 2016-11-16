package com.jcsoftware.rmshelper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by brutu on 9/21/2016.
 * OK
 */

class ItemDetailAsyncTask extends AsyncTask<String, Integer, ItemDetail> {
    private MainActivity parent;
    private String upc;
    private LoginData logindata;

    ItemDetailAsyncTask(MainActivity p, String u, LoginData ld){
        parent = p;
        upc= u;
        logindata = ld;
    }

    @Override
    protected ItemDetail doInBackground(String... params) {

        ItemDetail itemdetail = new ItemDetail();

        Log.i("Android"," MySQL Connect Example.");
        Connection conn = null;
        Statement stmt = null;
        try {
            String driver = "net.sourceforge.jtds.jdbc.Driver";
            Class.forName(driver).newInstance();
            String connString = "jdbc:jtds:sqlserver://" + logindata.getServer() + "/" + logindata.getDb();
            String username = logindata.getName();
            String password = logindata.getPwd();
            conn = DriverManager.getConnection(connString,username,password);
            Log.w("Connection","open");
            stmt = conn.createStatement();
            ResultSet reset = stmt.executeQuery("select " +
                    "itemlookupcode, " +
                    "description, " +
                    "subdescription1, " +
                    "subdescription2, " +
                    "subdescription3, " +
                    "extendeddescription, " +
                    "price, " +
                    "saleprice, " +
                    "cost, " +
                    "lastsold, " +
                    //"deptid, " +
                    //"salestart, " +
                    //"saleend, " +
                    "inactive " +
                    "from item " +
                    "where " +
                    "itemlookupcode = '" +params[0] +
                    "'"
            );
            //Print the data to the console
            while(reset.next()){
                String temp = reset.getString("itemlookupcode") + "  " + reset.getString("description") + "  " + reset.getString("price") + "  " + reset.getString("cost");
                Log.w("Data:", temp);
                itemdetail.setItemLookupCode(reset.getString("itemlookupcode"));
                itemdetail.setDescription(reset.getString("description"));
                itemdetail.setSubDescription1(reset.getString("subdescription1"));
                itemdetail.setSubDescription2(reset.getString("subdescription2"));
                itemdetail.setSubDescription3(reset.getString("subdescription3"));
                itemdetail.setExtendedDescription(reset.getString("extendeddescription"));
                itemdetail.setPrice(Double.parseDouble(reset.getString("price")));
                itemdetail.setSalePrice(Double.parseDouble(reset.getString("saleprice")));
                itemdetail.setCost(Double.parseDouble(reset.getString("cost")));
                itemdetail.setLastSold(reset.getDate("lastsold"));
                //itemdetail.setDept(reset.getString("dept"));
                //itemdetail.setSaleStart(reset.getDate("salestart"));
                //itemdetail.setSaleEnd(reset.getDate("saleend"));
                itemdetail.setInactive(reset.getBoolean("inactive"));

            }

            // close stmt
            stmt.close();
            // close connection
            conn.close();
            return itemdetail;

        } catch (ClassNotFoundException | SQLException | java.lang.InstantiationException | IllegalAccessException e) {
            Log.e("EXCEPTION", Log.getStackTraceString(e));
            return null;
        } finally {
            Log.i("Android","Close Conn ");
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

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        //updateProgressBar(values[0]);
    }

    private void updateProgressBar(Integer integer) {
        // TODO Auto-generated method stub

    }


    @Override
    protected void onPostExecute(ItemDetail result) {
        super.onPostExecute(result);
        if (result.getItemLookupCode() == null) {
            parent.ReturnItemDetailSet(null);
        } else {
            parent.ReturnItemDetailSet(result);
        }
        dismissProgressBar();
    }


    private void dismissProgressBar() {
        // TODO Auto-generated method stub

    }
}
