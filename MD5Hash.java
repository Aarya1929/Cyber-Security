package cs;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Hash{

    public static String generateMD5(String input) {
        try {
            // Create an MD5 MessageDigest instance
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Update the digest with the input bytes
            md.update(input.getBytes());

            // Convert the digest to a hexadecimal representation
            byte[] digest = md.digest();
            String result = new BigInteger(1, digest).toString(16);

            // Pad the result with zeros to ensure a 32-character length
            while (result.length() < 32) {
                result = "0" + result;
            }

            return result;
        } catch (NoSuchAlgorithmException e) {
            // Handle the exception appropriately
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String input = "Hello, MD5!";

        // Generate the MD5 hash
        String md5Hash = generateMD5(input);

        // Print the original input and the MD5 hash
        System.out.println("Original: " + input);
        System.out.println("MD5 Hash: " + md5Hash);
    }
}
