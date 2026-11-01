package lab3;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
public class EntryData {
 
char[] letters;
int[] numbers;
String userWord;
String userKey;
int i=0;
Map<Character,Integer> letterToIndex = new HashMap<>();
ArrayList<Character> lettersList= new ArrayList<>();
private static EntryData LogicObj;

private  EntryData(String message, String key){
    this.letters = new char[] {
        'A', 'Ă', 'Â', 'B', 'C', 'D', 'E', 'F', 'G',
        'H', 'I', 'Î', 'J', 'K', 'L', 'M', 'N', 'O',
        'P', 'Q', 'R', 'S', 'Ș', 'T', 'Ț', 'U', 'V',
        'W', 'X', 'Y', 'Z'
    };
    this.numbers = new int[letters.length];
    for (int i=0; i<letters.length; i++){

            numbers[i]=i;

        }

    this.userWord=message;
    this.userKey=key;
    for(char letter : letters){
        
            this.letterToIndex.put(letter, i);
            i++;

       }
       for (char c : letters){
        this.lettersList.add(c);
       }

}




//   public static char[] letters = {
//         'A', '�', '�', 'B', 'C', 'D', 'E', 'F', 'G',
//         'H', 'I', '�', 'J', 'K', 'L', 'M', 'N', 'O',
//         'P', 'Q', 'R', 'S', '�', 'T', '�', 'U', 'V',
//         'W', 'X', 'Y', 'Z'
//     };
    
public static EntryData getInstance(String message, String key){
    if (LogicObj==null){
       LogicObj = new EntryData(message,key);
       
    }
    
    return LogicObj;
    

}








// public static getLetters();


}
