package src;

import javax.crypto.Cipher;
import javax.swing.*;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class RSA {
    private static PublicKey publicKey;
    private static PrivateKey privateKey;

    public static void generateKeyPair() {
        try {
            SecureRandom secureRandom = new SecureRandom();
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1024, secureRandom);

            KeyPair keyPair = keyPairGenerator.genKeyPair();

            publicKey = keyPair.getPublic();
            privateKey = keyPair.getPrivate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String plainText) {
        try {
            generateKeyPair();
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptText = cipher.doFinal(plainText.getBytes());

            return Base64.getEncoder().encodeToString(encryptText);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public static String decrypt(String cipherText, String myPrivateKey) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(myPrivateKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, keyFactory.generatePrivate(keySpec));
            byte[] decryptText = cipher.doFinal(Base64.getDecoder().decode(cipherText));

            return new String(decryptText);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public static String getPrivateKey() {
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    public static String getPublicKey() {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }
}
