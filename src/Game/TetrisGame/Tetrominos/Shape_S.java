package Game.TetrisGame.Tetrominos;

import Game.Menu.Constant;
import Game.TetrisGame.Shape;

import java.awt.*;

public class Shape_S extends Shape{
    public Shape_S() {
        create(Color.PINK);
    }

    @Override
    public void setXY(int x, int y) {
        //o
        //o O
        //  o
        blocks[0].x = x;
        blocks[0].y = y;
        blocks[1].x = x;
        blocks[1].y = y + Constant.TETRIS_CELL_SIZE;
        blocks[2].x = x - Constant.TETRIS_CELL_SIZE;
        blocks[2].y = y;
        blocks[3].x = x - Constant.TETRIS_CELL_SIZE;
        blocks[3].y = y - Constant.TETRIS_CELL_SIZE;
    }

    @Override
    public void getDirection1() {
        //o
        //o O
        //  o
        tmpBlocks[0].x = blocks[0].x;
        tmpBlocks[0].y = blocks[0].y;
        tmpBlocks[1].x = blocks[0].x;
        tmpBlocks[1].y = blocks[0].y + Constant.TETRIS_CELL_SIZE;
        tmpBlocks[2].x = blocks[0].x - Constant.TETRIS_CELL_SIZE;
        tmpBlocks[2].y = blocks[0].y;
        tmpBlocks[3].x = blocks[0].x - Constant.TETRIS_CELL_SIZE;
        tmpBlocks[3].y = blocks[0].y - Constant.TETRIS_CELL_SIZE;
        updateXY(1);
    }

    @Override
    public void getDirection2() {
        //  o o
        //o O
        tmpBlocks[0].x = blocks[0].x;
        tmpBlocks[0].y = blocks[0].y;
        tmpBlocks[1].x = blocks[0].x;
        tmpBlocks[1].y = blocks[0].y - Constant.TETRIS_CELL_SIZE;
        tmpBlocks[2].x = blocks[0].x - Constant.TETRIS_CELL_SIZE;
        tmpBlocks[2].y = blocks[0].y;
        tmpBlocks[3].x = blocks[0].x + Constant.TETRIS_CELL_SIZE;
        tmpBlocks[3].y = blocks[0].y - Constant.TETRIS_CELL_SIZE;
        updateXY(2);
    }

    @Override
    public void getDirection3() {
        getDirection1();
    }

    @Override
    public void getDirection4() {
        getDirection2();
    }
}
