package graph;


import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Character.getName;

public class Graph {

    //number of vertices
    private int numV;
    //number of edges
    private int numE;

    private int[][] m;

    private ArrayList<String> nodenames;

    private HashMap<String, Integer> hm;

    private IntUtil u = new IntUtil();

    private GraphType t;

    public Graph() {
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
