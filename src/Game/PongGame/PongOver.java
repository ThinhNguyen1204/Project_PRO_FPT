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
import java.util.Scanner;

public class PongOver extends Scene {
    private KL key;
    private String n ="GOOD JOB!", retry  = " Retry", exit = "Exit";
    private File dataFile = new File("data/pong.txt");
    private int highestMinutes, highestSecond;
    private BufferedImage pointer;
    private int pointerDirection;
    public PongOver(KL key) {
        this.key = key;
        pointerDirection = 1;
        try{
            pointer = ImageIO.read(new File("assets/choose.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
        readScore();
    }

    @Override
    public void update() {
        if(key.up || key.down){
            key.up=false;
            key.down=false;
            if(pointerDirection==1) pointerDirection=2;
            else pointerDirection =1;
        }
        if(key.enter){
            key.enter=false;
            if(pointerDirection==1) Window.getWindow().changeState(State.PONG_GAME);
            else Window.getWindow().changeState(State.MAIN_MENU);
        }
    }
    public void readScore(){
        String token[];
        try{
            Scanner sc = new Scanner(dataFile);
            String line = sc.nextLine();
            line.trim();
            token = line.split(" ");
            highestMinutes = Integer.parseInt(token[0]);
            highestSecond = Integer.parseInt(token[1]);

            sc.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0,0, Constant.PONG_MENU_WIDTH,Constant.PONG_MENU_HEIGHT);
        g.setColor(Color.white);
        g.setFont(new Font("Pixel AE",Font.BOLD,100));
        FontMetrics metrics = g.getFontMetrics();
        g.drawString(n,(Constant.PONG_MENU_WIDTH-metrics.stringWidth(n))/2,200);
        g.setFont(new Font("Pixel AE",Font.BOLD,50));
        metrics = g.getFontMetrics();
        String n = String.format("Your highest survival time is: %02d:%02d",highestMinutes,highestSecond);
        g.drawString(n, (Constant.PONG_MENU_WIDTH-metrics.stringWidth(n)) /2 , 280);
        g.drawString(retry, (Constant.PONG_MENU_WIDTH-metrics.stringWidth(retry)) /2 , 350);
        g.drawString(exit, (Constant.PONG_MENU_WIDTH-metrics.stringWidth(exit)) /2 , 430);
        if(pointerDirection==1){
            g.drawImage(pointer,(Constant.PONG_MENU_WIDTH-metrics.stringWidth(retry)) /2-40,310,40,40,null);
        }else{
            g.drawImage(pointer,(Constant.PONG_MENU_WIDTH-metrics.stringWidth(exit)) /2-60,390,40,40,null);
        }
    }
}
