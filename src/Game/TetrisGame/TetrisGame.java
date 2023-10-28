package Game.TetrisGame;

import Game.Menu.Constant;
import Game.Menu.Scene;

import java.awt.*;

public class TetrisGame extends Scene {


    public TetrisGame() {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,Constant.TETRIS_MENU_WIDTH,Constant.TETRIS_MENU_HEIGHT);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(2f));
        g2.drawRect(Constant.TETRIS_BOARD_X-2,Constant.TETRIS_BOARD_Y-2,Constant.TETRIS_BOARD_WIDTH+4,Constant.TETRIS_BOARD_HEIGHT+4);
        g2.drawRect(Constant.TETRIS_BOARD2_X-2,Constant.TETRIS_BOARD2_Y-2,Constant.TETRIS_BOARD2_SIZE+4,Constant.TETRIS_BOARD2_SIZE+4);
    }
}
