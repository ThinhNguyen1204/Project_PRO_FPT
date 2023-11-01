package Game.TetrisGame;

import Game.Control.KL;
import Game.Menu.Constant;
import Game.Menu.Scene;
import Game.Menu.State;
import Game.Menu.Window;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class TetrisOver extends Scene {
    private KL key;
    private BufferedImage pointer;
    private int pointerDirection = 1;
    public TetrisOver(KL key) {
        this.key = key;
        try{
            pointer = ImageIO.read(new File("assets/choose.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        if(key.up || key.down){
            key.up = false;
            key.down = false;
            switch (this.pointerDirection){
                case 1 -> pointerDirection = 2;
                case 2 -> pointerDirection = 1;
            }
        }
        if(key.enter){
            key.enter = false;
            if(pointerDirection==1) Window.getWindow().changeState(State.TETRIS_GAME);
            else Window.getWindow().changeState(State.MAIN_MENU);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,Constant.TETRIS_MENU_WIDTH, Constant.TETRIS_MENU_HEIGHT);
        g.setColor(Color.white);
        g.setFont(new Font("Pixel AE", Font.BOLD, 100));
        FontMetrics metrics = g.getFontMetrics();
        g.drawString("GAME OVER", (Constant.TETRIS_MENU_WIDTH - metrics.stringWidth("GAME OVER"))/2, Constant.TETRIS_MENU_HEIGHT/3);
        g.setFont(new Font("Pixel AE", Font.BOLD, 50));
        metrics = g.getFontMetrics();
        g.drawString("RETRY", (Constant.TETRIS_MENU_WIDTH - metrics.stringWidth("RETRY"))/2, Constant.TETRIS_MENU_HEIGHT/3+100);
        g.drawString("EXIT", (Constant.TETRIS_MENU_WIDTH - metrics.stringWidth("EXIT"))/2, Constant.TETRIS_MENU_HEIGHT/3 + 200);
        if(pointerDirection==1){
            g.drawImage(pointer,(Constant.TETRIS_MENU_WIDTH - metrics.stringWidth("RETRY"))/2 - metrics.stringWidth("RETRY")/2,Constant.TETRIS_MENU_HEIGHT/3+100 - 40, 40,40,null );
        }else{
            g.drawImage(pointer,(Constant.TETRIS_MENU_WIDTH - metrics.stringWidth("EXIT"))/2 - metrics.stringWidth("EXIT")/2,Constant.TETRIS_MENU_HEIGHT/3+200 - 40, 40,40,null );
        }
    }
}
