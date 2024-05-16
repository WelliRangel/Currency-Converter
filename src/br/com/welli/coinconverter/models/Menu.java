package br.com.welli.coinconverter.models;

import br.com.welli.coinconverter.calculator.Calculadora;
import br.com.welli.coinconverter.calculator.ConsultarCotacao;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Menu {
    private static final String API_KEY = "212750b449d8f094455a9a08";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/codes/";

    public String exibirOpcoes() throws IOException, InterruptedException {
        System.out.println("""
                Bem vindo ao conversor de moedas!
                1) Lista de moedas e seus respectivos códigos
                2) Inserir moeda a ser convertida
                """);
        Scanner opcao = new Scanner(System.in);
        Scanner input = new Scanner(System.in);
        System.out.print("Escolha uma opção: ");
        int opcaoEscolhida = opcao.nextInt();

        String resultado = null;
        while (opcaoEscolhida == 1 || opcaoEscolhida == 2) {
            switch (opcaoEscolhida) {
                case 1:
                    exibirCodigos();
                    opcaoEscolhida = opcao.nextInt();
                    break;
                case 2:
                    ConsultarCotacao cotacao = new ConsultarCotacao();
                    System.out.print("Insira a moeda de entrada: ");
                    String moedaEntrada = input.nextLine();
                    System.out.print("Insira a quantia de " + moedaEntrada + " que deseja converter: ");
                    String quantiaString = input.nextLine();
                    System.out.print("Insira a moeda de saída: ");
                    String moedaSaida = input.nextLine();
                    BigDecimal multiplicador = new BigDecimal(quantiaString);
                    Calculadora calculadora = new Calculadora();
                    resultado = calculadora.converterMoeda(cotacao.buscarValor(moedaEntrada, moedaSaida), multiplicador).toString();
                    return resultado;
            }
        }
        return resultado;
    }

    public void exibirCodigos() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());


        Gson gson = new Gson();
        String json = response.body();
        System.out.println(json);
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        JsonArray codesJson = jsonObject.get("supported_codes").getAsJsonArray();
        for (int i = 0; i < codesJson.size(); i++) {
            JsonArray codeArray = codesJson.get(i).getAsJsonArray();
            String code = codeArray.get(0).getAsString();
            String country = codeArray.get(1).getAsString();
            System.out.println(code + " - " + country);
        }
    }
}
