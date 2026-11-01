package lab3;

public class VigenereLogic {
    
    public static void Encrypt(String message,String key, EntryData obj){
        String[] parsedMessage = message.split(" ");
        for (int i=0;i<parsedMessage.length;i++){
            parsedMessage[i] = parsedMessage[i].toUpperCase();
        }
        // for (int i=0;i<key.length();i++){
                        
        // }
        key=key.toUpperCase();



        StringBuilder stringBuilder = new StringBuilder();

        String combinedString = String.join("", parsedMessage);
             for (int i=0;i<combinedString.length();i++){
              int keyInd = i % key.length(); //calcualte the correspong key index
              int CypheredInd= GetCypheredInd(obj.letterToIndex.get(combinedString.charAt(i)),obj.letterToIndex.get(key.charAt(keyInd)));
                stringBuilder.append(obj.letters[CypheredInd]);
             }

            System.out.println(stringBuilder);

    }



    


    public static int GetCypheredInd(int letterInd, int KeyInd){
        int CypheredInd=0;
        CypheredInd= (letterInd + KeyInd)%31;

        return CypheredInd;

    }

    public static void Decrypt(String message, String key, EntryData obj){
            String[] parsedMessage = message.split(" ");
        for (int i=0;i<parsedMessage.length;i++){
            parsedMessage[i] = parsedMessage[i].toUpperCase();
        }
        // for (int i=0;i<key.length();i++){
                        
        // }
        key=key.toUpperCase();



        StringBuilder stringBuilder = new StringBuilder();

        String combinedString = String.join("", parsedMessage);
             for (int i=0;i<combinedString.length();i++){
            //   int keyInd=  combinedString.charAt(i)%key.length(); //calcualte the correspong key index
                    int keyInd = i % key.length();
            int CypheredInd= GetDecypheredInd(obj.letterToIndex.get(combinedString.charAt(i)),obj.letterToIndex.get(key.charAt(keyInd)));
                stringBuilder.append(obj.letters[CypheredInd]);
             }

            System.out.println(stringBuilder);

    }



    


    public static int GetDecypheredInd(int letterInd, int KeyInd){
        int DecypheredInd=0;
        DecypheredInd = (letterInd - KeyInd + 31) % 31;

        return DecypheredInd;   

            
        }

}
