package Graphs;

import java.util.Iterator;

import Heaps.LinkedHeap;
import Interfaces.NetworkADT;
import Interfaces.UnorderedListADT;
import Lists.UnorderedLists.ArrayUnorderedList;
import PriorityQueue.PriorityQueue;
import exceptions.EmptyCollectionException;
import exceptions.UnknownPathException;

public class Network<T> extends MatrixGraph<T> implements NetworkADT<T> {
    protected final int DEFAULT_CAPACITY = 10;
    private double[][] adjMatrix;

    public Network() {
        super();
        this.adjMatrix = new double[this.DEFAULT_CAPACITY][this.DEFAULT_CAPACITY];
    }

    @Override
    public void addVertex(T vertex) {
        if (this.numVertices + 1 >= this.adjMatrix.length) {
            this.expandMatrix();
        }

        super.addVertex(vertex);
    }

    @Override
    public void addEdge(T vertex1, T vertex2, double weight) {
        if (weight <= 0.0D) {
            throw new IllegalArgumentException("The weight cannot be under the default.");
        } else {
            super.addEdge(vertex1, vertex2);
            this.setEdgeWeight(vertex1, vertex2, weight);
        }
    }

    protected int[] getEdgeWithWeightOf(double weight, boolean[] visited) {
        int[] edge = new int[2];
        for (int i = 0; i < numVertices; i++)
            for (int j = 0; j < numVertices; j++)
                if ((adjMatrix[i][j] == weight) && (visited[i] ^ visited[j])) {
                    edge[0] = i;
                    edge[1] = j;
                    return edge;
                }

        // Will only get to here if a valid edge is not found
        edge[0] = -1;
        edge[1] = -1;
        return edge;
    }

    public void setEdgeWeight(T firstVertex, T secondVertex, double weight) {
        if (weight < 0.0D) {
            throw new IllegalArgumentException("The weight cannot be under the default.");
        }

        int first = this.getIndex(firstVertex);
        int second = this.getIndex(secondVertex);

        if (secondVertex.equals("exterior") || firstVertex.equals("exterior") || secondVertex.equals("entrada") || firstVertex.equals("entrada")) {
            this.adjMatrix[first][second] = 0;
            this.adjMatrix[second][first] = 0;
        } else {
            this.adjMatrix[first][second] = weight;
        }

    }

    public double getEdgeWeight(T firstVertex, T secondVertex) {
        int first = this.getIndex(firstVertex);
        int second = this.getIndex(secondVertex);

        return this.adjMatrix[first][second];
    }

    private void expandMatrix() {
        double[][] tempMatrix = new double[this.numVertices * 2][this.numVertices * 2];

        for (int i = 0; i < this.numVertices; ++i) {
            for (int j = 0; j < this.numVertices; ++j) {
                tempMatrix[i][j] = this.adjMatrix[i][j];
            }
        }

        this.adjMatrix = tempMatrix;
    }

    public Iterator<T> iteratorShortestWeight(T startVertex, T targetVertex) throws UnknownPathException, EmptyCollectionException {
        return shortestPathWeight(startVertex, targetVertex).iterator();
    }

