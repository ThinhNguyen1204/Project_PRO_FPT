package Game.SnakeGame;

import Game.Control.KL;
import Game.Menu.Constant;
import Game.Menu.RectImage;
import Game.Menu.Scene;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class SnakeGame extends Scene {
    private KL key;
    private RectImage foreGround;
    private BufferedImage background,backgroundLeft,backgroundRight;
    private BufferedImage backgroundShadowDown,backgroundShadowLeft,backgroundShadowRight,backgroundShadowUp;
    public SnakeGame(KL key) {
        this.key = key;
        foreGround = new RectImage(Constant.SNAKE_FOREGROUND_X,Constant.SNAKE_FOREGROUND_Y, Constant.SNAKE_FOREGROUND_WIDTH, Constant.SNAKE_FOREGROUND_HEIGHT);
        try{
            background = ImageIO.read(new File("assets/snakeBackground.png"));
            backgroundLeft = ImageIO.read(new File("assets/snakeBackgroundLeft.png"));
            backgroundRight = ImageIO.read(new File("assets/snakeBackgroundRight.png"));
            backgroundShadowDown = ImageIO.read(new File("assets/backgroundShadowDown.png"));
            backgroundShadowLeft = ImageIO.read(new File("assets/backgroundShadowLeft.png"));
            backgroundShadowRight = ImageIO.read(new File("assets/backgroundShadowRight.png"));
            backgroundShadowUp = ImageIO.read(new File("assets/backgroundShadowUp.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(Constant.SNAKE_FOREGROUND_X,Constant.SNAKE_FOREGROUND_Y, Constant.SNAKE_FOREGROUND_WIDTH, Constant.SNAKE_FOREGROUND_HEIGHT);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < Constant.MAIN_MENU_WIDTH/Constant.SNAKE_CELL_SIZE; j++) {
                g.drawImage(background,j*Constant.SNAKE_CELL_SIZE,i*Constant.SNAKE_CELL_SIZE,
                        Constant.SNAKE_CELL_SIZE,Constant.SNAKE_CELL_SIZE,null);
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < Constant.MAIN_MENU_WIDTH / Constant.SNAKE_CELL_SIZE; j++) {
                g.drawImage(background,j*Constant.SNAKE_CELL_SIZE,Constant.SNAKE_MENU_HEIGHT-20-i*Constant.SNAKE_CELL_SIZE,
                        Constant.SNAKE_CELL_SIZE,Constant.SNAKE_CELL_SIZE,null);
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < Constant.SNAKE_ROWS+2; j++) {
                g.drawImage(backgroundLeft,i*Constant.SNAKE_CELL_SIZE,80+j*Constant.SNAKE_CELL_SIZE,
                        Constant.SNAKE_CELL_SIZE,Constant.SNAKE_CELL_SIZE,null);
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < Constant.SNAKE_ROWS+2; j++) {
                g.drawImage(backgroundRight,780-i*Constant.SNAKE_CELL_SIZE,80+j*Constant.SNAKE_CELL_SIZE,
                        Constant.SNAKE_CELL_SIZE,Constant.SNAKE_CELL_SIZE,null);
            }
        }
        g.drawImage(background,Constant.SNAKE_FOREGROUND_X-Constant.SNAKE_CELL_SIZE,
                Constant.SNAKE_FOREGROUND_Y-Constant.SNAKE_CELL_SIZE, Constant.SNAKE_CELL_SIZE,Constant.SNAKE_CELL_SIZE,null);


        g.drawImage(background,Constant.SNAKE_FOREGROUND_X-Constant.SNAKE_CELL_SIZE,
                Constant.SNAKE_FOREGROUND_Y-Constant.SNAKE_CELL_SIZE+Constant.SNAKE_FOREGROUND_HEIGHT+Constant.SNAKE_CELL_SIZE,
                Constant.SNAKE_CELL_SIZE,Constant.SNAKE_CELL_SIZE,null);


        g.drawImage(background,Constant.SNAKE_FOREGROUND_X-Constant.SNAKE_CELL_SIZE+Constant.SNAKE_FOREGROUND_WIDTH+Constant.SNAKE_CELL_SIZE,
                Constant.SNAKE_FOREGROUND_Y-Constant.SNAKE_CELL_SIZE,
                Constant.SNAKE_CELL_SIZE,Constant.SNAKE_CELL_SIZE,null);


        g.drawImage(background,Constant.SNAKE_FOREGROUND_X-Constant.SNAKE_CELL_SIZE+Constant.SNAKE_FOREGROUND_WIDTH+Constant.SNAKE_CELL_SIZE,
                Constant.SNAKE_FOREGROUND_Y-Constant.SNAKE_CELL_SIZE+Constant.SNAKE_FOREGROUND_HEIGHT+Constant.SNAKE_CELL_SIZE,
                Constant.SNAKE_CELL_SIZE,Constant.SNAKE_CELL_SIZE,null);

        for (int i = 0; i < Constant.SNAKE_COLS; i++) {
            g.drawImage(backgroundShadowDown,Constant.SNAKE_FOREGROUND_X+i*Constant.SNAKE_CELL_SIZE,
                    Constant.SNAKE_FOREGROUND_Y - Constant.SNAKE_CELL_SIZE,Constant.SNAKE_CELL_SIZE,Constant.SNAKE_CELL_SIZE,null);
        }

        for (int i = 0; i < Constant.SNAKE_COLS; i++) {
            g.drawImage(backgroundShadowUp,Constant.SNAKE_FOREGROUND_X+i*Constant.SNAKE_CELL_SIZE,
                    Constant.SNAKE_FOREGROUND_Y + Constant.SNAKE_FOREGROUND_HEIGHT,Constant.SNAKE_CELL_SIZE,Constant.SNAKE_CELL_SIZE,null);
        }

        for (int i = 0; i < Constant.SNAKE_ROWS; i++) {
            g.drawImage(backgroundShadowRight,Constant.SNAKE_FOREGROUND_X-Constant.SNAKE_CELL_SIZE,
                    Constant.SNAKE_FOREGROUND_Y+i*Constant.SNAKE_CELL_SIZE,Constant.SNAKE_CELL_SIZE,Constant.SNAKE_CELL_SIZE,null);
        }

        for (int i = 0; i < Constant.SNAKE_ROWS; i++) {
            g.drawImage(backgroundShadowLeft,Constant.SNAKE_FOREGROUND_X+Constant.SNAKE_FOREGROUND_WIDTH,
                    Constant.SNAKE_FOREGROUND_Y+i*Constant.SNAKE_CELL_SIZE,Constant.SNAKE_CELL_SIZE,Constant.SNAKE_CELL_SIZE,null);
        }
    }
}
