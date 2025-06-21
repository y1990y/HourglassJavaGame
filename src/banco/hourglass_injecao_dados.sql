-- Injeção de Dados - Projeto Hourglass

-- Tabela: usuarios
INSERT INTO usuarios (nome, email, senha) VALUES
('Alice', 'alice@email.com', 'senha123'),
('Bruno', 'bruno@email.com', 'abc456'),
('Carla', 'carla@email.com', 'xyz789'),
('Daniel', 'daniel@email.com', 'teste123'),
('Eduarda', 'eduarda@email.com', 'senha321'),
('Felipe', 'felipe@email.com', 'qwe123'),
('Giovana', 'giovana@email.com', 'asd456'),
('Henrique', 'henrique@email.com', 'zxc789'),
('Isabela', 'isabela@email.com', 'segura123'),
('Jonas', 'jonas@email.com', '123senha');

-- Tabela: jogador
INSERT INTO jogador (id, nome_personagem, posicao_x, posicao_y, vida) VALUES
(1, 'Chrono', 100, 200, 10),
(2, 'Blade', 50, 180, 8),
(3, 'Luna', 90, 120, 9),
(4, 'Nova', 130, 140, 10),
(5, 'Zen', 30, 60, 7),
(6, 'Kira', 75, 90, 6),
(7, 'Thor', 200, 50, 10),
(8, 'Ashe', 10, 10, 5),
(9, 'Mira', 90, 180, 9),
(10, 'Drake', 150, 100, 10);

-- Tabela: item
INSERT INTO item (nome, tipo, descricao) VALUES
('Chave de Ferro', 'chave', 'Abre portas trancadas'),
('Poção de Vida', 'consumivel', 'Recupera 20 pontos de vida'),
('Cristal Temporal', 'buff', 'Permite voltar no tempo'),
('Chave Dourada', 'chave', 'Abre portas especiais'),
('Poção de Energia', 'consumivel', 'Recupera energia'),
('Livro Antigo', 'buff', 'Libera habilidades especiais'),
('Fragmento de Luz', 'buff', 'Ilumina o caminho'),
('Flecha Fantasma', 'consumivel', 'Causa dano a distância'),
('Chave Rústica', 'chave', 'Abre portões de madeira'),
('Relógio Quebrado', 'buff', 'Fragmento de tempo perdido');

-- Tabela: inventario
INSERT INTO inventario (jogador_id, item_id, quantidade) VALUES
(1, 1, 1),
(1, 2, 3),
(2, 2, 2),
(2, 3, 1),
(3, 4, 1),
(4, 5, 2),
(5, 6, 1),
(6, 7, 3),
(7, 8, 2),
(8, 9, 1);

-- Tabela: mapa_estado
INSERT INTO mapa_estado (jogador_id, mapa_nome, porta_id, porta_aberta, item_id, item_coletado) VALUES
(1, 'Mapa1', 'P1', 1, 1, 1),
(2, 'Mapa1', 'P2', 0, 2, 1),
(3, 'Mapa2', 'P3', 1, 3, 0),
(4, 'Mapa2', 'P1', 0, 1, 0),
(5, 'Mapa3', 'P4', 1, 4, 1),
(6, 'Mapa3', 'P2', 0, 5, 0),
(7, 'Mapa1', 'P1', 1, 1, 1),
(8, 'Mapa4', 'P5', 1, 6, 0),
(9, 'Mapa4', 'P2', 0, 2, 0),
(10, 'Mapa5', 'P6', 1, 3, 1);

-- Tabela: sistema_tempo
INSERT INTO sistema_tempo (jogador_id, estado_salvo) VALUES
(1, 'estado_json_1'),
(1, 'estado_json_2'),
(2, 'estado_json_1'),
(3, 'estado_json_1'),
(4, 'estado_json_1'),
(5, 'estado_json_1'),
(6, 'estado_json_1'),
(7, 'estado_json_1'),
(8, 'estado_json_1'),
(9, 'estado_json_1');