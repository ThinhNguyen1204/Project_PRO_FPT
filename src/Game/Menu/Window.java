package Game.Menu;

import Game.Caro.CaroEnd;
import Game.Caro.CaroGame;
import Game.Caro.CaroMenu;
import Game.Control.KL;
import Game.Control.ML;
import Game.PongGame.PongGame;
import Game.PongGame.PongMenu;
import Game.PongGame.PongOver;
import Game.SnakeGame.SnakeGame;
import Game.SnakeGame.SnakeMenu;
import Game.SnakeGame.SnakeOver;
import Game.TetrisGame.TetrisGame;
import Game.TetrisGame.TetrisMenu;
import Game.TetrisGame.TetrisOver;

import javax.swing.*;
import java.awt.*;

public class Window extends JPanel implements Runnable {
    private boolean isRunning;
    private int FPS = 60;
    private static Window window;
    private State currentState;
    private Scene currentScene;
    private ML mouse = new ML();
    private KL key = new KL();


    public Window(int width, int height) {
        setPreferredSize(new Dimension(width,height));
        addKeyListener(key);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        setFocusable(true);
        isRunning = true;
        changeState(State.MAIN_MENU);

    }

    public static Window getWindow(){
        if(window==null){
            window = new Window(Constant.MAIN_MENU_WIDTH, Constant.MAIN_MENU_HEIGHT);
        }
        return window;
    }

    public void changeState(State newState){
        currentState = newState;
        switch (currentState){
            case MAIN_MENU -> {
                FPS =60;
                currentScene = new MainMenuScene(mouse);
                this.setPreferredSize(new Dimension(Constant.MAIN_MENU_WIDTH,Constant.MAIN_MENU_HEIGHT));
                Main.frame.pack();
                Main.frame.setLocationRelativeTo(null);
            }
            case SNAKE_MENU -> {
                FPS = 12;
                currentScene = new SnakeMenu(mouse);
                Main.frame.setSize(Constant.MAIN_MENU_WIDTH, Constant.MAIN_MENU_HEIGHT);
                this.setPreferredSize(new Dimension(Constant.SNAKE_MENU_WIDTH,Constant.SNAKE_MENU_HEIGHT));
                this.revalidate();
                Main.frame.pack();
                Main.frame.setLocationRelativeTo(null);
            }
            case SNAKE_GAME -> {
                currentScene = new SnakeGame(key);
            }
            case SNAKE_GAME_OVER -> {
                currentScene=null;
                currentScene = new SnakeOver(mouse);
            }
            case TETRIS_MENU -> {
                currentScene = new TetrisMenu(mouse);
                Main.frame.setSize(Constant.MAIN_MENU_WIDTH, Constant.MAIN_MENU_HEIGHT);
                this.setPreferredSize(new Dimension(Constant.TETRIS_MENU_WIDTH,Constant.TETRIS_MENU_HEIGHT));
                this.revalidate();
                Main.frame.pack();
                Main.frame.setLocationRelativeTo(null);
            }
            case TETRIS_GAME -> {
                currentScene = new TetrisGame(key);
            }
            case TETRIS_GAME_OVER -> {
                currentScene = new TetrisOver(key);
            }
            case PONG_MENU -> {
                currentScene = new PongMenu(key);
                Main.frame.setSize(Constant.MAIN_MENU_WIDTH, Constant.MAIN_MENU_HEIGHT);
                this.setPreferredSize(new Dimension(Constant.PONG_MENU_WIDTH, Constant.PONG_MENU_HEIGHT));
                this.revalidate();
                Main.frame.pack();
                Main.frame.setLocationRelativeTo(null);
            }
            case PONG_GAME -> {
                currentScene = new PongGame(key);
            }
            case PONG_GAME_OVER -> {
                currentScene = new PongOver(key);
            }
            case CARO_MENU -> {
                currentScene = new CaroMenu(key);
                Main.frame.setSize(Constant.MAIN_MENU_WIDTH, Constant.MAIN_MENU_HEIGHT);
                this.setPreferredSize(new Dimension(Constant.CARO_MENU_SIZE, Constant.CARO_MENU_SIZE));
                this.revalidate();
                Main.frame.pack();
                Main.frame.setLocationRelativeTo(null);
            }
            case CARO_GAME -> {
                currentScene = new CaroGame(mouse);
            }
            case CARO_END_GAME -> {
                currentScene = new CaroEnd(key);
            }
        }

    }

    public void start(){
        Thread thread = new Thread(this);
        thread.start();
    }

    public void close(){
        isRunning = false;
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double deltaTime = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        try{
            while (isRunning){
                drawInterval = 1000000000/FPS;
                currentTime =System.nanoTime();
                deltaTime += (currentTime-lastTime) /drawInterval;
                lastTime = currentTime;
                if (deltaTime >= 1){
                    currentScene.update();
                    repaint();
                    //System.out.println(deltaTime);
                    deltaTime--;
                }
                //Thread.sleep(40);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.exit(0);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        currentScene.draw(g);
    }
}
