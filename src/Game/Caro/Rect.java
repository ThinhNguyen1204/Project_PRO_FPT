package Game.Caro;

import Game.Menu.Constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Rect {
    public  int x=0,y=0;
    public  int isPlayed = 0;
    BufferedImage xImage;
    BufferedImage oImage;

    public Rect(){
        try{
            xImage = ImageIO.read(new File("assets/X.png"));
            oImage = ImageIO.read(new File("assets/O.png"));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void draw(Graphics g){
        if(isPlayed == 1){
            g.drawImage(xImage,x,y, Constant.CARO_CELL_SIZE,Constant.CARO_CELL_SIZE,null);
        }else if(isPlayed == 2) {
            g.drawImage(oImage,x,y,Constant.CARO_CELL_SIZE,Constant.CARO_CELL_SIZE,null);
        }
    }
}
