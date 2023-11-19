package com.konstde00;

import java.math.BigInteger;

public class LegendreJacobi {
    public static int legendreSymbol(BigInteger a, BigInteger p) {
        BigInteger result = a.modPow(p.subtract(BigInteger.ONE).divide(BigInteger.valueOf(2)), p);
        return result.equals(BigInteger.ONE) ? 1 : (result.equals(p.subtract(BigInteger.ONE)) ? -1 : 0);
    }

    public static int jacobiSymbol(BigInteger a, BigInteger n) {
        if (a.equals(BigInteger.ONE)) return 1;
        if (a.equals(BigInteger.ZERO)) return 0;
        if (a.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
            return jacobiSymbol(a.divide(BigInteger.valueOf(2)), n) * (n.mod(BigInteger.valueOf(8)).equals(BigInteger.ONE) || n.mod(BigInteger.valueOf(8)).equals(BigInteger.valueOf(7)) ? 1 : -1);
        }
        return jacobiSymbol(n.mod(a), a) * (a.mod(BigInteger.valueOf(4)).equals(BigInteger.ONE) || n.mod(BigInteger.valueOf(4)).equals(BigInteger.ONE) ? 1 : -1);
    }

    public static void main() {
        BigInteger a = new BigInteger("65537");
        BigInteger p = new BigInteger("97");
        BigInteger n = new BigInteger("121");

        // Output the results
        System.out.println("The Legendre symbol (a|p) is: " + legendreSymbol(a, p));
        System.out.println("The Jacobi symbol (a|n) is: " + jacobiSymbol(a, n));
    }
}
