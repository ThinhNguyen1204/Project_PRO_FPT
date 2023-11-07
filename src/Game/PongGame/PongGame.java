package Game.PongGame;

import Game.Control.KL;
import Game.Menu.Constant;
import Game.Menu.Scene;
import Game.Menu.State;
import Game.Menu.Window;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class PongGame extends Scene {
    private KL key;
    private Ball ball;
    private AIPaddle aiPaddle;
    private PlayerPaddle playerPaddle;
    private int second , minutes;
    private int count;
    private int chance;
    private int velocityCount = 0 ;
    private File dataFile  = new File("data/pong.txt");
    public PongGame(KL key) {
        this.key = key;
        ball = new Ball(500,300, 4,4);
        aiPaddle = new AIPaddle(Constant.PONG_AI_PADDLE_START_X,Constant.PONG_AI_PADDLE_START_Y);
        playerPaddle = new PlayerPaddle(Constant.PONG_PLAYER_PADDLE_START_X,Constant.PONG_PLAYER_PADDLE_START_Y);
        second = 0;
        minutes = 0;
        count = 0;
        chance = 3;
    }

    public void update(){
        count++;
        velocityCount++;
        if(key.isKeyPressed(KeyEvent.VK_D)){
            if(playerPaddle.x+Constant.PONG_PADDLE_WIDTH+20 <= Constant.PONG_MENU_WIDTH){
                playerPaddle.x+=20;
            }
        } else if (key.isKeyPressed(KeyEvent.VK_A)) {
            if(playerPaddle.x -20 >= 0){
                playerPaddle.x-=20;
            }
        }
        ball.update();
        playerPaddle.update(ball);
        aiPaddle.update(ball);
        if(ball.isOut){
            ball=new Ball(500,300, 4,4);
            chance--;
        }
        if(count==60){
            count = 0;
            second++;
            if(second==60){
                minutes++;
                second=0;
            }
        }
        if(chance==0){
            saveHighestScore();
            Window.getWindow().changeState(State.PONG_GAME_OVER);
        }
        if(velocityCount == 1800){
            ball.dx*=2;
            ball.dy*=2;
        }
    }


    public void saveHighestScore(){
        String token[];
        int highestMinutes,highestSecond;
        try{
            FileWriter writer = new FileWriter(dataFile);
            Scanner sc  = new Scanner(dataFile);
            if(dataFile.length()==0){
                String n = String.format("%d %d",minutes,second);
                writer.write(n);
            }else{
                String line = sc.nextLine();
                line = line.trim();
                token = line.split(" ");
                if(Integer.parseInt(token[0]) < minutes){
                    highestMinutes = minutes;
                    highestSecond = second;
                }else if(Integer.parseInt(token[0])>minutes){
                    highestMinutes = Integer.parseInt(token[0]);
                    highestSecond = Integer.parseInt(token[1]);
                }else{
                    if(Integer.parseInt(token[0]) < second){
                        highestMinutes = minutes;
                        highestSecond = second;
                    }else {
                        highestMinutes = Integer.parseInt(token[0]);
                        highestSecond = Integer.parseInt(token[1]);
                    }
                }
                String n = String.format("%d %d",highestMinutes,highestSecond);
                writer.write("");
                writer.write(n);
            }
            sc.close();
            writer.flush();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void draw(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0,0,1000,600);
        g.setColor(Color.white);
        g.drawLine(0,300,1000,300);
        ball.draw(g);
        aiPaddle.draw(g);
        playerPaddle.draw(g);
        g.setColor(Color.white);
        g.setFont(new Font("Pixel AE",Font.BOLD,30));
        String n = String.format("%02d:%02d",minutes,second);
        String k = String.format("Health: %d", chance);
        g.drawString(n,10, 290);
        g.drawString(k,10,340);
    }
}
