package br.ufrn.maisgrana.service;

import br.ufrn.maisgrana.model.TipoTransacao;
import br.ufrn.maisgrana.model.Transacao;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TransacaoService {

    private static final String ARQUIVO_DADOS = "transacoes.json";
    
    // Configuração Especial para o Java aceitar o LocalDate
    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {
                @Override
                public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
                    return new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE));
                }
            })
            .registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
                @Override
                public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    return LocalDate.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE);
                }
            })
            .setPrettyPrinting() // Deixa o JSON bonitinho no arquivo
            .create();

    // Salva uma nova transação no final da lista
    public void salvar(Transacao novaTransacao) {
        List<Transacao> listaAtual = listar();
        listaAtual.add(novaTransacao);
        
        try (Writer writer = new FileWriter(ARQUIVO_DADOS)) {
            gson.toJson(listaAtual, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Lê o arquivo JSON e devolve a lista
    public List<Transacao> listar() {
        try {
            if (!Files.exists(Paths.get(ARQUIVO_DADOS))) {
                return new ArrayList<>();
            }
            Reader reader = new FileReader(ARQUIVO_DADOS);
            Type listType = new TypeToken<ArrayList<Transacao>>(){}.getType();
            List<Transacao> lista = gson.fromJson(reader, listType);
            
            if(lista == null) return new ArrayList<>();
            return lista;
            
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    // Calcula o Balanço
    public double calcularBalanco() {
        List<Transacao> lista = listar();
        double saldo = 0;
        for (Transacao t : lista) {
            if (t.getTipo() == TipoTransacao.RECEITA) {
                saldo += t.getValor();
            } else {
                saldo -= t.getValor();
            }
        }
        return saldo;
    }
}