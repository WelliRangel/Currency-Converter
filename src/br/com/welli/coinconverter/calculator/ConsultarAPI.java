package br.com.welli.coinconverter.calculator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultarAPI {
    private static final String API_KEY = "212750b449d8f094455a9a08";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY;

    public BigDecimal taxaDeConversao(String moedaEntrada, String moedaSaida) throws IOException, InterruptedException {
        String url = BASE_URL + "/pair/" + moedaEntrada + "/" + moedaSaida;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        Gson gson = new Gson();
        JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);
        return jsonResponse.get("conversion_rate").getAsBigDecimal();
    }

    public JsonArray codigosMoedas() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/codes/"))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        Gson gson = new Gson();
        var json = response.body();
        var jsonObject = gson.fromJson(json, JsonObject.class);
        return jsonObject.get("supported_codes").getAsJsonArray();
    }
}

