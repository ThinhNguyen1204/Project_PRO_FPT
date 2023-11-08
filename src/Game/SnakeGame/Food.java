package Game.SnakeGame;

import Game.Menu.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class Food {
    int x,y;
    private  Snake snake;
    public   int foodEaten;
    public static String score;
    BufferedImage scoreImage;
    BufferedImage foodImage;
    private final Random rand = new Random();

    public Food(Snake snake){
        this.snake = snake;
        foodEaten=-1;
        try {
            scoreImage = ImageIO.read(new File("assets/Score.png"));
            foodImage = ImageIO.read(new File("assets/food.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(snake.x[0]==x && snake.y[0] == y){
            snake.length++;
            newFood();
        }
    }

    public void newFood() {
        x = rand.nextInt(Constant.SNAKE_COLS) * Constant.SNAKE_CELL_SIZE+Constant.SNAKE_FOREGROUND_X;
        y = rand.nextInt(Constant.SNAKE_ROWS) * Constant.SNAKE_CELL_SIZE+Constant.SNAKE_FOREGROUND_Y;
        foodEaten++;
        for (int i = 0; i < snake.length; i++) {
            if(x!=snake.x[i] && y!=snake.y[i]){
                score = Integer.toString(foodEaten);
            }else {
                foodEaten--;
                newFood();
            }
        }
        //System.out.println("food" + x + " " +y);
        //System.out.println(foodEaten);
    }

    public void draw(Graphics g){
        g.drawImage(foodImage,x,y,Constant.SNAKE_CELL_SIZE,Constant.SNAKE_CELL_SIZE,null);
        g.drawImage(scoreImage,10,30,100,50,null);
        g.setColor(Color.BLUE);
        g.setFont(new Font("Pixel AE", Font.BOLD, 35));
        g.drawString(score,105,75);
    }
}
