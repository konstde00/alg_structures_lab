package com.konstde00;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class BigStepSmallStep {

    private static BigInteger modExp(BigInteger base, BigInteger exponent, BigInteger mod) {
        return base.modPow(exponent, mod);
    }

    public static BigInteger bigStepSmallStep(BigInteger g, BigInteger h, BigInteger p) {
        BigInteger n = (BigInteger.ONE.add(BigInteger.valueOf((long) Math.sqrt(p.subtract(BigInteger.ONE).doubleValue())))).mod(p);

        Map<BigInteger, BigInteger> value = new HashMap<>();
        for (BigInteger j = BigInteger.ZERO; j.compareTo(n) < 0; j = j.add(BigInteger.ONE)) {
            BigInteger value1 = modExp(g, j, p);
            value.put(value1, j);
        }

        BigInteger gn = modExp(g, n.multiply(p.subtract(BigInteger.TWO)), p);

        BigInteger cur = h;
        for (BigInteger i = BigInteger.ZERO; i.compareTo(n) < 0; i = i.add(BigInteger.ONE)) {
            if (value.containsKey(cur)) {
                return i.multiply(n).add(value.get(cur));
            } else {
                cur = cur.multiply(gn).mod(p);
            }
        }

        return BigInteger.valueOf(-1);
    }

    public static void main() {
        BigInteger g = new BigInteger("2");
        BigInteger h = new BigInteger("22");
        BigInteger p = new BigInteger("29");

        BigInteger result = bigStepSmallStep(g, h, p);
        System.out.println("The discrete logarithm of " + h + " base " + g + " modulo " + p + " is: " + result);
    }
}
