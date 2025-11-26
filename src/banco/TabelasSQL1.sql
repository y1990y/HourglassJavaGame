create database hourglass

USE hourglass

CREATE TABLE usuarios (
    id INT IDENTITY(1,1) PRIMARY KEY,
    usuario VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    created_at DATETIME2 DEFAULT SYSDATETIME(),
    updated_at DATETIME2 DEFAULT SYSDATETIME()
);

CREATE TABLE jogador
(
    usuario_id      INT PRIMARY KEY, -- mesmo ID da tabela usuarios
    nome_jogador    VARCHAR (100) NOT NULL,
    vida            INT DEFAULT 100,
    posicao_x       INT DEFAULT 0,
    posicao_y       INT DEFAULT 0,
    data_salvo      DATETIME2 DEFAULT SYSDATETIME(),
    FOREIGN KEY (usuario_id) REFERENCES usuarios (id) ON DELETE CASCADE
);

CREATE TABLE inventario_jogador
(
    jogador_id					INT PRIMARY KEY,
    qtd_chaves					INT DEFAULT 0,
    qtd_buffs_coletados			INT DEFAULT 0,
    ultima_atualizacao			DATETIME2 DEFAULT SYSDATETIME(),
    FOREIGN KEY (jogador_id)	REFERENCES jogador(usuario_id)
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

CREATE TABLE item
(
    id        INT IDENTITY(1,1) PRIMARY KEY,
    nome      NVARCHAR(100) NOT NULL,
    tipo      NVARCHAR(50) CHECK(tipo IN ('consumivel', 'chave', 'buff')) NOT NULL,
    descricao NVARCHAR(255)
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
