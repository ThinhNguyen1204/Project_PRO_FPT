package Control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ML extends MouseAdapter {
    public boolean isPressed = false;
    public double x= 0.0, y =0.0;

    @Override
    public void mousePressed(MouseEvent e){
        isPressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e){
        isPressed = false;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
    }
}
