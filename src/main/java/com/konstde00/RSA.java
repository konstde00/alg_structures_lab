package com.konstde00;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {
    private BigInteger n, d, e;

    private static final BigInteger ONE = BigInteger.ONE;
    private static final SecureRandom RANDOM = new SecureRandom();

    public RSA(int bits) {
        BigInteger p = new BigInteger(bits / 2, 100, RANDOM);
        BigInteger q = new BigInteger(bits / 2, 100, RANDOM);
        n = p.multiply(q);

        BigInteger phi = p.subtract(ONE).multiply(q.subtract(ONE));
        e = new BigInteger("65537");
        d = e.modInverse(phi);
    }

    public BigInteger encrypt(BigInteger message) {
        return message.modPow(e, n);
    }

    public BigInteger decrypt(BigInteger ciphertext) {
        return ciphertext.modPow(d, n);
    }

    public static void main() {
        RSA rsa = new RSA(512);


        BigInteger message = new BigInteger("1234567890");


        BigInteger ciphertext = rsa.encrypt(message);
        System.out.println("Encrypted message: " + ciphertext);


        BigInteger decryptedMessage = rsa.decrypt(ciphertext);
        System.out.println("Decrypted message: " + decryptedMessage);
    }
}
