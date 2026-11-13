package lab4;

import java.util.Random;

/** Validation of the input data and conversion between the representations of bits. */
public class InputCheck {

    /** Removes all spaces from the string. */
    public static String clean(String s) {
        return s.replaceAll("\\s+", "");
    }

    /** Checks that the string has exactly len characters, all of them 0 or 1. */
    public static boolean isBits(String s, int len) {
        return s.length() == len && s.matches("[01]+");
    }

    /** Checks that the string has exactly len hexadecimal digits. */
    public static boolean isHex(String s, int len) {
        return s.length() == len && s.matches("[0-9A-Fa-f]+");
    }

    /** Converts hexadecimal digits to a string of bits (4 bits per digit). */
    public static String hexToBits(String hex) {
        StringBuilder sb = new StringBuilder();
        for (char c : hex.toCharArray()) {
            String b = Integer.toBinaryString(Character.digit(c, 16));
            sb.append("0000".substring(b.length())).append(b);
        }
        return sb.toString();
    }

    /** Converts a string of bits to hexadecimal digits (the length must be a multiple of 4). */
    public static String bitsToHex(String bits) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bits.length(); i += 4) {
            sb.append(Integer.toHexString(Integer.parseInt(bits.substring(i, i + 4), 2)).toUpperCase());
        }
        return sb.toString();
    }

    /** Checks that every symbol of the text is a single byte (code 0..255). */
    public static boolean isByteText(String text) {
        for (char c : text.toCharArray()) {
            if (c > 255) {
                return false;
            }
        }
        return true;
    }

    /** Converts the text to bits, 8 bits per symbol. */
    public static String textToBits(String text) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            String b = Integer.toBinaryString(c & 0xFF);
            sb.append("00000000".substring(b.length())).append(b);
        }
        return sb.toString();
    }

    /** Generates a random string of bits. */
    public static String randomBits(int len, Random rnd) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(rnd.nextInt(2));
        }
        return sb.toString();
    }

    /** Writes the bits in groups of the given size separated by spaces. */
    public static String group(String bits, int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bits.length(); i += size) {
            if (i > 0) {
                sb.append(' ');
            }
            sb.append(bits, i, Math.min(i + size, bits.length()));
        }
        return sb.toString();
    }
}