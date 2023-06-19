package com.hit.model;

import com.hit.client.Client;
import com.hit.client.Request;
import com.hit.client.RequestGenerator;

public class AddNewSong {
    public void addNewSong(String title, String artistName, String lyrics) {
        String requestStr = String.format("{\"title\":\"%s\",\"artistName\":\"%s\",\"lyrics\":\"%s\"}", title, artistName, lyrics);
        Request request = RequestGenerator.generateRequest("song/add", requestStr);

        new Client().sendRequest(request);
    }
}
