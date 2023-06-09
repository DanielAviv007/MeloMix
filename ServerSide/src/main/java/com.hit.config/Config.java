package com.hit.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.hit.alg.KMP;
import com.hit.alg.SlidingWindow;
import com.hit.alg.StringMatcher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Config {
    private static final String DEFAULT_CONFIG_PATH = "src/main/resources/config.json";
    private static Config config;
    private JsonObject configObject;

    private Config() {
        try {
            loadConfig(DEFAULT_CONFIG_PATH);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Config getInstance() {
        if (config == null)
            config = new Config();

        return config;
    }

    public void loadConfig(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        StringBuilder jsonString = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null)
            jsonString.append(line);

        configObject = JsonParser.parseString(jsonString.toString()).getAsJsonObject();
    }

    private int getNumber(String key) {
        if (configObject != null && configObject.has(key)) {
            JsonElement value = configObject.get(key);

            if (value.isJsonPrimitive() && value.getAsJsonPrimitive().isNumber())
                return value.getAsInt();
        }

        return 0; // Default value if key not found or not a number
    }

    private String getString(String key) {
        if (configObject != null && configObject.has(key)) {
            JsonElement value = configObject.get(key);

            if (value.isJsonPrimitive() && value.getAsJsonPrimitive().isString())
                return value.getAsString();
        }

        return ""; // Default value if key not found or not a string
    }

    public String getUsersPath() {
        return getString("UsersPath");
    }

    public String getSongsPath() {
        return getString("SongsPath");
    }

    public int getServerPortNumber() {
        return getNumber("ServerPortNumber");
    }

    public int getThreadPoolSize() {
        return getNumber("ThreadPoolSize");
    }

    public StringMatcher getAlgoModule() {
        String algName = getString("AlgoModule");

        StringMatcher alg = switch (algName) {
            case "kmp" -> new KMP();
            case "slidingWindow" -> new SlidingWindow();
            default -> null;
        };

        return alg;
    }
}
