package cs;

import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.KeyAgreement;
import java.util.Base64;

public class ecdh{

    public static void main(String[] args) {
        try {
            // Alice generates her key pair
            KeyPair aliceKeyPair = generateKeyPair();

            // Bob generates his key pair
            KeyPair bobKeyPair = generateKeyPair();

            // Alice and Bob exchange public keys
            byte[] aliceSharedSecret = performKeyExchange(aliceKeyPair, bobKeyPair);
            byte[] bobSharedSecret = performKeyExchange(bobKeyPair, aliceKeyPair);

            // Validate that the shared secrets match
            if (MessageDigest.isEqual(aliceSharedSecret, bobSharedSecret)) {
                System.out.println("Shared secrets match! Key exchange successful.");
            } else {
                System.out.println("Error: Shared secrets do not match.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static KeyPair generateKeyPair() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
        ECGenParameterSpec ecGenParameterSpec = new ECGenParameterSpec("secp256r1"); // Specify the curve
        keyPairGenerator.initialize(ecGenParameterSpec);
        return keyPairGenerator.generateKeyPair();
    }

    private static byte[] performKeyExchange(KeyPair keyPair, KeyPair otherKeyPair)
            throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException {
        KeyAgreement keyAgreement = KeyAgreement.getInstance("ECDH");
        keyAgreement.init(keyPair.getPrivate());

        // Serialize and deserialize public keys
        byte[] otherPublicKeyBytes = otherKeyPair.getPublic().getEncoded();
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        X509EncodedKeySpec otherPublicKeySpec = new X509EncodedKeySpec(otherPublicKeyBytes);
        PublicKey otherPublicKey = keyFactory.generatePublic(otherPublicKeySpec);

        keyAgreement.doPhase(otherPublicKey, true);
        return keyAgreement.generateSecret();
    }
}
