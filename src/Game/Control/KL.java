package Game.Control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KL extends KeyAdapter {
    public boolean up, left,down,right,enter,esc;
    private boolean[] keyPressed = new boolean[120];

    @Override
    public void keyPressed(KeyEvent keyEvent){
        int code = keyEvent.getKeyCode();
        keyPressed[code]=true;
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
        if(code == KeyEvent.VK_ENTER){
            enter=true;
        }
        if(code == KeyEvent.VK_ESCAPE){
            esc=true;
        }
    }

    public void keyReleased(KeyEvent keyEvent){
        keyPressed[keyEvent.getKeyCode()]=false;
    }

    public boolean isKeyPressed(int keycode){
        return keyPressed[keycode];
    }

}
