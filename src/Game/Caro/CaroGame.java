package Game.Caro;

import Game.Control.ML;
import Game.Menu.Constant;
import Game.Menu.Scene;
import Game.Menu.State;
import Game.Menu.Window;

import java.awt.*;

public class CaroGame extends Scene {
    private ML mouse;
    private Player player = Player.PLAYER_X;

    Rect[][] rects;
    public CaroGame(ML mouse) {
        rects = new Rect[Constant.CARO_BLOCKS][Constant.CARO_BLOCKS];
        for (int i = 0; i < Constant.CARO_BLOCKS; i++) {
            for (int j = 0; j < Constant.CARO_BLOCKS; j++) {
                rects[i][j] = new Rect();
            }
        }
        this.mouse = mouse;
        rects[0][0].x = 50;
        rects[0][0].y = 60;
        for (int i = 1; i < Constant.CARO_BLOCKS ; i++){
            for (int j = 1; j < Constant.CARO_BLOCKS; j++) {
                rects[i][j].x = rects[0][0].x + j*Constant.CARO_CELL_SIZE;
                rects[i][j].y = rects[0][0].y + i*Constant.CARO_CELL_SIZE;
            }
        }
    }

    @Override
    public void update() {
        //System.out.println(mouse.x + " "+ mouse.y);
        if(mouse.isPressed){
            for (int i = 0; i < Constant.CARO_BLOCKS; i++) {
                for (int j = 0; j < Constant.CARO_BLOCKS; j++) {
                    if(mouse.x >= rects[i][j].x && mouse.x <= rects[i][j].x + Constant.CARO_CELL_SIZE
                    && mouse.y >= rects[i][j].y && mouse.y <= rects[i][j].y + Constant.CARO_CELL_SIZE
                    && rects[i][j].isPlayed == 0){
                        mouse.isPressed = false;
                        if(player == Player.PLAYER_X){
                            rects[i][j].isPlayed=1;
                            checkWin(i,j,1);
                        }
                        else{
                            rects[i][j].isPlayed=2;
                            checkWin(i,j,2);
                        }
                        changePlayer();
                        break;
                    }
                }
            }
        }
    }

    public void checkWin(int i, int j, int isPlay){
       if ( countRows(i,j,isPlay) || countCols(i,j,isPlay)
               || countRightDiagonal(i,j,isPlay) || countLeftDiagonal(i,j,isPlay)){
           CaroEnd.winPlayer = isPlay;
           Window.getWindow().changeState(State.CARO_END_GAME);
       }

    }

    public boolean countRows(int i, int j , int isPlay){
        int count = 1;
        for (int k =1; k<=4; k++){
            if(rects[i][j+k].isPlayed == isPlay) count++;
            else break;
        }
        for (int k = 1; k <= 4; k++) {
            if(rects[i][j-k].isPlayed == isPlay) count++;
            else break;
        }
        if(count>=5)return true;
        return false;
    }

    public boolean countCols(int i, int j , int isPlay){
        int count = 1;
        for (int k =1; k<=4; k++){
            if(rects[i+k][j].isPlayed == isPlay) count++;
            else break;
        }
        for (int k = 1; k <= 4; k++) {
            if(rects[i-k][j].isPlayed == isPlay) count++;
            else break;
        }
        if(count>=5)return true;
        return false;
    }

    public boolean countRightDiagonal(int i, int j , int isPlay){
        int count = 1;
        for (int k = 1; k <= 4; k++){
            if(rects[i-k][j-k].isPlayed == isPlay) count++;
            else break;
        }
        for (int k = 1; k <= 4; k++) {
            if(rects[i+k][j+k].isPlayed == isPlay) count++;
            else break;
        }
        if(count>=5)return true;
        return false;
    }

    public boolean countLeftDiagonal(int i, int j , int isPlay){
        int count = 1;
        for (int k = 1; k <= 4; k++){
            if(rects[i-k][j+k].isPlayed == isPlay) count++;
            else break;
        }
        for (int k = 1; k <= 4; k++) {
            if(rects[i+k][j-k].isPlayed == isPlay) count++;
            else break;
        }
        if(count>=5)return true;
        return false;
    }


    public void changePlayer(){
        if(player== Player.PLAYER_O)player = Player.PLAYER_X;
        else player = Player.PLAYER_O;
    }
    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,Constant.CARO_MENU_SIZE,Constant.CARO_MENU_SIZE);
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(2));
        g2.drawRect(48,58,Constant.CARO_BOARD_SIZE+4,Constant.CARO_BOARD_SIZE+4);
        g.setColor(Color.GRAY);
        for (int i = 50 + Constant.CARO_CELL_SIZE; i <= 50+Constant.CARO_BOARD_SIZE - Constant.CARO_CELL_SIZE ; i+=Constant.CARO_CELL_SIZE) {
            g.drawLine(i,60,i,60+Constant.CARO_BOARD_SIZE);
        }
        for (int i = 60 + Constant.CARO_CELL_SIZE; i <= 60 + Constant.CARO_BOARD_SIZE - Constant.CARO_CELL_SIZE ; i+=Constant.CARO_CELL_SIZE) {
            g.drawLine(50,i,50+Constant.CARO_BOARD_SIZE,i);
        }
        g.setColor(Color.white);
        g.setFont(new Font("Pixel AE", Font.BOLD, 20));
        g.drawString("Player"+ (player== Player.PLAYER_X ? " 1" : " 2") , 260,50);
        for (int i = 0; i < Constant.CARO_BLOCKS; i++) {
            for (int j = 0; j < Constant.CARO_BLOCKS; j++) {
                rects[i][j].draw(g);
            }
        }
    }
}
