package br.com.welli.coinconverter.calculator;

import java.math.BigDecimal;

public class Calculadora {
    public BigDecimal converterMoeda(BigDecimal multiplicador, BigDecimal valorBase) {
        return multiplicador.multiply(valorBase);
    }
}
