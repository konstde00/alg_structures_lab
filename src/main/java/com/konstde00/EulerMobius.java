package com.konstde00;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class EulerMobius {
    public static BigInteger gcd(BigInteger a, BigInteger b) {
        return b.equals(BigInteger.ZERO) ? a : gcd(b, a.mod(b));
    }

    public static BigInteger eulerTotient(BigInteger n) {
        BigInteger result = BigInteger.ONE;
        for (BigInteger i = BigInteger.valueOf(2); i.compareTo(n) < 0; i = i.add(BigInteger.ONE)) {
            if (gcd(i, n).equals(BigInteger.ONE)) {
                result = result.add(BigInteger.ONE);
            }
        }
        return result;
    }

    public static int mobius(BigInteger n) {
        if (n.equals(BigInteger.ONE)) return 1;
        BigInteger tempN = new BigInteger(n.toString());
        BigInteger p = BigInteger.valueOf(2);
        int primeFactors = 0;
        while (tempN.compareTo(BigInteger.ONE) > 0 && (p.multiply(p)).compareTo(tempN) <= 0) {
            if (tempN.mod(p).equals(BigInteger.ZERO)) {
                tempN = tempN.divide(p);
                primeFactors++;
                if (tempN.mod(p).equals(BigInteger.ZERO)) return 0;  // Square factor found
            }
            p = (p.equals(BigInteger.valueOf(2))) ? BigInteger.valueOf(3) : p.add(BigInteger.valueOf(2));
        }
        return (primeFactors % 2 == 0) ? 1 : -1;
    }

    public static BigInteger lcm(List<BigInteger> nums) {
        BigInteger result = BigInteger.ONE;
        for (BigInteger num : nums) {
            result = (result.multiply(num)).divide(gcd(result, num));
        }
        return result;
    }
    public static void main() {
        BigInteger n = new BigInteger("12345");

        System.out.println("Euler's Totient of " + n + " is " + eulerTotient(n));
        System.out.println("Möbius function of " + n + " is " + mobius(n));

        List<BigInteger> nums = (Arrays.asList(n, n.add(BigInteger.ONE), n.add(BigInteger.valueOf(2))));
        System.out.println("LCM of the set is " + lcm(nums));
    }
}
