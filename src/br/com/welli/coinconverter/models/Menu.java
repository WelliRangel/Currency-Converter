package br.com.welli.coinconverter.models;

import br.com.welli.coinconverter.calculator.Calculadora;
import br.com.welli.coinconverter.calculator.ConsultarAPI;
import com.google.gson.JsonArray;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class Menu {

    public void exibirOpcoes() throws IOException, InterruptedException {
        String menuInicial = ("""
                1) Lista de moedas e seus respectivos códigos
                2) Inserir moeda a ser convertida
                3) Sair
                """);
        System.out.println("*********************************");
        System.out.println("Bem vindo ao conversor de moedas!");
        System.out.println(menuInicial);
        Scanner opcao = new Scanner(System.in);
        Scanner input = new Scanner(System.in);
        System.out.print("Escolha uma opção: ");
        int opcaoEscolhida = opcao.nextInt();
        System.out.println("*********************************");

        while (opcaoEscolhida == 1 || opcaoEscolhida == 2) {
            switch (opcaoEscolhida) {
                case 1:
                    exibirCodigos();
                    System.out.println();
                case 2:
                    ConsultarAPI cotacao = new ConsultarAPI();
                    System.out.print("Insira a moeda de entrada: ");
                    String moedaEntrada = input.nextLine();
                    System.out.print("Insira a quantia de " + moedaEntrada + " que deseja converter: ");
                    String quantiaString = input.nextLine();
                    System.out.print("Insira a moeda de saída: ");
                    String moedaSaida = input.nextLine();
                    BigDecimal multiplicador = new BigDecimal(quantiaString);
                    Calculadora calculadora = new Calculadora();
                    String resultado = "A quantia de " + quantiaString + " " + moedaEntrada.toUpperCase() + " é referente a: -->  " + calculadora.converterMoeda(cotacao.taxaDeConversao(moedaEntrada, moedaSaida), multiplicador).toString() + " " + moedaSaida.toUpperCase() + "<--";

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
