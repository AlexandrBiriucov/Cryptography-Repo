package lab4;

/** The part of the DES key schedule that produces C(i) and D(i). */
public class KeySchedule {

    /** Result of the computation: the halves after the round i. */
    public static class Result {
        public final String c;
        public final String d;

        Result(String c, String d) {
            this.c = c;
            this.d = d;
        }
    }

    /** Applies a permutation table: the bit j of the result is the bit table[j] of the input. */
    public static String permute(String bits, int[] table) {
        StringBuilder sb = new StringBuilder();
        for (int pos : table) {
            sb.append(bits.charAt(pos - 1));
        }
        return sb.toString();
    }

    /** Cyclic left shift of the block by s positions. */
    public static String leftShift(String bits, int s) {
        return bits.substring(s) + bits.substring(0, s);
    }

    /** Computes C(i) and D(i) from K+ and prints all the intermediate steps. */
    public static Result compute(String kPlus, int i) {
        String c = kPlus.substring(0, 28);
        String d = kPlus.substring(28);
        System.out.println();
        System.out.println("Step 1. Split K+ into two halves of 28 bits");
        System.out.println("C0 = " + InputCheck.group(c, 7));
        System.out.println("D0 = " + InputCheck.group(d, 7));

        System.out.println();
        System.out.println("Step 2. Rounds 1.." + i + ": cyclic left shift of C and D");
        String c0 = c;
        String d0 = d;
        for (int n = 1; n <= i; n++) {
            int s = DesTables.SHIFTS[n - 1];
            String cn = leftShift(c, s);
            String dn = leftShift(d, s);
            System.out.println("Round " + n + ": shift by " + s + " position(s)");
            System.out.println("  C" + (n - 1) + " = " + InputCheck.group(c, 7));
            System.out.println("       the first " + s + " bit(s) " + c.substring(0, s) + " go to the end");
            System.out.println("  C" + n + " = " + InputCheck.group(cn, 7));
            System.out.println("  D" + (n - 1) + " = " + InputCheck.group(d, 7));
            System.out.println("       the first " + s + " bit(s) " + d.substring(0, s) + " go to the end");
            System.out.println("  D" + n + " = " + InputCheck.group(dn, 7));
            c = cn;
            d = dn;
        }

        System.out.println();
        System.out.println("Step 3. Result for i = " + i);
        System.out.println("C" + i + " = " + InputCheck.group(c, 7));
        System.out.println("D" + i + " = " + InputCheck.group(d, 7));
        System.out.println("C" + i + "D" + i + " = " + InputCheck.group(c + d, 7));
        System.out.println("C" + i + "D" + i + " (hex) = " + InputCheck.bitsToHex(c + d));

        int total = DesTables.totalShift(i);
        boolean okC = leftShift(c0, total % 28).equals(c);
        boolean okD = leftShift(d0, total % 28).equals(d);
        System.out.println();
        System.out.println("Check: the total shift S" + i + " = " + total);
        System.out.println("  C0 shifted by " + total + " gives C" + i + ": " + (okC ? "OK" : "ERROR"));
        System.out.println("  D0 shifted by " + total + " gives D" + i + ": " + (okD ? "OK" : "ERROR"));
        return new Result(c, d);
    }
}