package com.hit.client;

import com.google.gson.JsonObject;

public class Request {
    public String action;
    public JsonObject jsonData;

    public Request(String action, JsonObject data) {
        this.action = action;
        this.jsonData = data;
    }

    public String getAction() {
        return action;
    }

    public JsonObject getData() {
        return jsonData;
    }
}
