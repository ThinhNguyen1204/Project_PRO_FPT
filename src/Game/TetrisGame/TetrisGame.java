package Game.TetrisGame;

import Game.Control.KL;
import Game.Menu.Constant;
import Game.Menu.Scene;
import Game.Menu.State;
import Game.Menu.Window;
import Game.TetrisGame.Tetrominos.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class TetrisGame extends Scene {
    private KL key;
    private int startX = (Constant.TETRIS_COLS/2*Constant.TETRIS_CELL_SIZE) - Constant.TETRIS_CELL_SIZE + Constant.TETRIS_BOARD_X;
    private int startY = Constant.TETRIS_BOARD_Y + Constant.TETRIS_CELL_SIZE;
    private int startX2 = Constant.TETRIS_BOARD2_X + Constant.TETRIS_BOARD2_SIZE/2 - Constant.TETRIS_CELL_SIZE;
    private int startY2 = Constant.TETRIS_BOARD2_Y + 2*Constant.TETRIS_CELL_SIZE;
    private Shape currentShape, nextShape;
    public static ArrayList<Block>  listBlock;
    public static int score;

    public TetrisGame(KL key) {
        this.key = key;
        listBlock = new ArrayList<>();
        score = 0;
        currentShape = randomShape();
        currentShape.setXY(startX, startY);
        nextShape = randomShape();
        nextShape.setXY(startX2,startY2);
    }


    public Shape randomShape(){
        Shape shape = null;
        int rand = new Random().nextInt(7);
        switch (rand){
            case 0 -> shape = new Shape_I();
            case 1 -> shape = new Shape_L();
            case 2 -> shape = new Shape_L2();
            case 3 -> shape = new Shape_O();
            case 4 -> shape = new Shape_S();
            case 5 -> shape = new Shape_T();
            case 6 -> shape = new Shape_Z();

        }
        return shape;
    }

    @Override
    public void update() {
        if (key.up){
            key.up = false;
            currentShape.up = true;
        }
        if(key.left){
            key.left = false;
            currentShape.left = true;
        }
        if(key.right){
            key.right = false;
            currentShape.right = true;
        }
        if(key.down){
            key.down = false;
            currentShape.down = true;

        }
        if(currentShape.isActive){
            currentShape.update();
        }else {
            for(Block block : currentShape.blocks){
                listBlock.add(block);
            }
            checkDeleteLine();
            currentShape = nextShape;
            currentShape.setXY(startX, startY);
            nextShape = randomShape();
            nextShape.setXY(startX2,startY2);
            checkLose(currentShape);
        }
    }


    public void checkDeleteLine(){
        int blockCount = 0;
        int deleteY = Constant.TETRIS_BOARD_Y;
        while ( deleteY < Constant.TETRIS_BOARD_Y + Constant.TETRIS_BOARD_HEIGHT){
            for(Block block : listBlock){
                if(block.y == deleteY) blockCount++;
            }
            if(blockCount == Constant.TETRIS_COLS){
                score+=100;
                for(int i = listBlock.size() - 1 ; i>=0 ; i--){
                    if(listBlock.get(i).y == deleteY) listBlock.remove(i);
                }
                for (Block block : listBlock){
                    if(block.y < deleteY) block.y+=Constant.TETRIS_CELL_SIZE;
                }
            }
            deleteY+=Constant.TETRIS_CELL_SIZE;
            blockCount=0;
        }
    }

    public void checkLose(Shape currentShape){
        for(Block block : currentShape.blocks){
            for (Block blockList : listBlock){
                if(block.x == blockList.x && block.y == blockList.y){
                    Window.getWindow().changeState(State.TETRIS_GAME_OVER);
                }
            }
        }
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
        for(Block block : listBlock){
            block.draw(g);
        }
        currentShape.draw(g);
        nextShape.draw(g);
        g.setFont(new Font("Pixel AE", Font.BOLD, 35));
        g.drawString("Score: "+ Integer.toString(score) , Constant.TETRIS_BOARD2_X , 2*Constant.TETRIS_BOARD2_Y + Constant.TETRIS_BOARD2_SIZE  );
    }
}
