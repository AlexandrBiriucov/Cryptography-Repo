package lab1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Scanner;

class Main{


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("enter : encrypt, decrypt , or 2keys");
       String user_choice= sc.nextLine();


        if (user_choice.equals("encrypt")){
        test.encrypt();
        
        }

        else if (user_choice.equals("decrypt")) {
            test.decrypt();
        }

        else if (user_choice.equals("2keys")){
            Test2Keys.encrypt();


        }

        // else if (user_choice.equals("test2")){
        //         test2.encrypt();



        // }
    


        }


}
