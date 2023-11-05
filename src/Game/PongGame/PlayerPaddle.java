package Game.PongGame;

import javax.imageio.ImageIO;
import java.io.File;

public class PlayerPaddle  extends Paddle {

    public PlayerPaddle(int x, int y) {
        super(x, y);
        try{
            image = ImageIO.read(new File("assets/Player.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void update(Ball ball){
        if(ball.canTouch){
            if(ball.x+20 > x && ball.x < x+160 && ball.y+20 == y){
                if(ball.x < x+80){
                    ball.canTouch = false;
                    if(ball.dx>0){
                        if(ball.fistTouch){
                            ball.dx+=4;
                            ball.dy+=4;
                            ball.fistTouch=false;
                        }
                        ball.reverseX();
                    }
                    else{
                        if(ball.fistTouch){
                            ball.dx -= 4;
                            ball.dy += 4;
                            ball.fistTouch = false;
                        }
                    }
                }else if( ball.x < x+160) {
                    if(ball.dx<0){
                        if(ball.fistTouch){
                            ball.dx-=4;
                            ball.dy+=4;
                            ball.fistTouch=false;
                        }
                        ball.reverseX();
                    }
                    else {
                        if (ball.fistTouch) {
                            ball.dx += 4;
                            ball.dy += 4;
                            ball.fistTouch = false;
                        }
                    }
                }
                ball.reverseY();
            } else if (ball.x + 20 == x && ball.y + 20 == y) {
                if(ball.dx>0){
                    ball.reverseX();
                }
                ball.reverseY();
            } else if (ball.x == x + 160 && ball.y+20 == y){
                if(ball.dx<0){
                    ball.reverseX();
                }
                ball.reverseY();
            }
        }

    }
}
