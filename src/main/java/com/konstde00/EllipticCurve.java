package com.konstde00;

import java.util.ArrayList;
import java.util.Scanner;

public class EllipticCurve {

    public static void main() {
        Scanner scanner = new Scanner(System.in);
        int n, p;
        System.out.println("P: ");
        p = scanner.nextInt();
        n = p;

        int[][] LHS = new int[2][n];
        int[][] RHS = new int[2][n];

        System.out.println("a: ");
        int a = scanner.nextInt();
        System.out.println("b: ");
        int b = scanner.nextInt();

        System.out.println("elliptic curve: y^2 mod " + p + " = (x^3  + " + a + "*x + " + b + ") mod p");

        ArrayList<Integer> arr_x = new ArrayList<>();
        ArrayList<Integer> arr_y = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            LHS[0][i] = i;
            RHS[0][i] = i;
            LHS[1][i] = (int) (Math.pow(i, 3) + a * i + b) % p;
            RHS[1][i] = (i * i) % p;
        }

        int in_c = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (LHS[1][i] == RHS[1][j]) {
                    in_c++;
                    arr_x.add(LHS[0][i]);
                    arr_y.add(RHS[0][j]);
                }
            }
        }

        System.out.println("\npoints are:");
        for (int i = 0; i < in_c; i++) {
            System.out.println((i + 1) + "\t" + arr_x.get(i) + " , " + arr_y.get(i));
        }

        System.out.println("Base: " + arr_x.get(0) + "," + arr_y.get(0));

        System.out.println("Enter the random number d");
        int d = scanner.nextInt();

        int Qx = d * arr_x.get(0);
        int Qy = d * arr_y.get(0);

        System.out.println("Enter the random number k");
        int k = scanner.nextInt();

        System.out.println("Enter the message to be sent");
        int M = scanner.nextInt();
        System.out.println("The message: " + M);

        int c1x = k * arr_x.get(0);
        int c1y = k * arr_y.get(0);
        System.out.println("Value of C1: " + c1x + ", " + c1y);

        int c2x = k * Qx + M;
        int c2y = k * Qy + M;
        System.out.println("Value of C2: " + c2x + ", " + c2y);

        System.out.println("The message received:");
        int Mx = c2x - d * c1x;
        System.out.println(Mx);

        scanner.close();
    }
}
