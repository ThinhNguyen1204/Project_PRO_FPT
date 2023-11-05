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

public class CaroEnd extends Scene {
    public static int winPlayer;
    private KL key;
    private BufferedImage pointer;
    private int pointerDirection;
    private String retry = "Retry" , exit = "Exit";

    public CaroEnd(KL key) {
        this.key = key;
        pointerDirection =1;
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
            if(pointerDirection ==1 ) Game.Menu.Window.getWindow().changeState(State.CARO_GAME);
            else Window.getWindow().changeState(State.MAIN_MENU);
        }

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,Constant.CARO_MENU_SIZE,Constant.CARO_MENU_SIZE);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Pixel AE", Font.BOLD, 60));
        g.drawString("Player"+ (winPlayer==1 ? " X" : " O") + " Win" , 120,200);
        g.setFont(new Font("Pixel AE", Font.BOLD, 40));
        FontMetrics metrics = g.getFontMetrics();
        g.drawString(retry,(Constant.CARO_MENU_SIZE-metrics.stringWidth(retry))/2 ,280 );
        g.drawString(exit,(Constant.CARO_MENU_SIZE-metrics.stringWidth(exit))/2 ,330 );
        if(pointerDirection==1){
            g.drawImage(pointer,(Constant.CARO_MENU_SIZE-metrics.stringWidth(retry))/2 - 50, 250,30,30,null);
        }else {
            g.drawImage(pointer,(Constant.CARO_MENU_SIZE-metrics.stringWidth(exit))/2 - 50, 300,30,30,null);
        }
    }
}
