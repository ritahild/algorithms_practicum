public class fib_binet {
    public static int fib(int n) {

        double a = Math.sqrt(5);
        double b = (1 + a) / 2;

        return (int) Math.ceil(Math.pow(b, n) / b);
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println(fib(n));
    }

}
