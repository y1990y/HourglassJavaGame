package main;

import objeto.ChaveObjeto;

public class DefinidorAsset {

    PainelJogo pj;

    public DefinidorAsset(PainelJogo pj) {
        this.pj = pj;
    }

    public void defineObjeto() {

        pj.obj[0] = new ChaveObjeto();
        pj.obj[0].mundoX = 23 * pj.tamanhoTile;
        pj.obj[0].mundoY = 19 * pj.tamanhoTile;

        pj.obj[1] = new ChaveObjeto();
        pj.obj[1].mundoX = 27 * pj.tamanhoTile;
        pj.obj[1].mundoY = 34 * pj.tamanhoTile;

    }
}
