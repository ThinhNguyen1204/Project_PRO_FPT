package Game.SnakeGame;

import Game.Control.ML;
import Game.Menu.Constant;
import Game.Menu.RectImage;
import Game.Menu.Scene;
import Game.Menu.State;
import Game.Menu.Window;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class SnakeMenu extends Scene {
    private BufferedImage title, play, playPressed, exit, exitPressed;
    private BufferedImage currentPlay, currentExit;
    private RectImage playRect, exitRect;
    private ML mouse;

    public SnakeMenu(ML mouse) {
        this.mouse = mouse;
        try{
            title = ImageIO.read(new File("assets/snake.png"));
            play = ImageIO.read(new File("assets/play.png"));
            playPressed = ImageIO.read(new File("assets/playPressed.png"));
            exit = ImageIO.read(new File("assets/exit.png"));
            exitPressed = ImageIO.read(new File("assets/exitPressed.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
        currentExit = exit;
        currentPlay = play;
        playRect = new RectImage(300,240,200,100);
        exitRect = new RectImage(325,360,150,75);
    }

    @Override
    public void update() {
        //System.out.println(mouse.x+"   "+ mouse.y);
        if(mouse.x >= playRect.x && mouse.x <= playRect.x + playRect.width
        && mouse.y >= playRect.y && mouse.y <= playRect.y + playRect.height){
            currentPlay = playPressed;
            if(mouse.isPressed){
                Window.getWindow().changeState(State.SNAKE_GAME);
            }
        }else {
            currentPlay = play;
        }

        if(mouse.x >= exitRect.x && mouse.x <= exitRect.x + exitRect.width
                && mouse.y >= exitRect.y && mouse.y <= exitRect.y + exitRect.height){
            currentExit = exitPressed;
            if(mouse.isPressed){
                mouse.isPressed=false;
                Window.getWindow().changeState(State.MAIN_MENU);
            }
        }else {
            currentExit = exit;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(5, 84, 15));
        g.fillRect(0,0, Constant.SNAKE_MENU_WIDTH, Constant.SNAKE_MENU_HEIGHT);
        g.drawImage(title,200,20,400,200,null);
        g.drawImage(currentPlay,300,240,200,100,null);
        g.drawImage(currentExit,325,360,150,75,null);
    }
}
