package com.hit.server;

import com.hit.config.Config;

public class ServerDriver {
    public static void main(String[] args) {
        Server server = new Server(Config.getInstance().getServerPortNumber(), Config.getInstance().getThreadPoolSize());

        new Thread(server).start();
    }
}
