package Game.Caro;

import Game.Control.KL;
import Game.Menu.Constant;
import Game.Menu.Scene;
import Game.Menu.State;
import Game.Menu.Window;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class CaroMenu extends Scene {
    private KL key;
    private BufferedImage pointer;
    private int pointerDirection;
    private String title  = "CARO", play = "Play", exit = "Exit";

    public CaroMenu(KL key) {
        this.key = key;
        pointerDirection=1;
        try{
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
            if(pointerDirection ==1) pointerDirection =2;
            else pointerDirection =1;
        }
        if(key.enter){
            key.enter = false;
            if(pointerDirection ==1 ) Window.getWindow().changeState(State.CARO_GAME);
            else Window.getWindow().changeState(State.MAIN_MENU);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0,0, Constant.CARO_MENU_SIZE, Constant.CARO_MENU_SIZE);
        g.setColor(Color.white);
        g.setFont(new Font("Pixel AE", Font.BOLD, 100));
        FontMetrics metrics = g.getFontMetrics();
        g.drawString(title, (Constant.CARO_MENU_SIZE-metrics.stringWidth(title))/2 , 200);
        g.setFont(new Font("Pixel AE", Font.BOLD, 50));
        g.drawString(play, (Constant.CARO_MENU_SIZE-metrics.stringWidth(play))/2 +40, 270);
        g.drawString(exit, (Constant.CARO_MENU_SIZE-metrics.stringWidth(exit))/2 + 40, 340);
        if(pointerDirection==1){
            g.drawImage(pointer,(Constant.CARO_MENU_SIZE-metrics.stringWidth(play))/2 +40 - 60, 230,40,40,null);
        }else {
            g.drawImage(pointer,(Constant.CARO_MENU_SIZE-metrics.stringWidth(play))/2 +40 - 40, 300,40,40,null);
        }

    }
}
