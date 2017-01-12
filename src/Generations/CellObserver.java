/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generations;

/**
 *
 * @author Ahmed
 */
public class CellObserver implements Runnable {

    private volatile Cell[][] cells;

    public CellObserver(Cell[][] cells) {
        this.cells = cells;
    }

    @Override
    public void run() {
        synchronized(cells)  {
            try {
                cells.wait();
            } catch (Exception e) {
            }
        }
    }
}
