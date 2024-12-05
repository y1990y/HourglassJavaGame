package tile;

import main.PainelJogo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class GerenciadorTile {
    PainelJogo pj;
    Tile[] tile;
    int numTileMapa[][];

    public GerenciadorTile(PainelJogo pj) {

        this.pj = pj;
        tile = new Tile[10]; // Número de tipos de tiles
        numTileMapa = new int[pj.maxColTela][pj.maxFilTela];

        imagemTile();
        carregarMapa("/mapas/mapa01.txt"); // Caminho do mapa utilizado pelo metodo, evitando a criação de um novo metodo
    }

    public void imagemTile() {

        try {

            tile[0] = new Tile();
            tile[0].imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/floor_1.png")));

            tile[1] = new Tile();
            tile[1].imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/wall_1.png")));

            tile[2] = new Tile();
            tile[2].imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/water_1.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void carregarMapa(String caminhoArquivo) {

        try {

            InputStream is = getClass().getResourceAsStream(caminhoArquivo); // Importa o arquivo de texto para a construção do mapa
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int fil = 0;

            while(col < pj.maxColTela && fil < pj.maxFilTela) {
                String linha = br.readLine(); // Faz a leitura da linha de texto presente no arquivo do mapa

                while(col < pj.maxColTela) {
                    String numero[] = linha.split(" "); // Separa a linha e pega os números das tiles um por um (numero[0]; numero[1]; numero[2]...)

                    int num = Integer.parseInt(numero[col]); // Transforma a String 'numero' para uma int 'num' para podermos usar o valor como um número
                    numTileMapa[col][fil] = num; // Armazena o número extraído em 'numTileMapa'
                    col++;
                }
                if(col == pj.maxColTela) { // Caso a coluna seja completa, será adicionada uma nova fileira e a coluna será zerada
                    col = 0;
                    fil++;
                }
            }
            br.close(); // Encerramento do 'BufferedReader'

        } catch (Exception e) {

        }
    }
    public void render(Graphics2D g2d) {

        int col = 0;
        int fil = 0;
        int x = 0;
        int y = 0;

        while (col < pj.maxColTela && fil < pj.maxFilTela) { // Loop da construção do cenário

            int numTile = numTileMapa[col][fil]; // As informações do tile map estão armazenadas no 'numTileMapa', fazendo com que a função abaixo possa o 'desenhar'

            g2d.drawImage(tile[numTile].imagem, x, y, pj.tamanhoTile, pj.tamanhoTile, null);
            col++;
            x += pj.tamanhoTile;

            if (col == pj.maxColTela) {
                col = 0;
                x = 0;
                fil++;
                y += pj.tamanhoTile;
            }
        }

    }
}
