package br.com.welli.coinconverter.main;

import br.com.welli.coinconverter.models.Menu;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Menu menu = new Menu();
        System.out.println(menu.exibirOpcoes());
    }
}


