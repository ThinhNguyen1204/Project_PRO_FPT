package Game.TetrisGame;

import Game.Control.KL;
import Game.Menu.Constant;
import Game.Menu.Scene;
import Game.Menu.State;
import Game.Menu.Window;
import Game.TetrisGame.Tetrominos.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TetrisGame extends Scene {
    private KL key;
    private int startX = (Constant.TETRIS_COLS/2*Constant.TETRIS_CELL_SIZE) - Constant.TETRIS_CELL_SIZE + Constant.TETRIS_BOARD_X;
    private int startY = Constant.TETRIS_BOARD_Y + Constant.TETRIS_CELL_SIZE;
    private int startX2 = Constant.TETRIS_BOARD2_X + Constant.TETRIS_BOARD2_SIZE/2 - Constant.TETRIS_CELL_SIZE;
    private int startY2 = Constant.TETRIS_BOARD2_Y + 2*Constant.TETRIS_CELL_SIZE;
    private Shape currentShape, nextShape;
    public static ArrayList<Block>  listBlock;
    public static int score;
    private static boolean isPause;
    private BufferedImage pointer;
    private int pointerDirection;
    private File dataFile = new File("data/tetris.txt");
    private File scoreFile =new File("data/tetrisScore.txt");

    public TetrisGame(KL key) {
        this.key = key;
        listBlock = new ArrayList<>();
        score = 0;
        isPause = false;
        pointerDirection = 1;
        try{
            pointer = ImageIO.read(new File("assets/choose.png"));
        }catch (Exception e){
            e.printStackTrace();
        }

            currentShape = randomShape();
            currentShape.setXY(startX, startY);
            nextShape = randomShape();
            nextShape.setXY(startX2,startY2);

        if(dataFile.length()>0){
            readSave();
        }
    }


    public Shape randomShape(){
        Shape shape = null;
        int rand = new Random().nextInt(7);
        switch (rand){
            case 0 -> shape = new Shape_I();
            case 1 -> shape = new Shape_L();
            case 2 -> shape = new Shape_L2();
            case 3 -> shape = new Shape_O();
            case 4 -> shape = new Shape_S();
            case 5 -> shape = new Shape_T();
            case 6 -> shape = new Shape_Z();

        }
        return shape;
    }

    public Shape loadShape(int index){
        Shape shape = null;
        switch (index){
            case 0 -> shape = new Shape_I();
            case 1 -> shape = new Shape_L();
            case 2 -> shape = new Shape_L2();
            case 3 -> shape = new Shape_O();
            case 4 -> shape = new Shape_S();
            case 5 -> shape = new Shape_T();
            case 6 -> shape = new Shape_Z();

        }
        return shape;
    }

    @Override
    public void update() {
        if(!isPause){
            if(key.esc){
                key.esc=false;
                isPause=true;
            }
            if (key.up){
                key.up = false;
                currentShape.up = true;
            }
            if(key.left){
                key.left = false;
                currentShape.left = true;
            }
            if(key.right){
                key.right = false;
                currentShape.right = true;
            }
            if(key.down){
                key.down = false;
                currentShape.down = true;

            }
            if(currentShape.isActive){
                currentShape.update();
            }else {
                for(Block block : currentShape.blocks){
                    listBlock.add(block);
                }
                checkDeleteLine();
                currentShape = nextShape;
                currentShape.setXY(startX, startY);
                nextShape = randomShape();
                nextShape.setXY(startX2,startY2);
                checkLose(currentShape);
            }
        }
        else{
            if(key.up || key.down){
                key.up = false;
                key.down = false;
                if(pointerDirection==1) pointerDirection=2;
                else  pointerDirection = 1;
            }
            if(key.enter){
                key.enter=false;
                if(pointerDirection==1) isPause=false;
                else{
                    saveGame();
                    Window.getWindow().changeState(State.TETRIS_MENU);
                }
            }
        }
    }


    public void checkDeleteLine(){
        int blockCount = 0;
        int deleteY = Constant.TETRIS_BOARD_Y;
        while ( deleteY < Constant.TETRIS_BOARD_Y + Constant.TETRIS_BOARD_HEIGHT){
            for(Block block : listBlock){
                if(block.y == deleteY) blockCount++;
            }
            if(blockCount == Constant.TETRIS_COLS){
                score+=100;
                for(int i = listBlock.size() - 1 ; i>=0 ; i--){
                    if(listBlock.get(i).y == deleteY) listBlock.remove(i);
                }
                for (Block block : listBlock){
                    if(block.y < deleteY) block.y+=Constant.TETRIS_CELL_SIZE;
                }
            }
            deleteY+=Constant.TETRIS_CELL_SIZE;
            blockCount=0;
        }
    }

    public void checkLose(Shape currentShape){
        for(Block block : currentShape.blocks){
            for (Block blockList : listBlock){
                if(block.x == blockList.x && block.y == blockList.y){
                    try{
                        FileWriter writer = new FileWriter(dataFile);
                        writer.flush();
                        writer.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    saveScore();
                    Window.getWindow().changeState(State.TETRIS_GAME_OVER);
                }
            }
        }
    }
    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,Constant.TETRIS_MENU_WIDTH,Constant.TETRIS_MENU_HEIGHT);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(2f));
        g2.drawRect(Constant.TETRIS_BOARD_X-2,Constant.TETRIS_BOARD_Y-2,Constant.TETRIS_BOARD_WIDTH+4,Constant.TETRIS_BOARD_HEIGHT+4);
        g2.drawRect(Constant.TETRIS_BOARD2_X-2,Constant.TETRIS_BOARD2_Y-2,Constant.TETRIS_BOARD2_SIZE+4,Constant.TETRIS_BOARD2_SIZE+4);
        for(Block block : listBlock){
            block.draw(g);
        }
        currentShape.draw(g);
        nextShape.draw(g);
        g.setFont(new Font("Pixel AE", Font.BOLD, 35));
        g.drawString("Score: "+ Integer.toString(score) , Constant.TETRIS_BOARD2_X , 2*Constant.TETRIS_BOARD2_Y + Constant.TETRIS_BOARD2_SIZE  );
        if(isPause){
            g2.setColor(Color.white);
            g2.drawRect(438,158, 404,404);
            g2.setColor(Color.black);
            g2.fillRect(440,160,400,400);
            g2.setColor(Color.white);
            g.setFont(new Font("Pixel AE", Font.BOLD, 80));
            FontMetrics metrics = g.getFontMetrics();
            g.drawString("PAUSE",(Constant.TETRIS_MENU_WIDTH-metrics.stringWidth("PAUSE"))/2,260);
            g.setFont(new Font("Pixel AE", Font.BOLD, 40));
            metrics = g.getFontMetrics();
            g.drawString("Resume",(Constant.TETRIS_MENU_WIDTH-metrics.stringWidth("Resume"))/2,350);
            g.drawString("Exit",(Constant.TETRIS_MENU_WIDTH-metrics.stringWidth("Exit"))/2,400);
            if(pointerDirection==1){
                g.drawImage(pointer,(Constant.TETRIS_MENU_WIDTH-metrics.stringWidth("Resume"))/2-40,320,30,30,null);
            }else{
                g.drawImage(pointer,(Constant.TETRIS_MENU_WIDTH-metrics.stringWidth("Exit"))/2-40,370,30,30,null);
            }
        }
    }


    public int getShapeIndex(Shape currentShape){
        if(currentShape instanceof Shape_I) return 0;
        else if(currentShape instanceof Shape_L ) return 1;
        else if(currentShape instanceof Shape_L2 ) return 2;
        else if(currentShape instanceof Shape_O ) return 3;
        else if(currentShape instanceof Shape_S ) return 4;
        else if(currentShape instanceof Shape_T ) return 5;
        else if(currentShape instanceof Shape_Z ) return 6;
        return 0;
    }
    public int getColorIndex(Block block){
        if(block.color.equals(Color.CYAN))return 1;
        else if(block.color.equals(Color.ORANGE)) return 2;
        else if(block.color.equals(Color.BLUE)) return 3;
        else if(block.color.equals(Color.yellow)) return 4;
        else if(block.color.equals(Color.PINK)) return 5;
        else if(block.color.equals(new Color(147, 49, 71))) return 6;
        else if(block.color.equals(Color.green)) return 7;
        return  0;
    }

    public Color loadColor(int colorIndex){
        if(colorIndex==1) return Color.CYAN;
        else if(colorIndex == 2) return Color.ORANGE;
        else if(colorIndex == 3) return Color.BLUE;
        else if(colorIndex == 4) return Color.yellow;
        else if(colorIndex == 5) return Color.PINK;
        else if(colorIndex == 6) return new Color(147, 49, 71);
        else if(colorIndex == 7) return Color.green;
        return Color.BLACK;
    }
    public void saveGame(){
        String data="";
        String n = String.format("Velocity %d\n", Shape.velocity );
        data+=n;
        n = String.format("currentShape %d\n", getShapeIndex(currentShape));
        data+=n;
        n = String.format("nextShape %d\n", getShapeIndex(nextShape));
        data+=n;
        n = String.format("score %d\n", score);
        data+=n;
        n = String.format("block %d\n", listBlock.size());
        data+=n;
        for(Block block : listBlock){
            n = String.format("%d %d %d\n", block.x, block.y,getColorIndex(block));
            data+=n;
        }

        try{
            FileWriter writer = new FileWriter(dataFile);
            writer.write(data);
            writer.flush();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void saveScore(){
        String line;
        if(scoreFile.length()==0){
            try {
                FileWriter writer = new FileWriter(scoreFile);
                String n = String.format("%d", score);
                writer.write(n);
                writer.flush();
                writer.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            try {
                Scanner sc = new Scanner(scoreFile);
                line = sc.nextLine();
                line = line.trim();
                int highestScore = Integer.parseInt(line);
                FileWriter writer = new FileWriter(scoreFile);
                if(highestScore<score){
                    String n = String.format("%d",score);
                    writer.write(n);
                }else{
                    String n = String.format("%d",highestScore);
                    writer.write(n);
                }
                writer.flush();
                writer.close();
                sc.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void readSave(){
        String token[];
        String line;
        try{
            Scanner sc = new Scanner(dataFile);
            while(sc.hasNextLine()){
                line = sc.nextLine();
                line= line.trim();
                token = line.split(" ");
                String statement = token[0];
                if(statement.equals("currentShape")){
                    currentShape = loadShape(Integer.parseInt(token[1]));
                    currentShape.setXY(startX,startY);
                }else if(statement.equals("nextShape")){
                    nextShape = loadShape(Integer.parseInt(token[1]));
                    nextShape.setXY(startX2,startY2);
                }else if(statement.equals("score")){
                    score = Integer.parseInt(token[1]);
                }else if(statement.equals("block")){
                    int i = Integer.parseInt(token[1]);
                    for(int j = 0 ; j<i ; j++){
                        line = sc.nextLine();
                        line= line.trim();
                        token = line.split(" ");
                        Block b = new Block(loadColor(Integer.parseInt(token[2])));
                        b.x = Integer.parseInt(token[0]);
                        b.y = Integer.parseInt(token[1]);
                        listBlock.add(b);
                    }
                }else if(statement.equals("Velocity")) {
                    Shape.velocity = Integer.parseInt(token[1]);
                }else{

                }
            }
            sc.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
