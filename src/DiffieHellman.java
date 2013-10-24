import java.util.ArrayList;

public class DiffieHellman {

    private ArrayList<Character> alphabet = new ArrayList<Character>();
    private int alphabetSize;
    private int keyA;
    private int keyB;
    private int mod;
    private int generator;
    private int secretKeyA;
    private int secretKeyB;
    private int key;

    DiffieHellman() {
        for (char symbol = 'à'; symbol <= 'ÿ'; symbol++) {
            alphabet.add(symbol);
        }
        alphabetSize = alphabet.size();
        secretKeyA = 0;
        secretKeyB = 0;
    }

    public void setKeyA(int keyA) {
        this.keyA = keyA;
    }

    public void setKeyB(int keyB) {
        this.keyB = keyB;
    }

    public void setMod(int mod) {
        this.mod = mod;
    }

    public void setGenerator(int number) {
        generator = number;
    }

    public void createSecretKey() {
        secretKeyA = createKey(generator, keyA, mod);
        secretKeyB = createKey(generator, keyB, mod);
        if(secretKeyA != 0){
            key = createKey(keyB , secretKeyA, mod);
        }
        else if(secretKeyB != 0){
            key = createKey(secretKeyB, keyA, mod);
        }
    }

    private int createKey(int number, int degree, int mod) {
        int result = 1;
        while (degree != 0) {
            if (degree % 2 == 0) {
                degree /= 2;
                number = (number * number) % mod;
            } else {
                degree--;
                result = (result * number) % mod;
            }
        }
        return result;
    }

    String encrypt(String text) {

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

    public String decrypt(String text) {
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
