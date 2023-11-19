package com.konstde00;

import java.math.BigInteger;
import java.util.Random;

public class PollardsRhoAlgorithm {
    private static final Random random = new Random(10);

    private static BigInteger gcd(BigInteger a, BigInteger b) {
        return a.gcd(b);
    }

    public static BigInteger pollardsRho(BigInteger n) {
        if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) return BigInteger.TWO;

        BigInteger x = new BigInteger(n.bitLength(), random);
        BigInteger y = x;
        BigInteger c = new BigInteger(n.bitLength(), random);
        BigInteger d = BigInteger.ONE;

        while (d.equals(BigInteger.ONE)) {
            x = (x.multiply(x).add(c)).mod(n);
            y = (y.multiply(y).add(c).multiply(y).add(c)).mod(n);
            d = gcd(x.subtract(y).abs(), n);
        }

        return d;
    }

    public static void main() {
        BigInteger n = new BigInteger("104729");

        System.out.println("One non-trivial factor of " + n + " is: " + pollardsRho(n));
    }
}
