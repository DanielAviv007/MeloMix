package com.hit.client;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class RequestGenerator {
    public static Request generateRequest(String action, String jData) {
        return new Request(action, new Gson().fromJson(jData, JsonObject.class));
    }
}
