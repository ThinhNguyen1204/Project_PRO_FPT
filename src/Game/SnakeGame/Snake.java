package Game.SnakeGame;

import Game.Menu.Constant;
import Game.Menu.State;
import Game.Menu.Window;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Snake {
    public final int[] x = new  int[Constant.SNAKE_ROWS*Constant.SNAKE_COLS];
    public final int[] y = new  int[Constant.SNAKE_ROWS*Constant.SNAKE_COLS];
    protected int length;
    private SnakeDirection direction = SnakeDirection.RIGHT;
    private BufferedImage headUp,headDown,headLeft,headRight, bodyImage;
    private BufferedImage currentHead;
    private File dataFile = new File("data/snake.txt");

    public Snake(int length, int startX,int startY) {
        this.length=length;
        for (int i = length; i >=0; i--) {
            x[i]= startX-i*Constant.SNAKE_CELL_SIZE;
            y[i]= startY;
        }
        try {
            headUp = ImageIO.read((new File("assets/headUp.png")));
            headDown = ImageIO.read((new File("assets/headDown.png")));
            headLeft = ImageIO.read((new File("assets/headLeft.png")));
            headRight = ImageIO.read((new File("assets/headRight.png")));
            bodyImage = ImageIO.read((new File("assets/snakeBody.png")));
        }catch (Exception e){
            e.printStackTrace();
        }
        currentHead = headRight;
    }

    public void update(){
        for (int i = length; i > 0 ; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        switch (direction){
            case RIGHT -> x[0] = x[0] + Constant.SNAKE_CELL_SIZE;

            case LEFT -> x[0] = x[0] - Constant.SNAKE_CELL_SIZE;

            case UP -> y[0] = y[0] - Constant.SNAKE_CELL_SIZE;

            case DOWN -> y[0] = y[0] + Constant.SNAKE_CELL_SIZE;
        }
        if(checkCollision()){
            saveHighestScore();
            Window.getWindow().changeState(State.SNAKE_GAME_OVER);
        }
    }

    public void saveHighestScore(){
        try{
            Scanner sc  = new Scanner(dataFile);
            if(dataFile.length()==0){
                FileWriter writer = new FileWriter("data/snake.txt");
                String n = Food.score;
                writer.write(n);
                writer.flush();
                writer.close();
            }else {
                String line = sc.nextLine();
                line = line.trim();
                int highestScore = Integer.parseInt(line);
                int newScore = Integer.parseInt(Food.score);
                FileWriter writer = new FileWriter("data/snake.txt");
                if(newScore>highestScore){
                    String n = Integer.toString(newScore);
                    writer.write(n);
                }else {
                    String n = Integer.toString(highestScore);
                    writer.write(n);
                }
                writer.flush();
                writer.close();
            }
            sc.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void changeSnakeDirection(SnakeDirection newSnakeDirection){
        if(newSnakeDirection==SnakeDirection.RIGHT && direction!=SnakeDirection.LEFT) {
            direction = newSnakeDirection;
            currentHead = headRight;
        }else if(newSnakeDirection == SnakeDirection.LEFT && direction!=SnakeDirection.RIGHT){
            direction = newSnakeDirection;
            currentHead = headLeft;
        }else if(newSnakeDirection == SnakeDirection.UP && direction!=SnakeDirection.DOWN){
            direction = newSnakeDirection;
            currentHead = headUp;
        }else if(newSnakeDirection == SnakeDirection.DOWN && direction!=SnakeDirection.UP){
            direction = newSnakeDirection;
            currentHead = headDown;
        }
    }

    public boolean checkCollision(){
        for (int i = length; i >0 ; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                return true;
            }
        }
        return x[0] < Constant.SNAKE_FOREGROUND_X || x[0] >= Constant.SNAKE_FOREGROUND_X + Constant.SNAKE_FOREGROUND_WIDTH
                || y[0] < Constant.SNAKE_FOREGROUND_Y || y[0] >=Constant.SNAKE_FOREGROUND_Y + Constant.SNAKE_FOREGROUND_HEIGHT;

    }

    public void draw(Graphics g){
        for (int i = 0; i < length; i++) {
            if(i==0){
                g.drawImage(currentHead,x[i],y[i],Constant.SNAKE_CELL_SIZE,Constant.SNAKE_CELL_SIZE,null);
            }
            else {
                g.drawImage(bodyImage,x[i],y[i],Constant.SNAKE_CELL_SIZE,Constant.SNAKE_CELL_SIZE,null);
            }
        }
    }
}
