package Game.Control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KL extends KeyAdapter {
    private boolean[] keyPressed = new boolean[120];

    @Override
    public void keyPressed(KeyEvent keyEvent){
        keyPressed[keyEvent.getKeyCode()]=true;
    }
    @Override
    public void keyReleased(KeyEvent keyEvent){
        keyPressed[keyEvent.getKeyCode()]=false;
    }

    public boolean isKeyPressed(int keycode){
        return keyPressed[keycode];
    }
}
