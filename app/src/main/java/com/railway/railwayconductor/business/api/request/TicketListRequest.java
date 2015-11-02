package com.railway.railwayconductor.business.api.request;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.RequestFuture;
import com.railway.railwayconductor.DI;
import com.railway.railwayconductor.business.api.API;
import com.railway.railwayconductor.business.api.entity.Ticket;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created by cteixeira on 02-11-2015.
 *
 */
public class TicketListRequest implements APIRequest{
    public final RequestFuture<JSONArray> future ;
    private final API api;
    JsonArrayRequest request;


    public TicketListRequest(){
        String url = "https://cmovtrainserver.herokuapp.com/ticket";
        future = RequestFuture.newFuture();
        request = new JsonArrayRequest(
                url,
                future,
                future
        );
        api = DI.get().provideRequestAPI();
    }
    @Override
    public Request getRequest()  {
        return request;
    }
    public List<Ticket> getResponse() throws ExecutionException, InterruptedException, TimeoutException, JSONException {
        api.request(this);
        JSONArray responseJson = future.get();
        List<Ticket> response = new ArrayList<Ticket>();
        for(int i = 0 ; i < responseJson.length() ; i++){
            response.add(new Ticket(responseJson.getJSONObject(i)));
        }
        return response;
    }
}
