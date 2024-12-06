package main;

import entidade.Entidade;

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

        int entidadeColEsquerda = entidadeMundoXEsquerdo/ pj.tamanhoTile;
        int entidadeColDireita = entidadeMundoXDireito/ pj.tamanhoTile;
        int entidadeFilCima = entidadeMundoYCima/pj.tamanhoTile;
        int entidadeFilBaixo = entidadeMundoYBaixo/pj.tamanhoTile;

        int numTile1, numTile2;

        // Detecção de colisão para cada direção

        switch (entidade.direcao) {
            case "up":
                entidadeFilCima = (entidadeMundoYCima - entidade.speed)/pj.tamanhoTile;
                numTile1 = pj.tileG.numTileMapa[entidadeColEsquerda][entidadeFilCima];
                numTile2 = pj.tileG.numTileMapa[entidadeColDireita][entidadeFilCima];
                if (pj.tileG.tile[numTile1].colisao == true || pj.tileG.tile[numTile2].colisao == true) {
                    entidade.colisaoAtiva = true;
                }
                break;
            case "down":
                entidadeFilBaixo = (entidadeMundoYBaixo + entidade.speed)/pj.tamanhoTile;
                numTile1 = pj.tileG.numTileMapa[entidadeColEsquerda][entidadeFilBaixo];
                numTile2 = pj.tileG.numTileMapa[entidadeColDireita][entidadeFilBaixo];
                if (pj.tileG.tile[numTile1].colisao == true || pj.tileG.tile[numTile2].colisao == true) {
                    entidade.colisaoAtiva = true;
                }
                break;
            case "left":
                entidadeColEsquerda = (entidadeMundoXEsquerdo - entidade.speed)/pj.tamanhoTile;
                numTile1 = pj.tileG.numTileMapa[entidadeColEsquerda][entidadeFilCima];
                numTile2 = pj.tileG.numTileMapa[entidadeColEsquerda][entidadeFilBaixo];
                if (pj.tileG.tile[numTile1].colisao == true || pj.tileG.tile[numTile2].colisao == true) {
                    entidade.colisaoAtiva = true;
                }
                break;
            case "right":
                entidadeColDireita = (entidadeMundoXDireito + entidade.speed)/pj.tamanhoTile;
                numTile1 = pj.tileG.numTileMapa[entidadeColDireita][entidadeFilCima];
                numTile2 = pj.tileG.numTileMapa[entidadeColDireita][entidadeFilBaixo];
                if (pj.tileG.tile[numTile1].colisao == true || pj.tileG.tile[numTile2].colisao == true) {
                    entidade.colisaoAtiva = true;
                }
                break;
        }
    }
}
