package com.hit.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {
    private boolean serverUp;
    private final int portNumber;
    private final ExecutorService pool;


    public Server(int portNumber, int threadPoolSize) {
        this.portNumber = portNumber;
        this.pool =  Executors.newFixedThreadPool(threadPoolSize);
        this.serverUp = false;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);

            this.serverUp = true;

            while (serverUp) {
                Socket clientSocket = serverSocket.accept();
                pool.execute(new HandleRequest(clientSocket));
            }

            pool.shutdown();
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void shutDownServer() {
        serverUp = false;
    }
}