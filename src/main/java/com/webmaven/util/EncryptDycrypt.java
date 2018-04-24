package com.webmaven.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 * This example program shows how AES encryption and decryption can be done in Java.
 * Please note that secret key and encrypted text is unreadable binary and hence 
 * in the following program we display it in hexadecimal format of the underlying bytes.
 */
public class EncryptDycrypt {
 
	public static final String SKey = "4F309D7EAEA1EA16D2E2A55F1B6E4E6F";
	
    /**
     * 1. Generate a plain text for encryption
     * 2. Get a secret key (printed in hexadecimal form). In actual use this must 
     * by encrypted and kept safe. The same key is required for decryption.
     * 3. 
     */
    public static void main(String[] args) throws Exception {
        String plainText = "suvigya";
        
        String secretKeyHex = getHexSecretEncryptionKey();
        System.out.println("SecretKeyHex:"+BmsConstants.AES_KEY);
        
        String encryptPassHex = getHexEncryptText(plainText, BmsConstants.AES_KEY);
        System.out.println("encryptPassHex:"+encryptPassHex);
        
        String decryptHexText = decryptHexText(encryptPassHex, BmsConstants.AES_KEY);
        System.out.println("decryptHexText:"+decryptHexText);
        
        /*SecretKey secKey = getSecretEncryptionKey();
        byte[] cipherText = encryptText(plainText, secKey);
        String decryptedText = decryptText(cipherText, secKey);
        System.out.println("Original Text:" + plainText);
        System.out.println("secKey:" + secKey.getEncoded());
        System.out.println("HexTobyte:"+hexToBytes(bytesToHex(secKey.getEncoded())));
        System.out.println("AES Key (Hex Form):"+bytesToHex(secKey.getEncoded()));
        System.out.println("Reconvert:"+bytesToHex(hexToBytes(bytesToHex(secKey.getEncoded()))));
        System.out.println("Encrypted Text (Hex Form):"+bytesToHex(cipherText));
        System.out.println("Descrypted Text:"+decryptedText);*/
    }
    
    /**
     * gets the AES encryption key. In your actual programs, this should be safely
     * stored.
     * @return
     * @throws Exception 
     */
    public static SecretKey getSecretEncryptionKey() throws Exception{
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(128); // The AES key size in number of bits
        SecretKey secKey = generator.generateKey();
        return secKey;
    }
    
    public static String getHexSecretEncryptionKey() throws Exception{
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(128); // The AES key size in number of bits
        SecretKey secKey = generator.generateKey();
        return bytesToHex(secKey.getEncoded());
    }
    
    public static SecretKey getSecretEncryptionKeyFromHex(String secretEncrptionKeyHex) throws Exception{
    	byte[] secKey = hexToBytes(secretEncrptionKeyHex);
    	SecretKey originalKey = new SecretKeySpec(secKey, 0, secKey.length, "AES");
    	return originalKey;
    }
    
    /**
     * Encrypts plainText in AES using the secret key
     * @param plainText
     * @param secKey
     * @return
     * @throws Exception 
     */
    public static byte[] encryptText(String plainText,SecretKey secKey) throws Exception{
		// AES defaults to AES/ECB/PKCS5Padding in Java 7
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.ENCRYPT_MODE, secKey);
        byte[] byteCipherText = aesCipher.doFinal(plainText.getBytes());
        return byteCipherText;
    }
    
    public static String getHexEncryptText(String plainText,String secKeyHex) throws Exception{
    	SecretKey secKey = getSecretEncryptionKeyFromHex(secKeyHex);
		// AES defaults to AES/ECB/PKCS5Padding in Java 7
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.ENCRYPT_MODE, secKey);
        byte[] byteCipherText = aesCipher.doFinal(plainText.getBytes());
        return bytesToHex(byteCipherText);
    }
    
    /**
     * Decrypts encrypted byte array using the key used for encryption.
     * @param byteCipherText
     * @param secKey
     * @return
     * @throws Exception 
     */
    public static String decryptText(byte[] byteCipherText, SecretKey secKey) throws Exception {
		// AES defaults to AES/ECB/PKCS5Padding in Java 7
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.DECRYPT_MODE, secKey);
        byte[] bytePlainText = aesCipher.doFinal(byteCipherText);
        return new String(bytePlainText);
    }
    
    public static String decryptHexText(String hexText, String secKeyHex) throws Exception {
    	SecretKey secKey = getSecretEncryptionKeyFromHex(secKeyHex);
		// AES defaults to AES/ECB/PKCS5Padding in Java 7
    	byte[] byteCipherText = hexToBytes(hexText);
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.DECRYPT_MODE, secKey);
        byte[] bytePlainText = aesCipher.doFinal(byteCipherText);
        return new String(bytePlainText);
    }
    
    /**
     * Convert a binary byte array into readable hex form
     * @param hash
     * @return 
     */
    private static String  bytesToHex(byte[] hash) {
        return DatatypeConverter.printHexBinary(hash);
    }
    
    /**
     * Convert a hex String into byte array form
     * @param hex
     * @return 
     */
    private static byte[] hexToBytes(String hex) {
        return DatatypeConverter.parseHexBinary(hex);
    }
}