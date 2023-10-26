package Menu;

import javax.swing.*;

public class Main extends JFrame {
    public  static JFrame frame;
    public static void main(String[] args) {
        frame = new JFrame("Project_PRO_SE1801_Group1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Window window = Window.getWindow();
        frame.add(window);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        window.start();
    }

}
