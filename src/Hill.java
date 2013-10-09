import java.util.ArrayList;

public class Hill {
    private ArrayList<Character> alphabet = new ArrayList<Character>();
    private int alphabetSize;

    Hill() {
        for (char symbol = 'a'; symbol <= 'z'; symbol++) {
            alphabet.add(symbol);
        }
        alphabetSize = alphabet.size();
    }

    public String encrypt(String text) {
        StringBuilder result = new StringBuilder();
        int key[][] = { { 6, 24, 1 }, { 13, 16, 10 }, { 20, 17, 15 } };

        key = multiplicationMatrix(createMatrixWithWord(text, 3), key);

        for (int i = 0; i < key[0].length; i++) {
            for (int j = 0; j < key.length; j++) {
                result.append(alphabet.get(key[j][i] % alphabetSize));
            }
        }

        return result.toString();
    }

    public String decrypt(String text) {
        StringBuilder result = new StringBuilder();
        int key[][] = { { 8, 5, 10 }, { 21, 8, 21 }, { 21, 12, 8 } };

        key = multiplicationMatrix(createMatrixWithWord(text, 3), key);

        for (int i = 0; i < key[0].length; i++) {
            for (int j = 0; j < key.length; j++) {
                result.append(alphabet.get(key[j][i] % alphabetSize));
            }
        }

        return result.toString();
    }

    public int[][] createMatrixWithWord(String text, int size) {
        int sizeArr = text.length() / size;
        if (sizeArr * size < text.length()) {
            sizeArr++;
        }

        int result[][] = new int[size][sizeArr];
        int index = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = alphabetSize - 1;
            }
        }
        int i = 0;
        int j = 0;

        while (index < text.length()) {
            result[i][j] = text.charAt(index) - alphabet.get(0);
            i++;
            if (i == size) {
                j++;
                i = 0;
            }
            index++;

        }

        return result;
    }

    private int[][] multiplicationMatrix(int[][] words, int[][] matrixKey) {
        int sizeWords = words[0].length;
        int result[][] = new int[matrixKey.length][sizeWords];

        if (words.length != matrixKey.length) {
            System.out.println("error");
        } else {
            for (int i = 0; i < words.length; i++) {
                for (int j = 0; j < sizeWords; j++) {
                    for (int k = 0; k < words.length; k++) {
                        result[i][j] += matrixKey[i][k] * words[k][j];
                    }
                }
            }
        }

        return result;
    }

}
