package com.hourglass.game.src.main;

import com.hourglass.game.src.entidadesJogo.Entidade;

public class VerificaColi {

    PainelJogo pj;

    public VerificaColi(PainelJogo pj) {
        this.pj = pj;
    }

    public void verificaTile(Entidade entidade) {
        // Variáveis usadas para a detecção de colisão
        int entidadeMundoXEsquerdo = entidade.mundoX + entidade.areaSolida.x;
        int entidadeMundoXDireito = entidade.mundoX + entidade.areaSolida.x + entidade.areaSolida.width;
        int entidadeMundoYCima = entidade.mundoY + entidade.areaSolida.y;
        int entidadeMundoYBaixo = entidade.mundoY + entidade.areaSolida.y + entidade.areaSolida.height;

        int entidadeColEsquerda = entidadeMundoXEsquerdo / pj.tamanhoTile;
        int entidadeColDireita = entidadeMundoXDireito / pj.tamanhoTile;
        int entidadeFilCima = entidadeMundoYCima / pj.tamanhoTile;
        int entidadeFilBaixo = entidadeMundoYBaixo / pj.tamanhoTile;

        int numTile1, numTile2;

        // Detecção de colisão para cada direção

        switch (entidade.direcao) {
            case "up":
                entidadeFilCima = (entidadeMundoYCima - entidade.speed) / pj.tamanhoTile;
                numTile1 = pj.tileG.numTileMapa[entidadeColEsquerda][entidadeFilCima];
                numTile2 = pj.tileG.numTileMapa[entidadeColDireita][entidadeFilCima];
                if (pj.tileG.tile[numTile1].colisao == true || pj.tileG.tile[numTile2].colisao == true) {
                    entidade.colisaoAtiva = true;
                }
                break;
            case "down":
                entidadeFilBaixo = (entidadeMundoYBaixo + entidade.speed) / pj.tamanhoTile;
                numTile1 = pj.tileG.numTileMapa[entidadeColEsquerda][entidadeFilBaixo];
                numTile2 = pj.tileG.numTileMapa[entidadeColDireita][entidadeFilBaixo];
                if (pj.tileG.tile[numTile1].colisao == true || pj.tileG.tile[numTile2].colisao == true) {
                    entidade.colisaoAtiva = true;
                }
                break;
            case "left":
                entidadeColEsquerda = (entidadeMundoXEsquerdo - entidade.speed) / pj.tamanhoTile;
                numTile1 = pj.tileG.numTileMapa[entidadeColEsquerda][entidadeFilCima];
                numTile2 = pj.tileG.numTileMapa[entidadeColEsquerda][entidadeFilBaixo];
                if (pj.tileG.tile[numTile1].colisao == true || pj.tileG.tile[numTile2].colisao == true) {
                    entidade.colisaoAtiva = true;
                }
                break;
            case "right":
                entidadeColDireita = (entidadeMundoXDireito + entidade.speed) / pj.tamanhoTile;
                numTile1 = pj.tileG.numTileMapa[entidadeColDireita][entidadeFilCima];
                numTile2 = pj.tileG.numTileMapa[entidadeColDireita][entidadeFilBaixo];
                if (pj.tileG.tile[numTile1].colisao == true || pj.tileG.tile[numTile2].colisao == true) {
                    entidade.colisaoAtiva = true;
                }
                break;
        }
    }

    public int verificaObjeto(Entidade entidade, boolean player) { // Verifica se o jogador está em colisão com algum objeto, se sim, retorna o valor do index do objeto

        int index = 999;

        for (int i = 0; i < pj.obj.length; i++) {

            if (pj.obj[i] != null) {

                // Verifica a posição da area solida da entidade
                entidade.areaSolida.x = entidade.mundoX + entidade.areaSolida.x;
                entidade.areaSolida.y = entidade.mundoY + entidade.areaSolida.y;

                // Verifica a posição da area solida do objeto
                pj.obj[i].areaSolida.x = pj.obj[i].mundoX + pj.obj[i].areaSolida.x;
                pj.obj[i].areaSolida.y = pj.obj[i].mundoY + pj.obj[i].areaSolida.y;

                switch (entidade.direcao) { // Reação para cada tipo de colisão
                    case "up":
                        entidade.areaSolida.y -= entidade.speed;
                        if (entidade.areaSolida.intersects(pj.obj[i].areaSolida)) {
                            if(pj.obj[i].colisao == true) {
                                entidade.colisaoAtiva = true;
                            }
                            if(player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entidade.areaSolida.y += entidade.speed;
                        if (entidade.areaSolida.intersects(pj.obj[i].areaSolida)) {
                            if(pj.obj[i].colisao == true) {
                                entidade.colisaoAtiva = true;
                            }
                            if(player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entidade.areaSolida.x -= entidade.speed;
                        if (entidade.areaSolida.intersects(pj.obj[i].areaSolida)) {
                            if(pj.obj[i].colisao == true) {
                                entidade.colisaoAtiva = true;
                            }
                            if(player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entidade.areaSolida.x += entidade.speed;
                        if (entidade.areaSolida.intersects(pj.obj[i].areaSolida)) {
                            if(pj.obj[i].colisao == true) {
                                entidade.colisaoAtiva = true;
                            }
                            if(player == true) {
                                index = i;
                            }
                        }
                        break;
                }
                entidade.areaSolida.x = entidade.areaSolidaPadraoX;
                entidade.areaSolida.y = entidade.areaSolidaPadraoY;
                pj.obj[i].areaSolida.x = pj.obj[i].areaSolidaPadraoX;
                pj.obj[i].areaSolida.y = pj.obj[i].areaSolidaPadraoY;
            }
        }
        return index;
    }
}
