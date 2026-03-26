package br.com.sistema.eventos.model;

import java.time.LocalDateTime; // API moderna de data e hora
import java.time.temporal.ChronoUnit; // Para calcular diferença de tempo

/**
 * A classe Evento representa a entidade de dados central do sistema.
 * Implementa o pilar de ENCAPSULAMENTO da POO.
 */
public class Evento {
    // Atributos privados: Acesso restrito apenas via métodos públicos
    private String nome, endereco, categoria, descricao;
    private LocalDateTime horario;

    public Evento(String nome, String endereco, String categoria, LocalDateTime horario, String descricao) {
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.horario = horario;
        this.descricao = descricao;
    }

    /**
     * Lógica de Status (Exigência do Slide):
     * Compara o horário do evento com o horário atual (LocalDateTime.now())
     */
    public String getStatus() {
        LocalDateTime agora = LocalDateTime.now();

        // Se a data do evento já passou
        if (horario.isBefore(agora)) {
            // Se passou menos de 3 horas do início, consideramos que ainda está "ocorrendo"
            if (ChronoUnit.HOURS.between(horario, agora) < 3) {
                return "OCORRENDO AGORA";
            }
            return "ENCERRADO";
        }
        return "FUTURO (Agendado)";
    }

    // Getters: Permitem que outras classes leiam os dados sem alterá-los diretamente
    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public String getCategoria() { return categoria; }
    public LocalDateTime getHorario() { return horario; }
    public String getDescricao() { return descricao; }

    @Override
    public String toString() {
        return String.format("[%s] %s | Local: %s | Categoria: %s | Data: %s",
                getStatus(), nome, endereco, categoria, horario);
    }
}