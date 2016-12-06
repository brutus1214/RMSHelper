package com.jcsoftware.rmshelper;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    final Context context = this;
    ItemDetail itemdetail;
    LoginData ld = new LoginData();
    PO po = new PO();

    public void setItemDetail (ItemDetail id) {
        itemdetail = id;
    }

    public ItemDetail getItemDetail () {
        return itemdetail;
    }

    private boolean IsILCPresent () {
        EditText etILC= (EditText) findViewById(R.id.etILC);
        if (etILC.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etILC= (EditText) findViewById(R.id.etILC);
        etILC.setOnFocusChangeListener(new View.OnFocusChangeListener()
                                       {
                                           @Override
                                           public void onFocusChange(View v, boolean hasFocus)
                                           {
                                               if(hasFocus)
                                               {
                                                   EditText etILC= (EditText) findViewById(R.id.etILC);
                                                   etILC.setSelectAllOnFocus(true);
                                               }
                                           }
                                       }
        );
        etILC.setFocusable(true);
        etILC.requestFocus();


        Button bPrint = (Button) findViewById(R.id.bPrint);

        bPrint.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                EditText etUPC = (EditText) findViewById(R.id.etILC);

                if (IsILCPresent()) {
                    String upc = etUPC.getText().toString();
                    ItemPrintAsyncTask at = new ItemPrintAsyncTask(MainActivity.this, upc, ld);
                    at.execute(upc);
                } else {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                    // set title
                    alertDialogBuilder.setTitle("RMSHelper Warning");

                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Please enter or scan Item Lookup Code!")
                            .setCancelable(false)
                            .setNegativeButton("OK",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // if this button is clicked, just close
                                    // the dialog box and do nothing
                                    dialog.cancel();
                                }
                            });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();
                }
            }

        });

        Button bSelect = (Button) findViewById(R.id.bSelect);

        bSelect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                EditText etUPC = (EditText) findViewById(R.id.etILC);

                if (IsILCPresent()) {
                    String upc = etUPC.getText().toString();
                    ItemDetailAsyncTask at = new ItemDetailAsyncTask(MainActivity.this, upc, ld);
                    at.execute(upc);
                } else {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                    // set title
                    alertDialogBuilder.setTitle("RMSHelper Warning");

                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Please enter or scan Item Lookup Code!")
                            .setCancelable(false)
                            .setNegativeButton("OK",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // if this button is clicked, just close
                                    // the dialog box and do nothing
                                    dialog.cancel();
                                }
                            });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();
                }
            }

        });

        Button bPO = (Button) findViewById(R.id.bPO);

        bPO.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (po.getIsSelected()) {
                    // Add current item to PO
              } else {
                    if (IsILCPresent()) {
                        Intent intent = new Intent(v.getContext(), POSelectorActivity.class);
                        startActivity(intent);
                    } else {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                        // set title
                        alertDialogBuilder.setTitle("RMSHelper Warning");

                        // set dialog message
                        alertDialogBuilder
                                .setMessage("Please enter or scan Item Lookup Code!")
                                .setCancelable(false)
                                .setNegativeButton("OK",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // if this button is clicked, just close
                                        // the dialog box and do nothing
                                        dialog.cancel();
                                    }
                                });

                        // create alert dialog
                        AlertDialog alertDialog = alertDialogBuilder.create();

                        // show it
                        alertDialog.show();
                    }

                }
            }

        });


        Button bExit = (Button) findViewById(R.id.bExit);

        bExit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }

        });


    }

    public void ReturnItemDetailSet(ItemDetail id){

        if (id != null) {

            TextView tvLastSold = (TextView) findViewById(R.id.tvLastSold);
            tvLastSold.setText(id.getLastSold().toString());

            TextView tvDescription = (TextView) findViewById(R.id.tvDescription);
            tvDescription.setText(id.getDescription());

            EditText etExtendedDescription = (EditText) findViewById(R.id.etExtendedDescription);
            etExtendedDescription.setText(id.getExtendedDescription());

            TextView tvSubDescription1 = (TextView) findViewById(R.id.tvSubDescription1);
            tvSubDescription1.setText(id.getSubDescription1());

            TextView tvSubDescription2 = (TextView) findViewById(R.id.tvSubDescription2);
            tvSubDescription2.setText(id.getSubDescription2());

            TextView tvSubDescription3 = (TextView) findViewById(R.id.tvSubDescription3);
            tvSubDescription3.setText(id.getSubDescription3());

            TextView tvRetailPrice = (TextView) findViewById(R.id.tvRetailPrice);
            tvRetailPrice.setText(String.format(Locale.US, "%1$.2f", id.getPrice()));

            TextView tvSalePrice = (TextView) findViewById(R.id.tvSalePrice);
            tvSalePrice.setText(String.format(Locale.US, "%1$.2f", id.getSalePrice()));

            TextView tvCost = (TextView) findViewById(R.id.tvCost);
            tvCost.setText(String.format(Locale.US, "%1$.2f", id.getCost()));
        } else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

            // set title
            alertDialogBuilder.setTitle("RMSHelper Warning");

            // set dialog message
            alertDialogBuilder
                    .setMessage("Item Not Found!")
                    .setCancelable(false)
                    .setNegativeButton("OK",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing
                            dialog.cancel();
                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
        }

        // Highlight and focus ilc textview
        EditText etUPC = (EditText) findViewById(R.id.etILC);
        etUPC.requestFocus();
        etUPC.selectAll();

    }

    public void PrintConfirmation(Integer numRows) {
        if (numRows != 1) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

            // set title
            alertDialogBuilder.setTitle("RMSHelper Warning");

            // set dialog message
            alertDialogBuilder
                    .setMessage("Item Print Failed!")
                    .setCancelable(false)
                    .setNegativeButton("OK",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing
                            dialog.cancel();
                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
        } else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

            // set title
            alertDialogBuilder.setTitle("RMSHelper Confirmation");

            // set dialog message
            alertDialogBuilder
                    .setMessage("Item sent to print queue!")
                    .setCancelable(false)
                    .setNegativeButton("OK",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing
                            dialog.cancel();
                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
        }
    }
}
