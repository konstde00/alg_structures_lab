package com.konstde00;

import java.math.BigInteger;
import java.util.Random;

public class MillerRabin {
    private static final Random random = new Random();

    private static BigInteger modExp(BigInteger base, BigInteger exp, BigInteger mod) {
        return base.modPow(exp, mod);
    }

    public static boolean isPrime(BigInteger n, int iterations) {
        if (n.equals(BigInteger.TWO) || n.equals(BigInteger.valueOf(3))) {
            return true;
        }
        if (n.compareTo(BigInteger.TWO) < 0 || n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            return false;
        }

        BigInteger d = n.subtract(BigInteger.ONE);
        int r = 0;
        while (d.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            d = d.divide(BigInteger.TWO);
            r++;
        }

        for (int i = 0; i < iterations; i++) {
            BigInteger a = (new BigInteger(n.bitLength(), random)).mod(n.subtract(BigInteger.ONE)).add(BigInteger.ONE);
            BigInteger x = modExp(a, d, n);
            if (x.equals(BigInteger.ONE) || x.equals(n.subtract(BigInteger.ONE))) {
                continue;
            }
            int j = 0;
            for (; j < r - 1; j++) {
                x = modExp(x, BigInteger.TWO, n);
                if (x.equals(n.subtract(BigInteger.ONE))) {
                    break;
                }
            }
            if (j == r - 1) {
                return false;
            }
        }
        return true;
    }

    public static void main() {
        BigInteger n = new BigInteger("104729");
        int iterations = 5;

        System.out.println(n + " is prime: " + isPrime(n, iterations));
    }
}
