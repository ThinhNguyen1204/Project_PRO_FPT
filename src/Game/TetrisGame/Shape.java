package Game.TetrisGame;

import java.awt.*;

public abstract class Shape {
    protected Block[] blocks = new Block[4];
    protected Block[] tmpBlocks = new Block[4];

    public void create(Color c){
        for (int i = 0; i < 4; i++) {
            blocks[i] = new Block(c);
        }
        for (int i = 0; i < 4; i++) {
            tmpBlocks[i] = new Block(c);
        }
    }

    public abstract void setXY(int x, int y);
    public abstract void getDirection1();
    public abstract void getDirection2();
    public abstract void getDirection3();
    public abstract void getDirection4();


    public void draw(Graphics g){
        for (int i = 0; i < 4; i++) {
            blocks[i].draw(g);
        }
    }
}
