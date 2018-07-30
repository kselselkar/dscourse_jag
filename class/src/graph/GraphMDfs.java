package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * File Name: GraphMDfs.java
 * Depth first search algorithm
 * <p>
 * To Compile: IntUtil.java RandomInt.java GraphMtester.java GraphM.java GraphMDfs.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2018
 */

class GraphMDfs {
    private String t; //Title of the graph. If empty do not print
    private GraphM g;
    private String start; //Starting city
    private boolean[] cycle;//True if graph has cycle
    private int[] work; //Total work done
    private ArrayList<String> ans; //All cites that can be visited starting s exactly once
    //YOU CAN HAVE ANY NUMBER OF PRIVATE VARIABLES
    //YOU CAN HAVE ANY NUMBER OF PRIVATE FUNCTIONS
    private Map<String, GraphColor> nodeColors = new HashMap();

    enum GraphColor {
        GREEN, RED, BLUE
    }

    GraphMDfs(String t, GraphM g, String s, boolean[] cycle, int[] work, ArrayList<String> ans) {
        this.t = t;
        this.g = g;
        this.start = s;
        this.cycle = cycle;
        this.work = work;
        this.ans = ans;
        //CALL YOUR PRIVATE ROUTINE HERE
        dfs();
        printDFS();
        assertDFS();
    }

    private void assertDFS() {
        if (cycle[0] == false) {
            //ASSERT THAT DFS ORDER IS RIGHT
            //THAT means for a node, all the fanins must be visited before
            boolean []values=new boolean[ans.size()];
            String[] ts = ans.toArray(new String[ans.size()]);
            for (int i = ts.length-1; i >=0; i--) {
                values[i]=true;

            }
            for (boolean value : values) {
                if(!value){
                    System.out.println("assert failed");
                }
            }
            System.out.println("dfs assert passed");
        }
    }

    private void printDFS() {
        if (t.isEmpty() == false) {
            //WRITE CODE HERE
            System.out.println(t);
            if (cycle[0]) {
                System.out.println("GRAPH HAS CYCLE");
            }else{
                System.out.println("Topological order " + ans);
                System.out.println("Total time recursion called " + work[0]);
            }
        }
    }


    private void dfs() {
        colorNodes();
        //WRITE YOUR CODE HERE
        dfs_r(start);
    }

    private void dfs_r(String vertex) {
        if (nodeColors.get(vertex) == GraphColor.GREEN) {
            nodeColors.put(vertex, GraphColor.BLUE);
            int[] rowValues = g.m[g.hm.get(vertex)];
            for (int i = 0; i < rowValues.length; i++) {
                int rowValue = rowValues[i];
                if ((g.t == GraphType.UNDIRECTED || g.t == GraphType.DIRECTED) && rowValue == 1 && i != g.hm.get(vertex)) {
                    if (g.t == GraphType.UNDIRECTED && nodeColors.get(g.nodenames.get(i)) == GraphColor.BLUE) {

                    } else {
                        work[0] = work[0] + 1;
                        dfs_r(g.nodenames.get(i));
                    }
                } else if ((g.t == GraphType.WEIGHTED_DIRECTED || g.t == GraphType.WEIGHTED_UNDIRECTED) && rowValue != GraphMTester.INFINITY && i != g.hm.get(vertex)) {
                    if (g.t == GraphType.WEIGHTED_UNDIRECTED && nodeColors.get(g.nodenames.get(i)) == GraphColor.BLUE) {

                    } else {
                        work[0] = work[0] + 1;
                        dfs_r(g.nodenames.get(i));
                    }
                }

            }
            nodeColors.put(vertex, GraphColor.RED);
            ans.add(vertex);
        } else if (nodeColors.get(vertex) == GraphColor.BLUE) {
            //System.out.println("This graph has cycle " + vertex);
            cycle[0] = true;

        } else if (nodeColors.get(vertex) == GraphColor.RED) {
            //  System.out.println("This graph is red " + vertex);
        }

    }


    private void colorNodes() {
        for (String nodename : g.nodenames) {
            nodeColors.put(nodename, GraphColor.GREEN);
        }
    }

    public static void main(String[] args) {
        System.out.println("GraphMDfs STARTS");
        System.out.println("use GraphMTesterBST.java to test");
        System.out.println("GraphMDfs ENDS");
    }

}