    @Override
    public ArrayUnorderedList<T> shortestPathWeight(T vertex1, T vertex2) throws EmptyCollectionException, UnknownPathException {
        PriorityQueue<Pair<T>> priorityQueue = new PriorityQueue<>();
        UnorderedListADT<T> verticesFromPossiblePath = new ArrayUnorderedList<>();
        ArrayUnorderedList<T> result = new ArrayUnorderedList<>();
        Pair<T> startPair = new Pair<>(null, vertex1, 0.0);

        priorityQueue.addElement(startPair, (int) startPair.cost);

        while (!priorityQueue.isEmpty()) {
            Pair<T> pair = priorityQueue.removeNext();
            T vertex = pair.vertex;
            double minCost = pair.cost;

            if (vertex.equals(vertex2)) {
                Pair<T> finalPair = pair;

                while (finalPair != null) {
                    result.addToFront(finalPair.vertex);
                    finalPair = finalPair.previous;
                }

                return result;
            }

            verticesFromPossiblePath.addToRear(vertex);

            for (int i = 0; i < numVertices; i++) {
                if (super.adjMatrix[getIndex(vertex)][i] && !verticesFromPossiblePath.contains(vertices[i])) {
                    double minCostToVertex = minCost + adjMatrix[getIndex(vertex)][i];
                    Pair<T> tmpPair = new Pair<>(pair, vertices[i], minCostToVertex);
                    priorityQueue.addElement(tmpPair, (int) tmpPair.cost);
                }
            }
        }

        throw new UnknownPathException("Path doesn't exist");
    }
    public ArrayUnorderedList<T> averagePath(T vertex1, T vertex2) throws EmptyCollectionException, UnknownPathException {
    PriorityQueue<Pair<T>> priorityQueue = new PriorityQueue<>();
    UnorderedListADT<T> verticesFromPossiblePath = new ArrayUnorderedList<>();
    ArrayUnorderedList<T> result = new ArrayUnorderedList<>();
    Pair<T> startPair = new Pair<>(null, vertex1, 0.0);

    priorityQueue.addElement(startPair, (int) startPair.cost);

    while (!priorityQueue.isEmpty()) {
        Pair<T> pair = priorityQueue.removeNext();
        T vertex = pair.vertex;
        double minCost = pair.cost;

        if (vertex.equals(vertex2)) {
            Pair<T> finalPair = pair;

            while (finalPair != null) {
                result.addToFront(finalPair.vertex);
                finalPair = finalPair.previous;
            }

            return result;
        }

        verticesFromPossiblePath.addToRear(vertex);

        for (int i = 0; i < numVertices; i++) {
            if (super.adjMatrix[getIndex(vertex)][i] && !verticesFromPossiblePath.contains(vertices[i])) {
                double minCostToVertex = minCost + adjMatrix[getIndex(vertex)][i] + calculateDensityCost(vertex, vertices[i]);
                Pair<T> tmpPair = new Pair<>(pair, vertices[i], minCostToVertex);
                priorityQueue.addElement(tmpPair, (int) tmpPair.cost);
            }
        }
    }

    throw new UnknownPathException("Path doesn't exist");
}

private double calculateDensityCost(T vertex1, T vertex2) {
    // Implemente lógica para calcular o custo com base na densidade das conexões
    // Você pode considerar o número de conexões ao redor dos vértices ou outras métricas.
    // Quanto mais conexões ao redor, menor o custo.
    return 0.0;
}

    @SuppressWarnings("unchecked")
    public Network mstNetwork() {
        int x, y;
        int index;
        double weight;
        int[] edge = new int[2];
        LinkedHeap<Double> minHeap = new LinkedHeap<>();
        Network<T> resultGraph = new Network<>();
        if (isEmpty() || !isConnected())
            return resultGraph;
        resultGraph.adjMatrix = new double[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++)
            for (int j = 0; j < numVertices; j++)
                resultGraph.adjMatrix[i][j] = Double.POSITIVE_INFINITY;
        resultGraph.vertices = (T[]) (new Object[numVertices]);
        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++)
            visited[i] = false;

        edge[0] = 0;
        resultGraph.vertices[0] = this.vertices[0];
        resultGraph.numVertices++;
        visited[0] = true;

        // Add all edges, which are adjacent to the starting vertex, to the heap
        for (int i = 0; i < numVertices; i++)
            minHeap.addElement(adjMatrix[0][i]);
        while ((resultGraph.size() < this.size()) && !minHeap.isEmpty()) {
            // Get the edge with the smallest weight that has exactly one vertex already in the resultGraph
            do {
                try {
                    weight = minHeap.removeMin();
                    edge = getEdgeWithWeightOf(weight, visited);
                } catch (EmptyCollectionException e) {
                    System.out.println(e.getMessage());
                }
            } while (!indexIsValid(edge[0]) || !indexIsValid(edge[1]));
            x = edge[0];
            y = edge[1];
            if (!visited[x])
                index = x;
            else
                index = y;
            // Add the new edge and vertex to the resultGraph
            resultGraph.vertices[index] = this.vertices[index];
            visited[index] = true;
            resultGraph.numVertices++;
            resultGraph.adjMatrix[x][y] = this.adjMatrix[x][y];
            resultGraph.adjMatrix[y][x] = this.adjMatrix[y][x];

            // Add all edges, that are adjacent to the newly added vertex, to the heap
            for (int i = 0; i < numVertices; i++) {
                if (!visited[i] && (this.adjMatrix[i][index] < Double.POSITIVE_INFINITY)) {
                    edge[0] = index;
                    edge[1] = i;
                    minHeap.addElement(adjMatrix[index][i]);
                }
            }
        }
        return resultGraph;
    }

    public String toString() {
        if (numVertices == 0)
            return "Graph is empty";

        String result = super.toString();

        //Print the weights of the edges
        result += "\n\nWeights of Edges";
        result += "\n----------------\n";
        result += "index\tweight\n\n";

        for (int i = 0; i < numVertices; i++) {
            for (int j = numVertices - 1; j > i; j--) {
                if (super.adjMatrix[i][j]) {
                    result += i + " to " + j + "\t";
                    result += adjMatrix[i][j] + "\n";
                }
            }
        }

        result += "\n";
        return result;
    }
    
    
}