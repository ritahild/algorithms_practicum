public class fib_loop {
    public static int fib(int n) {
        if (n <= 1) {
            return n;
        }
        int a = 1;
        int b = 1;

        for (int i = 2; i < n; i++) {
            int temp = a;
            a += b;
            b = temp;
        }

        return a;
    }

    public static void main(String[] args) {
        int fn = fib(6);
        System.out.println( fn);
    }


}
