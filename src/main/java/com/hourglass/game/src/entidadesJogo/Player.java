package com.hourglass.game.src.entidadesJogo;

import com.hourglass.game.api.entity.InventarioJogadorEntity;
import com.hourglass.game.api.entity.JogadorEntity;
import com.hourglass.game.src.main.ControleTeclado;
import com.hourglass.game.src.main.PainelJogo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import com.hourglass.game.src.banco.dao.InventarioJogadorDAO;

public final class Player extends Entidade {

    private final PainelJogo pj;
    private final ControleTeclado conTec;
    private JogadorEntity jogadorEntity;

    private InventarioJogadorEntity inventarioChave; // item_id = 1
    private InventarioJogadorEntity inventarioBuff;  // item_id = 2
    private final InventarioJogadorDAO inventarioDAO = new InventarioJogadorDAO();

    public final int telaX;
    public final int telaY;

    public int vida;
    public int temChave;
    public int temRaio;
    public int qtdBuffsColetados;
    public int tempoPoder;

    public boolean musicaMutada = false;

    public Player(PainelJogo pj, ControleTeclado conTec, JogadorEntity jogadorEntity) {
        this.pj = pj;
        this.conTec = conTec;
        this.jogadorEntity = jogadorEntity;

        this.mundoX = jogadorEntity.getPosicaoX();
        this.mundoY = jogadorEntity.getPosicaoY();
        this.vida = jogadorEntity.getVida();

        carregarInventario(jogadorEntity.getUsuarioId());

        telaX = pj.larguraTela / 2;
        telaY = pj.alturaTela / 2;

        configurarAreaSolida();
        configurarStatusIniciais();
        carregarSprites();
    }

    private void carregarInventario(int jogadorId) {
        // Inventário da chave
        inventarioChave = inventarioDAO.carregarInventario(jogadorId, 1);
        if (inventarioChave == null) {
            inventarioChave = new InventarioJogadorEntity();
            inventarioChave.setJogadorId(jogadorId);
            inventarioChave.setItemId(1);
            inventarioChave.setQuantidadeAtual(0);
            inventarioChave.setTotalColetado(0);
            inventarioChave.salvarViaDAO();
        }
        temChave = inventarioChave.getQuantidadeAtual();

        // Inventário do buff
        inventarioBuff = inventarioDAO.carregarInventario(jogadorId, 2);
        if (inventarioBuff == null) {
            inventarioBuff = new InventarioJogadorEntity();
            inventarioBuff.setJogadorId(jogadorId);
            inventarioBuff.setItemId(2);
            inventarioBuff.setQuantidadeAtual(0);
            inventarioBuff.setTotalColetado(0);
            inventarioBuff.salvarViaDAO();
        }
        qtdBuffsColetados = inventarioBuff.getTotalColetado();
    }

    private void configurarAreaSolida() {
        areaSolida = new Rectangle();
        areaSolida.x = 15;
        areaSolida.y = 16;
        areaSolidaPadraoX = 15;
        areaSolidaPadraoY = 16;
        areaSolida.width = 25;
        areaSolida.height = 32;
    }

    private void configurarStatusIniciais() {
        speed = 3;
        trocaSpriteVel = 6;
        direcao = "down";
        numeroSprite = 1;
    }

