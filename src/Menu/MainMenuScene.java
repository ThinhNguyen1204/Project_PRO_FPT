package Menu;

import Control.ML;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class MainMenuScene extends Scene{

    BufferedImage exit,exitPressed,snake, snakePressed, tetris, tetrisPressed,caro,caroPressed;
    BufferedImage currentExit,currentSnake, currentTetris, currentCaro;
    RectImage exitRect, snakeRect, tetrisRect, caroRect;
    private ML mouse;

    public MainMenuScene(ML mouse){
        this.mouse = mouse;
        try{
            exit = ImageIO.read(new File("assets/exit.png"));
            exitPressed = ImageIO.read(new File("assets/exitPressed.png"));
            snake = ImageIO.read(new File("assets/snake.png"));
            snakePressed = ImageIO.read(new File("assets/snakePressed.png"));
            tetris = ImageIO.read(new File("assets/tetris.png"));
            tetrisPressed = ImageIO.read(new File("assets/tetrisPressed.png"));
            caro = ImageIO.read(new File("assets/caro.png"));
            caroPressed = ImageIO.read(new File("assets/caroPressed.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
        currentCaro = caro;
        currentExit = exit;
        currentSnake = snake;
        currentTetris = tetris;
        exitRect = new RectImage(590,600,100,50);
        caroRect = new RectImage(226,100,300,150);
        snakeRect = new RectImage(752,100,300,150);
        tetrisRect = new RectImage(226,350,300,150);
    }

    @Override
    public void update() {
        if(mouse.x >= caroRect.x && mouse.x <= caroRect.x+caroRect.width
        && mouse.y>= caroRect.y && mouse.y <= caroRect.y + caroRect.height ){
            currentCaro = caroPressed;
            
        }else {
            currentCaro = caro;
        }

        if(mouse.x >= snakeRect.x && mouse.x <= snakeRect.x+snakeRect.width
                && mouse.y>= snakeRect.y && mouse.y <= snakeRect.y + snakeRect.height ){
            currentSnake = snakePressed;

        }else {
            currentSnake = snake;
        }

        if(mouse.x >= tetrisRect.x && mouse.x <= tetrisRect.x+tetrisRect.width
                && mouse.y>= tetrisRect.y && mouse.y <= tetrisRect.y + tetrisRect.height ){
            currentTetris = tetrisPressed;

        }else {
            currentTetris = tetris;
        }

        if(mouse.x >= exitRect.x && mouse.x <= exitRect.x+exitRect.width
                && mouse.y>= exitRect.y && mouse.y <= exitRect.y + exitRect.height ){
            currentExit = exitPressed;
            if(mouse.isPressed) Window.getWindow().close();
        }else {
            currentExit = exit;
        }
        
        
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(32, 54, 180, 255));
        g.fillRect(0,0,Constant.MAIN_MENU_WIDTH,Constant.MAIN_MENU_HEIGHT);
        g.drawImage(currentExit,590,600,100,50,null);
        g.drawImage(currentCaro,226,100,300,150,null);
        g.drawImage(currentSnake,752,100,300,150,null);
        g.drawImage(currentTetris,226,350,300,150,null);
    }
}
