package com.hit.model;

import com.hit.client.Client;
import com.hit.client.Request;
import com.hit.client.RequestGenerator;
import com.hit.client.Response;
import com.hit.dm.Song;

import java.util.List;

public class GetFilteredSongs extends SongGetter {

    public List<Song> getFilteredSongs(String category, String pattern) {
        String action = String.format("song/search/%s", category);
        String requestStr = String.format("{'pattern': '%s'}", pattern);

        Request request = RequestGenerator.generateRequest(action, requestStr);
        Response response = new Client().sendRequest(request);

        return extractSongs(response.getJsonData().get("songs"));
    }
}
