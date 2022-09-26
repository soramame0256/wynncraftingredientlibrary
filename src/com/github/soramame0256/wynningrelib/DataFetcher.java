package com.github.soramame0256.wynningrelib;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class DataFetcher {
    public static JsonObject getJsonFromSpecificURL(String url) throws IOException {
        URL url1 = new URL(url);
        InputStream strm = url1.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(strm));
        StringBuilder sb = new StringBuilder();
        while(true){
            String lin = br.readLine();
            if(lin == null) break;
            sb.append(lin);
        }
        return JsonParser.parseString(sb.toString()).getAsJsonObject();

    }
}
