package br.com.sistema.eventos.view;

import br.com.sistema.eventos.controller.EventController;
import br.com.sistema.eventos.model.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EventController controller = new EventController();
        controller.carregar(); // Carrega dados ao abrir

        // Formato brasileiro de data/hora
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("=== CADASTRO OBRIGATÓRIO DE USUÁRIO ===");
        System.out.print("Nome: "); String nomeU = sc.nextLine();
        System.out.print("Email: "); String emailU = sc.nextLine();
        System.out.print("Cidade: "); String cidadeU = sc.nextLine();
        Usuario usuarioAtivo = new Usuario(nomeU, emailU, cidadeU);

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- SISTEMA DE EVENTOS (" + usuarioAtivo.getNome() + ") ---");
            System.out.println("1. Cadastrar Evento (Festas, Esportes, Shows, Culturais)");
            System.out.println("2. Consultar Todos os Eventos (Ordenados por data)");
            System.out.println("3. Confirmar Presença em Evento");
            System.out.println("4. Ver Meus Eventos / Cancelar Participação");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome do Evento: "); String nome = sc.nextLine();
                    System.out.print("Endereço: "); String end = sc.nextLine();
                    System.out.print("Categoria (Festas/Esportes/Shows/Culturais): "); String cat = sc.nextLine();
                    System.out.print("Horário (dd/MM/yyyy HH:mm): ");
                    LocalDateTime data = LocalDateTime.parse(sc.nextLine(), formato);
                    System.out.print("Descrição: "); String desc = sc.nextLine();

                    controller.adicionar(new Evento(nome, end, cat, data, desc));
                }
                case 2 -> {
                    System.out.println("\n--- LISTA GERAL ---");
                    controller.getListaGeral().forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("Nome do evento que deseja participar: ");
                    // O .trim() remove espaços acidentais que você possa digitar
                    String busca = sc.nextLine().trim();

                    controller.getListaGeral().stream()
                            .filter(e -> e.getNome().trim().equalsIgnoreCase(busca)) // Compara limpando os dois lados
                            .findFirst()
                            .ifPresentOrElse(
                                    e -> {
                                        usuarioAtivo.confirmarPresenca(e);
                                        System.out.println("✅ Presença confirmada em: " + e.getNome());
                                    },
                                    () -> System.out.println("❌ Erro: Evento '" + busca + "' não foi encontrado na lista.")
                            );
                }
                case 4 -> {
                    System.out.println("\n--- SUA AGENDA ---");
                    usuarioAtivo.getEventosConfirmados().forEach(System.out::println);
                    System.out.print("\nDeseja cancelar algum? Digite o nome (ou 'N'): ");
                    String cancel = sc.nextLine();
                    if (!cancel.equalsIgnoreCase("N")) {
                        usuarioAtivo.cancelarPresenca(cancel);
                        System.out.println("Participação removida.");
                    }
                }
            }
        }
        System.out.println("Sistema encerrado. Dados salvos em events.data.");
    }
}