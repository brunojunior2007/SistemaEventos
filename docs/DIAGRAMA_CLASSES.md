# 📊 Diagrama de Classes Estrutural

Este diagrama representa a arquitetura estática do sistema, destacando o encapsulamento e a hierarquia de pacotes.

```mermaid
classDiagram
    direction TB

    namespace br.com.sistema.eventos.model {
        class Evento {
            - String nome
            - String endereco
            - String categoria
            - String descricao
            - LocalDateTime horario
            + getStatus() String
            + toString() String
        }

        class Usuario {
            - String nome
            - String email
            - String cidade
            - List~Evento~ eventosConfirmados
            + confirmarPresenca(Evento e) void
            + cancelarPresenca(String nomeEvento) void
        }
    }

    namespace br.com.sistema.eventos.controller {
        class EventController {
            - List~Evento~ listaGeral
            - final String ARQUIVO
            + salvar() void
            + carregar() void
            + adicionar(Evento e) void
            + getListaGeral() List~Evento~
        }
    }

    namespace br.com.sistema.eventos.view {
        class Main {
            + main(String[] args)$
        }
    }

    %% Relacionamentos Semânticos
    Usuario "1" o-- "*" Evento : Agregação (O usuário possui uma lista de interesses)
    EventController "1" --> "*" Evento : Gerencia (CRUD e Persistência)
    Main ..> EventController : Dependência (Invoca lógica de negócio)
    Main ..> Usuario : Dependência (Instancia o ator do sistema)
    EventController ..> Evento : Manipula (Ordenação e I/O)