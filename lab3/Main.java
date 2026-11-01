package lab3;
import java.util.Scanner;
//PS C:\Cryptography> [Console]::OutputEncoding = [System.Text.Encoding]::UTF8
// PS C:\Cryptography> java "-Dfile.encoding=UTF-8" -cp out lab3.Main   
public class Main {
    
    public static void main(String[] args){

          Scanner sc = new Scanner(System.in);
          // String message=sc.nextLine();
          // String key = sc.nextLine();


        // EntryData DataObj= EntryData.getInstance(message,key);
         
        // System.out.println(DataObj.letters);
        // System.out.println(DataObj.userKey);
        // System.out.println(DataObj.userWord);
        System.out.println("For encryption type 'Encrypt' and for decryption type 'Decrypt' ");
        String choice = sc.nextLine();

        
        
        if (choice.equals("Encrypt")){
        System.out.println("Enter the Message, only romanian letters allowed");
          String message=sc.nextLine();
          System.out.println("Enter the key (not smaller than 7 letters)");
          String key = sc.nextLine();
          EntryData DataObj= EntryData.getInstance(message,key);

          if(!UserValuesCheck.checkKey(key)) return;
          if(!UserValuesCheck.checkMessage(message,DataObj)) return;
          
        VigenereLogic.Encrypt(message,key,DataObj);
        }
        else if (choice.equals("Decrypt")){
          System.out.println("Enter the Message, only romanian letters allowed");
          String message=sc.nextLine();
          System.out.println("Enter the key (not smaller than 7 letters)");
          String key = sc.nextLine();
          EntryData DataObj= EntryData.getInstance(message,key);

          if(!UserValuesCheck.checkKey(key)) return;
          if(!UserValuesCheck.checkMessage(message,DataObj)) return;
          
        VigenereLogic.Decrypt(message,key,DataObj);
        }

      //  java "-Dfile.encoding=UTF-8" -cp out lab3.Main

      
    }


}
