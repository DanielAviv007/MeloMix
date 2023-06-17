package com.hit.model;

import com.hit.client.Client;
import com.hit.client.Request;
import com.hit.client.RequestGenerator;

public class RemoveFromPlaylist {
    public void removeFromPlaylist(String username, Long id) {
        String requestStr = String.format("{'username': '%s', 'songId': %d}", username, id);
        Request request = RequestGenerator.generateRequest("playlist/delete", requestStr);

        new Client().sendRequest(request);
    }
}
