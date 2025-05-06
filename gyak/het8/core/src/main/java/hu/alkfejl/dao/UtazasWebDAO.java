package hu.alkfejl.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import hu.alkfejl.model.Utazas;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class UtazasWebDAO implements UtazasDAO {
    private final String baseUrl;
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    public UtazasWebDAO(String url) {
        baseUrl = url;
    }

    @Override
    public boolean add(Utazas utazas) {
        boolean result = false;

        String URL = baseUrl + "/api/add";
        Gson gson = new Gson();
        String json = gson.toJson(utazas);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            result = (response.statusCode() == 201);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public List<Utazas> find(Utazas utazas) {
        List<Utazas> results = new ArrayList<>();

        StringBuilder URL = new StringBuilder(baseUrl + "/api/find?q=1");

        if ( utazas.getNev() != null ) {
            URL.append("&nev=");
            URL.append(URLEncoder.encode(utazas.getNev(), StandardCharsets.UTF_8));
        }

        if ( utazas.getUticel() != null ) {
            URL.append("&uticel=");
            URL.append(URLEncoder.encode(utazas.getUticel(), StandardCharsets.UTF_8));
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL.toString()))
                .build();

        HttpResponse<String> response;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                Gson gson = new Gson();
                results = gson.fromJson(response.body(), new TypeToken<List<Utazas>>() {} );
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return results;
    }
}
