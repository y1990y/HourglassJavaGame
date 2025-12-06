# ⌛ Hourglass

## Sobre o Projeto

**Hourglass** é um jogo 2D em desenvolvimento como parte do projeto interdisciplinar do curso de **Tecnologia em Informática para Negócios** da **Fatec São José do Rio Preto**.  
O foco principal está na criação de um protótipo funcional que inclui **controle de jogador**, **sistema de inventário**, **itens**, **conquistas** e **persistência dos dados no banco de dados**.

O projeto integra conceitos de programação orientada a objetos, arquitetura em camadas e modelagem de banco de dados relacional.

---

## Objetivo do Projeto

O objetivo é implementar um sistema que permita:

- Autenticar usuários.
- Criar e gerenciar jogadores.
- Registrar atributos do jogador (vida, posição, nome).
- Coletar, armazenar e utilizar itens.
- Registrar conquistas e recompensas.
- Salvar e carregar todos os dados através do SQL Server.

---

## Tecnologias Utilizadas

- **Linguagem:** Java  
- **IDE:** IntelliJ IDEA / Visual Studio Code  
- **Banco de Dados:** SQL Server  
- **Modelagem:** UML e DER  
- **Arquitetura:** Estrutura baseada em camadas (Apresentação → Aplicação → Persistência)

---

## Organização das Classes

A arquitetura foi atualizada para refletir as entidades reais do banco de dados. As principais estruturas são:

- **Usuario** — representa a conta cadastrada no sistema.
- **Jogador** — entidade associada ao usuário, contendo nome, vida, posição e demais atributos do personagem.
- **Item** — tabela e classe que representam objetos coletáveis do jogo.
- **InventarioJogador** — relaciona um jogador aos itens que possui, controlando quantidade.
- **JogadorConquista** — armazena conquistas desbloqueadas pelo jogador.
- **ConquistasItens** — relaciona conquistas a itens de recompensa.

Essas entidades formam a base da lógica principal implementada até o momento.

---

## Banco de Dados

O sistema utiliza um banco de dados relacional SQL Server com integridade referencial entre as tabelas.  
As tabelas atualmente implementadas são:

- `usuarios`
- `jogador`
- `itens`
- `inventario_jogador`
- `conquistas_itens`
- `jogador_conquista`

O código Java interage com o banco, promovendo persistência dos dados do jogador.

---

## Status do Projeto

O projeto está em desenvolvimento ativo.  
As seguintes funcionalidades já estão estruturadas ou em implementação:

- Cadastro e login de usuários  
- Criação e atualização de jogadores  
- Registro de posição, vida e atributos básicos  
- Sistema de itens  
- Inventário do jogador  
- Sistema de conquistas integrado ao banco  

Novas mecânicas serão adicionadas conforme o avanço do desenvolvimento.

---

## Repositório

🔗 **GitHub:** https://github.com/y1990y/HourglassJavaGame.git

---

## Equipe de Desenvolvimento

| Nome                   | Função                         |
|------------------------|--------------------------------|
| Eduardo Risso de Mira  | Desenvolvimento e documentação |
| Giovanna Peres André   | Desenvolvimento e documentação |

---

## Considerações Finais

'Hourglass' é uma iniciativa pessoal com propósito acadêmico, fundamentada em uma base técnica sólida e estrutura adequada para futura expansão. O projeto tem como foco o avanço paralelamente ao aprendizado contínuo de sua equipe de desenvolvimento, promovendo evolução tanto no código quanto nas habilidades técnicas adquiridas ao longo do processo.

---
