package cs;

import java.util.Scanner;

public class cyber {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a word:");
        String pt = scanner.nextLine();
        
        String ew = Encryption(pt);
        System.out.println("Encrypted word:"+ew);
        
        String dw = Decryption(ew);
        System.out.println("Decrypted word:"+dw);
    }
	private static String Encryption(String pt) 
	{
		StringBuilder encrypted = new StringBuilder();
        
		for (int i = 0; i < pt.length(); i++) {
            char currentChar = pt.charAt(i);
                char encryptedChar = (char) ((currentChar - 'a' + 3) % 26 + 'a');
                encrypted.append(encryptedChar);
        }
        return encrypted.toString();
	}
	private static String Decryption(String ew)
	{
		StringBuilder decrypted = new StringBuilder();
		for (int i = 0; i < ew.length(); i++) {
            char currentChar = ew.charAt(i);
                char decryptedChar = (char) ((currentChar - 'a' + 23) % 26 + 'a');
                decrypted.append(decryptedChar);
        }
		return decrypted.toString();	
	}
}
