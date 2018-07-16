package HW2;

import utils.IntUtil;

public class SwapTest {

    IntUtil u=new IntUtil();

    private void perm_r(int[] a, int s, int e) {
        if (s == e) {
            u.pLn(a);
            return;
        }
        for (int i = s; i <= e; ++i) {
            u.swap(a, i, s);
            perm_r(a, s + 1, e);
            u.swap(a, i, s);
        }
    }

    public void perm(int n) {
        int a[] = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = i;
        }
        perm_r(a, 0, a.length - 1);
    }

    public static void main(String[] args) {
        SwapTest sw=new SwapTest();
        sw.perm(3);
    }

}
