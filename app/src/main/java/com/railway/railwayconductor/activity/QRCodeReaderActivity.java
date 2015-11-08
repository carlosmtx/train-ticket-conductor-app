package com.railway.railwayconductor.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.railway.railwayconductor.R;
import com.railway.railwayconductor.activity.listener.QRCodeReaderOnStart;
import com.railway.railwayconductor.activity.listener.QRCodeReaderOnVerifyClick;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;


public class QRCodeReaderActivity extends MenuActivity {
    // Esta viagem tem 2 bilhetes
    public String departure = "Station A";
    public String arrival = "Station B";
    public String timestamp = "1422820800000";

    public PieChart chart;
    private int totalTickets;
    private int usedTickets;

    TextView infoTrip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_reader);

        this.arrival = getIntent().getStringExtra("arrival");
        this.departure = getIntent().getStringExtra("departure");
        this.timestamp = getIntent().getStringExtra("departureTime");

        this.chart = initializeChart();
        this.infoTrip = (TextView) findViewById(R.id.result);
        this.infoTrip.setText(departure + " to " + arrival + " on " + new Timestamp(Long.parseLong(timestamp)).toString());

        new QRCodeReaderOnStart(this).execute();
        findViewById(R.id.qrcodereader_verify_button).setOnClickListener(new QRCodeReaderOnVerifyClick());
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {
            infoTrip.setText(scanResult.getContents());
            refreshChartData(true);

            // TODO: Comparar SHA1 do bilhete com signature desencriptada
        }
    }

    public PieChart initializeChart(){
        PieChart chart = (PieChart) findViewById(R.id.chart);
        chart.notifyDataSetChanged();
        chart.invalidate();
        return chart;
    }

    public void refreshChartData(boolean subtractOne){
        if(subtractOne){
            this.totalTickets--;
            this.usedTickets++;
        }

        PieDataSet dataSet = new PieDataSet(
                new ArrayList<>(Arrays.asList(
                        new Entry(totalTickets,0),
                        new Entry(usedTickets,1)
                )),
                "Tickets"
        );

        dataSet.setSliceSpace(2f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(Arrays.asList(Color.rgb(136, 170, 255), Color.rgb(239, 155, 15)));

        this.chart.setData(new PieData(
                new ArrayList<>(Arrays.asList("Total", "Validated")),
                dataSet
        ));
        this.chart.notifyDataSetChanged();
    }


    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public void setUsedTickets(int usedTickets) {
        this.usedTickets = usedTickets;
    }



}
