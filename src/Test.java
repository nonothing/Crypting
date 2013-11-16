public class Test {
    public static void main(String args[]) {

        String str = "—ъешь этих м€гких французских булок ";
        String cryptXOR = new XOR().encrypt(str.toLowerCase(), 4);
        String decryptXOR = new XOR().decrypt(cryptXOR, 4);
        System.out.println("XOR crypt: " + cryptXOR);
        System.out.println("XOR decrypt: " + decryptXOR);

        String cryptCesar = new Cesar().encrypt(str.toLowerCase(), 3);
        String decryptCesar = new Cesar().decrypt(cryptCesar, 3);
        System.out.println("Cesar crypt: " + cryptCesar);
        System.out.println("Cesar decrypt: " + decryptCesar);

        String formula = "n*n+n*3";
        String cryptTritemius = new Tritemius().encrypt(str.toLowerCase(),formula);
        String decryptTritemius = new Tritemius().decrypt(cryptTritemius,formula);
        System.out.println("Tritemius crypt:" + cryptTritemius);
        System.out.println("Tritemius decrypt:" + decryptTritemius);

        String cryptBacon = new Bacon().encrypt(str.toLowerCase());
        String decryptBacon = new Bacon().decrypt(cryptBacon);
        System.out.println("Bacon crypt:" + cryptBacon);
        System.out.println("Bacon decrypt:" + decryptBacon);
        
        String cryptVigenere = new Vigenere().encrypt(str.toLowerCase(), "асд");
        String decryptVigenere = new Vigenere().decrypt(cryptVigenere, "асд");
        System.out.println("Vigenere crypt:" + cryptVigenere);
        System.out.println("Vigenere decrypt:" + decryptVigenere);
        
        String cryptHill = new Hill().encrypt("welcome");
        String decryptHill = new Hill().decrypt(cryptHill);
        System.out.println("Hill crypt:" + cryptHill); 
        System.out.println("Hill decrypt:" + decryptHill);
        
        String cryptPlayfair = new Playfair().encrypt("hidethegoldinthetreestump", "playfair example");
        String decryptPlayfair = new Playfair().decrypt(cryptPlayfair, "playfair example");
        System.out.println("Playfair crypt:" + cryptPlayfair);
        System.out.println("Playfair decrypt:" + decryptPlayfair);

        
        DiffieHellman diffieHellman = new DiffieHellman();
        diffieHellman.setKeyA(24);
        diffieHellman.setKeyB(54);
        diffieHellman.setGenerator(3);
        diffieHellman.setMod(17);
        diffieHellman.createSecretKey();
        String cryptDiffieHellman = diffieHellman.encrypt(str.toLowerCase());
        String decryptDiffieHellman = diffieHellman.decrypt(cryptDiffieHellman);
        System.out.println("crypt DiffieHellman:" + cryptDiffieHellman);
        System.out.println("decryp tDiffieHellman:" + decryptDiffieHellman);
     
        
        RSA rsa = new RSA();
        rsa.setP(3);
        rsa.setQ(11);
        rsa.createKey();
        String cryptRSA = rsa.encrypt("cab",rsa.getPublicNumber(),rsa.getGeneralNumber());
        String decryptRSA = rsa.decrypt(cryptRSA,rsa.getPrivateNumber(),rsa.getGeneralNumber());
        System.out.println("crypt RSA:" + cryptRSA);
        System.out.println("decrypt RSA:"+decryptRSA);
    }
}
