package Menu;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class MainMenuScene extends Scene{

    BufferedImage exit,exitPressed,snake, snakePressed, tetris, tetrisPressed,caro,caroPressed;
    BufferedImage currentExit,currentSnake, currentTetris, currentCaro;

    public MainMenuScene(){
        try{
            exit = ImageIO.read(new File("exit.png"));
            exitPressed = ImageIO.read(new File("exitPressed.png"));
            snake = ImageIO.read(new File("snake.png"));
            snakePressed = ImageIO.read(new File("snakePressed.png"));
            tetris = ImageIO.read(new File("tetris.png"));
            tetrisPressed = ImageIO.read(new File("tetrisPressed.png"));
            caro = ImageIO.read(new File("caro.png"));
            caroPressed = ImageIO.read(new File("caroPressed.png"));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {

    }
}
