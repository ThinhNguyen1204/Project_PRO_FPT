package Game.TetrisGame;

import Game.Menu.Constant;

import java.awt.*;

public class Block {
    public int x,y;
    protected Color color;

    public Block(Color color) {
        this.color = color;
    }

    public void draw(Graphics g){
        g.setColor(color);
        g.fillRect(x,y, Constant.TETRIS_CELL_SIZE,Constant.TETRIS_CELL_SIZE);
    }
}
