package lab3;

public  class UserValuesCheck {
    
    public static boolean checkKey(String key){

        if (key.length()< 7){

            System.out.println("key cannot be shorter than 7 characters");
            return false ;


        }

            return true;
    }


    public static boolean checkMessage(String message ,EntryData obj){
        for (String s : message.split(" ")){
            for (int i=0;i<s.length();i++){
                char c= Character.toUpperCase(s.charAt(i));
                if (!obj.lettersList.contains(c)){
                    System.out.println("only romanian language allowed");
                    return false;
                }
            }
            
            // char c = Character.toUpperCase(key2.charAt(i));
            
            // if (!keyLetters.contains(c)) {
                //     keyLetters.add(c);
                // }
                
            }    
            return true;


    }


}
