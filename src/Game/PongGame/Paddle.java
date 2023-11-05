package Game.PongGame;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Paddle {
    public int x , y;
    public BufferedImage image;
    public Paddle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g){
        g.drawImage(image,x,y,160,20,null);
    }
}
