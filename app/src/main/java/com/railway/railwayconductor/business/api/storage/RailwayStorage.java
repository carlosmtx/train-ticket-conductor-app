package com.railway.railwayconductor.business.api.storage;

import com.railway.railwayconductor.business.api.entity.Railway;
import com.railway.railwayconductor.business.api.entity.User;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by cteixeira on 21-10-2015.
 * com.railway.railway.business.api.storage
 */
public class RailwayStorage implements Storage {
    private HashMap<String,String> storage;
    private HashMap<String,JSONObject> responseStorage;

    private Railway schedule;
    private User user;

    public RailwayStorage() {
        this.storage = new HashMap<>();
        this.responseStorage = new HashMap<>();
    }

    @Override
    public String getToken() {
        return storage.get("token");
    }

    @Override
    public void setToken(String token) {
        storage.put("token",token);
    }

    @Override
    public Railway getSchedule() {
        return this.schedule;
    }

    @Override
    public void setSchedule(Railway schedule) {
        this.schedule = schedule;
    }

    @Override
    public User getUser() {
        return this.user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void cacheResult(String call, JSONObject response){
        responseStorage.put(call, response);
    }

    @Override
    public JSONObject getCachedResult(String call) {
        return responseStorage.get(call);
    }


}