    private void carregarSprites() {
        try {
            down_1 = carregar("/com/hourglass/game/assets/player/down_1.png");
            down_2 = carregar("/com/hourglass/game/assets/player/down_2.png");
            down_3 = carregar("/com/hourglass/game/assets/player/down_3.png");
            down_4 = carregar("/com/hourglass/game/assets/player/down_4.png");
            down_5 = carregar("/com/hourglass/game/assets/player/down_5.png");
            down_6 = carregar("/com/hourglass/game/assets/player/down_6.png");
            down_7 = carregar("/com/hourglass/game/assets/player/down_7.png");
            down_8 = carregar("/com/hourglass/game/assets/player/down_8.png");

            up_1 = carregar("/com/hourglass/game/assets/player/up_1.png");
            up_2 = carregar("/com/hourglass/game/assets/player/up_2.png");
            up_3 = carregar("/com/hourglass/game/assets/player/up_3.png");
            up_4 = carregar("/com/hourglass/game/assets/player/up_4.png");
            up_5 = carregar("/com/hourglass/game/assets/player/up_5.png");
            up_6 = carregar("/com/hourglass/game/assets/player/up_6.png");
            up_7 = carregar("/com/hourglass/game/assets/player/up_7.png");
            up_8 = carregar("/com/hourglass/game/assets/player/up_8.png");

            right_1 = carregar("/com/hourglass/game/assets/player/right_1.png");
            right_2 = carregar("/com/hourglass/game/assets/player/right_2.png");
            right_3 = carregar("/com/hourglass/game/assets/player/right_3.png");
            right_4 = carregar("/com/hourglass/game/assets/player/right_4.png");
            right_5 = carregar("/com/hourglass/game/assets/player/right_5.png");
            right_6 = carregar("/com/hourglass/game/assets/player/right_6.png");
            right_7 = carregar("/com/hourglass/game/assets/player/right_7.png");
            right_8 = carregar("/com/hourglass/game/assets/player/right_8.png");

            left_1 = carregar("/com/hourglass/game/assets/player/left_1.png");
            left_2 = carregar("/com/hourglass/game/assets/player/left_2.png");
            left_3 = carregar("/com/hourglass/game/assets/player/left_3.png");
            left_4 = carregar("/com/hourglass/game/assets/player/left_4.png");
            left_5 = carregar("/com/hourglass/game/assets/player/left_5.png");
            left_6 = carregar("/com/hourglass/game/assets/player/left_6.png");
            left_7 = carregar("/com/hourglass/game/assets/player/left_7.png");
            left_8 = carregar("/com/hourglass/game/assets/player/left_8.png");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage carregar(String path) throws IOException {
        return ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
    }

    public void update() {
        if (conTec.mutePress) {
            if (!musicaMutada) pj.paraMusica();
            else pj.tocaMusica(0);

            musicaMutada = !musicaMutada;
            conTec.mutePress = false;
        }

        if (movimentoAtivo()) {
            definirDirecao();
            aplicarVelocidade();
            verificarColisoes();
            mover();
            animar();
        }

        if (temRaio > 0) tempoPoder++;
        if (tempoPoder >= 500) temRaio--;
    }

    private boolean movimentoAtivo() {
        return conTec.upPress || conTec.downPress ||
               conTec.leftPress || conTec.rightPress;
    }

    private void definirDirecao() {
        boolean up = conTec.upPress;
        boolean down = conTec.downPress;
        boolean left = conTec.leftPress;
        boolean right = conTec.rightPress;

        if (up && !down) direcao = "up";
        else if (down && !up) direcao = "down";
        else if (left && !right) direcao = "left";
        else if (right && !left) direcao = "right";
    }

    private void aplicarVelocidade() {
        if (temRaio > 0) {
            speed = conTec.shiftPress ? 8 : 6;
            trocaSpriteVel = conTec.shiftPress ? 2 : 3;
        } else {
            speed = conTec.shiftPress ? 6 : 3;
            trocaSpriteVel = conTec.shiftPress ? 3 : 6;
        }
    }

    private void verificarColisoes() {
        colisaoAtiva = false;

        pj.vColi.verificaTile(this);

        int objIndex = pj.vColi.verificaObjeto(this, true);
        coletaObjeto(objIndex);
    }

    private void mover() {
        if (!colisaoAtiva) {
            switch (direcao) {
                case "up" -> mundoY -= speed;
                case "down" -> mundoY += speed;
                case "left" -> mundoX -= speed;
                case "right" -> mundoX += speed;
            }
        }
    }

    private void animar() {
        contaSprite++;
        if (contaSprite > trocaSpriteVel) {
            numeroSprite = (numeroSprite % 8) + 1;
            contaSprite = 0;
        }
    }

    public void coletaObjeto(int i) {
        if (i == 999) return;

        String nomeObjeto = pj.obj[i].nome;

        switch (nomeObjeto) {
            case "Chave" -> {
                pj.tocaSFX(1);
                temChave++;
                inventarioChave.setQuantidadeAtual(temChave);
                inventarioChave.salvarViaDAO();
                pj.obj[i] = null;
                pj.ui.exibeMensagem("Você coletou uma chave!");
            }

            case "Porta" -> {
                if (temChave > 0) abrirPorta(i);
            }

            case "Raio" -> {
                pj.tocaSFX(3);
                temRaio++;
                qtdBuffsColetados++;
                inventarioBuff.setTotalColetado(qtdBuffsColetados);
                inventarioBuff.salvarViaDAO();
                pj.obj[i] = null;
            }
        }
    }

    private void abrirPorta(int index) {
        pj.tocaSFX(2);
        try {
            pj.obj[index].imagem = ImageIO.read(
                Objects.requireNonNull(getClass().getResourceAsStream("/com/hourglass/game/assets/objetos/door_2.png"))
            );
            pj.obj[index].colisao = false;
            pj.obj[index].nome = "PortaAberta";
        } catch (IOException e) {
            e.printStackTrace();
        }

        temChave--;
        inventarioChave.setQuantidadeAtual(temChave);
        inventarioChave.salvarViaDAO();
    }

    public void render(Graphics g2d) {
        BufferedImage imagem = switch (direcao) {
            case "up"    -> selecionarSprite(up_1, up_2, up_3, up_4, up_5, up_6, up_7, up_8);
            case "down"  -> selecionarSprite(down_1, down_2, down_3, down_4, down_5, down_6, down_7, down_8);
            case "left"  -> selecionarSprite(left_1, left_2, left_3, left_4, left_5, left_6, left_7, left_8);
            case "right" -> selecionarSprite(right_1, right_2, right_3, right_4, right_5, right_6, right_7, right_8);
            default      -> null;
        };

        g2d.drawImage(imagem, telaX, telaY, pj.tamanhoTile, pj.tamanhoTile, null);
    }

    private BufferedImage selecionarSprite(
            BufferedImage s1, BufferedImage s2, BufferedImage s3, BufferedImage s4,
            BufferedImage s5, BufferedImage s6, BufferedImage s7, BufferedImage s8
    ) {
        return switch (numeroSprite) {
            case 1 -> s1;
            case 2 -> s2;
            case 3 -> s3;
            case 4 -> s4;
            case 5 -> s5;
            case 6 -> s6;
            case 7 -> s7;
            case 8 -> s8;
            default -> s1;
        };
    }

    public void syncToEntity() {
        jogadorEntity.setPosicaoX(mundoX);
        jogadorEntity.setPosicaoY(mundoY);
        jogadorEntity.setVida(vida);
    }

    public JogadorEntity getJogadorEntity() {
        return jogadorEntity;
    }
}
