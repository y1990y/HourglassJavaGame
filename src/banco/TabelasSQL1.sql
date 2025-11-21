create database hourglass

USE hourglass
CREATE TABLE usuarios
(
    id         INT IDENTITY(1,1) PRIMARY KEY,
    nome       NVARCHAR(140) NOT NULL,
    email      NVARCHAR(255) NOT NULL UNIQUE,
    senha      NVARCHAR(255) NOT NULL,
    created_at DATETIME2 DEFAULT SYSDATETIME(),
    updated_at DATETIME2 DEFAULT SYSDATETIME()
);

CREATE TABLE jogador
(
    id              INT PRIMARY KEY, -- mesmo ID da tabela usuarios
    nome_personagem NVARCHAR(100) NOT NULL,
    posicao_x       INT NOT NULL,
    posicao_y       INT NOT NULL,
    vida            INT NOT NULL,
    data_salvo      DATETIME2 DEFAULT SYSDATETIME(),
    FOREIGN KEY (id) REFERENCES usuarios (id)
);

CREATE TABLE item
(
    id        INT IDENTITY(1,1) PRIMARY KEY,
    nome      NVARCHAR(100) NOT NULL,
    tipo      NVARCHAR(50) CHECK(tipo IN ('consumivel', 'chave', 'buff')) NOT NULL,
    descricao NVARCHAR(255)
);

-- CREATE TABLE inventario
-- (
--     id         INT IDENTITY(1,1) PRIMARY KEY,
--     jogador_id INT NOT NULL,
--     item_id    INT NOT NULL,
--     quantidade INT DEFAULT 1,
--     FOREIGN KEY (jogador_id) REFERENCES jogador (id),
--     FOREIGN KEY (item_id) REFERENCES item (id)
-- );

CREATE TABLE inventario_jogador
(
    jogador_id					INT PRIMARY KEY,
    qtd_chaves					INT DEFAULT 0,
    qtd_buffs_coletados			INT DEFAULT 0,
    ultima_atualizacao			DATETIME2 DEFAULT SYSDATETIME(),
    FOREIGN KEY (jogador_id)	REFERENCES jogador(id)
);

CREATE TABLE mapa_estado
(
    id            INT IDENTITY(1,1) PRIMARY KEY,
    jogador_id    INT NOT NULL,
    mapa_nome     NVARCHAR(100) NOT NULL,
    porta_id      NVARCHAR(50),
    porta_aberta  BIT DEFAULT 0,
    item_id       INT NOT NULL,
    item_coletado BIT DEFAULT 0,
    FOREIGN KEY (jogador_id) REFERENCES jogador (id),
    FOREIGN KEY (item_id) REFERENCES item (id)
);

CREATE TABLE sistema_tempo
(
    id           INT IDENTITY(1,1) PRIMARY KEY,
    jogador_id   INT NOT NULL,
    estado_salvo NVARCHAR(MAX) NOT NULL, -- JSON com snapshots
    data_criacao DATETIME2 DEFAULT SYSDATETIME(),
    FOREIGN KEY (jogador_id) REFERENCES jogador (id)
);
