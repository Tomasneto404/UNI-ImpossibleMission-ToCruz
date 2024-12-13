package Game.Mission;

import ClassesAulas.ArrayUnorderedList;
import ClassesAulas.LinkedQueue;
import ClassesAulas.LinkedStack;
import ExceptionsAulas.EmptyCollectionException;
import InterfacesAulas.GraphADT;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Represents a graph data structure using an adjacency matrix implementation.
 * The graph stores vertices and their connections and supports basic operations such as adding/removing vertices and edges,
 * as well as traversing the graph using BFS (Breadth-First Search) and DFS (Depth-First Search).
 *
 * @param <T> The type representing the vertices in the graph.
 * @author Tânia Morais
 * @author Tomás Neto
 */
public class Graph<T> implements GraphADT<T> {

    protected final int DEFAULT_CAPACITY = 10;
    protected int numVertices;
    protected boolean[][] adjMatrix;
    protected T[] vertices;

    /**
     * Creates an empty graph.
     */
    public Graph() {
        numVertices = 0;
        this.adjMatrix = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Expands the capacity of the graph's adjacency matrix and vertex array when more space is needed.
     */
    protected void expandCapacity() {
        T[] largerVertices = (T[]) (new Object[vertices.length * 2]);
        boolean[][] largerAdjMatrix = new boolean[vertices.length * 2][vertices.length * 2];

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                largerAdjMatrix[i][j] = adjMatrix[i][j];
            }
            largerVertices[i] = vertices[i];
        }

