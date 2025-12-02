# ⌛ Hourglass

## Sobre o Projeto

**Hourglass** é um jogo 2D em desenvolvimento, criado como parte do projeto interdisciplinar do curso de **Tecnologia em Informática para Negócios** da **Fatec São José do Rio Preto**. A proposta consiste em implementar um protótipo funcional com foco em manipulação temporal, controle de inventário e persistência de estado do jogo.

Este projeto busca integrar conceitos técnicos de programação, modelagem orientada a objetos, arquitetura de software e banco de dados relacional.

## Objetivo

O objetivo é construir um sistema que permita ao jogador interagir com o ambiente a sua volta, coletar itens, salvar e restaurar o progresso, além de utilizar a mecânica de retorno no tempo como ferramenta estratégica.

Durante o processo de desenvolvimento, existe a tentativa de criar:
- Uma estrutura de classes coesa e reutilizável.
- Persistência confiável dos dados do jogador.
- Separação clara entre as camadas do sistema.
- Uma base sólida para expansão futura.

## Tecnologias Utilizadas

- **Linguagem:** Java
- **IDE:** IntelliJ IDEA - Visual Studio Code
- **Banco de Dados:** SQL Server
- **Modelagem:** UML (Classes, Sequência, ER)
- **Arquitetura:** Baseada em camadas (Apresentação, Aplicação, Persistência)

## Organização das Classes

As classes foram organizadas pensando em manter coesão e responsabilidade única:

- `Usuario`: representa o usuário autenticado no sistema.
- `Jogador`: herda de `Usuario` e define o personagem controlado.
- `Item`: descreve os objetos que podem ser coletados.
- `Inventario`: define a relação entre jogador e itens armazenados.
- `MapaEstado`: armazena o estado de elementos interativos do ambiente.
- `SistemaTempo`: gerencia os registros de retorno temporal (snapshots).

## Banco de Dados

O projeto utiliza banco de dados relacional com modelagem normalizada e integridade referencial. O controle de persistência será realizado por meio de conexão JDBC com SQL Server.

As principais tabelas do sistema são:

- `usuarios`
- `jogadores`
- `inventario`

Scripts SQL encontram-se organizados na pasta `/banco`.

## Diagrama Entidade-Relacionamento (DER)

O DER pode ser visualizado no arquivo `DER hourglass.html` incluso na pasta `banco/der/`.

## Status

O projeto encontra-se em desenvolvimento. Algumas funcionalidades continuam a ser implementadas e testadas. A arquitetura e os principais componentes já estão definidos, com foco nas funcionalidades básicas do projeto.

## Repositório

O código fonte está disponível em:

[🔗 GitHub - Hourglass](https://github.com/y1990y/HourglassJavaGame.git)

## Equipe de Desenvolvimento

| Nome                  | Função                         |
|-----------------------|--------------------------------|
| Eduardo Risso de Mira | Desenvolvimento e documentação |
| Giovanna Peres André  | Desenvolvimento e documentação |

## Considerações Finais

'Hourglass' é uma iniciativa pessoal com propósito acadêmico, fundamentada em uma base técnica sólida e estrutura adequada para futura expansão. O projeto tem como foco o avanço paralelamente ao aprendizado contínuo de sua equipe de desenvolvimento, promovendo evolução tanto no código quanto nas habilidades técnicas adquiridas ao longo do processo.

---

