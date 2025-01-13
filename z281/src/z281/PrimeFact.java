package z281;

import java.util.LinkedList;
import java.util.Random;

public class PrimeFact {
    static LinkedList<Long> factors = new LinkedList<>();
    static Random ran = new Random();

    public static void main(String[] args) {
        new Frame();
        
        /*
        long n = 888L; // n is the number to factorize 
        primeFact(n); // Find prime factors and add to `factors` list
        
        System.out.printf("Prime factors of %d are: ", n);
        for (long factor : factors) {
            System.out.print(factor + " ");
        }
        */
    }

    // =============METHODS==============
    public static void primeFact(long n) {
        if (n <= 1) return;

        if (isPrime(n)) {  // base case
            factors.add(n);
            return;
        }

        // Check for small prime factors to simplify n, e.g., 2 and 3
        while (n % 2 == 0) {
            factors.add(2L);  // Add long value (2L)
            n /= 2;
        }
        while (n % 3 == 0) {
            factors.add(3L);  // Add long value (3L)
            n /= 3;
        }

        if (n == 1) return;

        // Pollard's Rho Algorithm to find a factor
        long x = 2, y = 2, d = 1;
        long C = ran.nextLong() % (n - 1) + 1; // Generate a random constant for Pollard's Rho
        int attempts = 0;

        while ((d == 1 || d == n) && attempts < 3000) {
            x = (x * x + C) % n;
            y = (y * y + C) % n;
            y = (y * y + C) % n;
            d = gcd(Math.abs(x - y), n);

            // Restart with a new random constant if a cycle is detected
            if (x == y) {
                C = ran.nextLong() % (n - 1) + 1;
                x = y = 2;
            }

            attempts++;
        }

        if (d != 1 && d != n) { // Factorize the discovered factor d and the remaining n / d recursively
            primeFact(d);
            primeFact(n / d);
        } else {
            // If no factor is found, add the remaining number to the list
            factors.add(n);
        }
    }
    
    
    

    private static long gcd(long x, long y) {
        if (y == 0) {
            return x;
        }
        return gcd(y, x % y);
    }

    private static boolean isPrime(long n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (long i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }
}

