import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class testwh {
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        char[] letters = {
            'A', 'Ă', 'Â', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'Î', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'Ș', 'T', 'Ț', 'U', 'V',
            'W', 'X', 'Y', 'Z'
        };

        for (char c : letters) {
            System.out.println(c);
        }
    }
}