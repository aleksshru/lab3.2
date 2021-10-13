package com.company;

import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;

public class A {
    static String byteToStringHex(byte[] mess) {
        StringBuilder sb = new StringBuilder();
        for (byte b : mess) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString();
    }
    public static byte[] hexStringToByteArray(final String s) {
        if (s == null) {
            return (new byte[]{});
        }

        if (s.length() % 2 != 0 || s.length() == 0) {
            return (new byte[]{});
        }

        byte[] data = new byte[s.length() / 2];
        for (int i = 0; i < s.length(); i += 2) {
            try {
                data[i / 2] = (Integer.decode("0x" + s.charAt(i) + s.charAt(i + 1))).byteValue();
            } catch (NumberFormatException e) {
                return (new byte[]{});
            }
        }
        return data;
    }
    static String byteToString(byte[] mess){
        StringBuilder sb = new StringBuilder();
        byte[] enc =  Hex.encode(mess);
        for (byte b : enc) {
            sb.append((char)b);
        }
        return sb.toString();
    }
}