import java.util.ArrayList;

public class Cesar {

    private ArrayList<Character> alphabet = new ArrayList<Character>();
    private int alphabetSize;

    Cesar() {
        for (char symbol = 'à'; symbol <= 'ÿ'; symbol++) {
            alphabet.add(symbol);
        }
        alphabetSize = alphabet.size();
    }

    String encrypt(String text, int key) {

        key = key % alphabetSize;

        StringBuilder cryptogram = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);
            if (symbol == ' ') {
                cryptogram.append(" ");
            } else {
                int index = alphabet.indexOf(symbol);
                index = (index + key) % alphabetSize;
                cryptogram.append(alphabet.get(index));
            }
        }
        return cryptogram.toString();
    }

    public String decrypt(String text, int key) {
        StringBuilder newText = new StringBuilder();
        key = key % alphabetSize;
        int reversedM = -1;

        for (int i = 0; i < alphabetSize; i++) {
            if (i % alphabetSize == 1) {
                reversedM = i;
                break;
            }
        }

        for (int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);
            if (symbol == ' ') {
                newText.append(" ");
            } else {
                int index = alphabet.indexOf(symbol);
                index = (((index - key) * reversedM) % alphabetSize + alphabetSize)
                        % alphabetSize;
                newText.append(alphabet.get(index));
            }
        }
        return newText.toString();

    }

}