package com.hit.client;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 12345;
    private static final int TIMEOUT = 5_000; // 5s timeout

    public Response sendRequest(Request request) {
        Response response = new Response(false, "", null);
        try {
            Socket server = new Socket(SERVER_HOST, SERVER_PORT);

            server.setSoTimeout(TIMEOUT);

            Gson gson = new Gson();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(server.getOutputStream()));
            Scanner reader = new Scanner(new InputStreamReader(server.getInputStream()));

            // Send request to the server
            writer.println(gson.toJson(request));
            writer.flush();

            // Receive response from the server
            response = gson.fromJson(reader.nextLine(), Response.class);

            reader.close();
            writer.close();

            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
