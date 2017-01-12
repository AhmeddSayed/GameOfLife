/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generations;

import field.GameJPanel;
import java.awt.Point;
import java.util.ArrayDeque;

/**
 *
 * @author Ahmed
 */
public class CellObserver implements Runnable {

    private final Cell[][] cells;
    private boolean stopped = false;

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }
    private final GameJPanel gamePanel;

    public CellObserver(Cell[][] cells, GameJPanel gamePanel) {
        this.cells = cells;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run() {
        while (!stopped) {
            synchronized (gamePanel) {

                if (!gamePanel.isGameOn()) {
                    try {
                        gamePanel.wait();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace(System.out);
                    }
                } else {
                    try {
                        int i = 0;
                        while (gamePanel.isGameOn() && i <= 10) {
                            Thread.sleep(20);
                            i++;
                        }
                    } catch (InterruptedException ex) {
                        ex.printStackTrace(System.out);
                    }

                    calculateLife(cells);

                }
            }
        }
    }

    private void calculateLife(Cell[][] cells) {

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                int liveNeighboursCount = countLiveNeighbours(cells, i, j);

                switch (liveNeighboursCount) {
                    case 0:
                    case 1:
                        // die
                        cells[i][j].die();
                        break;
                    case 2:
                        // continue to live
                        break;
                    case 3:
                        // live
                        if (!cells[i][j].isAlive()) {
                            cells[i][j].toggle();
                        }
                        break;
                    default:
                        // die
                        cells[i][j].die();
                        break;
                }
                gamePanel.repaint();
            }
        }
    }

    private int countLiveNeighbours(Cell[][] cells, int iPosition, int jPosition) {
        int liveCount = 0;
        /*
        j   j   j
    i   0   1   2
    i   3   4   5
    i   6   7   8
         */

        for (int i = 0; i <= 8; i++) {
            Point neighbourIndex = null;
            switch (i) {
                case 0:
                    neighbourIndex = new Point(iPosition - 1, jPosition - 1);
                    break;
                case 1:
                    neighbourIndex = new Point(iPosition, jPosition - 1);
                    break;
                case 2:
                    neighbourIndex = new Point(iPosition + 1, jPosition - 1);
                    break;
                case 3:
                    neighbourIndex = new Point(iPosition - 1, jPosition);
                    break;
                case 4:
                    // skip, current element
                    break;
                case 5:
                    neighbourIndex = new Point(iPosition + 1, jPosition);
                    break;
                case 6:
                    neighbourIndex = new Point(iPosition - 1, jPosition + 1);
                    break;
                case 7:
                    neighbourIndex = new Point(iPosition, jPosition + 1);
                    break;
                case 8:
                    neighbourIndex = new Point(iPosition + 1, jPosition + 1);
                    break;
            }

            if (neighbourIndex != null) {
                if (neighbourIndex.x >= 0 && neighbourIndex.x < cells.length) {
                    if (neighbourIndex.y >= 0 && neighbourIndex.y < cells.length) {
                        if (cells[neighbourIndex.x][neighbourIndex.y].isAlive()) {
                            liveCount++;
                        }
                    }
                }
            }

        }

        return liveCount;
    }
}
