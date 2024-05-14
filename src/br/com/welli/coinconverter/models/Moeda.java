package br.com.welli.coinconverter.models;

public class Moeda {
    private String nome;
    private String valor;

    public Moeda(MoedaRecord moeda) {
        this.nome = moeda.base_code();
        this.valor = moeda.BRL();
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValor() {
        return this.valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "nome='" + nome + '\'' +
                ", valor=" + valor;
    }
}
