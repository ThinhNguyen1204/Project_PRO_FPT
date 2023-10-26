package Game.SnakeGame;

import Game.Control.ML;
import Game.Menu.*;
import Game.Menu.Window;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class SnakeOver extends Scene {
    private ML mouse;
    private BufferedImage over, retry,retryPressed,exit,exitPressed;
    private BufferedImage currentRetry,currentExit;
    private RectImage retryRect, exitRect;
    public SnakeOver(ML mouse) {
        this.mouse = mouse;
        try {
            over = ImageIO.read(new File("assets/gameOver.png"));
            retry = ImageIO.read(new File("assets/retry.png"));
            retryPressed = ImageIO.read(new File("assets/retryPressed.png"));
            exit = ImageIO.read(new File("assets/exit.png"));
            exitPressed = ImageIO.read(new File("assets/exitPressed.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
        currentExit = exit;
        currentRetry = retry;
        retryRect = new RectImage(300,310, 200,100);
        exitRect = new RectImage(325,440, 150,75);
    }

    @Override
    public void update() {
        if(mouse.x >= retryRect.x && mouse.x <= retryRect.x + retryRect.width
        && mouse.y >= retryRect.y && mouse.y <= retryRect.y + retryRect.height){
            currentRetry = retryPressed;
            if(mouse.isPressed){
                Window.getWindow().changeState(State.SNAKE_GAME);
            }
        }else {
            currentRetry = retry;
        }
        
        
        if(mouse.x >= exitRect.x && mouse.x <= exitRect.x + exitRect.width
                && mouse.y >= exitRect.y && mouse.y <= exitRect.y + exitRect.height){
            currentExit = exitPressed;
            if(mouse.isPressed){
                Window.getWindow().changeState(State.MAIN_MENU);
            }
        }else {
            currentExit = exit;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(147, 49, 71));
        g.fillRect(0,0, Constant.SNAKE_MENU_WIDTH,Constant.SNAKE_MENU_HEIGHT);
        g.drawImage(over,225,30, 350,250,null);
        g.drawImage(currentRetry,300,310, 200,100,null);
        g.drawImage(currentExit,325,440, 150,75,null);
    }
}
