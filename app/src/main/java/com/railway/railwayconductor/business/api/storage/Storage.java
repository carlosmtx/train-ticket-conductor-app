package com.railway.railwayconductor.business.api.storage;


import com.railway.railwayconductor.business.api.entity.Inspector;
import com.railway.railwayconductor.business.api.entity.Railway;
import com.railway.railwayconductor.business.api.entity.Ticket;
import com.railway.railwayconductor.business.api.entity.User;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by cteixeira on 21-10-2015.
 * com.railway.railway.business.api.storage
 */
public interface Storage {

    String getToken();
    void setToken(String token);

    Railway getSchedule();
    void setSchedule(Railway schedule);

    Inspector getInspector();
    void setUser(Inspector ins);

    void cacheResult(String call, JSONObject response);
    JSONObject getCachedResult(String call);


    List<Ticket> getTickets();

    Storage setTickets(List<Ticket> val);
}
