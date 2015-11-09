package com.railway.railwayconductor.business.api.storage;

import com.railway.railwayconductor.business.api.entity.Inspector;
import com.railway.railwayconductor.business.api.entity.Railway;
import com.railway.railwayconductor.business.api.entity.Ticket;
import com.railway.railwayconductor.business.api.entity.User;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by cteixeira on 21-10-2015.
 * com.railway.railway.business.api.storage
 */
public class RailwayStorage implements Storage {
    private HashMap<String,String> storage;
    private HashMap<String,JSONObject> responseStorage;
    private List<Ticket> tickets;
    private Railway schedule;
    private Inspector inspector;
    private String publicKey;
    private ArrayList<String> validatedIDs;

    public RailwayStorage() {
        this.storage = new HashMap<>();
        this.responseStorage = new HashMap<>();
        this.tickets = new ArrayList<>();
        this.validatedIDs = new ArrayList<>();
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
    public Inspector getInspector() {
        return this.inspector;
    }

    @Override
    public void setUser(Inspector ins) {
        this.inspector = ins;
    }


    @Override
    public void cacheResult(String call, JSONObject response){
        responseStorage.put(call, response);
    }

    @Override
    public JSONObject getCachedResult(String call) {
        return responseStorage.get(call);
    }

    @Override
    public List<Ticket> getTickets(){
        return new ArrayList<>(this.tickets);
    }

    @Override
    public Storage setTickets(List<Ticket> val){
        this.tickets = new ArrayList<>(val);
        return this;
    }

    @Override
    public String getPublicKey() {
        return publicKey;
    }

    @Override
    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    @Override
    public void addValidatedTicketID(String id) throws AlreadyExists {
        for(String _id : this.validatedIDs){
            if (id.equals(_id))
                throw new AlreadyExists();
        }
        this.validatedIDs.add(id);
    }

    @Override
    public void resetValidatedTickets(){
        this.validatedIDs.clear();
    }

    @Override
    public ArrayList<String> getValidatedIDs(){
        return this.validatedIDs;
    }

}
