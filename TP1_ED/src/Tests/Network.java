package Tests;

import ExceptionsAulas.ElementNotFoundException;

import java.util.Iterator;

public class Network<T> implements NetworkADT<T> {

    private final int DEFAULT_CAPACITY = 10;

    private double[][] adjMatrix;
    private T[] vertices;
    private int vertexCounter;

    public Network() {
        vertexCounter = 0;
        adjMatrix = new double[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        vertices = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public Network(int capacity) {
        vertexCounter = 0;
        adjMatrix = new double[capacity][capacity];
        vertices = (T[]) new Object[capacity];
    }

    @Override
    public void addVertex(T vertex) {
        if (vertexCounter == vertices.length) {
            expandCapacity();
        }

        for (int i = 0; i < vertices.length; i++) {
            adjMatrix[vertexCounter][i] = 0;
            adjMatrix[i][vertexCounter] = 0;
        }

        vertices[vertexCounter] = vertex;
        vertexCounter++;
    }

    private void expandCapacity() {
        T[] newVertices = (T[]) new Object[vertexCounter];
        double[][] newMatrix = new double[vertexCounter * 2][vertexCounter * 2];

        System.arraycopy(vertices, 0, newVertices, 0, vertexCounter);
        System.arraycopy(adjMatrix, 0, newMatrix, 0, vertexCounter);

        vertices = newVertices;
        adjMatrix = newMatrix;
    }

    @Override
    public void removeVertex(T vertex) {
        int vertexIndex = findVertexIndex(vertex);

        if (vertexIndex != -1) {
            if (size() > 1) {

                vertices[vertexIndex] = vertices[vertexCounter - 1]; //Mete o último elemento do Array no lugar do que foi removido

                for (int i = 0; i < vertexCounter; i++) {
                    adjMatrix[i][vertexIndex] = adjMatrix[i][vertexCounter - 1];
                    adjMatrix[vertexIndex][i] = adjMatrix[vertexCounter - 1][i];
                }

                vertices[vertexCounter - 1] = null;
                vertexCounter--;

                for (int i = 0; i < vertexCounter; i++) {
                    adjMatrix[i][vertexCounter] = 0;
                    adjMatrix[vertexCounter][i] = 0;
                }

            } else {
                vertices[0] = null;
                vertexCounter = 0;
                adjMatrix[0][0] = 0;
            }
        }
    }

    private int findVertexIndex(T vertex) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].equals(vertex)) {
                return i;
            }
        }
        return -1;
    }

    public boolean hasVertex(T vertex) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].equals(vertex)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addEdge(T vertex1, T vertex2) {
        try {
            addEdgeToMatrix(findVertexIndex(vertex1), findVertexIndex(vertex2));
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addEdgeToMatrix(int indexVertex1, int indexVertex2) throws ElementNotFoundException {
        if (validateIndex(indexVertex1) && validateIndex(indexVertex2)) {
            adjMatrix[indexVertex1][indexVertex2] = 1;
            adjMatrix[indexVertex2][indexVertex1] = 1;
        } else {
            throw new ElementNotFoundException("Vertex not found.");
        }
    }

    private void addEdgeToMatrix(int indexVertex1, int indexVertex2, int weight) throws ElementNotFoundException {
        if (validateIndex(indexVertex1) && validateIndex(indexVertex2)) {
            adjMatrix[indexVertex1][indexVertex2] = weight;
            adjMatrix[indexVertex2][indexVertex1] = weight;
        } else {
            throw new ElementNotFoundException("Vertex not found.");
        }
    }

    private boolean validateIndex(int index) {
        return index >= 0 && index < vertexCounter;
    }

    @Override
    public void removeEdge(T vertex1, T vertex2) {
        try {
            removeEdgeFromMatrix(findVertexIndex(vertex1), findVertexIndex(vertex2));
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void removeEdgeFromMatrix(int indexVertex1, int indexVertex2) throws ElementNotFoundException {
        if (validateIndex(indexVertex1) && validateIndex(indexVertex2)) {
            adjMatrix[indexVertex1][indexVertex2] = -1;
            adjMatrix[indexVertex2][indexVertex1] = -1;
        }
    }

    @Override
    public Iterator iteratorBFS(T startVertex) {
        return null;
    }

    @Override
    public Iterator iteratorDFS(T startVertex) {
        return null;
    }

    @Override
    public Iterator iteratorShortestPath(T startVertex, T targetVertex) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return vertexCounter == 0;
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public int size() {
        return vertexCounter;
    }

    @Override
    public void addEdge(T vertex1, T vertex2, double weight) {

    }

    @Override
    public double shortestPathWeight(T vertex1, T vertex2) {
        return 0;
    }

    @Override
    public String toString(){

        String str = "";

        for (T vertex : vertices) {
            if (vertex != null) {
                str += vertex + "\n";
            }
        }
        return str;
    }
}
