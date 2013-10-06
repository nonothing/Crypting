

public class Test {
    public static void main(String args[]){
        
        String str= "����� ���� ������ ����������� ����� ";
        String cryptXOR = new XOR().encrypt(str.toLowerCase(), 4);
        String decryptXOR= new XOR().decrypt(cryptXOR, 4);
        System.out.println("XOR crypt: " + cryptXOR);
        System.out.println("XOR decrypt: " + decryptXOR);
        
        String cryptCesar = new Cesar().encrypt(str.toLowerCase(), 3);
        String decryptCesar = new Cesar().decrypt(cryptCesar, 3);
        System.out.println("Cesar crypt: "+cryptCesar);
        System.out.println("Cesar decrypt: "+ decryptCesar);
        
        String formula = "n*n+n*3";
        String cryptTritemius = new Tritemius().encrypt(str.toLowerCase(), formula);
        String decryptTritemius = new Tritemius().decrypt(cryptTritemius, formula);       
        System.out.println("Tritemius crypt:" + cryptTritemius);
        System.out.println("Tritemius decrypt:" + decryptTritemius);
        
    }
}
