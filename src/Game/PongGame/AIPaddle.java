package Game.PongGame;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.Random;

public class AIPaddle extends Paddle {
    private Random random = new Random();
    public AIPaddle(int x, int y) {
        super(x, y);
        try{
            image = ImageIO.read(new File("assets/Computer.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update(Ball ball){
        if(ball.x+20 < x ){
            if(x-20>=0){
                x-=20;
            }
        }else if (ball.x > x + 160 ){
            if(x+180 <= 1000){
                x+=20;
            }
        }
        checkCollision(ball);

    }
    public void checkCollision(Ball ball){
        if(ball.canTouch){
            if(ball.x+20 > x && ball.x < x+160 && ball.y -4 == y + 20){
                if(ball.x < x+80){
                    ball.canTouch=false;
                    if(ball.dx>0){
                        if(ball.fistTouch){
                            ball.dx+=4;
                            ball.dy-=4;
                            ball.fistTouch=false;
                        }
                        ball.reverseX();
                    }
                    else{
                        if(ball.fistTouch){
                            ball.dx -= 12;
                            ball.dy -= 12;
                            ball.fistTouch = false;
                        }
                    }
                }else if( ball.x < x+160) {
                    if(ball.dx<0){
                        if(ball.fistTouch){
                            ball.dx-=4;
                            ball.dy-=4;
                            ball.fistTouch=false;
                        }
                        ball.reverseX();
                    }
                    else {
                        if (ball.fistTouch) {
                            ball.dx += 4;
                            ball.dy -= 4;
                            ball.fistTouch = false;
                        }
                    }
                }
                if(random.nextInt(1,5)==3) ball.reverseX();
                ball.reverseY();
            } else if (ball.x + 20 == x && ball.y -4 == y+20) {
                if(ball.dx>0){
                    ball.reverseX();
                }
                if(random.nextInt(1,5)==3) ball.reverseX();
                ball.reverseY();
            } else if (ball.x == x + 160 && ball.y+ -4  == y+20){
                if(ball.dx<0){
                    ball.reverseX();
                }
                if(random.nextInt(1,5)==3) ball.reverseX();
                ball.reverseY();
            }
        }

    }
}
