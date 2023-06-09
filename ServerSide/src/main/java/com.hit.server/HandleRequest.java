package com.hit.server;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hit.controller.PlaylistController;
import com.hit.controller.SongController;
import com.hit.controller.UserController;
import com.hit.dm.Song;
import com.hit.dm.User;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HandleRequest implements Runnable {
//    request
    private final Socket clientSocket;

//    controllers
    private final PlaylistController playlistController = new PlaylistController();
    private final SongController songController = new SongController();
    private final UserController userController = new UserController();

//    reader & writer
    private final Scanner reader;
    private final PrintWriter writer;
//    gson
    private final Gson gson;

    public HandleRequest(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.reader = new Scanner(new InputStreamReader(clientSocket.getInputStream()));
        this.writer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        this.gson = new Gson();
    }

    @Override
    public void run() {
        try {
            Request request = gson.fromJson(reader.nextLine(), Request.class);
            Response response = new Response(false, "", null);
            JsonObject json = request.getData();

            switch (request.getAction()) {
                case "user/login" -> {
//                template: {"username": "user"}
                    String username = json.get("username").getAsString();

                    if (username != null) {
                        User user = userController.login(username);
                        response = new Response(true, request.getAction(), null);
                    }
                }
                case "song/add" -> {
//                template: {"title": ., "artistName": ., "lyrics": .}
                    String title = json.get("title").getAsString();
                    String artistName = json.get("artistName").getAsString();
                    String lyrics = json.get("lyrics").getAsString();
                    if (title != null && artistName != null && lyrics != null) {
                        Song song = new Song(title, artistName, lyrics);

                        songController.addNewSong(song);
                        response = new Response(true, request.getAction(), null);
                    }
                }
                case "song/get" -> {
//                template: null
                    List<Song> allSongs = songController.getAllSongs();
                    JsonArray songs = gson.toJsonTree(allSongs).getAsJsonArray();
                    JsonObject jsonResponse = new JsonObject();
                    jsonResponse.add("songs", songs);
                    response = new Response(true, request.getAction(), jsonResponse);
                }
                case "song/search/title", "song/search/lyrics", "song/search/artist" -> {
//                template: {"pattern": "PATTERN"}
                    String pattern = json.get("pattern").getAsString();
                    if (pattern != null) {
                        List<Song> matchedByTitle = songController.searchSongsByTitle(pattern);

                        JsonArray songs = gson.toJsonTree(matchedByTitle).getAsJsonArray();
                        JsonObject jsonResponse = new JsonObject();
                        jsonResponse.add("songs", songs);

                        response = new Response(true, request.getAction(), jsonResponse);
                    }
                }
//                TODO: fix playlist methods!!
                case "playlist/add" -> {
//                template: {"username": "USERNAME", "songId": 1L}
                    String username = json.get("username").getAsString();
                    Long songId = json.get("songId").getAsLong();

                    if (username != null)
                        response = new Response(playlistController.addSongToPlaylist(songId, username), request.getAction(), null);
                }
                case "playlist/delete" -> {
//                template: {"username": "USERNAME", "songId": 1L}
                    String username = json.get("username").getAsString();
                    Long songId = json.get("songId").getAsLong();

                    if (username != null)
                        response = new Response(playlistController.removeSongFromPlaylist(songId, username), request.getAction(), null);
                }
                case "playlist/get" -> {
//                template: {"username": "USERNAME"}
                    String username = json.get("username").getAsString();

                    if (username != null) {
                        User user = userController.login(username);

                        if (user != null) {
                            List<Long> userPlaylist = new ArrayList<>(user.getPlaylist());

                            List<Song> userSongs = playlistController.getUserPlaylist(userPlaylist);

                            JsonArray songs = gson.toJsonTree(userSongs).getAsJsonArray();
                            JsonObject jsonResponse = new JsonObject();
                            jsonResponse.add("songs", songs);

                            response = new Response(true, request.getAction(), jsonResponse);
                        }
                    }
                }
            }

            //            send the request back to the client
            writer.println(gson.toJson(response));
            writer.flush();

            reader.close();
            writer.close();

            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
