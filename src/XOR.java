

import java.util.ArrayList;

public class XOR {

    private ArrayList <Character> alphabet = new ArrayList<Character>();
    private int alphabetSize; 
    XOR() {
        for (char c = 'à'; c <= 'ÿ'; c++) {
            alphabet.add(c);
        }
        alphabetSize = alphabet.size();
    }

    String encrypt(String text, int key) {
        StringBuilder cryptogram = new StringBuilder();

        key = key % alphabetSize;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if(c == ' '){
                cryptogram.append(" ");
            }else{
            int index = alphabet.indexOf(c);
            index = xor(index,random(key, i))%alphabetSize;
            cryptogram.append(alphabet.get(index));
            }
        }
        return cryptogram.toString();
    }
    
    public String decrypt(String text, int key){
        return encrypt(text, key);
    }
    
    private int random(int number, int count) {
        int numbers[] = new int[] { 5, 67, 21, 76, 13, 86, 32, 87, 3, 98, 21,
                9, 11, 54, 94, 1, 4, 7, 55, 44, 32, 95, 33, 22, 64, 87, 30, 39,
                65 };
        return numbers[(number * count) % numbers.length] % alphabet.size();
    }
    
    private int xor(int a, int b){
        return a^b;
    }
}
