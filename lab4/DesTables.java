package lab4;

/** Tables of the DES key schedule used in the task 2.2. */
public class DesTables {

    /** Permuted choice 1: 64 bits of the key -> 56 bits (K+). */
    public static final int[] PC1 = {
        57, 49, 41, 33, 25, 17,  9,
         1, 58, 50, 42, 34, 26, 18,
        10,  2, 59, 51, 43, 35, 27,
        19, 11,  3, 60, 52, 44, 36,
        63, 55, 47, 39, 31, 23, 15,
         7, 62, 54, 46, 38, 30, 22,
        14,  6, 61, 53, 45, 37, 29,
        21, 13,  5, 28, 20, 12,  4
    };

    /** Number of left shifts of C and D in rounds 1..16. */
    public static final int[] SHIFTS = {
        1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1
    };

    /** Total number of shifts after round n (n = 1..16). */
    public static int totalShift(int n) {
        int sum = 0;
        for (int j = 0; j < n; j++) {
            sum += SHIFTS[j];
        }
        return sum;
    }

    /** Prints PC-1 as a table of 8 rows and 7 columns. */
    public static void printPc1() {
        System.out.println("Table PC-1 (the position of the bit of K in K+):");
        for (int row = 0; row < 8; row++) {
            StringBuilder sb = new StringBuilder("  ");
            for (int col = 0; col < 7; col++) {
                sb.append(String.format("%3d", PC1[row * 7 + col]));
            }
            System.out.println(sb);
        }
    }

    /** Prints the schedule of the left shifts. */
    public static void printShifts() {
        StringBuilder r = new StringBuilder("Round :");
        StringBuilder s = new StringBuilder("Shift :");
        StringBuilder t = new StringBuilder("Total :");
        for (int n = 1; n <= 16; n++) {
            r.append(String.format("%3d", n));
            s.append(String.format("%3d", SHIFTS[n - 1]));
            t.append(String.format("%3d", totalShift(n)));
        }
        System.out.println("Table of the left shifts:");
        System.out.println(r);
        System.out.println(s);
        System.out.println(t);
    }
}