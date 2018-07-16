
public class GraphMtester_1 {
    public enum GraphType {
        NONE, UNDIRECTED, DIRECTED, WEIGHTED_UNDIRECTED, WEIGHTED_DIRECTED
    }

    static final int UNCONNECTED = 0;
    static final int INFINITY = Integer.MAX_VALUE;

    GraphMtester_1() {

    }

    private void u1() {
        GraphM g = new GraphM();
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
        GraphMTester.GraphType t = GraphMTester.GraphType.UNDIRECTED;
        g.buildGraph(t, e);
        g.printMatrix();
        g.writeDot("u1.dot");
    }

    private void uw1() {
        GraphM g = new GraphM();
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
        GraphMTester.GraphType t = GraphMTester.GraphType.WEIGHTED_UNDIRECTED;
        g.buildGraph(t, e);
        g.printMatrix();
        g.writeDot("uw1.dot");
    }

    private void d1() {
        GraphM g = new GraphM();
        String[][] e = {{"A", "B"},
                {"E", "F"},
                {"E", "D"},
                {"C", "E"},
                {"B", "C"},
                {"D", "B"},

        };
        GraphMTester.GraphType t = GraphMTester.GraphType.DIRECTED;
        g.buildGraph(t, e);
        g.printMatrix();
        g.writeDot("d1.dot");
    }

    private static void dw1() {
        GraphM g = new GraphM();
        String[][] e = {
                {"A", "C", "12"},
                {"A", "D", "60"},
                {"B", "A", "10"},
                {"C", "B", "20"},
                {"C", "D", "32"},
                {"E", "A", "7"},
        };
        GraphMTester.GraphType t = GraphMTester.GraphType.WEIGHTED_DIRECTED;
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
        GraphMtester_1 m = new GraphMtester_1();
        m.testBench();
        System.out.println("GraphM ENDS");
    }
}
