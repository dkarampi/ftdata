package com.dkarampi;

import java.util.*;

public class Graph {

    public static void main(String[] args) {
        Node n1 = new Node(1, Type.COMPUTABLE);
        Node n2 = new Node(2, Type.COMPUTABLE);
        Node n3 = new Node(3, Type.COMPUTABLE);
        Node n4 = new Node(4, Type.COMPUTABLE);
        Node n5 = new Node(5, Type.COMPUTABLE);
        Node n6 = new Node(6, Type.COMPUTABLE);
        Node n7 = new Node(7, Type.COMPUTABLE);
        Node n8 = new Node(8, Type.COMPUTABLE);
        Node n9 = new Node(9, Type.COMPUTABLE);


        buildPlan(n1);
    }

    private static List<Node> buildPlan(Node node) {
        Set<Node> visited = new HashSet<>();
        buildReversePlan(node, new LinkedList<>(), visited, new ArrayDeque<>());
        return null;
    }

    private static void buildReversePlan(Node node, List<Node> reversePlan, Set<Node> visited, Deque endNodeStack) {
        visited.add(node);

        if (node.getOutNodes().size() == 1) {
            buildReversePlan(node, reversePlan, visited, endNodeStack);
        } else if (node.getOutNodes().size() > 1) {
            buildReversePlanMultipleChildren(node, reversePlan, visited, endNodeStack);
        }

        reversePlan.add(node);
    }

    // Diamond structure
    private static void buildReversePlanMultipleChildren(Node node, List<Node> reversePlan, Set<Node> visited, Deque endNodeStack) {
        int numOfOutNodes = node.getOutNodes().size();

        for (int i = numOfOutNodes-1; i >= 0; i--) {
            Node child = node.getOutNodes().get(i);
            if (visited.contains(child)) {
                // This node has been visited by another branch

                continue;
            }

            if (i == numOfOutNodes - 1) {
                endNodeStack.add(new Node(node.getId(), Type.END));
            }

            buildReversePlan(child, reversePlan, visited, endNodeStack);


        }

        reversePlan.add(new Node(node.getId(), Type.BEGIN));
        reversePlan.add(node);
    }
}


enum Type {
    BEGIN,
    CHECKPOINT,
    END,
    COMPUTABLE,
}
class Node {
    private final int id;
    private final Type type;
    private List outNodes = new ArrayList<>();

    Node(int id, Type type) {
        this.id = id;
        this.type = type;
    }

    void addOutNode(Node node) {
        outNodes.add(node);
    }

    int getId() {
        return id;
    }

    Type getType() {
        return type;
    }

    List<Node> getOutNodes() {
        return outNodes;
    }
}
