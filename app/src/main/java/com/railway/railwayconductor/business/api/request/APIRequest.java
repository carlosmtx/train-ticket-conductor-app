package com.railway.railwayconductor.business.api.request;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created by cteixeira on 17-10-2015.
 */
public interface APIRequest {
    com.android.volley.Request getRequest();
}
