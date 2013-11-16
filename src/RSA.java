import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class RSA {

    private ArrayList<Character> alphabet = new ArrayList<Character>();
    private int alphabetSize;
    private int onePrimeNumber; // p
    private int twoPrimeNumber; // q
    private int privateKey;     // e
    private int generalNumber;  // n
    private int publicKey;      // d

    RSA() {
        for (char symbol = 'a'; symbol <= 'z'; symbol++) {
            alphabet.add(symbol);
        }
        alphabetSize = alphabet.size();
    }

    public String encrypt(String text, int publicKey, int publicN) {
        StringBuilder cryptogram = new StringBuilder();
        for (int index = 0; index < text.length(); index++) {
            char symbol = text.charAt(index);
            int indexSymbol = alphabet.indexOf(symbol) + 1;
            cryptogram.append(calculate(indexSymbol, publicKey, publicN) + " ");
        }

        return cryptogram.toString();
    }

    public String decrypt(String text, int privateKey, int publicN) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(text);
        StringBuilder cryptogram = new StringBuilder();
        while (scanner.hasNext()) {
            BigInteger ind = calculate(scanner.nextInt(), privateKey, publicN);
            ind.mod(new BigInteger(String.valueOf(alphabetSize)));
            cryptogram.append(alphabet.get((int)(ind.longValue())-1));
        }
        return cryptogram.toString();
    }

    private int findPublicKey(int d, int m){
        int result = 2;
        while (true){
            if((result*d)%m == 1)break;
                result++;
        }
        return result;
    }

    private int gcd(int a, int b) {
        int c;
        while (b > 0) {
            c = a % b;
            a = b;
            b = c;
        }
        return Math.abs(a);
    }
    
    private BigInteger calculate(int number, int degree, int mod) {
        return new BigInteger(String.valueOf(number)).modPow(new BigInteger(String.valueOf(degree)), new BigInteger(String.valueOf(mod)));
    }
    
    private int findPrivateKey(int m){
        for(int i=2; i < m; i++)
            if(gcd(i, m)==1)return i;
        return 0;
    }
    
    public void createKey(){
        generalNumber = onePrimeNumber * twoPrimeNumber;
        int gamaN = (onePrimeNumber-1)*(twoPrimeNumber-1);
        privateKey = findPrivateKey(gamaN);
        publicKey = findPublicKey(privateKey, gamaN);
        showKey(privateKey, generalNumber);
        showKey(publicKey, generalNumber);
    }
    
    private void showKey(int key1, int key2) {
        System.out.print("{" + key1  + "," + key2 + "} -");
        if (key1 == privateKey) {
            System.out.println(" private key");
        } else if(key1 == publicKey){
            System.out.println(" public key");
        } else{
            System.out.println(" ERROR key");
        }
    }
    
    
    public void setP(int p) {
        this.onePrimeNumber = p;
    }

    public void setQ(int q) {
        this.twoPrimeNumber = q;
    }

    public int getGeneralNumber(){
        return generalNumber;
    }
    
    public int getPrivateNumber(){
        return privateKey;
    }
    
    public int getPublicNumber() {
        return publicKey;
    }
}
