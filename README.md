# 📅 Sistema de Gestão de Eventos Urbanos

Este projeto é uma aplicação de console desenvolvida em **Java** para a gestão de eventos em cidades. O foco principal foi aplicar os fundamentos da Programação Orientada a Objetos (POO) e organização arquitetural.

## 🚀 Funcionalidades
- **Cadastro de Usuário:** Sistema de identificação com Nome, Email e Cidade.
- **Gestão de Eventos:** Cadastro completo com nome, endereço, categoria, horário e descrição.
- **Categorias Suportadas:** Festas, Esportes, Shows e Culturais.
- **Inteligência de Horários:** O sistema identifica se o evento está *Ocorrendo Agora*, se é um evento *Futuro* ou se já foi *Encerrado*.
- **Persistência de Dados:** Salvamento automático em arquivo de texto (`events.data`).
- **Agenda Personalizada:** Opção para o usuário confirmar presença ou cancelar participação em eventos.

## 🏗️ Arquitetura do Projeto
O sistema segue o padrão **MVC (Model-View-Controller)**:
- **Model:** Representação dos dados e regras de negócio.
- **View:** Interface de linha de comando (CLI) para interação com o usuário.
- **Controller:** Gerenciamento da lógica, ordenação cronológica e persistência em arquivo.

## 📁 Documentação Adicional
Os detalhes técnicos e diagramas podem ser encontrados na pasta `/docs`:
- [Diagrama de Classes UML](./docs/DIAGRAMA_CLASSES.md)
- [Explicação da Arquitetura MVC](./docs/MVC_ARQUITETURA.md)

## 🛠️ Tecnologias Utilizadas
- **Linguagem:** Java 21
- **IDE:** IntelliJ IDEA
- **Versionamento:** Git & GitHub
- **API de Data:** `java.time` (LocalDateTime)

---
*Projeto desenvolvido para fins acadêmicos - 1º Semestre de Análise e Desenvolvimento de Sistemas.*