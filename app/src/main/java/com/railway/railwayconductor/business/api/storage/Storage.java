package com.railway.railwayconductor.business.api.storage;


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

    User getUser();
    void setUser(User user);

    void cacheResult(String call, JSONObject response);
    JSONObject getCachedResult(String call);


    List<Ticket> getTickets();

    Storage setTickets(List<Ticket> val);
}
