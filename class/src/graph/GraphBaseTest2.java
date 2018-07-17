package graph;

public class GraphBaseTest2 {

    static final int UNCONNECTED = 0;
    static final int INFINITY = Integer.MAX_VALUE;

    GraphBaseTest2() {

    }

    private void u1() {
        Graph g = new Graph();
        String[][] e = {
                {"1", "2"},
                {"1", "3"},
                {"1", "4"},
                {"2", "5"},
                {"2", "4"},
                {"3", "6"},
                {"3", "4"},
                {"4", "5"},
                {"4", "7"},
                {"7", "5"},
                {"7", "6"},
                {"6", "4"},

        };
        GraphType t = GraphType.UNDIRECTED;
        g.buildGraph(t, e);
        g.printMatrix();
        g.writeDot("u1.dot");
    }

    private void uw1() {
        Graph g = new Graph();
        String[][] e = {
                {"1", "2", "6"},
                {"1", "3", "1"},
                {"1", "4", "5"},
                {"2", "3", "5"},
                {"2", "5", "3"},
                {"3", "4", "5"},
                {"3", "5", "4"},
                {"3", "6", "4"},
                {"4", "6", "2"},
                {"5", "6", "6"},
        };
        GraphType t = GraphType.WEIGHTED_UNDIRECTED;
        g.buildGraph(t, e);
        g.printMatrix();
        g.writeDot("uw1.dot");
    }

    private void d1() {
        Graph g = new Graph();
        String[][] e = {{"A", "B"},
                {"E", "F"},
                {"E", "D"},
                {"C", "E"},
                {"B", "C"},
                {"D", "B"},

        };
        GraphType t = GraphType.DIRECTED;
        g.buildGraph(t, e);
        g.printMatrix();
        g.writeDot("d1.dot");
    }

    private static void dw1() {
        Graph g = new Graph();
        String[][] e = {
                {"A", "C", "12"},
                {"A", "D", "60"},
                {"B", "A", "10"},
                {"C", "B", "20"},
                {"C", "D", "32"},
                {"E", "A", "7"},
        };
        GraphType t = GraphType.WEIGHTED_DIRECTED;
        g.buildGraph(t, e);
        g.printMatrix();
        g.writeDot("dw1.dot");
    }

    private void testGraphRepresentation() {
        u1();
        uw1();
        d1();
        dw1();
    }

    private void testBench() {
        testGraphRepresentation();
    }


    public static void main(String[] args) {
        System.out.println("GraphMTester STARTS");
        GraphBaseTest2 m = new GraphBaseTest2();
        m.testBench();
        System.out.println("Graph ENDS");
    }
}

