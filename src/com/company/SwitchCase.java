package com.company;

import javax.crypto.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class SwitchCase {
    void run() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, NoSuchProviderException, InvalidKeyException {
        C file = new C();
        System.out.println("Введите номер:\n1. Записать в файл\n2. Зашифровать файл\n3. Расшифровать файл\n4. Выход");
        String s = "";
        String temp = "";
        SecretKey key =null;
        System.out.println("Generating key...");
        try {
            key = KeyGenerator.getInstance("AES").generateKey();
            System.out.println("key: " + A.byteToString(key.getEncoded()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        while (!s.equals("4")){
            System.out.println("Введите номер:\n1. Записать в файл\n2. Зашифровать файл\n3. Расшифровать файл\n4. Выход");
            s = read();
            switch (s){
                case "1":
                    System.out.println("Введите сообщение:");
                    temp = read();
                    file.fileWrite("Text.txt",temp);
                    break;
                case "2":
                    System.out.println("Идет запись в файл Text_encoded:");
                    file.fileIncrypt("Text.txt", key);
                    System.out.println("Готово:");
                    break;
                case "3":
                    System.out.println("Идет расшифровка:");
                    file.fileDecrypt(key, "Text.txt");
                    System.out.println("Готово:");
                    break;
                case "4":
                    break;
                default:
                    System.out.println("Некорректный ввод");
            }
        }

    }
    String read(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = "";
        try {
            s = br.readLine();
        } catch (IOException e) {
            System.out.println("Ошибка ввода");
        }
        return s;
    }
}
