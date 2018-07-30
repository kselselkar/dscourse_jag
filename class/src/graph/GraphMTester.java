package graph;

import java.util.ArrayList;

/**
 * File Name: GraphM.java
 * Graph implementation
 * <p>
 * To Compile: IntUtil.java RandomInt.java GraphM.java GraphMTester.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2018
 */

class GraphMTester {

    static final int UNCONNECTED = 0;
    static final int INFINITY = Integer.MAX_VALUE;
    private static final IntUtil u = new IntUtil();

    GraphMTester() {

    }


    private void testDFS() {
      {
            //DAG
            GraphM g = new GraphM();
            String[][] e = {
                    {"1", "2"},
                    {"1", "3"},
                    {"2", "4"},
                    {"3", "4"},
                    {"4", "5"},
            };
            GraphType t = GraphType.DIRECTED;
            g.buildGraph(t, e);
          //  g.printMatrix();
            g.writeDot("dfs1.dot");

            String s = "1"; //Starting city
            ArrayList<String> ans = new ArrayList<String>();
            boolean[] cycle = {false};
            int[] work = {0};
            g.dfs("Example 1", s, cycle, work, ans);
        }
       {
            //Directed graph with cycle
            GraphM g = new GraphM();
            String[][] e = {
                    {"0", "1"},
                    {"0", "2"},
                    {"1", "3"},
                    {"2", "3"},
                    {"3", "4"},
                    {"4", "2"},
            };
            GraphType t = GraphType.DIRECTED;
            g.buildGraph(t, e);
           // g.printMatrix();
            g.writeDot("dfs1.dot");

            String s = "0"; //Starting city
            ArrayList<String> ans = new ArrayList<String>();
            boolean[] cycle = {false};
            int[] work = {0};
            g.dfs("Example 2", s, cycle, work, ans);
            //g.dfs("",s,cycle,work,ans) ; //This will not print
        }
       /* {
            //UnDirected graph with cycle
            GraphM g = new GraphM();
            String[][] e = {
                    {"1", "0"},
                    {"1", "2"},
                    {"0", "3"},
                    {"0", "2"},
                    {"3", "4"},
            };
            GraphType t = GraphType.UNDIRECTED;
            g.buildGraph(t, e);
            g.printMatrix();
            g.writeDot("dfs3.dot");

            String s = "1"; //Starting city
            ArrayList<String> ans = new ArrayList<String>();
            boolean[] cycle = {false};
            int[] work = {0};
            g.dfs("Example 3", s, cycle, work, ans);
            //g.dfs("",s,cycle,work,ans) ; //This will not print
        }*/
        {
            //UnDirected graph with NO cycle
            GraphM g = new GraphM();
            String[][] e = {
                    {"1", "2"},
                    {"1", "4"},
                    {"1", "3"},
            };
            GraphType t = GraphType.UNDIRECTED;
            g.buildGraph(t, e);
       //     g.printMatrix();
            g.writeDot("dfs4.dot");

            String s = "1"; //Starting city
            ArrayList<String> ans = new ArrayList<String>();
            boolean[] cycle = {false};
            int[] work = {0};
            g.dfs("Example 4", s, cycle, work, ans);
            //g.dfs("",s,cycle,work,ans) ; //This will not print
        }

        {
            //Directed weighted Acyclic graph
            GraphM g = new GraphM();
            String[][] e = {
                    {"0", "2", "5"},
                    {"0", "3", "3"},
                    {"0", "1", "14"},
                    {"1", "6", "6"},
                    {"1", "4", "7"},
                    {"2", "5", "2"},
                    {"2", "4", "3"},
                    {"3", "2", "11"},
                    {"3", "4", "7"},
                    {"3", "1", "6"},
                    {"4", "6", "5"},
                    {"5", "6", "7"},

            };
            GraphType t = GraphType.WEIGHTED_DIRECTED;
            g.buildGraph(t, e);
        //    g.printMatrix();
            g.writeDot("dfs5.dot");

            String s = "0"; //Starting city
            ArrayList<String> ans = new ArrayList<String>();
            boolean[] cycle = {false};
            int[] work = {0};
            g.dfs("Example 5", s, cycle, work, ans);
        }
    }

    private void testBench() {
        testDFS();
    }

    public static void main(String[] args) {
        System.out.println("GraphMTester STARTS");
        GraphMTester m = new GraphMTester();
        m.testBench();
        System.out.println("GraphM ENDS");
    }
}