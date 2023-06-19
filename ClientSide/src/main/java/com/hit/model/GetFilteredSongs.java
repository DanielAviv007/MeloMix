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

        System.out.println("Action : " + action);
        System.out.println("Pattern : " + pattern);
                                                // song/search/artist
//     "song/search/title", "song/search/lyrics", "song/search/artist"

        Request request = RequestGenerator.generateRequest(action, requestStr);
        Response response = new Client().sendRequest(request);

        System.out.println("response : " + response.getJsonData());

        return extractSongs(response.getJsonData().get("songs"));
    }
}
