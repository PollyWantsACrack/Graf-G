import java.util.*;

class Node {
    int id;
    int degree;
    Set<Integer> neighbors;
    int color;

    public Node(int id) {
        this.id = id;
        this.degree = 0;
        this.neighbors = new HashSet<>();
        this.color = -1;
    }

    public void addNeighbor(int neighborId) {
        this.neighbors.add(neighborId);
        this.degree++;
    }
}

class Graph {
    private Map<Integer, Node> nodes;

    public Graph() {
        this.nodes = new HashMap<>();
    }

    public void addNode(int id) {
        nodes.put(id, new Node(id));
    }

    public void addEdge(int srcId, int dstId) {
        Node src = nodes.get(srcId);
        Node dst = nodes.get(dstId);

        if (src != null && dst != null) {
            src.addNeighbor(dstId);
            dst.addNeighbor(srcId);
        }
    }

    public int findMinColoring() {
        // Sort nodes by degree in descending order
        List<Node> sortedNodes = new ArrayList<>(nodes.values());
        sortedNodes.sort((n1, n2) -> Integer.compare(n2.degree, n1.degree));

        // Assign colors to nodes
        Set<Integer> availableColors = new HashSet<>();
        for (Node node : sortedNodes) {
            for (int color : node.neighbors) {
                availableColors.remove(color);
            }

            if (availableColors.isEmpty()) {
                availableColors.add(node.color + 1);
            }

            node.color = availableColors.iterator().next();
        }

        // Count the number of used colors
        int colorCount = 0;
        for (Node node : nodes.values()) {
            if (node.color != -1) {
                colorCount++;
            }
        }

        return colorCount;
    }
}

public class MinimumColoring { // Now class is private
    public static void main(String[] args) {
        // Create a graph
        Graph graph = new Graph();
        graph.addNode(0);
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        // Find the minimum number of colors
        int minColors = graph.findMinColoring();
        System.out.println("Minimalna liczba kolorów: " + minColors);
    }
}