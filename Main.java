
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Scanner;
class Main{


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
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
