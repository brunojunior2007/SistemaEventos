package br.com.sistema.eventos.controller;

import br.com.sistema.eventos.model.Evento;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * O Controlador gerencia a lógica de persistência (salvamento)
 * e a manipulação da lista global de eventos.
 */
public class EventController {
    private List<Evento> listaGeral = new ArrayList<>();
    private final String ARQUIVO = "events.data"; // Nome solicitado no slide

    /**
     * Grava os dados em formato de TEXTO (CSV) para ser legível por humanos.
     * Usa PrintWriter para escrever linha por linha.
     */
    public void salvar() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO))) {
            for (Evento e : listaGeral) {
                // Salva os atributos separados por ponto e vírgula
                writer.println(e.getNome() + ";" + e.getEndereco() + ";" +
                        e.getCategoria() + ";" + e.getHorario() + ";" + e.getDescricao());
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo de texto: " + e.getMessage());
        }
    }

    /**
     * Carrega os dados do arquivo assim que o programa inicia.
     * Faz o "parse" (conversão) do texto de volta para objetos Java.
     */
    public void carregar() {
        File f = new File(ARQUIVO);
        if (!f.exists()) return;

        try (Scanner reader = new Scanner(f)) {
            listaGeral.clear();
            while (reader.hasNextLine()) {
                String linha = reader.nextLine();
                String[] dados = linha.split(";");
                // Reconstrói o objeto Evento com os dados da linha
                Evento e = new Evento(dados[0], dados[1], dados[2],
                        LocalDateTime.parse(dados[3]), dados[4]);
                listaGeral.add(e);
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar dados: " + e.getMessage());
        }
    }

    public void adicionar(Evento e) {
        listaGeral.add(e);
        // ORDENAÇÃO: Mantém a lista sempre em ordem cronológica (mais próximos primeiro)
        listaGeral.sort(Comparator.comparing(Evento::getHorario));
        salvar(); // Persistência imediata
    }

    public List<Evento> getListaGeral() { return listaGeral; }
}