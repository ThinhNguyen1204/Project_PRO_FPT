package Game.TetrisGame;

import Game.Control.ML;
import Game.Menu.Window;
import Game.Menu.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class TetrisMenu extends Scene {

    private ML mouse;
    private BufferedImage title, play, playPressed, exit, exitPressed;
    private BufferedImage currentPlay, currentExit;
    private RectImage playRect, exitRect;



    public TetrisMenu(ML mouse) {
        this.mouse = mouse;
        try{
            title = ImageIO.read(new File("assets/tetrisInGame.png"));
            play = ImageIO.read(new File("assets/play.png"));
            playPressed = ImageIO.read(new File("assets/playPressed.png"));
            exit = ImageIO.read(new File("assets/exit.png"));
            exitPressed = ImageIO.read(new File("assets/exitPressed.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
        currentExit = exit;
        currentPlay = play;
        playRect = new RectImage(540,300,200,100);
        exitRect = new RectImage(565,425,150,75);
    }

    @Override
    public void update() {
        if(mouse.x>=playRect.x && mouse.x<=playRect.x+playRect.width && mouse.y >= playRect.y && mouse.y <= playRect.y + playRect.height){
            currentPlay = playPressed;
            if(mouse.isPressed){
                Window.getWindow().changeState(State.TETRIS_GAME);
            }
        }else {
            currentPlay = play;
        }

        if(mouse.x>=exitRect.x && mouse.x<=exitRect.x+exitRect.width && mouse.y >= exitRect.y && mouse.y <= exitRect.y + exitRect.height){
            currentExit = exitPressed;
            if(mouse.isPressed){
                Window.getWindow().close();
            }
        }else {
            currentExit = exit;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0, Constant.TETRIS_MENU_WIDTH, Constant.TETRIS_MENU_HEIGHT);
        g.drawImage(title,390,50,500,200,null);
        g.drawImage(currentPlay,540,300,200,100,null);
        g.drawImage(currentExit,565,425,150,75,null);
    }
}
