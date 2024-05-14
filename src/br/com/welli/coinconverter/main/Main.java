package br.com.welli.coinconverter.main;

import br.com.welli.coinconverter.calculator.CotacaoMoedas;
import br.com.welli.coinconverter.models.Moeda;
import br.com.welli.coinconverter.models.MoedaRecord;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Gson gson = new Gson();
        CotacaoMoedas cotacao = new CotacaoMoedas();
        Scanner input = new Scanner(System.in);
        String nomeMoeda = input.nextLine();
        String json = String.valueOf(cotacao.buscarValor(nomeMoeda));
        MoedaRecord moedaRecord = gson.fromJson(json, MoedaRecord.class);
        Moeda moeda = new Moeda(moedaRecord);

        System.out.println(moeda);
    }
}