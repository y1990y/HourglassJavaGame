package entidade;

import main.ControleTeclado;
import main.PainelJogo;
import objeto.ObjetoPorta;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public final class Player extends Entidade {

    PainelJogo pj;
    ControleTeclado conTec;

    public final int telaX;
    public final int telaY;

    // Verifica os itens que o jogador coletou
    int temChave = 0;
    public int temRaio = 0;
    public int tempoPoder = 0;

    public Player(PainelJogo pj, ControleTeclado conTec) {
        this.pj = pj;
        this.conTec = conTec;

        telaX = pj.larguraTela/2;
        telaY = pj.alturaTela/2;

        areaSolida = new Rectangle();
        areaSolida.x = 15;
        areaSolida.y = 16;
        areaSolidaPadraoX = areaSolida.x;
        areaSolidaPadraoY = areaSolida.y;
        areaSolida.width = 25;
        areaSolida.height = 32;

        valoresIniciais();
        carregaSpritePlayer();
    }

    public void valoresIniciais() {
        // Posições iniciais
        mundoX = pj.tamanhoTile * 24; // Define a posição horizontal do jogador na tela
        mundoY = pj.tamanhoTile * 20; // Define a posição vertical do jogador na tela
        // Velocidade e direção inicial
        speed = 3;
        trocaSpriteVel = 6;
        direcao = "down";
    }

    public void carregaSpritePlayer() {
        try {
            down_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/down_1.png")));
            down_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/down_2.png")));
            down_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/down_3.png")));
            down_4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/down_4.png")));
            down_5 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/down_5.png")));
            down_6 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/down_6.png")));
            down_7 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/down_7.png")));
            down_8 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/down_8.png")));
            up_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/up_1.png")));
            up_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/up_2.png")));
            up_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/up_3.png")));
            up_4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/up_4.png")));
            up_5 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/up_5.png")));
            up_6 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/up_6.png")));
            up_7 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/up_7.png")));
            up_8 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/up_8.png")));
            right_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/right_1.png")));
            right_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/right_2.png")));
            right_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/right_3.png")));
            right_4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/right_4.png")));
            right_5 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/right_5.png")));
            right_6 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/right_6.png")));
            right_7 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/right_7.png")));
            right_8 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/right_8.png")));
            left_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/left_1.png")));
            left_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/left_2.png")));
            left_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/left_3.png")));
            left_4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/left_4.png")));
            left_5 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/left_5.png")));
            left_6 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/left_6.png")));
            left_7 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/left_7.png")));
            left_8 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/left_8.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        //Movimentação

        // Impede a troca de sprites parado
        if (conTec.upPress == true || conTec.downPress == true || conTec.leftPress == true || conTec.rightPress == true) {

            if (conTec.upPress == true) {
                direcao = "up";
            }
            else if (conTec.downPress == true) {
                direcao = "down";
            }
            else if (conTec.leftPress == true) {
                direcao = "left";
            }
            else if (conTec.rightPress == true) {
                direcao = "right";
            }

            // Velocidades com o botão de corrida ativo e inativo sem power up de velocidade
            if (conTec.shiftPress == true && temRaio < 1) {
                speed = 6;
                trocaSpriteVel = 3;
            } else if (conTec.shiftPress == false && temRaio < 1) {
                speed = 3;
                trocaSpriteVel = 6;
            }

            // Velocidades com o botão de corrida ativo e inativo com power up de velocidade
            if (conTec.shiftPress == true && temRaio > 0) {
                speed = 8;
                trocaSpriteVel = 2;
            } else if (conTec.shiftPress == false && temRaio > 0) {
                speed = 6;
                trocaSpriteVel = 3;
            }

            // Verifica a colisão com tiles
            colisaoAtiva = false;
            pj.vColi.verificaTile(this);

            // Verifica a colisão com objetos
            int objIndex = pj.vColi.verificaObjeto(this, true);
            coletaObjeto(objIndex);

            // Se a colisão não acontece, o player consegue se mover
            if (colisaoAtiva == false) {

                switch (direcao) {
                    case "up":
                        mundoY -= speed;
                        break;
                    case "down":
                        mundoY += speed;
                        break;
                    case "left":
                        mundoX -= speed;
                        break;
                    case "right":
                        mundoX += speed;
                        break;
                }
            }

            contaSprite++;
            if (contaSprite > trocaSpriteVel) { // Troca de sprites com base no tempo de update do jogo
                if (numeroSprite == 1) {
                    numeroSprite = 2;
                }
                else if (numeroSprite == 2) {
                    numeroSprite = 3;
                }
                else if (numeroSprite == 3) {
                    numeroSprite = 4;
                }
                else if (numeroSprite == 4) {
                    numeroSprite = 5;
                }
                else if (numeroSprite == 5) {
                    numeroSprite = 6;
                }
                else if (numeroSprite == 6) {
                    numeroSprite = 7;
                }
                else if (numeroSprite == 7) {
                    numeroSprite = 8;
                }
                else if (numeroSprite == 8) {
                    numeroSprite = 1;
                }
                contaSprite = 0;
            }
        }

        if (temRaio > 0) {
            tempoPoder++;
        }

        if (tempoPoder >= 500) {  // Tempo ativo do poder
            temRaio--;
        }
    }

    public void coletaObjeto(int i) {

        if (i != 999) {

            String nomeObjeto = pj.obj[i].nome;

            switch (nomeObjeto) {
                case "Chave":
                    pj.tocaSFX(1);
                    temChave++;
                    pj.obj[i] = null;
                    System.out.println("Chaves: " + temChave);
                    break;
                case "Porta":
                    if(temChave > 0) {
                        pj.tocaSFX(2);
                        try {
                            pj.obj[i].imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objetos/door_2.png")));
                            pj.obj[i].colisao = false;
                            pj.obj[i].nome = "PortaAberta";
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        temChave--;
                    }
                    System.out.println("Chaves: " + temChave);
                    break;
                case "Raio":
                    pj.tocaSFX(3);
                    temRaio++;
                    pj.obj[i] = null;
                    break;
            }
        }
    }

    public void render(Graphics g2d) {

        BufferedImage imagem = null;

        switch (direcao) {
            case "up":
                if (numeroSprite == 1) {
                    imagem = up_1;
                }
                if (numeroSprite == 2) {
                    imagem = up_2;
                }
                if (numeroSprite == 3) {
                    imagem = up_3;
                }
                if (numeroSprite == 4) {
                    imagem = up_4;
                }
                if (numeroSprite == 5) {
                    imagem = up_5;
                }
                if (numeroSprite == 6) {
                    imagem = up_6;
                }
                if (numeroSprite == 7) {
                    imagem = up_7;
                }
                if (numeroSprite == 8) {
                    imagem = up_8;
                }
                break;
            case "down":
                if (numeroSprite == 1) {
                    imagem = down_1;
                }
                if (numeroSprite == 2) {
                    imagem = down_2;
                }
                if (numeroSprite == 3) {
                    imagem = down_3;
                }
                if (numeroSprite == 4) {
                    imagem = down_4;
                }
                if (numeroSprite == 5) {
                    imagem = down_5;
                }
                if (numeroSprite == 6) {
                    imagem = down_6;
                }
                if (numeroSprite == 7) {
                    imagem = down_7;
                }
                if (numeroSprite == 8) {
                    imagem = down_8;
                }
                break;
            case "left":
                if (numeroSprite == 1) {
                    imagem = left_1;
                }
                if (numeroSprite == 2) {
                    imagem = left_2;
                }
                if (numeroSprite == 3) {
                    imagem = left_3;
                }
                if (numeroSprite == 4) {
                    imagem = left_4;
                }
                if (numeroSprite == 5) {
                    imagem = left_5;
                }
                if (numeroSprite == 6) {
                    imagem = left_6;
                }
                if (numeroSprite == 7) {
                    imagem = left_7;
                }
                if (numeroSprite == 8) {
                    imagem = left_8;
                }
                break;
            case "right":
                if (numeroSprite == 1) {
                    imagem = right_1;
                }
                if (numeroSprite == 2) {
                    imagem = right_2;
                }
                if (numeroSprite == 3) {
                    imagem = right_3;
                }
                if (numeroSprite == 4) {
                    imagem = right_4;
                }
                if (numeroSprite == 5) {
                    imagem = right_5;
                }
                if (numeroSprite == 6) {
                    imagem = right_6;
                }
                if (numeroSprite == 7) {
                    imagem = right_7;
                }
                if (numeroSprite == 8) {
                    imagem = right_8;
                }
                break;
        }
        g2d.drawImage(imagem, telaX, telaY, pj.tamanhoTile, pj.tamanhoTile, null);
    }
}