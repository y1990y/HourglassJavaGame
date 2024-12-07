package main;

import objeto.ObjetoBau;
import objeto.ObjetoChave;
import objeto.ObjetoPorta;
import sobreposicao.SbrTree1;
import sobreposicao.SbrTree2;
import sobreposicao.SbrTree3;
import sobreposicao.SbrTree4;

public class DefinidorAsset {

    PainelJogo pj;

    public DefinidorAsset(PainelJogo pj) {
        this.pj = pj;
    }

    public void defineObjeto() {

        pj.obj[0] = new ObjetoChave();
        pj.obj[0].mundoX = 25 * pj.tamanhoTile;
        pj.obj[0].mundoY = 19 * pj.tamanhoTile;

        pj.obj[1] = new ObjetoPorta();
        pj.obj[1].mundoX = 24 * pj.tamanhoTile;
        pj.obj[1].mundoY = 22 * pj.tamanhoTile;

        pj.obj[2] = new ObjetoBau();
        pj.obj[2].mundoX = 23 * pj.tamanhoTile;
        pj.obj[2].mundoY = 19 * pj.tamanhoTile;

        pj.obj[3] = new ObjetoBau();
        pj.obj[3].mundoX = 27 * pj.tamanhoTile;
        pj.obj[3].mundoY = 25 * pj.tamanhoTile;
    }

    public void defineSbr() {

        //Arvore direita baixo casa
        pj.sbr[0] = new SbrTree1();
        pj.sbr[0].mundoX = 30 * pj.tamanhoTile;
        pj.sbr[0].mundoY = 24 * pj.tamanhoTile;

        pj.sbr[1] = new SbrTree2();
        pj.sbr[1].mundoX = 31 * pj.tamanhoTile;
        pj.sbr[1].mundoY = 24 * pj.tamanhoTile;

        pj.sbr[2] = new SbrTree3();
        pj.sbr[2].mundoX = 30 * pj.tamanhoTile;
        pj.sbr[2].mundoY = 25 * pj.tamanhoTile;

        pj.sbr[3] = new SbrTree4();
        pj.sbr[3].mundoX = 31 * pj.tamanhoTile;
        pj.sbr[3].mundoY = 25 * pj.tamanhoTile;

        // Arvore esquerda baixo casa

        pj.sbr[4] = new SbrTree1();
        pj.sbr[4].mundoX = 11 * pj.tamanhoTile;
        pj.sbr[4].mundoY = 21 * pj.tamanhoTile;

        pj.sbr[5] = new SbrTree2();
        pj.sbr[5].mundoX = 12 * pj.tamanhoTile;
        pj.sbr[5].mundoY = 21 * pj.tamanhoTile;

        pj.sbr[6] = new SbrTree3();
        pj.sbr[6].mundoX = 11 * pj.tamanhoTile;
        pj.sbr[6].mundoY = 22 * pj.tamanhoTile;

        pj.sbr[7] = new SbrTree4();
        pj.sbr[7].mundoX = 12 * pj.tamanhoTile;
        pj.sbr[7].mundoY = 22 * pj.tamanhoTile;

        // Arvore direita casa lado

        pj.sbr[8] = new SbrTree1();
        pj.sbr[8].mundoX = 34 * pj.tamanhoTile;
        pj.sbr[8].mundoY = 16 * pj.tamanhoTile;

        pj.sbr[9] = new SbrTree2();
        pj.sbr[9].mundoX = 35 * pj.tamanhoTile;
        pj.sbr[9].mundoY = 16 * pj.tamanhoTile;

        pj.sbr[10] = new SbrTree3();
        pj.sbr[10].mundoX = 34 * pj.tamanhoTile;
        pj.sbr[10].mundoY = 17 * pj.tamanhoTile;

        pj.sbr[11] = new SbrTree4();
        pj.sbr[11].mundoX = 35 * pj.tamanhoTile;
        pj.sbr[11].mundoY = 17 * pj.tamanhoTile;

        // Arvore direita casa lado/cima

        pj.sbr[12] = new SbrTree1();
        pj.sbr[12].mundoX = 32 * pj.tamanhoTile;
        pj.sbr[12].mundoY = 12 * pj.tamanhoTile;

        pj.sbr[13] = new SbrTree2();
        pj.sbr[13].mundoX = 33 * pj.tamanhoTile;
        pj.sbr[13].mundoY = 12 * pj.tamanhoTile;

        pj.sbr[14] = new SbrTree3();
        pj.sbr[14].mundoX = 32 * pj.tamanhoTile;
        pj.sbr[14].mundoY = 13 * pj.tamanhoTile;

        pj.sbr[15] = new SbrTree4();
        pj.sbr[15].mundoX = 33 * pj.tamanhoTile;
        pj.sbr[15].mundoY = 13 * pj.tamanhoTile;

        // Arvore direita casa lado/cima

        pj.sbr[16] = new SbrTree1();
        pj.sbr[16].mundoX = 15 * pj.tamanhoTile;
        pj.sbr[16].mundoY = 8 * pj.tamanhoTile;

        pj.sbr[17] = new SbrTree2();
        pj.sbr[17].mundoX = 16 * pj.tamanhoTile;
        pj.sbr[17].mundoY = 8 * pj.tamanhoTile;

        pj.sbr[18] = new SbrTree3();
        pj.sbr[18].mundoX = 15 * pj.tamanhoTile;
        pj.sbr[18].mundoY = 9 * pj.tamanhoTile;

        pj.sbr[19] = new SbrTree4();
        pj.sbr[19].mundoX = 16 * pj.tamanhoTile;
        pj.sbr[19].mundoY = 9 * pj.tamanhoTile;

    }
}
