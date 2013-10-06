public class Bacon {

    private static final int SIZE = 5;

    String encrypt(String text) {
        StringBuilder cryptogram = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);
            if (symbol == ' ') {
                cryptogram.append(" ");
            } else {
                cryptogram.append(binaryToAB(Integer
                        .toBinaryString(inAlphabet(symbol))));
            }
        }
        return cryptogram.toString();
    }

    public String decrypt(String text) {
        StringBuilder newText = new StringBuilder();
        String number = "";
        for (int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);
            if (symbol == ' ') {
                newText.append(" ");
            } else {
                number += symbol;
                if (number.length() == SIZE) {
                    newText.append(outAlphabet(Integer.parseInt(
                            ABToBinary(number), 2)));
                    number = "";
                }
            }
        }

        return newText.toString();
    }

    private String balancerLiner(String binary) {
        StringBuilder result = new StringBuilder();
        result.append(binary).reverse();

        while (result.length() < SIZE) {
            result.append('0');
        }

        return result.reverse().toString();
    }

    private String binaryToAB(String binary) {
        StringBuilder result = new StringBuilder();
        binary = balancerLiner(binary);
        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '0') {
                result.append('A');
            } else {
                result.append('B');
            }
        }
        return result.toString();
    }

    public String ABToBinary(String binary) {
        StringBuilder result = new StringBuilder();
        binary = balancerLiner(binary);
        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == 'A') {
                result.append('0');
            } else {
                result.append('1');
            }
        }
        return result.toString();
    }

    private int inAlphabet(char c) {
        int number = (int) c;

        if (number <= 1103 && number >= 1072) {
            return number - 'à';
        }
        if (number <= 1040 && number >= 1071) {
            return number - 'A';
        } else {
            return 0;
        }
    }

    private char outAlphabet(int number) {
        return (char) (number + 'à');
    }

}
