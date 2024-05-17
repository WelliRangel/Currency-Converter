package br.com.welli.coinconverter.models;

import br.com.welli.coinconverter.calculator.Calculadora;
import br.com.welli.coinconverter.calculator.ConsultarAPI;
import com.google.gson.JsonArray;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class Menu {
private String menuInicial = ("""
                1) Lista de moedas e seus respectivos códigos
                2) Inserir moeda a ser convertida
                3) Sair
                """);
private String decorationLine = "*********************************";

    public void exibirOpcoes() throws IOException, InterruptedException {
        System.out.println(menuInicial);
        System.out.println(decorationLine);
        System.out.println("Bem vindo ao conversor de moedas!");
        System.out.println(menuInicial);
        Scanner opcao = new Scanner(System.in);
        System.out.print("Escolha uma opção: ");
        int opcaoEscolhida = opcao.nextInt();
        System.out.println(decorationLine);
        escolherOpcao(opcaoEscolhida);
    }

    public void escolherOpcao(int opcaoEscolhida) throws IOException, InterruptedException {
        Scanner opcao = new Scanner(System.in);
        Scanner input = new Scanner(System.in);
        while (opcaoEscolhida == 1 || opcaoEscolhida == 2) {
            switch (opcaoEscolhida) {
                case 1:
                    exibirCodigos();
                    System.out.println();
                case 2:
                    ConsultarAPI cotacao = new ConsultarAPI();
                    System.out.print("Insira a moeda de entrada: ");
                    String moedaEntrada = input.nextLine().toUpperCase();
                    System.out.print("Insira a quantia de " + moedaEntrada + " que deseja converter: ");
                    String quantiaString = input.nextLine();
                    System.out.print("Insira a moeda de saída: ");
                    String moedaSaida = input.nextLine().toUpperCase();
                    BigDecimal multiplicador = new BigDecimal(quantiaString);
                    Calculadora calculadora = new Calculadora();
                    String resultado = "A quantia de " + multiplicador + " " + moedaEntrada + " é referente a: -->  " + calculadora.converterMoeda(cotacao.taxaDeConversao(moedaEntrada, moedaSaida), multiplicador) + " " + moedaSaida + "  <--";

                    System.out.println(resultado);
                    System.out.println();
                    System.out.println(menuInicial);
                    System.out.print("Escolha uma opção: ");
                    opcaoEscolhida = opcao.nextInt();
            }
        }
    }

    public void exibirCodigos() throws IOException, InterruptedException {
        JsonArray codesJson = new ConsultarAPI().codigosMoedas();
        for (int i = 0; i < codesJson.size(); i++) {
            JsonArray codeArray = codesJson.get(i).getAsJsonArray();
            String code = codeArray.get(0).getAsString();
            String country = codeArray.get(1).getAsString();
            System.out.println(code + " - " + country);
        }
    }
}
