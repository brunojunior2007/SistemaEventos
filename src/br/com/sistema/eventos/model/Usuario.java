package br.com.sistema.eventos.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa o usuário do sistema.
 * Demonstra COMPOSIÇÃO: Um usuário possui uma lista de eventos.
 */
public class Usuario {
    private String nome, email, cidade;
    // Lista de eventos que o usuário confirmou presença
    private List<Evento> eventosConfirmados;

    public Usuario(String nome, String email, String cidade) {
        this.nome = nome;
        this.email = email;
        this.cidade = cidade;
        this.eventosConfirmados = new ArrayList<>();
    }

    // Métodos de Negócio: Gerenciam a participação em eventos
    public void confirmarPresenca(Evento e) {
        if (!eventosConfirmados.contains(e)) {
            eventosConfirmados.add(e);
        }
    }

    public void cancelarPresenca(String nomeEvento) {
        // Remove da lista se o nome do evento coincidir (ignora maiúsculas/minúsculas)
        eventosConfirmados.removeIf(e -> e.getNome().equalsIgnoreCase(nomeEvento));
    }

    public List<Evento> getEventosConfirmados() { return eventosConfirmados; }
    public String getNome() { return nome; }
}