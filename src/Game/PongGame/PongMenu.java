package Game.PongGame;

import Game.Control.KL;
import Game.Menu.Constant;
import Game.Menu.Scene;
import Game.Menu.State;
import Game.Menu.Window;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class PongMenu extends Scene {
    private KL key;
    private BufferedImage pong,pointer;
    private int pointerDirection;

    private String play = "Play", exit = "Exit";

    public PongMenu(KL key) {
        pointerDirection = 1;
        this.key = key;
        try{
            pong = ImageIO.read(new File("assets/Pong.png"));
            pointer = ImageIO.read(new File("assets/choose.png"));
        }catch (Exception e){
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
            if(pointerDirection==1) Game.Menu.Window.getWindow().changeState(State.PONG_GAME);
            else Window.getWindow().changeState(State.MAIN_MENU);
        }

    }



    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0, Constant.PONG_MENU_WIDTH,Constant.PONG_MENU_HEIGHT);
        g.drawImage(pong ,(Constant.PONG_MENU_WIDTH-400)/2,50, 400, 200,null);
        g.setColor(Color.white);
        g.setFont(new Font("Pixel AE", Font.BOLD, 50));
        FontMetrics metrics = g.getFontMetrics();
        g.drawString(play, (Constant.PONG_MENU_WIDTH-metrics.stringWidth(play)) /2 , 300);
        g.drawString(exit, (Constant.PONG_MENU_WIDTH-metrics.stringWidth(exit)) /2 , 400);
        if(pointerDirection==1){
            g.drawImage(pointer,(Constant.PONG_MENU_WIDTH-metrics.stringWidth(play)) /2 - 50, 300 - 40,40,40,null);
        }else{
            g.drawImage(pointer,(Constant.PONG_MENU_WIDTH-metrics.stringWidth(exit)) /2 - 50, 400 - 40,40,40,null);
        }
    }

}
