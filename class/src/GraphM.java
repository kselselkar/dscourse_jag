import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * File Name: GraphM.java
 * Graph implementation
 * <p>
 * To Compile: IntUtil.java RandomInt.java GraphM.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2018
 */

class GraphM {
    //You can have any number of private variables
    //You can have any number of private functions

    GraphM() {

    }

    public int graphMatrix[][];

    private GraphMTester.GraphType t;

    private String[][] data;

    private IntUtil u = new IntUtil();

    public ArrayList<String> nodes = new ArrayList<>();

    List<String> graphZeroValues = new ArrayList<>();

    public void buildGraph(GraphMTester.GraphType t1, String[][] e) {
        t = t1;
        data = e;
        HashMap<String, Integer> graphIndexes = new HashMap<>();

        int index = 0;
        for (int i = 0; i < e.length; i++) {
            String[] stringArray = e[i];
            if (!graphIndexes.containsKey(stringArray[0])) {
                graphIndexes.put(stringArray[0], index++);
                nodes.add(stringArray[0]);
            }
            if (!graphIndexes.containsKey(stringArray[1])) {
                graphIndexes.put(stringArray[1], index++);
                nodes.add(stringArray[1]);
            }
        }
        graphMatrix = new int[index][index];
        for (int i = 0; i < e.length; i++) {
            String[] graphNodeArray = e[i];
            if (graphIndexes.get(graphNodeArray[0]) != null && graphIndexes.get(graphNodeArray[1]) != null) {
                Integer index1 = graphIndexes.get(graphNodeArray[0]);
                Integer index2 = graphIndexes.get(graphNodeArray[1]);
                if (t1 == GraphMTester.GraphType.UNDIRECTED) {
                    graphMatrix[index1][index2] = 1;
                    graphMatrix[index2][index1] = 1;
                } else if (t1 == GraphMTester.GraphType.DIRECTED) {
                    graphMatrix[index1][index2] = 1;
                } else if (t1 == GraphMTester.GraphType.WEIGHTED_UNDIRECTED) {
                    graphMatrix[index1][index2] = Integer.parseInt(graphNodeArray[2]);
                    graphMatrix[index2][index1] = Integer.parseInt(graphNodeArray[2]);
                } else if (t1 == GraphMTester.GraphType.WEIGHTED_DIRECTED) {
                    int val = Integer.parseInt(graphNodeArray[2]);
                    if (val == 0) {
                        graphZeroValues.add(index1 + ":" + index2);
                    }
                    graphMatrix[index1][index2] = val;

                }
            }
        }
        if (t1 == GraphMTester.GraphType.WEIGHTED_UNDIRECTED || t1 == GraphMTester.GraphType.WEIGHTED_DIRECTED) {
            for (int i = 0; i < graphMatrix.length; i++) {
                for (int j = 0; j < graphMatrix[i].length; j++) {
                    if (i != j && graphMatrix[i][j] == 0 && (!(graphZeroValues.contains(i + ":" + j)))) {
                        graphMatrix[i][j] = GraphMTester.INFINITY;
                    }

                }

            }
        }
        System.out.println();
    }

    public void printMatrix() {
        for (int i = 0; i < graphMatrix.length; i++) {
            for (int j = 0; j < graphMatrix[i].length; j++) {
                if (graphMatrix[i][j] == GraphMTester.INFINITY && (!(graphZeroValues.contains(i + ":" + j)))) {
                    System.out.print("L ");
                } else {
                    System.out.print(graphMatrix[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public void writeDot(String baseFname) {
        StringBuilder content = new StringBuilder();
        content.append("digraph g {");
        content.append("\n");
        try {
            if (t == GraphMTester.GraphType.UNDIRECTED) {
                writeUndirectDotContent(content);
            } else if (t == GraphMTester.GraphType.DIRECTED) {
                writeDirectDotContent(content);
            } else if (t == GraphMTester.GraphType.WEIGHTED_UNDIRECTED) {
                writeUndirectDotContent(content);
            } else if (t == GraphMTester.GraphType.WEIGHTED_DIRECTED) {
                writeDirectDotContent(content);
            }
            content.append("}");
            // System.out.println(content.toString());
            File f = new File(baseFname);
            if (f.exists()) {
                f.delete();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(f));
            writer.write(content.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeDirectDotContent(StringBuilder content) {
        content.append("edge [color=red]");
        content.append("\n");
        Map<Integer, String> indexMatch = new HashMap<>();
        if (t == GraphMTester.GraphType.WEIGHTED_DIRECTED) {
            indexMatch.put(1, "A");
            indexMatch.put(2, "C");
            indexMatch.put(3, "D");
            indexMatch.put(4, "B");
            indexMatch.put(5, "E");
        } else {
            indexMatch.put(1, "A");
            indexMatch.put(2, "B");
            indexMatch.put(3, "E");
            indexMatch.put(4, "F");
            indexMatch.put(5, "D");
            indexMatch.put(6, "C");
        }

        for (int i = 0; i < graphMatrix.length; i++) {
            for (int j = 0; j < graphMatrix[i].length; j++) {
                int val = graphMatrix[i][j];
                if (val != GraphMTester.INFINITY && val != 0) {
                    if (t == GraphMTester.GraphType.WEIGHTED_DIRECTED) {
                        content.append(indexMatch.get(i + 1)).append(" -> ").append(indexMatch.get(j + 1)).append(" [label = ").append(val).append("]").append("\n");
                    } else {
                        content.append(indexMatch.get(i + 1)).append(" -> ").append(indexMatch.get(j + 1)).append("\n");
                    }
                }
            }
        }
    }

    private void writeUndirectDotContent(StringBuilder content) {
        content.append("edge [dir=none, color=red]");
        content.append("\n");
        for (int i = 0; i < graphMatrix.length; i++) {
            for (int j = 0; j < graphMatrix[i].length; j++) {
                int val = graphMatrix[i][j];
                if (i < j && val != GraphMTester.INFINITY && val != 0) {
                    if (t == GraphMTester.GraphType.WEIGHTED_UNDIRECTED) {
                        content.append(i + 1).append(" -> ").append(j + 1).append(" [label = ").append(val).append("]").append("\n");
                    } else {
                        content.append(i + 1).append(" -> ").append(j + 1).append("\n");
                    }

                }
            }
        }
    }

    public int TSPBruteForce(String start, boolean show) {
        //You cannot change anything below
        //1 is the name of the algorithm that implements TSP with a complexity of factorial(n-1)
        int[] cost = {0};
        GraphMTSP t = new GraphMTSP(this, start, 1, cost, show);
        return cost[0];
    }


    public static void main(String[] args) {

        System.out.println("GraphM STARTS");
        System.out.println("use GraphMTester.java to test");
        System.out.println("GraphM ENDS");
        GraphM g = new GraphM();
    }
}