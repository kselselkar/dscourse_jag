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

    public enum GraphType {
        NONE, UNDIRECTED, DIRECTED, WEIGHTED_UNDIRECTED, WEIGHTED_DIRECTED
    }

    static final int UNCONNECTED = 0;
    static final int INFINITY = Integer.MAX_VALUE;
    private static final IntUtil u = new IntUtil();

    GraphMTester() {

    }


    private void testTSP(int alg) {
        {
            GraphM g = new GraphM();
            String[][] e = {
                    {"1", "2", "10"},
                    {"1", "3", "15"},
                    {"1", "4", "20"},
                    {"2", "1", "5"},
                    {"2", "3", "9"},
                    {"2", "4", "10"},
                    {"3", "1", "6"},
                    {"3", "2", "13"},
                    {"3", "4", "12"},
                    {"4", "1", "8"},
                    {"4", "2", "8"},
                    {"4", "3", "9"},

            };
            GraphType t = GraphType.WEIGHTED_DIRECTED;
            g.buildGraph(t, e);
            g.printMatrix();
            g.writeDot("tsp1.dot");
            if (alg == 1) {
                int cost = g.TSPBruteForce("1", true);
                u.myassert(cost == 35);
            }
        }
        {
            GraphM g = new GraphM();
            String[][] e = {
                    {"1", "2", "1"},
                    {"1", "3", "2"},
                    {"1", "4", "3"},
                    {"2", "1", "5"},
                    {"2", "3", "4"},
                    {"2", "4", "3"},
                    {"3", "1", "6"},
                    {"3", "2", "1"},
                    {"3", "4", "8"},
                    {"4", "1", "14"},
                    {"4", "2", "10"},
                    {"4", "3", "11"},

            };
            GraphType t = GraphType.WEIGHTED_DIRECTED;
            g.buildGraph(t, e);
            g.printMatrix();
            g.writeDot("tsp2.dot");
            if (alg == 1) {
                int cost = g.TSPBruteForce("4", true);
                u.myassert(cost == 20);
            }
        }
        {
            GraphM g = new GraphM();
            String[][] e = {
                    {"1", "2", "2"},
                    {"1", "3", "0"},
                    {"1", "4", "6"},
                    {"1", "5", "1"},
                    {"2", "1", "1"},
                    {"2", "3", "4"},
                    {"2", "4", "4"},
                    {"2", "5", "2"},
                    {"3", "1", "5"},
                    {"3", "2", "3"},
                    {"3", "4", "1"},
                    {"3", "5", "5"},
                    {"4", "1", "4"},
                    {"4", "2", "7"},
                    {"4", "3", "2"},
                    {"4", "5", "1"},
                    {"5", "1", "2"},
                    {"5", "2", "6"},
                    {"5", "3", "3"},
                    {"5", "4", "6"},

            };
            GraphType t = GraphType.WEIGHTED_DIRECTED;
            g.buildGraph(t, e);
            g.printMatrix();
            g.writeDot("tsp2.dot");
            if (alg == 1) {
                int cost = g.TSPBruteForce("5", true);
                u.myassert(cost == 9);
            }
        }
        {
            GraphM g = new GraphM();
            String[][] e = {
                    {"A", "B", "2"},
                    {"A", "C", "9"},
                    {"B", "A", "1"},
                    {"B", "C", "6"},
                    {"B", "D", "4"},
                    {"C", "B", "7"},
                    {"C", "D", "8"},
                    {"D", "A", "6"},
                    {"D", "B", "3"},
            };
            GraphType t = GraphType.WEIGHTED_DIRECTED;
            g.buildGraph(t, e);
            g.printMatrix();
            g.writeDot("tsp3.dot");
            if (alg == 1) {
                int cost = g.TSPBruteForce("A", true);
                u.myassert(cost == 21);
            }
        }
    }


    private void testBench() {
        testTSP(1);
    }

    public static void main(String[] args) {
        System.out.println("GraphMTester STARTS");
        GraphMTester m = new GraphMTester();
        m.testBench();
        System.out.println("GraphM ENDS");
    }
}