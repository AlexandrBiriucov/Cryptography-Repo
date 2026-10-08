import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class test2 {

    static String normalizeRomanian(String text) {
        Map<Character, Character> variants = new HashMap<>();

        variants.put('Ş', 'Ș');
        variants.put('ş', 'ș');
        variants.put('Ţ', 'Ț');
        variants.put('ţ', 'ț');

        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (variants.containsKey(c)) {
                result.append(variants.get(c));
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    public static void encrypt() {

        


        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
    Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);

        char[] letters = {
            'A', 'Ă', 'Â', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'Î', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'Ș', 'T', 'Ț', 'U', 'V',
            'W', 'X', 'Y', 'Z'
        };

        Map<Character, Integer> letterToIndex = new HashMap<>();

        for (int i = 0; i < letters.length; i++) {
            letterToIndex.put(letters[i], i);
        }

        String sentence = normalizeRomanian(sc.nextLine());
        int key = sc.nextInt();

        StringBuilder cipheredString = new StringBuilder();

        for (String word : sentence.split(" ")) {

            String upperWord = word.toUpperCase();

            for (int i = 0; i < upperWord.length(); i++) {

                char currentChar = upperWord.charAt(i);

                int index = letterToIndex.get(currentChar);

                int newIndex = (index + key) % letters.length;

                for (Map.Entry<Character, Integer> entry : letterToIndex.entrySet()) {
                    if (entry.getValue() == newIndex) {
                        cipheredString.append(entry.getKey());
                    }
                }
            }

            cipheredString.append(' ');
        }

        System.out.println(cipheredString);
    }

    public static void main(String[] args) {
        encrypt();
    }
}