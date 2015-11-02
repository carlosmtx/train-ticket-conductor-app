package com.railway.railwayconductor.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.railway.railwayconductor.R;
import com.railway.railwayconductor.activity.listener.QRCodeReaderOnStart;
import com.railway.railwayconductor.activity.listener.QRCodeReaderOnVerifyClick;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class QRCodeReaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_reader);
        new QRCodeReaderOnStart().execute();
        PieChart chart = (PieChart) findViewById(R.id.chart);
        PieDataSet dataSet = new PieDataSet(
                new ArrayList<>(Arrays.asList(
                        new Entry(90f,0),
                        new Entry(10f,1)
                )),
                "Bilhetes"
        );

        dataSet.setSliceSpace(2f);
        dataSet.setSelectionShift(5f);

        dataSet.setColors(Arrays.asList(Color.rgb(136,170,255),Color.rgb(239,155,15)));
        PieData data = new PieData(
                new ArrayList<>(Arrays.asList("", "")),
                dataSet
        );

        chart.setData(data);
        chart.notifyDataSetChanged();
        chart.invalidate();
        findViewById(R.id.qrcodereader_verify_button).setOnClickListener(new QRCodeReaderOnVerifyClick());
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {
            TextView view = (TextView)this.findViewById(R.id.result);
            view.setText(scanResult.getContents());
            view.invalidate();
        }
    }



}
