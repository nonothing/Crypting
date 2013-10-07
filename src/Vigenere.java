import java.util.ArrayList;

public class Vigenere {
    private ArrayList<Character> alphabet = new ArrayList<Character>();
    private String table[];
    private int alphabetSize;

    Vigenere() {
        for (char symbol = 'à'; symbol <= 'ÿ'; symbol++) {
            alphabet.add(symbol);
        }
        alphabetSize = alphabet.size();
        table = new String[32];
        createTable();
    }

    private void createTable() {
        int rows = 0;
        while (rows < 32) {
            table[rows] = "";
            for (int index = rows; index < alphabetSize + alphabetSize; index++) {
                table[rows] += alphabet.get(index % alphabetSize);
                if (table[rows].length() == alphabetSize)
                    break;
            }
            rows++;
        }
    }

    private String createKey(String key, int length) {
        String result = "";
        for (int i = 0; i < length; i++) {
            result += key.charAt(i % key.length());
        }
        return result;
    }

    public String encrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        key = createKey(key, text.length());
        for (int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);
            if (symbol == ' ') {
                result.append(" ");
            } else {
                int numberKey = key.charAt(i) - 'à';
                int numberText = symbol - 'à';
                result.append(table[numberText].charAt(numberKey));
            }
        }
        return result.toString();
    }

    public String decrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        key = createKey(key, text.length());
        for (int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);
            if (symbol == ' ') {
                result.append(" ");
            } else {
            int numberKey = key.charAt(i) - 'à';
            int numberText = symbol - 'à';
            result.append(alphabet.get((numberText - numberKey + alphabetSize)
                    % alphabetSize));
        }}
        return result.toString();
    }

}
