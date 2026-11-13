package lab4;


import java.util.Random;
import java.util.Scanner;

/**
 * Laboratory work 4, task 2.2: given K+ in DES, determine C(i) and D(i).
 *
 * Interactive mode: java lab4.Main
 * Batch mode:       java lab4.Main (--kplus V | --key TEXT | --keyhex V | --random) [--i N] [--seed S]
 * Self-test:        java lab4.Main --selftest
 */
public class Main {

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--selftest")) {
            selfTest();
            return;
        }
        String mode = null;
        String value = null;
        String iText = null;
        long seed = System.nanoTime();
        for (int a = 0; a < args.length; a++) {
            switch (args[a]) {
                case "--kplus": case "--key": case "--keyhex":
                    mode = args[a].substring(2);
                    value = a + 1 < args.length ? args[++a] : "";
                    break;
                case "--random":
                    mode = "random";
                    break;
                case "--i":
                    iText = a + 1 < args.length ? args[++a] : "";
                    break;
                case "--seed":
                    seed = Long.parseLong(args[++a]);
                    break;
                default:
                    System.out.println("Unknown argument: " + args[a]);
                    return;
            }
        }


        
        Random rnd = new Random(seed);
        System.out.println("DES key schedule: determination of C(i) and D(i) (task 2.2)");
        System.out.println("------------------------------------------------------------");
        Scanner sc = mode == null ? new Scanner(System.in) : null;
        String kPlus;
        if (mode == null) {
            kPlus = askKPlus(sc, rnd);
        } else {
            kPlus = buildKPlus(mode, value, rnd);
        }
        if (kPlus == null) {
            return;
        }
        int i;
        if (iText == null && mode == null) {
            i = askRound(sc, rnd);
        } else {
            i = parseRound(iText, rnd);
        }
        if (i == 0) {
            return;
        }
        System.out.println();
        DesTables.printShifts();
        System.out.println();
        System.out.println("The required round: i = " + i);
        KeySchedule.compute(kPlus, i);
    }

    /** Builds K+ from the batch arguments. Returns null if the data are not valid. */
    private static String buildKPlus(String mode, String value, Random rnd) {
        switch (mode) {
            case "kplus": {
                String v = InputCheck.clean(value);
                String bits = InputCheck.isBits(v, 56) ? v : (InputCheck.isHex(v, 14) ? InputCheck.hexToBits(v) : null);
                if (bits == null) {
                    System.out.println("Error: K+ must be 56 bits or 14 hexadecimal digits");
                    return null;
                }
                System.out.println("K+ is given by the user");
                System.out.println("K+ = " + InputCheck.group(bits, 7));
                return bits;
            }
            case "key": {
                if (value.length() != 8 || !InputCheck.isByteText(value)) {
                    System.out.println("Error: the key must contain exactly 8 symbols (codes 0..255)");
                    return null;
                }
                System.out.println("Key (8 symbols) = " + value);
                return fromKey(InputCheck.textToBits(value));
            }
            case "keyhex": {
                String v = InputCheck.clean(value);
                if (!InputCheck.isHex(v, 16)) {
                    System.out.println("Error: the key must contain exactly 16 hexadecimal digits");
                    return null;
                }
                System.out.println("Key (hex) = " + v.toUpperCase());
                return fromKey(InputCheck.hexToBits(v));
            }
            default: {
                String bits = InputCheck.randomBits(56, rnd);
                System.out.println("K+ is generated randomly");
                System.out.println("K+ = " + InputCheck.group(bits, 7));
                return bits;
            }
        }
    }

    /** K (64 bits) -> K+ (56 bits) with the table PC-1. */
    private static String fromKey(String k) {
        System.out.println("K (64 bits):");
        System.out.println("K  = " + InputCheck.group(k, 8));
        System.out.println();
        DesTables.printPc1();
        String kPlus = KeySchedule.permute(k, DesTables.PC1);
        System.out.println("The bits 8, 16, ..., 64 of K (the parity bits) are not used.");
        System.out.println("K+ = PC-1(K) = " + InputCheck.group(kPlus, 7));
        return kPlus;
    }

    private static int parseRound(String text, Random rnd) {
        if (text == null) {
            int r = 1 + rnd.nextInt(16);
            System.out.println("i is generated randomly: " + r);
            return r;
        }
        try {
            int r = Integer.parseInt(text.trim());
            if (r >= 1 && r <= 16) {
                return r;
            }
        } catch (NumberFormatException e) {
            // falls through to the error message
        }
        System.out.println("Error: i must be an integer from 1 to 16");
        return 0;
    }

    private static String askKPlus(Scanner sc, Random rnd) {
        while (true) {
            System.out.println("Select the source of the data:");
            System.out.println("  1 - enter K+ (56 bits or 14 hexadecimal digits)");
            System.out.println("  2 - enter the key (8 symbols); K+ is computed with PC-1");
            System.out.println("  3 - enter the key as 16 hexadecimal digits; K+ is computed with PC-1");
            System.out.println("  4 - generate K+ randomly");
            System.out.print("Choice: ");
            String choice = sc.nextLine().trim();
            String[] modes = {"kplus", "key", "keyhex", "random"};
            String[] labels = {"K+ (56 bits or 14 hex digits): ", "Key (8 symbols): ", "Key (16 hex digits): ", ""};
            if (!choice.matches("[1-4]")) {
                System.out.println("Error: enter a number from 1 to 4");
                continue;
            }
            int m = Integer.parseInt(choice) - 1;
            String value = "";
            if (m < 3) {
                System.out.print(labels[m]);
                value = m == 1 ? sc.nextLine() : sc.nextLine().trim();
            }
            String kPlus = buildKPlus(modes[m], value, rnd);
            if (kPlus != null) {
                return kPlus;
            }
        }
    }

    private static int askRound(Scanner sc, Random rnd) {
        while (true) {
            System.out.print("Enter i (1..16), or 0 for a random value: ");
            String t = sc.nextLine().trim();
            if (t.equals("0")) {
                return parseRound(null, rnd);
            }
            int r = parseRound(t, rnd);
            if (r != 0) {
                return r;
            }
        }
    }

    /** Checks the program with the example of J. O. Grabbe (K = 133457799BBCDFF1). */
    private static void selfTest() {
        String k = InputCheck.hexToBits("133457799BBCDFF1");
        String kPlus = KeySchedule.permute(k, DesTables.PC1);
        String[] c = {"1111000011001100101010101111", "1110000110011001010101011111",
                "1100001100110010101010111111", "0000110011001010101011111111"};
        String[] d = {"0101010101100110011110001111", "1010101011001100111100011110",
                "0101010110011001111000111101", "0101011001100111100011110101"};
        boolean ok = kPlus.equals(c[0] + d[0]);
        System.out.println("K+ = " + InputCheck.group(kPlus, 7) + " : " + (ok ? "OK" : "ERROR"));
        String cn = c[0];
        String dn = d[0];
        for (int n = 1; n <= 3; n++) {
            cn = KeySchedule.leftShift(cn, DesTables.SHIFTS[n - 1]);
            dn = KeySchedule.leftShift(dn, DesTables.SHIFTS[n - 1]);
            boolean good = cn.equals(c[n]) && dn.equals(d[n]);
            ok &= good;
            System.out.println("C" + n + ", D" + n + " : " + (good ? "OK" : "ERROR"));
        }
        for (int n = 4; n <= 16; n++) {
            cn = KeySchedule.leftShift(cn, DesTables.SHIFTS[n - 1]);
            dn = KeySchedule.leftShift(dn, DesTables.SHIFTS[n - 1]);
        }
        boolean back = cn.equals(c[0]) && dn.equals(d[0]);
        ok &= back;
        System.out.println("C16 = C0 and D16 = D0 (the total shift is 28): " + (back ? "OK" : "ERROR"));
        System.out.println(ok ? "Self-test passed" : "Self-test FAILED");
    }
}