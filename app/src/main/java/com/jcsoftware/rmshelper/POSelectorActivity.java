package com.jcsoftware.rmshelper;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;


public class POSelectorActivity extends AppCompatActivity {

    private TextView tvSelectStartDate;
    private TextView tvSelectEndDate;

    private int year;
    private int month;
    private int day;

    LoginData ld = new LoginData();
    List<PO> POList;

    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poselector);
        dateFormatter = new SimpleDateFormat("MM-dd-yyyy", Locale.US);
        setCurrentDateOnView();

        // Now get Dates from range and retrieve POs from SQLDB
        Calendar cal = Calendar.getInstance();

        POSelectorAsyncTask at = new POSelectorAsyncTask(this, ld, cal, cal, POList);
        at.execute();

        Button bCancel = (Button) findViewById(R.id.bCancel);

        bCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }

        });

        Button bSelect = (Button) findViewById(R.id.bSelect);

        bSelect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Set PO object initialize and finish

                finish();
            }

        });


        TextView tvStart = (TextView) findViewById(R.id.tvSelectStartDate);

        tvStart.setOnClickListener(new View.OnClickListener() {
            DatePickerDialog startDatePickerDialog = new DatePickerDialog(POSelectorActivity.this, new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Calendar newDate = Calendar.getInstance();
                    newDate.set(year, monthOfYear, dayOfMonth);
                    tvSelectStartDate.setText(dateFormatter.format(newDate.getTime()));
                }

            },Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

            @Override
            public void onClick(View v) {
                startDatePickerDialog.show();
            }

        });

        TextView tvEnd = (TextView) findViewById(R.id.tvSelectEndDate);

        tvEnd.setOnClickListener(new View.OnClickListener() {
            DatePickerDialog endDatePickerDialog = new DatePickerDialog(POSelectorActivity.this, new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Calendar newDate = Calendar.getInstance();
                    newDate.set(year, monthOfYear, dayOfMonth);
                    tvSelectEndDate.setText(dateFormatter.format(newDate.getTime()));
                }

            },Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

            @Override
            public void onClick(View v) {
                endDatePickerDialog.show();
            }

        });
    }

    // display current date
    public void setCurrentDateOnView() {

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        tvSelectStartDate = (TextView) findViewById(R.id.tvSelectStartDate);

        // set current date into textview
        tvSelectStartDate.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));

        tvSelectEndDate = (TextView) findViewById(R.id.tvSelectEndDate);

        // set current date into textview
        tvSelectEndDate.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));

    }

    public void POSelectorConfirmation(List<PO> list) {
        POList = list;
        // To Do
    }
}
