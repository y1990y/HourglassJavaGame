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
    public Tile[] tile;
    public int numTileMapa[][];

    public GerenciadorTile(PainelJogo pj) {

        this.pj = pj;
        tile = new Tile[20]; // Número de tipos de tiles
        numTileMapa = new int[pj.maxColMundo][pj.maxFilMundo];

        imagemTile();
        carregarMapa("/mapas/mapa02.txt"); // Caminho do mapa utilizado pelo metodo, evitando a criação de um novo metodo
    }

    public void imagemTile() {

        try {

            tile[0] = new Tile();
            tile[0].imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/floor_1.png")));

            tile[1] = new Tile();
            tile[1].imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/wall_1.png")));
            tile[1].colisao = true;

            tile[2] = new Tile();
            tile[2].imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/water_1.png")));
            tile[2].colisao = true;

            tile[3] = new Tile();
            tile[3].imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/dirt_1.png")));

            tile[4] = new Tile();
            tile[4].imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/sand_1.png")));

            tile[5] = new Tile();
            tile[5].imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/bush_1.png")));
            tile[5].colisao = true;

            tile[6] = new Tile();
            tile[6].imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/stone_1.png")));

            // Árvore grande

            tile[7] = new Tile();
            tile[7].imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/tree_1.png")));
            tile[7].colisao = true;

            tile[8] = new Tile();
            tile[8].imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/tree_2.png")));
            tile[8].colisao = true;

            tile[9] = new Tile();
            tile[9].imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/tree_3.png")));
            tile[9].colisao = true;

            tile[10] = new Tile();
            tile[10].imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/tree_4.png")));
            tile[10].colisao = true;

            tile[11] = new Tile();
            tile[11].imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/tree_5.png")));
            tile[11].colisao = true;

            tile[12] = new Tile();
            tile[12].imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/tree_6.png")));
            tile[12].colisao = true;

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

            while(col < pj.maxColMundo && fil < pj.maxFilMundo) {
                String linha = br.readLine(); // Faz a leitura da linha de texto presente no arquivo do mapa

                while(col < pj.maxColMundo) {
                    String numero[] = linha.split(" "); // Separa a linha e pega os números das tiles um por um (numero[0]; numero[1]; numero[2]...)

                    int num = Integer.parseInt(numero[col]); // Transforma a String 'numero' para uma int 'num' para podermos usar o valor como um número
                    numTileMapa[col][fil] = num; // Armazena o número extraído em 'numTileMapa'
                    col++;
                }
                if(col == pj.maxColMundo) { // Caso a coluna seja completa, será adicionada uma nova fileira e a coluna será zerada
                    col = 0;
                    fil++;
                }
            }
            br.close(); // Encerramento do 'BufferedReader'

        } catch (Exception e) {

        }
    }
    public void render(Graphics2D g2d) {

        int mundoCol = 0;
        int mundoFil = 0;


        while (mundoCol < pj.maxColMundo && mundoFil < pj.maxFilMundo) { // Loop da construção do cenário

            int numTile = numTileMapa[mundoCol][mundoFil]; // As informações do tile map estão armazenadas no 'numTileMapa', fazendo com que a função abaixo possa o 'desenhar'

            int mundoX = mundoCol * pj.tamanhoTile; // Define a posição horizontal dos objetos com relação ao mundo no jogo
            int mundoY = mundoFil * pj.tamanhoTile; // Define a posição vertical dos objetos com relação ao mundo no jogo
            int telaX = mundoX - pj.player.mundoX + pj.player.telaX; // Define a posição horizontal dos objetos com relação à tela do jogo
            int telaY = mundoY - pj.player.mundoY + pj.player.telaY; // Define a posição vertical dos objetos com relação à tela do jogo

            if(mundoX + pj.tamanhoTile >  pj.player.mundoX - pj.player.telaX && mundoX - pj.tamanhoTile < pj.player.mundoX + pj.player.telaX && mundoY + pj.tamanhoTile > pj.player.mundoY - pj.player.telaY && mundoY - pj.tamanhoTile < pj.player.mundoY + pj.player.telaY) {

                g2d.drawImage(tile[numTile].imagem, telaX, telaY, pj.tamanhoTile, pj.tamanhoTile, null); // Faz com que as tiles apareçam
            }

            mundoCol++;

            if (mundoCol == pj.maxColMundo) {
                mundoCol = 0;
                mundoFil++;
            }
        }
    }
}
