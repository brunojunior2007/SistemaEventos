# 🏗️ Documentação de Arquitetura (Padrão MVC)

O sistema foi estruturado utilizando o padrão **Model-View-Controller (MVC)**, que é o alicerce para o desenvolvimento de softwares profissionais e escaláveis.

## 1. Model (Modelo)
**Classes:** `Evento.java`, `Usuario.java`
* **Responsabilidade:** Representar o estado e os dados do negócio.
* **Lógica Interna:** Aqui reside a inteligência dos dados. Por exemplo, a classe `Evento` é responsável por calcular seu próprio status (se está ocorrendo, se é futuro ou passado) através do método `getStatus()`.
* **Regra de Ouro:** O Model nunca sabe que existe uma tela ou um console; ele apenas processa informações.

## 2. View (Visão)
**Classe:** `Main.java`
* **Responsabilidade:** Interface com o usuário (I/O).
* **Papel no Fluxo:** Captura as entradas do teclado via `Scanner`, formata as saídas para o usuário e delega as decisões pesadas para o Controller.
* **Regra de Ouro:** Toda e qualquer interação via `System.out` ou `Scanner` deve ficar restrita a esta camada.

## 3. Controller (Controlador)
**Classe:** `EventController.java`
* **Responsabilidade:** Orquestrador do sistema. Ele faz a "ponte" entre a View e o Model.
* **Lógica de Persistência:** É aqui que o arquivo `events.data` é manipulado. O Controller lê o arquivo de texto, converte em objetos e os organiza.
* **Lógica de Ordenação:** Implementa o algoritmo de ordenação cronológica utilizando `Comparator.comparing(Evento::getHorario)`.

---

## 🔄 Fluxo de Funcionamento (Sequence)
1.  **Inicialização:** O `Main` solicita ao `EventController` o carregamento dos dados (`carregar()`).
2.  **Entrada:** O usuário preenche os dados de um novo evento na `Main`.
3.  **Processamento:** A `Main` envia um objeto `Evento` para o `EventController`.
4.  **Persistência:** O `EventController` adiciona o evento à lista, ordena e escreve a nova lista no arquivo `events.data` (`salvar()`).
5.  **Resposta:** O `EventController` confirma o sucesso e a `Main` exibe a mensagem final ao usuário.