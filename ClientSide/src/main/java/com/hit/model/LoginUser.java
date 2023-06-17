package com.hit.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hit.client.Client;
import com.hit.client.Request;
import com.hit.client.RequestGenerator;
import com.hit.client.Response;
import com.hit.dm.User;

import java.lang.reflect.Type;

public class LoginUser {
    public User loginUser(String username) {
        Gson gson = new Gson();
        Request request = RequestGenerator.generateRequest("user/login", String.format("{\"username\": %s}", username));

        Response response = new Client().sendRequest(request);

        Type userType = new TypeToken<User>(){}.getType();
        User user = gson.fromJson(response.getJsonData(), userType);

        return user;
    }
}
