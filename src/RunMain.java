package src;

import javax.swing.*;
import java.awt.*;

public class RunMain {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run(){

                try {
                    String className = UIManager.getSystemLookAndFeelClassName();
                    UIManager.setLookAndFeel(className);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                GUI gui = new GUI();
                GUIController controller = new GUIController(gui);

                controller.showGUI();
            }
        });
    }
}