        vertices = largerVertices;
        adjMatrix = largerAdjMatrix;
    }

    /**
     * Retrieves the index of a given vertex in the graph's vertex array.
     *
     * @param vertex the vertex to find in the graph.
     * @return the index of the vertex.
     * @throws NoSuchElementException if the vertex is not found.
     */
    protected int getIndex(T vertex) throws NoSuchElementException {

        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(vertex)) {
                return i;
            }
        }
        throw new NoSuchElementException("Vertex not found");

    }

    /**
     * Checks if the provided index is within valid bounds for the graph's vertices array.
     *
     * @param index the index to check.
     * @return true if the index is valid, false otherwise.
     */
    private boolean indexIsValid(int index) {
        return (index >= 0 && index < numVertices);
    }

    /**
     * Inserts an edge between two vertices of the graph.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2));
    }

    /**
     * Inserts an edge between two vertices of the graph.
     *
     * @param index1 the first index
     * @param index2 the second index
     */
    public void addEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = true;
            adjMatrix[index2][index1] = true;
        }

    }

    /**
     * Adds a vertex to the graph, expanding the capacity of the graph if
     * necessary. It also associates an object with the vertex.
     *
     * @param vertex the vertex to add to the graph
     */
    public void addVertex(T vertex) {
        if (numVertices == vertices.length) {
            expandCapacity();
        }
        vertices[numVertices] = vertex;
        for (int i = 0; i <= numVertices; i++) {
            adjMatrix[numVertices][i] = false;
            adjMatrix[i][numVertices] = false;
        }
        numVertices++;
    }

    /**
     * Removes a vertex from the graph by its value.
     *
     * @param vertex the vertex to remove.
     */
    @Override
    public void removeVertex(T vertex) {
        removeVertex(getIndex(vertex));
    }

    /**
     * Removes a vertex from the graph by its index.
     *
     * @param index the index of the vertex to remove.
     */
    public void removeVertex(int index) {

        if (!isEmpty()) {
            for (int i = index; i < numVertices - 1; i++) {
                this.vertices[i] = this.vertices[i + 1];
            }

            numVertices--;
            this.vertices[numVertices] = null;
        }

    }

    /**
     * Removes an edge between two vertices using their indices.
     *
     * @param index1 the index of the first vertex.
     * @param index2 the index of the second vertex.
     */
    public void removeEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = false;
            adjMatrix[index2][index1] = false;

        }
    }

    /**
     * Removes an edge between two vertices by their values.
     *
     * @param vertex1 the first vertex.
     * @param vertex2 the second vertex.
     */
    @Override
    public void removeEdge(T vertex1, T vertex2) {
        removeEdge(getIndex(vertex1), getIndex(vertex2));
    }

    /**
     * Returns an iterator to traverse the graph using a depth-first search (DFS) starting from a specified vertex.
     *
     * @param startVertex the starting vertex for DFS traversal.
     * @return an iterator that traverses the graph in DFS order.
     */
    @Override
    public Iterator iteratorDFS(T startVertex) {
        try {
            return iteratorBFS(getIndex(startVertex));
        } catch (EmptyCollectionException ex) {
            return null;
        }
    }

    /**
     * Placeholder for an unimplemented method to find the shortest path between two vertices.
     *
     * @param startVertex  the starting vertex.
     * @param targetVertex the destination vertex.
     */
    @Override
    public Iterator iteratorShortestPath(T startVertex, T targetVertex) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Checks if the graph contains any vertices.
     *
     * @return true if the graph is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return (numVertices == 0);
    }

    /**
     * Placeholder for an unimplemented method to check if the graph is connected.
     *
     * @return false always as it is not implemented.
     */
    @Override
    public boolean isConnected() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Returns the number of vertices currently in the graph.
     *
     * @return the number of vertices in the graph.
     */
    @Override
    public int size() {
        return numVertices;
    }

    /**
     * Returns an iterator to traverse the graph using a breadth-first search (BFS) starting from a specified vertex.
     *
     * @param startVertex the starting vertex for BFS traversal.
     * @return an iterator that traverses the graph in BFS order.
     */
    @Override
    public Iterator iteratorBFS(T startVertex) {
        try {
            return iteratorBFS(getIndex(startVertex));
        } catch (EmptyCollectionException ex) {
            return null;
        }
    }

    /**
     * Returns an iterator that performs a breadth first search traversal
     * starting at the given index.
     *
     * @param startIndex the index to begin the search from
     * @return an iterator that performs a breadth first traversal
     */
    public Iterator<T> iteratorBFS(int startIndex) throws EmptyCollectionException {
        Integer x;
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<Integer>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();
        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }
        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalQueue.enqueue(startIndex);
        visited[startIndex] = true;
        while (!traversalQueue.isEmpty()) {
            x = traversalQueue.dequeue(); //nunca da excessão
            resultList.addToRear(vertices[x.intValue()]);
            /**
             * Find all vertices adjacent to x that have not been visited and
             * queue them up
             */
            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[x.intValue()][i] && !visited[i]) {
                    traversalQueue.enqueue(i);
                    visited[i] = true;
                }
            }
        }
        return resultList.iterator();
    }

    /**
     * Returns an iterator that performs a depth first search traversal starting
     * at the given index.
     *
     * @param startIndex the index to begin the search traversal from
     * @return an iterator that performs a depth first traversal
     */
    public Iterator<T> iteratorDFS(int startIndex) throws EmptyCollectionException {
        Integer x = null;
        boolean found;
        LinkedStack<Integer> traversalStack = new LinkedStack<Integer>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();
        boolean[] visited = new boolean[numVertices];
        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalStack.push(startIndex);
        resultList.addToRear(vertices[startIndex]);
        visited[startIndex] = true;
        while (!traversalStack.isEmpty()) {
            x = traversalStack.peek();
            found = false;
            /**
             * Find a vertex adjacent to x that has not been visited and push it
             * on the stack
             */
            for (int i = 0; (i < numVertices) && !found; i++) {
                if (adjMatrix[x.intValue()][i] && !visited[i]) {
                    traversalStack.push(i);
                    resultList.addToRear(vertices[i]);
                    visited[i] = true;
                    found = true;
                }
            }
            if (!found && !traversalStack.isEmpty()) {
                traversalStack.pop();
            }
        }
        return resultList.iterator();
    }

}
