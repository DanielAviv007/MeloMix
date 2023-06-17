package com.hit.model;

import com.hit.client.Client;
import com.hit.client.Request;
import com.hit.client.RequestGenerator;
import com.hit.client.Response;
import com.hit.dm.Song;

import java.util.List;

public class GetAllPlaylist extends SongGetter {
    public List<Song> getPlaylist(String username) {
        String requestStr = String.format("{'username': '%s'}", username);
        Request request = RequestGenerator.generateRequest("playlist/get", requestStr);
        Response response = new Client().sendRequest(request);

        return extractSongs(response.getJsonData().get("songs"));
    }
}
