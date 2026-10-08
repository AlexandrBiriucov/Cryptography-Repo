import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
public class Test2Keys {

    

public static void encrypt(){
    // System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
    Scanner sc = new Scanner(System.in);
    //  System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
ArrayList<Character> lettersList = new ArrayList<>();

      char[] letters = {
        'A', 'Ă', 'Â', 'B', 'C', 'D', 'E', 'F', 'G',
        'H', 'I', 'Î', 'J', 'K', 'L', 'M', 'N', 'O',
        'P', 'Q', 'R', 'S', 'Ș', 'T', 'Ț', 'U', 'V',
        'W', 'X', 'Y', 'Z'  
    };


   

    int key1;
    String key2;
    String sentence;

    System.out.println("enter the first key");
    key1 = sc.nextInt();
    sc.nextLine(); // consume the leftover Enter
    System.out.println("enter the second key");

    key2 = sc.nextLine();
    
    



  if (key2.length() < 7){
    System.out.println("key2 should be longer than 7 letters");  
    return;
  }




    for (int i=0;i<key2.length();i++){
            boolean exists = false;
            char c = Character.toUpperCase(key2.charAt(i));
            for (char letter : letters){
                if (c==letter){
                    exists = true;
                    break;

                }

                }

                if (!exists){
                    System.out.println("use only romanain letters");
                    return;

            }

    }




    for (char letter : letters ){
        lettersList.add(letter);



    }

  



    System.out.println("ENTER  the sentence");

    sentence = sc.nextLine();

    ArrayList<Character> keyLetters = new ArrayList<>();

for (int i = 0; i < key2.length(); i++) {
    char c = Character.toUpperCase(key2.charAt(i));

    if (!keyLetters.contains(c)) {
        keyLetters.add(c);
    }
}

ArrayList<Character> combinedList = new ArrayList<>();
     

    for (int j=0;j<keyLetters.size();j++){
     combinedList.add(keyLetters.get(j));


    }

    for (int i=0;i<lettersList.size();i++){
        if (!combinedList.contains(lettersList.get(i))){
            combinedList.add(lettersList.get(i));


        }


    }


    Map<Character, Integer> letterToIndex = new HashMap<>();
    
    
    // int[] numbers =   new int[combinedList.size()];



    for (int i =0; i<combinedList.size(); i++){
            letterToIndex.put(combinedList.get(i), i);



    }

    // for (Map.Entry<Character,Integer> entry :  letterToIndex.entrySet()){
    //     System.out.println(entry);



    // }



    String[] parsedSentences;

       parsedSentences=sentence.split(" ");
       

       

      StringBuilder cipheredString = new StringBuilder();

        for (String sn : parsedSentences) {
        String msn = sn.toUpperCase();

        for (int j = 0; j < msn.length(); j++) {

            char currentChar = msn.charAt(j);

            int index = letterToIndex.get(currentChar);

            int newIndex = (index + key1) % combinedList.size();

            cipheredString.append(combinedList.get(newIndex));
        }

        cipheredString.append(' ');
    }


        System.out.println(cipheredString);
    









}




}
