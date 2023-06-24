package src;

import com.convertapi.client.Config;
import com.convertapi.client.ConversionResult;
import com.convertapi.client.ConvertApi;
import com.convertapi.client.Param;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.util.concurrent.CompletableFuture;

public class GUI extends JFrame implements ActionListener {
    private JLabel plainTextLabel;
    private JLabel aesKeyLabel;
    private JLabel encryptedAesKeyLabel;
    private JLabel cipherTextLabel;
    private JLabel publicKeyLabel;
    private JLabel privateKeyLabel;
    private JLabel fileNameLabel;

    private JTextArea plainTestTA;
    private JTextArea aesTA;
    private JTextArea encryptedAesTA;
    private JTextArea cipherTextTA;
    private JTextArea publicKeyTA;
    private JTextArea privateKeyTA;

    private JScrollPane jScrollPanePlainText;
    private JScrollPane jScrollAesKey;
    private JScrollPane jScrollEncryptedAes;
    private JScrollPane jScrollCipherText;
    private JScrollPane jScrollPrivateKey;
    private JScrollPane jScrollPublicKey;

    private JButton encryptBtn;
    private JButton decryptBtn;
    private JButton readFileBtn;
    private JButton chooseFileBtn;
    private JButton clearBtn;
    private JButton generateBtn;

    private File file;

    public GUI() {
        initComponent();
    }

    private void initComponent() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        plainTextLabel = new JLabel("PLAIN TEXT");
        aesKeyLabel = new JLabel("SECRET KEY");
        encryptedAesKeyLabel = new JLabel("ENCRYPTED SECRET KEY");
        cipherTextLabel = new JLabel("CIPHER TEXT");
        privateKeyLabel = new JLabel("PRIVATE KEY");
        publicKeyLabel = new JLabel("PUBLIC KEY");
        fileNameLabel = new JLabel();

        plainTestTA = new JTextArea(15, 30);
        plainTestTA.setLineWrap(true);
        plainTestTA.setWrapStyleWord(true);
        aesTA = new JTextArea(3, 30);
        aesTA.setLineWrap(true);
        aesTA.setWrapStyleWord(true);
        encryptedAesTA = new JTextArea(8, 30);
        encryptedAesTA.setLineWrap(true);
        encryptedAesTA.setWrapStyleWord(true);
        publicKeyTA = new JTextArea(3, 30);
        publicKeyTA.setLineWrap(true);
        publicKeyTA.setWrapStyleWord(true);
        privateKeyTA = new JTextArea(8, 30);
        privateKeyTA.setLineWrap(true);
        privateKeyTA.setWrapStyleWord(true);
        cipherTextTA = new JTextArea(20 , 30);
        cipherTextTA.setLineWrap(true);
        cipherTextTA.setWrapStyleWord(true);

        jScrollPanePlainText = new JScrollPane(plainTestTA);
        jScrollAesKey = new JScrollPane(aesTA);
        jScrollEncryptedAes = new JScrollPane(encryptedAesTA);
        jScrollPrivateKey = new JScrollPane(privateKeyTA);
        jScrollPublicKey = new JScrollPane(publicKeyTA);
        jScrollCipherText = new JScrollPane(cipherTextTA);

        encryptBtn = new JButton("   ENCRYPT    ");
        decryptBtn = new JButton("   DECRYPT    ");
        readFileBtn = new JButton("  READ FILE   ");
        chooseFileBtn = new JButton("CHOOSE FILE");
        clearBtn = new JButton("      CLEAR     ");
        generateBtn = new JButton("GENERATE SECRET KEY");

        SpringLayout layout = new SpringLayout();
        JPanel mainGUI = new JPanel();
        mainGUI.setLayout(layout);

        mainGUI.add(plainTextLabel);
        mainGUI.add(aesKeyLabel);
        mainGUI.add(encryptedAesKeyLabel);
        mainGUI.add(cipherTextLabel);
        mainGUI.add(privateKeyLabel);
        mainGUI.add(publicKeyLabel);
        mainGUI.add(fileNameLabel);

        mainGUI.add(jScrollPanePlainText);
        mainGUI.add(jScrollAesKey);
        mainGUI.add(jScrollEncryptedAes);
        mainGUI.add(jScrollPrivateKey);
        mainGUI.add(jScrollPublicKey);
        mainGUI.add(jScrollCipherText);

        mainGUI.add(encryptBtn);
        mainGUI.add(decryptBtn);
        mainGUI.add(readFileBtn);
        mainGUI.add(chooseFileBtn);
        mainGUI.add(clearBtn);
        mainGUI.add(generateBtn);

