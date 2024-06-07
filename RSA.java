package cs;
import java.util.*;

public class RSA {
	//2 large prime no.s
	static int p = 11;
	static int q = 13;
	static int n,tot,e,d;
	static int m = 5;
	static int c,pl;
	// Function to calculate the GCD of two numbers
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
    public static int generateRandomNumberWithGCD(int lowerLimit, int upperLimit) {
        Random random = new Random();
        int other = upperLimit;
        int randomNumber;

        do {
            randomNumber = random.nextInt(upperLimit - lowerLimit + 1) + lowerLimit;
            boolean hasGCD1 = true;
                if (gcd(randomNumber, other) != 1) {
                    hasGCD1 = false;
                    break;
                }

            if (hasGCD1) {
                break;
            }
        } while (true);

        return randomNumber;
    }
    public static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return 1;
    }

    public static int modExp(int base, int exponent, int modulus) {
        int result = 1;
        base = base % modulus;
        while (exponent > 0) {
            if (exponent % 2 == 1) {
                result = (result * base) % modulus;
            }
            exponent = exponent >> 1;
            base = (base * base) % modulus;
        }
        return result;
    }
    public static void key_gen()
    {
		n=p*q; // computation of n
		tot = (p-1)*(q-1); // totient fn
		//e = generateRandomNumberWithGCD(1,tot); // select e such that GCD(e,tot)=1
		e = 7;
		System.out.printf("Randomly generated e: %d",e);
		//calculating d
		d = modInverse(e, tot);
	    System.out.printf("\nd: %d",d);
    }
    public static void encryption()
    {
    	c = modExp(m, e, n);
    	System.out.printf("\nCipher Text: %d",c);
    }
    public static void decryption()
    {
    	pl = modExp(c, d, n);
    	System.out.printf("\nPlain Text: %d",pl);
    }
	public static void main(String[] args)
	{
	    key_gen();
	    encryption();
	    decryption();
	}
}
