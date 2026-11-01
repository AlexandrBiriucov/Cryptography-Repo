package lab1;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
class test{


    public static void encrypt(){
    Scanner sc = new Scanner(System.in);
    char[] letters = {
        'A', 'Ă', 'Â', 'B', 'C', 'D', 'E', 'F', 'G',
        'H', 'I', 'Î', 'J', 'K', 'L', 'M', 'N', 'O',
        'P', 'Q', 'R', 'S', 'Ș', 'T', 'Ț', 'U', 'V',
        'W', 'X', 'Y', 'Z'
    };
        int[] numbers = new int[31];



        
    for (int i=0; i<letters.length; i++){

        numbers[i]=i;

    }
    Map<Character, Integer> letterToIndex = new HashMap<>();
    

        

       int letter_arr_length = letters.length;
        
   
       int i =0;
       for(char letter : letters){
        
            letterToIndex.put(letter, i);
            i++;

       }


       

    //    for(char letter : letters){
        
    //         System.out.println(letter);

    //    }
       String sentence;
       int key;
       System.out.println("enter a sentence to encrypt");
       sentence=sc.nextLine();
    //    if (sentence.length()<7){
    //     return;

    //    }



       System.out.println("enter a key");
       key=sc.nextInt();

    if (key>31 || key<=0){
        System.out.println("wrong key value");
        return;
    }
    //    System.out.println(sentence);

    //    System.out.println(key);


       String[] parsedSentences;

       parsedSentences=sentence.split(" ");
       

       

      StringBuilder cipheredString = new StringBuilder();

       for(String sn:parsedSentences){
            String msn = sn.toUpperCase();
            for(int j=0;j<msn.length();j++){
                
                // System.out.println(sn);`
                char blank_char;
                blank_char = msn.charAt(j);
                
                // System.out.println(lette`rToIndex.get(blank_char));

                int index_of_the_ciphered_letter=((letterToIndex.get(blank_char)+key)% letters.length);
                // cipheredString.append

                    for (Map.Entry<Character,Integer> entry : letterToIndex.entrySet()){
                        if (entry.getValue()  == index_of_the_ciphered_letter){
                                // char letterKey = entry.getKey();
                                cipheredString.append(entry.getKey());
                        }
                    }


            }

            cipheredString.append(' ');

        

       }



       System.out.println(cipheredString);


}


    public static void decrypt(){
        
         Scanner sc = new Scanner(System.in);
    char[] letters = {
        'A', 'Ă', 'Â', 'B', 'C', 'D', 'E', 'F', 'G',
        'H', 'I', 'Î', 'J', 'K', 'L', 'M', 'N', 'O',
        'P', 'Q', 'R', 'S', 'Ș', 'T', 'Ț', 'U', 'V',
        'W', 'X', 'Y', 'Z'
    };
        int[] numbers = new int[31];

        
    for (int i=0; i<letters.length; i++){

        numbers[i]=i;

    }
    Map<Character, Integer> letterToIndex = new HashMap<>();
    

        

       int letter_arr_length = letters.length;
        
   
       int i =0;
       for(char letter : letters){
        
            letterToIndex.put(letter, i);
            i++;

       }


    //    for(char letter : letters){
        
    //         System.out.println(letter);

    //    }
       String sentence;
       int key;
       System.out.println("enter the sentence to decrypt");
       sentence=sc.nextLine();
        System.out.println("enter the key");

       key=sc.nextInt();

    //    System.out.println(sentence);

    //    System.out.println(key);


       String[] parsedSentences;

       parsedSentences=sentence.split(" ");
       

       

      StringBuilder cipheredString = new StringBuilder();

       for(String sn:parsedSentences){
            String msn = sn.toUpperCase();
            for(int j=0;j<msn.length();j++){
                
                // System.out.println(sn);`
                char blank_char;
                blank_char = msn.charAt(j);
                
                // System.out.println(letterToIndex.get(blank_char));

                int index_of_the_ciphered_letter=((letterToIndex.get(blank_char)-key+letters.length)% letters.length);
                // cipheredString.append

                    for (Map.Entry<Character,Integer> entry : letterToIndex.entrySet()){
                        if (entry.getValue()  == index_of_the_ciphered_letter){
                                // char letterKey = entry.getKey();
                                cipheredString.append(entry.getKey());
                        }
                    }


            }

            cipheredString.append(' ');

        

       }



       System.out.println(cipheredString);


    }





}