        layout.putConstraint(SpringLayout.WEST, plainTextLabel, 135, SpringLayout.WEST, mainGUI);
        layout.putConstraint(SpringLayout.NORTH, plainTextLabel, 25, SpringLayout.NORTH, mainGUI);
        layout.putConstraint(SpringLayout.WEST, aesKeyLabel, 140, SpringLayout.WEST, mainGUI);
        layout.putConstraint(SpringLayout.NORTH, aesKeyLabel, 340, SpringLayout.NORTH, mainGUI);
        layout.putConstraint(SpringLayout.WEST, encryptedAesKeyLabel, 670, SpringLayout.WEST, mainGUI);
        layout.putConstraint(SpringLayout.NORTH, encryptedAesKeyLabel, 25, SpringLayout.NORTH, mainGUI);
        layout.putConstraint(SpringLayout.WEST, cipherTextLabel, 1000, SpringLayout.WEST, mainGUI);
        layout.putConstraint(SpringLayout.NORTH, cipherTextLabel, 25, SpringLayout.NORTH, mainGUI);
        layout.putConstraint(SpringLayout.WEST, publicKeyLabel, 140, SpringLayout.WEST, mainGUI);
        layout.putConstraint(SpringLayout.NORTH, publicKeyLabel, 460, SpringLayout.NORTH, mainGUI);
        layout.putConstraint(SpringLayout.WEST, privateKeyLabel, 695, SpringLayout.WEST, mainGUI);
        layout.putConstraint(SpringLayout.NORTH, privateKeyLabel, 240, SpringLayout.NORTH, mainGUI);
        layout.putConstraint(SpringLayout.WEST, fileNameLabel, 400, SpringLayout.WEST, mainGUI);
        layout.putConstraint(SpringLayout.NORTH, fileNameLabel, 245, SpringLayout.NORTH, mainGUI);

        layout.putConstraint(SpringLayout.WEST, jScrollPanePlainText, 40, SpringLayout.WEST, mainGUI);
        layout.putConstraint(SpringLayout.NORTH, jScrollPanePlainText, 50, SpringLayout.NORTH, mainGUI);
        layout.putConstraint(SpringLayout.WEST, jScrollAesKey, 40, SpringLayout.WEST, mainGUI);
        layout.putConstraint(SpringLayout.NORTH, jScrollAesKey, 360, SpringLayout.NORTH, mainGUI);
        layout.putConstraint(SpringLayout.WEST, jScrollEncryptedAes, 615, SpringLayout.WEST, mainGUI);
        layout.putConstraint(SpringLayout.NORTH, jScrollEncryptedAes, 50, SpringLayout.NORTH, mainGUI);
        layout.putConstraint(SpringLayout.WEST, jScrollCipherText, 905, SpringLayout.WEST, mainGUI);
        layout.putConstraint(SpringLayout.NORTH, jScrollCipherText, 50, SpringLayout.NORTH, mainGUI);
        layout.putConstraint(SpringLayout.WEST, jScrollPrivateKey, 615, SpringLayout.WEST, mainGUI);
        layout.putConstraint(SpringLayout.NORTH, jScrollPrivateKey, 265, SpringLayout.NORTH, mainGUI);
        layout.putConstraint(SpringLayout.WEST, jScrollPublicKey, 40, SpringLayout.WEST, mainGUI);
        layout.putConstraint(SpringLayout.NORTH, jScrollPublicKey, 480, SpringLayout.NORTH, mainGUI);

