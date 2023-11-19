package com.konstde00;

import java.math.BigInteger;

public class ChineseRemainderTheorem {
    public static BigInteger modInverse(BigInteger a, BigInteger m) {
        return a.modInverse(m);
    }

    public static BigInteger chineseRemainder(BigInteger[] num, BigInteger[] rem) {
        int k = num.length;
        BigInteger prod = BigInteger.ONE;
        for (int i = 0; i < k; i++) {
            prod = prod.multiply(num[i]);
        }

        BigInteger result = BigInteger.ZERO;
        for (int i = 0; i < k; i++) {
            BigInteger pp = prod.divide(num[i]);
            result = result.add(rem[i].multiply(modInverse(pp, num[i])).multiply(pp));
        }

        return result.mod(prod);
    }

    public static void main() {
        BigInteger[] num = {
                new BigInteger("3"),
                new BigInteger("4"),
                new BigInteger("5")
        };
        BigInteger[] rem = {
                new BigInteger("2"),
                new BigInteger("3"),
                new BigInteger("1")
        };

        System.out.println("The solution to the system of congruences is: " + chineseRemainder(num, rem));
    }
}
