import java.io.IOException;

public class Prime {
    

    public static void main(String[] args) throws IOException {
        int n = 10;
        boolean[] primes = new boolean[n + 1];
        System.out.printf("created array of length %d\n", primes.length);
        for (int i = 0; i < n + 1; i++) {
            primes[i] = true;
        }

        for (int divisor = 2; divisor * divisor < n; divisor++) {
            if (primes[divisor]) {
                for (int j = 2*divisor; j < n; j = j + divisor) {
                    primes[j] = false;
                }
            }
        }

        for (int i = 2; i < n + 1; i++) {
            System.out.printf("i = %d, %b \n", i, primes[i]);
        }
    }
}
