package com.railway.railwayconductor.activity.listener;

import android.content.Context;
import android.os.AsyncTask;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.railway.railwayconductor.DI;
import com.railway.railwayconductor.activity.QRCodeReaderActivity;
import com.railway.railwayconductor.business.api.entity.Ticket;
import com.railway.railwayconductor.business.api.entity.User;
import com.railway.railwayconductor.business.api.request.TicketListRequest;
import com.railway.railwayconductor.business.api.request.TicketListRequestData;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created by cteixeira on 02-11-2015.
 *
 */
public class QRCodeReaderOnStart extends AsyncTask<Void, Void, Void> {



    public QRCodeReaderActivity activity;

    public QRCodeReaderOnStart(QRCodeReaderActivity act){
        this.activity = act;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        TicketListRequest request = null;
        final int noTickets;

        try {
            request = new TicketListRequest(new TicketListRequestData(activity.departure, activity.arrival,activity.timestamp));
            noTickets = request.getResponse();
            activity.setUsedTickets(0);
            activity.setTotalTickets(noTickets);
            activity.refreshChartData(false);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
