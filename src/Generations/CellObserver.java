/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generations;

import field.GameJPanel;

/**
 *
 * @author Ahmed
 */
public class CellObserver implements Runnable {

    private final Cell[][] cells;
    private final GameJPanel gamePanel;

    public CellObserver(Cell[][] cells, GameJPanel gamePanel) {
        this.cells = cells;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (gamePanel) {

                if (!gamePanel.isGameOn()) {
                    try {
                        gamePanel.wait();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace(System.out);
                    }
                } else {
                    for (Cell[] cellArray : cells) {
                        for (Cell aCell : cellArray) {
                            aCell.toggle();
                            gamePanel.repaint();
                        }
                    }

                    try {
                        int i = 0;
                        while (gamePanel.isGameOn() && i <= 50) {
                            Thread.sleep(20);
                            i++;
                        }
                    } catch (InterruptedException ex) {
                        ex.printStackTrace(System.out);
                    }
                }
            }
        }
    }
}
