package graph;


import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Character.getName;

public class GraphM {

    //number of vertices
     int numV;
    //number of edges
     int numE;

     int[][] m;

     ArrayList<String> nodenames;

     HashMap<String, Integer> hm;

     IntUtil u = new IntUtil();

     GraphType t;

    public GraphM() {
        numV = 0;
        numE = 0;
        t = GraphType.NONE;
        nodenames = new ArrayList<>();
        hm = new HashMap<>();
        m = null;
    }

    public void buildGraph(GraphType t1, String[][] e) {
        t = t1;
        numE = e.length;
        for (String[] a : e) {
            if (t1 == GraphType.DIRECTED || t1 == GraphType.UNDIRECTED) {
                u.myassert(a.length == 2);
            } else if (t1 == GraphType.WEIGHTED_DIRECTED || t1 == GraphType.WEIGHTED_UNDIRECTED) {
                u.myassert(a.length == 3);
            } else {
                u.myassert(false);
            }
            for (int j = 0; j < 2; j++) {
                insertOrFind(a[j], false);
            }
        }
        m = new int[numV][numV];
        if (t1 == GraphType.WEIGHTED_DIRECTED || t1 == GraphType.WEIGHTED_UNDIRECTED) {
            initializeMatrix(GraphBaseTest1.INFINITY);
        } else {
            initializeMatrix(GraphBaseTest1.UNCONNECTED);
        }

        int w = 1;
        for (String[] a : e) {
            int p1 = insertOrFind(a[0], true);
            int p2 = insertOrFind(a[1], true);
            if (a.length == 3) {
                w = Integer.parseInt(a[2]);
            }
            setWeight(p1, p2, w);
        }

    }

    private void setWeight(int index1, int index2, int val) {
        if (t == GraphType.UNDIRECTED) {
            m[index1][index2] = val;
            m[index2][index1] = val;
        } else if (t == GraphType.DIRECTED) {
            m[index1][index2] = val;
        } else if (t == GraphType.WEIGHTED_UNDIRECTED) {
            // int val = Integer.parseInt(graphNodeArray[2]);
            /*if (val == 0) {
                graphZeroValues.add(index1 + ":" + index2);
            }*/
            m[index1][index2] = val;
            m[index2][index1] = val;
        } else if (t == GraphType.WEIGHTED_DIRECTED) {
            /*int val = Integer.parseInt(graphNodeArray[2]);
            if (val == 0) {
                graphZeroValues.add(index1 + ":" + index2);
            }*/
            m[index1][index2] = val;

        }
    }

    private void initializeMatrix(int value) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (i != j) {
                    m[i][j] = value;
                }
            }

        }

    }


    public void dfs(String t,String s, boolean[] cycle, int[] work, ArrayList<String> ans) {
        //CANNOT CHANGE ANYTHING HERE
        GraphMDfs z = new GraphMDfs(t,this,s,cycle,work,ans) ;
    }

    public int TSPBruteForce(String start, boolean show) {
        //You cannot change anything below
        //1 is the name of the algorithm that implements TSP with a complexity of factorial(n-1)
        int[] cost = {0};
        GraphMTSP t = new GraphMTSP(this, start, 1, cost, show);
        return cost[0];
    }


    private int insertOrFind(String node, boolean elementPresent) {
        int index = 0;
        if (elementPresent) {
            index = hm.get(node);
        } else {
            boolean f = hm.containsKey(node);
            if (!f) {
                hm.put(node, numV++);
                nodenames.add(node);
                u.myassert(nodenames.size() == numV);
                index = numV - 1;
            }
        }
        return index;
    }


    public void printMatrix() {
        System.out.println();
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] == GraphBaseTest1.INFINITY) {
                    System.out.print("L ");
                } else {
                    System.out.print(m[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public void writeDot(String baseFname) {

    }
}
