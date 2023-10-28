package Game.Control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KL extends KeyAdapter {
    public boolean up, left,down,right;

    @Override
    public void keyPressed(KeyEvent keyEvent){
        int code = keyEvent.getKeyCode();
        if(code == KeyEvent.VK_W){
            up=true;
        }
        if(code == KeyEvent.VK_S){
            down=true;
        }
        if(code == KeyEvent.VK_A){
            left=true;
        }
        if(code == KeyEvent.VK_D){
            right=true;
        }
    }

}
