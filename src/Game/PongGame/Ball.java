package Game.PongGame;

import java.awt.*;

public class Ball {
    public  int x,y,dx,dy;
    protected boolean isOut = false, fistTouch = true, canTouch;
    int  canTouchCount;
    public Ball(int x, int y, int dx, int dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        canTouch=true;
        canTouchCount=0;
    }
    public void update(){
        x+=dx;
        y+=dy;
        if(x<=0 || x+20>=1000) reverseX();
        if(y<=0 || y>= 600) isOut = true;
        //System.out.println(x+ "   " + y + "  " + dx + "  " + dy );
        if(!canTouch){
            canTouchCount++;
            if(canTouchCount==20){
                canTouch=true;
                canTouchCount = 0;
            }
        }
    }
    public void reverseX(){
        dx*=-1;
    }

    public void reverseY(){
        dy*=-1;
    }
    public void draw(Graphics g){
        g.setColor(Color.white);
        g.fillOval(x,y,20-2,20-2);
    }
}
