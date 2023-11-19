package com.konstde00;

import java.math.BigInteger;

public class Cipolla {

    private static class Complex {
        BigInteger real, imag;

        Complex(BigInteger real, BigInteger imag) {
            this.real = real;
            this.imag = imag;
        }

        Complex multiply(Complex o, BigInteger p) {
            BigInteger r = (real.multiply(o.real).add(imag.multiply(o.imag).multiply(p))).mod(p);
            BigInteger i = (real.multiply(o.imag).add(imag.multiply(o.real))).mod(p);
            return new Complex(r, i);
        }

        Complex pow(BigInteger exp, BigInteger p) {
            Complex result = new Complex(BigInteger.ONE, BigInteger.ZERO);
            Complex base = this;
            while (!exp.equals(BigInteger.ZERO)) {
                if (exp.mod(BigInteger.TWO).equals(BigInteger.ONE)) {
                    result = result.multiply(base, p);
                }
                base = base.multiply(base, p);
                exp = exp.divide(BigInteger.TWO);
            }
            return result;
        }
    }

    public static BigInteger cipolla(BigInteger n, BigInteger p) {
        if (modExp(n, p.subtract(BigInteger.ONE).divide(BigInteger.TWO), p).equals(p.subtract(BigInteger.ONE))) {
            return null;
        }

        BigInteger a;
        do {
            a = new BigInteger(p.bitLength(), new java.util.Random());
        } while (!modExp(a.multiply(a).subtract(n), p.subtract(BigInteger.ONE).divide(BigInteger.TWO), p)
                .equals(p.subtract(BigInteger.ONE)));

        Complex w = new Complex(a, BigInteger.ONE);
        Complex res = w.pow(p.add(BigInteger.ONE).divide(BigInteger.TWO), p);
        return res.real;
    }

    private static BigInteger modExp(BigInteger base, BigInteger exp, BigInteger mod) {
        return base.modPow(exp, mod);
    }

    public static void main() {

        BigInteger n = new BigInteger("10");
        BigInteger p = new BigInteger("13");

        BigInteger result = cipolla(n, p);
        if (result != null) {
            System.out.println("A square root of " + n + " mod " + p + " is: " + result);
        } else {
            System.out.println("No square root exists for " + n + " mod " + p);
        }
    }
}
