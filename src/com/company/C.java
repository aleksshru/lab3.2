package com.company;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Scanner;

public class C{
    void fileWrite(String path, String mess){
        try(FileWriter fileWriter = new FileWriter(path)) {
            fileWriter.write(mess);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    String fileRead(String path){
        String mess = "";
        try (FileReader fileReader = new FileReader(path)){
            Scanner scaner = new Scanner(fileReader);
            mess = scaner.nextLine();
        }
         catch (IOException e) {
            e.printStackTrace();
        }
        return mess;
    }
    void fileIncrypt(String path, SecretKey key) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Provider provider = new BouncyCastleProvider();
        Security.addProvider(provider);
        Cipher cipher = Cipher.getInstance("AES","BC");
        String message = fileRead(path);
        byte[] mesBytes = message.getBytes(StandardCharsets.UTF_8);



        byte encMas[] = simpleSymmetricEncrypt(cipher, key, mesBytes);
        String encod = A.byteToString(encMas);
        System.out.println("Encoded: "+ encod);
        fileWrite(path, encod);
    }
    void fileDecrypt(SecretKey key, String path) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Provider provider = new BouncyCastleProvider();
        Security.addProvider(provider);
        Cipher cipher = Cipher.getInstance("AES","BC");
        byte[] encMas = A.hexStringToByteArray(fileRead(path));


        System.out.println("key: " + key.toString());

        byte decMas[] = simpleSymmetricDecrypt(cipher, key, encMas);
        String decod = new String(decMas);

        System.out.println("Decoded: "+ decod);
        fileWrite("Text_encoded.txt", decod);
    }
    public byte [] simpleSymmetricEncrypt(Cipher cipher, SecretKey
            key, byte[] inputMas) throws InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException
    {
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(inputMas);
    }
    public byte [] simpleSymmetricDecrypt(Cipher cipher, SecretKey
            key, byte[] encryptMas) throws InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException
    {
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(encryptMas);
    }
}
