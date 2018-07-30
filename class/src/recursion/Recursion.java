package recursion;

import HW1.IntUtil;

public class Recursion {

    IntUtil u = new IntUtil();

    //n,n-1,n-2..n
    private void perm_r(int[] a, int s, int e, int[] d) {
        if (s == e) {
            System.out.print(++d[0]);
            u.pLn(a);
        } else {
            for (int i = s; i <= e; i++) {
                u.swap(a, i, s);
                //reducing n by 1 .,
                perm_r(a, s + 1, e, d);
                u.swap(a, i, s);
            }
        }
    }

    void perm(int n) {
        int a[] = new int[n];
        int[] c = {0};
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }
        perm_r(a, 0, a.length - 1, c);
    }

    //you cannot build fibonaci using recursion.
    private int fib(int n){
        if(n<2){
            return n;
        }
        return (fib(n-1)+fib(n-2));
    }
    public static void main(String[] args) {
        Recursion r = new Recursion();
        r.perm(3);
        System.out.println("fib "+r.fib(10));
    }
}
