package com.hourglass.game.src.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControleTeclado implements KeyListener {
    //O KeyListener registra o pressionamento de alguma tecla do teclado e pode ser usado para tomar ações a partir disso

    public boolean upPress, downPress, leftPress, rightPress, shiftPress, mutePress;
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode(); //Retorna o valor do KeyCode associado com a tecla nesse evento

        if (key == KeyEvent.VK_W) {
            upPress = true;
        }
        if (key == KeyEvent.VK_A) {
            leftPress = true;
        }
        if (key == KeyEvent.VK_S) {
            downPress = true;
        }
        if (key == KeyEvent.VK_D) {
            rightPress = true;
        }
        if (key == KeyEvent.VK_SHIFT) {
            shiftPress = true;
        }
        if (key == KeyEvent.VK_M) {
            mutePress = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode(); //Retorna o valor do KeyCode associado com a tecla nesse evento

        if (key == KeyEvent.VK_W) {
            upPress = false;
        }
        if (key == KeyEvent.VK_A) {
            leftPress = false;
        }
        if (key == KeyEvent.VK_S) {
            downPress = false;
        }
        if (key == KeyEvent.VK_D) {
            rightPress = false;
        }
        if (key == KeyEvent.VK_SHIFT) {
            shiftPress = false;
        }
        if (key == KeyEvent.VK_M) {
            mutePress = false;
        }
    }
}