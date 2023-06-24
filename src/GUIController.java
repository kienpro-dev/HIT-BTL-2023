package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIController {
    private final GUI gui;

    public GUIController(GUI gui) {
        this.gui = gui;
        gui.addEncryptListener(new EncryptListener());
        gui.addDecryptListener(new DecryptListener());
        gui.addClearListener(new ClearListener());
        gui.addChooseFileListener(new ChooseFileListener());
        gui.addReadFileListener(new ReadFileListener());
        gui.addGenerateListener(new GenerateListener());
    }

    public void showGUI() {
        gui.setVisible(true);
    }

    class EncryptListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            gui.setEncrypt();
        }
    }

    class DecryptListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            gui.setDecrypt();
        }
    }

    class ClearListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            gui.setClear();
        }
    }

    class ChooseFileListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            gui.setChooseFile();
        }
    }

    class ReadFileListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            gui.setReadFile();
        }
    }

    class GenerateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            gui.setGenerate();
        }
    }
}
