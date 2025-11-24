package br.ufrn.maisgrana.model;

import java.time.LocalDate;

public class Transacao {
    private String descricao;
    private double valor;
    private TipoTransacao tipo;
    private String categoria;
    private LocalDate data;

    public Transacao(String descricao, double valor, TipoTransacao tipo, String categoria) {
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
        this.categoria = categoria;
        this.data = LocalDate.now();
    }

    // Getters
    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }
    public TipoTransacao getTipo() { return tipo; }
    
    @Override
    public String toString() {
        return "Transacao: " + descricao + " | R$ " + valor;
    }
}