package Game.TetrisGame.Tetrominos;

import Game.Menu.Constant;
import Game.TetrisGame.Shape;

import java.awt.*;

public class Shape_O extends Shape {

    public Shape_O() {
        create(Color.yellow);
    }

    @Override
    public void setXY(int x, int y) {
        //O o
        //o o
        blocks[0].x = x;
        blocks[0].y = y;
        blocks[1].x = x + Constant.TETRIS_CELL_SIZE;
        blocks[1].y = y;
        blocks[2].x = x;
        blocks[2].y = y + Constant.TETRIS_CELL_SIZE;
        blocks[3].x = x + Constant.TETRIS_CELL_SIZE;
        blocks[3].y = y + Constant.TETRIS_CELL_SIZE;
    }

    @Override
    public void getDirection1() {
        tmpBlocks[0].x = blocks[0].x;
        tmpBlocks[0].y = blocks[0].y;
        tmpBlocks[1].x = blocks[0].x + Constant.TETRIS_CELL_SIZE;
        tmpBlocks[1].y = blocks[0].y;
        tmpBlocks[2].x = blocks[0].x;
        tmpBlocks[2].y = blocks[0].y + Constant.TETRIS_CELL_SIZE;
        tmpBlocks[3].x = blocks[0].x + Constant.TETRIS_CELL_SIZE;
        tmpBlocks[3].y = blocks[0].y + Constant.TETRIS_CELL_SIZE;
        updateXY(1);
    }

    @Override
    public void getDirection2() {
        getDirection1();
    }

    @Override
    public void getDirection3() {

    }

    @Override
    public void getDirection4() {

    }
}
