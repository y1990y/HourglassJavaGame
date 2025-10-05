package main;

import java.awt.*;

public class UI {

    PainelJogo pj;
    Font arial_40;

    public UI(PainelJogo pj) {
        this.pj = pj;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
    }

    public void draw(Graphics2D g2d) {

        g2d.setFont(arial_40);
        g2d.setColor(Color.white);
        g2d.drawString("Chaves: "+ pj.player.temChave, 50, 50);
    }
}
