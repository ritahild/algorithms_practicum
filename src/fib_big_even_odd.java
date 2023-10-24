import java.math.BigInteger;

public class fib_big_even_odd {
    public static BigInteger[][] Myltimatrix(BigInteger[][] a, BigInteger[][] b) {

        return new BigInteger[][]{


                {a[0][0].multiply(b[0][0]).add(a[0][1].multiply(b[1][0])),
                        a[0][0].multiply(b[0][1]).add(a[0][1].multiply(b[1][1]))},

                {a[1][0].multiply(b[0][0]).add(a[1][1].multiply(b[1][0])),
                        a[1][0].multiply(b[0][1]).add(a[1][1].multiply(b[1][1]))},
        };
    }

    public static BigInteger[][] povermatrix(BigInteger[][] a, int n) {
        if (n == 0) {
            return new BigInteger[][]{

                    {BigInteger.ONE, BigInteger.ZERO},
                    {BigInteger.ZERO, BigInteger.ONE}
            };
        } else if (n % 2 == 0) {

            return povermatrix(Myltimatrix(a, a), n / 2);
        } else {

            return Myltimatrix(povermatrix(a, n - 1), a);
        }
    }

    public static BigInteger fib(int n) {
        if (n == 0) {
            return BigInteger.ZERO;
        }
        BigInteger[][] a = {
                {BigInteger.ONE, BigInteger.ONE},
                {BigInteger.ONE, BigInteger.ZERO}
        };
        BigInteger[][] powerOfA = povermatrix(a, n - 1);
        BigInteger nthFib = powerOfA[0][0];
        return nthFib;
    }

    public static void main(String[] args) {
        String a = fib(6).remainder(BigInteger.valueOf(10)).toString();
        int i = Integer.parseInt(a.trim());
        if (i % 2 == 0){
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}
