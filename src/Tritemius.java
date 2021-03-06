
import java.util.ArrayList;

import matchParser.MatchParser;

public class Tritemius {

    private ArrayList<Character> alphabet = new ArrayList<Character>();
    private MatchParser parser;
    private int alphabetSize;
    Tritemius() {
        for (char symbol = '�'; symbol <= '�'; symbol++) {
            alphabet.add(symbol);
        }
        alphabetSize = alphabet.size();
        parser = new MatchParser();
    }

    String encrypt(String text, String formula) {
        StringBuilder cryptogram = new StringBuilder();
        int key = 0;

        for (int i = 0; i < text.length(); i++) {
            parser.setVariable("n", (double) i);
            char symbol = text.charAt(i);
            if (symbol == ' ') {
                cryptogram.append(" ");
            } else {
                int index = alphabet.indexOf(symbol);
                try {
                    key = (int) parser.Parse(formula);

                } catch (Exception e) {
                    e.printStackTrace();
                    key = 1;
                }

                key = key % alphabetSize;
                index = (index + key) % alphabetSize;
                cryptogram.append(alphabet.get(index));
            }
        }
        return cryptogram.toString();
    }

    public String decrypt(String text, String formula) {
        StringBuilder newText = new StringBuilder();
        int key = 0;
        int reversedM = -1;

        for (int i = 0; i < alphabetSize; i++) {
            if (i % alphabet.size() == 1) {
                reversedM = i;
                break;
            }
        }
        
        for (int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);
            parser.setVariable("n", (double) i);
            if (symbol == ' ') {
                newText.append(" ");
            } else {
                int index = alphabet.indexOf(symbol);
                try {
                    key = (int) parser.Parse(formula);

                } catch (Exception e) {
                    e.printStackTrace();
                    key = 1;
                }

                index = (((index - key) * reversedM) % alphabetSize + alphabetSize) % alphabetSize;
                newText.append(alphabet.get(index));
            }
        }
        return newText.toString();

    }

}
