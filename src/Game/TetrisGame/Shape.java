package Game.TetrisGame;

import Game.Menu.Constant;

import java.awt.*;

public class Shape {
    protected Block[] blocks = new Block[4];
    protected Block[] tmpBlocks = new Block[4];

    private boolean bottomCollision, leftCollision, rightCollision;
    private int dropCounter = 0 ;
    public boolean isActive = true;
    private boolean deactivating;
    private int deactivateCount = 0;
    private int direction = 1;
    private int velocity = 30, velocityCount = 0;

    public boolean up,left,right,down;

    public void create(Color c){
        for (int i = 0; i < 4; i++) {
            blocks[i] = new Block(c);
        }
        for (int i = 0; i < 4; i++) {
            tmpBlocks[i] = new Block(c);
        }
    }

    public void setXY(int x, int y){};
    public void getDirection1(){};
    public void getDirection2(){};
    public void getDirection3(){};
    public void getDirection4(){};

    private void checkMovementCollision(){
        bottomCollision = false;
        leftCollision = false;
        rightCollision = false;
        checkBlockCollision();
        for(Block block : blocks){
            if(block.y+Constant.TETRIS_CELL_SIZE==Constant.TETRIS_BOARD_Y+Constant.TETRIS_BOARD_HEIGHT){
                bottomCollision = true;
            }
        }
        for(Block block : blocks){
            if(block.x == Constant.TETRIS_BOARD_X){
                leftCollision = true;
            }
        }
        for(Block block : blocks){
            if(block.x + Constant.TETRIS_CELL_SIZE == Constant.TETRIS_BOARD_X + Constant.TETRIS_BOARD_WIDTH){
                rightCollision = true;
            }
        }
    }

    public void updateXY(int direction){
        this.direction = direction;
        checkRotationCollision();
        if(!leftCollision && !rightCollision && !bottomCollision){
            for(int i = 0 ; i<4; i++){
                blocks[i].x = tmpBlocks[i].x;
                blocks[i].y = tmpBlocks[i].y;
            }
        }

    }

    public void checkRotationCollision(){
        bottomCollision = false;
        leftCollision = false;
        rightCollision = false;
        checkBlockCollision();
        for(Block block : tmpBlocks){
            if(block.x < Constant.TETRIS_BOARD_X) leftCollision = true;
            if(block.x + Constant.TETRIS_CELL_SIZE > Constant.TETRIS_BOARD_X+ Constant.TETRIS_BOARD_WIDTH) rightCollision = true;
            if(block.y + Constant.TETRIS_CELL_SIZE > Constant.TETRIS_BOARD_Y + Constant.TETRIS_BOARD_HEIGHT) bottomCollision = true;
        }
    }

    public void deactivating(){
        deactivateCount++;
        if(deactivateCount==15){
            deactivateCount = 0;
            checkMovementCollision();
            if(bottomCollision){
                isActive = false;
            }
        }
    }

    public void checkBlockCollision(){
        for(Block block : TetrisGame.listBlock){
            for(Block thisBlock : blocks){
                if(thisBlock.x == block.x && thisBlock.y + Constant.TETRIS_CELL_SIZE == block.y) bottomCollision =true;
                if(thisBlock.x == block.x + Constant.TETRIS_CELL_SIZE && thisBlock.y == block.y) leftCollision = true;
                if(thisBlock.x + Constant.TETRIS_CELL_SIZE == block.x && thisBlock.y == block.y) rightCollision = true;
            }
        }
    }
    public void update(){
        velocityCount++;
        if(deactivating){
            deactivating();
        }
        checkMovementCollision();
        if(up){
            up = false;
            switch (this.direction){
                case 1 -> getDirection2();
                case 2 -> getDirection3();
                case 3 -> getDirection4();
                case 4 -> getDirection1();
            }
        }
        if(left){
            left = false;
            if(!leftCollision){
                for(Block block : blocks){
                    block.x -= Constant.TETRIS_CELL_SIZE;
                }
            }

        }
        if(right){
            right =false;
            if(!rightCollision){
                for(Block block : blocks){
                    block.x += Constant.TETRIS_CELL_SIZE;
                }
            }

        }
        if(down){
            down = false;
            if(!bottomCollision){
                TetrisGame.score += 2;
                for (Block block : blocks){
                    block.y += Constant.TETRIS_CELL_SIZE;
                }
                dropCounter=0;
            }
        }
        if(bottomCollision){
            deactivating = true;
        }else{
            dropCounter++;
            if(dropCounter == velocity){
                for(Block block : blocks){
                    block.y += Constant.TETRIS_CELL_SIZE;
                }
                dropCounter=0;
            }
        }
        if(velocityCount==1800){
            velocityCount=0;
            velocity -= 1;
        }
    }
    public void draw(Graphics g){
        for (int i = 0; i < 4; i++) {
            blocks[i].draw(g);
        }
    }
}
