/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generations;

import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author Ahmed
 */
public class Cell extends JPanel {

    private boolean isAlive;

    public Cell(boolean isAlive, String cellname) {
        this.isAlive = isAlive;
        this.setName(cellname);

        if (isAlive) {
            this.setBackground(Color.GREEN);
        } else {
            this.setBackground(Color.BLACK);
        }

    }

    public void die() {
        this.isAlive = false;
        this.setBackground(Color.BLACK);

    }

    public boolean getHealth() {
        return this.isAlive;
    }

    public void revive() {
        this.isAlive = true;
        this.setBackground(Color.YELLOW);
    }
}
