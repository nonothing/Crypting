import java.util.ArrayList;

public class Playfair {
    private ArrayList<Character> alphabet = new ArrayList<Character>();
    private int alphabetSize;

    Playfair() {
        for (char symbol = 'a'; symbol <= 'z'; symbol++) {
            alphabet.add(symbol);
        }
        alphabetSize = alphabet.size();
    }

    public String encrypt(String text, String key) {
        StringBuilder cryptogram = new StringBuilder();
        String[] matrix = new Playfair().createMatrix(key);
        int index = 0;
        text = principleOne(text);
        if (text.length() % 2 != 0)
            text += "x";
        while (index < text.length()) {
            int[] pair = new Playfair().findSymbol(text.charAt(index),
                    text.charAt(index + 1), matrix);
            cryptogram.append(principleTwo(pair, matrix, true));
            cryptogram.append(principleThree(pair, matrix, true));
            cryptogram.append(principleFour(pair, matrix, true));
            index += 2;
        }

        return cryptogram.toString();
    }

    public String decrypt(String text, String key) {
        StringBuilder newText = new StringBuilder();
        String[] matrix = new Playfair().createMatrix(key);
        int index = 0;

        while (index < text.length()) {
            int[] pair = new Playfair().findSymbol(text.charAt(index),
                    text.charAt(index + 1), matrix);
            newText.append(principleTwo(pair, matrix, false));
            newText.append(principleThree(pair, matrix, false));
            newText.append(principleFour(pair, matrix, false));
            index += 2;
        }
        return newText.toString();

    }

    private String avoidRepetition(String text) {
        String result = "";
        int index = 0;
        while (index < text.length()) {
            boolean is = false;
            if (text.charAt(index) == ' ')
                index++;
            for (int i = 0; i < result.length(); i++) {
                if (text.charAt(index) == result.charAt(i)) {
                    is = true;
                    break;
                }
            }
            if (!is) {
                result += text.charAt(index);
            }
            index++;
        }
        return result;
    }

    private String createAlphabet(String text) {
        int index = 0;
        while (index < alphabetSize) {
            boolean is = false;
            for (int i = 0; i < text.length(); i++) {
                if (alphabet.get(index) == text.charAt(i)
                        || alphabet.get(index) == 'q') {
                    is = true;
                    break;
                }
            }
            if (!is) {
                text += alphabet.get(index);
            }
            index++;
        }

        return text;
    }

    private String principleOne(String text) {
        String result = "";
        int index = 1;
        result += text.charAt(0);
        while (text.length() > index) {
            if (result.charAt(result.length() - 1) == text.charAt(index)) {
                result += 'x';
            }
            result += text.charAt(index);
            index++;
        }
        return result;
    }

    private String principleTwo(int[] pair, String[] matrix, boolean crypt) {
        String result = "";
        if (pair[0] == pair[2] && crypt) {
            int one = ++pair[1];
            int two = ++pair[3];
            if (one == 5)
                one = 0;
            if (two == 5)
                two = 0;
            result += matrix[pair[0]].charAt(one);
            result += matrix[pair[0]].charAt(two);
        }
        if (pair[0] == pair[2] && !crypt) {
            int one = --pair[1];
            int two = --pair[3];
            if (one < 0)
                one = 4;
            if (two < 0)
                two = 4;
            result += matrix[pair[0]].charAt(one);
            result += matrix[pair[0]].charAt(two);
        }
        return result;
    }

    private String principleThree(int[] pair, String[] matrix, boolean crypt) {
        String result = "";
        if (pair[1] == pair[3] && crypt) {
            int one = ++pair[0];
            int two = ++pair[2];
            if (one == 5)
                one = 0;
            if (two == 5)
                two = 0;
            result += matrix[two].charAt(pair[3]);
            result += matrix[one].charAt(pair[3]);
        }
        if (pair[1] == pair[3] && !crypt) {
            int one = --pair[0];
            int two = --pair[2];
            if (one == 0)
                one = 4;
            if (two == 0)
                two = 4;
            result += matrix[two].charAt(pair[3]);
            result += matrix[one].charAt(pair[3]);
        }
        return result;
    }

    private String principleFour(int[] pair, String[] matrix, boolean crypt) {
        String result = "";
        if (pair[1] != pair[3] && pair[0] != pair[2] && crypt) {
            result += matrix[pair[0]].charAt(pair[3]);
            result += matrix[pair[2]].charAt(pair[1]);
        }
        if (pair[1] != pair[3] && pair[0] != pair[2] && !crypt) {
            result += matrix[pair[0]].charAt(pair[3]);
            result += matrix[pair[2]].charAt(pair[1]);
        }

        return result;
    }

    private int[] findSymbol(char one, char two, String[] matrix) {
        int result[] = new int[4];
        int index = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length(); j++) {
                if (one == matrix[i].charAt(j)) {
                    result[index] = i;
                    result[index + 1] = j;
                    index += 2;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length(); j++) {
                if (two == matrix[i].charAt(j)) {
                    result[index] = i;
                    result[index + 1] = j;
                    index += 2;
                }
            }
        }

        return result;
    }

    private String[] createMatrix(String key) {
        key = createAlphabet(avoidRepetition(key));
        String[] result = new String[] { "", "", "", "", "", "" };
        int index = 0;
        int row = 0;
        while (index != alphabetSize - 1) {
            result[row] += key.charAt(index);
            index++;
            if (index % 5 == 0) {
                row++;
            }
        }
        return result;
    }
}
