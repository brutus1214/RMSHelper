package com.jcsoftware.rmshelper;

import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by brutu on 10/2/2016.
 * OK
 */

class ItemPrintAsyncTask extends AsyncTask<String, Integer, Integer> {
    private MainActivity parent;
    private String upc;
    private LoginData logindata;

    ItemPrintAsyncTask(MainActivity p, String u, LoginData ld){
        parent = p;
        upc= u;
        logindata = ld;
    }

    @Override
    protected Integer doInBackground(String... params) {


        ItemDetail itemdetail = new ItemDetail();

        Log.i("Android"," ItemPrintAsyncTask. Start");
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
            Integer numRows = stmt.executeUpdate("update " +
                    "item set consignment = 1 " +
                    "where " +
                    "itemlookupcode = '" +params[0] +
                    "'"
            );

            // close stmt
            stmt.close();
            // close connection
            conn.close();
            if (numRows != 1) {
                return 0;
            } else {
                return 1;
            }
        } catch (ClassNotFoundException | SQLException | java.lang.InstantiationException | IllegalAccessException e) {
            Log.e("EXCEPTION", Log.getStackTraceString(e));
            return null;
        }  finally {
            Log.i("Android"," ItemPrintAsyncTask. End ");
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
    protected void onPostExecute(Integer numRows) {
        super.onPostExecute(numRows);
        if (numRows != 1) {
            parent.PrintConfirmation(0);
        } else {
            parent.PrintConfirmation(1);
        }
        dismissProgressBar();
    }


    private void dismissProgressBar() {
        // TODO Auto-generated method stub

    }



}
