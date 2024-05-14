package br.com.welli.coinconverter.calculator;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CotacaoMoedas {

    public JsonObject buscarValor(String moeda) throws IOException, InterruptedException {
        String endereco = "https://v6.exchangerate-api.com/v6/212750b449d8f094455a9a08/latest/" + moeda;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        JsonElement nome =  jsonObject.get("base_code");
        JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");
        JsonElement valor = conversionRates.get("BRL");
        JsonObject result = new JsonObject();
        result.add("base_code", nome);
        result.add("BRL", valor);

        return result;
    }
}


//public class br.com.welli.conversor.calculadora.CotacaoMoedas {
//
//    private static final String API_KEY = "212750b449d8f094455a9a08";
//    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";
//
//    public JsonObject buscarValor(String moeda) throws IOException, InterruptedException {
//        String url = BASE_URL + moeda;
//
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(url))
//                .build();
//
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//
//        JsonObject jsonResponse = new JsonParser().parseString(response.body()).getAsJsonObject();
//        String baseCode = jsonResponse.get("base_code").getAsString();
//        JsonObject conversionRates = jsonResponse.getAsJsonObject("conversion_rates");
//        double valorBRL = conversionRates.get("BRL").getAsDouble();
//
//        JsonObject cotacao = new JsonObject();
//        cotacao.addProperty("base_code", baseCode);
//        cotacao.addProperty("BRL", valorBRL);
//
//        return cotacao;
//    }
//}
