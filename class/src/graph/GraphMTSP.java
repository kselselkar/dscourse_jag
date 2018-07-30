package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * File Name: GraphMTSP.java
 * Traveling sales man problem
 * <p>
 * To Compile: IntUtil.java RandomInt.java GraphMtester.java GraphM.java GraphMTSP.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2018
 */

class GraphMTSP {
    private GraphM g;
    private String start; //starting city
    private int alg; //algorithm used
    private int[] tspcost; //minimum TSP cost. To be computed
    private boolean show; //True means print all the steps
    //You can have any number of private variables below

    private IntUtil u = new IntUtil();

    GraphMTSP(GraphM g, String s, int alg, int[] cost, boolean show) {
        this.g = g;
        this.start = s;
        this.alg = alg;
        this.show = show;
        this.tspcost = cost;
        //You can initialize your data structures here

        if (alg == 1) {
            TSPBruteForce();
        }
    }


    private void TSPBruteForce() {
        //Implement the algorithm.
        //Propagate the cost in int[] tspcost //Array of size 1
        //You can have any number of private procedures you want
        String[] pathMatrix = g.nodenames.toArray(new String[g.nodenames.size()]);
        int[][] graphMatrix = g.m;
        List<String[]> paths = new ArrayList<>();
        perm_r(pathMatrix, 0, pathMatrix.length - 1, paths, start);
        int nodeValue = 0;
        // boolean firstPath = false;
        boolean considerValue = true;
        for (String[] path : paths) {
            /*if (firstPath) {
                System.out.println("node value " + nodeValue);
            }
            firstPath = true;
            for (String i : path) {
                System.out.print(i + " ");
            }
            System.out.println();*/
            if (tspcost[0] == 0) {
                tspcost[0] = nodeValue;
            } else if (considerValue && nodeValue <= tspcost[0]) {
                tspcost[0] = nodeValue;
            }
            considerValue = true;
            nodeValue = 0;
            for (int i = 0; i < path.length; i++) {
                int node1 = g.nodenames.indexOf(path[i]);
                int node2;

                if ((i + 1) < path.length) {
                    node2 = g.nodenames.indexOf(path[i + 1]);
                    int graphValue = graphMatrix[node1][node2];
                    if (graphValue != GraphMTesterBST.INFINITY) {
                        nodeValue += graphValue;
                    } else {
                        considerValue = false;
                        break;
                    }
                }
            }
        }
        System.out.println();
    }


    private void perm_r(String[] a, int s, int e, List<String[]> paths, String startPosition) {
        if (s == e) {
            String postion = a[0];
            if (postion.equals(startPosition)) {
                String[] elements = Arrays.copyOf(a, a.length + 1);
                elements[elements.length - 1] = startPosition;
                paths.add(elements);
                pLn(elements);
            }
            return;
        }
        for (int i = s; i <= e; ++i) {
            swapString(a, i, s);
            perm_r(a, s + 1, e, paths, startPosition);
            swapString(a, i, s);
        }
    }

    private void swapString(String[] a, int x, int y) {
        if (x != y) {
            String t = a[x];
            a[x] = a[y];
            a[y] = t;
        }
    }

    void pLn(String[] a, int start, int tend) {
        p(a, start, tend);
        System.out.println();
    }

    void pLn(String[] a) {
        pLn(a, 0, a.length);
    }

    void p(String[] a, int start, int tend) {
        for (int i = start; i < tend; ++i) {
            String v = a[i];
            if (i != start) {
                System.out.print(' ');
            }
            System.out.format("%3s", v);
        }
    }

    public static void main(String[] args) {
        System.out.println("GraphMTSP STARTS");
        System.out.println("use GraphMTesterBST.java to test");
        System.out.println("GraphMTSP ENDS");

    }
}