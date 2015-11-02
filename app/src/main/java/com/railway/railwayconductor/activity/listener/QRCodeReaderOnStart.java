package com.railway.railwayconductor.activity.listener;

import android.content.Context;
import android.os.AsyncTask;

import com.railway.railwayconductor.DI;
import com.railway.railwayconductor.business.api.entity.Ticket;
import com.railway.railwayconductor.business.api.entity.User;
import com.railway.railwayconductor.business.api.request.TicketListRequest;

import org.json.JSONException;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created by cteixeira on 02-11-2015.
 *
 */
public class QRCodeReaderOnStart extends AsyncTask<Void, Void, Void> {


    @Override
    protected Void doInBackground(Void... voids) {
        TicketListRequest request = new TicketListRequest();
        List<Ticket> tickets;
        try {
            tickets = request.getResponse();
            DI.get().provideStorage().setTickets(tickets);
        } catch (ExecutionException | InterruptedException | JSONException | TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }
}