        layout.putConstraint(SpringLayout.WEST, encryptBtn, 400, SpringLayout.WEST, mainGUI);
        layout.putConstraint(SpringLayout.NORTH, encryptBtn, 70, SpringLayout.NORTH, mainGUI);
        layout.putConstraint(SpringLayout.WEST, decryptBtn, 400, SpringLayout.WEST, mainGUI);
        layout.putConstraint(SpringLayout.NORTH, decryptBtn, 140, SpringLayout.NORTH, mainGUI);
        layout.putConstraint(SpringLayout.WEST, chooseFileBtn, 400, SpringLayout.WEST, mainGUI);
        layout.putConstraint(SpringLayout.NORTH, chooseFileBtn, 210, SpringLayout.NORTH, mainGUI);
        layout.putConstraint(SpringLayout.WEST, readFileBtn, 400, SpringLayout.WEST, mainGUI);
        layout.putConstraint(SpringLayout.NORTH, readFileBtn, 280, SpringLayout.NORTH, mainGUI);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 400, SpringLayout.WEST, mainGUI);
        layout.putConstraint(SpringLayout.NORTH, clearBtn, 350, SpringLayout.NORTH, mainGUI);
        layout.putConstraint(SpringLayout.WEST, generateBtn, 40, SpringLayout.WEST, mainGUI);
        layout.putConstraint(SpringLayout.NORTH, generateBtn, 430, SpringLayout.NORTH, mainGUI);

        this.setSize(1200, 700);
        this.add(mainGUI);
        this.setTitle("Hybrid encryption by Kyel Nguyen");
        this.setLocationRelativeTo(null);
    }

    public void showMessage(String message, String title, int status) {
        JOptionPane.showMessageDialog(this, message, title, status);
    }

    public void setEncrypt() {
        String plainText = plainTestTA.getText();
        String aesKey = aesTA.getText();

        if(plainText == null || "".equals(plainText.trim()) || aesKey == null || "".equals(aesKey.trim())) {
            showMessage("Please enter plain text and AES key to encrypt!", "Warning", 2);
            return;
        }

        String cipherText = AES128.encrypt(plainText, aesKey);
        String encryptedAesKey = RSA.encrypt(aesKey);

        if(cipherText == null || encryptedAesKey == null) {
            return;
        }

        cipherTextTA.setText(cipherText);
        encryptedAesTA.setText(encryptedAesKey);
        privateKeyTA.setText(RSA.getPrivateKey());
        publicKeyTA.setText(RSA.getPublicKey());


        plainTestTA.setText("");
        aesTA.setText("");

        showMessage("Encrypt message successfully!", "Success", 1);

        if(file != null) {
            try {
                File newFile = new File("file_encrypted.txt");
                Files.deleteIfExists(newFile.toPath());
                Files.write(newFile.toPath(), cipherTextTA.getText().getBytes(), StandardOpenOption.CREATE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setDecrypt() {
        String cipherText = cipherTextTA.getText();
        String encryptedAesKey = encryptedAesTA.getText();
        String privateKey = privateKeyTA.getText();

        if(cipherText == null || "".equals(cipherText.trim()) || encryptedAesKey == null || "".equals(encryptedAesKey.trim()) || privateKey == null || "".equals(privateKey.trim())) {
            showMessage("Please enter cipher text, encrypted AES key, private key to decrypt!", "Warning", 2);
            return;
        }

        String aesKey = RSA.decrypt(encryptedAesKey, privateKey);
        String plainText = AES128.decrypt(cipherText, aesKey);

        if(aesKey == null || plainText == null) {
            return;
        }

        aesTA.setText(aesKey);
        plainTestTA.setText(plainText);

        showMessage("Decrypt message successfully!", "Success", 1);

        if(file != null) {
            String fileName = file.getName();
            String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1);
            try {
                if (fileExtension.equals("txt")) {
                    File newFile = new File("file_decrypted.txt");
                    Files.deleteIfExists(newFile.toPath());
                    Files.write(newFile.toPath(), plainTestTA.getText().getBytes(), StandardOpenOption.CREATE);
                } else if (fileExtension.equals("docx")) {
                    File xmlFile = new File("file_docx_converted.xml");
                    Files.write(xmlFile.toPath(), plainTestTA.getText().getBytes(), StandardOpenOption.CREATE);

                    Config.setDefaultSecret("GVcg8cxfJUupzWAL");
                    CompletableFuture<ConversionResult> result = ConvertApi.convert("xml", "docx", new Param("file", xmlFile.toPath()));

                    result.get().saveFile(Paths.get("file_docx_decrypted.docx")).get();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setChooseFile() {
        JFrame frame = new JFrame("File Chooser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();

            String fileName = fileChooser.getSelectedFile().getName();
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);

            if(!fileExtension.equals("txt") && !fileExtension.equals("docx") && !fileExtension.equals("pdf")) {
                showMessage("Please choose file having format .txt, .pdf or .docx", "Warning", 2);
            } else {
                fileNameLabel.setText(fileChooser.getSelectedFile().getName());
            }
        }
    }

    public void setReadFile() {
        String fileName = file.getName();
        String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1);

        if (fileExtension.equals("txt")) {
            try {
                byte[] fileData = Files.readAllBytes(file.toPath());
                plainTestTA.setText(new String(fileData));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (fileExtension.equals("docx")) {
            try {
                File xmlFile = new File("file_docx_converted.xml");

                Config.setDefaultSecret("GVcg8cxfJUupzWAL");
                CompletableFuture<ConversionResult> result = ConvertApi.convert("docx", "xml", new Param("file", file.toPath()));

                result.get().saveFile(xmlFile.toPath()).get();
                byte[] fileData = Files.readAllBytes(xmlFile.toPath());
                plainTestTA.setText(new String(fileData));

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (fileExtension.equals("pdf")){
             try {
                File xmlFile = new File("file_pdf_converted.png");

                Config.setDefaultSecret("GVcg8cxfJUupzWAL");
                CompletableFuture<ConversionResult> result = ConvertApi.convert("pdf", "png", new Param("file", file.toPath()));

                result.get().saveFile(xmlFile.toPath()).get();
                byte[] fileData = Files.readAllBytes(xmlFile.toPath());
                plainTestTA.setText(new String(fileData));
             } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setClear() {
        plainTestTA.setText("");
        aesTA.setText("");
        encryptedAesTA.setText("");
        cipherTextTA.setText("");
        privateKeyTA.setText("");
        fileNameLabel.setText("");
    }

    public void setGenerate() {
        aesTA.setText(AES128.generateSecretKey());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void addEncryptListener(ActionListener listener) {
        encryptBtn.addActionListener(listener);
    }

    public void addDecryptListener(ActionListener listener) {
        decryptBtn.addActionListener(listener);
    }

    public void addClearListener(ActionListener listener) {
        clearBtn.addActionListener(listener);
    }

    public void addChooseFileListener(ActionListener listener) {
        chooseFileBtn.addActionListener(listener);
    }

    public void addReadFileListener(ActionListener listener) {
        readFileBtn.addActionListener(listener);
    }

    public void addGenerateListener(ActionListener listener) {
        generateBtn.addActionListener(listener);
    }
}
