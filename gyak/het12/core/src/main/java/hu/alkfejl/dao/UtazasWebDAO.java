package hu.alkfejl.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import hu.alkfejl.model.Utazas;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class UtazasWebDAO implements UtazasDAO {
    private final String baseUrl;
    private static final HttpClient httpClient;

    private static final CookieManager cookieManager = new CookieManager();

    static{
        CookieHandler.setDefault(cookieManager);
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        httpClient = HttpClient.newBuilder().cookieHandler(cookieManager).build();
    }
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
            result = response.statusCode() == 201;
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

    @Override
    public boolean delete(Utazas utazas) {
        boolean result = false;
        String URL = baseUrl + "/api/delete/" + URLEncoder.encode(utazas.getNev(), StandardCharsets.UTF_8);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .DELETE()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            result = response.statusCode() == 204; // Assuming 204 No Content indicates successful deletion
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public boolean update(Utazas utazas) {
        boolean result = false;
        String URL = baseUrl + "/api/update";
        Gson gson = new Gson();
        String json = gson.toJson(utazas);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            result = response.statusCode() == 200; // Assuming 200 OK indicates successful update
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public void preferences(Utazas u) {
        String URL = baseUrl + "/api/preference";
        Gson gson = new Gson();
        String json = gson.toJson(u);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            CookieStore store = cookieManager.getCookieStore();
            for(var cookie : store.getCookies()){
                System.out.println(cookie.getName() + " " + cookie.getValue());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
